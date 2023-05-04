package tests;

import java.text.ParseException;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import constants.FrameworkConstants;
import fabricator.Fabricator;
import helpers.TravelerHelpers;
import io.restassured.response.Response;
import models.traveler.Travelerinformation;

@Listeners(utils.Listener.class)
public class TravelerTests {

	public TravelerHelpers helper;
	public int travelerid;

	String fname = Fabricator.contact().firstName();
	String email = Fabricator.contact().eMail();
	String address = Fabricator.contact().address();

	@BeforeClass(alwaysRun = true)
	public void init() {
		DOMConfigurator.configure(FrameworkConstants.getLog4jxmlPath());

	}

	@Test(priority = 0, groups = "booking")
	public void postTravellerInfo() throws JsonProcessingException {

		Travelerinformation trModel = new Travelerinformation();
		helper = new TravelerHelpers();

		trModel.setName(fname);
		trModel.setEmail(email);
		trModel.setAdderes(address);

		Response output = helper.xmlPost(trModel);
		travelerid = output.xmlPath().getInt("Travelerinformation.id");
		Assert.assertEquals(output.getStatusCode(), 201);

	}

	@Test(priority = 1, dependsOnMethods = "postTravellerInfo", groups = "booking")
	public void getTravellerInfo() {

		helper = new TravelerHelpers();

		Response output = helper.getRequest(travelerid);
		Assert.assertEquals(output.getStatusCode(), 200);

	}

	@Test(priority = 2, dependsOnMethods = "postTravellerInfo", groups = "booking")
	public void updateTravellerDetails() throws ParseException, JsonProcessingException {

		Travelerinformation trModel = new Travelerinformation();
		helper = new TravelerHelpers();

		trModel.setName(fname);
		trModel.setEmail(email);
		trModel.setAdderes(address);

		Response output = helper.putRequest(trModel, travelerid);
		Assert.assertEquals(output.getStatusCode(), 200);

	}

}
