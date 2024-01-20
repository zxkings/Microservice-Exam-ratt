package com.example.demo.graphql;

package com.example.demo.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private StudentService studentService;

    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    public Student getStudentById(String id) {
        return studentService.getStudentById(Long.parseLong(id));
    }

    public Student createStudent(String firstName, String lastName, int age) {
        Student newStudent = new Student();
        newStudent.setFirstName(firstName);
        newStudent.setLastName(lastName);
        newStudent.setAge(age);
        return studentService.createStudent(newStudent);
    }

    public Student updateStudent(String id, String firstName, String lastName, int age) {
        Long studentId = Long.parseLong(id);
        Student existingStudent = studentService.getStudentById(studentId);
        if (existingStudent != null) {
            existingStudent.setFirstName(firstName);
            existingStudent.setLastName(lastName);
            existingStudent.setAge(age);
            return studentService.updateStudent(studentId, existingStudent);
        }
        return null;
    }

    public Boolean deleteStudent(String id) {
        Long studentId = Long.parseLong(id);
        studentService.deleteStudent(studentId);
        return true;
    }
}

