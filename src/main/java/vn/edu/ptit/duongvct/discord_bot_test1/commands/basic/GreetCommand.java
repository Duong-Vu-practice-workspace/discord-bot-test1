package vn.edu.ptit.duongvct.discord_bot_test1.commands.basic;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.entity.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.basic.GreetCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;

@Component
public class GreetCommand implements SlashCommand {
    @Override
    public String getName() {
        return SlashCommandCommon.GREET_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        User u = event.getOption(GreetCommandCommon.USER_PARAMETER)
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(user -> user.asUser().block())
                .get();
        return  event.reply()
                .withEphemeral(true)
                .withContent("Hello, " + u.getGlobalName().orElseThrow());
    }
}