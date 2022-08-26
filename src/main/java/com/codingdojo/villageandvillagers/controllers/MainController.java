package com.codingdojo.villageandvillagers.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.villageandvillagers.models.Role;
import com.codingdojo.villageandvillagers.models.User;
import com.codingdojo.villageandvillagers.models.Village;
import com.codingdojo.villageandvillagers.services.RoleService;
import com.codingdojo.villageandvillagers.services.UserService;
import com.codingdojo.villageandvillagers.services.VillageService;

@Controller
public class MainController {

	private static UserService userServ;
	private static RoleService roleServ;
	private static VillageService villageServ;

	public MainController(UserService userServ, RoleService roleServ, VillageService villageServ) {
		this.userServ = userServ;
		this.roleServ = roleServ;
		this.villageServ = villageServ;
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		model.addAttribute("allVillages", villageServ.getAll());
		return "dashboard.jsp";
	}

	@PutMapping("/add/user/village")
	public String addUserVillage(@RequestParam("id") Long id, HttpSession session) {
		Village village = villageServ.getOne(id);
		User user = ((User) session.getAttribute("user"));
		user.setVillage(village);
		userServ.update(user);
		return "redirect:/dashboard";
	}

	@PutMapping("/remove/user/village")
	public String removeUserVillage(@RequestParam("villagerId") Long villagerId,
			@RequestParam("villageId") Long villageId) {
		Village village = villageServ.getOne(villageId);
		User villager = userServ.getUser(villagerId);
		village.getVillagers().remove(villager);
		villager.setVillage(null);
		userServ.update(villager);
		return "redirect:/show/village/" + villageId;
	}

	@PutMapping("/process/user/role/{id}")
	public String processUserRole(@PathVariable("id") Long villagerId, @RequestParam("role") Long roleId) {
		User user = userServ.getUser(villagerId);
		Role role = roleServ.getOne(roleId);
		user.getRole().add(role);
		userServ.update(user);
		return "redirect:/show/village/" + user.getVillage().getId();
	}

}
