package model;

public class Companies {

	int id_comp;
	String name_comp;

	public Companies(int id, String name) {

		this.id_comp = id;
		this.name_comp=name;
	}

	public Companies() {}

	public int getId_comp() {
		return id_comp;
	}
	public String getName_comp() {
		return name_comp;
	}
	public void setId_comp(int id_comp) {
		this.id_comp = id_comp;
	}
	public void setName_comp(String name_comp) {
		this.name_comp = name_comp;
	}




}
