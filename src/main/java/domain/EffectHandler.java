package domain;

import domain.card.CardType;
import domain.card.establishment.BusinessCenter;
import domain.card.establishment.CheeseFactory;
import domain.card.establishment.Establishment;
import domain.card.establishment.FruitAndVegetableMarket;
import domain.card.establishment.FurnitureFactory;
import domain.card.establishment.IndustryColor;
import domain.card.establishment.Stadium;
import domain.card.establishment.TvStation;
import domain.card.landmark.ShoppingMall;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EffectHandler {
    // 持有 CheeseFactory, FurnitureFactory 或 FruitAndVegetableMarket 時，
    // 獲得的金幣 = 手牌中其他符合對應的 CardType 建築物張數 * effectCoins
    private final Map<Class<? extends Establishment>, CardType> establishmentToCardType = Map.of(
            CheeseFactory.class, CardType.ANIMAL_HUSBANDRY,
            FurnitureFactory.class, CardType.NATURE_RESOURCES,
            FruitAndVegetableMarket.class, CardType.CROP);

    public void takeEffectRed(Player turnPlayer, Player ownCardPlayer, int effectCoins) {
        // red: 如果別人骰出這個數字，他必須給你x元
        if (ownCardPlayer.hasLandmarkFlipped(ShoppingMall.class)) {
            effectCoins += 1;
        }
        var actualCoins = turnPlayer.checkEffectMoneyEnough(effectCoins);
        turnPlayer.payCoin(actualCoins);
        ownCardPlayer.gainCoin(actualCoins);
    }

    public void takeEffectBlue(Player ownCardPlayer, Bank bank, int effectCoins) {
        // blue: 任何人骰出這個數字時，你都可以從銀行獲得x元
        ownCardPlayer.gainCoin(effectCoins);
        bank.payCoin(effectCoins);
    }

    public void takeEffectGreen(Player turnPlayer, Bank bank, Establishment establishment) {
        int effectCoins = establishment.getEffectCoins();
        if (establishmentToCardType.containsKey(establishment.getClass())) {
            effectCoins *= turnPlayer.getEstablishments(establishmentToCardType.get(establishment.getClass())).size();
        }

        // green: 當你自己骰出這個數字時，可以從銀行獲得x元
        if (turnPlayer.hasLandmarkFlipped(ShoppingMall.class) && establishment.getCardType() == CardType.SHOP) {
            effectCoins += 1;
        }

        turnPlayer.gainCoin(effectCoins);
        bank.payCoin(effectCoins);
    }

    public void takeEffectPurple(Player turnPlayer, Player targetPlayer, int tradeEstablishmentIndex, int targetEstablishmentIndex, Establishment establishment, List<Player> players) {
        if (establishment instanceof BusinessCenter) {
            // 商業中心： 當你自己骰出這個數字時，你可以與其他玩家交換一間非[重要建築物 or 地標]的建築物。
            takeEffectBusinessCenter(turnPlayer, targetPlayer, tradeEstablishmentIndex, targetEstablishmentIndex);
           }
        if (establishment instanceof TvStation) {
            // 電視台： 當你自己骰出這個數字時，你可以指定任意一位玩家給你5元。
            takeEffectTvStation(turnPlayer, targetPlayer);
        }
        if (establishment instanceof Stadium) {
            // 體育館： 當你自己骰出這個數字時，每位玩家都必須給你2元。
            takeEffectStadium(turnPlayer, players);
        }
    }

    public void takeEffectBusinessCenter(Player turnPlayer, Player targetPlayer, int tradeEstablishmentIndex, int targetEstablishmentIndex) {
        // 商業中心： 當你自己骰出這個數字時，你可以與其他玩家交換一間非[重要建築物 or 地標]的建築物。
        var tradeEstablishment = (Establishment) turnPlayer.getHandCard(tradeEstablishmentIndex);
        var targetEstablishment = (Establishment) targetPlayer.getHandCard(targetEstablishmentIndex);

        if (new BusinessCenter().canNotTradeEstablishment(tradeEstablishment) ||
                new BusinessCenter().canNotTradeEstablishment(targetEstablishment)) {
            return;
        }
        turnPlayer.removeEstablishment(tradeEstablishmentIndex);
        turnPlayer.addCardToHandCard(targetEstablishment);

        targetPlayer.removeEstablishment(targetEstablishmentIndex);
        targetPlayer.addCardToHandCard(tradeEstablishment);
    }

    public void takeEffectStadium(Player turnPlayer, List<Player> players) {
        // 體育館： 當你自己骰出這個數字時，每位玩家都必須給你2元。
        players.forEach(otherPlayer -> {
            var actualCoins = otherPlayer.checkEffectMoneyEnough(new Stadium().getEffectCoins());
            turnPlayer.gainCoin(actualCoins);
            otherPlayer.payCoin(actualCoins);
        });
    }

    public void takeEffectTvStation(Player turnPlayer, Player targetPlayer) {
        // 電視台： 當你自己骰出這個數字時，你可以指定任意一位玩家給你5元。
        var actualCoins = targetPlayer.checkEffectMoneyEnough(new TvStation().getEffectCoins());
        turnPlayer.gainCoin(actualCoins);
        targetPlayer.payCoin(actualCoins);
    }

    public Map<Player, List<Establishment>> getOwnCardsPlayers(List<Player> players, int dicePoint, IndustryColor industryColor) {
        Map<Player, List<Establishment>> map = new LinkedHashMap<>();
        players.forEach(player -> map.put(player, player.getEstablishments(dicePoint, industryColor)));
        return map;
    }

}
