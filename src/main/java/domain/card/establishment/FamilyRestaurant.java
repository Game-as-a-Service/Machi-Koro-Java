package domain.card.establishment;

import domain.Game;
import domain.Player;
import domain.card.CardType;

import java.util.Set;


public class FamilyRestaurant extends Establishment{
    private final int COIN_TO_PAY = 2;
    private final int COIN_TO_GAIN = 2;
    public FamilyRestaurant() { super("家庭餐廳",3, CardType.RESTAURANT, Set.of(9,10) , Industry.RED); }
    @Override
    public void takeEffect(Game game, Player player) {
        //如果別人骰出這個數字，他必須給你2元
        if(isDicePointToTakeEffect(game.getCurrentDicePoint()) && playerHasEnoughCoin(game)){
            game.getTurnPlayer().payCoin(COIN_TO_PAY);
            player.gainCoin(COIN_TO_GAIN);
        }else{
            //為了測試方便先丟出訊息
            System.out.println("點數錯誤不觸發效果");
        }
    }


    private boolean playerHasEnoughCoin(Game game) {return game.getTurnPlayer().getTotalCoin() > 0; }

}
