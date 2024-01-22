package univ.iwa.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import jakarta.annotation.PostConstruct;
import univ.iwa.model.Individuals;
import univ.iwa.model.UserInfo;
import univ.iwa.model.UserInfoDetails;
import univ.iwa.repository.UserInfoRepository;
import org.modelmapper.ModelMapper;

import univ.iwa.config.ModelMapperConfig;
import java.util.Optional;

import univ.iwa.dto.Userdto;
@Service
public class UserInfoService implements UserDetailsService {
	@Autowired
	ModelMapper modelMapper;
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
		UserInfo user=modelMapper.map(userdto,UserInfo.class);
		 user.setRoles("ROLE_FORMATEUR");
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
		UserInfo admin= new UserInfo();
		admin.setId(1);
		admin.setName("admin");
		admin.setRoles("ROLE_ADMIN");
		admin.setPassword(encoder.encode("adminadmin"));
		repository.save(admin);
	}
	@PostConstruct
	public void addassistant() {
		UserInfo assistant=new UserInfo();
		assistant.setId(2);
		assistant.setName("assistant");
		assistant.setEmail("assistant1@gmail.com");
		assistant.setPassword(encoder.encode("assistant"));
		assistant.setRoles("ROLE_ASSISTANT");
		repository.save(assistant);
		
	}



} 
