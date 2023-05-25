package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.json.simple.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;
import io.restassured.response.Response;

public class APITest extends ApiBaseClass {

	String test = "";

	@Test(description = "Response code should be 200")
	void test_01() {
		Response response = get("https://reqres.in/api/users?page=2");
		test = response.getHeader("content-type");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test(description = "Velidate First name and last name")
	void test_02() {
		System.out.println("Test" + test);
		given().get("https://reqres.in/api/users?page=2").then()
		.statusCode(200).body("data.id[0]", equalTo(7))
				.body("data.first_name", hasItems("Michael", "Lindsay"));
	}

	@Test(description = "log all json on log")
	void test_03() {

		given().get("https://reqres.in/api/users?page=2").then()
		.statusCode(200).log().all();
	}

	@Test(description = "Providing incorrect headers")
	void test_04() {
		
		JSONObject request = new JSONObject();
		request.put("name", "morpheus");
		request.put("job", "leader");
		JSONObject requestHeaders = new JSONObject();
		requestHeaders.put("content-type","application/json");
		Response response = postRequestS(request, requestHeaders, ApiUrls.createNewUser);
		Assert.assertEquals(response.statusCode(), 201);
		
		
	}
	
	@Test(description = "Get body form json file and verify")
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
	
	@Test(description = "Get and edit json file on run time")
	void test_06() {
			
		String requestBody;
		try {
			requestBody = new String(Files.readAllBytes(Paths.get("C:\\Users\\Lenovo\\git\\repository\\AIS_Test\\src\\main\\resources\\TestData.json")));
            Gson gson = new Gson();
            JSONObject jsonBody = gson.fromJson(requestBody, JSONObject.class);
            jsonBody.put("name", "TestNumber");
            String updateRequestBody = gson.toJson(jsonBody);
			System.out.println(updateRequestBody);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}		
		
	}
	
	@Test(description = "passing headers")
	void test_07() {
			
		String requestBody;
		try {
			requestBody = new String(Files.readAllBytes(Paths.get("C:\\Users\\Lenovo\\git\\repository\\AIS_Test\\src\\main\\resources\\TestData.json")));
            Gson gson = new Gson();
            JSONObject jsonBody = gson.fromJson(requestBody, JSONObject.class);
            jsonBody.put("name", "TestNumber");
            String updateRequestBody = gson.toJson(jsonBody);
			System.out.println(updateRequestBody);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}		
		
	}

}
