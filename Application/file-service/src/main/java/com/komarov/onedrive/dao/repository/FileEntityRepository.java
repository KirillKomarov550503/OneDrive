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
  double findAverageFileSize();

  @Query("select avg(fe.size) from FileEntity fe where fe.personId = :personId")
  double findAverageFileSizeByPersonId(@Param("personId") long personId);

  @Query("select sum(fe.size) from FileEntity fe")
  long findGeneralFileSizeSum();

  @Query("select sum(fe.size) from FileEntity fe where fe.personId = :personId")
  long findGeneralFileSizeSumByPersonId(@Param("personId") long personId);
}