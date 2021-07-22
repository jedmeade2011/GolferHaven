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

@Entity
@Table(name="course")
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@NotBlank
	private String courseName;
	@NotBlank
	private String holeCount;
	@NotNull
	private Integer courseLength;
	@NotNull
	private Integer coursePar;
	@NotBlank
	private String courseCity;
	@NotBlank
	private String courseState;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="courses_id")
    private Golfer courses;
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	
	public Course() {
		super();
	}
	
	public Course(Long id, @NotBlank String courseName, @NotBlank String holeCount,
			@NotNull Integer courseLength, @NotNull Integer coursePar, @NotBlank String courseCity,
			@NotBlank String courseState, Golfer courses, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.holeCount = holeCount;
		this.courseLength = courseLength;
		this.coursePar = coursePar;
		this.courseCity = courseCity;
		this.courseState = courseState;
		this.courses = courses;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getHoleCount() {
		return holeCount;
	}

	public void setHoleCount(String holeCount) {
		this.holeCount = holeCount;
	}


	public Integer getCourseLength() {
		return courseLength;
	}

	public void setCourseLength(Integer courseLength) {
		this.courseLength = courseLength;
	}

	public String getCourseCity() {
		return courseCity;
	}

	public void setCourseCity(String courseCity) {
		this.courseCity = courseCity;
	}

	public String getCourseState() {
		return courseState;
	}

	public void setCourseState(String courseState) {
		this.courseState = courseState;
	}
	

	public Integer getCoursePar() {
		return coursePar;
	}

	public void setCoursePar(Integer coursePar) {
		this.coursePar = coursePar;
	}

	public Golfer getCourses() {
		return courses;
	}

	public void setCourses(Golfer courses) {
		this.courses = courses;
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

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
