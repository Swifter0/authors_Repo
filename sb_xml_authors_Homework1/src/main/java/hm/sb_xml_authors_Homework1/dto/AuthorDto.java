package hm.sb_xml_authors_Homework1.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class AuthorDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "appearance")
	private int appears;
	
	public AuthorDto() {
		
	}
	
	public AuthorDto(String name) {
		super();
		this.name = name;
		this.appears = 1;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAppears() {
		return appears;
	}


	public void setAppears(int appears) {
		this.appears = appears;
	}
	
	
	public void addAppears() {
		
		this.appears++;
	}
	
}
