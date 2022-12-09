package main.model;

public class Ranch extends Establishment {

    public Ranch() {
        super("牧場", 1, CardType.ANIMAL_HUSBANDRY, 6, 2, Industry.BLUE);
    }

    public void takeEffect(Game game, Player owner) {
        if (isDicePointToTakeEffect(game.getCurrentDicePoint())) {
            game.getBank().payCoin(1);
            owner.gainCoin(1);
        }
    }
}
