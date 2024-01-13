package univ.iwa.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.iwa.dto.Userdto;
import univ.iwa.model.Entreprise;
import univ.iwa.model.UserInfo;
import univ.iwa.repository.EntrepriseReposetory;
import univ.iwa.dto.Entreprisedto;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EtrepriseService {

    @Autowired
    EntrepriseReposetory entrorepo;

    //ajouter une liste d entreprises
    public Entreprisedto addentreprise(Entreprisedto entreprisedto){
        Entreprise entreprise=new Entreprise();
        entreprise.setId(entreprisedto.getId());
        entreprise.setName(entreprisedto.getName());
        entreprise.setUrl(entreprisedto.getUrl());
        entreprise.setEmail(entreprisedto.getEmail());
        entreprise.setTel(entreprisedto.getTel());
        entrorepo.save(entreprise);
        return entreprisedto;
    }
     //recuperer la listes des entreprises
    public List<Entreprisedto> getallentreprise(){
        List<Entreprise> entreprises=entrorepo.findAll();
     return   entreprises.stream().map(this::convertEntityToDto).collect(Collectors.toList());

    }

    public Entreprisedto convertEntityToDto(Entreprise entreprise){
       Entreprisedto entreprisedto=new Entreprisedto();
        entreprisedto.setId(entreprise.getId());
       entreprisedto.setName(entreprise.getName());
        entreprisedto.setUrl(entreprise.getUrl());
        entreprisedto.setTel(entreprise.getTel());
        entreprisedto.setEmail(entreprise.getEmail());
        entreprisedto.setAdresse(entreprise.getAdresse());

        return entreprisedto;
    }
    //suprimer une entreprise
    public void deleteentreprisse(Long id){
        entrorepo.deleteById(id);
    }
    //update the entreprise
    public String updateentreprise(Long id,Entreprisedto entreprisedto){
       Optional<Entreprise> entreprise=entrorepo.findById(id);
       Entreprise entr=new Entreprise();
        if(entreprise.isPresent()){
       entr.setName(entreprisedto.getName());
       entr.setAdresse(entreprisedto.getAdresse());
       entr.setUrl(entreprisedto.getUrl());
       entr.setTel(entreprisedto.getTel());
       entr.setUrl(entreprisedto.getUrl());
        }
        entrorepo.save(entr);
        return "updated";
    }
}
