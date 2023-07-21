package vttp2023.batch3.assessment.paf.bookings.services;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2023.batch3.assessment.paf.bookings.models.SearchQuery;
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
	public List<Document> getSearchResults(SearchQuery query) {
		return repository.getSearchResults(query);
	}

	//TODO: Task 4
	public Document getListingByID(String id) {
		return repository.getListingByID(id);
	}

	//TODO: Task 5


}
