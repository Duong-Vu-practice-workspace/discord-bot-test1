package vn.edu.ptit.duongvct.discord_bot_test1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import vn.edu.ptit.duongvct.discord_bot_test1.constants.ResourceType;

@Document
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
public class LearningResource {
    private String id;
    private String name;
    private String description;
    private String link;
    private String courseId;
    private String userId;
    private ResourceType type;
}
