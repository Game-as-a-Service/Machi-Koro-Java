package app.usecase;

import app.repositories.GameRepository;
import domain.Game;
import domain.Player;
import lombok.*;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
public class CreateGameUseCase {
    private final GameRepository gameRepository;

    public void execute(Request request, Presenter presenter) {

        Player player = request.toPlayer();
        Game game = new Game(List.of(player));

        // 存
        gameRepository.save(game);

        // 推
        presenter.present(game.toCreateGameEvent());
    }

    public record Request(String playerId, String playerName) {
        private Player toPlayer() {
            return new Player(playerId, playerName);
        }
    }
}
