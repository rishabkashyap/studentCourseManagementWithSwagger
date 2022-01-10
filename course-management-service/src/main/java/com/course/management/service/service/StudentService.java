package com.course.management.service.service;

import com.course.management.service.entity.Student;
import com.course.management.service.exception.ResourceNotFoundException;
import com.course.management.service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student){
       return studentRepository.save(student);
    }

    public void deleteStudent(Long enrollmentId){
        studentRepository.deleteById(enrollmentId);
    }

    public Student updateStudent(Long enrollmentId, Student student){
        Student studentToBeUpdated = studentRepository.findById(enrollmentId).orElseThrow(() ->
                new ResourceNotFoundException("Requseted course id Not Found"));
        if (student.getDateOfBirth()!=null){
            studentToBeUpdated.setDateOfBirth(student.getDateOfBirth());
        }
        if (student.getFirstName()!=null){
            studentToBeUpdated.setFirstName(student.getFirstName());
        }
        if (student.getLastName()!=null){
            studentToBeUpdated.setLastName(student.getLastName());
        }
        return studentRepository.save(studentToBeUpdated);
    }

    public Student getStudentByEnrollmentId(Long enrollmentId){
        return studentRepository.findById(enrollmentId).orElseThrow(() ->
                new ResourceNotFoundException("Requseted course id Not Found,Please Enter differentId"));
    }

}
