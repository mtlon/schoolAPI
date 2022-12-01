package com.SchoolAPI.SchoolAPI.teacher;


import com.SchoolAPI.SchoolAPI.exception.UserNotFoundException;
import com.SchoolAPI.SchoolAPI.school.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SchoolService schoolService;

    public List<Teacher> teacherList() {
        return teacherRepository.findAll();
    }
    public Teacher getTeacherById(@PathVariable int id ) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    public String addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return "Teacher added to DB";
    }
    public Teacher updateTeacher(int id, Teacher updateTeacher) {
        return teacherRepository.findById(id)
                .map(teacher -> {
                    teacher.setName(updateTeacher.getName());
                    teacher.setSurname(updateTeacher.getSurname());
                    teacher.setSubject(updateTeacher.getSubject());
                    return teacherRepository.save(teacher);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }
    public String updateTeacherAccount(int id) {
        return teacherRepository.findById(id)
                .map(teacher -> {
                    teacher.setTeacher_account(teacher.getTeacher_account() + teacher.getSalary());
                    schoolService.payment(teacher.getSalary());
                    teacherRepository.save(teacher);
                    return "Updated successful";
                }).orElseThrow(()-> new UserNotFoundException(id));
    }
    public String deleteTeacher(int id) {
        if(teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return "Teacher deleted successfully";
        } else {
            throw new UserNotFoundException(id);
        }
    }
}
