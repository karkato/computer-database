package validator;

import java.time.LocalDate;

import org.hibernate.exception.DataException;
import org.springframework.stereotype.Component;

import exceptions.DateException;
import exceptions.NameException;
import model.Computer;

@Component
public class ComputerValidator {
	
	private static boolean dateValidator(LocalDate introduced, LocalDate discontinued) throws DateException {
		if (discontinued != null && introduced != null && discontinued.isBefore(introduced)) {
			throw new DateException();
		}
		return true;
	}
	
	private static boolean nameValidator(String name) throws NameException {
		if(name==null||name.equals("")) {
			throw new NameException();
		}
		return true;
	}
	
	public static boolean computerValidator(Computer computer) throws DataException, NameException, DateException  {
		
		boolean nameFlag = false;
		boolean dateFlag = false;
					nameFlag=nameValidator(computer.getName());
					dateFlag= dateValidator(computer.getIntroDate(),computer.getDiscDate());
		if(nameFlag&&dateFlag) {
			return true;
		}
		
		return false;
	}

}
