package vn.edu.ptit.duongvct.discord_bot_test1.command_request.course;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import lombok.extern.slf4j.Slf4j;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.course.CreateCourseCommandCommon;

@Slf4j
public class CreateCourseCommandRequest {
    public static ApplicationCommandRequest CREATE_COURSE_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(SlashCommandCommon.CREATE_COURSE_COMMAND)
                .description(CreateCourseCommandCommon.CREATE_COURSE_COMMAND_DESCRIPTION)
                .addOption(NAME_PARAMETER())
                .addOption(TOPIC_ID_PARAMETER())
                //non-required
                .addOption(DESCRIPTION_PARAMETER())
                .addOption(CC_WEIGHT_PARAMETER())
                .addOption(CC_SCORE_PARAMETER())
                .addOption(TBKT_WEIGHT_PARAMETER())
                .addOption(TBKT_SCORE_PARAMETER())
                .addOption(BTTL_WEIGHT_PARAMETER())
                .addOption(BTTL_SCORE_PARAMETER())
                .addOption(CK_WEIGHT_PARAMETER())
                .addOption(CK_SCORE_PARAMETER())
                .build();
    }
    private static ApplicationCommandOptionData NAME_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateCourseCommandCommon.NAME_PARAMETER)
                .description(CreateCourseCommandCommon.CreateCourseParameterCommon.NAME_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .build();
    }
    private static ApplicationCommandOptionData TOPIC_ID_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateCourseCommandCommon.TOPIC_ID_PARAMETER)
                .description(CreateCourseCommandCommon.CreateCourseParameterCommon.TOPIC_ID_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .autocomplete(true)
                .build();
    }
    private static ApplicationCommandOptionData DESCRIPTION_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateCourseCommandCommon.DESCRIPTION_PARAMETER)
                .description(CreateCourseCommandCommon.CreateCourseParameterCommon.DESCRIPTION_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(false)
                .build();
    }private static ApplicationCommandOptionData CC_WEIGHT_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateCourseCommandCommon.CC_WEIGHT_PARAMETER)
                .description(CreateCourseCommandCommon.CreateCourseParameterCommon.CC_WEIGHT_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData CC_SCORE_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateCourseCommandCommon.CC_SCORE_PARAMETER)
                .description(CreateCourseCommandCommon.CreateCourseParameterCommon.CC_SCORE_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData TBKT_WEIGHT_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateCourseCommandCommon.TBKT_WEIGHT_PARAMETER)
                .description(CreateCourseCommandCommon.CreateCourseParameterCommon.TBKT_WEIGHT_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData TBKT_SCORE_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateCourseCommandCommon.TBKT_SCORE_PARAMETER)
                .description(CreateCourseCommandCommon.CreateCourseParameterCommon.TBKT_SCORE_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData BTTL_WEIGHT_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateCourseCommandCommon.BTTL_WEIGHT_PARAMETER)
                .description(CreateCourseCommandCommon.CreateCourseParameterCommon.BTTL_WEIGHT_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData BTTL_SCORE_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateCourseCommandCommon.BTTL_SCORE_PARAMETER)
                .description(CreateCourseCommandCommon.CreateCourseParameterCommon.BTTL_SCORE_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData CK_WEIGHT_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateCourseCommandCommon.CK_WEIGHT_PARAMETER)
                .description(CreateCourseCommandCommon.CreateCourseParameterCommon.CK_WEIGHT_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData CK_SCORE_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CreateCourseCommandCommon.CK_SCORE_PARAMETER)
                .description(CreateCourseCommandCommon.CreateCourseParameterCommon.CK_SCORE_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
}
