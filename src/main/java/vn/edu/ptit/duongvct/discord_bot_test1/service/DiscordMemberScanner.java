package vn.edu.ptit.duongvct.discord_bot_test1.service;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Member;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.DiscordUser;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.DiscordUserRepository;


@Slf4j
@Service
@AllArgsConstructor
public class DiscordMemberScanner {
    private final GatewayDiscordClient client;
    private final DiscordUserRepository discordUserRepository;

    public String scanAndSaveMembers(long guildId) {
        StringBuilder s = new StringBuilder();
        client.getGuildById(Snowflake.of(guildId))
                .flatMapMany(guild -> guild.getMembers())
                .map(member -> {
                    log.info("{} {}", member.getId().asString(), member.getUsername());
                    return fromMember(member);
                })
                .collectList()
                .doOnNext(discordUserRepository::saveAll)
                .subscribe();
        return s.toString();
    }
    private DiscordUser fromMember(Member member) {
        return DiscordUser.builder()
                .id(member.getId().asString())
                .avatarUrl(member.getAvatarUrl())
                .globalName(member.getGlobalName().orElse(""))
                .username(member.getUsername())
                .build();

    }
}
