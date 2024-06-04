package helpers;

import java.text.ParseException;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import constants.EndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigManager;

public class LoginHelpers {

	public String BASE_URI = ConfigManager.getProperty("LOGINURI");
	public static Logger Log = Logger.getLogger(LoginHelpers.class.getName());
	String endpoint = EndPoints.login;

	public LoginHelpers() {

		RestAssured.baseURI = BASE_URI;

	}

	

	public Response JsonPost(Object payload) {

		Log.info("\n Payload :" + new Gson().toJson(payload));
		Response resultRes = RestAssured.given().body(payload).contentType(ContentType.JSON).post(endpoint).andReturn();
		String result = resultRes.asString();
		Log.info("\n Header :" + resultRes.getHeader(result));
		Log.info("\n Status Code : " + resultRes.getStatusCode());
		Log.info("\n Body : " + resultRes.body().prettyPrint());

		return resultRes;

	}

	public Response getRequest(int value) {

		Response resultRes = RestAssured.given().contentType(ContentType.JSON).when().get(endpoint + "/" + value).then()
				.extract().response();

		String result = resultRes.asString();
		Log.info("\n Header :" + resultRes.getHeader(result));
		Log.info("\n Status Code : " + resultRes.getStatusCode());
		Log.info("\n Body : " + resultRes.body().prettyPrint());


		return resultRes;

	}

	public Response putRequest(Object payload, int value) throws ParseException {

		Response resultRes = RestAssured.given().auth().preemptive().basic("admin", "password123")
				.header("Content-Type", "application/json").and().body(payload).when().put(endpoint + "/" + value)
				.then().extract().response();

		String result = resultRes.asString();
		Log.info("\n Header :" + resultRes.getHeader(result));
		Log.info("\n Status Code : " + resultRes.getStatusCode());
		Log.info("\n Body : " + resultRes.body().prettyPrint());


		return resultRes;
	}

	public Response deleteRequest(String endpoint, int value) throws ParseException {

		Response resultRes = RestAssured.given().auth().preemptive().basic("admin", "password123")
				.header("Content-Type", "application/json").and().when().delete(endpoint + "/" + value).then().extract()
				.response();

		String result = resultRes.asString();
		Log.info("\n Header :" + resultRes.getHeader(result));
		Log.info("\n Status Code : " + resultRes.getStatusCode());
		Log.info("\n Body : " + resultRes.body().prettyPrint());


		return resultRes;
	}

	public Response patchtRequest(Object payload,  int value) throws ParseException {

		Response resultRes = RestAssured.given().auth().preemptive().basic("jamesd", "jamesdpass")
				.header("Content-Type", "application/json").and().body(payload).when().patch(endpoint + "/" + value)
				.then().extract().response();

		String result = resultRes.asString();
		Log.info("\n Header :" + resultRes.getHeader(result));
		Log.info("\n Status Code : " + resultRes.getStatusCode());
		Log.info("\n Body : " + resultRes.body().prettyPrint());


		return resultRes;
	}

}
