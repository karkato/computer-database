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

import dto.UserDTO;
import mapper.UserDTOMapper;
import model.User;
import service.UserService;

@CrossOrigin()
@RestController("userController")
@RequestMapping("/user")
public class UserController {
	
	private final UserDTOMapper userMapper;
	
	public  UserController(UserDTOMapper userMapper) {
		this.userMapper = userMapper;

	}
	
	 @GetMapping("/login")
	  public ResponseEntity<Principal> user(Principal user) {
	    return new ResponseEntity<>(user, HttpStatus.OK);
	  }
	 
	 @GetMapping("/users")
		public ResponseEntity<List<UserDTO>> findAll() {
			List<User> userList;	
			userList = UserService.findAll();
			List<UserDTO> subUsersDTO = userList.stream().map(
					userMapper::fromUser
				).collect(Collectors.toList());
			return new ResponseEntity<>(subUsersDTO
					, HttpStatus.OK);
		}
}
