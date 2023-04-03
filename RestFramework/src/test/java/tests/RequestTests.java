package tests;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import constants.EndPoints;
import constants.FrameworkConstants;
import helpers.BooksHelpers;
import helpers.RequestHelpers;
import io.restassured.response.Response;
import models.books.RequestModel;
import utils.Log;

@Listeners(utils.Listener.class)
public class RequestTests {

	public RequestHelpers helper;

	@BeforeClass(alwaysRun = true)
	public void init() {
		DOMConfigurator.configure(FrameworkConstants.getLogPropertiesPath());
		helper = new RequestHelpers();
	}

	@Test
	public void RequestMethod() {

		RequestModel model = new RequestModel();

		model.setTitle("foo");
		model.setBody("bar");
		model.setUserId(1);

		System.out.println("request :" + model);

		Response response = helper.JsonPost(model, EndPoints.request);

		Assert.assertEquals(201, response.statusCode());
		Assert.assertEquals("foo", response.jsonPath().getString("title"));
		Assert.assertEquals("bar", response.jsonPath().getString("body"));
		Assert.assertEquals("1", response.jsonPath().getString("userId"));
		Assert.assertEquals("101", response.jsonPath().getString("id"));
		Log.info("response " + response.asPrettyString());
		System.out.println("response " + response.asPrettyString());
	}
}
