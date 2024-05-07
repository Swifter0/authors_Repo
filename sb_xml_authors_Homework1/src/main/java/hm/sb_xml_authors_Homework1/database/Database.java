package hm.sb_xml_authors_Homework1.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import hm.sb_xml_authors_Homework1.model.SavedAuthor;

@Repository
public class Database {

	private SessionFactory sessionFactory;
	
	public Database() {
		
		Configuration config = new Configuration();
		config.configure();
		
		this.sessionFactory = config.buildSessionFactory();
		
	}

	public void saveAuthorDto(SavedAuthor currentSavedAuthor) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(currentSavedAuthor);
		
		tx.commit();
		session.close();
	}

	public List<SavedAuthor> getSavedAuthors() {
		
		List<SavedAuthor> savedAuthors = new ArrayList<>();
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		
		SelectionQuery<SavedAuthor> query =
				session.createSelectionQuery("SELECT s FROM SavedAuthor s",SavedAuthor.class);
		
		savedAuthors = query.getResultList();
		
		tx.commit();
		session.close();
		
		return savedAuthors;
	}
	
	
	
	
	
	
	
}
