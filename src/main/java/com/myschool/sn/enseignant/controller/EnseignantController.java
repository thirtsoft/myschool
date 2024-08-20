package com.myschool.sn.enseignant.controller;

import com.myschool.sn.enseignant.controller.api.EnseignantApi;
import com.myschool.sn.enseignant.service.EnseignantService;
import com.myschool.sn.utils.MessageException;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.enseignant.EnseignantDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EnseignantController implements EnseignantApi {

    private final EnseignantService enseignantService;

    public EnseignantController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

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
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateEnseignant(Long enseignantId, EnseignantDTO enseignantDTO) {
        try {
            enseignantService.updateEnseignant(enseignantId, enseignantDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
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
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }
}
