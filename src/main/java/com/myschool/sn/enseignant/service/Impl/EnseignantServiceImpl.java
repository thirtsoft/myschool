package com.myschool.sn.enseignant.service.Impl;

import com.myschool.sn.admin.repository.UtilisateurRepository;
import com.myschool.sn.enseignant.entity.Conges;
import com.myschool.sn.enseignant.entity.Enseignant;
import com.myschool.sn.enseignant.exception.EnseignantException;
import com.myschool.sn.enseignant.mapping.DTOFactoryEns;
import com.myschool.sn.enseignant.mapping.ModelFactoryEns;
import com.myschool.sn.enseignant.repository.CongesRepository;
import com.myschool.sn.enseignant.repository.EnseignantRepository;
import com.myschool.sn.enseignant.service.EnseignantService;
import com.myschool.sn.utils.ConstantSigs;
import com.myschool.sn.utils.dtos.enseignant.CongesDTO;
import com.myschool.sn.utils.dtos.enseignant.EnseignantDTO;
import com.myschool.sn.utils.dtos.enseignant.EnseignantListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.NOT_FOUND_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.NULL_OBJECT;

@Service
@RequiredArgsConstructor
public class EnseignantServiceImpl implements EnseignantService {

    private final EnseignantRepository enseignantRepository;

    private final CongesRepository congesRepository;

    private final DTOFactoryEns dtoFactoryEns;

    private final ModelFactoryEns modelFactoryEns;

    private final UtilisateurRepository utilisateurRepository;

    @Override
    public Long saveEnseignant(EnseignantDTO enseignantDTO) throws EnseignantException {
        if (enseignantDTO == null)
            throw new EnseignantException(NOT_FOUND_OBJECT);
        if (enseignantDTO.getNom() == null || enseignantDTO.getNom().isEmpty())
            throw new EnseignantException("Le nom de l'enseignant est obligatoire");
        if (enseignantDTO.getPrenom() == null || enseignantDTO.getPrenom().isEmpty())
            throw new EnseignantException("Le prénom de l'enseignant est obligatoire");
        if (enseignantDTO.getTelephone() == null || enseignantDTO.getTelephone().isEmpty())
            throw new EnseignantException("Le numéro de téléphone est obligatoire");
        if (enseignantDTO.getEmail() == null || enseignantDTO.getEmail().isEmpty())
            throw new EnseignantException("L'émail de l'enseignant est obligatoire");
        EnseignantDTO byMatricule = findByMatricule(enseignantDTO.getMatricule());
        if (enseignantDTO.getId() == null && byMatricule != null
                || (enseignantDTO.getId() != null && byMatricule != null && !byMatricule.getId().equals(enseignantDTO.getId()))) {
            throw new EnseignantException(String.format("Le matricule %s est déjà associé à un autre enseignant  .", enseignantDTO.getMatricule()));
        }
        EnseignantDTO byTelephone = findByTelephone(enseignantDTO.getTelephone());
        if (enseignantDTO.getId() == null && byTelephone != null
                || (enseignantDTO.getId() != null && byTelephone != null && !byTelephone.getId().equals(enseignantDTO.getId()))) {
            throw new EnseignantException(String.format("Le téléphone %s est déjà associé à un autre enseignant  .", enseignantDTO.getTelephone()));
        }
        Enseignant saved = modelFactoryEns.createEnseignant(enseignantDTO);
        saved.setActif(true);
        return saved.getId();
    }

    @Override
    public Long updateEnseignant(Long id, EnseignantDTO enseignantDTO) throws EnseignantException {
        EnseignantDTO foundEnseignant = findEnseignantDTOById(id);
        if (foundEnseignant == null)
            throw new EnseignantException(NOT_FOUND_OBJECT);
        enseignantDTO.setId(id);
        saveEnseignant(enseignantDTO);
        return enseignantDTO.getId();
    }

    @Override
    public EnseignantDTO findEnseignantDTOById(Long id) {
        return dtoFactoryEns.createEnseignantDTO(enseignantRepository.findEnseignantById(id));
    }

    @Override
    public EnseignantDTO findByMatricule(String matricule) {
        return dtoFactoryEns.createEnseignantDTO(enseignantRepository.findByMatricule(matricule));
    }

    @Override
    public EnseignantDTO findByTelephone(String telephone) {
        return dtoFactoryEns.createEnseignantDTO(enseignantRepository.findByTelephone(telephone));
    }

    @Override
    public List<EnseignantDTO> findAllEnseignants() {
        return dtoFactoryEns.createListeEnseignantDTO(enseignantRepository.findAllEnseignants());
    }

    @Override
    public List<EnseignantListDTO> findEnseignantList() {
        return utilisateurRepository.findAllEnseignant().stream()
                .map(dtoFactoryEns::createEnseignantListDTO)
                .toList();
    }

    @Override
    public void deleteEnseignant(Long id) {
        Enseignant deleted = enseignantRepository.findEnseignantById(id);
        deleted.setActif(false);
        enseignantRepository.save(deleted);
    }

}