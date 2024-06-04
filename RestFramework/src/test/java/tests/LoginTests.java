package tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import constants.EndPoints;
import constants.FrameworkConstants;
import fabricator.Fabricator;
import helpers.LoginHelpers;
import io.restassured.response.Response;
import junit.framework.Assert;
import models.booking.AuthModel;
import utils.Listener;

@Listeners(utils.Listener.class)
public class LoginTests {

	public LoginHelpers helper;
	ExtentTest logger;
	Listener lisetener;
	String uname = "jamesd";
	String password = "jamesdpass";


	@BeforeClass(alwaysRun = true)
	public void init() {
		DOMConfigurator.configure(FrameworkConstants.getLog4jxmlPath());

	}

	@Test(priority = 0)
	public void getLoginDetails() throws ParseException {

		AuthModel model = new AuthModel();
		helper = new LoginHelpers();

		model.setUserName(uname);
		model.setPassword(password);
		
		Response output = helper.JsonPost(model);
		Assert.assertEquals(output.getStatusCode(), 200);

	}

	

}
