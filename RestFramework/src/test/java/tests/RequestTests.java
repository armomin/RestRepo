package tests;

import org.apache.log4j.xml.DOMConfigurator;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import constants.EndPoints;
import constants.FrameworkConstants;
import helpers.RequestHelpers;
import io.restassured.response.Response;
import models.request.RequestModel;
import utils.Log;

@Listeners(utils.Listener.class)
public class RequestTests {
	ExtentTest logger;

	public RequestHelpers helper;

	@BeforeClass(alwaysRun = true)
	public void init() {
		DOMConfigurator.configure(FrameworkConstants.getLog4jxmlPath());
		helper = new RequestHelpers();
	}
	

	@Test(priority=0)
	public void RequestMethod() {

		RequestModel model = new RequestModel();

		model.setTitle("foo");
		model.setBody("bar");
		model.setUserId(1);

		System.out.println("request :" + model);

		Response response = helper.JsonPost(model, EndPoints.request);
		Reporter.log("###########################################");
		Assert.assertEquals(201, response.statusCode());
		Assert.assertEquals("foo", response.jsonPath().getString("title"));
		Assert.assertEquals("bar", response.jsonPath().getString("body"));
		Assert.assertEquals("1", response.jsonPath().getString("userId"));
		Assert.assertEquals("101", response.jsonPath().getString("id"));
		Log.info("response " + response.asPrettyString());
		Reporter.log("###########################################");

		Log.info("Log Info");
		Log.fatal("Log fatal");
		Log.warn("Log warn");

		System.out.println("response " + response.asPrettyString());
	}

	@Test(priority=1)
	public void LogsTest() {

		Log.info("Log Info");
		Log.fatal("Log fatal");
		Log.warn("Log warn");
		
		logger.pass("Logloglog ug ");
		logger.pass("Log fatal");
		logger.pass("Log warn");
		

	}
}
