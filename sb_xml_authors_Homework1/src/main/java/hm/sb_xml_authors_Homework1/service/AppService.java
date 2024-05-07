package hm.sb_xml_authors_Homework1.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hm.sb_xml_authors_Homework1.database.Database;
import hm.sb_xml_authors_Homework1.dto.AuthorDto;
import hm.sb_xml_authors_Homework1.dto.AuthorDtoList;
import hm.sb_xml_authors_Homework1.model.Author;
import hm.sb_xml_authors_Homework1.model.SavedAuthor;
import hm.sb_xml_authors_Homework1.parser.XMLParser;

@Service
public class AppService {

	private XMLParser parser;
	private Database db;


	@Autowired
	public AppService(XMLParser parser, Database db) {
		super();
		this.parser = parser;
		this.db = db;
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

	public String saveToMySql() throws JDOMException, IOException {
		
		String success = "";
		
		AuthorDtoList authorDtos = getAuthors(null);
		List<AuthorDto> authorDtoList = authorDtos.getAuthorDtos();
		List<SavedAuthor> savedAuthors = new ArrayList<>();
		
		for(int index = 0; index < authorDtoList.size(); index++) {
			
			AuthorDto currentAuthorDto = authorDtoList.get(index);
			SavedAuthor savedAuthor = new SavedAuthor(currentAuthorDto.getName(),currentAuthorDto.getAppears());
			savedAuthors.add(savedAuthor);
		}
		
		for(int index = 0; index < savedAuthors.size(); index++) {
			
			SavedAuthor currentSavedAuthor = savedAuthors.get(index);
			db.saveAuthorDto(currentSavedAuthor);
			
		}
		
		success = "Saved to Database successfully";
		
		return success;
	}

	public String saveToXml() throws IOException, JDOMException {
		
		String success = "";
		
		AuthorDtoList authorDtos = getAuthors(null);
		List<AuthorDto> authorDtoList = authorDtos.getAuthorDtos();
		List<SavedAuthor> savedAuthors = new ArrayList<>();
		
		for(int index = 0; index < authorDtoList.size(); index++) {
			
			AuthorDto currentAuthorDto = authorDtoList.get(index);
			SavedAuthor savedAuthor = new SavedAuthor(currentAuthorDto.getName(),currentAuthorDto.getAppears());
			savedAuthors.add(savedAuthor);
		}
		
		parser.saveToXml(savedAuthors);
		
		success = "Saved to XML successfully";
		
		return success;
	}

	public AuthorDtoList getAuthorsFromDatabase() {
		
		AuthorDtoList authorDtos = null;
		List<AuthorDto> authorDtoList = new ArrayList<>();
		List<SavedAuthor> savedAuthors = db.getSavedAuthors();
		
		for(int index = 0; index < savedAuthors.size(); index++) {
			
			SavedAuthor currentSavedAuthor = savedAuthors.get(index);
			AuthorDto currentAuthorDto = new AuthorDto(currentSavedAuthor.getName());
			currentAuthorDto.setAppears(currentSavedAuthor.getAppearance());
			
			authorDtoList.add(currentAuthorDto);
		}
		
		authorDtos = new AuthorDtoList(authorDtoList);
		
		return authorDtos;
	}

	public AuthorDtoList getAuthorsFromXml() throws JDOMException, IOException {
		
		AuthorDtoList authorDtos = null;
		List<AuthorDto> authorDtoList = new ArrayList<>();
		List<SavedAuthor> savedAuthors = parser.getSavedAuthors();
		
		for(int index = 0; index < savedAuthors.size(); index++) {
			
			SavedAuthor currentSavedAuthor = savedAuthors.get(index);
			AuthorDto currentAuthorDto = new AuthorDto(currentSavedAuthor.getName());
			currentAuthorDto.setAppears(currentSavedAuthor.getAppearance());
			
			authorDtoList.add(currentAuthorDto);
		}
		
		authorDtos = new AuthorDtoList(authorDtoList);
		
		return authorDtos;
	}
	
	
	
	
}
