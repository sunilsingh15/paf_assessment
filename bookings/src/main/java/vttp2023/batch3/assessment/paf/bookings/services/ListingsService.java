package vttp2023.batch3.assessment.paf.bookings.services;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.assessment.paf.bookings.models.Query;
import vttp2023.batch3.assessment.paf.bookings.repositories.ListingsRepository;

@Service
public class ListingsService {

	@Autowired
	private ListingsRepository repository;
	
	//TODO: Task 2
	public List<String> getListofCountries() {
		return repository.getListofCountries();
	}
	
	//TODO: Task 3
	public List<Document> getSearchResults(Query query) {
		return repository.getSearchResults(query);
	}

	//TODO: Task 4
	

	//TODO: Task 5


}
