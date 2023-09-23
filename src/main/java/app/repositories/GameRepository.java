package app.repositories;

import domain.Game;

import java.util.List;
import java.util.Optional;

public interface GameRepository {
    Game save(Game game);

    Optional<Game> findById(String id);

    List<Game> findAll();
}
