package com.myschool.sn.admin.repository;

import com.myschool.sn.admin.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Long> {

    @Query("Select DISTINCT act from Action act where act.actif=1 order by act.libelle asc")
    List<Action> findAllActives();

    @Query("Select DISTINCT act from  Action act where act.typeCompte=:typeCompte and act.libelle=:libelle order by id")
    Action findByLibelle(@Param("libelle") String libelle, @Param("typeCompte") String typeCompte);

    @Query("Select DISTINCT act from  Action act where act.typeCompte=:typeCompte order by id")
    List<Action> findByTypeCompte(@Param("typeCompte") String typeCompte);
}
