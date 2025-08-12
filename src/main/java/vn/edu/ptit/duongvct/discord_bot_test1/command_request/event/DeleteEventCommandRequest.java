package vn.edu.ptit.duongvct.discord_bot_test1.command_request.event;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.event.EventCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;

public class DeleteEventCommandRequest {
    public static ApplicationCommandRequest DELETE_EVENT_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(SlashCommandCommon.DELETE_EVENT_COMMAND)
                .description("Delete event")
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