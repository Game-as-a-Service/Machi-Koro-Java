package app.usecase;

import app.exception.NotFoundException;
import app.output.GameRepository;
import domain.Game;
import domain.events.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
public class RollDiceUseCase {
    private final GameRepository gameRepository;

    public void execute(Request request, Presenter presenter) {

        // 查
        Game game = findGame(request);

        // 改
        var events = game.rollDice(request.playerId, request.diceCount);

        // 存
        gameRepository.save(game);

        // 推
        presenter.present(events);

    }

    private Game findGame(Request request) {
        String gameId = request.getGameId();
        return gameRepository.findById(gameId).orElseThrow(() -> new NotFoundException("this game is not found! gameId: " + gameId));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String gameId;
        private String playerId;
        private int diceCount;
    }

    public interface Presenter {
        void present(List<DomainEvent> events);
    }

}

