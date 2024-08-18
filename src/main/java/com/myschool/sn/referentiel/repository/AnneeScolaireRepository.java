package com.myschool.sn.referentiel.repository;

import com.myschool.sn.referentiel.entity.AnneeScolaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnneeScolaireRepository extends JpaRepository<AnneeScolaire, Long> {
    @Query("Select DISTINCT el from  AnneeScolaire el where el.actif=1 order by id")
    List<AnneeScolaire> findAllAnneeScolaires();

    @Query("Select DISTINCT el from  AnneeScolaire el where el.actif=1 and el.code=:code order by id")
    AnneeScolaire findByCode(@Param("code") String code);

    @Query("Select DISTINCT el from  AnneeScolaire el where el.id=:id and actif=1")
    AnneeScolaire findAnneeScolaireById(@Param("id") Long id);

}
