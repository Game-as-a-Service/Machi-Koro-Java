package domain.card.landmark;

import domain.Game;
import domain.card.CardType;

public class ShoppingMall extends Landmark{
    public ShoppingMall() {
        super("購物中心", 10, CardType.MAJOR_ESTABLISHMENT);
    }

    @Override
    protected void doTakeEffect(Game game) {

    }
}
