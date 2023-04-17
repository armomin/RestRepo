package models.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "checkin", "checkout" })
public class BookingDates {

	@JsonProperty("checkin")
	private Date checkin;

	@JsonProperty("checkout")
	private Date checkout;

	// Getter Methods

	public Date getCheckIn() {
		return checkin;
	}

	public Date getCheckOut() {
		return checkout;
	}

	// Setter Methods

	public void setCheckIn(Date checkin) {
		this.checkin = checkin;
	}

	public void setCheckOut(Date checkout) {
		this.checkout = checkout;
	}

	public String toString() {
		return "BookingDate[checkin" + checkin + ",checkout=" + checkout + "]";
	}
}
