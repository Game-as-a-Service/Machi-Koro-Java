package domain.card.establishment;

public enum IndustryColor {
    RED(1),
    BLUE(2),
    GREEN(3),
    PURPLE(4);

    private final int order;

    IndustryColor(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
