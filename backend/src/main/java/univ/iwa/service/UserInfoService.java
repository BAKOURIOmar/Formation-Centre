package univ.iwa.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import univ.iwa.model.UserInfo;
import univ.iwa.model.UserInfoDetails;
import univ.iwa.repository.UserInfoRepository;
import univ.iwa.model.Formation;
import java.util.Optional; 

@Service
public class UserInfoService implements UserDetailsService { 
	@Autowired UserInfoRepository repository; 
	@Autowired PasswordEncoder encoder; 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		Optional<UserInfo> userDetail = repository.findByName(username); 

		return userDetail.map(UserInfoDetails::new) 
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username)); 
	} 
	public String addUser(UserInfo userInfo) { 
		userInfo.setId(userInfo.getId());
		userInfo.setEmail(userInfo.getEmail());
		userInfo.setName(userInfo.getName());
		userInfo.setMotcles(userInfo.getMotcles());
		userInfo.setNote(userInfo.getNote());
		userInfo.setPassword(encoder.encode(userInfo.getPassword())); 
		repository.save(userInfo); 
		return "User Added Successfully"; 
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
