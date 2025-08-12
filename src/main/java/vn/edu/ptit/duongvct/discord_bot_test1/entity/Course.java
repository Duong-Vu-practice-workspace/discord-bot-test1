package vn.edu.ptit.duongvct.discord_bot_test1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
public class Course {
    @Id
    private String id;
    private String name;
    private String topicId;
    private String userId;
    private String description;
    private CourseMark cc;
    private CourseMark tbkt;
    private CourseMark bttl;
    private CourseMark ck;
    private String finalScoreGrade;
    private double finalScore;

}
