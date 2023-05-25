package Tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;


public class AuthApiTest extends ApiBaseClass {
	
	@Test(description = "Unlock the Barn")
	void test_01() {
		
		Response response = postRequest0(authToken,ApiUrls.baseAuthSec+id+ApiUrls.unlockTheBarn);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("action"), "barn-unlock");
		Assert.assertEquals(response.jsonPath().getString("success"), "true");
		
	}
	
	@Test(description = "Verify that toilet seat is down")
	void test_02() {
		
		Response response = postRequest0(authToken,ApiUrls.baseAuthSec+id+ApiUrls.putToiletSeatDown);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("action"), "toiletseat-down");
		Assert.assertEquals(response.jsonPath().getString("success"), "true");
		
	}
	
	@Test(description = "Verity that the chickens are fead")
	void test_03() {
		
		Response response = postRequest0(authToken,ApiUrls.baseAuthSec+id+ApiUrls.feedYourChickes);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("message"), "Your chickens are now full and happy");
		Assert.assertEquals(response.jsonPath().getString("success"), "true");
		
	}

}
