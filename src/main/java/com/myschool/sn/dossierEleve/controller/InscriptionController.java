package com.myschool.sn.dossierEleve.controller;

import com.myschool.sn.dossierEleve.controller.api.InscriptionApi;
import com.myschool.sn.dossierEleve.service.InscriptionService;
import com.myschool.sn.utils.MessageException;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.dossierEleve.DetailsInscriptionDTO;
import com.myschool.sn.utils.dtos.dossierEleve.InscriptionDTO;
import com.myschool.sn.utils.dtos.dossierEleve.ListeInscriptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateInscription(Long inscriptionId, InscriptionDTO inscriptionDTO) {
        try {
            inscriptionService.updateInscription(inscriptionId, inscriptionDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
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
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }
}
