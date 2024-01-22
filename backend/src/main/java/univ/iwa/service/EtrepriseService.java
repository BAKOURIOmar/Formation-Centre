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
import org.modelmapper.ModelMapper;
@Service
public class EtrepriseService {

    @Autowired
    EntrepriseReposetory entrorepo;
@Autowired
ModelMapper modelMapper;
    //ajouter une liste d entreprises
    public Entreprisedto addentreprise(Entreprisedto entreprisedto){
        Entreprise entreprise= modelMapper.map(entreprisedto,Entreprise.class);
        entrorepo.save(entreprise);
        return entreprisedto;
    }
     //recuperer la listes des entreprises
    public List<Entreprisedto> getallentreprise(){
        List<Entreprise> entreprises=entrorepo.findAll();
        List<Entreprisedto> entrepriseDtos = entreprises.stream()
                .map(entreprise -> modelMapper.map(entreprise, Entreprisedto.class))
                .collect(Collectors.toList());
        return entrepriseDtos;
    }
    //suprimer une entreprise
        public void deleteEntreprise(long id) {
            entrorepo.deleteById(id);
        }
    //update the entreprise
    public Entreprisedto updateentreprise(Long id,Entreprisedto entreprisedto){
       Optional<Entreprise> entreprise=entrorepo.findById(id);
       Entreprise entr=new Entreprise();
        if(entreprise.isPresent()){
          modelMapper.map(entreprisedto,Entreprise.class);
        }
        entrorepo.save(entr);
        return entreprisedto;
    }
}
