package com.myschool.sn.referentiel.controller;

import com.myschool.sn.referentiel.controller.api.ReferentielApi;
import com.myschool.sn.referentiel.service.ReferentielService;
import com.myschool.sn.utils.MessageException;
import com.myschool.sn.utils.dtos.admin.login.ReponseMessageDTO;
import com.myschool.sn.utils.dtos.referentiel.AnneeScolaireDTO;
import com.myschool.sn.utils.dtos.referentiel.BatimentDTO;
import com.myschool.sn.utils.dtos.referentiel.CategoryMenuDTO;
import com.myschool.sn.utils.dtos.referentiel.ClasseDTO;
import com.myschool.sn.utils.dtos.referentiel.EvenementDTO;
import com.myschool.sn.utils.dtos.referentiel.MatiereDTO;
import com.myschool.sn.utils.dtos.referentiel.MeetingDTO;
import com.myschool.sn.utils.dtos.referentiel.MenuDTO;
import com.myschool.sn.utils.dtos.referentiel.NiveauEducationDTO;
import com.myschool.sn.utils.dtos.referentiel.SalleDTO;
import com.myschool.sn.utils.dtos.referentiel.SemestreDTO;
import com.myschool.sn.utils.dtos.referentiel.TypeDocumentDTO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReferentielController implements ReferentielApi {

    private final ReferentielService referentielService;

    public ReferentielController(ReferentielService referentielService) {
        this.referentielService = referentielService;
    }

    /************           AnneeScolaire  *******************/
    @Override
    public List<AnneeScolaireDTO> getAnneeScolaires() {
        return referentielService.findAllAnneeScolaires();
    }

    @Override
    public AnneeScolaireDTO getAnneeScolaire(Long anneeScolaireId) {
        return referentielService.findAnneeScolaireDTOById(anneeScolaireId);
    }

    @Override
    public ReponseMessageDTO createAnneeScolaire(AnneeScolaireDTO anneeScolaireDTO) {
        try {
            referentielService.saveAnneeScolaire(anneeScolaireDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateAnneeScolaire(Long anneeScolaireId, AnneeScolaireDTO anneeScolaireDTO) {
        try {
            referentielService.updateAnneeScolaire(anneeScolaireId, anneeScolaireDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public AnneeScolaireDTO getAnneeScolaireByCode(String code) {
        return referentielService.findByCode(code);
    }

    @Override
    public ReponseMessageDTO deleteAnneeScolaire(Long anneeScolaireId) {
        try {
            referentielService.deleteAnneeScolaire(anneeScolaireId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    /***************    Batiment    *********************/
    @Override
    public List<BatimentDTO> getBatiments() {
        return referentielService.findAllBatiments();
    }

    @Override
    public BatimentDTO getBatiment(Long batimentId) {
        return referentielService.findBatimentById(batimentId);
    }

    @Override
    public ReponseMessageDTO createBatiment(BatimentDTO batimentDTO) {
        try {
            referentielService.saveBatiment(batimentDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateBatiment(Long batimentId, BatimentDTO batimentDTO) {
        try {
            referentielService.updateBatiment(batimentId, batimentDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public BatimentDTO getBatimentByCode(String code) {
        return referentielService.findBatimentByCode(code);
    }

    @Override
    public ReponseMessageDTO deleteBatiment(Long batimentId) {
        try {
            referentielService.deleteBatiment(batimentId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    /********************    Classe    *********************/
    @Override
    public List<ClasseDTO> getClasses() {
        return referentielService.findAllClasses();
    }

    @Override
    public ClasseDTO getClasse(Long classeId) {
        return referentielService.findClasseById(classeId);
    }

    @Override
    public ReponseMessageDTO createClasse(ClasseDTO classeDTO) {
        try {
            referentielService.saveClasse(classeDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateClasse(Long classeId, ClasseDTO classeDTO) {
        try {
            referentielService.updateClasse(classeId, classeDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ClasseDTO getClasseByLibelle(String libelle) {
        return referentielService.findClasseByLibelle(libelle);
    }

    @Override
    public ReponseMessageDTO deleteClasse(Long classeId) {
        try {
            referentielService.deleteClasse(classeId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    /****************   Matiere         *******************/
    @Override
    public List<MatiereDTO> getMatieres() {
        return referentielService.findAllMatieres();
    }

    @Override
    public MatiereDTO getMatiere(Long matiereId) {
        return referentielService.findMatiereById(matiereId);
    }

    @Override
    public ReponseMessageDTO createMatiere(MatiereDTO matiereDTO) {
        try {
            referentielService.saveMatiere(matiereDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateMatiere(Long matiereId, MatiereDTO matiereDTO) {
        try {
            referentielService.updateMatiere(matiereId, matiereDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public MatiereDTO getMatiereByCode(String code) {
        return referentielService.findMatiereByCode(code);
    }

    @Override
    public MatiereDTO getMatiereByLibelle(String libelle) {
        return referentielService.findMatiereByLibelle(libelle);
    }

    @Override
    public ReponseMessageDTO deleteMatiere(Long matiereId) {
        try {
            referentielService.deleteMatiere(matiereId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    /**************   Salle ********************/
    @Override
    public List<SalleDTO> getSalles() {
        return referentielService.findAllSalles();
    }

    @Override
    public List<SalleDTO> getSallesByBatiment(Long batimentId) {
        return referentielService.findSallesByBatiment(batimentId);
    }

    @Override
    public SalleDTO getSalle(Long salleId) {
        return referentielService.findSalleById(salleId);
    }

    @Override
    public ReponseMessageDTO createSalle(SalleDTO salleDTO) {
        try {
            referentielService.saveSalle(salleDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateSalle(Long salleId, SalleDTO salleDTO) {
        try {
            referentielService.updateSalle(salleId, salleDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public SalleDTO getSalleByCode(String code) {
        return referentielService.findSalleByCode(code);
    }

    @Override
    public ReponseMessageDTO deleteSalle(Long salleId) {
        try {
            referentielService.deleteSalle(salleId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    /***************  Evenement   ***************/
    @Override
    public List<EvenementDTO> getEvenements() {
        return referentielService.findAllEvenements();
    }

    @Override
    public EvenementDTO getEvenement(Long evenementId) {
        return referentielService.findEvenementById(evenementId);
    }

    @Override
    public ReponseMessageDTO createEvenement(EvenementDTO evenementDTO) {
        try {
            referentielService.saveEvenement(evenementDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateEvenement(Long evenementId, EvenementDTO evenementDTO) {
        try {
            referentielService.updateEvenement(evenementId, evenementDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public EvenementDTO getEvenementByLibelle(String libelle) {
        return referentielService.findEvenementByLibelle(libelle);
    }

    @Override
    public ReponseMessageDTO deleteEvenement(Long evenementId) {
        try {
            referentielService.deleteEvenement(evenementId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    /***************     Semestre  *****************/
    @Override
    public List<SemestreDTO> getSemestres() {
        return referentielService.findAllSemestres();
    }

    @Override
    public SemestreDTO getSemestre(Long semestreId) {
        return referentielService.findSemestreById(semestreId);
    }

    @Override
    public ReponseMessageDTO createSemestre(SemestreDTO semestreDTO) {
        try {
            referentielService.saveSemestre(semestreDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateSemestre(Long semestreId, SemestreDTO semestreDTO) {
        try {
            referentielService.updateSemestre(semestreId, semestreDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public SemestreDTO getSemestreByCode(String code) {
        return referentielService.findSemestreByCode(code);
    }

    @Override
    public SemestreDTO getSemestreByLibelle(String libelle) {
        return referentielService.findSemestreByLibelle(libelle);
    }

    @Override
    public ReponseMessageDTO deleteSemestre(Long semestreId) {
        try {
            referentielService.deleteSemestre(semestreId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    /**************   TypeDocument  ****************/
    @Override
    public List<TypeDocumentDTO> getTypeDocuments() {
        return referentielService.findAllTypeDocuments();
    }

    @Override
    public TypeDocumentDTO getTypeDocument(Long typeDocumentId) {
        return referentielService.findTypeDocumentById(typeDocumentId);
    }

    @Override
    public ReponseMessageDTO createTypeDocument(TypeDocumentDTO typeDocumentDTO) {
        try {
            referentielService.saveTypeDocument(typeDocumentDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateTypeDocument(Long typeDocumentId, TypeDocumentDTO typeDocumentDTO) {
        try {
            referentielService.updateTypeDocument(typeDocumentId, typeDocumentDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public SemestreDTO getTypeDocumentByCode(String code) {
        return referentielService.findSemestreByCode(code);
    }

    @Override
    public SemestreDTO getTypeDocumentByLibelle(String libelle) {
        return referentielService.findSemestreByLibelle(libelle);
    }

    @Override
    public ReponseMessageDTO deleteTypeDocument(Long typeDocumentId) {
        try {
            referentielService.deleteTypeDocument(typeDocumentId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    /**************   NiveauEducation  ****************/
    @Override
    public List<NiveauEducationDTO> getNiveauEducations() {
        return referentielService.findAllNiveauEducations();
    }

    @Override
    public NiveauEducationDTO getNiveauEducation(Long niveauEducationId) {
        return referentielService.findNiveauEducationById(niveauEducationId);
    }

    @Override
    public ReponseMessageDTO createNiveauEducation(NiveauEducationDTO niveauEducationDTO) {
        try {
            referentielService.saveNiveauEducation(niveauEducationDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateNiveauEducation(Long niveauEducationId, NiveauEducationDTO niveauEducationDTO) {
        try {
            referentielService.updateNiveauEducation(niveauEducationId, niveauEducationDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public NiveauEducationDTO getNiveauEducationByCode(String code) {
        return referentielService.findNiveauEducationByCode(code);
    }

    @Override
    public NiveauEducationDTO getNiveauEducationByLibelle(String libelle) {
        return referentielService.findNiveauEducationByLibelle(libelle);
    }

    @Override
    public ReponseMessageDTO deleteNiveauEducation(Long niveauEducationId) {
        try {
            referentielService.deleteNiveauEducation(niveauEducationId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    /****************      CategoryMenu    ***************/
    @Override
    public List<CategoryMenuDTO> getCategoryMenus() {
        return referentielService.findAllCategoryMenus();
    }

    @Override
    public CategoryMenuDTO getCategoryMenu(Long categoryMenuId) {
        return referentielService.findCategoryMenuById(categoryMenuId);
    }

    @Override
    public ReponseMessageDTO createCategoryMenu(CategoryMenuDTO categoryMenuDTO) {
        try {
            referentielService.saveCategoryMenu(categoryMenuDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateCategoryMenu(Long categoryMenuId, CategoryMenuDTO categoryMenuDTO) {
        try {
            referentielService.updateCategoryMenu(categoryMenuId, categoryMenuDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public CategoryMenuDTO getCategoryMenuByLibelle(String libelle) {
        return referentielService.findCategoryMenuByLibelle(libelle);
    }

    @Override
    public ReponseMessageDTO deleteCategoryMenu(Long categoryMenuId) {
        try {
            referentielService.deleteCategoryMenu(categoryMenuId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    /************    Menu    ****************/
    @Override
    public List<MenuDTO> getMenus() {
        return referentielService.findAllMenus();
    }

    @Override
    public List<MenuDTO> findMenusByCategoryMenu(Long catMenuId) {
        return referentielService.findMenusByCategoryMenu(catMenuId);
    }

    @Override
    public MenuDTO getMenu(Long menuId) {
        return referentielService.findMenuById(menuId);
    }

    @Override
    public ReponseMessageDTO createMenu(MenuDTO menuDTO) {
        try {
            referentielService.saveMenu(menuDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateMenu(Long menuId, MenuDTO menuDTO) {
        try {
            referentielService.updateMenu(menuId, menuDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public MenuDTO getMenuByLibelle(String libelle) {
        return referentielService.findMenuByLibelle(libelle);
    }

    @Override
    public ReponseMessageDTO deleteMenu(Long menuId) {
        try {
            referentielService.deleteMenu(menuId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    /***********          Meeting        **************/
    @Override
    public List<MeetingDTO> getMeetings() {
        return referentielService.findAllMeetings();
    }

    @Override
    public MeetingDTO getMeeting(Long meetingId) {
        return referentielService.findMeetingById(meetingId);
    }

    @Override
    public ReponseMessageDTO createMeeting(MeetingDTO meetingDTO) {
        try {
            referentielService.saveMeeting(meetingDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.SAVED_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public ReponseMessageDTO updateMeeting(Long meetingId, MeetingDTO meetingDTO) {
        try {
            referentielService.updateMeeting(meetingId, meetingDTO);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.EDIT_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }

    @Override
    public MeetingDTO getMeetingByLibelle(String libelle) {
        return referentielService.findMeetingByLibelle(libelle);
    }

    @Override
    public ReponseMessageDTO deleteMeeting(Long meetingId) {
        try {
            referentielService.deleteMeeting(meetingId);
            return new ReponseMessageDTO(MessageException.SUCCESS_MESSAGE, MessageException.DELETE_OBJECT);
        } catch (Exception e) {
            return new ReponseMessageDTO(MessageException.FAILED_MESSAGE, MessageException.ERROR_MESSAGE);
        }
    }
}
