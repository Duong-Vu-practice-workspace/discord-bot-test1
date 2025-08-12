package vn.edu.ptit.duongvct.discord_bot_test1.commands.topic;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.topic.CreateTopicCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Topic;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.TopicRepository;

import static vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon.CREATE_TOPIC_COMMAND;
@Component
@AllArgsConstructor
public class CreateTopicCommand implements SlashCommand {
    private final TopicRepository topicRepository;
    @Override
    public String getName() {
        return CREATE_TOPIC_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        User user = event.getInteraction().getUser();
        String name = event.getOption(CreateTopicCommandCommon.NAME_PARAMETER)
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElse("Unnamed");

        String description = event.getOption(CreateTopicCommandCommon.DESCRIPTION_PARAMETER)
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElse("");

        String parentId = event.getOption(CreateTopicCommandCommon.PARENT_ID_PARAMETER)
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElse("root");

        Topic topic = new Topic();
        topic.setName(name);
        topic.setDescription(description);
        topic.setParentId(parentId);
        topic.setUserId(user.getId().asString());
        topicRepository.save(topic);

        return event
                .reply()
                .withEphemeral(true)
                .withContent("Topic created: " + name);
    }
}
