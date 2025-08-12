package vn.edu.ptit.duongvct.discord_bot_test1.command_request.event;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import lombok.extern.slf4j.Slf4j;
import vn.edu.ptit.duongvct.discord_bot_test1.common.event.EventCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;

@Slf4j
public final class CreateEventCommandRequest {
    public static ApplicationCommandRequest CREATE_EVENT_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(SlashCommandCommon.CREATE_EVENT_COMMAND)
                .description(EventCommandCommon.CREATE_EVENT_COMMAND_DESCRIPTION)
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
                .name(EventCommandCommon.NAME_PARAMETER)
                .description(EventCommandCommon.EventParameterCommon.NAME_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .build();
    }
    private static ApplicationCommandOptionData DESCRIPTION_PARAMETER(){
        return ApplicationCommandOptionData.builder()
                .name(EventCommandCommon.DESCRIPTION_PARAMETER)
                .description(EventCommandCommon.EventParameterCommon.DESCRIPTION_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData START_TIME_PARAMETER(){
        return ApplicationCommandOptionData.builder()
                .name(EventCommandCommon.START_TIME_PARAMETER)
                .description(EventCommandCommon.EventParameterCommon.START_TIME_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .build();
    }
    private static ApplicationCommandOptionData END_TIME_PARAMETER(){
        return ApplicationCommandOptionData.builder()
                .name(EventCommandCommon.END_TIME_PARAMETER)
                .description(EventCommandCommon.EventParameterCommon.END_TIME_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .build();
    }
    private static ApplicationCommandOptionData LOCATION_PARAMETER(){
        return ApplicationCommandOptionData.builder()
                .name(EventCommandCommon.LOCATION_PARAMETER)
                .description(EventCommandCommon.EventParameterCommon.LOCATION_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData TOPIC_ID_PARAMETER(){
        return ApplicationCommandOptionData.builder()
                .name(EventCommandCommon.TOPIC_ID_PARAMETER)
                .description(EventCommandCommon.EventParameterCommon.TOPIC_ID_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .autocomplete(true)
                .required(true)
                .build();
    }

}
