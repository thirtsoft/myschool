package com.myschool.sn.dossierEleve.controller;

import com.myschool.sn.dossierEleve.controller.api.PaiementApi;
import com.myschool.sn.dossierEleve.service.PaiementService;
import com.myschool.sn.utils.MessageException;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.dossierEleve.PaiementDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaiementController implements PaiementApi {

    private final PaiementService paiementService;

    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @Override
    public List<PaiementDTO> getPaiements() {
        return paiementService.findAllPaiements();
    }

    @Override
    public List<PaiementDTO> getPaiementsByMois(String mois) {
        return paiementService.findPaiementsByMois(mois);
    }

    @Override
    public PaiementDTO getPaiement(Long paiementId) {
        return paiementService.findPaiementById(paiementId);
    }

    @Override
    public ReponseMessageDTO createPaiement(PaiementDTO paiementDTO) {
        try {
            paiementService.savePaiement(paiementDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updatePaiement(Long paiementId, PaiementDTO paiementDTO) {
        try {
            paiementService.updatePaiement(paiementId, paiementDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public PaiementDTO getPaiementByCode(String code) {
        return paiementService.findPaiementByCode(code);
    }

    @Override
    public ReponseMessageDTO deletePaiement(Long paiementId) {
        try {
            paiementService.deletePaiement(paiementId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }
}
