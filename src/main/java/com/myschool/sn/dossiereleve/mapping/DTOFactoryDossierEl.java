package com.myschool.sn.dossiereleve.mapping;

import com.myschool.sn.admin.entity.Utilisateur;
import com.myschool.sn.dossiereleve.entity.Eleve;
import com.myschool.sn.dossiereleve.entity.Inscription;
import com.myschool.sn.dossiereleve.entity.Note;
import com.myschool.sn.dossiereleve.entity.Paiement;
import com.myschool.sn.dossiereleve.mapping.mapper.MedecinTraitantMapper;
import com.myschool.sn.dossiereleve.repository.EleveRepository;
import com.myschool.sn.dossiereleve.repository.InscriptionRepository;
import com.myschool.sn.dossiereleve.repository.NoteRepository;
import com.myschool.sn.dossiereleve.repository.PaiementRepository;
import com.myschool.sn.referentiel.mapping.DTOFactoryRef;
import com.myschool.sn.referentiel.mapping.TypePaiementMapper;
import com.myschool.sn.referentiel.service.ReferentielService;
import com.myschool.sn.utils.dtos.admin.UtilisateurDTO;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsEleveDTO;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsEleveParentDTO;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsInscriptionDTO;
import com.myschool.sn.utils.dtos.dossiereleve.DetailsNoteDTO;
import com.myschool.sn.utils.dtos.dossiereleve.EleveDTO;
import com.myschool.sn.utils.dtos.dossiereleve.EleveEditDTO;
import com.myschool.sn.utils.dtos.dossiereleve.InscriptionDTO;
import com.myschool.sn.utils.dtos.dossiereleve.ListNoteDTO;
import com.myschool.sn.utils.dtos.dossiereleve.ListeInscriptionDTO;
import com.myschool.sn.utils.dtos.dossiereleve.NoteDTO;
import com.myschool.sn.utils.dtos.dossiereleve.PaiementDTO;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;

@Named("dtoFactoryEl")
@RequiredArgsConstructor
public class DTOFactoryDossierEl {

    private final DTOFactoryRef dtoFactoryRef;

    private final EleveRepository eleveRepository;

    private final ReferentielService referentielService;

    private final TypePaiementMapper typePaiementMapper;

    private final MedecinTraitantMapper medecinTraitantMapper;

    private final InscriptionRepository inscriptionRepository;

    private final PaiementRepository paiementRepository;

    private final NoteRepository noteRepository;

    public EleveDTO createEleveDTO(Eleve eleve) {
        if (eleve == null) {
            return null;
        }
        EleveDTO dto = new EleveDTO();
        dto.setId(eleve.getId());
        dto.setMatricule(eleve.getMatricule());
        dto.setNom(eleve.getNom());
        dto.setPrenom(eleve.getPrenom());
        dto.setSexe(eleve.getSexe());
        dto.setAddress(eleve.getAddress());
        dto.setDateNaissance(eleve.getDateNaissance());
        dto.setLieuNaissance(eleve.getLieuNaissance());
        dto.setNationalite(eleve.getNationalite());
        dto.setActif(eleve.isActif());
        dto.setAllergies(new ArrayList<>(eleve.getAllergies()));
        dto.setMedecinTraitantDTO(medecinTraitantMapper.toMedecinTraitantDTO(eleve.getMedecinTraitant()));
        return dto;
    }

    public DetailsEleveDTO createDetailsEleveDTO(Eleve eleve) {
        if (eleve == null) {
            return null;
        }
        DetailsEleveDTO dto = new DetailsEleveDTO();
        dto.setId(eleve.getId());
        dto.setMatricule(eleve.getMatricule());
        dto.setNom(eleve.getNom());
        dto.setPrenom(eleve.getPrenom());
        dto.setSexe(eleve.getSexe());
        dto.setAddress(eleve.getAddress());
        dto.setDateNaissance(eleve.getDateNaissance());
        dto.setLieuNaissance(eleve.getLieuNaissance());
        dto.setNationalite(eleve.getNationalite());
        dto.setActif(eleve.isActif());
        dto.setAllergies(new ArrayList<>(eleve.getAllergies()));
        dto.setMedecinTraitantDTO(medecinTraitantMapper.toMedecinTraitantDTO(eleve.getMedecinTraitant()));
        dto.setUtilisateurDTOS(createSetListParentDTO(eleve.getUtilisateurs()));
        dto.setPaiementDTOList(createListPaiementDTO(paiementRepository.findPaiementsByEleve(eleve.getId())));
        dto.setListeInscriptionDTOS(createListeInscriptionDTO(
                inscriptionRepository.findListInscriptionByEleveId(eleve.getId())));
        return dto;
    }


    public List<UtilisateurDTO> createSetListParentDTO(Set<Utilisateur> list) {
        if (list == null)
            return null;
        List<UtilisateurDTO> dtos = new ArrayList<>();
        for (Utilisateur ins : list) {
            if (ins != null)
                dtos.add(toParent(ins));
        }
        return dtos;
    }

