package vn.edu.ptit.duongvct.discord_bot_test1.commands.course;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.course.CourseCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Course;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.CourseMark;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.CourseRepository;
import vn.edu.ptit.duongvct.discord_bot_test1.util.CommonUtil;

@Component
public class EditCourseCommand implements SlashCommand {
    private final CourseRepository courseRepository;

    public EditCourseCommand(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public String getName() {
        return SlashCommandCommon.EDIT_COURSE_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Mono.fromCallable(() -> {
            String courseId = event.getOption(CourseCommandCommon.COURSE_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            Course course = courseRepository.findById(courseId).orElse(null);
            if (course == null) {
                return "Course not found.";
            }
            String newName = event.getOption(CourseCommandCommon.NAME_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse(null);
            String newDescription = event.getOption(CourseCommandCommon.DESCRIPTION_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse(null);
            String newTopicId = event.getOption(CourseCommandCommon.TOPIC_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse(null);
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
            if (CommonUtil.validateString(newName)) {
                course.setName(newName);
            }
            if (CommonUtil.validateString(newDescription)) {
                course.setDescription(newDescription);
            }
            if (CommonUtil.validateString(newTopicId)) {
                course.setTopicId(newTopicId);
            }
            course.setCc(CommonUtil.validateAndSetNewCourseMark(ccWeight, ccScore, course.getCc()));
            course.setTbkt(CommonUtil.validateAndSetNewCourseMark(tbktWeight, tbktScore, course.getTbkt()));
            course.setBttl(CommonUtil.validateAndSetNewCourseMark(bttlWeight, bttlScore, course.getBttl()));
            course.setCk(CommonUtil.validateAndSetNewCourseMark(ckWeight, ckScore, course.getCk()));
            course.setFinalScore(CommonUtil.calculateFinalScore(course));
            course.setFinalScoreGrade(CommonUtil.calculateFinalScoreGrade(course.getFinalScore()));
            courseRepository.save(course);
            return "Course updated.";
        }).flatMap(reply -> event.reply().withEphemeral(true).withContent(reply));
    }
}