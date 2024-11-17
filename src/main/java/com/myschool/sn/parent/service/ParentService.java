package com.myschool.sn.parent.service;

import com.myschool.sn.parent.exception.ParentException;
import com.myschool.sn.utils.dtos.parent.ParentDTO;

import java.util.List;

public interface ParentService {

    Long saveParent(ParentDTO parentDTO) throws ParentException;

    Long updateParent(Long id, ParentDTO parentDTO) throws ParentException;

    ParentDTO findParentById(Long id);

    ParentDTO findParentByUserId(Long userId);

    List<ParentDTO> findAllParents();

    void deleteParent(Long id);
}
