package com.qa.opencart.appconstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final String LOGIN_PAGE_TITLE ="Account Login";
	public static final String ACCOUNT_PAGE_TITLE="My Account";
	
	public static final String LOGIN_PAGE_URL ="https://naveenautomationlabs.com/opencart/index.php?route=account/login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final String ACCOUNT_PAGE_URL ="https://naveenautomationlabs.com/opencart/index.php?route=account/account";
	
	public static final String ACCOUNT_PAGE_URL_FRACTION = "route=account/account";
	public static final int SHORT_DEFAULT_TIME = 5;
	public static final int MEDIUM_DEFAULT_TIME = 10;
	public static final int LONG_DEFAULT_TIME = 20;
	
	public static final int POLLING_DEFAULT_WAIT = 2;
	
	public static final int ACCOUNT_PAGE_HEADERS_COUNT =4;
	
	public static final List <String> ACCOUNT_PAGE_HEADERS = Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	
	public static final int TOTAL_IMAGE_COUNT =4;
	
	public static final String REGISTER_SUCCESS_MESSAGE="Your Account Has Been Created!";
	
	public static final String EXCEL_TEST_REGISTER_DATA_SHEET = "registerData";
	
	public static final String EXCEL_TEST_PRODUCT_SEARCH_DATA = "searchData";
			
	
	
	
}
