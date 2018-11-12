package com.excilys.cdb.persistence.dto;

public class CompanyDTO {
	
	public String id;
	public String name;
	

	public CompanyDTO(String id, String name) {

		this.id = id;
		this.name=name;
	}

	public CompanyDTO() {}

	
	public String getId() {
		return id;
	}

	public void setId(String idComp) {
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
