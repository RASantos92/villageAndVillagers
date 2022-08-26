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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "User name is required")
	@Size(min = 5, max = 15, message = "User name needs to be between 5-15 characters")
	private String userName;

	@NotEmpty(message = "Email is required")
	@Email(message = "Invalid Email!")
	private String email;

	@NotEmpty(message = "Password is required")
	@Size(min = 8, message = "Password must be 8 characters or longer")
	private String password;

	@OneToMany(mappedBy = "villageFounder", fetch = FetchType.LAZY)
	private List<Village> createdVillages;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "village_id")
	private Village village;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "roles_villagers", joinColumns = @JoinColumn(name = "villager_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> role;

	@Transient
	private String confirm;

	@Column(updatable = false)
	private Date createdAt;
	@Column(updatable = false)
	private Date updatedAt;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Village> getCreatedVillages() {
		return createdVillages;
	}

	public void setCreatedVillages(List<Village> createdVillages) {
		this.createdVillages = createdVillages;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
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