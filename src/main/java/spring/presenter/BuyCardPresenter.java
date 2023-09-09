package spring.presenter;

import app.usecase.BuyCardUseCase;
import domain.events.DomainEvent;

import java.util.List;
import java.util.Optional;

public class BuyCardPresenter implements BuyCardUseCase.Presenter {
    private BuyCardPresenterViewModel viewModel;
    @Override
    public void present(List<DomainEvent> events) {

    }

    public Optional<BuyCardPresenterViewModel> getViewModel() {

        return Optional.ofNullable(viewModel);
    }

    public class BuyCardPresenterViewModel {
    }
}
