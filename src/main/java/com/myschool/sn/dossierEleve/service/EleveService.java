package com.myschool.sn.dossierEleve.service;

import com.myschool.sn.dossierEleve.exception.DossierEleveException;
import com.myschool.sn.utils.dtos.dossierEleve.EleveDTO;

import java.util.List;

public interface EleveService {

    Long saveEleve(EleveDTO eleveDTO) throws DossierEleveException;

    Long updateEleve(Long id, EleveDTO eleveDTO) throws DossierEleveException;

    EleveDTO findEleveById(Long id);

    EleveDTO findByMatricule(String matricule);

    EleveDTO findEleveByNomOrPrenom(String nom, String prenom);

    List<EleveDTO> findAllEleves();

    void deleteEleveDTO(Long id);

}
