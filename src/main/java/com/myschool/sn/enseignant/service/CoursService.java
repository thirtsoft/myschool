package com.myschool.sn.enseignant.service;

import com.myschool.sn.enseignant.exception.EnseignantException;
import com.myschool.sn.utils.dtos.enseignant.CoursDTO;
import com.myschool.sn.utils.dtos.enseignant.ListeCoursDTO;

import java.util.List;

public interface CoursService {

    void saveCours(CoursDTO coursDTO) throws EnseignantException;

    void updateCours(Long id, CoursDTO coursDTO) throws EnseignantException;

    CoursDTO findCoursById(Long id);

    List<ListeCoursDTO> findListeCours();

    List<ListeCoursDTO> findListeCoursByClasse(Long classeId);

    List<ListeCoursDTO> findListeCoursByMatiere(Long matId);

    List<ListeCoursDTO> findListeCoursByEnseignant(Long ensId);

    void deleteConges(Long id);
}
