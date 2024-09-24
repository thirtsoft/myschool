package com.myschool.sn.dossiereleve.service;

import com.myschool.sn.dossiereleve.exception.DossierEleveException;
import com.myschool.sn.utils.dtos.dossiereleve.PaiementDTO;

import java.util.List;

public interface TypePaiementService {

    Long savePaiement(PaiementDTO paiementDTO);

    Long updatePaiement(Long id, PaiementDTO paiementDTO) throws DossierEleveException;

    PaiementDTO findPaiementById(Long id);

    PaiementDTO findPaiementByCode(String code);

    List<PaiementDTO> findAllPaiements();

    List<PaiementDTO> findPaiementsByMois(String mois);

    void deletePaiement(Long id);

}
