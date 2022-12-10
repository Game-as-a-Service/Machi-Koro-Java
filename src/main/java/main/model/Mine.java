package main.model;

public class Mine extends Establishment {
    public Mine() {
        super("礦場", 6, CardType.Nature_Resources, 6, 9, Industry.BLUE);
    }

    @Override
    public void takeEffect(Game game, Player player) {
        // 當骰子點數為 9，持有該卡片的玩家從銀行獲得 5 元
        if (isDicePointToTakeEffect(game.getCurrentDicePoint())) {
            game.getBank().payCoin(5);
            player.gainCoin(5);
        }
    }
}
