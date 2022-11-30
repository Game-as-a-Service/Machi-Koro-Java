package main.model;

public class Bank {
    private int totalCoin;

    public Bank(int totalCoin) {
        this.totalCoin = totalCoin;
    }

    public void payCoin(int coin) {
        this.totalCoin -= coin;
    }

    public void gainCoin(int coin, Player player) {
        this.totalCoin += coin;
    }
}
