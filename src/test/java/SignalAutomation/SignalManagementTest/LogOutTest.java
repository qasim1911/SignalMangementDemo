package SignalAutomation.SignalManagementTest;

import java.util.HashMap;

import org.testng.annotations.Test;

import SignalAutomation.BaseComponent.BaseComponent;

public class LogOutTest extends BaseComponent {

	@Test(dataProvider = "getDataFromHashMap")
	public void logOutValidation(HashMap<String, String> inputData) throws InterruptedException {
		landingPage.login(inputData.get("email"), inputData.get("password"));
		landingPage.selectClient(inputData.get("client"));
		
		landingPage.logOut();
	}
}
