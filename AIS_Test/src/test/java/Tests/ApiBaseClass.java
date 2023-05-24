package Tests;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ApiBaseClass {
	
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
