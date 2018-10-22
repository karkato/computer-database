package com.excilys.cdb.model;

public class Company {

	private Long id;
	private String name;
	

	public Company(Long id, String name) {

		this.id = id;
		this.name=name;
	}

	public Company() {}

	
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
