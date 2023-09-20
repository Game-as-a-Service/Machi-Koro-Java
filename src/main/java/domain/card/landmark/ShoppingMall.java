package domain.card.landmark;

import domain.card.CardType;

public class ShoppingMall extends Landmark {
    public static final int BONUS = 1;

    public ShoppingMall() {
        super("購物中心", 10, CardType.MAJOR_ESTABLISHMENT);
    }

    public ShoppingMall(boolean isFlipped) {
        super("購物中心", 10, CardType.MAJOR_ESTABLISHMENT, isFlipped);
    }

}
