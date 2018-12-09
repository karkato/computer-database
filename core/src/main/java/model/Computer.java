package model;


import java.time.LocalDate;


public class Computer {

	private long id;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company company;
	private long companyId;

	public Computer(Long id, String name, LocalDate introduced, LocalDate discontinued, Company company, Long companyId) {

		this.id = id;
		this.name=name;
		this.introduced=introduced;
		this.discontinued=discontinued;
		this.company=company;
		this.companyId = companyId;
	}
	public Computer(Long id, String name, LocalDate introduced, LocalDate discontinued,Long companyId) {

		this.id = id;
		this.name=name;
		this.introduced=introduced;
		this.discontinued=discontinued;
		this.companyId = companyId;
	}

	public Computer() {}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}



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
		result = prime * result + (int) (companyId ^ (companyId >>> 32));
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
		if (companyId != other.companyId)
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
