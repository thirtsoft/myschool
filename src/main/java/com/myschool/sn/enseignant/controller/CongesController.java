package com.myschool.sn.enseignant.controller;

import com.myschool.sn.enseignant.controller.api.CongesApi;
import com.myschool.sn.enseignant.service.EnseignantService;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.enseignant.CongesDTO;
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
public class CongesController implements CongesApi {

    private final EnseignantService congesService;

    @Override
    public List<CongesDTO> getCongess() {
        return congesService.findAllConges();
    }

    @Override
    public List<CongesDTO> getCongessSoumis() {
        return congesService.findAllCongesSoumis();
    }

    @Override
    public List<CongesDTO> getCongessAcceptes() {
        return congesService.findAllCongesAccepte();
    }

    @Override
    public List<CongesDTO> getCongessRejetes() {
        return congesService.findAllCongesRejetes();
    }

    @Override
    public CongesDTO getConges(Long congesId) {
        return congesService.findCongeById(congesId);
    }

    @Override
    public ReponseMessageDTO createConges(CongesDTO congesDTO) {
        try {
            congesService.saveConges(congesDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateConges(Long congesId, CongesDTO congesDTO) {
        try {
            congesService.updateConges(congesId, congesDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO deleteConges(Long congesId) {
        try {
            congesService.deleteConges(congesId);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }
}
