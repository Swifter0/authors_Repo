package hm.sb_xml_authors_Homework1.controller;

import java.io.IOException;
import java.util.List;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hm.sb_xml_authors_Homework1.dto.AuthorDto;
import hm.sb_xml_authors_Homework1.dto.AuthorDtoList;
import hm.sb_xml_authors_Homework1.service.AppService;

@Controller
public class AppController {

	private AppService service;

	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/")
	public String getAuthors(Model model) throws JDOMException, IOException {
		
		AuthorDtoList authors = service.getAuthors(null);
		model.addAttribute("authorsdto", authors);
		
		return "index.html";
	}
	
	@PostMapping("/chooseview")
	public String getAuthorsByChoseView(
			Model model,
			@RequestParam("abcor321") String selection
			) throws JDOMException, IOException {
		
		AuthorDtoList authorDtoList = service.getAuthors(selection);
		model.addAttribute("authorsdto", authorDtoList);
		
		return "index.html";
	}
	
	@PostMapping("/selection")
	public String getAuthorsBySelection(
			Model model,
			@RequestParam("text") String text
			) throws JDOMException, IOException {
		
		AuthorDtoList authorsDtoList = service.getAuthors(text);
		model.addAttribute("authorsdto", authorsDtoList);
		
		return "index.html";
	}
	
	@PostMapping("/save") 
	public String saveData(
			Model model,
			@RequestParam("list") List<AuthorDto> authorDtoListFromFrontEnd,
			@RequestParam("option") String option
			) {
		
		
		
		AuthorDtoList authorDtoList = new AuthorDtoList(authorDtoListFromFrontEnd);
		model.addAttribute("authordtolist", authorDtoList);
		
		String success = "";
		
		if(option.equals("sql")) {
			
			success = service.saveToMySql(authorDtoListFromFrontEnd);
			
		}
		else if(option.equals("xml")) {
			
			success = service.saveToXml(authorDtoListFromFrontEnd);
			
		}
		
		model.addAttribute("success", success);
		
		return "index.html";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
