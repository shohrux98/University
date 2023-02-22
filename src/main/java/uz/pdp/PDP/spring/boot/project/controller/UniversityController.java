package uz.pdp.PDP.spring.boot.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.PDP.spring.boot.project.model.Address;
import uz.pdp.PDP.spring.boot.project.model.University;
import uz.pdp.PDP.spring.boot.project.payload.UniversityDto;
import uz.pdp.PDP.spring.boot.project.repository.AddressReposity;
import uz.pdp.PDP.spring.boot.project.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressReposity addressReposity;


    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversities(){
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }

    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto){
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        Address savedAddress = addressReposity.save(address);

        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);

        return "University added";

    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto){
        Optional<University> optional = universityRepository.findById(id);
        if (optional.isPresent()){
            University university = optional.get();
            university.setName(universityDto.getName());
            // universititetni addressi
            Address address = university.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            addressReposity.save(address);
            universityRepository.save(university);
            return "University edited";
        }
        return "University not found";
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id){
        Optional<University> optional = universityRepository.findById(id);
        if (optional.isPresent()) {
            University university = optional.get();
            Address address = university.getAddress();
            universityRepository.deleteById(id);
            addressReposity.deleteById(address.getId());
            return "University deleted";
        }
        return "University not fount";
    }
}
