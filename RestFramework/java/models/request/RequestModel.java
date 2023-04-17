package models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"title","body","userId"})
public class RequestModel {

	
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("body")
	private String body;
	
	@JsonProperty("userId")
	private float userId;
	

	// Getter Methods

	

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public float getUserId() {
		return userId;
	}



	// Setter Methods

	

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setUserId(float userId) {
		this.userId = userId;
	}

	
	public String toString() {
		return "requestBody[title="+title +",body="+body +",userId="+userId+"]";
	}
}
