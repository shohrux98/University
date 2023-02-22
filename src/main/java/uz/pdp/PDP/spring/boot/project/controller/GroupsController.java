package uz.pdp.PDP.spring.boot.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.PDP.spring.boot.project.model.Faculty;
import uz.pdp.PDP.spring.boot.project.model.Group;
import uz.pdp.PDP.spring.boot.project.payload.GroupDto;
import uz.pdp.PDP.spring.boot.project.repository.FacultyRepository;
import uz.pdp.PDP.spring.boot.project.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupsController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;


//    Vazirlik uchun
    @GetMapping
    public List<Group> getGroup(){
       return groupRepository.findAll();
    }

    // university uchun
    @GetMapping("/byUniversityId/{universityId}")
    public List<Group> getGroupsByUniversityId(@PathVariable Integer universityId){
        List<Group> allByFaculty_universityId = groupRepository.findAllByFaculty_UniversityId(universityId);
//        List<Group> groupsByUniversityId = groupRepository.getGroupsByUniversityId(universityId);
//        List<Group> groupsByUniversityIdNative = groupRepository.getGroupsByUniversityIdNative(universityId);
        return allByFaculty_universityId;
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto){
        Group group = new Group();
        group.setName(groupDto.getName());
        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent())
            return "Such faculty not fount";
        group.setFaculty(optionalFaculty.get());
        groupRepository.save(group);
        return "Group added";
    }
}
