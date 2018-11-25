package com.komarov.onedrive.dao.repository;

import com.komarov.onedrive.dao.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
