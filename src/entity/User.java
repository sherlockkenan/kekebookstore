package entity;

public class User {
	private String id;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String address;
	private String role;	
	
	public User(String id, String username, String password, String phone, String email,
			String address, String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.email = email;
		this.address = address;
	}
	public User(){};
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
