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
	
	@Override
	public String toString() {
		return "AuthorDtoList [authorDtos=" + authorDtos + "]";
	}

	public void orderByAbc() {
		
		for(int mainIndex = 0; mainIndex < this.authorDtos.size(); mainIndex++) {
			
			AuthorDto currentAuthorDto = authorDtos.get(mainIndex);
			
			for(int secIndex = mainIndex+1; secIndex < this.authorDtos.size(); secIndex++) {
				
				AuthorDto secondAuthorDto = authorDtos.get(secIndex);
				
				if(currentAuthorDto.getName().compareTo(secondAuthorDto.getName()) > 0) {
					
					this.authorDtos.set(secIndex, currentAuthorDto);
					this.authorDtos.set(mainIndex, secondAuthorDto);
					mainIndex--;
					break;
				}
			}
		}
	}

	public void orderBy321() {
		
		for(int mainIndex = 0; mainIndex < this.authorDtos.size(); mainIndex++) {
			
			AuthorDto currentAuthorDto = this.authorDtos.get(mainIndex);
			
			for(int secIndex = mainIndex+1; secIndex < this.authorDtos.size(); secIndex++) {
				
				AuthorDto secondAuthorDto = this.authorDtos.get(secIndex);
				
				if(currentAuthorDto.getAppears() < secondAuthorDto.getAppears()) {
					
					this.authorDtos.set(secIndex, currentAuthorDto);
					this.authorDtos.set(mainIndex, secondAuthorDto);
					mainIndex--;
					break;
				}
			}
		}
	}

	public List<AuthorDto> orderByText(String selection) {
		
		for(int index = 0; index < this.authorDtos.size(); index++) {
			
			AuthorDto currentAuthorDto = this.authorDtos.get(index);
			
			if(currentAuthorDto.getName().contains(selection) == false) {
				this.authorDtos.remove(index);
				index--;
			}
			
		}
		
		
		
		return this.authorDtos;
	}
	
	
	
}
