package model;

public class Company {

	private int id;
	private String name;
	

	public Company(int id, String name) {

		this.id = id;
		this.name=name;
	}

	public Company() {}

	
	public int getId() {
		return id;
	}

	public void setId(int idComp) {
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
