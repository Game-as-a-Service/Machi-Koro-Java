package spring.presenter;

import app.usecase.FlipLandMarkUseCase;
import domain.events.DomainEvent;
import domain.events.FlipLandMarkEvent;
import domain.events.GameOverEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static spring.presenter.RollDicePresenter.getEvent;

public class FlipLandMarkPresenter implements FlipLandMarkUseCase.Presenter {
    private FlipLandMarkViewModel viewModel;

    public Optional<Object> getViewModel() {
        return Optional.ofNullable(viewModel);
    }


    @Override
    public void present(List<DomainEvent> events) {
        viewModel = getEvent(events, GameOverEvent.class)
                .map(e -> new FlipLandMarkViewModel(e.message))
                .orElse(getEvent(events, FlipLandMarkEvent.class)
                        .map(e -> new FlipLandMarkViewModel(e.message))
                        .orElse(null));
    }

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static class FlipLandMarkViewModel {
        private String message;
    }
}
