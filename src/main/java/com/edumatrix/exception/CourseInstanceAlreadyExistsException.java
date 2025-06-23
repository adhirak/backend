package com.edumatrix.exception;

public class CourseInstanceAlreadyExistsException extends RuntimeException {
    public CourseInstanceAlreadyExistsException(String message) {
        super(message);
    }
}