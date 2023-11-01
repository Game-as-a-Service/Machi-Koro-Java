package domain;

import domain.card.establishment.Bakery;
import domain.card.establishment.Establishment;
import domain.card.establishment.IndustryColor;
import domain.card.establishment.WheatField;
import domain.card.landmark.Landmark;
import domain.card.landmark.TrainStation;
import domain.events.*;
import domain.exceptions.MachiKoroException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.*;

import static java.util.Collections.singletonList;

@Builder
@Data
@AllArgsConstructor
public class Game {
    private String id;
    private Bank bank;
    private final List<Player> players;
    @Builder.Default
    private List<Dice> dices = List.of(new Dice(), new Dice());
    private int currentDicePoint = 0;
    private int targetEstablishmentIndex = 0;
    private Player turnPlayer;
    private Player targetPlayer;
    private Marketplace marketplace;

    public Game(List<Player> players) {
        this(UUID.randomUUID().toString(), players);
    }

    public Game(String id, List<Player> players) {
        players = Objects.requireNonNullElseGet(players, ArrayList::new);
        this.id = id;
        this.players = players;
        this.bank = new Bank();
        this.marketplace = new Marketplace();
        this.dices = List.of(new Dice(), new Dice());
        this.turnPlayer = !players.isEmpty() ? players.get(0) : null;
    }

    public Game(Bank bank, List<Player> players, Marketplace marketplace) {
        this.bank = bank;
        this.players = players;
        this.marketplace = marketplace;
        this.dices = List.of(new Dice(), new Dice());
    }

    public int getCurrentDicePoint() {
        return currentDicePoint;
    }

    public Player getTurnPlayer() {
        return turnPlayer;
    }

    public void setTurnPlayer(Player turnPlayer) {
        this.turnPlayer = turnPlayer;
    }

    public boolean isTurnPlayer(Player player) {
        return this.getTurnPlayer().equals(player);
    }

    public void setCurrentDicePoint(int currentDicePoint) {
        this.currentDicePoint = currentDicePoint;
    }

    public Bank getBank() {
        return bank;
    }

    public void setUp() {
        for (Player player : players) {
            player.gainCoin(3);
            bank.payCoin(3);
            player.addCardToHandCard(new Bakery());
            player.addCardToHandCard(new WheatField());
        }
    }

    public Player getPlayer(String playerId) {
        return players.stream().filter(player -> playerId.equals(player.getId())).findFirst().orElseThrow();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> getOtherPlayers(Player targetPlayer) {
        return getPlayers()
                .stream()
                .filter(player -> !player.equals(targetPlayer))
                .toList();
    }

    public Marketplace getMarketplace() {
        return marketplace;
    }

    public List<Dice> getDices() {
        return dices;
    }

    public void takeAllPlayersEffect() {
        EffectHandler effectHandler = new EffectHandler();

        var redOwnCardPlayers = effectHandler.getOwnCardsPlayers(getPlayersExcludeTurnPlayer(), currentDicePoint, IndustryColor.RED);
        redOwnCardPlayers.forEach((player, establishments) ->
                establishments.forEach(redEstablishment -> effectHandler.takeEffectRed(
                        turnPlayer, player, redEstablishment.getEffectCoins())));

        var blueOwnCardPlayers = effectHandler.getOwnCardsPlayers(players, currentDicePoint, IndustryColor.BLUE);
        blueOwnCardPlayers.forEach((player, establishments) ->
                establishments.forEach(blueEstablishment -> effectHandler.takeEffectBlue(player, bank, blueEstablishment.getEffectCoins())));

        var turnPlayerGreenCards = turnPlayer.getEstablishments(currentDicePoint, IndustryColor.GREEN);
        turnPlayerGreenCards.forEach(greenEstablishment -> effectHandler.takeEffectGreen(turnPlayer, bank, greenEstablishment));
        var purpleOwnCardPlayers = turnPlayer.getEstablishments(currentDicePoint, IndustryColor.PURPLE);
        purpleOwnCardPlayers.forEach(purpleEstablishment ->
                effectHandler.takeEffectPurple(turnPlayer, targetPlayer,targetEstablishmentIndex,targetEstablishmentIndex,purpleEstablishment,players));
    }

    public List<Player> getPlayersExcludeTurnPlayer() {
        return players.stream().filter(player -> !player.equals(turnPlayer)).toList();
    }

    public List<DomainEvent> toCreateGameEvent() {
        return singletonList(new CreateGameEvent(id));
    }

    public List<DomainEvent> rollDice(String playerId, boolean isTwoDices) {
        // TODO : isTwoDices 可以用 true or false 判斷，因為我們只有一顆骰子或兩顆骰子的情況
        checkIsTurnPlayer(playerId);

        if (isTwoDices && !turnPlayer.hasLandmarkFlipped(TrainStation.class)) {
            throw new IllegalArgumentException("Invalid quantity of dice");
        }

        if (isTwoDices) {
            currentDicePoint = dices.stream().mapToInt(Dice::throwDice).sum();
        } else {
            currentDicePoint = dices.get(0).throwDice();
        }

        var event = RollDiceEvent.builder().dicePoint(currentDicePoint).build();

        takeAllPlayersEffect();
        return List.of(event);
    }

    public List<DomainEvent> turnPlayerBuyCard(String playerId, String cardName) {
        checkIsTurnPlayer(playerId);
        Establishment establishment = marketplace.findEstablishmentByName(cardName);
        turnPlayer.buyEstablishment(establishment, bank);
        marketplace.removeEstablishment(establishment);
        DomainEvent buyEstablishmentEvent = new BuyCardEvent(String.format("玩家 %s 花費了 %d 元 建造了 %s", turnPlayer.getId(), establishment.getConstructionCost(), establishment.getName()));
        updateTurnPlayerToNextOne();
        return List.of(buyEstablishmentEvent);
    }


    private void checkIsTurnPlayer(String playerId) {
        if (!playerId.equals(turnPlayer.getId())) {
            throw new MachiKoroException("Turn player id is incorrect");
        }
    }

    public List<DomainEvent> turnPlayerFlipLandMark(String playerId, String cardName) {
        checkIsTurnPlayer(playerId);
        Landmark landmark = turnPlayer.getHandCard().getLandmarks().stream().filter(lm -> cardName.equals(lm.getName())).findFirst().orElseThrow(NoSuchElementException::new);
        turnPlayer.flipLandMark(landmark, bank);
        if (isGameOver(turnPlayer)) {
            DomainEvent GameOverEvent = new GameOverEvent(String.format("玩家 %s 勝利", turnPlayer.getId()));
            return List.of(GameOverEvent);
        } else {
            DomainEvent flipLandMarkEvent = new FlipLandMarkEvent(String.format("玩家 %s 花費了 %d 元 建造了 %s", turnPlayer.getId(), landmark.getConstructionCost(), landmark.getName()));
            updateTurnPlayerToNextOne();
            return List.of(flipLandMarkEvent);
        }
    }

    private boolean isGameOver(Player turnPlayer) {
        return turnPlayer.getLandmarks().stream().allMatch(Landmark::isFlipped);
    }

    private void updateTurnPlayerToNextOne() {
        int index = players.indexOf(turnPlayer);
        turnPlayer = players.get((index + 1) % players.size());
    }
}
