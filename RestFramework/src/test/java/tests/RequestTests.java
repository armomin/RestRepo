package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import constants.EndPoints;
import helpers.BooksHelpers;
import helpers.RequestHelpers;
import io.restassured.response.Response;
import models.books.BooksModel;
import models.books.RequestModel;

public class RequestTests {
	public RequestHelpers helper = new RequestHelpers();

	@Test
	public void postBookDetails() {

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
		
		System.out.println("response " + response.asPrettyString());
	}
}
