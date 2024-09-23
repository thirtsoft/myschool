package com.myschool.sn.dossiereleve.controller;

import com.myschool.sn.dossiereleve.controller.api.InscriptionApi;
import com.myschool.sn.dossiereleve.service.InscriptionService;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsInscriptionDTO;
import com.myschool.sn.utils.dtos.dossiereleve.InscriptionDTO;
import com.myschool.sn.utils.dtos.dossiereleve.ListeInscriptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.DELETE_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.ERROR_MESSAGE;
import static com.myschool.sn.utils.MessageValueResponse.FAILED_MESSAGE;
import static com.myschool.sn.utils.MessageValueResponse.SAVED_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.SUCCESS_MESSAGE;

@RestController
@RequiredArgsConstructor
public class InscriptionController implements InscriptionApi {

    private final InscriptionService inscriptionService;

    @Override
    public List<ListeInscriptionDTO> getInscriptions() {
        return inscriptionService.findAllInscriptions();
    }

    @Override
    public List<ListeInscriptionDTO> getInscriptionsByAnneeScolaire(String code) {
        return inscriptionService.findInscriptionsByAnneeScolaire(code);
    }

    @Override
    public DetailsInscriptionDTO getInscription(Long inscriptionId) {
        return inscriptionService.findInscriptionById(inscriptionId);
    }

    @Override
    public ReponseMessageDTO createInscription(InscriptionDTO inscriptionDTO) {
        try {
            inscriptionService.saveInscription(inscriptionDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateInscription(Long inscriptionId, InscriptionDTO inscriptionDTO) {
        try {
            inscriptionService.updateInscription(inscriptionId, inscriptionDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public DetailsInscriptionDTO getInscriptionByCode(String code) {
        return inscriptionService.findInscriptionByCode(code);
    }

    @Override
    public DetailsInscriptionDTO getInscriptionByEleve(String nom, String prenom) {
        return inscriptionService.findInscriptionByEleve(nom, prenom);
    }

    @Override
    public ReponseMessageDTO deleteInscription(Long inscriptionId) {
        try {
            inscriptionService.deleteInscription(inscriptionId);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }
}
