package helpers;

import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import constants.EndPoints;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigManager;

public class RequestHelpers {

	public String BASE_URI = ConfigManager.getProperty("REQUESTURI");
	public static Logger Log = Logger.getLogger(RequestHelpers.class.getName());
	String endpoint = EndPoints.request;
	
	public RequestHelpers() {

		RestAssured.baseURI = BASE_URI;

	}

	public Response JsonPost(Object payload) {

		Log.info("\nPayload :" + new Gson().toJson(payload));
		Response resultRes = given().header("Content-type", "application/json").and().body(payload).when()
				.post(endpoint).then().extract().response();
		System.out.println("requestBody :" + payload.toString());
		String result = resultRes.asString();
		Log.info("\n Header :" + resultRes.getHeader(result));
		Log.info("\n Status Code : " + resultRes.getStatusCode());
		Log.info("\n Body : " + resultRes.body().prettyPrint());

		return resultRes;

	}

}
