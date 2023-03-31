package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredRequests {

    private static String requestBody = "{\n" +
            "  \"title\": \"foo\",\n" +
            "  \"body\": \"bar\",\n" +
            "  \"userId\": \"1\" \n}";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void Test2() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .extract().response();
        System.out.println("requestBody"+requestBody);

        

        Assert.assertEquals(201, response.statusCode());
        Assert.assertEquals("foo", response.jsonPath().getString("title"));
        Assert.assertEquals("bar", response.jsonPath().getString("body"));
        Assert.assertEquals("1", response.jsonPath().getString("userId"));
        Assert.assertEquals("101", response.jsonPath().getString("id"));
        
        System.out.println("response"+response.asPrettyString());
    }
}

