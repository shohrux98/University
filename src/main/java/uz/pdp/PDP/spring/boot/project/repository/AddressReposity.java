package uz.pdp.PDP.spring.boot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.PDP.spring.boot.project.model.Address;

public interface AddressReposity extends JpaRepository<Address, Integer> {
}
