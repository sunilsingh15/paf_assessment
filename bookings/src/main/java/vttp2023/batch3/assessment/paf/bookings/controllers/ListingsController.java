package vttp2023.batch3.assessment.paf.bookings.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import vttp2023.batch3.assessment.paf.bookings.models.SearchQuery;
import vttp2023.batch3.assessment.paf.bookings.services.ListingsService;

@Controller
@RequestMapping
public class ListingsController {

	@Autowired
	private ListingsService service;

	// TODO: Task 2
	@GetMapping
	public String landingPage(Model model) {
		model.addAttribute("countryList", service.getListofCountries());
		model.addAttribute("query", new SearchQuery());
		return "view1";
	}

	// TODO: Task 3
	@GetMapping(path = "/search")
	public String searchPage(@Valid SearchQuery query, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("countryList", service.getListofCountries());
			return "view1";
		}

		model.addAttribute("country", query.getCountry());
		model.addAttribute("searchResults", service.getSearchResults(query));
		return "view2";
	}

	// TODO: Task 4
	@GetMapping(path = "/listing/{listingID}")
	public String listingPage(@PathVariable String listingID, Model model) {
		System.out.println("------------------------->" + service.getListingByID(listingID));
		model.addAttribute("listing", service.getListingByID(listingID));
		return "view3";
	}

	// TODO: Task 5

}
