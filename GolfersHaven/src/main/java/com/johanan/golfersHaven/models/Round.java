package com.johanan.golfersHaven.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="round")
public class Round {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@NotBlank
	@Size(min=2, max=25, message="Golf Course must be between 5 and 40 characters.")
	private String golfCourse;
	@NotBlank
	private String  state;
	@NotBlank
	private String tees;
	@NotBlank
	private String holesPlayed;
	@DateTimeFormat(pattern= "yyyy-MM-dd")
	private Date roundDate;
	@NotNull
	private Integer score;
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="golfers_id")
    private Golfer golfers;
 
	
	public Round() {
		super();
	}
	
	
	

	public Round(Long id,
			@NotBlank @Size(min = 2, max = 25, message = "Golf Course must be between 5 and 40 characters.") String golfCourse,
			@NotBlank String state, @NotBlank String tees, @NotBlank String holesPlayed, Date roundDate,
			@NotNull Integer score, Date createdAt, Date updatedAt, Golfer golfers) {
		super();
		this.id = id;
		this.golfCourse = golfCourse;
		this.state = state;
		this.tees = tees;
		this.holesPlayed = holesPlayed;
		this.roundDate = roundDate;
		this.score = score;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.golfers = golfers;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGolfCourse() {
		return golfCourse;
	}

	public void setGolfCourse(String golfCourse) {
		this.golfCourse = golfCourse;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTees() {
		return tees;
	}

	public void setTees(String tees) {
		this.tees = tees;
	}

	public String getHolesPlayed() {
		return holesPlayed;
	}

	public void setHolesPlayed(String holesPlayed) {
		this.holesPlayed = holesPlayed;
	}

	public Date getRoundDate() {
		return roundDate;
	}

	public void setRoundDate(Date roundDate) {
		this.roundDate = roundDate;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
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

	public Golfer getGolfers() {
		return golfers;
	}

	public void setGolfers(Golfer golfers) {
		this.golfers = golfers;
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
