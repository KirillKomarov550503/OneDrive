package com.komarov.onedrive.dao.repository;

import com.komarov.onedrive.dao.entity.FileEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileEntityRepository extends JpaRepository<FileEntity, Long> {

  List<FileEntity> findFileEntitiesByPersonId(long personId);

  @Query("select avg(fe.size) from FileEntity fe")
  Double findAverageFileSize();

  @Query("select avg(fe.size) from FileEntity fe where fe.personId = :personId")
  Double findAverageFileSizeByPersonId(@Param("personId") long personId);

  @Query("select sum(fe.size) from FileEntity fe")
  Long findGeneralFileSizeSum();

  @Query("select sum(fe.size) from FileEntity fe where fe.personId = :personId")
  Long findGeneralFileSizeSumByPersonId(@Param("personId") long personId);
}