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

import org.springframework.web.cors.CorsConfiguration;
import univ.iwa.filters.JwtAuthFilter;
import univ.iwa.service.UserInfoService; 

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig { 
	@Autowired JwtAuthFilter authFilter; 
	@Bean
	public UserDetailsService userDetailsService() { 
		return new UserInfoService(); 
	} 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
		http
		.cors(cors->cors.configurationSource(request -> new CorsConfiguration(corsFilter())))
		.authorizeHttpRequests((auth)->auth
			.requestMatchers("/auth/welcome",  "/auth/generateToken").permitAll()
			.requestMatchers("/auth/user/**").authenticated()
			.requestMatchers("/auth/users").authenticated()
			.requestMatchers("/auth/admin/**").authenticated()
								.requestMatchers("/form/addformation").authenticated()
								.requestMatchers("/form/getformation").permitAll()
								.requestMatchers("/form/updateformation/**").authenticated()
								.requestMatchers("/form/deleteform/{id}").authenticated()
								.requestMatchers("form/getbyville/{ville}").authenticated()
								.requestMatchers("/form/getformationcat/**").authenticated()
								.requestMatchers("/form/filtreSearch").authenticated()
								.requestMatchers("/entreprise/**").authenticated()
								.requestMatchers("/indiv/addindividu").permitAll()
								.requestMatchers("/indiv/getallindividus").authenticated()
								.requestMatchers("/indiv/deleteindividu/{id}").authenticated()
								.requestMatchers("/indiv/updateIndividu/{id}").permitAll()
								.requestMatchers("/auth/addAssistant").authenticated()
								.requestMatchers("/auth/addFormateur").authenticated()
								.requestMatchers("/auth/updateAssistant/**").authenticated()
								.requestMatchers("/auth/updateFormateur/**").authenticated()
								.requestMatchers("/auth/supprimerUser/**").authenticated()
								.requestMatchers("/plan/**").authenticated()
								.requestMatchers("/photos/**").permitAll()
								.requestMatchers("/form/getformationbyid/**").permitAll()
								.requestMatchers("/form/getformationfiltre").permitAll()
								.requestMatchers("/auth/registerFormateurExterne").permitAll()
								.requestMatchers("/form/addIndividuToFormation").authenticated()
								.requestMatchers("/form/countIndividusInFormation/{formationId}").permitAll()
								.requestMatchers("/form/createGroupsIfNeeded/{formationId}/{seuil}").permitAll()
								.requestMatchers("/indiv/addIndividuToFormation/{individuId}/{formationId}").permitAll()
								.requestMatchers("/indiv/deleteIndividu/{id}").permitAll()
								.requestMatchers("/indiv/api/formation/{formationId}/individus").permitAll()
								.requestMatchers("/groupe/create").permitAll()
								.requestMatchers("/groupe/all").permitAll()
								.requestMatchers("/groupe/{id}").permitAll())

			.csrf(csrf->csrf.disable())
			.authenticationProvider(authenticationProvider()) 
			.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class) ;	
		return http.build();
	}
	
	public CorsConfiguration corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		return config;
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
