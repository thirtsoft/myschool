package com.myschool.sn.enseignant.controller;

import com.myschool.sn.enseignant.controller.api.CongesApi;
import com.myschool.sn.enseignant.service.EnseignantService;
import com.myschool.sn.utils.MessageException;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.enseignant.CongesDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CongesController implements CongesApi {

    private final EnseignantService congesService;

    public CongesController(EnseignantService congesService) {
        this.congesService = congesService;
    }

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
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateConges(Long congesId, CongesDTO congesDTO) {
        try {
            congesService.updateConges(congesId, congesDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO deleteConges(Long congesId) {
        try {
            congesService.deleteConges(congesId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }
}
