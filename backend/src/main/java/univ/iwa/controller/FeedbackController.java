package univ.iwa.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import univ.iwa.dto.Entreprisedto;
import univ.iwa.dto.FeedbackCreateDto;
import univ.iwa.dto.FeedbackDto;
import univ.iwa.dto.Userdto;
import univ.iwa.model.Feedback;
import univ.iwa.model.Individuals;
import univ.iwa.model.UserInfo;
import univ.iwa.service.EtrepriseService;
import univ.iwa.service.FeedbackService;
import univ.iwa.service.IndividuService;
import univ.iwa.service.UserInfoService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @Autowired
    UserInfoService userInfoService ;
    @Autowired
    IndividuService individuService ;

    @Autowired
    ModelMapper modelMapper;

    //ajouter une entreprise
    @PostMapping("/add")

    public ResponseEntity<String> addFeedback(@RequestBody FeedbackCreateDto feedbackCreateDto){


        System.out.println("start saving feedback .."+feedbackCreateDto);
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setDescription(feedbackCreateDto.getDescription());
        feedbackDto.setRating(feedbackCreateDto.getRating());
        System.out.println("initializing feedback dto");
        System.out.println("getiing user and individual");

        UserInfo formateur = userInfoService.getById(feedbackCreateDto.getFormateurId());
        Individuals individual = individuService.getById(feedbackCreateDto.getIndividualId());

        System.out.println("setting user and individual to feedback");

        feedbackDto.setUser(formateur);
        feedbackDto.setIndividual(individual);

        System.out.println("adding feedback to formateur");

        formateur.getFeedbacks().add( modelMapper.map(feedbackDto, Feedback.class) );



        System.out.println("updating the user ");

        userInfoService.updateFormateur(modelMapper.map(formateur, Userdto.class),Integer.parseInt(""+formateur.getId()) );
        System.out.println("saving feeback");
        feedbackService.addFeedback(feedbackDto);

        return new ResponseEntity<String>("feedback added succesfully",HttpStatus.OK);
    }
}
