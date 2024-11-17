package com.myschool.sn.utils.dtos.dossiereleve;


import com.myschool.sn.utils.dtos.admin.register.UtilisateurDTORequest;
import com.myschool.sn.utils.dtos.parent.ParentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EleveRequestDTO {

    private EleveDTO eleveDTO;

    private List<ParentDTO> parentDTOS;

    private List<UtilisateurDTORequest> utilisateurDTORequests;

}
