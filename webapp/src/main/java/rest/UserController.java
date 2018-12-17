package rest;

import java.security.Principal;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mapper.UserDTOMapper;
import model.User;

@CrossOrigin()
@RestController("userController")

public class UserController {
	
	private final UserDTOMapper userMapper;
	
	public  UserController(UserDTOMapper userMapper) {
		this.userMapper = userMapper;

	}
	
	 @RequestMapping("/login")
	  public boolean login(@RequestBody User user) {
	    return user.getName().equals("user") && user.getPassword().equals("password");
	  }
	 
	 @RequestMapping("/user")
		public Principal user (HttpServletRequest request) {
		 	String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
		 	
		 	return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
		 	
	 }
	 
}
