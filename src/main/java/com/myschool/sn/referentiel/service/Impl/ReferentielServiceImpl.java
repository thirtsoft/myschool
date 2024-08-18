package com.myschool.sn.referentiel.service.Impl;

import com.myschool.sn.dossierEleve.exception.DossierEleveException;
import com.myschool.sn.referentiel.entity.AnneeScolaire;
import com.myschool.sn.referentiel.exception.ReferentielException;
import com.myschool.sn.referentiel.mapping.DTOFactoryRef;
import com.myschool.sn.referentiel.mapping.ModelFactoryRef;
import com.myschool.sn.referentiel.repository.AnneeScolaireRepository;
import com.myschool.sn.referentiel.service.ReferentielService;
import com.myschool.sn.utils.MessageException;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferentielServiceImpl implements ReferentielService {

    private final AnneeScolaireRepository anneeScolaireRepository;

    private final DTOFactoryRef dtoFactoryRef;

    private final ModelFactoryRef modelFactoryRef;

    public ReferentielServiceImpl(AnneeScolaireRepository anneeScolaireRepository,
                                  DTOFactoryRef dtoFactoryRef,
                                  ModelFactoryRef modelFactoryRef) {
        this.anneeScolaireRepository = anneeScolaireRepository;
        this.dtoFactoryRef = dtoFactoryRef;
        this.modelFactoryRef = modelFactoryRef;
    }

    @Override
    public Long saveAnneeScolaire(AnneeScolaireDTO anneeScolaireDTO) throws ReferentielException {
        if (anneeScolaireDTO == null)
            throw new ReferentielException(MessageException.NULL_OBJECT);
        if (anneeScolaireDTO.getCode() == null || anneeScolaireDTO.getCode().isEmpty())
            throw new ReferentielException("Le code est obligatoire");
        if (anneeScolaireDTO.getLibelle() == null || anneeScolaireDTO.getLibelle().isEmpty())
            throw new ReferentielException("Le libelle est obligatoire");
        AnneeScolaire anneeScolaire = anneeScolaireRepository.findByCode(anneeScolaireDTO.getCode());
        if (anneeScolaireDTO.getId() == null && anneeScolaire != null
                || (anneeScolaireDTO.getId() != null && anneeScolaire != null && !anneeScolaire.getId().equals(anneeScolaireDTO.getId()))) {
            throw new DossierEleveException(String.format("Le code %s est déjà associé à une autre année scolaire  .", anneeScolaireDTO.getCode()));
        }
        AnneeScolaire saveAnneeScolaire = modelFactoryRef.createAnneeScolaire(anneeScolaireDTO);
        saveAnneeScolaire.setActif(true);
        anneeScolaireRepository.save(saveAnneeScolaire);
        return saveAnneeScolaire.getId();
    }

    @Override
    public Long updateAnneeScolaire(Long id, AnneeScolaireDTO anneeScolaireDTO) throws ReferentielException {
        AnneeScolaireDTO anneeScolaireDTODTOUpdate = findAnneeScolaireDTOById(id);
        if (anneeScolaireDTODTOUpdate == null) {
            throw new ReferentielException(MessageException.NOT_FOUND_OBJECT);
        }
        anneeScolaireDTO.setId(id);
        saveAnneeScolaire(anneeScolaireDTO);
        return anneeScolaireDTO.getId();
    }

    @Override
    public AnneeScolaireDTO findAnneeScolaireDTOById(Long id) {
        if (id == null)
            throw new ReferentielException(MessageException.NOT_FOUND_OBJECT);
        return dtoFactoryRef.createAnneeScolaireDTO(anneeScolaireRepository.findAnneeScolaireById(id));
    }

    @Override
    public AnneeScolaireDTO findByCode(String code) {
        if (code == null)
            throw new ReferentielException(MessageException.NOT_FOUND_OBJECT);
        return dtoFactoryRef.createAnneeScolaireDTO(anneeScolaireRepository.findByCode(code));
    }

    @Override
    public List<AnneeScolaireDTO> findAllAnneeScolaires() {
        return dtoFactoryRef.createListeAnneeScolaireDTO(anneeScolaireRepository.findAllAnneeScolaires());
    }

    @Override
    public void deleteAnneeScolaire(Long id) {
        AnneeScolaire deleted = anneeScolaireRepository.findAnneeScolaireById(id);
        deleted.setActif(false);
        anneeScolaireRepository.save(deleted);
    }
}
