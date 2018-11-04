package com.excilys.cdb.validator;

import java.time.LocalDate;

import com.excilys.cdb.exceptions.DataException;
import com.excilys.cdb.exceptions.DateException;
import com.excilys.cdb.exceptions.NameException;
import com.excilys.cdb.model.Computer;

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
	
	public static boolean computerValidator(Computer computer) throws DataException, NameException  {
		
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
