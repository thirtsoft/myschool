package com.myschool.sn.admin.service;

import com.myschool.sn.utils.dtos.admin.ActionDTO;

import java.util.List;

public interface ActionService {

    void saveAction(ActionDTO action);

    void updateAction(Long id, ActionDTO action);

    ActionDTO findById(Long id);

    List<ActionDTO> findAll();

    void deleteAction(Long id);
}
