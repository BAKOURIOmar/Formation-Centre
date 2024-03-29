package univ.iwa.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import univ.iwa.dto.Userdto;
import univ.iwa.model.UserInfo;

import java.util.List;
import java.util.Optional; 

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> { 
	Optional<UserInfo> findByName(String username);
	Page<UserInfo> findByRoles(String roles,Pageable pageable);
	List<UserInfo> findByNameContainingAndRoles(String name, String roles);
}
