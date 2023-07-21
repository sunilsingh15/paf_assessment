package vttp2023.batch3.assessment.paf.bookings.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import vttp2023.batch3.assessment.paf.bookings.models.Query;
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
		model.addAttribute("query", new Query());
		return "view1";
	}

	// TODO: Task 3
	@GetMapping(path = "/search")
	public String searchPage(@Valid Query query, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("countryList", service.getListofCountries());
			return "view1";
		}

		return "view2";
	}

	// TODO: Task 4

	// TODO: Task 5

}
