package com.johanan.golfersHaven.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johanan.golfersHaven.models.Golfer;
import com.johanan.golfersHaven.repositories.GolferRepository;

@Service
public class GolferService {
	@Autowired
	private GolferRepository golfRepo;
	
	public Golfer registerThisGolfer(Golfer golfer) {
		String hash = BCrypt.hashpw(golfer.getPassword(), BCrypt.gensalt());
		golfer.setPassword(hash);
		return this.golfRepo.save(golfer);
	}
	public Golfer findOneId(Long id) {
		return this.golfRepo.findById(id).orElse(null);
	}
	public boolean authenticateGolfer(String email, String password) {
		Golfer golfer = this.golfRepo.findByEmail(email);
		if(golfer == null) {
			return false;
		}
		return BCrypt.checkpw(password, golfer.getPassword());
	}
	public Golfer getByEmail(String email) {
		return this.golfRepo.findByEmail(email);
	}
	public Golfer updateGolfer(Golfer golfers) {
		return this.golfRepo.save(golfers);
	}
}
