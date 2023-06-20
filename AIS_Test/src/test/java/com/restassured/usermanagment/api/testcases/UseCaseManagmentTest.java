package com.restassured.usermanagment.api.testcases;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.restassured.constants.ApiUrls;
import com.restassured.utils.APIHeaders;
import com.restassured.utils.ConfigManager;
import com.restassured.utils.DBQueryExecutor;
import com.restassured.utils.TestUtils;
import com.restassured.models.common.Data;
import com.restassured.models.common.Example;
import com.restassured.models.common.PayLoad;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.google.gson.Gson;



public class UseCaseManagmentTest extends UserManagnmentBaseClass {
	
	private Map<String, Object> getHeaderList;
	private Example example;
	private ConfigManager getKey;
	
	@BeforeMethod(groups="Usecase_Managmnet")
	public void beforeMethod() {
		try {
			getKey = new ConfigManager();
			getHeaderList = new HashMap<String, Object>();
			getHeaderList.put("Authorization",getKey.getKeyValue("AUTH_TOKEN"));		
			getHeaderList.put("USER","HELLO");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Story("User Managment")
	@Feature("Usercase Managment")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="Usecase_Managmnet",description="Create Usecase and verify newly created usecase")
	public void createUsecase() {
				
		example = new Example();
		example.setData(new Data());
		example.getData().setPayLoad(new PayLoad());
		example.getData().getPayLoad().setTableName("TBL_TRANS_LIMIT");
		example.getData().getPayLoad().setFormName("TRANS_LIMIT");
		example.getData().getPayLoad().setRequestType("");
		example.getData().getPayLoad().setIsActive("Y");
		
		String request_json_body = TestUtils.gsonString(example);
		Response response_create_usecase = postApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_USECASES);
		System.out.println(response_create_usecase.asPrettyString());
		Assert.assertEquals(response_create_usecase.getStatusCode(), 200);
		System.out.println(example.getData().getPayLoad().getTableName());		
		Assert.assertEquals(response_create_usecase.jsonPath().getString("responseCode"), "0000");		
		Assert.assertNotNull(response_create_usecase.jsonPath().getString("payLoad.mcConfigId"),"Attribute value should not be null");
		example.getData().getPayLoad().setMcConfigId(response_create_usecase.jsonPath().getLong("payLoad.mcConfigId"));
		Assert.assertEquals(response_create_usecase.jsonPath().getString("message"), "Success");
		Assert.assertEquals(response_create_usecase.jsonPath().getString("payLoad.tableName"), example.getData().getPayLoad().getTableName());
		Assert.assertEquals(response_create_usecase.jsonPath().getString("payLoad.formName"), example.getData().getPayLoad().getFormName());
		Assert.assertEquals(response_create_usecase.jsonPath().getString("payLoad.isActive"), example.getData().getPayLoad().getIsActive());
		Assert.assertEquals(response_create_usecase.jsonPath().getString("payLoad.updateindex"), null);
	
		Response response_getall_usecases = getApiResponse(getHeaderList,ApiUrls.GET_ALL_USECASES );
		Assert.assertEquals(response_getall_usecases.jsonPath().getString("responseCode"), "0000");
		Assert.assertEquals(response_getall_usecases.jsonPath().getString("message"), "Success");
		String response_body_string = response_getall_usecases.getBody().asString();
		JsonPath jsonPath = JsonPath.from(response_body_string);
		List<Map<String, Object>> json_array = jsonPath.getList("payLoad");
		int target_mcConfigId = (int) example.getData().getPayLoad().getMcConfigId();
		Map<String, Object> target_json_object = null;
		
		for (Map<String, Object> json_object : json_array) {
		    int mcConfigId =  (Integer) json_object.get("mcConfigId");
		    if (mcConfigId == target_mcConfigId) {
		    	target_json_object = json_object;
		        break;
		    }
		}
		Assert.assertNotNull(target_json_object, "Created usecase is not verified in get api");
		Assert.assertEquals(target_json_object.get("formName"),example.getData().getPayLoad().getFormName());
		Assert.assertEquals(target_json_object.get("tableName"),example.getData().getPayLoad().getTableName());
		Assert.assertEquals(target_json_object.get("isActive"),example.getData().getPayLoad().getIsActive());
	}
	
