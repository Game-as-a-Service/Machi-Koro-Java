package spring.presenter;

import app.usecase.RollDiceUseCase;
import com.fasterxml.jackson.annotation.JsonProperty;
import domain.events.DomainEvent;
import domain.events.RollDiceEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class RollDicePresenter implements RollDiceUseCase.Presenter {
    public RollDiceViewModel viewModel;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RollDiceViewModel {
        @JsonProperty("dicePoint")
        private int dicePoint;
    }

    @Override
    public void present(List<DomainEvent> events) {
        viewModel = getEvent(events, RollDiceEvent.class)
                .map(e -> new RollDiceViewModel(e.dicePoint))
                .orElse(null);
    }

    public Optional<RollDiceViewModel> getViewModel() {
        return ofNullable(viewModel);
    }

    public static <T extends DomainEvent> Optional<T> getEvent(List<DomainEvent> events,
                                                                Class<T> type) {
        return events.stream()
                .filter(e -> type.isAssignableFrom(e.getClass()))
                .map(e -> (T) e)
                .findFirst();
    }
}