package uz.pdp.PDP.spring.boot.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.PDP.spring.boot.project.model.Subject;
import uz.pdp.PDP.spring.boot.project.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject){
        if (subjectRepository.existsByName(subject.getName())){
            return "This subject already exist";
        }
        subjectRepository.save(subject);
        return "Subject added";
    }

    @GetMapping
    public List<Subject> getSubject(){
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String editSubject(@PathVariable Integer id, Subject subject){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()){
            Subject subject1 = optionalSubject.get();
            subject1.setName(subject.getName());
            subjectRepository.save(subject1);
            return "Subject edited";
        }
        return "Subject not found";
    }
}
