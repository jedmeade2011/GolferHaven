package com.johanan.golfersHaven.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.johanan.golfersHaven.models.Round;

@Repository
public interface RoundRepository extends CrudRepository<Round, Long>{
	List<Round> findAll();
	List<Round> findByGolfCourseContaining(String golfCourse);
//	List<Round> findByGolfersContaining(String golfers);
	List<Round> findByGolfCourse(String golfCourse);
	List<Round> findByGolfCourseIsNot(String golfCourse);
}
