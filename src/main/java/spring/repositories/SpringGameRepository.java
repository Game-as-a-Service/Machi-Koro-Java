package spring.repositories;

import app.repositories.GameRepository;
import domain.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.repositories.dao.GameDAO;
import spring.repositories.data.GameData;

import java.util.List;
import java.util.Optional;

import static domain.StreamUtils.mapToList;

@Repository
@RequiredArgsConstructor
public class SpringGameRepository implements GameRepository {
    private final GameDAO dao;

    @Override
    public Game save(Game game) {
        var data = GameData.toData(game);
        var savedData = dao.save(data);
        return savedData.toDomain();
    }

    @Override
    public Optional<Game> findById(String id) {
        return dao.findById(id).map(GameData::toDomain);
    }

    @Override
    public List<Game> findAll() {
        return mapToList(dao.findAll(), GameData::toDomain);
    }
}
