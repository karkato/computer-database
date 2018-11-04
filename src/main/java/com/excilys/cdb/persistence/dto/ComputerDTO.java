package com.excilys.cdb.persistence.dto;

import java.time.LocalDate;

import com.excilys.cdb.model.Computer;

public class ComputerDTO extends Computer {


	private Long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Long companyId;

	public ComputerDTO(Long id, String name, LocalDate introduced, LocalDate discontinued, Long companyId) {

		this.id = id;
		this.name=name;
		this.introduced=introduced;
		this.discontinued=discontinued;
		this.companyId = companyId;
	}
	
	public ComputerDTO() {}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long idPc) {
		this.id = idPc;
	}

	public String getName() {
		return name;
	}

	public void setName(String namePc) {
		this.name = namePc;
	}

	public LocalDate getIntroDate() {
		return introduced;
	}

	public void setIntroDate(LocalDate introDate) {
		this.introduced = introDate;
	}

	public LocalDate getDiscDate() {
		return discontinued;
	}

	public void setDiscDate(LocalDate discDate) {
		this.discontinued = discDate;
	}


	@Override
	public String toString() {
		return "[ Id: " + getId() + " | Name: " + getName() +" | Introduced date : " + getIntroDate() +" | Discontinued date : " +getDiscDate() +"]";

	}


}

