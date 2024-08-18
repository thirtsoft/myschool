package com.myschool.sn.dossierEleve.controller;

import com.myschool.sn.admin.mapping.DTOFactory;
import com.myschool.sn.admin.mapping.ModelFactory;
import com.myschool.sn.dossierEleve.controller.api.EleveApi;
import com.myschool.sn.dossierEleve.service.EleveService;
import com.myschool.sn.utils.MessageException;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.dossierEleve.EleveDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, e.getMessage());
        }
    }

    @Override
    public ReponseMessageDTO updateEleve(Long eleveId, EleveDTO eleveDTO) {
        try {
            eleveService.updateEleve(eleveId, eleveDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, e.getMessage());
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
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }
}
