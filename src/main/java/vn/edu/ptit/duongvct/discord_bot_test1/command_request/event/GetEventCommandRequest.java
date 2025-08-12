package vn.edu.ptit.duongvct.discord_bot_test1.command_request.event;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.event.EventCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;

public class GetEventCommandRequest {
    public static ApplicationCommandRequest GET_EVENT_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(SlashCommandCommon.GET_EVENT_COMMAND)
                .description("Get event details")
                .addOption(ApplicationCommandOptionData.builder()
                        .name(EventCommandCommon.EVENT_ID_PARAMETER)
                        .description("Event ID")
                        .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                        .required(true)
                        .autocomplete(true)
                        .build())
                .build();
    }
}