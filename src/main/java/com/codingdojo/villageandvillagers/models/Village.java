package com.codingdojo.villageandvillagers.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "village")
public class Village {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // generates an auto incrementing id
	private Long id;

	@NotEmpty(message = "You have to provide a village name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "villageFounder_id")
	private User villageFounder;

	@OneToMany(mappedBy = "village", fetch = FetchType.LAZY)
	private List<User> villagers;

	@Column(updatable = false)
	private Date createdAt;
	@Column(updatable = false)
	private Date updatedAt;

	public Village() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getVillageFounder() {
		return villageFounder;
	}

	public void setVillageFounder(User villageFounder) {
		this.villageFounder = villageFounder;
	}

	public List<User> getVillagers() {
		return villagers;
	}

	public void setVillagers(List<User> villagers) {
		this.villagers = villagers;
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

	@PrePersist // adds the created at date and time to sql on creation
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate // add the updated at date and time when being updated
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}
