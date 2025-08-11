package vn.edu.ptit.duongvct.discord_bot_test1.command_request;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;
import vn.edu.ptit.duongvct.discord_bot_test1.common.GetAvatarCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;

public final class GetAvatarCommandRequest {
    public static ApplicationCommandRequest GET_AVATAR_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(SlashCommandCommon.GET_AVATAR_COMMAND)
                .description(GetAvatarCommandCommon.GET_AVATAR_COMMAND_DESCRIPTION)
                .addOption(ApplicationCommandOptionData.builder()
                        .name(GetAvatarCommandCommon.USER_PARAMETER)
                        .description(GetAvatarCommandCommon.GetAvatarParameter.USER_PARAMETER_DESCRIPTION)
                        .type(DiscordParameterType.getType(DiscordParameterType.USER))
                        .required(true)
                        .build())
                .build();
    }
}
