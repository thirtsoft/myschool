package com.myschool.sn.dossiereleve.service.impl;

import com.myschool.sn.dossiereleve.entity.Paiement;
import com.myschool.sn.dossiereleve.exception.DossierEleveException;
import com.myschool.sn.dossiereleve.mapping.DTOFactoryDossierEl;
import com.myschool.sn.dossiereleve.mapping.ModelFactoryDossierEl;
import com.myschool.sn.dossiereleve.repository.PaiementRepository;
import com.myschool.sn.dossiereleve.service.PaiementService;
import com.myschool.sn.referentiel.entity.TypePaiement;
import com.myschool.sn.utils.dtos.dossiereleve.PaiementAddDTO;
import com.myschool.sn.utils.dtos.dossiereleve.PaiementDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.NOT_FOUND_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.NULL_OBJECT;

@Service
@RequiredArgsConstructor
public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepository paiementRepository;

    private final DTOFactoryDossierEl dtoFactoryDossierEl;

    private final ModelFactoryDossierEl modelFactoryDossierEl;

    @Override
    public Long savePaiement(PaiementDTO paiementDTO) throws DossierEleveException {
        if (paiementDTO == null)
            throw new DossierEleveException(NULL_OBJECT);
        if (paiementDTO.getCode() == null || paiementDTO.getCode().isEmpty())
            throw new DossierEleveException("La référence du paiement est obligatoire");
        if (paiementDTO.getEleveDTO() == null)
            throw new DossierEleveException("Le choix d'un élève est obligatoire");
        if (paiementDTO.getMois() == null || paiementDTO.getMois().isEmpty())
            throw new DossierEleveException("Le mois est obligatoire");
        Paiement foundPaiement = paiementRepository.findByCode(paiementDTO.getCode());
        if (paiementDTO.getId() == null && foundPaiement != null
                || (paiementDTO.getId() != null && foundPaiement != null && !foundPaiement.getId().equals(paiementDTO.getId()))) {
            throw new DossierEleveException(String.format("Le code %s est déjà associé à une autre inscription  .", paiementDTO.getCode()));
        }
        Paiement savedPaiement = modelFactoryDossierEl.createPaiement(paiementDTO);
        savedPaiement.setActif(true);
        savedPaiement.setDatePaiement(new Date());
        double montantTotal = 0;
        for (TypePaiement typePaiement : savedPaiement.getTypePaiements()) {
            montantTotal = montantTotal + typePaiement.getMontant();
        }
        savedPaiement.setMontant(montantTotal);
        paiementRepository.save(savedPaiement);
        return savedPaiement.getId();
    }

    @Override
    public Long addPay(PaiementAddDTO paiementAddDTO) throws DossierEleveException {
        Paiement savedPaiement = new Paiement();
        for (PaiementDTO paiementDTO : paiementAddDTO.getPaiements()) {
            if (paiementDTO == null)
                throw new DossierEleveException(NULL_OBJECT);
            if (paiementDTO.getCode() == null || paiementDTO.getCode().isEmpty())
                throw new DossierEleveException("La référence du paiement est obligatoire");
            if (paiementDTO.getEleveDTO() == null)
                throw new DossierEleveException("Le choix d'un élève est obligatoire");
            if (paiementDTO.getMois() == null || paiementDTO.getMois().isEmpty())
                throw new DossierEleveException("Le mois est obligatoire");
            Paiement foundPaiement = paiementRepository.findByCode(paiementDTO.getCode());
            if (paiementDTO.getId() == null && foundPaiement != null
                    || (paiementDTO.getId() != null && foundPaiement != null && !foundPaiement.getId().equals(paiementDTO.getId()))) {
                throw new DossierEleveException(String.format("Le code %s est déjà associé à une autre inscription  .", paiementDTO.getCode()));
            }
            savedPaiement = modelFactoryDossierEl.createPaiement(paiementDTO);
            savedPaiement.setActif(true);
            savedPaiement.setDatePaiement(new Date());
            double montantTotal = 0;
            for (TypePaiement typePaiement : savedPaiement.getTypePaiements()) {
                montantTotal = montantTotal + typePaiement.getMontant();
            }
            savedPaiement.setMontant(montantTotal);
            paiementRepository.save(savedPaiement);
        }
        return savedPaiement.getId();
    }

    @Override
    public Long updatePaiement(Long id, PaiementDTO paiementDTO) throws DossierEleveException {
        Paiement paiement = paiementRepository.findPaiementById(id);
        PaiementDTO paiementDTOUpdate = dtoFactoryDossierEl.createPaiementDTO(paiement);
        if (paiementDTOUpdate == null) {
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        }
        paiementDTO.setId(id);
        savePaiement(paiementDTO);
        return paiementDTO.getId();
    }

    @Override
    public PaiementDTO findPaiementById(Long id) {
        return dtoFactoryDossierEl.createPaiementDTO(paiementRepository.findPaiementById(id));
    }

    @Override
    public PaiementDTO findPaiementByCode(String code) {
        return dtoFactoryDossierEl.createPaiementDTO(paiementRepository.findByCode(code));
    }

    @Override
    public List<PaiementDTO> findAllPaiements() {
        return dtoFactoryDossierEl.createListPaiementDTO(paiementRepository.findAllPaiements());
    }

    @Override
    public List<PaiementDTO> findPaiementsByMois(String mois) {
        return dtoFactoryDossierEl.createListPaiementDTO(paiementRepository.findPaiementsByMois(mois));
    }

    @Override
    public List<PaiementDTO> findPaiementsByEleve(Long eleveId) {
        return paiementRepository.findPaiementsByEleve(eleveId).stream()
                .map(dtoFactoryDossierEl::createPaiementDTO)
                .toList();
    }

    @Override
    public void deletePaiement(Long id) {
        Paiement paiement = paiementRepository.findPaiementById(id);
        paiement.setActif(false);
        paiementRepository.save(paiement);
    }

    @Override
    public long countNombrePaiement() {
        return paiementRepository.countNombrePaiement();
    }

    @Override
    public double getMontantPaiement() {
        return paiementRepository.getMontantPaiement();
    }
}
