package uz.pdp.PDP.spring.boot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.PDP.spring.boot.project.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    boolean existsByName(String name);
}
