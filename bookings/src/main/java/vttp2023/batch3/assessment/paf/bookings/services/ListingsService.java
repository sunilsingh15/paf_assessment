package vttp2023.batch3.assessment.paf.bookings.services;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import vttp2023.batch3.assessment.paf.bookings.models.Reservation;
import vttp2023.batch3.assessment.paf.bookings.models.SearchQuery;
import vttp2023.batch3.assessment.paf.bookings.repositories.ListingsRepository;

@Service
public class ListingsService {

	@Autowired
	private ListingsRepository repository;

	// TODO: Task 2
	public List<String> getListofCountries() {
		return repository.getListofCountries();
	}

	// TODO: Task 3
	public List<Document> getSearchResults(SearchQuery query) {
		return repository.getSearchResults(query);
	}

	// TODO: Task 4
	public Document getListingByID(String id) {
		return repository.getListingByID(id);
	}

	// TODO: Task 5
	public String submitReservation(MultiValueMap<String, String> formInputs) {

		Reservation reservation = new Reservation(UUID.randomUUID().toString().substring(0, 8),
				formInputs.getFirst("name"),
				formInputs.getFirst("email"), formInputs.getFirst("accomodationID"),
				Date.valueOf(formInputs.getFirst("arrivalDate")),
				Integer.parseInt(formInputs.getFirst("durationOfStay")));

		if (reservation.getDurationOfStay() > repository.checkVacancyByID(reservation.getAccomodationID())) {
			System.out.println("Vacancy not available for specified duration!");
		}

		if (repository.insertReservation(reservation) == 1) {
			System.out.println("Reservation added to database!");
		}

		return reservation.getId();
	}

}
