package vn.edu.ptit.duongvct.discord_bot_test1.service;

import discord4j.core.object.command.ApplicationCommandOptionChoice;
import discord4j.discordjson.json.ApplicationCommandOptionChoiceData;
import discord4j.discordjson.json.ImmutableApplicationCommandOptionChoiceData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Topic;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.TopicRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static vn.edu.ptit.duongvct.discord_bot_test1.command_request.CreateTopicCommandRequest.buildTopicPath;

@Service
@Slf4j
@AllArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;
    public List<ApplicationCommandOptionChoiceData> getAllTopicChoices(String userId) {
        List<Topic> allTopics = topicRepository.findAllByUserId(userId);
        Map<String, Topic> topicMap = allTopics.stream()
                .collect(Collectors.toMap(Topic::getId, t -> t));

        ArrayList<ApplicationCommandOptionChoiceData> parentChoices = new ArrayList<>(topicRepository.findAll().stream()
                .map(topic -> ApplicationCommandOptionChoiceData.builder()
                        .name(buildTopicPath(topic, topicMap))
                        .value(topic.getId())
                        .build())
                .toList());
        parentChoices.add(ApplicationCommandOptionChoiceData.builder()
            .name("root")
            .value("root")
            .build());
        log.info(parentChoices.toString());
        return parentChoices;
    }



}
