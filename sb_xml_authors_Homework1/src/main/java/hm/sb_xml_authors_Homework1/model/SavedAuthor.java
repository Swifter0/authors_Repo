package hm.sb_xml_authors_Homework1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class SavedAuthor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	

	@Column(name = "appearance")
	private int appearance;


	public SavedAuthor(String name, int appearance) {
		super();
		this.name = name;
		this.appearance = appearance;
	}


	public SavedAuthor() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAppearance() {
		return appearance;
	}


	public void setAppearance(int appearance) {
		this.appearance = appearance;
	}


	@Override
	public String toString() {
		return "SavedAuthor [id=" + id + ", name=" + name + ", appearance=" + appearance + "]";
	}
	
}
