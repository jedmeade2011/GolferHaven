package com.johanan.golfersHaven.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.johanan.golfersHaven.models.Golfer;
import com.johanan.golfersHaven.repositories.GolferRepository;

@Component
public class GolfValidators {
	@Autowired
	private GolferRepository golfRepo;
	
	public boolean supports(Class<?> clazz) {
		return Golfer.class.equals(clazz);
	}
	public void validate(Object target, Errors errors) {
		Golfer golfer = (Golfer) target;
		if(!golfer.getPassword().equals(golfer.getPasswordConfirmation())) {
			errors.rejectValue("password", "Match", "Passwords do not match.");
		}
		if(this.golfRepo.existsByEmail(golfer.getEmail())) {
			errors.rejectValue("email", "Unique", "Email exists in database.");
		}
		if(this.golfRepo.existsByUserName(golfer.getUserName())) {
			errors.rejectValue("userName", "Different", "User name exists in database.");
		}
	}
}
