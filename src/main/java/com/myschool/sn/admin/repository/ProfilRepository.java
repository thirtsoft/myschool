package com.myschool.sn.admin.repository;

import com.myschool.sn.admin.entity.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProfilRepository extends JpaRepository<Profil, Long> {

    @Query("Select DISTINCT prof from Profil prof where prof.actif=1 and prof.id != 1 order by prof.libelle")
    List<Profil> findAllActive();

    @Query("Select DISTINCT prof from  Profil prof where prof.id=:id")
    Profil findProfilById(@Param("id") Long id);

    @Query("Select DISTINCT prof from  Profil prof where prof.actif=1 AND lower(prof.libelle)=lower(:libelle)")
    Profil findByLibelle(@Param("libelle") String libelle);

    @Query("Select DISTINCT prof from  Profil prof where prof.typeCompte=:typeCompte and prof.id != 1 order by id")
    List<Profil> findByTypeCompte(@Param("typeCompte") String typeCompte);
}
