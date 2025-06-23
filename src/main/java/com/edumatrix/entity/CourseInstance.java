package com.edumatrix.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "course_instances", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "acadyear", "semester"}))
public class CourseInstance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "course_id", nullable = false)
    @NotNull(message = "Course ID is required")
    private String courseId;
    
    @Column(name = "acadyear", nullable = false)
    @NotNull(message = "Year is required")
    @Min(value = 2020, message = "Year must be at least 2020")
    @Max(value = 2050, message = "Year must not exceed 2050")
    private Integer acadyear;
    
    @Column(name = "semester", nullable = false)
    @NotNull(message = "Semester is required")
    @Min(value = 1, message = "Semester must be 1 or 2")
    @Max(value = 2, message = "Semester must be 1 or 2")
    private Integer semester;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;
    
    // Constructors
    public CourseInstance() {}
    
    public CourseInstance(String courseId, Integer acadyear, Integer semester) {
        this.courseId = courseId;
        this.acadyear = acadyear;
        this.semester = semester;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getCourseId() {
        return courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    
    public Integer getYear() {
        return acadyear;
    }
    
    public void setYear(Integer acadyear) {
        this.acadyear = acadyear;
    }
    
    public Integer getSemester() {
        return semester;
    }
    
    public void setSemester(Integer semester) {
        this.semester = semester;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
    
    @Override
    public String toString() {
        return "CourseInstance{" +
                "id=" + id +
                ", courseId='" + courseId + '\'' +
                ", acadyear=" + acadyear +
                ", semester=" + semester +
                '}';
    }
}