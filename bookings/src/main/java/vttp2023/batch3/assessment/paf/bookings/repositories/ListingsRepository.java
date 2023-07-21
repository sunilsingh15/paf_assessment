package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vttp2023.batch3.assessment.paf.bookings.models.Reservation;
import vttp2023.batch3.assessment.paf.bookings.models.SearchQuery;

@Repository
public class ListingsRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MongoTemplate mongoTemplate;

	// TODO: Task 2

	// db.getCollection('listings').aggregate(
	// [
	// {
	// $group: {
	// _id: '$address.country',
	// country: { $first: '$address.country' }
	// }
	// },
	// { $project: { _id: 0, country: 1 } }
	// ]
	// );

	public List<String> getListofCountries() {
		GroupOperation group = Aggregation.group("_id")
				.first("address.country").as("country");
		ProjectionOperation project = Aggregation.project("country").andExclude("_id");

		Aggregation pipeline = Aggregation.newAggregation(group, project);

		List<Document> countryList = mongoTemplate.aggregate(pipeline, "listings", Document.class).getMappedResults();
		List<String> countryListString = countryList.stream()
				.map(c -> c.getString("country"))
				.distinct()
				.sorted()
				.collect(Collectors.toList());

		return countryListString;
	}

	// TODO: Task 3

	// db.getCollection('listings').aggregate(
	// [
	// {
	// $match: {
	// 'address.country': 'Portugal',
	// accommodates: 2,
	// price: { $gte: 50, $lte: 100 }
	// }
	// },
	// { $sort: { price: -1 } },
	// {
	// $project: {
	// _id: 0,
	// 'address.street': 1,
	// price: 1,
	// 'images.picture_url': 1
	// }
	// }
	// ]
	// );

	public List<Document> getSearchResults(SearchQuery query) {
		MatchOperation match = Aggregation.match(Criteria.where("address.country").is(query.getCountry())
				.and("accommodates").is(query.getNumberOfPersons())
				.and("price").gte(query.getRangeMin()).lte(query.getRangeMax()));

		SortOperation sort = Aggregation.sort(Sort.by("price").descending());

		ProjectionOperation project = Aggregation.project("address.street", "price", "images.picture_url");

		Aggregation pipeline = Aggregation.newAggregation(match, sort, project);

		return mongoTemplate.aggregate(pipeline, "listings", Document.class).getMappedResults();
	}

	// TODO: Task 4

	// db.getCollection('listings').aggregate(
	// [
	// { $match: { _id: '13530122' } },
	// {
	// $project: {
	// description: 1,
	// address: 1,
	// 'images.picture_url': 1,
	// price: 1,
	// amenities: 1
	// }
	// }
	// ]
	// );

	public Document getListingByID(String id) {
		MatchOperation match = Aggregation.match(Criteria.where("_id").is(id));
		ProjectionOperation project = Aggregation.project("description", "address", "images.picture_url",
				"price", "amenities");

		Aggregation pipeline = Aggregation.newAggregation(match, project);

		return mongoTemplate.aggregate(pipeline, "listings", Document.class).getMappedResults().get(0);

	}

	// TODO: Task 5
	public Integer checkVacancyByID(String id) {
		String retrieveVacancySQL = "select vacancy from acc_occupancy where acc_id = ?";
		List<String> vacancy = jdbcTemplate.queryForList(retrieveVacancySQL, String.class, id);

		return Integer.parseInt(vacancy.get(0));
	}

	@Transactional
	public int insertReservation(Reservation reservation) {
		String insertReservationSQL = "insert into reservations (resv_id, name, email, acc_id, arrival_date, duration) values (?, ?, ?, ?, ?, ?)";
		String deductVacancySQL = "update acc_occupancy set vacancy = ? where acc_id = ?";

		int updatedVacancy = checkVacancyByID(reservation.getAccomodationID()) - reservation.getDurationOfStay();

		// Insert reservation into table
		jdbcTemplate.update(insertReservationSQL, reservation.getId(), reservation.getName(),
				reservation.getEmail(), reservation.getAccomodationID(), reservation.getArrivalDate(),
				reservation.getDurationOfStay());

		// Update vacancy in occupancy table
		return jdbcTemplate.update(deductVacancySQL, updatedVacancy, reservation.getAccomodationID());
	}

}
