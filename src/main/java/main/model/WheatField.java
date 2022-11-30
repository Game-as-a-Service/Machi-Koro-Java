package main.model;

import java.util.Optional;

public class WheatField extends Establishment {

    public WheatField() {
        super("小麥田", 0, CardType.CROP, 6, 1, Industry.BLUE);
    }

    @Override
    public void takeEffect(Game game) {
        // 任何人骰出這個數字時，你都可以從銀行獲得1元
        if (isDicePointToTakeEffect(game.getCurrentDicePoint())) {
            game.getBank().payCoin(1);
            var player = Optional.ofNullable(game.getPlayers().peek());
            if (player.isPresent()) {
                var wheatFields = player.get().getOwnedEstablishment().stream().filter(e -> e.equals(this)).toList();
                player.get().gainCoin(wheatFields.size());
            }
        }

    }

    public boolean isDicePointToTakeEffect(int dicePoint) {
        return dicePoint == this.getDiceRollNeededToActivateEffect();
    }

}
