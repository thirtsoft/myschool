package com.myschool.sn.dossiereleve.message;

import com.myschool.sn.dossiereleve.entity.Eleve;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEleveDTO {
    private String statut;

    private String message;

    private Long eleve;
}

