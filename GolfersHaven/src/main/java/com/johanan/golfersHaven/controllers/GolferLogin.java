package com.johanan.golfersHaven.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.johanan.golfersHaven.models.Golfer;
import com.johanan.golfersHaven.services.GolferService;
import com.johanan.golfersHaven.validator.GolfValidators;


@Controller
public class GolferLogin {
	@Autowired
	private GolfValidators golfValid;
	@Autowired
	private GolferService golfServe;
	
	@GetMapping("/golfersHaven")
	public String newGolfers(@ModelAttribute("golfer")Golfer golfer) {
		return "newGolfer.jsp";
	}
	@PostMapping("/register/create")
	public String createGolfer(@Valid @ModelAttribute("golfer")Golfer golfer, BindingResult result, HttpSession session){
		golfValid.validate(golfer, result);
		if(result.hasErrors()) {
			return "newGolfer.jsp";
		}else {

			Golfer newGolfer = this.golfServe.registerThisGolfer(golfer);
			session.setAttribute("golfer_id", newGolfer.getId());
			return "redirect:/home";
		}
    }
	@GetMapping("/")
	public String golferLogin() {
		return "golferLogin.jsp";
	}
	
	@PostMapping("/login/golfer")
	public String loginGolfer(@RequestParam("loginEmail")String email, @RequestParam("loginPass")String password, RedirectAttributes redirects, HttpSession sesh){
		if(!this.golfServe.authenticateGolfer(email, password)) {
			redirects.addFlashAttribute("loginError","Invalid Username or password.");
			return "redirect:/";
		}
		Golfer golfer = this.golfServe.getByEmail(email);
		sesh.setAttribute("golfer_id", golfer.getId());
		return "redirect:/home";
	}
}
