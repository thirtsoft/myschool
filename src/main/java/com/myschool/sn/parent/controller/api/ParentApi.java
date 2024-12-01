package com.myschool.sn.parent.controller.api;

import com.myschool.sn.utils.dtos.parent.ParentDetailsDTO;
import com.myschool.sn.utils.dtos.parent.ParentListeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/parent")
public interface ParentApi {

    @GetMapping(value = "/list")
    ResponseEntity<List<ParentListeDTO>> getAllParents();

    @GetMapping(value = "/details/{parentId}")
    ResponseEntity<ParentDetailsDTO> getParentDetails(@PathVariable Long parentId);

    @DeleteMapping("/delete/{userId}")
    void deleteParent(@PathVariable @NotNull Long userId);
}