    public UtilisateurDTO toParent(Utilisateur parentDTO) {
        return UtilisateurDTO.builder()
                .id(parentDTO.getId())
                .prenom(parentDTO.getPrenom())
                .nom(parentDTO.getNom())
                .civility(parentDTO.getCivility())
                .address(parentDTO.getAddress())
                .profession(parentDTO.getProfession())
                .email(parentDTO.getEmail())
                .telephone(parentDTO.getTelephone())
                .username(parentDTO.getUsername())
                .build();
    }


    public List<EleveDTO> createListeEleveDTO(List<Eleve> eleves) {
        if (eleves == null) {
            return emptyList();
        }
        return eleves.stream()
                .map(this::createEleveDTO)
                .toList();
    }

    public List<EleveDTO> createSetListEleveDTO(Set<Eleve> list) {
        if (list == null)
            return null;
        List<EleveDTO> dtos = new ArrayList<>();
        for (Eleve ins : list) {
            if (ins != null)
                dtos.add(createEleveDTO(ins));
        }
        return dtos;
    }

    public InscriptionDTO createInscriptionDTO(Inscription inscription) {
        if (inscription == null) {
            return null;
        }
        InscriptionDTO dto = new InscriptionDTO();
        dto.setId(inscription.getId());
        dto.setActif(inscription.isActif());
        dto.setEleveId(inscription.getEleve().getId());
        dto.setAnneeScolaireDebutId(inscription.getAnneeScolaire_debut().getId());
        dto.setAnneeScolaireFinId(inscription.getAnneeScolaire_fin().getId());
        dto.setCode(inscription.getCode());
        dto.setMontantInscription(inscription.getMontantInscription());
        dto.setDateInscription(inscription.getDateInscription());
        dto.setCreatedBy(inscription.getCreatedBy());
        return dto;
    }

    public ListeInscriptionDTO createInscriptionListeDTO(Inscription inscription) {
        if (inscription == null) {
            return null;
        }
        ListeInscriptionDTO dto = new ListeInscriptionDTO();
        dto.setId(inscription.getId());
        dto.setActif(inscription.isActif());
        if (inscription.getEleve() != null) {
            Eleve eleve = eleveRepository.findEleveById(inscription.getEleve().getId());
            String nomEleve = eleve.getPrenom() + " " + eleve.getNom();
            dto.setNomCompletEleve(nomEleve);
        }
        if (inscription.getAnneeScolaire_debut() != null) {
            dto.setAnneeScolareDebut(referentielService.findAnneeScolaireDTOById(
                    inscription.getAnneeScolaire_debut().getId()).getLibelle());
        }
        if (inscription.getAnneeScolaire_fin() != null) {
            dto.setAnneeScolareFin(referentielService.findAnneeScolaireDTOById(
                    inscription.getAnneeScolaire_fin().getId()).getLibelle());
        }
        if (inscription.getClasse() != null) {
            dto.setClasse(referentielService.findClasseById(inscription.getClasse().getId()).getLibelle());
        }
        dto.setCode(inscription.getCode());
        dto.setMontantInscription(inscription.getMontantInscription());
        dto.setDateInscription(inscription.getDateInscription());
        dto.setCreatedBy(inscription.getCreatedBy());
        return dto;
    }

    public List<ListeInscriptionDTO> createListeInscriptionDTO(List<Inscription> inscriptions) {
        if (inscriptions == null) {
            return emptyList();
        }
        return inscriptions.stream()
                .map(this::createInscriptionListeDTO)
                .toList();
    }


    public DetailsInscriptionDTO createDetailsInscriptionDTO(Inscription inscription) {
        if (inscription == null) {
            return null;
        }
        DetailsInscriptionDTO dto = new DetailsInscriptionDTO();
        dto.setActif(inscription.isActif());
        dto.setId(inscription.getId());
        dto.setEleveDTO(createEleveDTO(inscription.getEleve()));
        dto.setAnneeScolaireDTODebut(dtoFactoryRef.createAnneeScolaireDTO(inscription.getAnneeScolaire_debut()));
        dto.setAnneeScolaireDTOFin(dtoFactoryRef.createAnneeScolaireDTO(inscription.getAnneeScolaire_fin()));
        dto.setClasseDTO(dtoFactoryRef.createClasseDTO(inscription.getClasse()));
        dto.setCode(inscription.getCode());
        dto.setMontantInscription(inscription.getMontantInscription());
        dto.setDateInscription(inscription.getDateInscription());
        dto.setCreatedBy(inscription.getCreatedBy());
        return dto;
    }

    public List<DetailsInscriptionDTO> createListeDetailsInscriptionDTO(List<Inscription> inscriptions) {
        if (inscriptions == null)
            return new ArrayList<>();
        List<DetailsInscriptionDTO> dtos = new ArrayList<>();
        for (Inscription inscription : inscriptions) {
            dtos.add(createDetailsInscriptionDTO(inscription));
        }
        return dtos;
    }

