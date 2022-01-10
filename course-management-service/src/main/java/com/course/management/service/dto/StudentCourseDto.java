package com.course.management.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentCourseDto {
    private Long studentEnrollmentId;
    private Long courseId;
}
