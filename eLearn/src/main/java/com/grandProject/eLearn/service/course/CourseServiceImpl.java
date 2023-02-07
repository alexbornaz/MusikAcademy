package com.grandProject.eLearn.service.course;

import com.grandProject.eLearn.dto.request.CourseDTO;
import com.grandProject.eLearn.model.course.Course;
import com.grandProject.eLearn.model.user.User;
import com.grandProject.eLearn.repository.CourseRepository;
import com.grandProject.eLearn.service.user.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
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
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Long saveCourse(CourseDTO courseDTO) {
       return courseRepository.save(mapCourseDto(courseDTO)).getId();
    }

    @Override
    public void deleteUser(Long id) {
        courseRepository.deleteById(id);
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
