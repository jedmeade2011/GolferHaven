package com.johanan.golfersHaven.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="golfer")
public class Golfer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@NotBlank
	@Size(min=2, max=25, message="First name must be between 2 and 40 characters.")
	private String fname;
	@NotBlank
	@Size(min=2, max=25, message=" Last name must be between 2 and 40 characters.")
	private String lname;
	@NotNull
	private Integer handicap;
	@NotBlank
	@Size(min=2, max=25, message="User name must be between 2 and 40 characters.")
	private String userName;
	@Email
	@NotBlank
    private String email;
	@Size(min=2, max=25, message="Home course must be between 5 and 40 characters.")
	private String homeCourse;
	@Size(min=8, message="Password must be more than 8 characters long.")
	private String password;
	@Transient
    private String passwordConfirmation;
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @OneToOne(mappedBy="golferP", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private ProfilePhoto profilePhotos;
    
    @OneToMany(mappedBy="golfers", fetch = FetchType.LAZY)
    private List<Round> rounds;
    
    @OneToMany(mappedBy="golferPic", fetch = FetchType.LAZY)
    private List<Picture> pictures;
    
    @OneToMany(mappedBy="golferMessage", fetch = FetchType.LAZY)
    private List<Message> messages;
	@OneToMany(mappedBy="courses", fetch=FetchType.LAZY)
    private List<Course> courseCreator;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "likes",
    		joinColumns = @JoinColumn(name = "likerss_id"),
    		inverseJoinColumns = @JoinColumn(name = "comments_id")
    )
    private List<Message>likedMessage;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "picLikes",
    		joinColumns = @JoinColumn(name = "picLikes_id"),
    		inverseJoinColumns = @JoinColumn(name = "picComments_id")
    )
    private List<Picture>likedPics;
    
	public Golfer() {
		super();
	}
	




	public Golfer(Long id,
			@NotBlank @Size(min = 2, max = 25, message = "First name must be between 2 and 40 characters.") String fname,
			@NotBlank @Size(min = 2, max = 25, message = " Last name must be between 2 and 40 characters.") String lname,
			@NotNull Integer handicap,
			@NotBlank @Size(min = 2, max = 25, message = "User name must be between 2 and 40 characters.") String userName,
			@Email @NotBlank String email,
			@Size(min = 2, max = 25, message = "Home course must be between 5 and 40 characters.") String homeCourse,
			@Size(min = 8, message = "Password must be more than 8 characters long.") String password,
			String passwordConfirmation, Date createdAt, Date updatedAt, ProfilePhoto profilePhotos, List<Round> rounds,
			List<Picture> pictures, List<Message> messages, List<Course> courseCreator, List<Message> likedMessage,
			List<Picture> likedPics) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.handicap = handicap;
		this.userName = userName;
		this.email = email;
		this.homeCourse = homeCourse;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.profilePhotos = profilePhotos;
		this.rounds = rounds;
		this.pictures = pictures;
		this.messages = messages;
		this.courseCreator = courseCreator;
		this.likedMessage = likedMessage;
		this.likedPics = likedPics;
	}





	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public Integer getHandicap() {
		return handicap;
	}
	public void setHandicap(Integer handicap) {
		this.handicap = handicap;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomeCourse() {
		return homeCourse;
	}
	public void setHomeCourse(String homeCourse) {
		this.homeCourse = homeCourse;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<Round> getRounds() {
		return rounds;
	}
	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}
	public List<Picture> getPictures() {
		return pictures;
	}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public List<Message> getLikedMessage() {
		return likedMessage;
	}
	public void setLikedMessage(List<Message> likedMessage) {
		this.likedMessage = likedMessage;
	}
	public List<Picture> getLikedPics() {
		return likedPics;
	}
	public void setLikedPics(List<Picture> likedPics) {
		this.likedPics = likedPics;
	}
	
	public ProfilePhoto getProfilePhotos() {
		return profilePhotos;
	}

	public void setProfilePhotos(ProfilePhoto profilePhotos) {
		this.profilePhotos = profilePhotos;
	}

	public List<Course> getCourseCreator() {
		return courseCreator;
	}
	public void setCourseCreator(List<Course> courseCreator) {
		this.courseCreator = courseCreator;
	}

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
}
