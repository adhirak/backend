package com.edumatrix.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class CourseDTO {
    
    @NotBlank(message = "Course ID is required")
    @Pattern(regexp = "^[A-Z]{2,4}\\d{3}$", message = "Course ID should be in format like CS101, MATH201")
    private String courseId;
    
    @NotBlank(message = "Course title is required")
    @Size(max = 255, message = "Course title must not exceed 255 characters")
    private String title;
    
    @NotBlank(message = "Course description is required")
    @Size(max = 2000, message = "Course description must not exceed 2000 characters")
    private String description;
    
    private List<String> prerequisites;
    
    // Constructors
    public CourseDTO() {}
    
    public CourseDTO(String courseId, String title, String description, List<String> prerequisites) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.prerequisites = prerequisites;
    }
    
    // Getters and Setters
    public String getCourseId() {
        return courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<String> getPrerequisites() {
        return prerequisites;
    }
    
    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }
}