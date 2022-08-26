package com.codingdojo.villageandvillagers.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.villageandvillagers.models.Role;
import com.codingdojo.villageandvillagers.services.RoleService;
import com.codingdojo.villageandvillagers.services.UserService;

@Controller
public class RoleController {

	private static RoleService roleServ;
	private static UserService userServ;

	public RoleController(RoleService roleServ, UserService userServ) {
		this.roleServ = roleServ;
		this.userServ = userServ;
	}

	@GetMapping("/new/role")
	public String newRole(Model model, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		model.addAttribute("role", new Role());
		return "newRole.jsp";
	}

	@PostMapping("/process/role")
	public String processRole(@Valid @ModelAttribute("role") Role role, BindingResult result) {
		if (result.hasErrors()) {
			return "newRole.jsp";
		}
		roleServ.create(role);
		return "redirect:/dashboard";
	}

	@GetMapping("/add/user/role/{id}")
	public String addUserRole(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		model.addAttribute("villager", userServ.getUser(id));
		model.addAttribute("allRoles", roleServ.getAll());
		return "userRole.jsp";
	}

}
