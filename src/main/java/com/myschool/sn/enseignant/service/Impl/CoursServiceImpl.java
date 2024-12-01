package com.myschool.sn.enseignant.service.Impl;

import com.myschool.sn.enseignant.exception.EnseignantException;
import com.myschool.sn.enseignant.mapping.CoursMapper;
import com.myschool.sn.enseignant.repository.CoursRepository;
import com.myschool.sn.enseignant.service.CoursService;
import com.myschool.sn.utils.dtos.enseignant.CoursDTO;
import com.myschool.sn.utils.dtos.enseignant.ListeCoursDTO;
import lombok.RequiredArgsConstructor;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.NOT_FOUND_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.NULL_OBJECT;

@Service
@RequiredArgsConstructor
public class CoursServiceImpl implements CoursService {

    private final CoursRepository coursRepository;

    private final CoursMapper coursMapper;

    @Override
    public void saveCours(CoursDTO coursDTO) throws EnseignantException {
        if (coursDTO == null) {
            throw new EnseignantException(NULL_OBJECT);
        }
        if (coursDTO.getLibelle().isEmpty()) {
            throw new EnseignantException("Le libelle du cours est obligatoire");
        }
        if (coursDTO.getDateDebut() == null) {
            throw new EnseignantException("La date du cours est obligatoire");
        }
        if (coursDTO.getHeureDebut().isEmpty()) {
            throw new EnseignantException("L'heure de début du cours est obligatoire");
        }
        if (coursDTO.getHeureFin().isEmpty()) {
            throw new EnseignantException("L'heure de fin du cours est obligatoire");
        }
        var savedCours = coursMapper.fromCoursDTO(coursDTO);
        savedCours.setActif(true);
        coursRepository.save(savedCours);
    }

    @Override
    public void updateCours(Long id, CoursDTO coursDTO) throws EnseignantException {
        var found = findCoursById(id);
        if (found == null)
            throw new EnseignantException(NOT_FOUND_OBJECT);
        coursDTO.setId(id);
        saveCours(coursDTO);
    }

    @Override
    public CoursDTO findCoursById(Long id) {
        var searchCours = coursRepository.findById(id).orElseThrow(() ->
                new EnseignantException(NOT_FOUND_OBJECT));
        return coursMapper.fromCoursDTO(searchCours);
    }

    @Override
    public List<ListeCoursDTO> findListeCours() {
        return coursRepository.findAllCourss().stream()
                .map(coursMapper::fromListCoursDTO)
                .toList();
    }

    @Override
    public List<ListeCoursDTO> findListeCoursByClasse(Long classeId) {
        return coursRepository.findCoursByClasse(classeId).stream()
                .map(coursMapper::fromListCoursDTO)
                .toList();
    }

    @Override
    public List<ListeCoursDTO> findListeCoursByMatiere(Long matId) {
        return coursRepository.findCoursByMatiere(matId).stream()
                .map(coursMapper::fromListCoursDTO)
                .toList();
    }

    @Override
    public List<ListeCoursDTO> findListeCoursByEnseignant(Long ensId) {
        return coursRepository.findCoursByEnseignant(ensId).stream()
                .map(coursMapper::fromListCoursDTO)
                .toList();
    }

    @Override
    public void deleteConges(Long id) {
        var delete = coursRepository.findById(id).orElseThrow(() -> new EnseignantException(NOT_FOUND_OBJECT));
        delete.setActif(false);
        coursRepository.save(delete);
    }
}
