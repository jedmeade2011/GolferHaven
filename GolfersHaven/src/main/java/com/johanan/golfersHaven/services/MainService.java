package com.johanan.golfersHaven.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johanan.golfersHaven.models.Course;
import com.johanan.golfersHaven.models.Golfer;
import com.johanan.golfersHaven.models.Message;
import com.johanan.golfersHaven.models.Picture;
import com.johanan.golfersHaven.models.ProfilePhoto;
import com.johanan.golfersHaven.models.Round;
import com.johanan.golfersHaven.repositories.CourseRepository;
import com.johanan.golfersHaven.repositories.MessageRepository;
import com.johanan.golfersHaven.repositories.PictureRepository;
import com.johanan.golfersHaven.repositories.ProfilePhotoRepository;
import com.johanan.golfersHaven.repositories.RoundRepository;

@Service
public class MainService {
	@Autowired
	private MessageRepository messRepo;
	@Autowired
	private RoundRepository rRepo;
	@Autowired
	private PictureRepository picRepo;
	@Autowired
	private ProfilePhotoRepository ppRepo;
	@Autowired 
	private CourseRepository cRepo;
	
	public List<Round> findAllRounds(){
		return this.rRepo.findAll();
	}
	public List<Round> findThisCourse(String course){
		return this.rRepo.findByGolfCourseContaining(course);
	}
	public List<Round> findHomeCourse(String golfCourse){
		return this.rRepo.findByGolfCourse(golfCourse);
	}
	public List<Round> findOtherCourse(String course){
		return this.rRepo.findByGolfCourseIsNot(course);
	}
	public Round createRound(Round newRound) {
		return this.rRepo.save(newRound);
	}
	public Round findOneRound(Long id) {
		return this.rRepo.findById(id).orElse(null);
	}
	public void deleteRound(Long id) {
		rRepo.deleteById(id);
	}
	public Round updateRound(Round upRound) {
		return this.rRepo.save(upRound);
	}
	public List<Message> findAllMessages(){
		return this.messRepo.findAll();
	}
	public List<Message> findNewestMessages(){
		return this.messRepo.findAllByOrderByCreatedAtDesc();
	}
	public Message createMessage(Message newMessage) {
		return this.messRepo.save(newMessage);
	}
	public Message findOneMessage(Long id) {
		return this.messRepo.findById(id).orElse(null);
	}
	public void deleteMessage(Long id) {
		this.messRepo.deleteById(id);
	}
	public void delete(Long id) {
		this.rRepo.deleteById(id);
	}
	public void likedMessages(Golfer golfer, Message message) {
		List<Golfer> golferWhoLiked = message.getGolferLikes();
		golferWhoLiked.add(golfer);
		this.messRepo.save(message);
	}
	public void unlikedMessages(Golfer golfer, Message message) {
		List<Golfer> golfersUnliked = message.getGolferLikes();
		golfersUnliked.remove(golfer);
		this.messRepo.save(message);
	}
	public void uploadPic(Golfer golfer, String url) {
		Picture newPic = new Picture(url, golfer);
		this.picRepo.save(newPic);
	}
	public Picture findPicture(Long id) {
		return this.picRepo.findById(id).orElse(null);
	}
	public List<Picture> golferPictures(Golfer golfer){
		return this.picRepo.findAllByGolferPic(golfer);
	}
	public void deletePic(Long id) {
		this.picRepo.deleteById(id);
	}
	public void likedPictures(Golfer golfer, Picture picture) {
		List<Golfer> golfersLikedPics = picture.getGolfersPost();
		golfersLikedPics.add(golfer);
		this.picRepo.save(picture);
	}
	public void unlikedPictures(Golfer golfer, Picture picture) {
		List<Golfer> golfersUnlikedPics = picture.getGolfersPost();
		golfersUnlikedPics.remove(golfer);
		this.picRepo.save(picture);
	}
	public ProfilePhoto findProfilePicture(Long id) {
		return this.ppRepo.findById(id).orElse(null);
	}
	public void uploadProfilePic(Golfer golfer, String profilePic) {
		ProfilePhoto newProfile = new ProfilePhoto(profilePic, golfer);
		this.ppRepo.save(newProfile);	
	}
	public void UpdateProfileP(Golfer golfer, String newPics) {
		ProfilePhoto upProfile = new ProfilePhoto(newPics, golfer);
		this.ppRepo.save(upProfile);
	}
	public void deleteProfilePic(Long id) {
		this.ppRepo.deleteById(id);
	}
	public ProfilePhoto findOnePics(Golfer golfer) {
		return this.ppRepo.findByGolferP(golfer);
	}
	public Course createCourse(Course course) {
		return this.cRepo.save(course);
	}
	public Course updateCourse(Course courses) {
		return this.cRepo.save(courses);
	}
	public List<Course> findAllCourse(){
		return this.cRepo.findAll();
	}
	public Course findOneCourse(Long id) {
		return this.cRepo.findById(id).orElse(null);
	}
	public void deleteCourse(Long id) {
		this.cRepo.deleteById(id);
	}

}
