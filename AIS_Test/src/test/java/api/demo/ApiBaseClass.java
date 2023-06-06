package api.demo;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;

import com.google.gson.Gson;

import constants.ApiUrls;
import models.global.Account;
import models.global.AdditionalInformation;
import models.global.Data;
import models.global.Example;
import models.global.PayLoad;
import models.global.Security;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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
		
//		RestAssured.config = RestAssured.config()
//                .logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());
		
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
        //System.out.println(requestBody);
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
	
	public static Response postRequestHeaders(JSONObject headers,String requestBody,
			String url) {
        System.out.println(headers);
        return given()
        		.headers(headers)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(url)
                .then()
                .extract().response();
    }
	
	
	public String responseBody(String userName,
			String userPass,
			String securityToken,
			String bban,
			String currency,
			String iban,
			String msidn,
			String pan,
			String channel,
			String termainal,
			String referance,
			String luserName,
			String luserPassword) {
		Example example = new Example();
		example.setData(new Data());

		example.getData().setSecurity(new Security());
		example.getData().getSecurity().setUserName("Test");
		example.getData().getSecurity().setPassword("123456");
		example.getData().getSecurity().setSecurityToken("");

		example.getData().setAccount(new Account());
		example.getData().getAccount().setBban("382738237");
		example.getData().getAccount().setCurrency("PKR");
		example.getData().getAccount().setIban("PKR8237378272399239");
		example.getData().getAccount().setMsidn("2314124");
		example.getData().getAccount().setPan("72329393");

		example.getData().setChannel("channel");
		example.getData().setTerminal("Termainal");
		example.getData().setReterivalReferenceNumber("Reference");

		example.getData().setPayLoad(new PayLoad());
		example.getData().getPayLoad().setUserName("hassan");
		example.getData().getPayLoad().setUserPassword("123456");

		List<AdditionalInformation> additionalInfoList = new ArrayList<AdditionalInformation>();
		AdditionalInformation additionalInfo = new AdditionalInformation();
		additionalInfo.setInfoKey("Infokey");
		additionalInfo.setInfoValue("InfoValue");

		additionalInfoList.add(additionalInfo);

		example.getData().setAdditionalInformation(additionalInfoList);

		example.getData().setCheckSum("checkSum");

		Gson gson = new Gson();
		String json = gson.toJson(example);
		
		return json;
	}
	
	
}
