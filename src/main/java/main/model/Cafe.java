package main.model;

public class Cafe extends Establishment {
    private final int COIN_TO_PAY = 1;
    private final int COIN_TO_GAIN = 1;

    public Cafe() {
        super("咖啡館", null, 2, CardType.RESTAURANT, 6, 3, Industry.RED);
    }

    @Override
    public void takeEffect(Game game) {
        // 任何人骰出這個數字時，擁有此手牌的玩家能從骰骰子的人獲得1元，並且骰骰的人扣1元
        if (isDicePointToTakeEffect(game.getCurrentDicePoint()) && playerHasEnoughCoin(game)) {
            payCoin(game.getTurnPlayer());
            game.getPlayers().stream()
                    .filter(player -> player.getOwnedEstablishment().contains(this))
                    .forEach(this::gainCoin);
        }
    }

    private boolean playerHasEnoughCoin(Game game) {
        return game.getTurnPlayer().getTotalCoin() > 0;
    }

    void payCoin(Player player) {
        player.payCoin(COIN_TO_PAY);
    }

    void gainCoin(Player player) {
        player.gainCoin(COIN_TO_GAIN);
    }
}
