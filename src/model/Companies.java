package model;

public class Companies {

	int idComp;
	String nameComp;

	public Companies(int id, String name) {

		this.idComp = id;
		this.nameComp=name;
	}

	public Companies() {}

	
	public int getIdComp() {
		return idComp;
	}

	public void setIdComp(int idComp) {
		this.idComp = idComp;
	}

	public String getNameComp() {
		return nameComp;
	}

	public void setNameComp(String nameComp) {
		this.nameComp = nameComp;
	}

	@Override
    public String toString() {
        return "[ Id:" + getIdComp() + " | Name:  " + getNameComp() + "]";
     }



}
