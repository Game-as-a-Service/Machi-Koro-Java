package main.model;

public class Bank {
    private int totalCoin = 282;

    public Bank() {
    }

    public Bank(int coin) {
        this.totalCoin = coin;
    }

    public int getTotalCoin() {
        return totalCoin;
    }

    public void payCoin(int coin) {
        this.totalCoin -= coin;
    }

    public void gainCoin(int coin) {
        this.totalCoin += coin;
    }
}
