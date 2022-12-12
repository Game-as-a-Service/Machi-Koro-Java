package utils;

import domain.*;

abstract public class GameBuilder {

    public abstract  GameBuilder setBank(int initialCoinForBank);
    public abstract  GameBuilder setPlayers(int numberOfPlayers);
    public abstract  GameBuilder setDices(int numberOfDices);
    public abstract  GameBuilder setFixedDicePoint(int dicePoint);
    public abstract  GameBuilder setTurnPlayer(Player turnPlayer);
    public abstract  GameBuilder setMarketplace(Marketplace marketplace);
    public abstract Game build();
}
