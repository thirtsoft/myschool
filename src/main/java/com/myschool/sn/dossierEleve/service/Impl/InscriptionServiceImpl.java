package com.myschool.sn.dossierEleve.service.Impl;

import com.myschool.sn.dossierEleve.entity.Inscription;
import com.myschool.sn.dossierEleve.exception.DossierEleveException;
import com.myschool.sn.dossierEleve.mapping.DTOFactoryDossierEl;
import com.myschool.sn.dossierEleve.mapping.ModelFactoryDossierEl;
import com.myschool.sn.dossierEleve.repository.InscriptionRepository;
import com.myschool.sn.dossierEleve.service.InscriptionService;
import com.myschool.sn.utils.MessageException;
import com.myschool.sn.utils.dtos.dossierEleve.DetailsInscriptionDTO;
import com.myschool.sn.utils.dtos.dossierEleve.InscriptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InscriptionServiceImpl implements InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    private final DTOFactoryDossierEl dtoFactoryDossierEl;

    private final ModelFactoryDossierEl modelFactoryDossierEl;

    @Override
    public Long saveInscription(InscriptionDTO inscriptionDTO) throws DossierEleveException {
        if (inscriptionDTO == null)
            throw new DossierEleveException(MessageException.NULL_OBJECT);
        if (inscriptionDTO.getCode() == null || inscriptionDTO.getCode().isEmpty())
            throw new DossierEleveException("Le code de l'inscription est obligatoire");
        if (inscriptionDTO.getEleveDTO() == null)
            throw new DossierEleveException("L'indentifiant de l'élève est obligatoire");
        if (inscriptionDTO.getMontantInscription() == null || inscriptionDTO.getMontantInscription().isNaN())
            throw new DossierEleveException("Le montant de l'inscription est obligatoire");
//        if (inscriptionDTO.getDateInscription() == null)
//            throw new DossierEleveException("La date de l'inscription est obligatoire");
        Inscription SearchInscription = inscriptionRepository.findByCode(inscriptionDTO.getCode());
        if (inscriptionDTO.getId() == null && SearchInscription != null
                || (inscriptionDTO.getId() != null && SearchInscription != null && !SearchInscription.getId().equals(inscriptionDTO.getId()))) {
            throw new DossierEleveException(String.format("Le code %s est déjà associé à une autre inscription  .", inscriptionDTO.getCode()));
        }
        Inscription saveInscription = modelFactoryDossierEl.createInscription(inscriptionDTO);
        saveInscription.setActif(true);
        inscriptionRepository.save(saveInscription);
        return saveInscription.getId();
    }

    @Override
    public Long updateInscription(Long id, InscriptionDTO inscriptionDTO) throws DossierEleveException {
        Inscription inscription = inscriptionRepository.findInscriptionById(id);
        InscriptionDTO inscriptionDTOUpdate = dtoFactoryDossierEl.createInscriptionDTO(inscription);
        if (inscriptionDTOUpdate == null) {
            throw new DossierEleveException(MessageException.NOT_FOUND_OBJECT);
        }
        inscriptionDTO.setId(id);
        saveInscription(inscriptionDTO);
        return inscriptionDTO.getId();
    }

    @Override
    public DetailsInscriptionDTO findInscriptionById(Long id) {
        if (id == null)
            throw new DossierEleveException(MessageException.NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createDetailsInscriptionDTO(inscriptionRepository.findInscriptionById(id));
    }

    @Override
    public DetailsInscriptionDTO findInscriptionByCode(String code) {
        if (code == null)
            throw new DossierEleveException(MessageException.NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createDetailsInscriptionDTO(inscriptionRepository.findByCode(code));
    }

    @Override
    public DetailsInscriptionDTO findInscriptionByEleve(String nom, String prenom) {
        if (nom == null && prenom == null)
            throw new DossierEleveException(MessageException.NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createDetailsInscriptionDTO(inscriptionRepository.findInscriptionByEleve(nom, prenom));
    }

    @Override
    public List<DetailsInscriptionDTO> findAllInscriptions() {
        return dtoFactoryDossierEl.createListeDetailsInscriptionDTO(inscriptionRepository.findAllInscriptions());
    }

    @Override
    public List<DetailsInscriptionDTO> findInscriptionsByAnneeScolaire(String anneeScolaire) {
        if (anneeScolaire == null)
            throw new DossierEleveException(MessageException.NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createListeDetailsInscriptionDTO(inscriptionRepository.findInscriptionByAnneeScolaire(anneeScolaire));
    }

    @Override
    public void deleteInscription(Long id) {
        Inscription deleted = inscriptionRepository.findInscriptionById(id);
        deleted.setActif(false);
        inscriptionRepository.save(deleted);
    }
}
