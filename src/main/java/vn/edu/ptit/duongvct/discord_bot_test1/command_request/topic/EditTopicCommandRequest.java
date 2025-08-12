package vn.edu.ptit.duongvct.discord_bot_test1.command_request.topic;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.topic.TopicCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;

public class EditTopicCommandRequest {
    public static ApplicationCommandRequest EDIT_TOPIC_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(SlashCommandCommon.EDIT_TOPIC_COMMAND)
                .description("Edit topic")
                .addOption(ApplicationCommandOptionData.builder()
                        .name(TopicCommandCommon.TOPIC_ID_PARAMETER)
                        .description("Topic ID")
                        .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                        .required(true)
                        .autocomplete(true)
                        .build())
                .addOption(ApplicationCommandOptionData.builder()
                        .name(TopicCommandCommon.PARENT_ID_PARAMETER)
                        .description("New parent topic ID")
                        .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                        .required(false)
                        .autocomplete(true)
                        .build())
                .addOption(ApplicationCommandOptionData.builder()
                        .name(TopicCommandCommon.NAME_PARAMETER)
                        .description("New name")
                        .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                        .required(false)
                        .build())
                .addOption(ApplicationCommandOptionData.builder()
                        .name(TopicCommandCommon.DESCRIPTION_PARAMETER)
                        .description("New description")
                        .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                        .required(false)
                        .build())
                .build();
    }
}