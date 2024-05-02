package hm.sb_xml_authors_Homework1.dto;

import java.util.List;

public class AuthorDtoList {

	List<AuthorDto> authorDtos;

	public AuthorDtoList(List<AuthorDto> authorDtos) {
		super();
		this.authorDtos = authorDtos;
	}

	public List<AuthorDto> getAuthorDtos() {
		return authorDtos;
	}

	public void setAuthorDtos(List<AuthorDto> authorDtos) {
		this.authorDtos = authorDtos;
	}
	
}
