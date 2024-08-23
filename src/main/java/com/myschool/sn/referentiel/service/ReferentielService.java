package com.myschool.sn.referentiel.service;

import com.myschool.sn.referentiel.exception.ReferentielException;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;
import com.myschool.sn.utils.dtos.referentiel.BatimentDTO;
import com.myschool.sn.utils.dtos.referentiel.CategoryMenuDTO;
import com.myschool.sn.utils.dtos.referentiel.ClasseDTO;
import com.myschool.sn.utils.dtos.referentiel.EvenementDTO;
import com.myschool.sn.utils.dtos.referentiel.MatiereDTO;
import com.myschool.sn.utils.dtos.referentiel.MenuDTO;
import com.myschool.sn.utils.dtos.referentiel.NiveauEducationDTO;
import com.myschool.sn.utils.dtos.referentiel.SalleDTO;
import com.myschool.sn.utils.dtos.referentiel.SemestreDTO;
import com.myschool.sn.utils.dtos.referentiel.TypeDocumentDTO;

import java.util.List;

public interface ReferentielService {

    /**************   AnneeScolaire ***********************/
    Long saveAnneeScolaire(AnneeScolaireDTO anneeScolaireDTO) throws ReferentielException;

    Long updateAnneeScolaire(Long id, AnneeScolaireDTO anneeScolaireDTO) throws ReferentielException;

    AnneeScolaireDTO findAnneeScolaireDTOById(Long id);

    AnneeScolaireDTO findByCode(String code);

    List<AnneeScolaireDTO> findAllAnneeScolaires();

    void deleteAnneeScolaire(Long id);

    /**************   Batiment ***********************/
    Long saveBatiment(BatimentDTO batimentDTO) throws ReferentielException;

    Long updateBatiment(Long id, BatimentDTO batimentDTO) throws ReferentielException;

    BatimentDTO findBatimentById(Long id);

    BatimentDTO findBatimentByCode(String code);

    List<BatimentDTO> findAllBatiments();

    void deleteBatiment(Long id);

    /**************   Classe ***********************/
    Long saveClasse(ClasseDTO classeDTO) throws ReferentielException;

    Long updateClasse(Long id, ClasseDTO classeDTO) throws ReferentielException;

    ClasseDTO findClasseById(Long id);

    ClasseDTO findClasseByLibelle(String libelle);

    List<ClasseDTO> findAllClasses();

    void deleteClasse(Long id);

    /**************   Matiere ***********************/
    Long saveMatiere(MatiereDTO matiereDTO) throws ReferentielException;

    Long updateMatiere(Long id, MatiereDTO matiereDTO) throws ReferentielException;

    MatiereDTO findMatiereById(Long id);

    MatiereDTO findMatiereByCode(String code);

    MatiereDTO findMatiereByLibelle(String libelle);

    List<MatiereDTO> findAllMatieres();

    void deleteMatiere(Long id);

    /**************   Salle ***********************/
    Long saveSalle(SalleDTO salleDTO) throws ReferentielException;

    Long updateSalle(Long id, SalleDTO salleDTO) throws ReferentielException;

    SalleDTO findSalleById(Long id);

    SalleDTO findSalleByCode(String code);

    List<SalleDTO> findAllSalles();

    List<SalleDTO> findSallesByBatiment(Long batimentId);

    void deleteSalle(Long id);

    /**************   Evenement ***********************/
    Long saveEvenement(EvenementDTO evenementDTO) throws ReferentielException;

    Long updateEvenement(Long id, EvenementDTO evenementDTO) throws ReferentielException;

    EvenementDTO findEvenementById(Long id);

    EvenementDTO findEvenementByLibelle(String libelle);

    List<EvenementDTO> findAllEvenements();

    void deleteEvenement(Long id);

    /**************   Semestre ***********************/
    Long saveSemestre(SemestreDTO semestreDTO) throws ReferentielException;

    Long updateSemestre(Long id, SemestreDTO semestreDTO) throws ReferentielException;

    SemestreDTO findSemestreById(Long id);

    SemestreDTO findSemestreByCode(String code);

    SemestreDTO findSemestreByLibelle(String libelle);

    List<SemestreDTO> findAllSemestres();

    void deleteSemestre(Long id);

    /**************   TypeDocument ***********************/
    Long saveTypeDocument(TypeDocumentDTO typeDocumentDTO) throws ReferentielException;

    Long updateTypeDocument(Long id, TypeDocumentDTO typeDocumentDTO) throws ReferentielException;

    TypeDocumentDTO findTypeDocumentById(Long id);

    TypeDocumentDTO findTypeDocumentByCode(String code);

    TypeDocumentDTO findTypeDocumentByLibelle(String libelle);

    List<TypeDocumentDTO> findAllTypeDocuments();

    void deleteTypeDocument(Long id);

    /**************   NiveauEducation ***********************/
    Long saveNiveauEducation(NiveauEducationDTO niveauEducationDTO) throws ReferentielException;

    Long updateNiveauEducation(Long id, NiveauEducationDTO niveauEducationDTO) throws ReferentielException;

    NiveauEducationDTO findNiveauEducationById(Long id);

    NiveauEducationDTO findNiveauEducationByCode(String code);

    NiveauEducationDTO findNiveauEducationByLibelle(String libelle);

    List<NiveauEducationDTO> findAllNiveauEducations();

    void deleteNiveauEducation(Long id);

    /**************   CategoryMenu ***********************/
    Long saveCategoryMenu(CategoryMenuDTO categoryMenuDTO) throws ReferentielException;

    Long updateCategoryMenu(Long id, CategoryMenuDTO categoryMenuDTO) throws ReferentielException;

    CategoryMenuDTO findCategoryMenuById(Long id);

    CategoryMenuDTO findCategoryMenuByLibelle(String libelle);

    List<CategoryMenuDTO> findAllCategoryMenus();

    void deleteCategoryMenu(Long id);

    /**************   Menu ***********************/
    Long saveMenu(MenuDTO menuDTO) throws ReferentielException;

    Long updateMenu(Long id, MenuDTO menuDTO) throws ReferentielException;

    MenuDTO findMenuById(Long id);

    MenuDTO findMenuByLibelle(String libelle);

    List<MenuDTO> findAllMenus();

    List<MenuDTO> findMenusByCategoryMenu(Long catMenuId);

    void deleteMenu(Long id);
}
