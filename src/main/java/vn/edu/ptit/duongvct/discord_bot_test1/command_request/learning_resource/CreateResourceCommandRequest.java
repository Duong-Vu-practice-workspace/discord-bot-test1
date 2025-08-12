package vn.edu.ptit.duongvct.discord_bot_test1.command_request.learning_resource;

import discord4j.discordjson.json.ApplicationCommandOptionChoiceData;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.resource.LearningResourceCommandCommon;

public class CreateResourceCommandRequest {
    public static ApplicationCommandRequest CREATE_RESOURCE_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(SlashCommandCommon.CREATE_RESOURCE_COMMAND)
                .description(LearningResourceCommandCommon.CREATE_RESOURCE_COMMAND_DESCRIPTION)
                .addOption(NAME_PARAMETER())
                .addOption(LINK_PARAMETER())
                .addOption(COURSE_ID_PARAMETER())
                .addOption(TYPE_PARAMETER())
                .addOption(TOPIC_ID_PARAMETER())
                .addOption(DESCRIPTION_PARAMETER())
                .build();
    }

    private static ApplicationCommandOptionData NAME_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(LearningResourceCommandCommon.NAME_PARAMETER)
                .description(LearningResourceCommandCommon.LearningResourceParameterCommon.NAME_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .build();
    }
    private static ApplicationCommandOptionData DESCRIPTION_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(LearningResourceCommandCommon.DESCRIPTION_PARAMETER)
                .description(LearningResourceCommandCommon.LearningResourceParameterCommon.DESCRIPTION_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData LINK_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(LearningResourceCommandCommon.LINK_PARAMETER)
                .description(LearningResourceCommandCommon.LearningResourceParameterCommon.LINK_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .build();
    }
    private static ApplicationCommandOptionData COURSE_ID_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(LearningResourceCommandCommon.COURSE_ID_PARAMETER)
                .description(LearningResourceCommandCommon.LearningResourceParameterCommon.COURSE_ID_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .autocomplete(true)
                .build();
    }
    private static ApplicationCommandOptionData TYPE_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(LearningResourceCommandCommon.TYPE_PARAMETER)
                .description(LearningResourceCommandCommon.LearningResourceParameterCommon.TYPE_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .addChoice(ApplicationCommandOptionChoiceData.builder().name("LEARNING").value("LEARNING").build())
                .addChoice(ApplicationCommandOptionChoiceData.builder().name("RECORD").value("RECORD").build())
                .build();
    }
    private static ApplicationCommandOptionData TOPIC_ID_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(LearningResourceCommandCommon.TOPIC_ID_PARAMETER)
                .description(LearningResourceCommandCommon.LearningResourceParameterCommon.TOPIC_ID_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .autocomplete(true)
                .build();
    }
}