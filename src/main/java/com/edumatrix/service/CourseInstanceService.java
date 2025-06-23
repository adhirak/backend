package com.edumatrix.service;

import com.edumatrix.dto.CourseInstanceDTO;
import com.edumatrix.entity.CourseInstance;
import com.edumatrix.exception.CourseInstanceAlreadyExistsException;
import com.edumatrix.exception.CourseInstanceNotFoundException;
import com.edumatrix.exception.CourseNotFoundException;
import com.edumatrix.repository.CourseInstanceRepository;
import com.edumatrix.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseInstanceService {

    @Autowired
    private CourseInstanceRepository courseInstanceRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseInstance> getAllInstances() {
        return courseInstanceRepository.findAll();
    }

    public List<CourseInstance> getInstancesByYear(Integer acadyear) {
        return courseInstanceRepository.findByAcadyear(acadyear);
    }

    public List<CourseInstance> getInstancesBySemester(Integer semester) {
        return courseInstanceRepository.findBySemester(semester);
    }

    public List<CourseInstance> getInstancesByYearAndSemester(Integer acadyear, Integer semester) {
        return courseInstanceRepository.findByAcadyearAndSemester(acadyear, semester);
    }

    public CourseInstance getInstance(Integer acadyear, Integer semester, String courseId) {
        return courseInstanceRepository.findByCourseIdAndAcadyearAndSemester(courseId, acadyear, semester)
                .orElseThrow(() -> new CourseInstanceNotFoundException("Instance not found"));
    }

    public CourseInstance createInstance(CourseInstanceDTO instanceDTO) {
        // Check if course exists
        if (!courseRepository.existsById(instanceDTO.getCourseId())) {
            throw new CourseNotFoundException("Course " + instanceDTO.getCourseId() + " does not exist");
        }

        // Check if instance already exists
        if (courseInstanceRepository.findByCourseIdAndAcadyearAndSemester(
                instanceDTO.getCourseId(), instanceDTO.getYear(), instanceDTO.getSemester()).isPresent()) {
            throw new CourseInstanceAlreadyExistsException(
                    "Instance for " + instanceDTO.getCourseId() + " in " + instanceDTO.getYear() + 
                    " semester " + instanceDTO.getSemester() + " already exists");
        }

        CourseInstance instance = new CourseInstance();
        instance.setCourseId(instanceDTO.getCourseId());
        instance.setYear(instanceDTO.getYear());  // still using setYear() here because that's your method name
        instance.setSemester(instanceDTO.getSemester());

        return courseInstanceRepository.save(instance);
    }

    public void deleteInstance(Integer acadyear, Integer semester, String courseId) {
        CourseInstance instance = courseInstanceRepository.findByCourseIdAndAcadyearAndSemester(courseId, acadyear, semester)
                .orElseThrow(() -> new CourseInstanceNotFoundException("Instance not found"));

        courseInstanceRepository.delete(instance);
    }
}
