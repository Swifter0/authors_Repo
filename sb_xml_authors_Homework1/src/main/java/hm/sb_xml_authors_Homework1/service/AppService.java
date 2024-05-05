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

	public AuthorDtoList getAuthors(String selection) throws JDOMException, IOException {
		
		List<AuthorDto> authorDtoList = new ArrayList<>();
		List<Author> authorList = parser.getAuthors();
		
		
		for(int index = 0; index < authorList.size(); index++) {
			
			Author currentAuthor = authorList.get(index);
			AuthorDto authorDto = new AuthorDto(currentAuthor.getName());
			
			
			if(authorDtoList.size() > 0) {
				
				
				
				for(int otherIndex = 0; otherIndex < authorDtoList.size(); otherIndex++) {
					
					AuthorDto currentAuthorDto = authorDtoList.get(otherIndex);
					
					if(currentAuthorDto.getName().equals(authorDto.getName())) {
						
						currentAuthorDto.addAppears();
						authorDtoList.set(otherIndex, currentAuthorDto);
						
					}
					else if(otherIndex == authorDtoList.size()-1){
						
						authorDtoList.add(authorDto);
						break;
						
					}
				}
				
				
				
			}
			else {
				
				authorDtoList.add(authorDto);
				
			}
					
		}
		
		AuthorDtoList finalAuthorDtoList = new AuthorDtoList(authorDtoList);
		
		if(selection == null || selection.equals("abc")) {
			
			finalAuthorDtoList.orderByAbc();
			
		}
		else if(selection.equals("321")) {
			
			finalAuthorDtoList.orderBy321();
			
		}
		else {
			
		finalAuthorDtoList.setAuthorDtos(finalAuthorDtoList.orderByText(selection));
		
		}
		
		
		return finalAuthorDtoList;
	}

	public String saveToMySql(List<AuthorDto> authorDtoListFromFrontEnd) {
		
		String success = "";
		
		
		
		
		
		return success;
	}

	public String saveToXml(List<AuthorDto> authorDtoListFromFrontEnd) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
