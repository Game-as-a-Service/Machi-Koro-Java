package main.model;

import java.util.Queue;

public class Game {

    private final Bank bank;
    private final Queue<Player> players;
    private final Dice dice;
    private final BluePrintMarketPlace bluePrintMarketPlace;

    public Game(Queue<Player> players, Dice dice) {
        this.bank = new Bank(282);
        this.players = players;
        this.dice = dice;
        this.bluePrintMarketPlace = new BluePrintMarketPlace();
    }

    public void distributeResources(int dicePoint) {
        this.throwDice();
        this.getPlayers().forEach(player -> player.getOwnedEstablishment().forEach(establishment -> establishment.takeEffect(this)));
    }

    public void setUp() {

    }

    public void start() {

    }

    public void startNewTurn() {

    }

    public void end() {

    }


    public Queue<Player> getPlayers() {
        return players;
    }

    public Bank getBank() {
        return bank;
    }

    public Dice getDice() {
        return dice;
    }

    public int getCurrentDicePoint() {
        return this.dice.getPoint();
    }

    public void throwDice() {
        this.dice.throwDice();
    }

    public BluePrintMarketPlace getBluePrintMarketPlace() {
        return bluePrintMarketPlace;
    }
}
