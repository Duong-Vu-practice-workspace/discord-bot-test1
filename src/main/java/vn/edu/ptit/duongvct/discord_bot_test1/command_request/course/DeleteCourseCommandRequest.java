package vn.edu.ptit.duongvct.discord_bot_test1.command_request.course;

import discord4j.discordjson.json.ApplicationCommandOptionData;
import discord4j.discordjson.json.ApplicationCommandRequest;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.course.CourseCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.DiscordParameterType;

public class DeleteCourseCommandRequest {
    public static ApplicationCommandRequest DELETE_COURSE_COMMAND_REQUEST() {
        return ApplicationCommandRequest.builder()
                .name(SlashCommandCommon.DELETE_COURSE_COMMAND)
                .description("Delete course")
                .addOption(ApplicationCommandOptionData.builder()
                        .name(CourseCommandCommon.COURSE_ID_PARAMETER)
                        .description("Course ID")
                        .type(DiscordParameterType.getType(DiscordParameterType.STRING))
                        .required(true)
                        .autocomplete(true)
                        .build())
                .build();
    }
}