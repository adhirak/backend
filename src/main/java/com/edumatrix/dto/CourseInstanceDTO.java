package com.edumatrix.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CourseInstanceDTO {
    
    @NotNull(message = "Course ID is required")
    private String courseId;
    
    @NotNull(message = "Year is required")
    @Min(value = 2020, message = "Year must be at least 2020")
    @Max(value = 2050, message = "Year must not exceed 2050")
    private Integer year;
    
    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be 1 or 2")
    @Max(value = 2, message = "Semester must be 1 or 2")
    private Integer semester;
    
    // Constructors
    public CourseInstanceDTO() {}
    
    public CourseInstanceDTO(String courseId, Integer year, Integer semester) {
        this.courseId = courseId;
        this.year = year;
        this.semester = semester;
    }
    
    // Getters and Setters
    public String getCourseId() {
        return courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    
    public Integer getYear() {
        return year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    public Integer getSemester() {
        return semester;
    }
    
    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}