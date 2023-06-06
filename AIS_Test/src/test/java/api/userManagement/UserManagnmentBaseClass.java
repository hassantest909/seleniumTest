package api.userManagement;

import static io.restassured.RestAssured.given;

import com.google.gson.Gson;

import models.global.Example;
import models.global.Data;
import models.global.PayLoad;
import constants.ApiUrls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserManagnmentBaseClass {
	
	public Response beforeTest() {
		
		Example example = new Example();
		example.setData(new Data());
		example.getData().setPayLoad(new PayLoad());
		example.getData().getPayLoad().setUserName("Kashi");
		example.getData().getPayLoad().setUserPassword("kashi@123");
		String json = gsonString(example);
		System.out.println(json);
		Response response = loginGetAuthToken(ApiUrls.login, json);		
		return response; 
		
	}
	
	public String gsonString(Object obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;
	}
	
	public static Response loginGetAuthToken(String Url,String Body) {
		try {
			return given()
					.contentType(ContentType.JSON)
					.body(Body)
					.when()
					.post(Url)
					.then()
					.extract().response();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return null;
		}
		
	}	
}
