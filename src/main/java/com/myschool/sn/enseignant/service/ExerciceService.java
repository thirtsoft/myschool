package com.myschool.sn.enseignant.service;

import com.myschool.sn.enseignant.exception.EnseignantException;
import com.myschool.sn.utils.dtos.enseignant.DetailsExerciceDTO;
import com.myschool.sn.utils.dtos.enseignant.ExerciceDTO;
import com.myschool.sn.utils.dtos.enseignant.ListeExerciceDTO;

import java.util.List;

public interface ExerciceService {

    void saveExercice(ExerciceDTO exerciceDTO) throws EnseignantException;

    void updateExercice(Long id, ExerciceDTO exerciceDTO) throws EnseignantException;

    ExerciceDTO findExerciceById(Long id);

    DetailsExerciceDTO findDetailExercice(Long id);

    List<ListeExerciceDTO> findListeExercice();

    List<ListeExerciceDTO> findListeExerciceByEnseignant(Long enseignantId);

    List<ListeExerciceDTO> findListeExerciceByClasse(Long classeId);

    void deleteExercice(Long id);

}
