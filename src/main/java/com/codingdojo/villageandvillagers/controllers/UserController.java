package com.codingdojo.villageandvillagers.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.villageandvillagers.models.LoginUser;
import com.codingdojo.villageandvillagers.models.User;
import com.codingdojo.villageandvillagers.services.UserService;

@Controller
public class UserController {

	private static UserService userServ;

	public UserController(UserService userServ) {
		this.userServ = userServ;
	}

	@GetMapping("/")
	public String loginRegPage(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("loginUser", new LoginUser());
		return "loginPage.jsp";
	}

	@PostMapping("/register/user")
	public String processRegForm(@Valid @ModelAttribute("user") User user, BindingResult results, Model model,
			HttpSession session) {
		if (!user.getPassword().equals(user.getConfirm())) {
			results.rejectValue("password", "Match", "Confirm Password must match Password!");
		}
		if (userServ.getUser(user.getEmail()) != null) {
			results.rejectValue("email", "Unique", "Email already in use!");
		}
		if (results.hasErrors()) {
			model.addAttribute("loginUser", new LoginUser());
			return "loginPage.jsp";
		}
		User newUser = userServ.create(user);
		session.setAttribute("user", newUser);
		return "redirect:/dashboard";
	}

	@PostMapping("/login/user")
	public String processLoginFrom(@Valid @ModelAttribute("loginUser") LoginUser user, BindingResult results,
			Model model, HttpSession session) {
		User loggingUser = userServ.login(user, results);
		if (results.hasErrors()) {
			model.addAttribute("user", new User());
			return "loginPage.jsp";
		}
		session.setAttribute("user", loggingUser);
		return "redirect:/dashboard";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
