package com.myschool.sn.admin.controller;

import com.myschool.sn.admin.controller.api.ParentApi;
import com.myschool.sn.admin.service.ParentService;
import com.myschool.sn.utils.dtos.parent.ParentDetailsDTO;
import com.myschool.sn.utils.dtos.parent.ParentListeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParentController implements ParentApi {

    private final ParentService parentService;

    @Override
    public ResponseEntity<List<ParentListeDTO>> getAllParents() {
        return new ResponseEntity<>(parentService.findAllParent(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ParentDetailsDTO> getParentDetails(Long parentId) {
        return new ResponseEntity<>(parentService.getDetailsParent(parentId), HttpStatus.OK);
    }

    @Override
    public void deleteParent(Long userId) {
        parentService.deleteParent(userId);
    }
}
