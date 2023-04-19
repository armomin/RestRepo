package tests;

import java.text.ParseException;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import constants.EndPoints;
import constants.FrameworkConstants;
import fabricator.Alphanumeric;
import fabricator.Contact;
import fabricator.Fabricator;
import helpers.TravelerHelpers;
import io.restassured.response.Response;
import models.traveler.Travelerinformation;
import utils.Log;

@Listeners(utils.Listener.class)
public class TravelerTests {

	public TravelerHelpers helper;
	public int travelerid;

	@BeforeClass(alwaysRun = true)
	public void init() {
		DOMConfigurator.configure(FrameworkConstants.getLog4jxmlPath());

	}
	
	@Test(priority=0, groups="traveler")
	public void postTravellerInfo() throws JsonProcessingException {
		
		Travelerinformation trModel=new Travelerinformation();
		helper=new TravelerHelpers();
		
		Contact contact = Fabricator.contact();
		   Alphanumeric alpha = Fabricator.alphaNumeric();
						
		trModel.setName(contact.firstName());
		trModel.setEmail(contact.eMail());
		trModel.setAdderes(contact.address());
	
		
		Response output = helper.xmlPost(trModel, EndPoints.traveler);
		travelerid = output.xmlPath().getInt("Travelerinformation.id");
		Log.info("Traveler Id :" + travelerid);
	
	}
	
	@Test(priority = 1, dependsOnMethods = "postTravellerInfo", groups="traveler")
	public void getTravellerInfo()  {

		helper = new TravelerHelpers();

		Response output = helper.getRequest(EndPoints.traveler, travelerid);

	}
	
	@Test(priority = 2,dependsOnMethods = "postTravellerInfo", groups="traveler")
	public void updateTravellerDetails() throws ParseException, JsonProcessingException {

		Travelerinformation trModel=new Travelerinformation();
		helper = new TravelerHelpers();

		trModel.setName("Ammarjaa");
		trModel.setEmail("amarjaR281@gmail.com");
		trModel.setAdderes("USA");
		
		Response output = helper.putRequest(trModel, EndPoints.traveler, travelerid);

	}
	
	
}
