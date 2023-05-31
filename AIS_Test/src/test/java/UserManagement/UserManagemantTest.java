package UserManagement;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class UserManagemantTest extends UserManagnmentBaseClass {
	
	private String authToken;
	
	@Test(description = "Verify login and get Authorizaion Token")
	void Test01() {
		Response response = beforeTest();
		authToken = response.jsonPath().getString("payLoad.token");
		System.out.println(response.asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().getString("responseCode"), "0000");
		Assert.assertNotNull(authToken);
		Assert.assertTrue(response.
				getTime()<=2000,"Response duration should not be greater than 2000 milliseconds.");
		System.out.println("Response TIme "+response.getTime());
	}

}