	@Story("User Managment")
	@Feature("Usercase Managment")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="Usecase_Managmnet",description="Update Usecase and verify Updated usecase")
	public void updateUseCase() {
		
		example = new Example();
		example.setData(new Data());
		example.getData().setPayLoad(new PayLoad());
		example.getData().getPayLoad().setTableName("TBL_TRANS_LIMIT");
		example.getData().getPayLoad().setFormName("TRANS_LIMIT");
		example.getData().getPayLoad().setRequestType("");
		example.getData().getPayLoad().setIsActive("Y");
		
		String request_json_body = TestUtils.gsonString(example);
		Response response_create_usecase = postApiResponse(getHeaderList, request_json_body, ApiUrls.CREATE_USECASES);
		System.out.println(response_create_usecase.asPrettyString());
		Assert.assertEquals(response_create_usecase.getStatusCode(), 200);
		System.out.println(example.getData().getPayLoad().getTableName());		
		Assert.assertEquals(response_create_usecase.jsonPath().getString("responseCode"), "0000");		
		Assert.assertNotNull(response_create_usecase.jsonPath().getString("payLoad.mcConfigId"),"Attribute value should not be null");
		example.getData().getPayLoad().setMcConfigId(response_create_usecase.jsonPath().getLong("payLoad.mcConfigId"));
		Assert.assertEquals(response_create_usecase.jsonPath().getString("message"), "Success");
		Assert.assertEquals(response_create_usecase.jsonPath().getString("payLoad.tableName"), example.getData().getPayLoad().getTableName());
		Assert.assertEquals(response_create_usecase.jsonPath().getString("payLoad.formName"), example.getData().getPayLoad().getFormName());
		Assert.assertEquals(response_create_usecase.jsonPath().getString("payLoad.isActive"), example.getData().getPayLoad().getIsActive());
		Assert.assertEquals(response_create_usecase.jsonPath().getString("payLoad.updateindex"), null);
		
		example.getData().getPayLoad().setTableName("TBL_USER");example.getData().getPayLoad().setFormName("USER");
		example.getData().getPayLoad().setRequestType("");
		example.getData().getPayLoad().setIsActive("N");
		
		String update_json_body = TestUtils.gsonString(example);
		System.out.println("Updated Json Body ==>"+update_json_body);
		Response update_api_response = postApiResponse(getHeaderList, update_json_body, ApiUrls.UPDATE_USECASES);
		System.out.println("Response from Updated Api==>"+update_api_response.asPrettyString());
		Assert.assertEquals(update_api_response.jsonPath().getString("message"), "Success");
		System.out.println("Updated tabel name ==>"+example.getData().getPayLoad().getTableName());
		Assert.assertEquals(update_api_response.jsonPath().getString("payLoad.tableName"), example.getData().getPayLoad().getTableName());
		Assert.assertEquals(update_api_response.jsonPath().getString("payLoad.formName"), example.getData().getPayLoad().getFormName());
		Assert.assertEquals(update_api_response.jsonPath().getString("payLoad.isActive"), example.getData().getPayLoad().getIsActive());
		Assert.assertTrue(Integer.parseInt(update_api_response.jsonPath().getString("payLoad.updateindex")) > 0);
		
		
		Response response_getall_usecases = getApiResponse(getHeaderList,ApiUrls.GET_ALL_USECASES );
		Assert.assertEquals(response_getall_usecases.jsonPath().getString("responseCode"), "0000");
		Assert.assertEquals(response_getall_usecases.jsonPath().getString("message"), "Success");
		String response_body_string = response_getall_usecases.getBody().asString();
		JsonPath jsonPath = JsonPath.from(response_body_string);
		List<Map<String, Object>> jsonArray = jsonPath.getList("payLoad");
		int targetMcConfigId = (int) example.getData().getPayLoad().getMcConfigId();
		Map<String, Object> targetJsonObject = null;
		
		for (Map<String, Object> jsonObject : jsonArray) {
		    int mcConfigId =  (Integer) jsonObject.get("mcConfigId");
		    if (mcConfigId == targetMcConfigId) {
		        targetJsonObject = jsonObject;
		        break;
		    }
		}
		System.out.println("Target==>"+targetJsonObject.toString());		
		Assert.assertNotNull(targetJsonObject, "Created usecase is not verified in get api");
		Assert.assertEquals(targetJsonObject.get("formName"),example.getData().getPayLoad().getFormName());
		Assert.assertEquals(targetJsonObject.get("tableName"),example.getData().getPayLoad().getTableName());
		Assert.assertEquals(targetJsonObject.get("isActive"),example.getData().getPayLoad().getIsActive());
		
		
	}
	
	@Story("User Managment")
	@Feature("Usercase Managment")
	@Severity(SeverityLevel.CRITICAL)
	@Test(groups="Usecase_Managmnet",description="Get All Usecases")
	public void getAllUseCases() {			
		Response response = getApiResponse(getHeaderList, ApiUrls.GET_ALL_USECASES);
		System.out.println(response.asPrettyString());
	}

	@Test
	public void dbConnection() throws ClassNotFoundException, SQLException {
		System.out.println("HEADERS LIST ==>"+getHeaderList.toString());
		DBQueryExecutor executor = new DBQueryExecutor();
		JSONArray tableData = executor.getDBTables("LKP_ACCOUNT_STATUS");
		Gson gson = new Gson();
        String jsonData = gson.toJson(tableData);
        System.out.println(jsonData);
	}
}
