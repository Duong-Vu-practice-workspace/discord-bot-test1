package vn.edu.ptit.duongvct.discord_bot_test1.command_request;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;
import vn.edu.ptit.duongvct.discord_bot_test1.common.GreetCommandCommon;

import static vn.edu.ptit.duongvct.discord_bot_test1.common.GreetCommandCommon.GREET_COMMAND_DESCRIPTION;
import static vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon.GREET_COMMAND;

public final class GreetCommandRequest {
    public static ApplicationCommandRequest GREET_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(GREET_COMMAND)
                .description(GREET_COMMAND_DESCRIPTION)
                .addOption(ApplicationCommandOptionData.builder()
                        .name(GreetCommandCommon.USER_PARAMETER)
                        .description(GreetCommandCommon.GreetParameterCommon.USER_PARAMETER_DESCRIPTION)
                        .type(DiscordParameterType.getType(DiscordParameterType.USER))
                        .required(true).build()
                )
                .build();
    }
}
