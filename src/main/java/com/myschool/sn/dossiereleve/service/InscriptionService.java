package com.myschool.sn.dossiereleve.service;

import com.myschool.sn.dossiereleve.exception.DossierEleveException;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsInscriptionDTO;
import com.myschool.sn.utils.dtos.dossiereleve.InscriptionDTO;
import com.myschool.sn.utils.dtos.dossiereleve.ListeInscriptionDTO;

import java.util.List;

public interface InscriptionService {

    Long saveInscription(InscriptionDTO inscriptionDTO) throws DossierEleveException;

    Long updateInscription(Long id, InscriptionDTO inscriptionDTO) throws DossierEleveException;

    InscriptionDTO findInscriptionById(Long id);

    DetailsInscriptionDTO findDetailsInscription(Long id);

    DetailsInscriptionDTO findInscriptionByEleve(String nom, String prenom);

    List<ListeInscriptionDTO> findAllInscriptions();

    List<ListeInscriptionDTO> findInscriptionsByAnneeScolaire(String anneeScolaire);

    List<ListeInscriptionDTO> findInscriptionsByEleveID(Long eleveId);

    void deleteInscription(Long id);
}
