package edu.mum.cs.cs425.studentproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.studentproject.model.AccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long>{

}
