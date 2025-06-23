package com.edumatrix.controller;

import com.edumatrix.dto.CourseInstanceDTO;
import com.edumatrix.entity.CourseInstance;
import com.edumatrix.service.CourseInstanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instances")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class CourseInstanceController {

    @Autowired
    private CourseInstanceService courseInstanceService;

    @GetMapping
    public ResponseEntity<List<CourseInstance>> getAllInstances(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer semester) {
        
        List<CourseInstance> instances;
        
        if (year != null && semester != null) {
            instances = courseInstanceService.getInstancesByYearAndSemester(year, semester);
        } else if (year != null) {
            instances = courseInstanceService.getInstancesByYear(year);
        } else if (semester != null) {
            instances = courseInstanceService.getInstancesBySemester(semester);
        } else {
            instances = courseInstanceService.getAllInstances();
        }
        
        return ResponseEntity.ok(instances);
    }

    @GetMapping("/{year}/{semester}/{courseId}")
    public ResponseEntity<CourseInstance> getInstance(
            @PathVariable Integer year,
            @PathVariable Integer semester,
            @PathVariable String courseId) {
        
        CourseInstance instance = courseInstanceService.getInstance(year, semester, courseId);
        return ResponseEntity.ok(instance);
    }

    @PostMapping
    public ResponseEntity<CourseInstance> createInstance(@Valid @RequestBody CourseInstanceDTO instanceDTO) {
        CourseInstance createdInstance = courseInstanceService.createInstance(instanceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInstance);
    }

    @DeleteMapping("/{year}/{semester}/{courseId}")
    public ResponseEntity<Void> deleteInstance(
            @PathVariable Integer year,
            @PathVariable Integer semester,
            @PathVariable String courseId) {
        
        courseInstanceService.deleteInstance(year, semester, courseId);
        return ResponseEntity.noContent().build();
    }
}