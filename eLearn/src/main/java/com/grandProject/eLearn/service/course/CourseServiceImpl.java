package com.grandProject.eLearn.service.course;


import com.grandProject.eLearn.dto.request.CourseDTO;
import com.grandProject.eLearn.model.Course;
import com.grandProject.eLearn.model.User;
import com.grandProject.eLearn.repository.CourseRepository;
import com.grandProject.eLearn.service.user.UserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService{
    private  final CourseRepository courseRepository;
    private  final UserService userService;

    public CourseServiceImpl(CourseRepository courseRepository, UserService userService) {
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course getValidCourseById(Long id) {
        return getCourseById(id).orElseThrow(()->new IllegalArgumentException("Course not found"));
    }

    @Override
    public boolean exists(Long id) {
        return courseRepository.existsById(id);
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Long saveCourse(CourseDTO courseDTO) {
        log.info("Created course with title {} and owner {}", courseDTO.getTitle(),courseDTO.getCreatorUsername());
        return courseRepository.save(mapCourseDto(courseDTO)).getId();
    }

    @Override
    public List<Course> getTopCourses() {
        Pageable pageable = PageRequest.of(0,5);
        return courseRepository.getTop(pageable);
    }

    @Override
    public void deleteCourse(Long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();
            for (User user : course.getSignedUsers()) {
                user.getEnrolledCourses().remove(course);
            }
            courseRepository.delete(course);
        } else {
            throw new IllegalArgumentException("Course not found with ID " + courseId);
        }
    }


    private Course mapCourseDto(CourseDTO courseDTO){
        Course course = new Course();
        User creator = userService.validateAndGetUserByUsername(courseDTO.getCreatorUsername());
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setRequirements(courseDTO.getRequirements());
        course.setOutline(courseDTO.getOutline());
        course.setDefaultPrice(BigDecimal.valueOf(Double.parseDouble(courseDTO.getPrice())));
        course.setImage(courseDTO.getImage());
        course.setCreator(creator);
        return course;
    }
}
