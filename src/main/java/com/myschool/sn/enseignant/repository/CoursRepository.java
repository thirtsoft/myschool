package com.myschool.sn.enseignant.repository;

import com.myschool.sn.enseignant.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursRepository extends JpaRepository<Cours, Long> {


    @Query("Select DISTINCT c from Cours c where c.actif=1 order by c.id desc")
    List<Cours> findAllCourss();

    @Query("Select DISTINCT c from  Cours c where c.matiere.id=:matId and c.actif=1")
    List<Cours> findCoursByMatiere(@Param("matId") Long matId);

    @Query("Select DISTINCT c from  Cours c where c.classe.id=:classeId and c.actif=1")
    List<Cours> findCoursByClasse(@Param("classeId") Long classeId);

    @Query("Select DISTINCT c from  Cours c where c.enseignant.id=:ensId and c.actif=1")
    List<Cours> findCoursByEnseignant(@Param("ensId") Long ensId);
}
