package helpers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class BooksHelpers {

	public String BASE_URI = ConfigManager.getProperty("BOOKSURI");
	public static Logger Log= Logger.getLogger(BooksHelpers.class.getName());
	

	public BooksHelpers() {

		RestAssured.baseURI = BASE_URI;

	}

	public Response JsonPost(Object payload,String endpoint) {
		
		Log.info("payload :" + new Gson().toJson(payload));
		Response resultRes=RestAssured.given().body(payload).contentType(ContentType.JSON).post(endpoint).andReturn();
		String result=resultRes.asString();
		Log.info(""+resultRes.getHeader(result));
		Log.info(""+resultRes.getStatusCode());
		Log.info(""+resultRes.body().prettyPrint());
		
		
		
		
		return resultRes;
		
	}

}
