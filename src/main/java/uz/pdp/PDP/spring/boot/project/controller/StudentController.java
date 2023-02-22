package uz.pdp.PDP.spring.boot.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.PDP.spring.boot.project.model.Student;
import uz.pdp.PDP.spring.boot.project.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    // Vazirlik uchun
    @GetMapping("/forVazirlik")
    public Page<Student> getStudentVazirlik(@RequestParam int page){
        Pageable pageable = PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    // University Uchun
    @GetMapping("/forUniversity/{universityId}")
    public Page<Student> getStudentUniversity(@PathVariable Integer universityId, @RequestParam int page){
        Pageable pageable = PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPage;
    }

    @PostMapping("student")
    public String create(@RequestBody Student student){
        studentRepository.save(student);
        return "Studend added";
    }

    @GetMapping("student/{id}")
    public Student getStudent(@PathVariable Integer id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            return optionalStudent.get();
        }else {
            return new Student();
        }
    }

    @DeleteMapping("student/{id}")
    public String delete(@PathVariable Integer id){
        studentRepository.deleteById(id);
        return "Student deleted";
     }


    @PutMapping("student/{id}")
    public String editStudent(@PathVariable Integer id, @RequestBody Student student){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()){
            Student editStudent = optionalStudent.get();
            editStudent.setFirstName(student.getFirstName());
            editStudent.setLastName(student.getLastName());
            editStudent.setBirthDate(student.getBirthDate());
            editStudent.setPhoneNumber(student.getPhoneNumber());
            studentRepository.save(editStudent);
            return "Student edited";
        }
        return "Student not fount";
    }
}
