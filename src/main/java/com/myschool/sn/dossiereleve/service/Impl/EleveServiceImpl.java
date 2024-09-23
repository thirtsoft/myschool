package com.myschool.sn.dossiereleve.service.Impl;


import com.myschool.sn.dossiereleve.entity.Eleve;
import com.myschool.sn.dossiereleve.exception.DossierEleveException;
import com.myschool.sn.dossiereleve.mapping.DTOFactoryDossierEl;
import com.myschool.sn.dossiereleve.mapping.ModelFactoryDossierEl;
import com.myschool.sn.dossiereleve.repository.EleveRepository;
import com.myschool.sn.dossiereleve.service.EleveService;
import com.myschool.sn.utils.dtos.dossiereleve.EleveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.NOT_FOUND_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.NULL_OBJECT;

@Service
@RequiredArgsConstructor
public class EleveServiceImpl implements EleveService {

    private final EleveRepository eleveRepository;

    private final DTOFactoryDossierEl dtoFactoryDossierEl;

    private final ModelFactoryDossierEl modelFactoryDossierEl;

    @Override
    public Long saveEleve(EleveDTO eleveDTO) throws DossierEleveException {
        if (eleveDTO == null)
            throw new DossierEleveException(NULL_OBJECT);
        if (eleveDTO.getMatricule() == null || eleveDTO.getMatricule().isEmpty())
            throw new DossierEleveException("Le matricule de l'élève est obligatoire");
        if (eleveDTO.getPrenom() == null || eleveDTO.getPrenom().isEmpty())
            throw new DossierEleveException("Le prénom de l'élève est obligatoire");
        if (eleveDTO.getNom() == null || eleveDTO.getNom().isEmpty())
            throw new DossierEleveException("Le nom de l'élève est obligatoire");
        if (eleveDTO.getSexe() == null || eleveDTO.getSexe().isEmpty())
            throw new DossierEleveException("Le sexe de l'élève est obligatoire");
//        if (eleveDTO.getDateNaissance() == null)
//            throw new DossierEleveException("La date de naissance de l'élève est obligatoire");
        Eleve eleveOptional = eleveRepository.findByMatricule(eleveDTO.getMatricule());
        if (eleveDTO.getId() == null && eleveOptional != null
                || (eleveDTO.getId() != null && eleveOptional != null && !eleveOptional.getId().equals(eleveDTO.getId()))) {
            throw new DossierEleveException(String.format("Le matricule %s est déjà associé à un autre élève  .", eleveDTO.getMatricule()));
        }
        Eleve eleve = modelFactoryDossierEl.createEleve(eleveDTO);
        eleve.setActif(true);
        eleveRepository.save(eleve);
        return eleve.getId();
    }

    @Override
    public Long updateEleve(Long id, EleveDTO eleveDTO) throws DossierEleveException {
        EleveDTO eleveDTOUpdate = findEleveById(id);
        if (eleveDTOUpdate == null) {
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        }
        eleveDTO.setId(id);
        saveEleve(eleveDTO);
        return eleveDTO.getId();
    }

    @Override
    public EleveDTO findEleveById(Long id) {
        if (id == null)
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createEleveDTO(eleveRepository.findEleveById(id));
    }

    @Override
    public EleveDTO findByMatricule(String matricule) {
        if (matricule == null)
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createEleveDTO(eleveRepository.findByMatricule(matricule));
    }

    @Override
    public EleveDTO findEleveByNomOrPrenom(String nom, String prenom) {
        if (nom == null && prenom == null)
            throw new DossierEleveException(NOT_FOUND_OBJECT);
        return dtoFactoryDossierEl.createEleveDTO(eleveRepository.findByNomOrPrenom(nom, prenom));
    }

    @Override
    public List<EleveDTO> findAllEleves() {
        return dtoFactoryDossierEl.createListeEleveDTO(eleveRepository.findAllEleves());
    }

    @Override
    public void deleteEleveDTO(Long id) {
        Eleve eleveDelete = eleveRepository.findEleveById(id);
        eleveDelete.setActif(false);
        eleveRepository.save(eleveDelete);
    }
}
