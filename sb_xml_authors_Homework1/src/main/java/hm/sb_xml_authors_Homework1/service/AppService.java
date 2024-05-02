package hm.sb_xml_authors_Homework1.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.JDOMException;
import org.springframework.stereotype.Service;

import hm.sb_xml_authors_Homework1.dto.AuthorDto;
import hm.sb_xml_authors_Homework1.dto.AuthorDtoList;
import hm.sb_xml_authors_Homework1.model.Author;
import hm.sb_xml_authors_Homework1.parser.XMLParser;

@Service
public class AppService {

	private XMLParser parser;

	public AppService(XMLParser parser) {
		super();
		this.parser = parser;
	}

	public AuthorDtoList getAuthors() throws JDOMException, IOException {
		
		List<AuthorDto> authorDtoList = new ArrayList<>();
		List<Author> authorList = parser.getAuthors();
		
		
		for(int index = 0; index < authorList.size(); index++) {
			
			Author currentAuthor = authorList.get(index);
			AuthorDto authorDto = new AuthorDto(currentAuthor.getName());
			
			
			for(int otherIndex = 0; otherIndex < authorDtoList.size(); otherIndex++) {
				
				AuthorDto currentAuthorDto = authorDtoList.get(otherIndex);
				if(currentAuthorDto.getName().equals(authorDto.getName())) {
					
					authorDto.addAppears();
					
				}
			}
		
					authorDtoList.add(authorDto);
		}
		
		
		return authorDtoList;
	}
	
	
	
	
}
