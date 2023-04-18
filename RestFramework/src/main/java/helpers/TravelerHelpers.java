package helpers;

import static org.testng.Assert.assertEquals;

import java.text.ParseException;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigManager;


public class TravelerHelpers {

	public String BASE_URI=ConfigManager.getProperty("TRAVELLERSURI");
	public static Logger Log= Logger.getLogger(TravelerHelpers.class.getName());
	
	public TravelerHelpers()
	{
		RestAssured.baseURI=BASE_URI;
		RestAssured.useRelaxedHTTPSValidation();
	}
	
	
	
	public Response xmlPost(Object payload,String endPoint) throws JsonProcessingException {
		
		
		Log.info("Payload :");
		String xml;
		
			xml=new XmlMapper().writeValueAsString(payload);
			Response res=RestAssured.given().body(xml).contentType(ContentType.XML).log().all().post(endPoint);
		
			Log.info("Status code : "+res.getStatusCode());
			Log.info("Post Response :"+res.asString());
			assertEquals(res.getStatusCode(), HttpStatus.SC_CREATED,"Xml Post failure");
			Log.info("Response Body : "+res.body().prettyPrint());
			Log.info("Description : "+res.xmlPath().get("Response.request_status.error_desc"));
			return  res;
			
		}
		
	
	public Response getRequest(String endpoint, int travelerid) {

		Response res = RestAssured.given().contentType(ContentType.XML).when().get(endpoint + "/" + travelerid).then()
				.extract().response();

		String result = res.asString();
		Log.info("Get Header :" + res.getHeader(result));
		Log.info("Get Status Code : " + res.getStatusCode());
		assertEquals(res.getStatusCode(),HttpStatus.SC_OK,"Xml Post failure");
		Log.info("Get Body : " + res.body().prettyPrint());

		return res;

	}
	
	
	public Response putRequest(Object payload, String endPoint, int value) throws ParseException, JsonProcessingException {

		String xml;
		xml=new XmlMapper().writeValueAsString(payload);
		Response res=RestAssured.given().body(xml).contentType(ContentType.XML).log().all().put(endPoint);
		
		String result = res.asString();
		Log.info("Update Header :" + res.getHeader(result));
		Log.info("Update Status Code : " + res.getStatusCode());
		Log.info("Update Body : " + res.body().prettyPrint());

		return res;
	}

}
