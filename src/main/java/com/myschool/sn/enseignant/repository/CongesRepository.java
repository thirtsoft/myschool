package com.myschool.sn.enseignant.repository;


import com.myschool.sn.enseignant.entity.Conges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface CongesRepository extends JpaRepository<Conges, Long> {
    @Query("Select DISTINCT c from  Conges c where c.id=:id and c.actif=1")
    Conges findCongesById(@Param("id") Long id);

    @Query("Select DISTINCT c from  Conges c where c.actif=1 order by id")
    List<Conges> findAllCongess();

    @Query("Select DISTINCT c from  Conges c where c.actif=1 and c.etat=2 order by id")
    List<Conges> findAllCongesSoumis();

    @Query("Select DISTINCT c from  Conges c where c.actif=1 and c.etat=3 order by id")
    List<Conges> findAllCongesAcceptes();

    @Query("Select DISTINCT c from  Conges c where c.actif=1 and c.etat=4 order by id")
    List<Conges> findAllCongesRejetes();

    @Query("Select DISTINCT c from  Conges c where c.id=:id and c.actif=1")
    List<Conges> findCongesByDemandeur(@Param("id") Long id);
}
