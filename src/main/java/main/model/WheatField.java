package main.model;

public class WheatField extends Establishment {

    public WheatField() {
        super("小麥田", null, 1, CardType.CROP, 6, 1, Industry.BLUE);
    }

    @Override
    public void takeEffect(Game game) {
        // 任何人骰出這個數字時，你都可以從銀行獲得1元
        if (isDicePointToTakeEffect(game.getCurrentDicePoint())) {
            game.getBank().payCoin(1);
            game.getTurnPlayer().gainCoin(1);
        }
    }
}
