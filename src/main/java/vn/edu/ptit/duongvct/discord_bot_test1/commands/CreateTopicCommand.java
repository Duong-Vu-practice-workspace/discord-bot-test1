package vn.edu.ptit.duongvct.discord_bot_test1.commands;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.common.CreateTopicCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Topic;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.TopicRepository;

import static vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon.CREATE_TOPIC_COMMAND;
@Component
@AllArgsConstructor
public class CreateTopicCommand implements SlashCommand{
    private final TopicRepository topicRepository;
    @Override
    public String getName() {
        return CREATE_TOPIC_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        User user = event.getInteraction().getUser();
        String name = event.getOption(CreateTopicCommandCommon.NAME_PARAMETER)
                .flatMap(opt -> opt.getValue())
                .map(val -> val.asString())
                .orElse("Unnamed");

        String description = event.getOption(CreateTopicCommandCommon.DESCRIPTION_PARAMETER)
                .flatMap(opt -> opt.getValue())
                .map(val -> val.asString())
                .orElse("");

        String parentId = event.getOption(CreateTopicCommandCommon.PARENT_ID_PARAMETER)
                .flatMap(opt -> opt.getValue())
                .map(val -> val.asString())
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
