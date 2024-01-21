package univ.iwa.config;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.authentication.AuthenticationManager; 
import org.springframework.security.authentication.AuthenticationProvider; 
import org.springframework.security.authentication.dao.DaoAuthenticationProvider; 
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration; 
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity; 
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.security.web.SecurityFilterChain; 
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import univ.iwa.filters.JwtAuthFilter;
import univ.iwa.service.UserInfoService; 

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig { 
	@Autowired JwtAuthFilter authFilter; 
	@Bean
	public UserDetailsService userDetailsService() { 
		return new UserInfoService(null); 
	} 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
		http.authorizeHttpRequests((auth)->auth
			.requestMatchers("/auth/welcome", "/auth/addNewUser", "/auth/generateToken").permitAll()
			.requestMatchers("/auth/user/**").authenticated()
			.requestMatchers("/auth/admin/**").authenticated()
								.requestMatchers("/form/addformation").authenticated()
								.requestMatchers("/form/getformation").permitAll()
								.requestMatchers("/form/updateformation/**").authenticated()
								.requestMatchers("/form/deleteform/{id}").authenticated()
								.requestMatchers("form/getbyville/{ville}").authenticated()
								.requestMatchers("/form/getformationcat/**").authenticated()
								.requestMatchers("/entr/**").authenticated()
								.requestMatchers("/indiv/addindividu").permitAll()
								.requestMatchers("/indiv/getallindividus").authenticated()
								.requestMatchers("/indiv/deleteindividu/{id}").authenticated()
								.requestMatchers("/indiv/updateIndividu/{id}").permitAll()
								.requestMatchers("/auth/addFormateur").authenticated()
			).csrf(csrf->csrf.disable())
			.authenticationProvider(authenticationProvider()) 
			.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class) ;	
		return http.build();
	} 
	// Password Encoding 
	@Bean
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder(); 
	} 
	@Bean
	public AuthenticationProvider authenticationProvider() { 
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
		authenticationProvider.setUserDetailsService(userDetailsService()); 
		authenticationProvider.setPasswordEncoder(passwordEncoder()); 
		return authenticationProvider; 
	} 
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
		return config.getAuthenticationManager(); 
	} 
} 
