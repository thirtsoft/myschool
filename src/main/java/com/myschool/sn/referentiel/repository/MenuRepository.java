package com.myschool.sn.referentiel.repository;

import com.myschool.sn.referentiel.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("SELECT DISTINCT men from Menu men where men.actif=1")
    List<Menu> findAllActivesMenu();

    @Query("SELECT DISTINCT men from Menu men where men.actif=1 and men.categoryMenu.id=:catMenuId")
    List<Menu> findAllActivesMenu(@Param("catMenuId") Long catMenuId);

    @Query("SELECT DISTINCT men from Menu men where men.id=:id and men.actif=1 ")
    Menu findMenuById(@Param("id") Long id);

    @Query("SELECT DISTINCT men from  Menu men where men.actif=1 and men.libelle=:libelle")
    Menu findByLibelle(@Param("libelle") String libelle);
}
