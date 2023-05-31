package UserManagement;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;

import com.google.gson.Gson;

import Models.Data;
import Models.Example;
import Models.PayLoad;
import Tests.ApiUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserManagnmentBaseClass {
	
	
	
	
	public Response beforeTest() {
		
		Example example = new Example();
		example.setData(new Data());
		example.getData().setPayLoad(new PayLoad());
		example.getData().getPayLoad().setUserName("Kashi");
		example.getData().getPayLoad().setUserPassword("kashi@123");
		Gson gson = new Gson();
		String json = gson.toJson(example);
		Response response = loginGetAuthToken(ApiUrls.login, json);		
		return response; 
		
	}
	
	
	public static Response loginGetAuthToken(String Url,String Body) {
		return given()
				.contentType(ContentType.JSON)
				.body(Body)
				.when()
				.post(Url)
				.then()
				.extract().response();
	}	
}
