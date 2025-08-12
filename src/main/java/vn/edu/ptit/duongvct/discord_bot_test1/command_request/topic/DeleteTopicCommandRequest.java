package vn.edu.ptit.duongvct.discord_bot_test1.command_request.topic;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.topic.TopicCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;

public class DeleteTopicCommandRequest {
    public static ApplicationCommandRequest DELETE_TOPIC_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(SlashCommandCommon.DELETE_TOPIC_COMMAND)
                .description("Delete topic")
                .addOption(ApplicationCommandOptionData.builder()
                        .name(TopicCommandCommon.TOPIC_ID_PARAMETER)
                        .description("Topic ID")
                        .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                        .required(true)
                        .autocomplete(true)
                        .build())
                .build();
    }
}