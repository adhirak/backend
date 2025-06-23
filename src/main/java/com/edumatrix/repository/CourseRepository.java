package com.edumatrix.repository;

import com.edumatrix.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    
    @Query("SELECT c FROM Course c JOIN c.prerequisites p WHERE p = :courseId")
    List<Course> findCoursesByPrerequisite(@Param("courseId") String courseId);
    
    @Query("SELECT c FROM Course c WHERE c.courseId IN :courseIds")
    List<Course> findByCourseIdIn(@Param("courseIds") List<String> courseIds);
}