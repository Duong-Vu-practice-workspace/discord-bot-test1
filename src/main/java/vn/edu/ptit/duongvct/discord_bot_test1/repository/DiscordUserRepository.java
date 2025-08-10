package vn.edu.ptit.duongvct.discord_bot_test1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.DiscordUser;
@Repository
public interface DiscordUserRepository extends MongoRepository<DiscordUser, String> {
}
