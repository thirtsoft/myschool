package com.myschool.sn.parent.repository;


import com.myschool.sn.parent.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParentRepository extends JpaRepository<Parent, Long> {

    @Query("Select DISTINCT el from  Parent el where el.id=:id and actif=1")
    Parent findParentById(@Param("id") Long id);

    @Query("Select DISTINCT el from  Parent el where el.utilisateur.id=:userId and actif=1")
    Parent findParentByUtilisateurId(@Param("userId") Long userId);

    @Query("Select DISTINCT el from  Parent el where el.actif=1 order by id desc")
    List<Parent> findAllParents();

}
