package com.myschool.sn.dossiereleve.service;

import com.myschool.sn.dossiereleve.exception.DossierEleveException;
import com.myschool.sn.utils.dtos.dossiereleve.PaiementAddDTO;
import com.myschool.sn.utils.dtos.dossiereleve.PaiementDTO;

import java.util.List;

public interface PaiementService {

    Long savePaiement(PaiementDTO paiementDTO) throws DossierEleveException;

    Long addPay(PaiementAddDTO paiementAddDTO) throws DossierEleveException;

    Long updatePaiement(Long id, PaiementDTO paiementDTO) throws DossierEleveException;

    PaiementDTO findPaiementById(Long id);

    PaiementDTO findPaiementByCode(String code);

    List<PaiementDTO> findAllPaiements();

    List<PaiementDTO> findPaiementsByMois(String mois);

    List<PaiementDTO> findPaiementsByEleve(Long eleveId);

    void deletePaiement(Long id);

}
