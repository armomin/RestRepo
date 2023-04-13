package tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import constants.EndPoints;
import constants.FrameworkConstants;
import helpers.BookingHelpers;
import helpers.BooksHelpers;
import io.restassured.response.Response;
import models.booking.AuthModel;
import models.booking.BookingDates;
import models.booking.BookingModel;
import models.books.BooksModel;
import utils.Log;

@Listeners(utils.Listener.class)
public class BookingTests {

	public BookingHelpers helper;
	public int bookingid;

	@BeforeClass(alwaysRun = true)
	public void init() {
		DOMConfigurator.configure(FrameworkConstants.getLog4jxmlPath());

	}

	

	@Test(priority = 1)
	public void postBookingDetails() throws ParseException {

		BookingModel model = new BookingModel();
		BookingDates dates = new BookingDates();
		helper = new BookingHelpers();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		dates.setCheckIn(formatter.parse("2018-02-01"));
		dates.setCheckOut(formatter.parse("2019-02-01"));

		model.setFirstName("arslan");
		model.setLastName("momin");
		model.setTotalPrice(234);
		model.setDepositPaid(true);
		model.setBookingDates(dates);
		model.setAdditionalNeeds("Booking Needs");

		Response output = helper.JsonPost(model, EndPoints.booking);
		bookingid = output.jsonPath().get("bookingid");
		Log.info("booking Id :" + bookingid);

	}

	@Test(priority = 2, dependsOnMethods = "postBookingDetails")
	public void getBookingDetails() throws ParseException {

		helper = new BookingHelpers();

		Response output = helper.getRequest(EndPoints.booking, bookingid);

	}

	@Test(priority = 3,dependsOnMethods = "postBookingDetails")
	public void updateBookingDetails() throws ParseException {

		BookingModel model = new BookingModel();
		BookingDates dates = new BookingDates();
		helper = new BookingHelpers();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		dates.setCheckIn(formatter.parse("2018-02-01"));
		dates.setCheckOut(formatter.parse("2019-02-01"));

		model.setFirstName("arrrrrrssllaann");
		model.setLastName("momin");
		model.setTotalPrice(234);
		model.setDepositPaid(true);
		model.setBookingDates(dates);
		model.setAdditionalNeeds("Booking Needs");

		Response output = helper.putRequest(model, EndPoints.booking, bookingid);

	}

}
