package com.restassured.accounttypres.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.restassured.constants.ApiUrls;
import com.restassured.models.common.ApiModelZbox;
import com.restassured.models.common.Data;
import com.restassured.models.common.PayLoad;
import com.restassured.utils.ConfigManager;
import com.restassured.utils.TestUtils;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;

public class AccountTypesTestClass extends AccountTypesBaseClass {
	
	private Map<String, Object> getHeaderList;
	
	private ConfigManager getKey;

	@BeforeMethod
	public void beforeMethod() {
		try {
			getKey = new ConfigManager();
			getHeaderList = new HashMap<String, Object>();
			getHeaderList.put("Authorization",getKey.getKeyValue("AUTH_TOKEN"));		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Feature("Account Type")
	@Story("Account Type")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="Account_Type",description="Create account and verify newly created account")
	public void create_new_account_type() {	
		
		//Request get_kyc = getApiResponse(getHeaderList, ApiUrls.)
		
		ApiModelZbox api_body_skle = new ApiModelZbox();
		api_body_skle.setData(new Data());
		api_body_skle.getData().setPayLoad(new PayLoad());
		api_body_skle.getData().getPayLoad().setLkpAccountClassificationId("1");
		api_body_skle.getData().getPayLoad().setAccountLevelName("LEVEL 0");
		api_body_skle.getData().getPayLoad().setKycSetHeadId("24");
		api_body_skle.getData().getPayLoad().setDailyTransLimitDr("25000");
		api_body_skle.getData().getPayLoad().setDailyAmtLimitDr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyTransLimitDr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyAmtLimitDr("50000");
		api_body_skle.getData().getPayLoad().setYearlyTransLimitDr("200000");
		api_body_skle.getData().getPayLoad().setYearlyAmtLimitDr("200000");
		api_body_skle.getData().getPayLoad().setDailyTransLimitCr("25000");
		api_body_skle.getData().getPayLoad().setDailyAmtLimitCr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyTransLimitCr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyAmtLimitCr("50000");
		api_body_skle.getData().getPayLoad().setYearlyTransLimitCr("200000");
		api_body_skle.getData().getPayLoad().setYearlyAmtLimitCr("200000");
		String request_json_body = TestUtils.gsonString(api_body_skle);
		System.out.println(request_json_body.toString());
		Response response_create_Account = postApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
		assertions(response_create_Account, api_body_skle);
		
	}

	@Feature("Account Type")
	@Story("Account Type")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="Account_Type",description="Create account and update account status to In-active")
	public void create_and_update_account_type() {
		
		ApiModelZbox api_body_skle = new ApiModelZbox();
		api_body_skle.setData(new Data());
		api_body_skle.getData().setPayLoad(new PayLoad());		
		api_body_skle.getData().getPayLoad().setLkpAccountClassificationId("2");
		api_body_skle.getData().getPayLoad().setAccountLevelName("HELLO TEST");
		api_body_skle.getData().getPayLoad().setKycSetHeadId("24");
		api_body_skle.getData().getPayLoad().setDailyTransLimitDr("25000");
		api_body_skle.getData().getPayLoad().setDailyAmtLimitDr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyTransLimitDr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyAmtLimitDr("50000");
		api_body_skle.getData().getPayLoad().setYearlyTransLimitDr("200000");
		api_body_skle.getData().getPayLoad().setYearlyAmtLimitDr("200000");
		api_body_skle.getData().getPayLoad().setDailyTransLimitCr("25000");
		api_body_skle.getData().getPayLoad().setDailyAmtLimitCr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyTransLimitCr("25000");
		api_body_skle.getData().getPayLoad().setMonthlyAmtLimitCr("50000");
		api_body_skle.getData().getPayLoad().setYearlyTransLimitCr("200000");
		api_body_skle.getData().getPayLoad().setYearlyAmtLimitCr("200000");					
		String request_json_body = TestUtils.gsonString(api_body_skle);
		System.out.println(request_json_body.toString());
		Response response_create_Account = postApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_NEW_ACCOUNT_TYPE);
		assertions(response_create_Account, api_body_skle);
		api_body_skle.getData().getPayLoad().setAccountLevelId(response_create_Account.jsonPath().getString("payLoad.accountLevelId"));
		ApiModelZbox api_body_skle_update = new ApiModelZbox();
		api_body_skle_update.setData(new Data());
		api_body_skle_update.getData().setPayLoad(new PayLoad());		
		api_body_skle_update.getData().getPayLoad().setAccountLevelId(api_body_skle.getData().getPayLoad().getAccountLevelId());
		api_body_skle_update.getData().getPayLoad().setIsActive("N");
		String update_request_json_body = TestUtils.gsonString(api_body_skle_update);		
		Response inactive_api_response = postApiResponse(getHeaderList, update_request_json_body, ApiUrls.INACTIVE_ACCOUNT_TYPE);
		Assert.assertEquals(inactive_api_response.jsonPath().getString("payLoad.isActive"), "N");
		assertions(inactive_api_response, api_body_skle);
	}
	
	public void assertions(Response response_create_Account, ApiModelZbox api_body_skle) {
		
		Assert.assertNotNull(response_create_Account.jsonPath().getString("payLoad.accountLevelId"),"accountLevelId value should not be null or empty");
		//Assert.assertNotNull(response_create_Account.jsonPath().getString("payLoad.accountLevelCode"),"accountLevelCode Attribute value should not be null");
		Assert.assertNotNull(response_create_Account.jsonPath().getString("payLoad.createdate"), "createdate value should not be null or empty");
		Assert.assertTrue(TestUtils.isDateFormatValid(response_create_Account.jsonPath().getString("payLoad.createdate")),"Invalid Date Formate of payLoad.createdate"+response_create_Account.jsonPath().getString("payLoad.createdate"));
		Assert.assertNotNull(response_create_Account.jsonPath().getString("payLoad.createuser"), "Attribute value should not be null");
		Assert.assertEquals(response_create_Account.jsonPath().getString("payLoad.dailyAmtLimitCr"), api_body_skle.getData().getPayLoad().getDailyAmtLimitCr());
		Assert.assertEquals(response_create_Account.jsonPath().getString("payLoad.dailyAmtLimitDr"), api_body_skle.getData().getPayLoad().getDailyAmtLimitDr());
		Assert.assertEquals(response_create_Account.jsonPath().getString("payLoad.dailyTransLimitCr"), api_body_skle.getData().getPayLoad().getDailyTransLimitCr());
		Assert.assertEquals(response_create_Account.jsonPath().getString("payLoad.dailyTransLimitDr"), api_body_skle.getData().getPayLoad().getDailyTransLimitDr());
		Assert.assertTrue(response_create_Account.jsonPath().getString("payLoad.isActive").equals("Y") || response_create_Account.jsonPath().getString("payLoad.isActive").equals("N"));
		Assert.assertEquals(response_create_Account.jsonPath().getString("payLoad.monthlyAmtLimitCr"), api_body_skle.getData().getPayLoad().getMonthlyAmtLimitCr());
		Assert.assertEquals(response_create_Account.jsonPath().getString("payLoad.monthlyAmtLimitDr"), api_body_skle.getData().getPayLoad().getMonthlyAmtLimitDr());
		Assert.assertEquals(response_create_Account.jsonPath().getString("payLoad.monthlyTransLimitCr"), api_body_skle.getData().getPayLoad().getMonthlyTransLimitCr());
		Assert.assertEquals(response_create_Account.jsonPath().getString("payLoad.monthlyTransLimitDr"), api_body_skle.getData().getPayLoad().getMonthlyTransLimitDr());
		Assert.assertEquals(response_create_Account.jsonPath().getString("payLoad.yearlyAmtLimitCr"), api_body_skle.getData().getPayLoad().getYearlyAmtLimitCr());
		Assert.assertEquals(response_create_Account.jsonPath().getString("payLoad.yearlyAmtLimitDr"), api_body_skle.getData().getPayLoad().getYearlyAmtLimitDr());
		Assert.assertEquals(response_create_Account.jsonPath().getString("payLoad.yearlyTransLimitCr"), api_body_skle.getData().getPayLoad().getYearlyTransLimitCr());
		Assert.assertEquals(response_create_Account.jsonPath().getString("payLoad.yearlyTransLimitDr"), api_body_skle.getData().getPayLoad().getYearlyTransLimitDr());		
		Assert.assertNotNull(response_create_Account.jsonPath().getString("payLoad.lkpStatus.statusId"),"Attribute value should not be null");	
		Assert.assertEquals(response_create_Account.jsonPath().getString("payLoad.lkpAccountClassification.accountClassificationId"), api_body_skle.getData().getPayLoad().getLkpAccountClassificationId());
		//Assert.assertNotNull(response_create_Account.jsonPath().getString("payLoad.lkpAccountClassification.accountClassificationCode"), "Attribute value should not be null");
		//Assert.assertNotNull(response_create_Account.jsonPath().getString("payLoad.lkpAccountClassification.createdate"), "lkpAccountClassification.createdate Should not be null");
		//Assert.assertNotNull(response_create_Account.jsonPath().getString("payLoad.lkpAccountClassification.createuser"), "lkpAccountClassification.createuser should not be null");
		//Assert.assertTrue(response_create_Account.jsonPath().getString("payLoad.lkpAccountClassification.isActive").equals("Y") || response_create_Account.jsonPath().getString("payLoad.lkpAccountClassification.isActive").equals("N"));		
		Assert.assertEquals(response_create_Account.jsonPath().getString("payLoad.tblKycSetHead.kycSetHeadId"), api_body_skle.getData().getPayLoad().getKycSetHeadId());
		//Assert.assertNotNull(response_create_Account.jsonPath().getString("payLoad.tblKycSetHead.createdate"),"tblKycSetHead.createdate value should not be null");
		//Assert.assertTrue(TestUtils.isDateFormatValid(response_create_Account.jsonPath().getString("payLoad.tblKycSetHead.createdate")),"Invalid Date Formate of tblKycSetHead.createdate"+response_create_Account.jsonPath().getString("payLoad.createdate"));
		//Assert.assertNotNull(response_create_Account.jsonPath().getString("payLoad.tblKycSetHead.createuser"), "tblKycSetHead.createuser value should not be null");			
		
	}
	

}
