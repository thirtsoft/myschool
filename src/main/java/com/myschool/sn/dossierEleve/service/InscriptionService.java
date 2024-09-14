package com.myschool.sn.dossierEleve.service;

import com.myschool.sn.dossierEleve.exception.DossierEleveException;
import com.myschool.sn.utils.dtos.dossierEleve.DetailsInscriptionDTO;
import com.myschool.sn.utils.dtos.dossierEleve.InscriptionDTO;
import com.myschool.sn.utils.dtos.dossierEleve.ListeInscriptionDTO;

import java.util.List;

public interface InscriptionService {

    Long saveInscription(InscriptionDTO inscriptionDTO) throws DossierEleveException;

    Long updateInscription(Long id, InscriptionDTO inscriptionDTO) throws DossierEleveException;

    DetailsInscriptionDTO findInscriptionById(Long id);

    DetailsInscriptionDTO findInscriptionByCode(String code);


    DetailsInscriptionDTO findInscriptionByEleve(String nom, String prenom);

    List<ListeInscriptionDTO> findAllInscriptions();

    List<ListeInscriptionDTO> findInscriptionsByAnneeScolaire(String anneeScolaire);

    void deleteInscription(Long id);

}
