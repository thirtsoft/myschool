package com.myschool.sn.enseignant.controller;

import com.myschool.sn.enseignant.controller.api.EnseignantApi;
import com.myschool.sn.enseignant.service.EnseignantService;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.enseignant.EnseignantDTO;
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
public class EnseignantController implements EnseignantApi {

    private final EnseignantService enseignantService;

    @Override
    public List<EnseignantDTO> getEnseignants() {
        return enseignantService.findAllEnseignants();
    }

    @Override
    public EnseignantDTO getEnseignant(Long enseignantId) {
        return enseignantService.findEnseignantDTOById(enseignantId);
    }

    @Override
    public ReponseMessageDTO createEnseignant(EnseignantDTO enseignantDTO) {
        try {
            enseignantService.saveEnseignant(enseignantDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateEnseignant(Long enseignantId, EnseignantDTO enseignantDTO) {
        try {
            enseignantService.updateEnseignant(enseignantId, enseignantDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public EnseignantDTO getEnseignantByMatricule(String matricule) {
        return enseignantService.findByMatricule(matricule);
    }

    @Override
    public EnseignantDTO getEnseignantByTelephone(String telephone) {
        return enseignantService.findByTelephone(telephone);
    }

    @Override
    public ReponseMessageDTO deleteEnseignant(Long enseignantId) {
        try {
            enseignantService.deleteEnseignant(enseignantId);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }
}
