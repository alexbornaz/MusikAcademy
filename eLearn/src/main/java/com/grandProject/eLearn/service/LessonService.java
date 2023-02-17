package com.grandProject.eLearn.service;

import com.grandProject.eLearn.dto.request.LessonInfo;
import com.grandProject.eLearn.model.course.Course;
import com.grandProject.eLearn.model.lesson.Lesson;
import com.grandProject.eLearn.repository.LessonRepository;
import com.grandProject.eLearn.service.course.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Set<Lesson> getAllLessonsForCourse(long courseId) {
        return lessonRepository.findAllByCourse_Id(courseId);
    }


    public void addLessonToCourse(Course course, LessonInfo lessonInfo) {
        Lesson newLesson = new Lesson(lessonInfo.getTitle(),lessonInfo.getUrl(),course);
        lessonRepository.save(newLesson);
        log.info("Saved new lesson to course with id {}",course.getId());
    }

    public void deleteLesson(long lessonId) {
        log.info("Deleted lesson with id {}",lessonId);
        lessonRepository.deleteById(lessonId);
    }
}
