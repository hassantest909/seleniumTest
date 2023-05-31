package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Models.Account;
import Models.Data;
import Models.Example;
import Models.PayLoad;
import Models.Security;
import Models.AdditionalInformation;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import com.google.gson.Gson;
import java.util.List;
import java.util.*;

public class AuthApiTest extends ApiBaseClass {

	/*
	 * @Test(description = "Basic performance senity of unlock the barn API")
	 * 
	 * @Epic("Regression Test")
	 * 
	 * @Feature("Login Test")
	 * 
	 * @Severity(SeverityLevel.CRITICAL) void test_00() {
	 * 
	 * Response response =
	 * postRequest0(authToken,ApiUrls.baseAuthSec+id+ApiUrls.unlockTheBarn);
	 * Assert.assertEquals(response.statusCode(), 200);
	 * Assert.assertEquals(response.jsonPath().getString("action"), "barn-unlock");
	 * Assert.assertEquals(response.jsonPath().getString("success"), "true");
	 * 
	 * }
	 * 
	 * @Test(description = "Unlock the Barn") void test_01() {
	 * 
	 * Response response =
	 * postRequest0(authToken,ApiUrls.baseAuthSec+id+ApiUrls.unlockTheBarn);
	 * Assert.assertEquals(response.statusCode(), 200);
	 * Assert.assertEquals(response.jsonPath().getString("action"), "barn-unlock");
	 * Assert.assertEquals(response.jsonPath().getString("success"), "true");
	 * 
	 * }
	 * 
	 * @Test(description = "Verify that toilet seat is down") void test_02() {
	 * 
	 * Response response =
	 * postRequest0(authToken,ApiUrls.baseAuthSec+id+ApiUrls.putToiletSeatDown);
	 * Assert.assertEquals(response.statusCode(), 200);
	 * Assert.assertEquals(response.jsonPath().getString("action"),
	 * "toiletseat-down");
	 * Assert.assertEquals(response.jsonPath().getString("success"), "true");
	 * 
	 * }
	 * 
	 * @Test(description = "Verity that the chickens are fad") void test_03() {
	 * 
	 * Response response =
	 * postRequest0(authToken,ApiUrls.baseAuthSec+id+ApiUrls.feedYourChickes);
	 * Assert.assertEquals(response.statusCode(), 200);
	 * Assert.assertEquals(response.jsonPath().getString("message"),
	 * "Your chickens are now full and happy");
	 * Assert.assertEquals(response.jsonPath().getString("success"), "tru");
	 * 
	 * }
	 */

	@Test(description = "json obect")
	void test_04() {

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

		System.out.println(json);

	}

}
