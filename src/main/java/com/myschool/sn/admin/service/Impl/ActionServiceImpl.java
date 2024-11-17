package com.myschool.sn.admin.service.Impl;

import com.myschool.sn.admin.entity.Action;
import com.myschool.sn.admin.mapping.DTOFactory;
import com.myschool.sn.admin.mapping.ModelFactory;
import com.myschool.sn.admin.repository.ActionRepository;
import com.myschool.sn.admin.service.ActionService;
import com.myschool.sn.utils.dtos.admin.ActionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.myschool.sn.utils.MessageValueResponse.NOT_FOUND_OBJECT;
import static com.myschool.sn.utils.MessageValueResponse.NULL_OBJECT;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;

    private final ModelFactory modelFactory;

    private final DTOFactory dtoFactory;

    @Override
    public void saveAction(ActionDTO action) {
        if (action == null) {
            throw new RuntimeException(NULL_OBJECT);
        }
        if (action.getLibelle() == null || action.getLibelle().isEmpty()) {
            throw new RuntimeException("Le libelle est obligatoire");
        }
        Action foundAction = actionRepository.findById(action.getId()).orElse(null);
        assert foundAction != null;
        if (Objects.equals(foundAction.getLibelle(), action.getLibelle())) {
            throw new RuntimeException("Ce libellé existe déjà");
        }
        Action savedAction = modelFactory.createAction(action);
        savedAction.setActif(true);
        actionRepository.save(savedAction);
    }

    @Override
    public void updateAction(Long id, ActionDTO action) {
        ActionDTO searchAction = findById(id);
        if (searchAction == null) {
            throw new RuntimeException(NOT_FOUND_OBJECT);
        }
        action.setId(id);
        saveAction(action);
    }

    @Override
    public ActionDTO findById(Long id) {
        if (id == null) {
            throw new RuntimeException(NOT_FOUND_OBJECT);
        }
        var foundAction = actionRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Action id " + id + "not found"));
        return dtoFactory.createActionDTO(foundAction);
    }

    @Override
    public List<ActionDTO> findAll() {
        return actionRepository.findAll().stream()
                .map(dtoFactory::createActionDTO)
                .toList();
    }

    @Override
    public void deleteAction(Long id) {
        var foundAction = actionRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Action id " + id + "not found"));
        foundAction.setActif(false);
        actionRepository.save(foundAction);
    }
}
