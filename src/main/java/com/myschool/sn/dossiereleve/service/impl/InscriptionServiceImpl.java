package com.myschool.sn.dossiereleve.service.impl;

import com.myschool.sn.dossiereleve.entity.Inscription;
import com.myschool.sn.dossiereleve.exception.DossierEleveException;
import com.myschool.sn.dossiereleve.mapping.DTOFactoryDossierEl;
import com.myschool.sn.dossiereleve.mapping.ModelFactoryDossierEl;
import com.myschool.sn.dossiereleve.repository.InscriptionRepository;
import com.myschool.sn.dossiereleve.service.InscriptionService;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsInscriptionDTO;
import com.myschool.sn.utils.dtos.dossiereleve.InscriptionDTO;
import com.myschool.sn.utils.dtos.dossiereleve.ListeInscriptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.NOT_FOUND_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.NULL_OBJECT;

@Service
@RequiredArgsConstructor
public class InscriptionServiceImpl implements InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    private final DTOFactoryDossierEl dtoFactoryDossierEl;

    private final ModelFactoryDossierEl modelFactoryDossierEl;

    @Override
    public Long saveInscription(InscriptionDTO inscriptionDTO) throws DossierEleveException {
        if (inscriptionDTO == null)
            throw new DossierEleveException(NULL_OBJECT);
        if (inscriptionDTO.getCode() == null || inscriptionDTO.getCode().isEmpty())
            throw new DossierEleveException("Le code de l'inscription est obligatoire");
        if (inscriptionDTO.getEleveDTO() == null)
            throw new DossierEleveException("L'indentifiant de l'élève est obligatoire");
        if (inscriptionDTO.getMontantInscription() == null || inscriptionDTO.getMontantInscription().isNaN())
            throw new DossierEleveException("Le montant de l'inscription est obligatoire");
        if (inscriptionDTO.getAnneeScolaireDebutDTO() == null)
            throw new DossierEleveException("L'année scolaire de début est obligatoire");
        if (inscriptionDTO.getAnneeScolaireFinDTO() == null)
            throw new DossierEleveException("L'année scolaire de fin est obligatoire");
        if (inscriptionDTO.getClasseDTO() == null)
            throw new DossierEleveException("La classe d'inscription est obligatoire");
        Inscription SearchInscription = inscriptionRepository.findByCode(inscriptionDTO.getCode());
        if (inscriptionDTO.getId() == null && SearchInscription != null
                || (inscriptionDTO.getId() != null && SearchInscription != null && !SearchInscription.getId().equals(inscriptionDTO.getId()))) {
            throw new DossierEleveException(String.format("Le code %s est déjà associé à une autre inscription  .", inscriptionDTO.getCode()));
        }
        Inscription saveInscription = modelFactoryDossierEl.createInscription(inscriptionDTO);
        saveInscription.setActif(true);
        saveInscription.setDateInscription(new Date());
        inscriptionRepository.save(saveInscription);
        return saveInscription.getId();
    }

    @Override
    public Long updateInscription(Long id, InscriptionDTO inscriptionDTO) throws DossierEleveException {
        Inscription inscription = inscriptionRepository.findInscriptionById(id);
        InscriptionDTO inscriptionDTOUpdate = dtoFactoryDossierEl.createInscriptionDTO(inscription);
        if (inscriptionDTOUpdate == null) {
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        }
        inscriptionDTO.setId(id);
        saveInscription(inscriptionDTO);
        return inscriptionDTO.getId();
    }

    @Override
    public InscriptionDTO findInscriptionById(Long id) {
        if (id == null)
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createInscriptionDTO(inscriptionRepository.findInscriptionById(id));
    }

    @Override
    public DetailsInscriptionDTO findDetailsInscription(Long id) {
        if (id == null)
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createDetailsInscriptionDTO(inscriptionRepository.findInscriptionById(id));
    }

    @Override
    public DetailsInscriptionDTO findInscriptionByEleve(String nom, String prenom) {
        if (nom == null && prenom == null)
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createDetailsInscriptionDTO(inscriptionRepository.findInscriptionByEleve(nom, prenom));
    }

    @Override
    public List<ListeInscriptionDTO> findAllInscriptions() {
        return dtoFactoryDossierEl.createListeInscriptionDTO(inscriptionRepository.findAllInscriptions());
    }

    @Override
    public List<ListeInscriptionDTO> findInscriptionsByAnneeScolaire(String anneeScolaire) {
        if (anneeScolaire == null)
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createListeInscriptionDTO(inscriptionRepository.findInscriptionByAnneeScolaire(anneeScolaire));
    }

    @Override
    public List<ListeInscriptionDTO> findInscriptionsByEleveID(Long eleveId) {
        if (eleveId == null)
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createListeInscriptionDTO(inscriptionRepository.findListInscriptionByEleveId(eleveId));
    }

    @Override
    public void deleteInscription(Long id) {
        Inscription deleted = inscriptionRepository.findInscriptionById(id);
        deleted.setActif(false);
        inscriptionRepository.save(deleted);
    }

}
