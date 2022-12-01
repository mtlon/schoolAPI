package com.SchoolAPI.SchoolAPI.student;

import com.SchoolAPI.SchoolAPI.exception.UserNotFoundException;
import com.SchoolAPI.SchoolAPI.school.SchoolService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SchoolService schoolService;
    public List<Student> studentList() {
        return studentRepository.findAll();
    }
    public Student getStudentById(@PathVariable int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    public String addStudent(Student student) {
        studentRepository.save(student);
        return "Student added to DB";
    }
    public Student updateStudent(int id, Student updateStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    if(updateStudent.getName() != null) student.setName(updateStudent.getName());
                    if(updateStudent.getSurname() != null) student.setSurname(updateStudent.getSurname());
                    return studentRepository.save(student);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }
    public String depositMoney(int id, JsonNode data) {
        Student currentStudent = getStudentById(id);
        int paidAmount = data.get("amount").asInt();

        updateStudent(id, currentStudent);

        currentStudent.setRest_Money_To_Pay(currentStudent.getRest_Money_To_Pay() - paidAmount);
        currentStudent.setStudent_Account(currentStudent.getStudent_Account() + paidAmount);
        schoolService.depositSchoolAccount(paidAmount);
        studentRepository.save(currentStudent);

        return "Updated successful";
    }
    public String delete(int id) {
        studentRepository.deleteById(id);
        return "Deleted successful";
    }
}
