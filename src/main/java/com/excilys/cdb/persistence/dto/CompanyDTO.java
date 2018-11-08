package com.excilys.cdb.persistence.dto;

import com.excilys.cdb.model.Company;

public class CompanyDTO extends Company {
	
	private Long id;
	private String name;
	

	public CompanyDTO(Long id, String name) {

		this.id = id;
		this.name=name;
	}

	public CompanyDTO() {}

	
	public Long getId() {
		return id;
	}

	public void setId(Long idComp) {
		this.id = idComp;
	}

	public String getName() {
		return name;
	}

	public void setName(String nameComp) {
		this.name = nameComp;
	}

	@Override
    public String toString() {
        return "[ Id:" + getId() + " | Name:  " + getName() + "]";
     }

}
