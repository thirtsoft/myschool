package com.myschool.sn.utils;

public final class ConstantSigs {

    public static final String SESSION_ID_UTILISATEUR = "id_user";
    public static final String SESSION_TOKEN = "token";
    public static final String SESSION_NOM_UTILISATEUR = "nomUtilisateur";

    public static final String SESSION_USER_AUTHORISE = "userAuthorise";

    public static final String PASSWORD_PAR_DEFAULT = "Passer123#";

    public static final Long ID_ADMIN = 1L;
    public static final String TYPE_COMPTE_ADM = "AGENT";

    public static final String TYPE_COMPTE_PARA = "PARENT";

    public static final String TYPE_COMPTE_ELEV = "ELEVE";

    public static final Long PROFIL_ELEVE = 4L;

    public static final Long PROFIL_PARENT = 3L;

    public static final Long PROFIL_AGENT = 2L;

    public static final Long TYPE_PHOTO = 1L;
    public static final Long TYPE_PAIEMENT = 2L;
    public static final Long TYPE_DEVOIR = 3L;
    public static final Long TYPE_BULLETIN = 4L;
    public static final Long TYPE_AUTRES = 5L;

    public static final int ETAT_BROUILLON = 1;
    public static final int ETAT_SOUMIS = 2;
    public static final int ETAT_ACCEPTE = 3;
    public static final int ETAT_REJETE = 4;
    public static final int ETAT_ANNULE = 5;


    public static final String USER_ALREADY_EXIST = "Un utilisateur avec cet nom d'utilisateur ou téléphone existe dèjà.";
    public static final String USER_NOT_FOUND = "User with id {0} not found";
    public static final String PASSWORD_DID_NOT_MATCHED = "Le mot de passe courant est incorrect.";

}
