package vn.edu.ptit.duongvct.discord_bot_test1.commands.course;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.course.CourseCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.CourseRepository;

@Component
public class DeleteCourseCommand implements SlashCommand {
    private final CourseRepository courseRepository;

    public DeleteCourseCommand(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public String getName() {
        return SlashCommandCommon.DELETE_COURSE_COMMAND;
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Mono.fromCallable(() -> {
            String courseId = event.getOption(CourseCommandCommon.COURSE_ID_PARAMETER)
                    .flatMap(ApplicationCommandInteractionOption::getValue)
                    .map(ApplicationCommandInteractionOptionValue::asString)
                    .orElse("");
            courseRepository.deleteById(courseId);
            return "Course deleted.";
        }).flatMap(reply -> event.reply().withEphemeral(true).withContent(reply));
    }
}