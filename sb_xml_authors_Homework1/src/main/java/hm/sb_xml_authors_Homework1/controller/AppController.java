package hm.sb_xml_authors_Homework1.controller;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
		
		AuthorDtoList authors = service.getAuthors();
		model.addAttribute("authorsdto", authors);
		
		return "index.html";
	}
	
	
}
