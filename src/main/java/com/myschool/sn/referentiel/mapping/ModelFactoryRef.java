package com.myschool.sn.referentiel.mapping;

import com.myschool.sn.referentiel.entity.AnneeScolaire;
import com.myschool.sn.referentiel.entity.Batiment;
import com.myschool.sn.referentiel.entity.CategoryMenu;
import com.myschool.sn.referentiel.entity.Classe;
import com.myschool.sn.referentiel.entity.Evenement;
import com.myschool.sn.referentiel.entity.Matiere;
import com.myschool.sn.referentiel.entity.Meeting;
import com.myschool.sn.referentiel.entity.Menu;
import com.myschool.sn.referentiel.entity.NiveauEducation;
import com.myschool.sn.referentiel.entity.Salle;
import com.myschool.sn.referentiel.entity.Semestre;
import com.myschool.sn.referentiel.entity.TypeDocument;
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

import javax.inject.Named;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named("modelFactoryRef")
public class ModelFactoryRef {

    public AnneeScolaire createAnneeScolaire(AnneeScolaireDTO dto) {
        if (dto == null) return null;
        AnneeScolaire model = new AnneeScolaire();
        model.setId(dto.getId());
        model.setActif(true);
        model.setCode(dto.getCode());
        model.setLibelle(dto.getLibelle());
        return model;
    }

    public Batiment createBatiment(BatimentDTO batimentDTO) {
        if (batimentDTO == null) return null;
        Batiment model = new Batiment();
        model.setId(batimentDTO.getId());
        model.setActif(batimentDTO.isActif());
        model.setLibelle(batimentDTO.getLibelle());
        model.setClasses(createSetClasse(batimentDTO.getClasseDTOS()));
        return model;
    }

    public Classe createClasse(ClasseDTO dto) {
        if (dto == null) return null;
        Classe model = new Classe();
        model.setId(dto.getId());
        model.setActif(true);
        model.setLibelle(dto.getLibelle());
        return model;
    }

    public Set<Classe> createSetClasse(List<ClasseDTO> classeDTOList) {
        if (classeDTOList == null) return null;
        Set<Classe> classeHashSet = new HashSet<>();
        for (ClasseDTO dto : classeDTOList)
            if (dto != null) classeHashSet.add(createClasse(dto));
        return classeHashSet;
    }

    public Matiere createMatiere(MatiereDTO dto) {
        if (dto == null) return null;
        Matiere model = new Matiere();
        model.setId(dto.getId());
        model.setCode(dto.getCode());
        model.setLibelle(dto.getLibelle());
        model.setActif(dto.isActif());
        return model;
    }

    public Salle createSalle(SalleDTO dto) {
        if (dto == null) return null;
        Salle model = new Salle();
        model.setId(dto.getId());
        model.setLibelle(dto.getLibelle());
        model.setBatiment(createBatiment(dto.getBatimentDTO()));
        model.setActif(dto.isActif());
        return model;
    }

    public Evenement createEvenement(EvenementDTO dto) {
        if (dto == null) return null;
        Evenement model = new Evenement();
        model.setId(dto.getId());
        model.setLibelle(dto.getLibelle());
        model.setDateEvenement(dto.getDateEvenement());
        model.setHeureDebut(dto.getHeureDebut());
        model.setHeureFin(dto.getHeureFin());
        model.setDescription(dto.getDescription());
        model.setActif(dto.isActif());
        return model;
    }

    public Semestre createSemestre(SemestreDTO dto) {
        if (dto == null) return null;
        Semestre model = new Semestre();
        model.setId(dto.getId());
        model.setLibelle(dto.getLibelle());
        model.setCode(dto.getCode());
        model.setActif(dto.isActif());
        return model;
    }

    public TypeDocument createTypeDocument(TypeDocumentDTO dto) {
        if (dto == null) return null;
        TypeDocument model = new TypeDocument();
        model.setId(dto.getId());
        model.setLibelle(dto.getLibelle());
        model.setCode(dto.getCode());
        model.setActif(dto.isActif());
        return model;
    }

    public NiveauEducation createNiveauEducation(NiveauEducationDTO dto) {
        if (dto == null) return null;
        NiveauEducation model = new NiveauEducation();
        model.setId(dto.getId());
        model.setLibelle(dto.getLibelle());
        model.setCode(dto.getCode());
        model.setActif(dto.isActif());
        return model;
    }

    public CategoryMenu createCategoryMenu(CategoryMenuDTO dto) {
        if (dto == null) return null;
        CategoryMenu model = new CategoryMenu();
        model.setId(dto.getId());
        model.setLibelle(dto.getLibelle());
        model.setMenus(createSetMenu(dto.getMenuDTOs()));
        model.setActif(dto.isActif());
        return model;
    }

    public Set<Menu> createSetMenu(List<MenuDTO> menuDTOList) {
        if (menuDTOList == null) return null;
        Set<Menu> menus = new HashSet<>();
        for (MenuDTO dto : menuDTOList)
            if (dto != null) menus.add(createMenu(dto));
        return menus;
    }

    public Menu createMenu(MenuDTO dto) {
        if (dto == null) return null;
        Menu model = new Menu();
        model.setId(dto.getId());
        model.setLibelle(dto.getLibelle());
        model.setDescription(dto.getDescription());
        model.setActif(true);
        return model;
    }

    public Meeting createMeeting(MeetingDTO dto) {
        if (dto == null) return null;
        Meeting model = new Meeting();
        model.setId(dto.getId());
        model.setLibelle(dto.getLibelle());
        model.setDateMeeting(dto.getDateMeeting());
        model.setHeureDebut(dto.getHeureDebut());
        model.setHeureFin(dto.getHeureFin());
        model.setDescription(dto.getDescription());
        model.setActif(dto.isActif());
        return model;
    }
}
