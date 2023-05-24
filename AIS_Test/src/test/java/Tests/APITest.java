package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.json.simple.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APITest extends ApiBaseClass {

	String test = "";

	@Test
	void test_01() {
		Response response = get("https://reqres.in/api/users?page=2");
		// System.out.println(response.getStatusCode());
		// System.out.println(response.asString());
		// System.out.println(response.getBody());
		// System.out.println(response.getHeader("content-type"));
		test = response.getHeader("content-type");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	void test_02() {
		System.out.println("Test" + test);
		given().get("https://reqres.in/api/users?page=2").then()
		.statusCode(200).body("data.id[0]", equalTo(7))
				.body("data.first_name", hasItems("Michael", "Lindsay"));
	}

	@Test
	void test_03() {

		given().get("https://reqres.in/api/users?page=2").then()
		.statusCode(200).log().all();
	}

	@Test
	void test_04() {
		
		JSONObject request = new JSONObject();
		request.put("name", "morpheus");
		request.put("job", "leader");
		
		JSONObject requestHeaders = new JSONObject();
		requestHeaders.put("content-type","application/json");
		
		Response response = postRequestS(request, requestHeaders, ApiUrls.createNewUser);
		Assert.assertEquals(response.statusCode(), 201);
		
		
	}
	
	@Test
	void test_05() {
			
		String requestBody;
		try {
			requestBody = new String(Files.readAllBytes(Paths.get("C:\\Users\\Lenovo\\git\\repository\\AIS_Test\\src\\main\\resources\\TestData.json")));
			System.out.println(requestBody);
			Response response = postRequest(requestBody, ApiUrls.createNewUser);
			Assert.assertEquals(response.statusCode(), 201);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}		
		
	}
	
	@Test
	void test_06() {
			
		String requestBody;
		try {
			requestBody = new String(Files.readAllBytes(Paths.get("C:\\Users\\Lenovo\\git\\repository\\AIS_Test\\src\\main\\resources\\TestData.json")));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}		
		
	}

}
