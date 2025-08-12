package vn.edu.ptit.duongvct.discord_bot_test1.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.ptit.duongvct.discord_bot_test1.entity.Course;
import vn.edu.ptit.duongvct.discord_bot_test1.repository.CourseRepository;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public Course createCourse(Course course) {
        if (!validateCourse(course)) {
            throw new IllegalArgumentException("Course name already exists");
        }
        return this.courseRepository.save(course);
    }
    private boolean validateCourse(Course course) {
        List<Course> courses = this.courseRepository.findByName(course.getName());
        return courses.isEmpty();
    }
}
