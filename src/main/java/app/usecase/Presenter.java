package app.usecase;

import domain.events.DomainEvent;

import java.util.List;

public interface Presenter {
    void present(List<DomainEvent> events);
}
