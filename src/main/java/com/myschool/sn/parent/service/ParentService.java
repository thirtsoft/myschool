package com.myschool.sn.parent.service;

import com.myschool.sn.utils.dtos.parent.ParentDetailsDTO;
import com.myschool.sn.utils.dtos.parent.ParentListeDTO;

import java.util.List;

public interface ParentService {

    List<ParentListeDTO> findAllParent();

    ParentDetailsDTO getDetailsParent(Long parentId);

    void deleteParent(Long parentId);

}
