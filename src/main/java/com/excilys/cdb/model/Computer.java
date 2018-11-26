package com.excilys.cdb.model;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="computer")
public class Computer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	@ManyToOne
	@JoinColumn(name ="company_id")
	private Company company;
	
	

	public Computer(Long id, String name, LocalDate introduced, LocalDate discontinued, Company company) {

		this.id = id;
		this.name=name;
		this.introduced=introduced;
		this.discontinued=discontinued;
		this.company=company;
	}
	public Computer(Long id, String name, LocalDate introduced, LocalDate discontinued) {

		this.id = id;
		this.name=name;
		this.introduced=introduced;
		this.discontinued=discontinued;
	}

	public Computer() {}


	public long getId() {
		return id;
	}

	public void setId(long idPc) {
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company manufacturer) {
		this.company = manufacturer;
	}

	@Override
	public String toString() {
		return "[ Id: " + getId() + " | Name: " + getName() +" | Introduced date : " + getIntroDate() +" | Discontinued date : " +getDiscDate() +" | Company : "+ getCompany().getName() + "]";

	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	

}
