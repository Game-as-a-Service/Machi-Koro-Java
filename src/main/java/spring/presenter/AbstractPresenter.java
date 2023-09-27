package spring.presenter;

import app.usecase.Presenter;
import domain.events.DomainEvent;

import java.util.List;
import java.util.Optional;

public abstract class AbstractPresenter implements Presenter {
    @SuppressWarnings("unchecked")
    public static <T extends DomainEvent> Optional<T> getEvent(List<DomainEvent> events,
                                                               Class<T> type) {
        return events.stream()
                .filter(e -> type.isAssignableFrom(e.getClass()))
                .map(e -> (T) e)
                .findFirst();
    }
}
