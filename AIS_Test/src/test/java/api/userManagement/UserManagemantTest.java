package api.userManagement;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import constants.ApiUrls;

import java.util.Set;
import java.util.HashSet;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;

import models.global.Example;
import models.global.Data;
import models.global.PayLoad;


public class UserManagemantTest extends UserManagnmentBaseClass {

	private String authToken;

	@Feature("Login Test")
	@Story("User Management")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify login and get Authorizaion Token",
			groups = { "Login"})
	void Test01() {
		Response response = beforeTest();
		System.out.println(response.asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		authToken = response.jsonPath().getString("payLoad.token");
		Assert.assertEquals(response.jsonPath().getString("responseCode"), "0000");
		Assert.assertNotNull(authToken);
		Assert.assertTrue(response.getTime() <= 2000,
				"Response duration should not be greater than 2000 milliseconds.");
		System.out.println("Response TIme " + response.getTime());
	}

	@Feature("Login Test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("User Management")
	@Test(description = "Verify that Login API returns unique api token every time",
			groups = { "Login" })
	void Test02() {
		for (int i = 1; i < 5; i++) {
			Response response = beforeTest();
			Assert.assertEquals(response.getStatusCode(), 200);
			authToken = response.jsonPath().getString("payLoad.token");
			Assert.assertEquals(response.jsonPath().getString("responseCode"), "0000");
			Assert.assertNotNull(authToken);
			Set<String> tokenSet = new HashSet<String>();
			Assert.assertTrue(tokenSet.add(authToken), "Auth Token is not unique");
		}
	}

	@Feature("Login Test")
	@Severity(SeverityLevel.CRITICAL)
	@Story("User Management")
	@Test(description = "Verify that user should not be able to login with invalid credentials",
			groups = { "Login" })
	void Test03() {
		Example example = new Example();
		example.setData(new Data());
		example.getData().setPayLoad(new PayLoad());
		example.getData().getPayLoad().setUserName("hassan");
		example.getData().getPayLoad().setUserPassword("hassan");
		String jsonBody = gsonString(example);
		Response response = loginGetAuthToken(ApiUrls.login, jsonBody);
		Assert.assertEquals(response.getStatusCode(), 401);
	}
}
