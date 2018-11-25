package com.komarov.onedrive.dao.repository;

import com.komarov.onedrive.dao.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {
  Credential findCredentialByEmailAndAndPassword(String email, String password);
}
