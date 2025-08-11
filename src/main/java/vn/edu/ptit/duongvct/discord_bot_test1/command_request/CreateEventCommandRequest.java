package vn.edu.ptit.duongvct.discord_bot_test1.command_request;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import lombok.extern.slf4j.Slf4j;
import vn.edu.ptit.duongvct.discord_bot_test1.common.CreateEventCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;

@Slf4j
public final class CreateEventCommandRequest {
    public static ApplicationCommandRequest CREATE_EVENT_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(SlashCommandCommon.CREATE_EVENT_COMMAND)
                .description(CreateEventCommandCommon.CREATE_EVENT_COMMAND_DESCRIPTION)
                .addOption(NAME_PARAMETER())
                .addOption(START_TIME_PARAMETER())
                .addOption(END_TIME_PARAMETER())
                .addOption(TOPIC_ID_PARAMETER())
                // non-required params
                .addOption(LOCATION_PARAMETER())
                .addOption(DESCRIPTION_PARAMETER())

                .build();
    }
    private static ApplicationCommandOptionData NAME_PARAMETER(){
        return ApplicationCommandOptionData.builder()
                .name(CreateEventCommandCommon.NAME_PARAMETER)
                .description(CreateEventCommandCommon.CreateEventParameterCommon.NAME_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .build();
    }
    private static ApplicationCommandOptionData DESCRIPTION_PARAMETER(){
        return ApplicationCommandOptionData.builder()
                .name(CreateEventCommandCommon.DESCRIPTION_PARAMETER)
                .description(CreateEventCommandCommon.CreateEventParameterCommon.DESCRIPTION_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData START_TIME_PARAMETER(){
        return ApplicationCommandOptionData.builder()
                .name(CreateEventCommandCommon.START_TIME_PARAMETER)
                .description(CreateEventCommandCommon.CreateEventParameterCommon.START_TIME_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .build();
    }
    private static ApplicationCommandOptionData END_TIME_PARAMETER(){
        return ApplicationCommandOptionData.builder()
                .name(CreateEventCommandCommon.END_TIME_PARAMETER)
                .description(CreateEventCommandCommon.CreateEventParameterCommon.END_TIME_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .build();
    }
    private static ApplicationCommandOptionData LOCATION_PARAMETER(){
        return ApplicationCommandOptionData.builder()
                .name(CreateEventCommandCommon.LOCATION_PARAMETER)
                .description(CreateEventCommandCommon.CreateEventParameterCommon.LOCATION_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData TOPIC_ID_PARAMETER(){
        return ApplicationCommandOptionData.builder()
                .name(CreateEventCommandCommon.TOPIC_ID_PARAMETER)
                .description(CreateEventCommandCommon.CreateEventParameterCommon.TOPIC_ID_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .autocomplete(true)
                .required(true)
                .build();
    }

}
