package hm.sb_xml_authors_Homework1.dto;


public class AuthorDto {

	
	private String name;
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

	@Override
	public String toString() {
		return "AuthorDto [name=" + name + ", appears=" + appears + "]";
	}
	
	
	
}
