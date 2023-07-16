package domain;

import domain.card.establishment.Bakery;
import domain.card.establishment.WheatField;

import java.util.List;

public class Game {
    private final Bank bank;
    private final List<Player> players;
    private final List<Dice> dices;
    private int currentDicePoint;
    private Player turnPlayer;
    private final Marketplace marketplace;

    public Game(Bank bank, List<Player> players, List<Dice> dices, Marketplace marketplace) {
        this.bank = bank;
        this.players = players;
        this.dices = dices;
        this.marketplace = marketplace;
    }

    public void distributeResources(int dicePoint) {
        this.setCurrentDicePoint(dicePoint);
        this.getPlayers().forEach(player -> player.establishmentTakeEffect(this));
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

    public void start() {

    }

    public void startNewTurn() {

    }

    public void end() {

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
}
