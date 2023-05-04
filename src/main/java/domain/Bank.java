package domain;

public class Bank {

    public static final int TOTAL_COINS = 282;
    public static final int INIT_PAY_COINS = 3;
    private int coins;

    public Bank() {
        this(TOTAL_COINS);
    }

    public Bank(int coins) {
        this.coins = coins;
    }

    public int getTotalCoin() {
        return coins;
    }

    public void payCoin(int coins) {
        this.coins -= coins;
    }

    public void gainCoin(int coins) {
        this.coins += coins;
    }
}
