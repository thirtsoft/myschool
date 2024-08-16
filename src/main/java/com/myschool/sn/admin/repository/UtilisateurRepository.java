package com.myschool.sn.admin.repository;

import com.myschool.sn.admin.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    @Query("Select DISTINCT u from Utilisateur u where u.actif=true and u.id!=1 order by u.telephone")
    List<Utilisateur> findAllActive();

    @Query(value = "SELECT DISTINCT u FROM Utilisateur u WHERE u.id=:id")
    Utilisateur findUtilisateurById(@Param("id") Long id);

    @Query(value = "SELECT DISTINCT u FROM Utilisateur u WHERE lower(u.username)=lower(:username) and u.actif=true")
    Utilisateur findUtilisateurByUsername(@Param("username") String username);

    Optional<Utilisateur> findByUsername(String username);

    @Query(value = "SELECT DISTINCT u FROM Utilisateur u WHERE (lower(u.email)=lower(:email) or u.telephone=:email) and u.actif=true")
    Utilisateur findByEmailOrTel(@Param("email") String email);

    @Query(value = "SELECT DISTINCT u.email FROM Utilisateur u WHERE u.activation=:code")
    String findMailByActivation(@Param("code") String code);

    @Query(value = "SELECT DISTINCT u FROM Utilisateur u WHERE u.activation=:code")
    Utilisateur findUtilisateurByActivation(@Param("code") String code);

}