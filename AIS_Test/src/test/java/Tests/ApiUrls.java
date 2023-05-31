package Tests;

public class ApiUrls {

	public static String baseUrl = "https://reqres.in";
	public static String baseAuth = "http://coop.apps.symfonycasts.com";
	public static String baseUserManagement = "http://10.0.0.13:8080/mfs_user_management/umngt";

	/*
	 * ---------------------------------- Demo API's--------------------------------------------
	 */
	public static final String baseAuthSec = "http://coop.apps.symfonycasts.com/api/";
	public static final String gettoken = baseAuth + "/token";
	public static final String myProfile = baseAuth + "/api/me";
	public static final String unlockTheBarn = "/barn-unlock";
	public static final String putToiletSeatDown = "/toiletseat-down";
	public static final String feedYourChickes = "/chickens-feed";
	public static final String createNewUser = baseUrl + "/api/users";

	/*
	 * ---------------------------- Actual User Management API's-------------------------------
	 */

	public static final String login = baseUserManagement+"/login";
}
