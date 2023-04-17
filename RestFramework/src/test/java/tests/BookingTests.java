package tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import constants.EndPoints;
import constants.FrameworkConstants;
import helpers.BookingHelpers;
import io.restassured.response.Response;
import junit.framework.Assert;
import models.booking.BookingDates;
import models.booking.BookingModel;


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

		Response output = helper.JsonPost(model);

		bookingid = output.jsonPath().get("bookingid");
		Assert.assertEquals(output.getStatusCode(), 200);

	}

	@Test(priority = 2, dependsOnMethods = "postBookingDetails")
	public void getBookingDetails() throws ParseException {

		helper = new BookingHelpers();

		Response output = helper.getRequest(bookingid);
		Assert.assertEquals(output.getStatusCode(), 200);

	}

	@Test(priority = 3, dependsOnMethods = "postBookingDetails")
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

		Response output = helper.putRequest(model, bookingid);
		Assert.assertEquals(output.getStatusCode(), 200);

	}

	@Test(priority = 4, dependsOnMethods = "postBookingDetails")
	public void partialupdateBookingDetails() throws ParseException {

		BookingModel model = new BookingModel();
		BookingDates dates = new BookingDates();
		helper = new BookingHelpers();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		dates.setCheckIn(formatter.parse("2018-02-01"));
		dates.setCheckOut(formatter.parse("2019-02-01"));

		model.setFirstName("momin");
		model.setLastName("momin");
		Response getoutput = helper.patchtRequest(model, bookingid);
		Assert.assertEquals(getoutput.getStatusCode(), 200);

	}

	@Test(priority = 5)
	public void deleteBookingDetails() throws ParseException {
		helper = new BookingHelpers();
		Response output = helper.deleteRequest(EndPoints.booking, bookingid);
		Assert.assertEquals(output.getStatusCode(), 201);
		Response getoutput = helper.getRequest(bookingid);
		Assert.assertEquals(getoutput.getStatusCode(), 404);

	}

}
