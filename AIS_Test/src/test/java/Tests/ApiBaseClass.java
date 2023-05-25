package Tests;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiBaseClass {
	
	public String authToken;
	public String id;
	
	@BeforeClass
	public void tearUp() {
		Response response = getAuthToken(ApiUrls.gettoken);
		authToken = response.jsonPath().getString("access_token");
		System.out.println(authToken);		
		Response response2 = getRequest(authToken,ApiUrls.myProfile);
		id = response2.jsonPath().getString("id");
		System.out.println(id);		
	}
	
	
	public static Response getAuthToken(String url) {
		return given()
				.contentType("application/x-www-form-urlencoded; charset=utf-8")
				.formParam("client_id", "cy.test")
				.formParam("client_secret","8785559410621d932f6c0d4cc60f36dd")
				.formParam("grant_type", "client_credentials")
				.when()
				.post(url)
				.then()
				.extract().response();
	}
	
	public static Response getRequest(String token,
			String url) {
       
        return given()
        		.header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .extract().response();
    }
	
	public static Response postRequest0(String token,
			String url) {
        //System.out.println(requestBody);
        return given()
        		.header("Authorization","Bearer "+token)
                .contentType(ContentType.JSON)
                .when()
                .post(url)
                .then()
                .extract().response();
    }
	
	public static Response postRequest(String requestBody,
			String url) {
        System.out.println(requestBody);
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(url)
                .then()
                .extract().response();
    }
	
	public static Response postRequestS(JSONObject requestBody,JSONObject requestHeaders,
			String url) {
        System.out.println(requestBody);
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(url)
                .then()
                .extract().response();
    }
	
}
