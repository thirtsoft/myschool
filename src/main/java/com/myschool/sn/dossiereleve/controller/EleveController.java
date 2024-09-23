package com.myschool.sn.dossiereleve.controller;

import com.myschool.sn.dossiereleve.controller.api.EleveApi;
import com.myschool.sn.dossiereleve.service.EleveService;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.dossiereleve.EleveDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.DELETE_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.ERROR_MESSAGE;
import static com.myschool.sn.utils.MessageValueResponse.FAILED_MESSAGE;
import static com.myschool.sn.utils.MessageValueResponse.SAVED_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.SUCCESS_MESSAGE;

@RestController
public class EleveController implements EleveApi {

    private final EleveService eleveService;

    public EleveController(EleveService eleveService) {
        this.eleveService = eleveService;
    }

    @Override
    public List<EleveDTO> getEleves() {
        return eleveService.findAllEleves();
    }

    @Override
    public EleveDTO getEleve(Long eleveId) {
        return eleveService.findEleveById(eleveId);
    }

    @Override
    public ReponseMessageDTO createOrUpdateEleve(EleveDTO eleveDTO) {
        try {
            eleveService.saveEleve(eleveDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, e.getMessage());
        }
    }

    @Override
    public ReponseMessageDTO updateEleve(Long eleveId, EleveDTO eleveDTO) {
        try {
            eleveService.updateEleve(eleveId, eleveDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, e.getMessage());
        }
    }

    @Override
    public EleveDTO getEleveByMatricule(String matricule) {
        return eleveService.findByMatricule(matricule);
    }

    @Override
    public EleveDTO getEleveByNomOrPrenom(String nom, String prenom) {
        return eleveService.findEleveByNomOrPrenom(nom, prenom);
    }

    @Override
    public ReponseMessageDTO deleteEleve(Long eleveId) {
        try {
            eleveService.deleteEleveDTO(eleveId);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }
}
