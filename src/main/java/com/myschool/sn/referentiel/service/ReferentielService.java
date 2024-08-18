package com.myschool.sn.referentiel.service;

import com.myschool.sn.referentiel.exception.ReferentielException;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;
import com.myschool.sn.utils.dtos.referentiel.BatimentDTO;
import com.myschool.sn.utils.dtos.referentiel.ClasseDTO;
import com.myschool.sn.utils.dtos.referentiel.MatiereDTO;
import com.myschool.sn.utils.dtos.referentiel.SalleDTO;

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
}
