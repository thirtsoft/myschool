package com.myschool.sn.enseignant.controller;

import com.myschool.sn.enseignant.controller.api.ExerciceApi;
import com.myschool.sn.enseignant.service.ExerciceService;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.enseignant.ExerciceDTO;
import com.myschool.sn.utils.dtos.enseignant.ListeExerciceDTO;
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
public class ExerciceController implements ExerciceApi {

    private final ExerciceService exerciceService;

    @Override
    public List<ListeExerciceDTO> getListeExercices() {
        return exerciceService.findListeExercice();
    }

    @Override
    public ExerciceDTO getExerciceDTO(Long exerciceId) {
        return exerciceService.findExerciceById(exerciceId);
    }

    @Override
    public ReponseMessageDTO createExercice(ExerciceDTO exerciceDTO) {
        try {
            exerciceService.saveExercice(exerciceDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateEnseignant(Long exerciceId, ExerciceDTO exerciceDTO) {
        try {
            exerciceService.updateExercice(exerciceId, exerciceDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public List<ListeExerciceDTO> getListeExercicesByClasse(Long classeId) {
        return exerciceService.findListeExerciceByClasse(classeId);
    }

    @Override
    public List<ListeExerciceDTO> getListeExercicesByEnseignant(Long enseignantId) {
        return exerciceService.findListeExerciceByEnseignant(enseignantId);
    }

    @Override
    public ReponseMessageDTO deleteExercice(Long exerciceId) {
        try {
            exerciceService.deleteExercice(exerciceId);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }
}
