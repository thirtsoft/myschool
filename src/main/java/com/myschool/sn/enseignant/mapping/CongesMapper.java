package com.myschool.sn.enseignant.mapping;

import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.enseignant.entity.Conges;
import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.enseignant.CongesDTO;
import com.myschool.sn.utils.dtos.enseignant.ListeCongesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CongesMapper {

    public Conges toCongesDTO(CongesDTO congesDTO) {
        return Conges.builder()
                .id(congesDTO.getId())
                .objet(congesDTO.getObjet())
                .motif(congesDTO.getMotif())
                .enseignant(Utilisateur.builder().id(congesDTO.getEnseignantDTO().getId()).build())
                .etat(congesDTO.getEtat())
                .dateDebut(congesDTO.getDateDebut())
                .dateFin(congesDTO.getDateFin())
                .actif(congesDTO.getActif())
                .build();
    }

    public CongesDTO fromCongesEntity(Conges conges) {
        return CongesDTO.builder()
                .id(conges.getId())
                .objet(conges.getObjet())
                .motif(conges.getMotif())
                .enseignantDTO(UtilisateurDTO.builder().id(conges.getEnseignant().getId()).build())
                .etat(conges.getEtat())
                .dateDebut(conges.getDateDebut())
                .dateFin(conges.getDateFin())
                .actif(conges.getActif())
                .build();
    }

    public ListeCongesDTO createListConges(Conges conges) {
        if (conges == null) return null;
        ListeCongesDTO listeCongesDTO = new ListeCongesDTO();
        listeCongesDTO.setId(conges.getId());
        listeCongesDTO.setObjet(conges.getObjet());
        listeCongesDTO.setMotif(conges.getMotif());
        listeCongesDTO.setDemandeur(conges.getEnseignant().getPrenom() + ' ' + conges.getEnseignant().getNom());
        listeCongesDTO.setDateDebut(conges.getDateDebut());
        listeCongesDTO.setDateFin(conges.getDateFin());
        listeCongesDTO.setActif(conges.isActif());
        if (conges.getEtat() == 1) {
            listeCongesDTO.setEtat("ENCOURS");
        }
        if (conges.getEtat() == 2) {
            listeCongesDTO.setEtat("ENVOYE");
        }
        if (conges.getEtat() == 3) {
            listeCongesDTO.setEtat("ACCEPTE");
        }
        if (conges.getEtat() == 4) {
            listeCongesDTO.setEtat("REJETE");
        }
        return listeCongesDTO;
    }
}
