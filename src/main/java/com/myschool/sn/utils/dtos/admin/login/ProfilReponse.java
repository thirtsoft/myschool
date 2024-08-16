package com.myschool.sn.utils.dtos.admin.login;

import com.myschool.sn.admin.entity.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfilReponse {
    private List<ActionListResponse> actions;
}
