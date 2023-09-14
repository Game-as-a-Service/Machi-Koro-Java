package app.usecase;

import app.exception.NotFoundException;
import app.output.GameRepository;
import domain.Game;
import domain.events.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import spring.presenter.BuyCardPresenter;
import spring.presenter.FlipLandMarkPresenter;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
public class FlipLandMarkUseCase {
    private final GameRepository gameRepository;

    public void execute(FlipLandMarkUseCase.Request request, FlipLandMarkPresenter presenter) {
        Game game = findGameById(request.gameId);
        List<DomainEvent> events = game.turnPlayerFlipLandMark(request.getPlayerId(), request.getCardName());
        gameRepository.save(game);
        presenter.present(events);
    }

    private Game findGameById(String gameId) {
        return gameRepository.findById(gameId).orElseThrow(NotFoundException::new);
    }

    public interface Presenter {
        void present(List<DomainEvent> events);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String gameId;
        private String playerId;
        private String cardName;
    }
}
