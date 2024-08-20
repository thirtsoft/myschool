package com.myschool.sn.enseignant.repository;

import com.myschool.sn.enseignant.entity.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
    @Query("Select DISTINCT el from  Enseignant el where el.actif=1 order by id")
    List<Enseignant> findAllEnseignants();

    @Query("Select DISTINCT el from  Enseignant el where el.actif=1 and el.matricule=:matricule")
    Enseignant findByMatricule(@Param("matricule") String matricule);

    @Query("Select DISTINCT el from  Enseignant el where el.actif=1 and el.telephone=:telephone")
    Enseignant findByTelephone(@Param("telephone") String telephone);

    @Query("Select DISTINCT el from  Enseignant el where el.id=:id and actif=1")
    Enseignant findEnseignantById(@Param("id") Long id);
}
