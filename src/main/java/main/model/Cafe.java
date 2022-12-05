package main.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cafe extends Establishment {

    public Cafe() {
        super("咖啡館", null, 2, CardType.RESTAURANT, 6, 3, Industry.RED);
    }

    @Override
    public void takeEffect(Game game) {
        int count = 0;
        // 任何人骰出這個數字時，擁有此手牌的玩家能從骰骰子的人獲得1元，並且骰骰的人扣1元
        if (isDicePointToTakeEffect(game.getCurrentDicePoint())) {
            if (game.getTurnPlayer().getTotalCoin() > 0) {
                game.getTurnPlayer().payCoin(1);
                List<Player> playerList = game.getPlayers().stream().toList();
                for (Player player : playerList) if (player.getOwnedEstablishment().contains(this)) player.gainCoin(1);
            }
        }
    }
}