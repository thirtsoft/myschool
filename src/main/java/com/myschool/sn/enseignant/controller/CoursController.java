package com.myschool.sn.enseignant.controller;

import com.myschool.sn.enseignant.controller.api.CoursApi;
import com.myschool.sn.enseignant.service.CoursService;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.enseignant.CoursDTO;
import com.myschool.sn.utils.dtos.enseignant.ListeCoursDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.DELETE_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.EDIT_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.ERROR_MESSAGE;
import static com.myschool.sn.utils.MessageValueResponse.FAILED_MESSAGE;
import static com.myschool.sn.utils.MessageValueResponse.SAVED_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.SUCCESS_MESSAGE;

@RestController
@RequiredArgsConstructor
public class CoursController implements CoursApi {

    private final CoursService coursService;

    @Override
    public List<ListeCoursDTO> getListCours() {
        return coursService.findListeCours();
    }

    @Override
    public CoursDTO getCours(Long courId) {
        return coursService.findCoursById(courId);
    }

    @Override
    public ReponseMessageDTO createCours(CoursDTO coursDTO) {
        try {
            coursService.saveCours(coursDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateCours(Long courId, CoursDTO coursDTO) {
        try {
            coursService.updateCours(courId, coursDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO deleteCours(Long courId) {
        try {
            coursService.deleteConges(courId);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public List<ListeCoursDTO> getListCoursByClasse(Long classeId) {
        return coursService.findListeCoursByClasse(classeId);
    }

    @Override
    public List<ListeCoursDTO> getListCoursByMatiere(Long matId) {
        return coursService.findListeCoursByMatiere(matId);
    }

    @Override
    public List<ListeCoursDTO> getListCoursByEnseignant(Long ensId) {
        return coursService.findListeCoursByEnseignant(ensId);
    }
}