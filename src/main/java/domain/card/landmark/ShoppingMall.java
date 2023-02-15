package domain.card.landmark;

import domain.Game;
import domain.Player;
import domain.card.CardType;
import domain.card.establishment.Establishment;
import domain.card.establishment.Industry;

public class ShoppingMall extends Landmark {
    public ShoppingMall() {
        super("購物中心", 10, CardType.MAJOR_ESTABLISHMENT);
    }

    @Override
    protected void doTakeEffect(Game game) {
        for (Player player : game.getPlayers()) {
            if (!player.getOwnedLandmark().get(1).isCardsideTurnfront()) {
                return;
            } else {
                for (Establishment establishment : player.getOwnedEstablishment()) {
                    if (establishment.getIndustry().equals(Industry.GREEN)) {
                        if (game.isTurnPlayer(player)) {
                            effectCondition(game, establishment, player);
                        }
                    }
                    if (establishment.getIndustry().equals(Industry.RED)) {
                        if (!game.isTurnPlayer(player)) {
                            effectCondition(game, establishment, player);
                        }
                    }
                }
            }
        }
    }

    private void effectCondition(Game game, Establishment establishment, Player player) {
        for (int dice : establishment.getDiceRollNeededToActivateEffect()) {
            if (game.getCurrentDicePoint().equals(dice)) {
                player.gainCoin(1);
            }
        }
    }
}
