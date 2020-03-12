package edu.mum.cs.cs425.studentproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.cs425.studentproject.model.Credential;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long>{

}
