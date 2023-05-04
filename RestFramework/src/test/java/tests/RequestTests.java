package tests;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import constants.FrameworkConstants;
import fabricator.Fabricator;
import helpers.RequestHelpers;
import io.restassured.response.Response;
import models.request.RequestModel;

@Listeners(utils.Listener.class)
public class RequestTests {
	ExtentTest logger;

	public RequestHelpers helper;
	String title = Fabricator.words().word();
	String body = Fabricator.words().word();
	
	int randomnumber = Fabricator.contact().hashCode();

	@BeforeClass(alwaysRun = true)
	public void init() {
		DOMConfigurator.configure(FrameworkConstants.getLog4jxmlPath());
		helper = new RequestHelpers();
	}

	@Test(priority = 0, groups = "Request")
	public void RequestMethod() {

		RequestModel model = new RequestModel();

		model.setTitle(title);
		model.setBody(body);
		model.setUserId(3);

		Response response = helper.JsonPost(model);

		Assert.assertEquals(201, response.statusCode());

		System.out.println("response " + response.asPrettyString());
	}

}
