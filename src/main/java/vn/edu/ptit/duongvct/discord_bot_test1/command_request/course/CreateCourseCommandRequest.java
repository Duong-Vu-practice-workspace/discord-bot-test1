package vn.edu.ptit.duongvct.discord_bot_test1.command_request.course;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import lombok.extern.slf4j.Slf4j;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.course.CourseCommandCommon;

@Slf4j
public class CreateCourseCommandRequest {
    public static ApplicationCommandRequest CREATE_COURSE_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(SlashCommandCommon.CREATE_COURSE_COMMAND)
                .description(CourseCommandCommon.CREATE_COURSE_COMMAND_DESCRIPTION)
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
                .name(CourseCommandCommon.NAME_PARAMETER)
                .description(CourseCommandCommon.CourseParameterCommon.NAME_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .build();
    }
    private static ApplicationCommandOptionData TOPIC_ID_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CourseCommandCommon.TOPIC_ID_PARAMETER)
                .description(CourseCommandCommon.CourseParameterCommon.TOPIC_ID_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(true)
                .autocomplete(true)
                .build();
    }
    private static ApplicationCommandOptionData DESCRIPTION_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CourseCommandCommon.DESCRIPTION_PARAMETER)
                .description(CourseCommandCommon.CourseParameterCommon.DESCRIPTION_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                .required(false)
                .build();
    }private static ApplicationCommandOptionData CC_WEIGHT_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CourseCommandCommon.CC_WEIGHT_PARAMETER)
                .description(CourseCommandCommon.CourseParameterCommon.CC_WEIGHT_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData CC_SCORE_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CourseCommandCommon.CC_SCORE_PARAMETER)
                .description(CourseCommandCommon.CourseParameterCommon.CC_SCORE_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData TBKT_WEIGHT_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CourseCommandCommon.TBKT_WEIGHT_PARAMETER)
                .description(CourseCommandCommon.CourseParameterCommon.TBKT_WEIGHT_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData TBKT_SCORE_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CourseCommandCommon.TBKT_SCORE_PARAMETER)
                .description(CourseCommandCommon.CourseParameterCommon.TBKT_SCORE_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData BTTL_WEIGHT_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CourseCommandCommon.BTTL_WEIGHT_PARAMETER)
                .description(CourseCommandCommon.CourseParameterCommon.BTTL_WEIGHT_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData BTTL_SCORE_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CourseCommandCommon.BTTL_SCORE_PARAMETER)
                .description(CourseCommandCommon.CourseParameterCommon.BTTL_SCORE_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData CK_WEIGHT_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CourseCommandCommon.CK_WEIGHT_PARAMETER)
                .description(CourseCommandCommon.CourseParameterCommon.CK_WEIGHT_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
    private static ApplicationCommandOptionData CK_SCORE_PARAMETER() {
        return ApplicationCommandOptionData.builder()
                .name(CourseCommandCommon.CK_SCORE_PARAMETER)
                .description(CourseCommandCommon.CourseParameterCommon.CK_SCORE_PARAMETER_DESCRIPTION)
                .type(DiscordParameterType.getType(DiscordParameterType.NUMBER))
                .required(false)
                .build();
    }
}
