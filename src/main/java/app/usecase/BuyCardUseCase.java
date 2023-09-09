package app.usecase;

import app.output.GameRepository;
import domain.Game;
import domain.events.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.List;
import java.util.NoSuchElementException;
@Named
@RequiredArgsConstructor
public class BuyCardUseCase {
    private final GameRepository gameRepository;

    public void execute(Request request, Presenter presenter) {
        Game game = findGameById(request.gameId);
        List<DomainEvent> events = game.turnPlayerBuyCard(request.getType(), request.getCardName());
        gameRepository.save(game);
        presenter.present(events);
    }

    private Game findGameById(String gameId) {
        return gameRepository.findById(gameId).orElseThrow(NoSuchElementException::new);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String gameId;
        private String playerId;
        private String type;
        private String cardName;
    }

    public interface Presenter {
        void present(List<DomainEvent> events);
    }


}
