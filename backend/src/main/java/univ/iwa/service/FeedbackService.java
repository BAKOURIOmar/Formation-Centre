package univ.iwa.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import univ.iwa.dto.Entreprisedto;
import univ.iwa.dto.FeedbackDto;
import univ.iwa.model.Entreprise;
import univ.iwa.model.Feedback;
import univ.iwa.repository.EntrepriseReposetory;
import univ.iwa.repository.FeedbackReposetory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    FeedbackReposetory feedbackReposetory;
@Autowired
ModelMapper modelMapper;

    public FeedbackDto addFeedback(FeedbackDto feedbackDto){
        Feedback feedback = modelMapper.map(feedbackDto,Feedback.class);
        return modelMapper.map(feedbackReposetory.save(feedback),FeedbackDto.class);
    }

}
