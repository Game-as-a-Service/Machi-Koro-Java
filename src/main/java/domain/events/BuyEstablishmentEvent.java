package domain.events;

public class BuyEstablishmentEvent extends DomainEvent {
    private final String message;
    public BuyEstablishmentEvent(String message) {
        this.message = message;
    }
}
