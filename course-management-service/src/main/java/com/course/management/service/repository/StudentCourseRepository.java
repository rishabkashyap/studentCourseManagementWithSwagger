package com.course.management.service.repository;

import com.course.management.service.entity.Course;
import com.course.management.service.entity.Student;
import com.course.management.service.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse,Long> {

   List<StudentCourse> findByStudentAndCourse(Student student, Course course);
   List<StudentCourse>findByStudentAndEnabled(Student student,Boolean bool);
   List<StudentCourse>findByCourseAndEnabled(Course course,Boolean bool);
}
