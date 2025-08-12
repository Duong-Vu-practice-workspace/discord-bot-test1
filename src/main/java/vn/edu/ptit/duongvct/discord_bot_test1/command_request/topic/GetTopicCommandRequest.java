package vn.edu.ptit.duongvct.discord_bot_test1.command_request.topic;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.topic.TopicCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;

public class GetTopicCommandRequest {
    public static ApplicationCommandRequest GET_TOPIC_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(SlashCommandCommon.GET_TOPIC_COMMAND)
                .description("Get topic details")
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