    /************** Paiement ****************/
    public PaiementDTO createPaiementDTO(Paiement paiement) {
        if (paiement == null) {
            return null;
        }
        PaiementDTO dto = new PaiementDTO();
        dto.setId(paiement.getId());
        dto.setCode(paiement.getCode());
        dto.setActif(paiement.isActif());
        dto.setEleveDTO(createEleveDTO(paiement.getEleve()));
        dto.setCreatedBy(paiement.getCreatedBy());
        dto.setMois(paiement.getMois());
        dto.setMontant(paiement.getMontant());
        dto.setDatePaiement(paiement.getDatePaiement());
        dto.setTypePaiements(typePaiementMapper.createListeTypePaiementDTO(paiement.getTypePaiements()));
        return dto;
    }

    public List<PaiementDTO> createListPaiementDTO(List<Paiement> paiements) {
        if (paiements == null) {
            return emptyList();
        }
        return paiements.stream()
                .map(this::createPaiementDTO)
                .toList();
    }

    /****************** Note  ***********************/

    public NoteDTO createNoteDTO(Note note) {
        if (note == null) return null;
        return NoteDTO
                .builder()
                .id(note.getId())
                .note(note.getNote())
                .eleve(createEleveDTO(note.getEleve()))
                .matiere(dtoFactoryRef.createMatiereDTO(note.getMatiere()))
                .semestre(dtoFactoryRef.createSemestreDTO(note.getSemestre()))
                .type(note.getType())
                .actif(note.getActif())
                .dateCreation(note.getDateCreation())
                .build();
    }

    public ListNoteDTO createNoteListDTO(Note note) {
        if (note == null) return null;
        return ListNoteDTO
                .builder()
                .id(note.getId())
                .note(note.getNote())
                .eleve(note.getEleve().getPrenom() + ' ' + note.getEleve().getNom())
                .matiere(note.getMatiere().getLibelle())
                .semestre(note.getSemestre().getLibelle())
                .type(note.getType())
                .actif(note.getActif())
                .dateCreation(note.getDateCreation())
                .build();
    }

    public List<ListNoteDTO> createListNoteDTO(List<Note> noteList) {
        if (noteList == null) return emptyList();
        return noteList.stream()
                .map(this::createNoteListDTO)
                .toList();
    }

    public DetailsNoteDTO createDetailsNoteDTO(Note note) {
        if (note == null) return null;
        return DetailsNoteDTO
                .builder()
                .id(note.getId())
                .note(note.getNote())
                .eleve(createDetailsEleveDTO(note.getEleve()))
                .matiere(dtoFactoryRef.createMatiereDTO(note.getMatiere()))
                .semestre(dtoFactoryRef.createSemestreDTO(note.getSemestre()))
                .type(note.getType())
                .actif(note.getActif())
                .dateCreation(note.getDateCreation())
                .build();
    }

    public DetailsEleveParentDTO createDetailsEleveParentDTO(Eleve eleve) {
        if (eleve == null) {
            return null;
        }
        DetailsEleveParentDTO dto = new DetailsEleveParentDTO();
        dto.setId(eleve.getId());
        dto.setMatricule(eleve.getMatricule());
        dto.setNom(eleve.getNom());
        dto.setPrenom(eleve.getPrenom());
        dto.setSexe(eleve.getSexe());
        dto.setAddress(eleve.getAddress());
        dto.setDateNaissance(eleve.getDateNaissance());
        dto.setLieuNaissance(eleve.getLieuNaissance());
        dto.setNationalite(eleve.getNationalite());
        dto.setActif(eleve.isActif());
        dto.setAllergies(new ArrayList<>(eleve.getAllergies()));
        dto.setMedecinTraitantDTO(medecinTraitantMapper.toMedecinTraitantDTO(eleve.getMedecinTraitant()));
        dto.setPaiementDTOList(createListPaiementDTO(paiementRepository.findPaiementsByEleve(eleve.getId())));
        dto.setListeInscriptionDTOS(createListeInscriptionDTO(
                inscriptionRepository.findListInscriptionByEleveId(eleve.getId())));
        dto.setListNoteDTOS(createListNoteDTO(noteRepository.findAllNotesByEleve(eleve.getId())));
        return dto;
    }

    public List<DetailsEleveParentDTO> createSetDetailsEleveParentDTO(Set<Eleve> list) {
        if (list == null) return null;
        List<DetailsEleveParentDTO> dtos = new ArrayList<>();
        for (Eleve ins : list) {
            if (ins != null)
                dtos.add(createDetailsEleveParentDTO(ins));
        }
        return dtos;
    }

    public EleveEditDTO createEleveEditDTO(Eleve eleve) {
        return EleveEditDTO.builder().id(eleve.getId()).matricule(eleve.getMatricule())
                .nom(eleve.getNom()).prenom(eleve.getPrenom()).sexe(eleve.getSexe())
                .DateNaissance(eleve.getDateNaissance()).lieuNaissance(eleve.getLieuNaissance())
                .address(eleve.getAddress()).nationalite(eleve.getNationalite())
                .actif(eleve.getActif()).build();

    }


}
