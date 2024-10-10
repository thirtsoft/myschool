package com.myschool.sn.dossiereleve.controller;

import com.myschool.sn.dossiereleve.controller.api.EleveApi;
import com.myschool.sn.dossiereleve.mapping.DTOFactoryDossierEl;
import com.myschool.sn.dossiereleve.mapping.ModelFactoryDossierEl;
import com.myschool.sn.dossiereleve.message.ResponseEleveDTO;
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

    private final DTOFactoryDossierEl dtoFactoryDossierEl;

    private final ModelFactoryDossierEl modelFactoryDossierEl;

    public EleveController(EleveService eleveService, DTOFactoryDossierEl dtoFactoryDossierEl, ModelFactoryDossierEl modelFactoryDossierEl) {
        this.eleveService = eleveService;
        this.dtoFactoryDossierEl = dtoFactoryDossierEl;
        this.modelFactoryDossierEl = modelFactoryDossierEl;
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
    public ResponseEleveDTO createOrUpdateEleve(EleveDTO eleveDTO) {
        try {
            Long saved = eleveService.saveEleve(eleveDTO);
            return new ResponseEleveDTO(SUCCESS_MESSAGE, SAVED_OBJECT, saved);
        } catch (Exception e) {
            return new ResponseEleveDTO(FAILED_MESSAGE, e.getMessage(), null);
        }
    }

    @Override
    public ResponseEleveDTO updateEleve(Long eleveId, EleveDTO eleveDTO) {
        try {
            Long saved = eleveService.updateEleve(eleveId, eleveDTO);
            return new ResponseEleveDTO(SUCCESS_MESSAGE, SAVED_OBJECT, saved);
        } catch (Exception e) {
            return new ResponseEleveDTO(FAILED_MESSAGE, e.getMessage(), null);
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
