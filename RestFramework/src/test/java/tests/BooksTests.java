package tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import constants.EndPoints;
import helpers.BooksHelpers;
import models.books.BooksModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BooksTests {
	public BooksHelpers helper= new BooksHelpers();
	 
	@Test
	public void postBookDetails() {
		
		BooksModel model= new BooksModel();
		
		model.setId(440);
		model.setTitle("Ars");
		model.setDescription("bzx");
		model.setPageCount(28);
		model.setExcerpt("helllo");
		model.setPublishDate("2023-03-08T11:11:21.5300965+00:00");
		System.out.println("request :"+model);
		 
		Response output=helper.JsonPost(model, EndPoints.books);
		System.out.println("response "+output.asPrettyString());
		
	}
	 


}
