package vn.edu.ptit.duongvct.discord_bot_test1.command_request.learning_resource;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.resource.LearningResourceCommandCommon;

public class DeleteResourceCommandRequest {
    public static ApplicationCommandRequest DELETE_RESOURCE_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(SlashCommandCommon.DELETE_RESOURCE_COMMAND)
                .description("Delete resource")
                .addOption(RESOURCE_ID_PARAMETER())
                .build();
    }
    private static ApplicationCommandOptionData RESOURCE_ID_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(LearningResourceCommandCommon.RESOURCE_ID_PARAMETER)
                .description(LearningResourceCommandCommon.RESOURCE_ID_PARAMETER)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .autocomplete(true)
                .build();
    }
}
