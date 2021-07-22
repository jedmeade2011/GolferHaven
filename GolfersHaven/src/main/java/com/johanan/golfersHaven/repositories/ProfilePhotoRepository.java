package com.johanan.golfersHaven.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.johanan.golfersHaven.models.Golfer;
import com.johanan.golfersHaven.models.ProfilePhoto;

@Repository
public interface ProfilePhotoRepository extends CrudRepository<ProfilePhoto, Long>{
	ProfilePhoto findByGolferP(Golfer golfer);
}
