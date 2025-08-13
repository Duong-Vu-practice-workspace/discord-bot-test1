package vn.edu.ptit.duongvct.discord_bot_test1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.LearningResource;

import java.util.List;

@Repository
public interface LearningResourceRepository extends MongoRepository<LearningResource, String> {
    List<LearningResource> findByName(String name);
    List<LearningResource> findByUserIdAndNameContainingIgnoreCase(String userId, String query);
}
