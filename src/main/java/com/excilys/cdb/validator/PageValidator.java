package com.excilys.cdb.validator;

import java.util.List;

import com.excilys.cdb.exceptions.PageNumberException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;

public class PageValidator {
	public static void previousPageValidator() throws PageNumberException {
		if (Page.getPageSize() <= 0 || Page.getPage() <= 0) {
			throw new PageNumberException();
		}
	}

	public static  void nextPageValidator(List<Computer> sourceList) throws PageNumberException{
		if( Page.getPage()>1 && sourceList.size()==0 )
	{
		throw new PageNumberException();
	}
}

}
