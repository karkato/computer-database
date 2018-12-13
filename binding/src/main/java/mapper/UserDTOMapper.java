package mapper;

import org.springframework.stereotype.Component;

import model.Role;
import model.User;
import model.User.UserBuilder;
import dto.UserDTO;;

@Component
public class UserDTOMapper {

	private static UserDTOMapper mapperUserDTO = new UserDTOMapper();

	private UserDTOMapper() {
	}

	public static UserDTOMapper getInstance() {
		return mapperUserDTO;
 	}

	public User toUser(UserDTO userDto) {
		Role role = new Role();
		role.setId(Long.parseLong(userDto.role_id));
		UserBuilder userBuilder = new UserBuilder(userDto.name).id(Long.parseLong(userDto.id)).password(userDto.password).role(role);
		User User = new User(userBuilder);
		return User;
	}

	public UserDTO fromUser(User User) {
		UserDTO UserDto = new UserDTO();
		UserDto.id = User.getId() + "";
		UserDto.name = User.getName();
		return UserDto;

	}

}
