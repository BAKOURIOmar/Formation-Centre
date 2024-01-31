package univ.iwa.controller;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;
import univ.iwa.dto.Userdto;
import univ.iwa.model.AuthRequest;
import univ.iwa.model.UserInfo;
import univ.iwa.service.JwtService;
import univ.iwa.service.UserInfoService;
@RestController
@RequestMapping("/auth")
public class UserController { 
    @Autowired UserInfoService service; 
    @Autowired JwtService jwtService; 
    @Autowired AuthenticationManager authenticationManager; 
    
    
    @GetMapping("/welcome") 
    public String welcome() {return "Welcome this endpoint is not secure";}
    
    @PostMapping("/addAssistant") 
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_FORMATEUR')")
    public ResponseEntity<Userdto> addAssistant(@RequestBody Userdto userdto) {
        return new ResponseEntity<Userdto>(service.addAssistant(userdto),HttpStatus.OK);
    }
    
    @PutMapping("/updateAssistant/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_FORMATEUR')")
    public ResponseEntity<Userdto> updateAssistant(@RequestBody Userdto userdto,@PathVariable Integer id) {
        return new ResponseEntity<Userdto>(service.updateAssistant(userdto, id),HttpStatus.OK);
    }
    
    @PostMapping("/addFormateur") 
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_FORMATEUR')")
    public ResponseEntity<Userdto> addFormateur(@RequestBody Userdto userdto) {
        return new ResponseEntity<Userdto>(service.addFormateur(userdto),HttpStatus.OK); 
    }
    
    @PutMapping("/updateFormateur/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_FORMATEUR')")
    public ResponseEntity<Userdto> updateFormateur(@RequestBody Userdto userdto,@PathVariable Integer id) {
        return new ResponseEntity<Userdto>(service.updateFormateur(userdto, id),HttpStatus.OK);
    }
    
    @DeleteMapping("/supprimerUser/{id}") 
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_FORMATEUR')")
    public ResponseEntity<Boolean> deleteUserByid(@PathVariable Integer id) {
        return new ResponseEntity<Boolean>(service.deleteUserByid(id),HttpStatus.OK);
    }
    
    @GetMapping("/users")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_ASSISTANT','ROLE_FORMATEUR')")
    public ResponseEntity<List<Userdto>> getUsersByRole(@RequestParam(name = "role" ,required = true) String role) {
    	System.out.println(role);
    	return new ResponseEntity<List<Userdto>>(service.getUsersByRole(role),HttpStatus.OK); 
    }
    
    @GetMapping("/user/userProfile") 
    @PreAuthorize("hasAnyAuthority('ROLE_USER')") 
    public String userProfile() { return "Welcome to User Profile"; } 
    
    @GetMapping("/admin/adminProfile") 
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')") 
    public String adminProfile() { return "Welcome to Admin Profile"; } 
  
    
    @GetMapping("/user/assistant")
    @PreAuthorize("hasAnyAuthority('ROLE_ASSISTANT')")
    public String assistantuser() {
    	return "Welcome to assistant Profile";
    }
    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if (authentication.isAuthenticated()) {
            System.out.println("gen token");
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return new ResponseEntity<>("{\"message\":\"" + jwtService.generateToken(authRequest.getUsername(), roles.get(0)) + "\",\"role\":\""+ roles.get(0)+"\"}", HttpStatus.OK);
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    @PostMapping("/registerFormateurExterne")
    public ResponseEntity<Userdto> registerFormateurExterne(@RequestBody Userdto userdto) {
        return new ResponseEntity<Userdto>(service.registerFormateurExterne(userdto), HttpStatus.OK);
    }

} 