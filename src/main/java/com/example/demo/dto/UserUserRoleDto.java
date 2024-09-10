package com.example.demo.dto;
 
import java.util.List;
 
public class UserUserRoleDto {
	private int userId;
	private String fullName;
	private String email;
	private List<String> roleName;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public UserUserRoleDto() {
		super();
	}
	public UserUserRoleDto(int userId, String fullName, String email, List<String> roleName) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.roleName = roleName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getRoleName() {
		return roleName;
	}
	public void setRoleName(List<String> roleName) {
		this.roleName = roleName;
	}
}