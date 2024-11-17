package com.myschool.sn.dossiereleve.service;

import com.myschool.sn.dossiereleve.exception.DossierEleveException;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsEleveDTO;
import com.myschool.sn.utils.dtos.dossiereleve.EleveDTO;
import com.myschool.sn.utils.dtos.dossiereleve.EleveRequestDTO;

import java.util.List;

public interface EleveService {

    Long saveEleve(EleveDTO eleveDTO) throws DossierEleveException;

    Long updateEleve(Long id, EleveDTO eleveDTO) throws DossierEleveException;

    EleveDTO findEleveById(Long id);

    EleveDTO findByMatricule(String matricule);

    EleveDTO findEleveByNomOrPrenom(String nom, String prenom);

    List<EleveDTO> findAllEleves();

    void deleteEleveDTO(Long id);

//    Long saveEleveRequest(EleveRequestDTO eleveRequestDTO) throws DossierEleveException;

    Long saveEleveRequest(EleveDTO eleveDTO) throws DossierEleveException;

    Long savedStudent(EleveDTO eleveDTO) throws DossierEleveException;

    Long updateEleveRequest(Long id, EleveRequestDTO eleveRequestDTO) throws DossierEleveException;

    DetailsEleveDTO findDetailEleve(Long studentId);

    long countNombreEleve();
}
