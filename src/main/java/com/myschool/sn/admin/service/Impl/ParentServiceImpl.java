package com.myschool.sn.admin.service.Impl;

import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.admin.exception.ResourceNotFoundException;
import com.myschool.sn.admin.mapping.DTOFactory;
import com.myschool.sn.admin.repository.UtilisateurRepository;
import com.myschool.sn.admin.service.ParentService;
import com.myschool.sn.utils.dtos.parent.ParentDetailsDTO;
import com.myschool.sn.utils.dtos.parent.ParentListeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private static final String USER_NOT_FOUND = "User with id {0} not found";

    private final UtilisateurRepository parentRepository;

    private final DTOFactory dtoFactory;

    @Override
    public List<ParentListeDTO> findAllParent() {
        return parentRepository.findAllParent().stream()
                .map(dtoFactory::createParentListeDTO)
                .toList();
    }


    @Override
    public ParentDetailsDTO getDetailsParent(Long parentId) {
        return dtoFactory.createParentDetailsDTO(
                parentRepository.findById(parentId).orElseThrow(() ->
                        new
                                ResourceNotFoundException(MessageFormat.format(USER_NOT_FOUND, parentId))));
    }

    @Override
    public void deleteParent(Long parentId) {
        Utilisateur parentDelete = parentRepository.findUtilisateurById(parentId);
        parentDelete.setActif(false);
        parentRepository.save(parentDelete);
    }
}
