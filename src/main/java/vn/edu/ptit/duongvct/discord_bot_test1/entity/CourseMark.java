package vn.edu.ptit.duongvct.discord_bot_test1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
public class CourseMark {
    private double weight;
    private double score;
}
