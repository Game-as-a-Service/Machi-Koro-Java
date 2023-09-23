package spring.presenter;

import com.fasterxml.jackson.annotation.JsonProperty;
import domain.events.DomainEvent;
import domain.events.RollDiceEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class RollDicePresenter extends AbstractPresenter {
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
                .map(e -> new RollDiceViewModel(e.getDicePoint()))
                .orElse(null);
    }

    public Optional<RollDiceViewModel> getViewModel() {
        return ofNullable(viewModel);
    }

}