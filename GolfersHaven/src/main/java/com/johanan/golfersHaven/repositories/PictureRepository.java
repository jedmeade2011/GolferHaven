package com.johanan.golfersHaven.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.johanan.golfersHaven.models.Golfer;
import com.johanan.golfersHaven.models.Picture;

public interface PictureRepository extends CrudRepository<Picture, Long>{
	List<Picture> findAllByGolferPic(Golfer golfer);
}
