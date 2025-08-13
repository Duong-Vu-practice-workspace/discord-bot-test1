package vn.edu.ptit.duongvct.discord_bot_test1.service;

import discord4j.discordjson.json.ApplicationCommandOptionChoiceData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.LearningResource;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.LearningResourceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LearningResourceService {
    private final LearningResourceRepository learningResourceRepository;
    public LearningResource createLearningResource(LearningResource learningResource) {
        if (!validateLearningResource(learningResource)) {
            throw new IllegalArgumentException("Learning resource name already exists");
        }
        return this.learningResourceRepository.save(learningResource);
    }
    private boolean validateLearningResource(LearningResource learningResource) {
        return this.learningResourceRepository.findByName(learningResource.getName()).isEmpty();
    }
    public List<ApplicationCommandOptionChoiceData> getAllResourceChoices(String query, String userId) {
        return new ArrayList<>(learningResourceRepository.findByUserIdAndNameContainingIgnoreCase(userId, query)
                .stream()
                .map(resource -> ApplicationCommandOptionChoiceData.builder()
                        .name(resource.getName())
                        .value(resource.getId())
                        .build())
                .toList());
    }
    public LearningResource findLearningResourceById(String resourceId) {
        return this.learningResourceRepository.findById(resourceId).orElse(null);
    }
    public LearningResource updateLearningResource(LearningResource learningResource) {
        return this.learningResourceRepository.save(learningResource);
    }
    public void deleteLearningResource(String id) {
        this.learningResourceRepository.deleteById(id);
    }
}
