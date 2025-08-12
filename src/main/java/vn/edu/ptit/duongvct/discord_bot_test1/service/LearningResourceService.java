package vn.edu.ptit.duongvct.discord_bot_test1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.LearningResource;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.LearningResourceRepository;

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
}
