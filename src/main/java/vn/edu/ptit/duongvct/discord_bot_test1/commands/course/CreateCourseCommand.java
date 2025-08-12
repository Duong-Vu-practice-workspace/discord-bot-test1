package vn.edu.ptit.duongvct.discord_bot_test1.commands.course;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.object.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.course.CourseCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Course;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.CourseMark;
import vn.edu.ptit.duongvct.discord_bot_test1.service.CourseService;

@Component
@AllArgsConstructor
public class CreateCourseCommand implements SlashCommand {
    private final CourseService courseService;
    @Override
    public String getName() {
        return SlashCommandCommon.CREATE_COURSE_COMMAND;
    }

    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Mono.fromCallable(() -> {
            User user = event.getInteraction().getUser();
            String name = event.getOption(CourseCommandCommon.NAME_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("Unnamed");
            String description = event.getOption(CourseCommandCommon.DESCRIPTION_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            String topicId = event.getOption(CourseCommandCommon.TOPIC_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            Double ccWeight = event.getOption(CourseCommandCommon.CC_WEIGHT_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asDouble)
                    .orElse(0.0);
            Double ccScore = event.getOption(CourseCommandCommon.CC_SCORE_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asDouble)
                    .orElse(0.0);
            Double tbktWeight = event.getOption(CourseCommandCommon.TBKT_WEIGHT_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asDouble)
                    .orElse(0.0);
            Double tbktScore = event.getOption(CourseCommandCommon.TBKT_SCORE_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asDouble)
                    .orElse(0.0);
            Double bttlWeight = event.getOption(CourseCommandCommon.BTTL_WEIGHT_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asDouble)
                    .orElse(0.0);
            Double bttlScore = event.getOption(CourseCommandCommon.BTTL_SCORE_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asDouble)
                    .orElse(0.0);
            Double ckWeight = event.getOption(CourseCommandCommon.CK_WEIGHT_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asDouble)
                    .orElse(0.0);
            Double ckScore = event.getOption(CourseCommandCommon.CK_SCORE_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asDouble)
                    .orElse(0.0);
            Course course = Course.builder()
                    .name(name)
                    .topicId(topicId)
                    .description(description.isBlank() ? null : description)
                    .cc(CourseMark.builder()
                            .weight(ccWeight)
                            .score(ccScore)
                            .build())
                    .tbkt(
                            CourseMark.builder()
                                    .weight(tbktWeight)
                                    .score(tbktScore)
                                    .build())
                    .bttl(
                            CourseMark.builder()
                                    .weight(bttlWeight)
                                    .score(bttlScore)
                                    .build())
                    .ck(
                            CourseMark.builder()
                                    .weight(ckWeight)
                                    .score(ckScore)
                                    .build())
                    .userId(user.getId().asString())
                    .build();

            this.courseService.createCourse(course);
            return "New course created: " + course;
        }).flatMap(msg -> event.reply()
                .withEphemeral(false)
                .withContent(msg));
    }
    private double parseDoubleSafe(String value) {
        try {
            return value.isBlank() ? 0.0 : Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
