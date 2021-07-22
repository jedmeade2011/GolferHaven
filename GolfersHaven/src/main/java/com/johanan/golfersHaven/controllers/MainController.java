package com.johanan.golfersHaven.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.johanan.golfersHaven.models.Course;
import com.johanan.golfersHaven.models.Golfer;
import com.johanan.golfersHaven.models.Message;
import com.johanan.golfersHaven.models.Round;
import com.johanan.golfersHaven.services.GolferService;
import com.johanan.golfersHaven.services.MainService;

@Controller
public class MainController {
	@Autowired
	private MainService mainServe;
	@Autowired
	private GolferService golfServe;
	
    private static String UPLOADED_FOLDER = "src/main/resources/static/images/";
    private static String PROFILE_FOLDER = "src/main/resources/static/profileP/";
	
	@GetMapping("/home")
	public String homePage(Model model, HttpSession session,@ModelAttribute("message") Message message ) {
		if(session.getAttribute("golfer_id")==null) {
			return "redirect:/";		
		}else {
			Long golfId = (Long)session.getAttribute("golfer_id");
			Golfer golfers = this.golfServe.findOneId(golfId);
			
			List<Round> theseRounds = mainServe.findHomeCourse(golfers.getHomeCourse());
			List<Round> otherRounds = mainServe.findOtherCourse(golfers.getHomeCourse());
			
			model.addAttribute("golf", golfers);
			model.addAttribute("rounds", theseRounds);
			model.addAttribute("round", otherRounds);
			model.addAttribute("messages", this.mainServe.findNewestMessages());
			return "mainPage.jsp";
		}
	}
	@GetMapping("/newRound")
	public String createRound(@ModelAttribute("round") Round round, HttpSession session, Model model) {
		if(session.getAttribute("golfer_id")==null) {
			return "redirect:/";		
		}else {
			model.addAttribute("golf", this.golfServe.findOneId((Long)session.getAttribute("golfer_id")));
			return "createRound.jsp";
		}
	}
	@PostMapping("create/round/{id}")
	public String newRound(@Valid @ModelAttribute("round")Round round, BindingResult result, HttpSession session, Model model) {
		if(result.hasErrors()) {
			return "createRound";
		}else {
			round.setGolfers(this.golfServe.findOneId((Long)session.getAttribute("golfer_id")));
			this.mainServe.createRound(round);
			return "redirect:/profile/{id}";
		}
	}
	@GetMapping("/round/{id}/edit")
	public String updateRound(@PathVariable("id")Long id, @ModelAttribute("round")Round round, HttpSession session, Model model) {
		if(session.getAttribute("golfer_id")==null) {
			return "redirect:/";		
		}else {
			model.addAttribute("round", this.mainServe.findOneRound(id));
			model.addAttribute("golf", this.golfServe.findOneId((Long)session.getAttribute("golfer_id")));
			return "editRound.jsp";
		}
	}
	@PostMapping("/update/{id}")
	public String editRound(@Valid @PathVariable("id")Long id, @ModelAttribute("round")Round round, HttpSession session, BindingResult results) {
		if(results.hasErrors()) {
			return "editRound.jsp";
		}else {
			round.setGolfers(this.golfServe.findOneId((Long)session.getAttribute("golfer_id")));
			this.mainServe.updateRound(round);
			return "redirect:/home";
		}
	}
	@GetMapping("/profile/{id}")
	public String profilePage(Model model, HttpSession session, @PathVariable("id")Long id) {
		if(session.getAttribute("golfer_id")==null) {
			return "redirect:/";		
		}else {
			Golfer thisGolfer = this.golfServe.findOneId(id);
			Golfer loggedGolf = this.golfServe.findOneId((Long)session.getAttribute("golfer_id"));
			model.addAttribute("golf", loggedGolf);
			model.addAttribute("golfer", this.golfServe.findOneId(id));
			model.addAttribute("golfPics", this.mainServe.golferPictures(thisGolfer));
			model.addAttribute("onePics", this.mainServe.findPicture(id));
			model.addAttribute("prof", this.mainServe.findOnePics(thisGolfer));
			return "profile.jsp";
		}
	}
	@GetMapping("/newCourse")
	public String createCourse(@ModelAttribute("course") Course course, HttpSession session, Model model) {
		if(session.getAttribute("golfer_id")==null) {
			return "redirect:/";		
		}else {
			model.addAttribute("golf", this.golfServe.findOneId((Long)session.getAttribute("golfer_id")));
			model.addAttribute("courses", this.mainServe.findAllCourse());
			
			return "courseCreate.jsp";
		}
	}
	@PostMapping("/create/course")
	public String newCourse(@Valid @ModelAttribute("course")Course course, BindingResult result, HttpSession session, Model model) {
		if(result.hasErrors()) {
			return "courseCreate.jsp";
		}else {
			course.setCourses(this.golfServe.findOneId((Long)session.getAttribute("golfer_id")));
			this.mainServe.createCourse(course);
			return "redirect:/newCourse";
		}
	}
	@PostMapping("/profile/{id}/upload")
	public String postPic(@PathVariable("id")Long id, @RequestParam("image") MultipartFile file, HttpSession session, RedirectAttributes redirects) {
		Golfer golfer = golfServe.findOneId((Long)session.getAttribute("golfer_id"));
		if(file.isEmpty()) {
			redirects.addFlashAttribute("messag", "Upload field must not be empty");
			return "redirect:/profile/{id}";
		}
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			
			String url = "/images/" + file.getOriginalFilename();
			this.mainServe.uploadPic(golfer, url);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "redirect:/profile/{id}";
	}
	@PostMapping("/profile/{id}/profileP")
	public String profilePicture(@PathVariable("id")Long id, @RequestParam("profile") MultipartFile file, HttpSession session, RedirectAttributes redirects) {
		Golfer golfer = golfServe.findOneId((Long)session.getAttribute("golfer_id"));
		if(file.isEmpty()) {
			redirects.addFlashAttribute("messages", "Upload field must not be empty");
			return "redirect:/profile/{id}/edit";
		}
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(PROFILE_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			
			String url = "/profileP/" + file.getOriginalFilename();
			this.mainServe.uploadProfilePic(golfer, url);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "redirect:/profile/{id}";
	}
	@PostMapping("/home/messages")
	public String postMessages(@Valid @ModelAttribute("message") Message message, BindingResult result, Long id, HttpSession session) {
		Long golferId = (Long)session.getAttribute("golfer_id");
		Golfer thisGolfer = this.golfServe.findOneId(golferId);
		if(result.hasErrors()) {
			return "redirect:/home";
		}
		message.setGolferMessage(thisGolfer);
		this.mainServe.createMessage(message);
		return "redirect:/home";
	}
	@GetMapping("/liked/{id}")
	public String likeMessages(@PathVariable("id")Long id, HttpSession session) {
		this.mainServe.likedMessages(this.golfServe.findOneId((Long)session.getAttribute("golfer_id")), this.mainServe.findOneMessage(id));
		return "redirect:/home";
	}
	@GetMapping("unliked/{id}")
	public String unlikeMessages(@PathVariable("id")Long id, HttpSession session) {
		this.mainServe.unlikedMessages(this.golfServe.findOneId((Long)session.getAttribute("golfer_id")), this.mainServe.findOneMessage(id));
		return "redirect:/home";
	}
	
	@GetMapping("/course/search")
	public String search(@RequestParam("golfCourse")String golfCourse, Model model, HttpSession session) {
		if(session.getAttribute("golfer_id")==null) {
			return "redirect:/";		
		}else {
			model.addAttribute("golf", this.golfServe.findOneId((Long)session.getAttribute("golfer_id")));
			model.addAttribute("course", this.mainServe.findThisCourse(golfCourse));
			model.addAttribute("golfCourse", golfCourse);
			return "courseResults.jsp";
		}
	}
	@GetMapping("/delete/round/{id}")
	public String deleteProduct(@PathVariable("id")Long id) {
			this.mainServe.delete(id);
			return "redirect:/home";
	}
	@GetMapping("/course/{id}")
	public String course(@PathVariable("id")Long id, HttpSession session, Model model) {
		if(session.getAttribute("golfer_id")==null) {
			return "redirect:/";		
		}else {
			model.addAttribute("golf", this.golfServe.findOneId((Long)session.getAttribute("golfer_id")));
			model.addAttribute("oneCourse", this.mainServe.findOneCourse(id));
			return "course.jsp";
		}
	}
	@GetMapping("/delete/message/{id}")
	public String deleteMess(@PathVariable("id")Long id) {
			this.mainServe.deleteMessage(id);
			return "redirect:/home";
	}
	@GetMapping("/delete/picture/{id}")
	public String deletePicture(@PathVariable("id")Long id){
		this.mainServe.deletePic(id);
		return "redirect:/home";
	}
	@GetMapping("/delete/profPic/{id}")
	public String deleteProfPicture(@PathVariable("id")Long id){
		this.mainServe.deleteProfilePic(id);
		return "redirect:/home";
	}
	@GetMapping("/delete/course/{id}")
	public String deleteCourse(@PathVariable("id")Long id) {
		this.mainServe.deleteCourse(id);
		return "redirect:/newCourse";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
