package models.traveler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Travelerinformation")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Travelerinformation {

	
	@JacksonXmlProperty(localName="name")
	private String name;
	
	@JacksonXmlProperty(localName="email")
	private String email;

	@JacksonXmlProperty(localName="adderes")
	private String adderes;
	
	// Getter Methods
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAdderes() {
		return adderes;
	}

	// Setter Methods
	
	@JacksonXmlProperty(localName="name")
	public void setName(String name) {
		this.name = name;
	}

	@JacksonXmlProperty(localName="email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JacksonXmlProperty(localName="adderes")
	public void setAdderes(String adderes) {
		this.adderes = adderes;
	}

	@Override
	public String toString() {
		return "Travelerinformation [name=" + name + ", email=" + email + ", adderes=" + adderes + "]";
	}

}
