package com.myschool.sn.admin.service;

import com.myschool.sn.admin.entity.Utilisateur;


public interface UtilsServiceCustom {

    void sendMailCreateUser(Utilisateur utilisateur) throws Exception;

    void sendMailForgotPass(Utilisateur utilisateur, String motDePasse) throws Exception;

    void sendMailUpdatePass(Utilisateur user) throws Exception;

}
