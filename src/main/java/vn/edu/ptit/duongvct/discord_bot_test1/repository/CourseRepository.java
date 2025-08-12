package vn.edu.ptit.duongvct.discord_bot_test1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Course;

import java.util.List;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    List<Course> findByName(String name);
    List<Course> findByUserIdAndNameContainingIgnoreCase(String userId, String query);
}
