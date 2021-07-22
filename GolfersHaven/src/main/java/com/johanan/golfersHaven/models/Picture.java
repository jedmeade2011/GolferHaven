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
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="picture")
public class Picture {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	@NotBlank
	private String image_url;
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pictures_id")
    private Golfer golferPic;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "picLikes",
    		joinColumns = @JoinColumn(name = "picComments_id"),
    		inverseJoinColumns = @JoinColumn(name = "picLikes_id")
    )
    private List<Golfer>golfersPost;
    
	public Picture() {
	}
	

	public Picture(String image_url, Golfer golferPic) {
		super();
		this.image_url = image_url;
		this.golferPic = golferPic;
	}

	public Picture(String image_url, Golfer golferPic,List<Golfer> golfersPost) {
		this.image_url = image_url;
		this.golferPic = golferPic;
		this.golfersPost = golfersPost;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getImage_url() {
		return image_url;
	}


	public void setImage_url(String image_url) {
		this.image_url = image_url;
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


	public Golfer getGolferPic() {
		return golferPic;
	}


	public void setGolferPic(Golfer golferPic) {
		this.golferPic = golferPic;
	}


	public List<Golfer> getGolfersPost() {
		return golfersPost;
	}


	public void setGolfersPost(List<Golfer> golfersPost) {
		this.golfersPost = golfersPost;
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
