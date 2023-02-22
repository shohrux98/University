package uz.pdp.PDP.spring.boot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.PDP.spring.boot.project.model.Faculty;
import uz.pdp.PDP.spring.boot.project.model.University;
import uz.pdp.PDP.spring.boot.project.payload.FacultyDto;
import uz.pdp.PDP.spring.boot.project.repository.FacultyRepository;
import uz.pdp.PDP.spring.boot.project.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;

    // Vazirlik
    @GetMapping
    public List<Faculty> getFaculties(){
        return facultyRepository.findAll();
    }

    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto){
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (exists)
            return "This university such faculty exists";

        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (!optionalUniversity.isPresent())
            return "University not found";
        faculty.setUniversity(optionalUniversity.get());
        facultyRepository.save(faculty);
        return "faculty saved";


    }

    // University uchun
    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultiesUniversityId(@PathVariable Integer universityId){
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;
    }

    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id){
        try{
            facultyRepository.deleteById(id);
            return "Faculty deleted";
        }catch (Exception e){
            return "Error in deleting";
        }
    }

    @PutMapping("/{id}")
    public String editFaculty(@PathVariable Integer id, @RequestBody FacultyDto facultyDto){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()){
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDto.getName());
            Optional<University> optionalUniversity = universityRepository.findById(id);
            if (!optionalUniversity.isPresent()){
                return "University not found";
            }
            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "Faculty edited";
        }
        return "Faculty not found";
    }

}