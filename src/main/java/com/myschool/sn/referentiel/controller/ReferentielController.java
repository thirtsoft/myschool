package com.myschool.sn.referentiel.controller;

import com.myschool.sn.referentiel.controller.api.ReferentielApi;
import com.myschool.sn.referentiel.service.ReferentielService;
import com.myschool.sn.utils.MessageException;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReferentielController implements ReferentielApi {

    private final ReferentielService referentielService;

    public ReferentielController(ReferentielService referentielService) {
        this.referentielService = referentielService;
    }

    /************           AnneeScolaire  *******************/
    @Override
    public List<AnneeScolaireDTO> getAnneeScolaires() {
        return referentielService.findAllAnneeScolaires();
    }

    @Override
    public AnneeScolaireDTO getAnneeScolaire(Long anneeScolaireId) {
        return referentielService.findAnneeScolaireDTOById(anneeScolaireId);
    }

    @Override
    public ReponseMessageDTO createAnneeScolaire(AnneeScolaireDTO anneeScolaireDTO) {
        try {
            referentielService.saveAnneeScolaire(anneeScolaireDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateAnneeScolaire(Long anneeScolaireId, AnneeScolaireDTO anneeScolaireDTO) {
        try {
            referentielService.updateAnneeScolaire(anneeScolaireId, anneeScolaireDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public AnneeScolaireDTO getAnneeScolaireByCode(String code) {
        return referentielService.findByCode(code);
    }

    @Override
    public ReponseMessageDTO deleteAnneeScolaire(Long anneeScolaireId) {
        try {
            referentielService.deleteAnneeScolaire(anneeScolaireId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }
}
