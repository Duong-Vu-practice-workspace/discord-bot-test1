package vn.edu.ptit.duongvct.discord_bot_test1.commands.course;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.spec.EmbedCreateSpec;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.commands.SlashCommand;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.course.CourseCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Course;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.CourseRepository;
import vn.edu.ptit.duongvct.discord_bot_test1.service.TopicService;

@Component
@AllArgsConstructor
public class GetCourseCommand implements SlashCommand {
    private final CourseRepository courseRepository;
    private final TopicService topicService;
    @Override
    public String getName() {
        return SlashCommandCommon.GET_COURSE_COMMAND;
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
                throw new RuntimeException("Course not found");
            }
            EmbedCreateSpec basicEmbed = EmbedCreateSpec.builder()
                    .title("Course Details")
                    .addField("ID", course.getId() != null ? course.getId() : "N/A", false)
                    .addField("Name", course.getName() != null ? course.getName() : "N/A", false)
                    .addField("Description", course.getDescription() != null ? course.getDescription() : "N/A", false)
                    .addField("Topic ID", course.getTopicId() != null ? topicService.convertTopicNameToMeaningfulName(course.getTopicId()) : "N/A", false)
                    .build();
            EmbedCreateSpec marksEmbed = EmbedCreateSpec.builder()
                    .title("Course Marks & Scores")
                    .addField("Trọng số điểm chuyên cần", course.getCc() != null ? String.valueOf(course.getCc().getWeight()) : "N/A", true)
                    .addField("Điểm chuyên cần ", String.valueOf(course.getCc().getScore()), true)
                    .addField("Trọng số điểm trung bình kiểm tra ", String.valueOf(course.getTbkt().getWeight()), false)
                    .addField("Điểm trung bình kiểm tra", String.valueOf(course.getTbkt().getScore()), true)
                    .addField("Trọng số điểm bài tập thảo luận ", String.valueOf(course.getBttl().getWeight()), false)
                    .addField("Điểm bài tập thảo luận ", String.valueOf(course.getBttl().getScore()), true)
                    .addField("Trọng số điểm cuối kỳ  ", String.valueOf(course.getCk().getWeight()), false)
                    .addField("Điểm cuối kỳ ", String.valueOf(course.getCk().getScore()), true)
                    .addField("Điểm tổng kết(chữ)", course.getFinalScoreGrade() != null ? course.getFinalScoreGrade() : "N/A", false)
                    .addField("Điểm tổng kết hệ 4 ", String.valueOf(course.getFinalScore()), false)
                    .build();
            return new EmbedCreateSpec[] { basicEmbed, marksEmbed };
        }).flatMap(embeds -> {
            if (embeds == null) {
                throw new IllegalArgumentException("Course not found.");
            }
            return event.reply().withEphemeral(true).withEmbeds(embeds[0])
                    .then(event.createFollowup().withEphemeral(true).withEmbeds(embeds[1]))
                    .then();
        });
    }
}