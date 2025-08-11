package vn.edu.ptit.duongvct.discord_bot_test1.command_request;

import discord4j.discordjson.json.ApplicationCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.common.PingCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;

import static vn.edu.ptit.duongvct.discord_bot_test1.common.PingCommandCommon.PING_COMMAND_DESCRIPTION;
import static vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon.PING_COMMAND;

public final class PingCommandRequest {
    public static ApplicationCommandRequest PING_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(PING_COMMAND)
                .description(PING_COMMAND_DESCRIPTION)
                .build();
    }
}
