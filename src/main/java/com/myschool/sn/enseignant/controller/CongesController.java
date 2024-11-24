package com.myschool.sn.enseignant.controller;

import com.myschool.sn.enseignant.controller.api.CongesApi;
import com.myschool.sn.enseignant.service.CongesService;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.enseignant.CongesDTO;
import com.myschool.sn.utils.dtos.enseignant.ListeCongesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.ACCEPT_CONGES;
import static com.myschool.sn.utils.MessageValueResponse.DELETE_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.EDIT_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.ERROR_MESSAGE;
import static com.myschool.sn.utils.MessageValueResponse.FAILED_MESSAGE;
import static com.myschool.sn.utils.MessageValueResponse.REJET_CONGES;
import static com.myschool.sn.utils.MessageValueResponse.SAVED_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.SEND_CONGES;
import static com.myschool.sn.utils.MessageValueResponse.SUCCESS_MESSAGE;

@RestController
@RequiredArgsConstructor
public class CongesController implements CongesApi {

    private final CongesService congesService;


    @Override
    public List<ListeCongesDTO> getListCongess() {
        return congesService.findListeConges();
    }

    @Override
    public List<ListeCongesDTO> getListCongessSoumis() {
        return congesService.findListeCongesSoumis();
    }

    @Override
    public List<ListeCongesDTO> getListCongessAcceptes() {
        return congesService.findListeCongesAcceptes();
    }

    @Override
    public List<ListeCongesDTO> getListCongessRejetes() {
        return congesService.findListeCongesRejetes();
    }

    @Override
    public CongesDTO getConges(Long congesId) {
        return congesService.findCongesById(congesId);
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
    public ReponseMessageDTO sendConges(Long congesId) {
        try {
            congesService.sendConges(congesId);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, SEND_CONGES);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO acceptConges(Long congesId) {
        try {
            congesService.acceptConges(congesId);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, ACCEPT_CONGES);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO rejetConges(Long congesId) {
        try {
            congesService.rejetConges(congesId);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, REJET_CONGES);
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
