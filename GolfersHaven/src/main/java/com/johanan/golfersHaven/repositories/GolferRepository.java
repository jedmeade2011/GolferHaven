package com.johanan.golfersHaven.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.johanan.golfersHaven.models.Golfer;

@Repository
public interface GolferRepository extends CrudRepository<Golfer, Long>{
	Golfer findByEmail(String email);
	boolean existsByEmail(String email);
	boolean existsByUserName(String userName);

}
