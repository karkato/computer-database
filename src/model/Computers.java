package model;

import java.sql.Date;


public class Computers {

	int id_pc;
	String name_pc;
	Date intro_date;
	Date disc_date;
	Companies manufacturer;

	public Computers(int id, String name, Date intro, Date disc, Companies manufacturer) {

		this.id_pc = id;
		this.name_pc=name;
		this.intro_date=intro;
		this.disc_date=disc;
		this.manufacturer=manufacturer;
	}

	public Computers() {}

	public Companies getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Companies manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getId_pc() {
		return id_pc;
	}
	public void setId_pc(int id_pc) {
		this.id_pc = id_pc;
	}
	public String getName_pc() {
		return name_pc;
	}
	public void setName_pc(String name_pc) {
		this.name_pc = name_pc;
	}
	public Date getIntro_date() {
		return intro_date;
	}
	public void setIntro_date(Date intro_date) {
		this.intro_date = intro_date;
	}
	public Date getDisc_date() {
		return disc_date;
	}
	public void setDisc_date(Date disc_date) {
		this.disc_date = disc_date;
	}

}
