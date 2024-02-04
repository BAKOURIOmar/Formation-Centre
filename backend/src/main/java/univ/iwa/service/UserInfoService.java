package univ.iwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.PageImpl;


import org.modelmapper.ModelMapper;
import jakarta.annotation.PostConstruct;
import univ.iwa.model.Formationplanifier;
import univ.iwa.model.Individuals;
import univ.iwa.model.UserInfo;
import univ.iwa.model.UserInfoDetails;
import univ.iwa.repository.PlanificationReposertory;
import univ.iwa.repository.UserInfoRepository;
import org.modelmapper.ModelMapper;

import univ.iwa.config.ModelMapperConfig;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import univ.iwa.dto.Userdto;
@Service
public class UserInfoService implements UserDetailsService {
	@Autowired 
	private UserInfoRepository repository; 
	@Autowired 
	private PasswordEncoder encoder; 
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	PlanificationReposertory planificationReposertory;
	
	@Autowired
	EmailService emailService ;
	
	String subject="Identifiants de connexion";
	String body;
	
	@Override
	public UserDetails  loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userDetail = repository.findByName(username);

		return  userDetail.map(UserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}
	

	public Userdto addAssistant(Userdto userdto) {
		
		body = "Cher "+userdto.getName()+",\\n\\n"
				+ "Voici vos identifiants de connexion pour accéder au système du centre de formation Gonzales :\n\n"
				+ "Utilisateur : " + userdto.getName() + "\n\n"
				+ "Mot de passe : " + userdto.getPassword()+ "\n\n"
				+ "Cordialement,\n\n"
				+ "Centre de formation Gonzales";
		
		emailService.sendSimpleEmail(userdto.getEmail(),subject,body);
		
		
		UserInfo user=modelMapper.map(userdto,UserInfo.class);
			user.setRoles("ROLE_ASSISTANT");
		 user.setPassword(encoder.encode(userdto.getPassword()));
		 UserInfo createdUser = repository.save(user);
		return modelMapper.map(createdUser,Userdto.class);
	} 
	public Userdto addFormateur(Userdto userdto) {
		body = "Cher "+userdto.getName()+",\n\n"
				+ "Voici vos identifiants de connexion pour accéder au système du centre de formation Gonzales :\n\n"
				+ "Utilisateur : " + userdto.getName() + "\n"
				+ "Mot de passe : " + userdto.getPassword()+ "\n\n"
				+ "Cordialement,\n"
				+ "Centre de formation Gonzales";
		
		emailService.sendSimpleEmail(userdto.getEmail(),subject,body);
		
		
		
		UserInfo user = modelMapper.map(userdto, UserInfo.class);
			user.setRoles("ROLE_FORMATEUR");
			user.setPassword(encoder.encode(userdto.getPassword()));
			 UserInfo createdUser = repository.save(user);
		return modelMapper.map(createdUser,Userdto.class);
	} 
	
	public Userdto updateFormateur(Userdto userdto , Integer id) {
		Optional<UserInfo> result = repository.findById(id);
		   if (result.isPresent()) {
			   UserInfo user = result.get();
			   user.setName(userdto.getName());
			   user.setEmail(userdto.getEmail());
			   user.setMotcles(userdto.getMotcles());
			   user.setRemarque(userdto.getRemarque());
			   user.setPassword(encoder.encode(userdto.getPassword()));
			   user.setType(userdto.getType());
			   UserInfo updatedUser = repository.save(user);
	            return modelMapper.map(updatedUser, Userdto.class);
	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Formateur with ID %d does not exist", id));
	        }
		
	}
	
	public Userdto updateAssistant(Userdto userdto , Integer id) {
		Optional<UserInfo> result = repository.findById(id);
		   if (result.isPresent()) {
			   UserInfo user = result.get();
			   user.setName(userdto.getName());
			   user.setEmail(userdto.getEmail());
			   user.setPassword(encoder.encode(userdto.getPassword()));
			   user.setVille(userdto.getVille());
			   UserInfo updatedUser = repository.save(user);
	            return modelMapper.map(updatedUser, Userdto.class);
	        } else {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Assistant with ID %d does not exist", id));
	        }
		
	}
	
	public boolean deleteUserByid(int id) {
		if (!repository.existsById(id)) {
  	      return false; 
  	    }
		UserInfo userInfo = repository.findById(id).get();
		
		List<Formationplanifier> lists = planificationReposertory.findByFormateur(userInfo);
		for (Formationplanifier formationplanifier : lists) {
			formationplanifier.setFormateur(null);
			planificationReposertory.save(formationplanifier);
			
		}
		repository.deleteById(id);
		return true;
	}
	
	
	
	public Page<Userdto> getUsersByRole(String role, Pageable pageable) {
	  
	        Page<UserInfo> userInfoPage = repository.findByRoles(role, pageable);

	        List<Userdto> userDtoList = userInfoPage.getContent().stream()
	            .map(userInfo -> modelMapper.map(userInfo, Userdto.class))
	            .collect(Collectors.toList());

	        return new PageImpl<>(userDtoList, pageable, userInfoPage.getTotalElements());
	}
	public UserInfo getById (Integer id ){
		return repository.getReferenceById(id);
	}
	
	
	@PostConstruct
	public void addAdmin() {
		UserInfo admin= new UserInfo();
		admin.setId(1);
		admin.setName("admin");
		admin.setRoles("ROLE_ADMIN");
		admin.setPassword(encoder.encode("adminadmin"));
		repository.save(admin);
	}
	
	
	public Userdto registerFormateurExterne(Userdto userdto) {
	    UserInfo user = modelMapper.map(userdto, UserInfo.class);
	    user.setRoles("ROLE_FORMATEUR");
	    //user.setPassword(encoder.encode(userdto.getPassword()));
	    user.setType("EXTERNE");
	    UserInfo createdUser = repository.save(user);
	    return modelMapper.map(createdUser, Userdto.class);
	}





} 
