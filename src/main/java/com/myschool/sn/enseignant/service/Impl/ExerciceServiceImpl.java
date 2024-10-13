package com.myschool.sn.enseignant.service.Impl;

import com.myschool.sn.enseignant.entity.Exercice;
import com.myschool.sn.enseignant.exception.EnseignantException;
import com.myschool.sn.enseignant.mapping.ExerciceMapper;
import com.myschool.sn.enseignant.repository.ExerciceRepository;
import com.myschool.sn.enseignant.service.ExerciceService;
import com.myschool.sn.utils.dtos.enseignant.DetailsExerciceDTO;
import com.myschool.sn.utils.dtos.enseignant.ExerciceDTO;
import com.myschool.sn.utils.dtos.enseignant.ListeExerciceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.NOT_FOUND_OBJECT;

@Service
@RequiredArgsConstructor
public class ExerciceServiceImpl implements ExerciceService {

    private final ExerciceRepository exerciceRepository;
    private final ExerciceMapper exerciceMapper;

    @Override
    public void saveExercice(ExerciceDTO exerciceDTO) throws EnseignantException {
        if (exerciceDTO == null) {
            throw new EnseignantException(NOT_FOUND_OBJECT);
        }
        if (exerciceDTO.getLibelle() == null || exerciceDTO.getLibelle().isEmpty()) {
            throw new EnseignantException("Le libellé de l'exercice est obligatoire");
        }
        if (exerciceDTO.getEnseignantId() == null) {
            throw new EnseignantException("L'identifiant de l'enseignant est obligatoire");
        }
        if (exerciceDTO.getClasseId() == null) {
            throw new EnseignantException("L'identifiant de l'enseignant est obligatoire");
        }
        Exercice exercice = exerciceMapper.toExercieDTO(exerciceDTO);
        exercice.setActif(true);
        exerciceRepository.save(exercice);
    }

    @Override
    public void updateExercice(Long id, ExerciceDTO exerciceDTO) throws EnseignantException {
        ExerciceDTO foundExercice = findExerciceById(id);
        if (foundExercice == null) {
            throw new EnseignantException(NOT_FOUND_OBJECT);
        }
        exerciceDTO.setId(id);
        saveExercice(exerciceDTO);
    }

    @Override
    public ExerciceDTO findExerciceById(Long id) {
        return exerciceMapper.toExercie(exerciceRepository.findExerciceById(id));
    }

    @Override
    public DetailsExerciceDTO findDetailExercice(Long id) {
        return exerciceMapper.fromExercice(exerciceRepository.findExerciceById(id));
    }

    @Override
    public List<ListeExerciceDTO> findListeExercice() {
        return List.of();
    }

    @Override
    public List<ListeExerciceDTO> findListeExerciceByEnseignant(Long enseignantId) {
        return List.of();
    }

    @Override
    public List<ListeExerciceDTO> findListeExerciceByClasse(Long classeId) {
        return List.of();
    }

    @Override
    public void deleteExercice(Long id) {

    }
}
