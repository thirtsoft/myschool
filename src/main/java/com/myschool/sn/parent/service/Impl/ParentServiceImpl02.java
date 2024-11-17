package com.myschool.sn.parent.service.Impl;

import com.myschool.sn.parent.entity.Parent;
import com.myschool.sn.parent.exception.ParentException;
import com.myschool.sn.parent.mapping.ParentMapper;
import com.myschool.sn.parent.repository.ParentRepository;
import com.myschool.sn.parent.service.ParentService;
import com.myschool.sn.referentiel.exception.ReferentielException;
import com.myschool.sn.utils.dtos.parent.ParentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.myschool.sn.utils.MessageValueResponse.NOT_FOUND_OBJECT;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl02 implements ParentService {

    private final ParentRepository parentRepository;
    private final ParentMapper parentMapper;

    @Override
    public Long saveParent(ParentDTO parentDTO) throws ParentException {
        Parent parent = parentMapper.toParent(parentDTO);
        parentRepository.save(parent);
        return parent.getId();
    }

    @Override
    public Long updateParent(Long id, ParentDTO parentDTO) throws ParentException {
        parentRepository.findById(id)
                .orElseThrow(() -> new ReferentielException(NOT_FOUND_OBJECT));
        Parent parent = parentMapper.toParent(parentDTO);
        parentRepository.save(parent);
        return parent.getId();
    }

    @Override
    public ParentDTO findParentById(Long id) {
        Parent model = parentRepository.findById(id).orElseThrow(
                () -> new ReferentielException(NOT_FOUND_OBJECT)
        );
        return parentMapper.toParentDTO(model);
    }

    @Override
    public ParentDTO findParentByUserId(Long userId) {
        Parent parent = parentRepository.findParentByUtilisateurId(userId);
        return parentMapper.toParentDTO(parent);
    }

    @Override
    public List<ParentDTO> findAllParents() {
        return parentMapper.mapListParentDTO(parentRepository.findAllParents());
    }

    @Override
    public void deleteParent(Long id) {
        Parent parent = parentRepository.findById(id).orElseThrow(
                () -> new ReferentielException(NOT_FOUND_OBJECT)
        );
        parent.setActif(false);
        parentRepository.save(parent);
    }
}
