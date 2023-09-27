package spring.presenter;

import domain.events.CreateGameEvent;
import domain.events.DomainEvent;
import spring.presenter.viewmodel.CreateGameViewModel;

import java.util.List;
import java.util.Optional;

public class CreateGamePresenter extends AbstractPresenter {

    private CreateGameViewModel createGameViewModel;

    public Optional<Object> getViewModel() {
        return Optional.ofNullable(createGameViewModel);
    }

    @Override
    public void present(List<DomainEvent> events) {
        createGameViewModel = getEvent(events, CreateGameEvent.class)
                .map(e -> new CreateGameViewModel(e.getGameId()))
                .orElse(null);
    }
}
