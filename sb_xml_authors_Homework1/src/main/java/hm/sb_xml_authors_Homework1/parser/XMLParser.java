package hm.sb_xml_authors_Homework1.parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Repository;

import hm.sb_xml_authors_Homework1.dto.AuthorDto;
import hm.sb_xml_authors_Homework1.model.Author;
import hm.sb_xml_authors_Homework1.model.SavedAuthor;

@Repository
public class XMLParser {

	public List<Author> getAuthors() throws JDOMException, IOException {
		
		List<Author> authors = new ArrayList<>();
		
		SAXBuilder sb = new SAXBuilder();
		Document document = sb.build(new File("C:\\Users\\Marto\\OneDrive\\Dokumentumok\\Java_Hazifeladatok\\sigmodRecords.xml"));
		
		Element rootElement = document.getRootElement();
		Namespace ns = Namespace.getNamespace(rootElement.getNamespaceURI());
		
		List<Element> issueElements = rootElement.getChildren("issue",ns);
		
		for(int issueIndex = 0; issueIndex < issueElements.size(); issueIndex++) {
			
			Element currentIssue = issueElements.get(issueIndex);
			List<Element> articlesElements = currentIssue.getChildren("articles",ns);
			
			for(int articlesIndex = 0; articlesIndex < articlesElements.size(); articlesIndex++) {
				
				Element currentArticles = articlesElements.get(articlesIndex);
				List<Element> articleElements = currentArticles.getChildren("article",ns);
				
				for(int articleIndex = 0; articleIndex < articleElements.size(); articleIndex++) {
					
					Element currentArticle = articleElements.get(articleIndex);
					List<Element> authorsElements = currentArticle.getChildren();
					
					
					for(int authorsIndex = 0; authorsIndex < authorsElements.size(); authorsIndex++) {
						
						Element currentAuthors = authorsElements.get(authorsIndex);
						List<Element> authorElements = currentAuthors.getChildren();
						
						
						List<Author> authorsToSave = new ArrayList<>();
						Boolean isThere01 = false;
						
						for(int authorIndex = 0; authorIndex < authorElements.size(); authorIndex++) {
							
							Element currentAuthor = authorElements.get(authorIndex);
							Author author = new Author(currentAuthor.getValue());
							authorsToSave.add(author);
							
							String authorPosition = currentAuthor.getAttributeValue("position");
							
							if(authorPosition.equals("01")) {
								
								isThere01 = true;
							}
							
						}
						
						if(isThere01) {
							
							for(int index = 0; index < authorsToSave.size(); index++) {
								
								Author currentAuthor = authorsToSave.get(index);
								authors.add(currentAuthor);
								
							}
						}
					}
				}
			}
		}
		
		
		return authors;
	}

	public void saveToXml(List<SavedAuthor> savedAuthors) throws IOException {
		
		FileWriter writer = new FileWriter("C:\\Users\\Marto\\OneDrive\\Dokumentumok\\Java_Hazifeladatok_Marci\\Saved Xml\\Új Szöveges dokumentum.txt");
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		
		Document doc = new Document();
		
		Element rootElement = new Element("Authors");
		
		for(int index = 0; index < savedAuthors.size(); index++) {
			
			SavedAuthor SavedAuthor = savedAuthors.get(index);
			
			
			Element authorElement = new Element("Author");
			authorElement.setText(SavedAuthor.getName());
			authorElement.setAttribute("Appearances", SavedAuthor.getAppearance() + "");
			
			rootElement.addContent(authorElement);
			
		}
		
		doc.setRootElement(rootElement);
		
		outputter.output(doc, writer);
		writer.close();
	}

	public List<SavedAuthor> getSavedAuthors() throws JDOMException, IOException {
		
		List<SavedAuthor> savedAuthors = new ArrayList<>();
		
		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build(new File("C:\\Users\\Marto\\OneDrive\\Dokumentumok\\Java_Hazifeladatok_Marci\\Saved Xml\\Új Szöveges dokumentum.txt"));
		
		Element rootElement = doc.getRootElement();
		
		List<Element> authorElements = rootElement.getChildren("Author");
		
		for(int index = 0; index < authorElements.size(); index++) {
			
			Element currentAuthorElement = authorElements.get(index);
			SavedAuthor savedAuthor = new SavedAuthor(
					currentAuthorElement.getValue(),
					Integer.parseInt(currentAuthorElement.getAttributeValue("Appearances"))
					);
			
			savedAuthors.add(savedAuthor);
			
		}
		
		return savedAuthors;
	}
}







