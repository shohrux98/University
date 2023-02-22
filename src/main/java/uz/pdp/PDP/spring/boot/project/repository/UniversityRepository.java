package uz.pdp.PDP.spring.boot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.PDP.spring.boot.project.model.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {

}
