package vn.edu.ptit.duongvct.discord_bot_test1.commands.learning_resource;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.resource.LearningResourceCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.service.LearningResourceService;

@Component
@AllArgsConstructor
public class DeleteResourceCommand implements SlashCommand {
    private final LearningResourceService learningResourceService;
    @Override
    public String getName() {
        return SlashCommandCommon.DELETE_RESOURCE_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Mono.fromCallable(() -> {
            String resourceId = event.getOption(LearningResourceCommandCommon.RESOURCE_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            this.learningResourceService.deleteLearningResource(resourceId);
            return "Resource deleted";
        }).flatMap(msg -> event.reply()
                .withEphemeral(false)
                .withContent(msg));
    }
}
