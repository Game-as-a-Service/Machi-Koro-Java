package main.model;

public class Forest extends Establishment {
    public Forest() {
        super("森林", 3, CardType.CROP, 6, 5, Industry.BLUE);
    }

    @Override
    public void takeEffect(Game game, Player player) {
        if (isDicePointToTakeEffect(game.getCurrentDicePoint())) {
            game.getBank().payCoin(1);
            player.gainCoin(1);
        }
    }

    @Override
    public boolean isDicePointToTakeEffect(int dicePoint)  {
        return dicePoint == this.getDiceRollNeededToActivateEffect();
    }
}
