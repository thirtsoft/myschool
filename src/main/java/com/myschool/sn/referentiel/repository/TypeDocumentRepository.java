package com.myschool.sn.referentiel.repository;

import com.myschool.sn.referentiel.entity.TypeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {
    @Query("Select DISTINCT el from  TypeDocument el where el.actif=1 order by id")
    List<TypeDocument> findAllTypeDocuments();

    @Query("Select DISTINCT el from  TypeDocument el where el.actif=1 and el.code=:code")
    TypeDocument findByCode(@Param("code") String code);

    @Query("Select DISTINCT el from  TypeDocument el where el.actif=1 and el.libelle=:libelle")
    TypeDocument findByLibelle(@Param("libelle") String libelle);

    @Query("Select DISTINCT el from  TypeDocument el where el.id=:id and actif=1")
    TypeDocument findTypeDocumentById(@Param("id") Long id);
}
