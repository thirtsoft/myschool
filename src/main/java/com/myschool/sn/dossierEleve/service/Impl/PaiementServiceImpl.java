package com.myschool.sn.dossierEleve.service.Impl;

import com.myschool.sn.dossierEleve.entity.Inscription;
import com.myschool.sn.dossierEleve.entity.Paiement;
import com.myschool.sn.dossierEleve.exception.DossierEleveException;
import com.myschool.sn.dossierEleve.mapping.DTOFactoryDossierEl;
import com.myschool.sn.dossierEleve.mapping.ModelFactoryDossierEl;
import com.myschool.sn.dossierEleve.repository.PaiementRepository;
import com.myschool.sn.dossierEleve.service.PaiementService;
import com.myschool.sn.utils.MessageException;
import com.myschool.sn.utils.dtos.dossierEleve.InscriptionDTO;
import com.myschool.sn.utils.dtos.dossierEleve.PaiementDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepository paiementRepository;

    private final DTOFactoryDossierEl dtoFactoryDossierEl;

    private final ModelFactoryDossierEl modelFactoryDossierEl;

    public PaiementServiceImpl(PaiementRepository paiementRepository, DTOFactoryDossierEl dtoFactoryDossierEl, ModelFactoryDossierEl modelFactoryDossierEl) {
        this.paiementRepository = paiementRepository;
        this.dtoFactoryDossierEl = dtoFactoryDossierEl;
        this.modelFactoryDossierEl = modelFactoryDossierEl;
    }

    @Override
    public Long savePaiement(PaiementDTO paiementDTO) throws DossierEleveException {
        if (paiementDTO == null)
            throw new DossierEleveException(MessageException.NULL_OBJECT);
        if (paiementDTO.getCode() == null || paiementDTO.getCode().isEmpty())
            throw new DossierEleveException("La référence du paiement est obligatoire");
        if (paiementDTO.getEleveDTO().getId() == null)
            throw new DossierEleveException("Le choix d'un élève est obligatoire");
        if (paiementDTO.getMois() == null || paiementDTO.getMois().isEmpty())
            throw new DossierEleveException("Le mois est obligatoire");
        if (paiementDTO.getMontant() == null)
            throw new DossierEleveException("Le montant payée est obligatoire");
        Paiement foundPaiement = paiementRepository.findByCode(paiementDTO.getCode());
        if (paiementDTO.getId() == null && foundPaiement != null
                || (paiementDTO.getId() != null && foundPaiement != null && !foundPaiement.getId().equals(paiementDTO.getId()))) {
            throw new DossierEleveException(String.format("Le code %s est déjà associé à une autre inscription  .", paiementDTO.getCode()));
        }
        Paiement savedPaiement = modelFactoryDossierEl.createPaiement(paiementDTO);
        savedPaiement.setActif(true);
        paiementRepository.save(savedPaiement);
        return savedPaiement.getId();
    }

    @Override
    public Long updatePaiement(Long id, PaiementDTO paiementDTO) throws DossierEleveException {
        Paiement paiement = paiementRepository.findPaiementById(id);
        PaiementDTO paiementDTOUpdate = dtoFactoryDossierEl.createPaiementDTO(paiement);
        if (paiementDTOUpdate == null) {
            throw new DossierEleveException(MessageException.NOT_FOUND_OBJECT);
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
        return dtoFactoryDossierEl.createPaiementDTO(
                paiementRepository.findByCode(code)
        );
    }

    @Override
    public List<PaiementDTO> findAllPaiements() {
        return dtoFactoryDossierEl.createListePaiementDTO(
                paiementRepository.findAllPaiements()
        );
    }

    @Override
    public List<PaiementDTO> findPaiementsByMois(String mois) {
        return dtoFactoryDossierEl.createListePaiementDTO(
                paiementRepository.findPaiementsByMois(mois)
        );
    }

    @Override
    public void deletePaiement(Long id) {
        Paiement paiement = paiementRepository.findPaiementById(id);
        paiement.setActif(false);
        paiementRepository.save(paiement);
    }
}
