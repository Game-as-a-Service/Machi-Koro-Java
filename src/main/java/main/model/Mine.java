package main.model;

public class Mine extends Establishment {
    public Mine() {
        super("礦場", 6, CardType.Nature_Resources, 6, 9, Industry.BLUE);
    }

    @Override
    public void takeEffect(Game game, Player player) {
        if (isDicePointToTakeEffect(game.getCurrentDicePoint())) {
            game.getBank().payCoin(5);
            player.gainCoin(5);
        }
    }
}
