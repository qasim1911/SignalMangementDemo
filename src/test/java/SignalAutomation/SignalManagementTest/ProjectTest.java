package SignalAutomation.SignalManagementTest;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


import SignalAutomation.BaseComponent.BaseComponent;
import SignalAutomation.TestComponents.ProjectPage;

public class ProjectTest extends BaseComponent {

	// LandingPage landingPage;
		ProjectPage projectPage;
		WebDriver driver;
		
		// TEST NO - 1
		@Test(dataProvider = "getDataFromJson", priority = 0)
		public void addProjectTest(HashMap<String, String> input) throws InterruptedException {
			
			driver = landingPage.login(input.get("email"), input.get("password"));
			landingPage.selectClient(input.get("client"));
			
			projectPage = new ProjectPage(driver);

			WebElement confirmMsg = projectPage.addProject(input);
			softAssert.assertTrue(confirmMsg.getText().equalsIgnoreCase("Project Created Successfully !..."));
			softAssert.assertAll();

		}

		// TEST NO - 2
		@Test(dataProvider = "getDataFromJson", priority = 2)
		public void editProjectTest(HashMap<String, String> input) throws InterruptedException {
			driver = landingPage.login(input.get("email"), input.get("password"));
			landingPage.selectClient(input.get("client"));
			projectPage = new ProjectPage(driver);
			WebElement confirmMsg = projectPage.editProject(input);
			softAssert.assertTrue(confirmMsg.getText().equalsIgnoreCase("Project Updated Successfully !..."));
			softAssert.assertAll();
		}

		// TEST NO - 3
		@Test(dataProvider = "getDataFromJson", priority = 4)
		public void deleteProjectTest(HashMap<String, String> input) throws InterruptedException {

			driver = landingPage.login(input.get("email"), input.get("password"));
			landingPage.selectClient(input.get("client"));
			
			projectPage = new ProjectPage(driver);
			WebElement confirmationMsg = projectPage.deleteProject(input);

			softAssert.assertTrue(confirmationMsg.getText().equalsIgnoreCase("Project deleted successfully!..."));
			softAssert.assertAll();

		}

		
		// searching is pending
		// TEST NO - 4
		//@Test(dataProvider = "getDataByJson1", priority = 1)
		public void searchWithInServerTest(HashMap<String, String> input)  {
			driver = landingPage.login(input.get("email"), input.get("password"));
			projectPage = new ProjectPage(driver);
			boolean flag = projectPage.searchInServer(input);
			softAssert.assertTrue(flag);
			softAssert.assertAll();

		}
		
		// TEST NO - 5 SEARCHING
		//@Test(dataProvider = "getDataByJson1", priority = 1)
		public void searchWithInPage(HashMap<String, String> input) throws InterruptedException {
			
			driver = landingPage.login(input.get("email"), input.get("password"));
			projectPage = new ProjectPage(driver);
			boolean flag = projectPage.searchWithInPage(input);
			softAssert.assertTrue(flag);
			softAssert.assertAll();
			
		}
}
