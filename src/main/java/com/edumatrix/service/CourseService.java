package com.edumatrix.service;

import com.edumatrix.dto.CourseDTO;
import com.edumatrix.entity.Course;
import com.edumatrix.exception.CourseAlreadyExistsException;
import com.edumatrix.exception.CourseNotFoundException;
import com.edumatrix.exception.PrerequisiteViolationException;
import com.edumatrix.repository.CourseInstanceRepository;
import com.edumatrix.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseInstanceRepository courseInstanceRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(String courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course " + courseId + " not found"));
    }

    public Course createCourse(CourseDTO courseDTO) {
        // Check if course already exists
        if (courseRepository.existsById(courseDTO.getCourseId())) {
            throw new CourseAlreadyExistsException("Course " + courseDTO.getCourseId() + " already exists");
        }

        // Validate prerequisites exist
        if (courseDTO.getPrerequisites() != null && !courseDTO.getPrerequisites().isEmpty()) {
            for (String prereq : courseDTO.getPrerequisites()) {
                if (!courseRepository.existsById(prereq)) {
                    throw new PrerequisiteViolationException("Prerequisite course " + prereq + " does not exist");
                }
            }
        }

        Course course = new Course();
        course.setCourseId(courseDTO.getCourseId().toUpperCase());
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setPrerequisites(courseDTO.getPrerequisites());

        return courseRepository.save(course);
    }

    public void deleteCourse(String courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course " + courseId + " not found"));

        // Check if course is a prerequisite for other courses
        List<Course> dependentCourses = courseRepository.findCoursesByPrerequisite(courseId);
        if (!dependentCourses.isEmpty()) {
            String dependentIds = dependentCourses.stream()
                    .map(Course::getCourseId)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("");
            throw new PrerequisiteViolationException(
                    "Cannot delete course " + courseId + ". It is a prerequisite for: " + dependentIds);
        }

        // Check if course has instances
        if (courseInstanceRepository.existsByCourseId(courseId)) {
            throw new PrerequisiteViolationException(
                    "Cannot delete course " + courseId + ". It has active instances.");
        }

        courseRepository.delete(course);
    }
}