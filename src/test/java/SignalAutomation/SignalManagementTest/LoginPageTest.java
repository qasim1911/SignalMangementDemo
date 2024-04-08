package SignalAutomation.SignalManagementTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import SignalAutomation.BaseComponent.BaseComponent;

public class LoginPageTest extends BaseComponent {

	// Positive Test
	// @Test(dataProvider = "getDataFromJson")
//	public void loginTest(HashMap<String, String> inputHashMap) {
//		System.out.println("1" + inputHashMap.get("url")+"2"+inputHashMap.get("userName")+"3"+inputHashMap.get("password"));
//		//assertTrue(false);
//	}

	// Positive TestCases

	@Test(dataProvider = "getDataFromHashMap", priority = 1)
	public void loginValidation(HashMap<String, String> inputData) throws IOException, InterruptedException {

		//System.out.println(inputData.get("email") + inputData.get("password"));
		landingPage.login(inputData.get("email"), inputData.get("password"));
		landingPage.selectClient(inputData.get("client"));

	}

	// Negative TestCases

	@Test(dataProvider = "getDataFromHashMap", priority = 2)
	public void loginByInvalidUsername(HashMap<String, String> inputData) throws IOException {

		landingPage.login("syed001", inputData.get("password"));

	}

	@Test(dataProvider = "getDataFromHashMap", priority = 3)
	public void loginByInvalidPassword(HashMap<String, String> inputData) throws IOException {

		landingPage.login(inputData.get("email"), "sdfgyuh$xdcfvgbh234");

	}

	@Test(dataProvider = "getDataFromJson", priority = 4)
	public void loginByBlankUserName(HashMap<String, String> inputData) throws IOException {

		List<String> valMsgTxtString = landingPage.loginByBlankValues("", inputData.get("password"));

		Assert.assertTrue(valMsgTxtString.get(0).equalsIgnoreCase("Username is required"));

	}

	@Test(dataProvider = "getDataFromJson", priority = 5)
	public void loginByBlankPassword(HashMap<String, String> inputData) throws IOException {

		System.out.println("test1");

		List<String> valMsgTxtString = landingPage.loginByBlankValues(inputData.get("userName"), "");
		
		softAssert = new SoftAssert();
		softAssert.assertTrue(valMsgTxtString.get(0).equalsIgnoreCase("Password is required"));
		softAssert.assertAll();

	}

}
