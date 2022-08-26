package com.codingdojo.villageandvillagers.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.villageandvillagers.models.User;
import com.codingdojo.villageandvillagers.models.Village;
import com.codingdojo.villageandvillagers.services.UserService;
import com.codingdojo.villageandvillagers.services.VillageService;

@Controller
public class VillageController {

	private static VillageService villageSer;
	private static UserService userServ;

	public VillageController(VillageService villageServ, UserService userServ) {
		this.villageServ = villageServ;
		this.userServ = userServ;
	}

	@GetMapping("/new/village")
	public String newVillage(Model model, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		model.addAttribute("newVillage", new Village());
		return "newVillage.jsp";
	}

	@PostMapping("/process/village")
	public String processVillage(@Valid @ModelAttribute("newVillage") Village village, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			return "newVillage.jsp";
		}
		List<User> founder = new ArrayList<User>();
		User user = (User) session.getAttribute("user");
		founder.add(user);
		village.setVillagers(founder);
		villageServ.create(village);
		user.setVillage(village);
		userServ.update(user);
		return "redirect:/dashboard";
	}

	@GetMapping("/show/village/{id}")
	public String showVillage(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		model.addAttribute("village", villageServ.getOne(id));
		return "showVillage.jsp";
	}

}
