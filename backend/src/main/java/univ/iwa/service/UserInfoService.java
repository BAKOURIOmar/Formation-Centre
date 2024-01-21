package univ.iwa.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import univ.iwa.model.Individuals;
import univ.iwa.model.UserInfo;
import univ.iwa.model.UserInfoDetails;
import univ.iwa.repository.UserInfoRepository;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import univ.iwa.dto.Userdto;
@Service
public class UserInfoService implements UserDetailsService { 
	@Autowired UserInfoRepository repository; 
	@Autowired PasswordEncoder encoder; 
	private ModelMapper modelMapper;
	@Override
	public UserDetails  loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userDetail = repository.findByName(username);

		return  userDetail.map(UserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}
	
	public UserInfoService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
	
	public Userdto addUser(Userdto userdto) {
		UserInfo user = modelMapper.map(userdto, UserInfo.class);
		repository.save(user);
		return userdto;
	} 
	public Userdto addFormateur(Userdto userdto) {
		UserInfo user = modelMapper.map(userdto, UserInfo.class);
		user.setRoles("ROLE_FORMATEUR");
	        repository.save(user);
	        return userdto;
	} 
	@PostConstruct
	public void addAdmin() {
		Userdto admindto=new Userdto();
		admindto.setId(1);
		admindto.setName("admin");
		admindto.setRoles("ROLE_ADMIN");
		UserInfo admin= new UserInfo();
		admin.setId(admindto.getId());
		admin.setName(admindto.getName());
		admin.setRoles(admindto.getRoles());
		admin.setPassword(encoder.encode("adminadmin"));
		repository.save(admin);
	}
	@PostConstruct
	public void addassistant() {
		Userdto assistantdto=new Userdto();
		assistantdto.setId(2);
		assistantdto.setName("assistant");
		assistantdto.setEmail("assistant1@gmail.com");
		assistantdto.setRoles("ROLE_ASSISTANT");
		assistantdto.setPassword(encoder.encode("assistant"));
		UserInfo assistant=new UserInfo();
		assistant.setId(assistantdto.getId());
		assistant.setName(assistantdto.getName());
		assistant.setEmail(assistantdto.getEmail());
		assistant.setPassword(assistantdto.getPassword());
		assistant.setRoles(assistantdto.getRoles());
		repository.save(assistant);
		
	}

	public Userdto convertEntityToDto(UserInfo user){
		Userdto userdto=new Userdto();
		userdto.setId(user.getId());
		userdto.setName(user.getName());
		userdto.setMotcles(user.getMotcles());
		userdto.setEmail(user.getEmail());
		user.setRoles("ROLE_FORMATEUR");
		return userdto;
	}
	
} 
