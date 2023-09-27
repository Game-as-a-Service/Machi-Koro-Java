package spring.repositories.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import spring.repositories.data.PlayerData;

@EnableMongoRepositories
public interface PlayerDAO extends MongoRepository<PlayerData, String> {
}
