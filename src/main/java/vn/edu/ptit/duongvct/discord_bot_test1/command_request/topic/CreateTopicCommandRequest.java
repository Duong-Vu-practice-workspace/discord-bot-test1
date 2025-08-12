package vn.edu.ptit.duongvct.discord_bot_test1.command_request.topic;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import lombok.extern.slf4j.Slf4j;
import vn.edu.ptit.duongvct.discord_bot_test1.common.topic.TopicCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Topic;

import java.util.Map;

import static vn.edu.ptit.duongvct.discord_bot_test1.common.topic.TopicCommandCommon.CREATE_TOPIC_COMMAND_DESCRIPTION;
import static vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon.CREATE_TOPIC_COMMAND;

@Slf4j
public final class CreateTopicCommandRequest {
    public static ApplicationCommandRequest CREATE_TOPIC_COMMAND_REQUEST () {
        return ApplicationCommandRequest.builder()
                .name(CREATE_TOPIC_COMMAND)
                .description(CREATE_TOPIC_COMMAND_DESCRIPTION)
                .addOption(
                        NAME_PARAMETER()
                )
                .addOption(
                        PARENT_ID_PARAMETER()
                )
                .addOption(
                        DESCRIPTION_PARAMETER()
                )
                .build();
    }
    private static ApplicationCommandOptionData NAME_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(TopicCommandCommon.NAME_PARAMETER)
                .description(TopicCommandCommon.TopicParameterCommon.NAME_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true).build();
    }
    private static ApplicationCommandOptionData PARENT_ID_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(TopicCommandCommon.PARENT_ID_PARAMETER)
                .description(TopicCommandCommon.TopicParameterCommon.PARENT_ID_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .autocomplete(true)
                .required(true).build();
    }
    private static ApplicationCommandOptionData DESCRIPTION_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(TopicCommandCommon.DESCRIPTION_PARAMETER)
                .description(TopicCommandCommon.TopicParameterCommon.DESCRIPTION_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(false).build();
    }

    public static String buildTopicPath(Topic topic, Map<String, Topic> topicMap) {
        if (topic.getParentId() == null || topic.getParentId().equals("root")) {
            return topic.getName();
        }
        Topic parent = topicMap.get(topic.getParentId());
        if (parent == null) {
            return topic.getName();
        }
        return buildTopicPath(parent, topicMap) + "/" + topic.getName();
    }
}
