package adt;

import android.R.integer;
import android.R.string;

public class jobs {
	private String nameString;
	private String sexString;
    private int age;
    private String idString;
    public jobs(String nameString, String sexString, int age) {
		super();
		this.nameString = nameString;
		this.sexString = sexString;
		this.age = age;
	}
    public jobs() {
		// TODO Auto-generated constructor stub
	}

	
    public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public String getSexString() {
		return sexString;
	}

	public void setSexString(String sexString) {
		this.sexString = sexString;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	public String getIdString() {
		return idString;
	}
	public void setIdString(String idString) {
		this.idString = idString;
	}
	

}
