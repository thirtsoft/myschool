package com.myschool.sn.parent.controller;

import com.myschool.sn.parent.controller.api.ParentApi;
import com.myschool.sn.parent.service.ParentService;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.parent.ParentDTO;
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
public class ParentController02 implements ParentApi {

    private final ParentService parentService;

    @Override
    public List<ParentDTO> getAllParents() {
        return parentService.findAllParents();
    }

    @Override
    public ParentDTO getParent(Long parentId) {
        return parentService.findParentById(parentId);
    }

    @Override
    public ParentDTO getParentByUser(Long userId) {
        return parentService.findParentByUserId(userId);
    }

    @Override
    public ReponseMessageDTO createOrUpdateParent(ParentDTO parentDTO) {
        try {
            parentService.saveParent(parentDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateParent(Long parentId, ParentDTO parentDTO) {
        try {
            parentService.updateParent(parentId, parentDTO);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO deleteParent(Long parentId) {
        try {
            parentService.deleteParent(parentId);
            return new ReponseMessageDTO(SUCCESS_MESSAGE, DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(FAILED_MESSAGE, ERROR_MESSAGE);
        }
    }
}
