package com.myschool.sn.dossierEleve.service;

import com.myschool.sn.dossierEleve.exception.DossierEleveException;
import com.myschool.sn.utils.dtos.dossierEleve.PaiementDTO;
import com.myschool.sn.utils.dtos.dossierEleve.PaiementDTO;

import java.util.List;

public interface PaiementService {

    Long savePaiement(PaiementDTO paiementDTO) throws DossierEleveException;

    Long updatePaiement(Long id, PaiementDTO paiementDTO) throws DossierEleveException;

    PaiementDTO findPaiementById(Long id);

    PaiementDTO findPaiementByCode(String code);

    List<PaiementDTO> findAllPaiements();

    List<PaiementDTO> findPaiementsByMois(String mois);

    void deletePaiement(Long id);

}
