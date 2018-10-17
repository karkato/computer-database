package model;


import java.time.LocalDate;


public class Computers {

	int idPc;
	String namePc;
	LocalDate introDate;
	LocalDate discDate;
	int manufacturer;

	public Computers(int id, String name, LocalDate intro, LocalDate disc, int manufacturer) {

		this.idPc = id;
		this.namePc=name;
		this.introDate=intro;
		this.discDate=disc;
		this.manufacturer=manufacturer;
	}
	
	public Computers(int id, String name) {

		this.idPc = id;
		this.namePc=name;
		this.introDate=null;
		this.discDate=null;
		this.manufacturer=0;
	}

	public Computers() {}

	public int getIdPc() {
		return idPc;
	}

	public void setIdPc(int idPc) {
		this.idPc = idPc;
	}

	public String getNamePc() {
		return namePc;
	}

	public void setNamePc(String namePc) {
		this.namePc = namePc;
	}

	public LocalDate getIntroDate() {
		return introDate;
	}

	public void setIntroDate(LocalDate introDate) {
		this.introDate = introDate;
	}

	public LocalDate getDiscDate() {
		return discDate;
	}

	public void setDiscDate(LocalDate discDate) {
		this.discDate = discDate;
	}

	public int getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(int manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "[ Id: " + getIdPc() + " | Name: " + getNamePc()+"]";

	}

	public String information() {

		return "[ Id: " + getIdPc() + " | Name: " + getNamePc() +" | Introduced date : " + getIntroDate() +" | Discontinued date : " +getDiscDate() +" | Company : "+ getManufacturer() + "]";

	}

}
