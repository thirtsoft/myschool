package com.myschool.sn.referentiel.repository;

import com.myschool.sn.referentiel.entity.CategoryMenu;
import com.myschool.sn.referentiel.entity.CategoryMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryMenuRepository extends JpaRepository<CategoryMenu, Long> {
    @Query("SELECT DISTINCT cat from CategoryMenu cat where cat.actif=1")
    List<CategoryMenu> findAllActivesCategoryMenu();

    @Query("SELECT DISTINCT cat from CategoryMenu cat where cat.id=:id and cat.actif=1 ")
    CategoryMenu findCategoryMenuById(@Param("id") Long id);

    @Query("SELECT DISTINCT cat from  CategoryMenu cat where cat.actif=1 and cat.libelle=:libelle")
    CategoryMenu findByLibelle(@Param("libelle") String libelle);

}