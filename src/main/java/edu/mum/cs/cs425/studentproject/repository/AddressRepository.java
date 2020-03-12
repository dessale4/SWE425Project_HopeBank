package edu.mum.cs.cs425.studentproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.studentproject.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
