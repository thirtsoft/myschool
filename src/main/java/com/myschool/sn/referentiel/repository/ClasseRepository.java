package com.myschool.sn.referentiel.repository;

import com.myschool.sn.referentiel.entity.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClasseRepository extends JpaRepository<Classe, Long> {

    @Query("SELECT DISTINCT cl from Classe cl where cl.actif=1 and cl.id=:id")
    Classe findClasseById(@Param("id") Long id);

    @Query("SELECT DISTINCT cl from Classe cl where cl.actif=1 and cl.libelle=:libelle")
    Classe findClasseByLibelle(@Param("libelle") String libelle);

    @Query("SELECT DISTINCT cl from Classe cl where cl.actif=1")
    List<Classe> findAllActives();
}
