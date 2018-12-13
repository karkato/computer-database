package rest;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.CompanyDTO;
import dto.UserDTO;
import model.Company;
import model.User;
import service.UserService;

@CrossOrigin()
@RestController("userController")
@RequestMapping("/user")
public class UserController {
	
	 @GetMapping("/login")
	  public ResponseEntity<Principal> user(Principal user) {
	    return new ResponseEntity<>(user, HttpStatus.OK);
	  }
	 
	 @GetMapping("/users")
		public ResponseEntity<List<UserDTO>> findAll() {
			List<User> companyList;	
			companyList = UserService.findAll();
			List<UserDTO> subCompaniesDTO = companyList.stream().map(
					userMapper:fromCompany
				).collect(Collectors.toList());	
			return new ResponseEntity<>(subCompaniesDTO, HttpStatus.OK);
		}
}
