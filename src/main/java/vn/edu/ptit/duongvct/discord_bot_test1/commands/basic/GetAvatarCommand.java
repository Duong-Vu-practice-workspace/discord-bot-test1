package vn.edu.ptit.duongvct.discord_bot_test1.commands.basic;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.entity.User;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.basic.GetAvatarCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;

import java.time.Instant;

@Component
public class GetAvatarCommand implements SlashCommand {
    @Override
    public String getName() {
        return SlashCommandCommon.GET_AVATAR_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        User user = event.getOption(GetAvatarCommandCommon.USER_PARAMETER)
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(u -> u.asUser().block())
                .orElse(event.getInteraction().getUser());
        EmbedCreateSpec embed = EmbedCreateSpec.builder().build()
                .withImage(user.getAvatarUrl())
                .withThumbnail(user.getAvatarUrl())
                .withDescription("Description")
                .withColor(Color.BLUE)
                .withTimestamp(Instant.now())
                .withTitle("Avatar")
                .withUrl(user.getAvatarUrl());
//        return event.reply()
//                .withEphemeral(true)
//                .withContent(user.getAvatarUrl());
        return event.reply()
//                .withEphemeral(true)
                .withEmbeds(embed);
    }
}
