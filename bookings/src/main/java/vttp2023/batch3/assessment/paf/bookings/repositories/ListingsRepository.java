package vttp2023.batch3.assessment.paf.bookings.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
	// ],
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

	// TODO: Task 4

	// TODO: Task 5

}
