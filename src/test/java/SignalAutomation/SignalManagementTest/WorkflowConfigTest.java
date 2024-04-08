package SignalAutomation.SignalManagementTest;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import SignalAutomation.BaseComponent.BaseComponent;
import SignalAutomation.TestComponents.WorkflowConfig;

public class WorkflowConfigTest extends BaseComponent {
	WebDriver driver;
	WorkflowConfig workflowConfig;

	@Test(dataProvider = "getDataFromJson", priority = 1)
	public void addWorkflowConfig(HashMap<String, String> input) throws InterruptedException {
		driver = landingPage.login(input.get("email"), input.get("password"));
		landingPage.selectClient(input.get("client"));
		workflowConfig = new WorkflowConfig(driver);
		String cnfString = workflowConfig.addWorkflow(input);
		softAssert.assertEquals(cnfString, "Workflow Created Successfully !...");
		softAssert.assertAll();
		// Workflow Created Successfully !...

	}

	@Test(dataProvider = "getDataFromJson", priority = 2)
	public void editWorkflowTest(HashMap<String, String> input) throws InterruptedException {
		driver = landingPage.login(input.get("email"), input.get("password"));
		landingPage.selectClient(input.get("client"));
		workflowConfig = new WorkflowConfig(driver);
		String cnfString = workflowConfig.editWorkflow(input);
		// Thread.sleep(5000);
		softAssert.assertEquals(cnfString, "Workflow Updated Successfully !...");
		softAssert.assertAll();
	}

	@Test(dataProvider = "getDataFromJson", priority = 3)
	public void deleteWorkflow(HashMap<String, String> input) throws InterruptedException {
		driver = landingPage.login(input.get("email"), input.get("password"));
		landingPage.selectClient(input.get("client"));
		workflowConfig = new WorkflowConfig(driver);
		String cnfString = workflowConfig.deleteWorkflow(input);
		softAssert.assertEquals(cnfString, "Workflow configuration deleted successfully!...");
		softAssert.assertAll();

	}

	// @Test(dataProvider = "getDataFromJson")
	public void deleteWorkflow1(HashMap<String, String> input) throws InterruptedException {
		driver = landingPage.login(input.get("email"), input.get("password"));
		Assert.assertEquals("String", "String");
		
		//action class
		//Actions actions = new Actions(driver);
	//	actions.click(null).build().perform();
	}

}
