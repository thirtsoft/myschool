package com.myschool.sn.dossiereleve.controller;

import com.myschool.sn.dossiereleve.controller.api.PaiementApi;
import com.myschool.sn.dossiereleve.service.PaiementService;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.dossiereleve.PaiementAddDTO;
import com.myschool.sn.utils.dtos.dossiereleve.PaiementDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.DELETE_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.ERROR_MESSAGE;
import static com.myschool.sn.utils.MessageValueResponse.FAILED_MESSAGE;
import static com.myschool.sn.utils.MessageValueResponse.SAVED_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.SUCCESS_MESSAGE;

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
    public ReponseMessageDTO savePaiement(PaiementAddDTO paiementAddDTO) {
        try {
            paiementService.addPay(paiementAddDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updatePaiement(Long paiementId, PaiementDTO paiementDTO) {
        try {
            paiementService.updatePaiement(paiementId, paiementDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
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
            return new ReponseMessageDTO(SUCCESS_MESSAGE, DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }
}
