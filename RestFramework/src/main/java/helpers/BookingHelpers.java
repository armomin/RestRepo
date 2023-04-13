package helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.booking.AuthModel;
import utils.ConfigManager;

import java.text.ParseException;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import constants.EndPoints;

public class BookingHelpers {

	public String BASE_URI = ConfigManager.getProperty("BOOKINGURI");
	public static Logger Log = Logger.getLogger(BookingHelpers.class.getName());
	String token;

	public BookingHelpers() {

		RestAssured.baseURI = BASE_URI;

	}

	public String postAuth() throws ParseException {

		AuthModel model = new AuthModel();

		model.setUserName("admin");
		model.setPassword("password123");

		Response resultRes = RestAssured.given().body(model).contentType(ContentType.JSON).post(EndPoints.auth)
				.andReturn();

		token = resultRes.jsonPath().get("token");
		return token;

	}

	public Response JsonPost(Object payload, String endpoint) {

		Log.info("Payload :" + new Gson().toJson(payload));
		Response resultRes = RestAssured.given().body(payload).contentType(ContentType.JSON).post(endpoint).andReturn();
		String result = resultRes.asString();
		Log.info("Header :" + resultRes.getHeader(result));
		Log.info("Status Code : " + resultRes.getStatusCode());
		Log.info("Body : " + resultRes.body().prettyPrint());

		return resultRes;

	}

	public Response getRequest(String endpoint, int value) {

		Response resultRes = RestAssured.given().contentType(ContentType.JSON).when().get(endpoint + "/" + value).then()
				.extract().response();

		String result = resultRes.asString();
		Log.info("Get Header :" + resultRes.getHeader(result));
		Log.info("Get Status Code : " + resultRes.getStatusCode());
		Log.info("Get Body : " + resultRes.body().prettyPrint());

		return resultRes;

	}

	public Response putRequest(Object payload, String endpoint, int value) throws ParseException {

		token = this.postAuth();
		System.out.println("tokenenenenen8*************************" + token);
		Response resultRes = RestAssured.given().auth().preemptive().basic("admin", "password123")
				.header("Content-Type", "application/json").and().body(payload).when().put(endpoint + "/" + value)
				.then().extract().response();

		String result = resultRes.asString();
		Log.info("Update Header :" + resultRes.getHeader(result));
		Log.info("Update Status Code : " + resultRes.getStatusCode());
		Log.info("Update Body : " + resultRes.body().prettyPrint());

		return resultRes;
	}
	
	public Response deleteRequest(String endpoint, int value) throws ParseException {

		token = this.postAuth();
		
		Response resultRes = RestAssured.given().auth().preemptive().basic("admin", "password123")
				.header("Content-Type", "application/json").and().when().delete(endpoint + "/" + value)
				.then().extract().response();

		String result = resultRes.asString();
		Log.info("Update Header :" + resultRes.getHeader(result));
		Log.info("Update Status Code : " + resultRes.getStatusCode());
		Log.info("Update Body : " + resultRes.body().prettyPrint());

		return resultRes;
	}

}
