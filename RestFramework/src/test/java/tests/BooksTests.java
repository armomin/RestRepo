package tests;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import constants.EndPoints;
import constants.FrameworkConstants;
import helpers.BooksHelpers;
import io.restassured.response.Response;
import models.books.BooksModel;
import utils.Log;

@Listeners(utils.Listener.class)
public class BooksTests {

	public BooksHelpers helper;

	@BeforeClass(alwaysRun = true)
	public void init() {
		DOMConfigurator.configure(FrameworkConstants.getLogPropertiesPath());
		helper = new BooksHelpers();
	}

	@Test
	public void postBookDetails() {

		BooksModel model = new BooksModel();

		model.setId(440);
		model.setTitle("Ars");
		model.setDescription("bzx");
		model.setPageCount(28);
		model.setExcerpt("helllo");
		model.setPublishDate("2023-03-08T11:11:21.5300965+00:00");
		System.out.println("request :" + model);
		Log.info("response " + "request :" + model);
		Response output = helper.JsonPost(model, EndPoints.books);
		System.out.println("response " + output.asPrettyString());
		Log.info("response " + output.asPrettyString());

	}

}
