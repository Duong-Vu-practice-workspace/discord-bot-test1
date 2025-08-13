package vn.edu.ptit.duongvct.discord_bot_test1.autocomplete.course;

import discord4j.core.event.domain.interaction.ChatInputAutoCompleteEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.discordjson.json.ApplicationCommandOptionChoiceData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import vn.edu.ptit.duongvct.discord_bot_test1.autocomplete.AutoCompleteHandler;
import vn.edu.ptit.duongvct.discord_bot_test1.common.SlashCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.common.course.CourseCommandCommon;
import vn.edu.ptit.duongvct.discord_bot_test1.service.CourseService;

import java.util.List;

@Component
@AllArgsConstructor
public class CourseIdAutoCompleteHandler implements AutoCompleteHandler {
    private final CourseService courseService;
    @Override
    public boolean supports(ChatInputAutoCompleteEvent event) {
        return ((SlashCommandCommon.GET_COURSE_COMMAND.equals(event.getCommandName())) || (SlashCommandCommon.EDIT_COURSE_COMMAND.equals(event.getCommandName())) || (SlashCommandCommon.DELETE_COURSE_COMMAND.equals(event.getCommandName())))
                && CourseCommandCommon.COURSE_ID_PARAMETER.equals(event.getFocusedOption().getName());
    }

    @Override
    public Mono<Void> handle(ChatInputAutoCompleteEvent event) {
        String query = event.getFocusedOption().getValue().map(ApplicationCommandInteractionOptionValue::asString).orElse("");
        String userId = event.getInteraction().getUser().getId().asString();

        List<ApplicationCommandOptionChoiceData> choices = courseService.getAllCourseChoices(query, userId)
                .stream()
                .limit(25)
                .toList();

        return event.respondWithSuggestions(choices);
    }
}
