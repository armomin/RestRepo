package models.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "firstname", "lastname", "totalprice", "depositpaid", "bookingdates", "additionalneeds" })
public class BookingModel {

	@JsonProperty("firstname")
	private String firstname;

	@JsonProperty("lastname")
	private String lastname;

	@JsonProperty("totalprice")
	private float totalprice;

	@JsonProperty("depositpaid")
	private boolean depositpaid;

	@JsonProperty("bookingdates")
	private BookingDates bookingdates;

	@JsonProperty("additionalneeds")
	private String additionalneeds;

	// Getter Methods

	public String getfirstName() {
		return firstname;
	}

	public String getLastName() {
		return lastname;
	}

	public float getTotalPrice() {
		return totalprice;
	}

	public boolean getDepositPaid() {
		return depositpaid;
	}

	public BookingDates getBookingDates() {
		return bookingdates;
	}

	public String additionalneeds() {
		return additionalneeds;
	}

	// Setter Methods

	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	public void setLastName(String lastname) {
		this.lastname = lastname;
	}

	public void setTotalPrice(float totalprice) {
		this.totalprice = totalprice;
	}

	public void setDepositPaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}

	public void setBookingDates(BookingDates bookingdates) {
		this.bookingdates = bookingdates;
	}

	public void setAdditionalNeeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}

	public String toString() {
		return "Booking Model[firstname" + firstname + ",lastname=" + lastname + ",totalprice=" + totalprice
				+ ",depositpaid=" + depositpaid + ",bookingdates=" + bookingdates + ",additionalneeds="
				+ additionalneeds + "]";
	}
}
