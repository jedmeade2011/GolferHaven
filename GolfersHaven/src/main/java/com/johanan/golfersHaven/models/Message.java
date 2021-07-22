package com.johanan.golfersHaven.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	private String comment;
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Golfer golferMessage;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "likes",
    		joinColumns = @JoinColumn(name = "comments_id"),
    		inverseJoinColumns = @JoinColumn(name = "likerss_id")
    )
    private List<Golfer>golferLikes;
    
	public Message() {
		super();
	}
	
	
	
	public Message(Long id, String comment, Date createdAt, Date updatedAt, Golfer golferMessage,
			List<Golfer> golferLikes) {
		super();
		this.id = id;
		this.comment = comment;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.golferMessage = golferMessage;
		this.golferLikes = golferLikes;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public Golfer getGolferMessage() {
		return golferMessage;
	}

	public void setGolferMessage(Golfer golferMessage) {
		this.golferMessage = golferMessage;
	}
	

	public List<Golfer> getGolferLikes() {
		return golferLikes;
	}

	public void setGolferLikes(List<Golfer> golferLikes) {
		this.golferLikes = golferLikes;
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
