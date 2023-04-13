package models.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "username", "checkout" })
public class AuthModel {

	@JsonProperty("username")
	private String username;

	@JsonProperty("password")
	private String password;

	// Getter Methods

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	// Setter Methods

	public void setUserName(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "Auth[username" + username + ",password=" + password + "]";
	}
}
