package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "USERROLE")
public class UserRole {

	@Id
	@Column(name = "USERROLEID")
	private int userRoleId;

	@Column(name = "ROLENAME")
	@Size(min = 3, max = 40, message = "Role name must be between 2 and 40 characters")
	@NotNull(message = "Role name cannot be null")
	private String roleName;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USERROLES", joinColumns = @JoinColumn(name = "USERROLEID"), inverseJoinColumns = @JoinColumn(name = "USERID"))
	@JsonIgnore
	List<User> users;

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UserRole() {
	}

	public UserRole(int userRoleId, String roleName) {
		super();
		this.userRoleId = userRoleId;
		this.roleName = roleName;
	}

}
