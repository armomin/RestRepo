package helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigManager;

import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class RequestHelpers {

	public String BASE_URI = ConfigManager.getProperty("REQUESTURI");
	public static Logger Log= Logger.getLogger(RequestHelpers.class.getName());
	

	public RequestHelpers() {

		RestAssured.baseURI = BASE_URI;

	}

	public Response JsonPost(Object payload,String endpoint) {
		
		Response response = given()
        .header("Content-type", "application/json").and().body(payload).when().post(endpoint).then().extract().response();		
		  System.out.println("requestBody :"+payload.toString());
		  Log.info(""+response.getHeader(endpoint));
			Log.info(""+response.getStatusCode());
			Log.info(""+response.body().prettyPrint());
		
		
		return response;
		
	}

}
