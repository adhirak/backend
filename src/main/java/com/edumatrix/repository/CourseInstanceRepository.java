package com.edumatrix.repository;

import com.edumatrix.entity.CourseInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseInstanceRepository extends JpaRepository<CourseInstance, Long> {

    Optional<CourseInstance> findByCourseIdAndAcadyearAndSemester(String courseId, Integer acadyear, Integer semester);

    List<CourseInstance> findByCourseId(String courseId);

    List<CourseInstance> findByAcadyear(Integer acadyear);

    List<CourseInstance> findBySemester(Integer semester);

    List<CourseInstance> findByAcadyearAndSemester(Integer acadyear, Integer semester);

    @Query("SELECT COUNT(ci) > 0 FROM CourseInstance ci WHERE ci.courseId = :courseId")
    boolean existsByCourseId(@Param("courseId") String courseId);
}
