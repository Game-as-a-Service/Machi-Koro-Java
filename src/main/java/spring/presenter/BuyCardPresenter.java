package spring.presenter;

import app.usecase.BuyCardUseCase;
import domain.events.BuyCardEvent;
import domain.events.DomainEvent;
import domain.events.GameOverEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static spring.presenter.RollDicePresenter.getEvent;


public class BuyCardPresenter implements BuyCardUseCase.Presenter {
    private BuyCardPresenterViewModel viewModel;

    @Override
    public void present(List<DomainEvent> events) {
        viewModel = getEvent(events, GameOverEvent.class)
                .map(e -> new BuyCardPresenterViewModel(e.message))
                .orElse(getEvent(events, BuyCardEvent.class)
                        .map(e -> new BuyCardPresenterViewModel(e.message))
                        .orElse(null));
    }

    public Optional<BuyCardPresenterViewModel> getViewModel() {
        return Optional.ofNullable(viewModel);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class BuyCardPresenterViewModel {
        private String message;
    }
}
