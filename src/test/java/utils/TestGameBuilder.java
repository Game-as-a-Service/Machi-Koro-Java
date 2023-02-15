package utils;

import domain.*;

import java.util.Arrays;
import java.util.List;

public class TestGameBuilder extends GameBuilder {
    private int initialCoinForBank = 100;
    private int numberOfPlayers = 1;
    private int numberOfDices = 1;
    private int fixedDicePoint;
    private Player turnPlayer;
    private Marketplace marketplace;

    @Override
    public GameBuilder setBank(int initialCoinForBank) {
        this.initialCoinForBank = initialCoinForBank;
        return this;
    }

    @Override
    public GameBuilder setPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        return this;
    }

    @Override
    public GameBuilder setDices(int numberOfDices) {
        this.numberOfDices = numberOfDices;
        return this;
    }

    @Override
    public GameBuilder setFixedDicePoint(int dicePoint) {
        this.fixedDicePoint = dicePoint;
        return this;
    }

    @Override
    public GameBuilder setTurnPlayer(Player turnPlayer) {
        this.turnPlayer = turnPlayer;
        return this;
    }

    @Override
    public GameBuilder setMarketplace(Marketplace marketplace) {
        this.marketplace = marketplace;
        return this;
    }

    @Override
    public Game build() {
        return new Game(
                createBankWithFixedCoin(initialCoinForBank),
                createPlayerArrayWithFixedNumber(numberOfPlayers),
                createDiceArrayWithFixedNumber(numberOfDices),
                marketplace
        ) {
            @Override
            public Integer getCurrentDicePoint() {
                return fixedDicePoint;
            }
        };
    }

    private static List<Player> createPlayerArrayWithFixedNumber(int numberOfPlayer) {
        var players = new Player[numberOfPlayer];
        for (int i = 0; i < numberOfPlayer; i++)
            players[i] = new Player(String.valueOf(i + 1));

        return Arrays.asList(players);
    }

    private static List<Dice> createDiceArrayWithFixedNumber(int numberOfDices) {
        var dices = new Dice[numberOfDices];
        for (int i = 0; i < numberOfDices; i++)
            dices[i] = new Dice();

        return Arrays.asList(dices);
    }

    private static Bank createBankWithFixedCoin(int initialCoinForBank) {
        return new Bank(initialCoinForBank);
    }
}
