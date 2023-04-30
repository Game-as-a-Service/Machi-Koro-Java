package domain.card.establishment;

public enum IndustryColor {
    BLUE(2),
    GREEN(3),
    RED(1),
    PURPLE(4);

    private int order;

    IndustryColor(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
