package vn.edu.ptit.duongvct.discord_bot_test1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Topic;

import java.util.List;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {
    List<Topic> findAllByUserId(String userId);
    List<Topic> findByUserIdAndNameContainingIgnoreCase(String userId, String name);
}
