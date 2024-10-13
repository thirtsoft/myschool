package com.myschool.sn.enseignant.repository;

import com.myschool.sn.enseignant.entity.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciceRepository extends JpaRepository<Exercice, Long> {

    @Query("Select DISTINCT c from  Exercice c where c.id=:id and c.actif=true")
    Exercice findExerciceById(@Param("id") Long id);

    @Query("Select DISTINCT c from  Exercice c where c.actif=true order by id desc")
    List<Exercice> findAllExercices();

    @Query("Select DISTINCT c from  Exercice c where c.actif=true and c.enseignant.id=id order by id desc")
    List<Exercice> findAllExerciceByEnseignants(@Param("id") Long id);

    @Query("Select DISTINCT c from  Exercice c where c.actif=true and c.classe.id=id order by id desc")
    List<Exercice> findAllExerciceByClasses(@Param("id") Long id);
}
