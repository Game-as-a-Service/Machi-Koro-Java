package spring.repositories.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import spring.repositories.data.GameData;
@EnableMongoRepositories
public interface GameDAO extends MongoRepository<GameData, String> {

}
