package vn.edu.ptit.duongvct.discord_bot_test1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class DiscordUser {
    @Id
    private String id;
    private String username;
    private String avatarUrl;
    private String globalName;
}
