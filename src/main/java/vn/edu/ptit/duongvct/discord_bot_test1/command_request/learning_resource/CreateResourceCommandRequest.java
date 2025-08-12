package vn.edu.ptit.duongvct.discord_bot_test1.command_request.learning_resource;

import discord4j.discordjson.json.ApplicationCommandOptionChoiceData;
import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.resource.CreateResourceCommandCommon;

public class CreateResourceCommandRequest {
    public static ApplicationCommandRequest CREATE_RESOURCE_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(SlashCommandCommon.CREATE_RESOURCE_COMMAND)
                .description(CreateResourceCommandCommon.CREATE_RESOURCE_COMMAND_DESCRIPTION)
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
                .name(CreateResourceCommandCommon.NAME_PARAMETER)
                .description(CreateResourceCommandCommon.CreateResourceParameterCommon.NAME_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .build();
    }
    private static ApplicationCommandOptionData DESCRIPTION_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateResourceCommandCommon.DESCRIPTION_PARAMETER)
                .description(CreateResourceCommandCommon.CreateResourceParameterCommon.DESCRIPTION_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData LINK_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateResourceCommandCommon.LINK_PARAMETER)
                .description(CreateResourceCommandCommon.CreateResourceParameterCommon.LINK_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .build();
    }
    private static ApplicationCommandOptionData COURSE_ID_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateResourceCommandCommon.COURSE_ID_PARAMETER)
                .description(CreateResourceCommandCommon.CreateResourceParameterCommon.COURSE_ID_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .autocomplete(true)
                .build();
    }
    private static ApplicationCommandOptionData TYPE_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateResourceCommandCommon.TYPE_PARAMETER)
                .description(CreateResourceCommandCommon.CreateResourceParameterCommon.TYPE_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .addChoice(ApplicationCommandOptionChoiceData.builder().name("LEARNING").value("LEARNING").build())
                .addChoice(ApplicationCommandOptionChoiceData.builder().name("RECORD").value("RECORD").build())
                .build();
    }
    private static ApplicationCommandOptionData TOPIC_ID_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateResourceCommandCommon.TOPIC_ID_PARAMETER)
                .description(CreateResourceCommandCommon.CreateResourceParameterCommon.TOPIC_ID_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .autocomplete(true)
                .build();
    }
}