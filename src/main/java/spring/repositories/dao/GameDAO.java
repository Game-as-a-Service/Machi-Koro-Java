package spring.repositories.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import spring.repositories.data.GameData;

public interface GameDAO extends MongoRepository<GameData, String> {

}
