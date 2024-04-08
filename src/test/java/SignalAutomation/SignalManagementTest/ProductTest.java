package SignalAutomation.SignalManagementTest;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import SignalAutomation.BaseComponent.BaseComponent;
import SignalAutomation.TestComponents.ProductPage;

public class ProductTest extends BaseComponent {
	WebDriver driver;
	ProductPage productPage;

	// Positive Test
	@Test(dataProvider = "getDataFromJson", priority = 1)
	public void addProduct(HashMap<String, String> input) throws InterruptedException {
		driver = landingPage.login(input.get("email"), input.get("password"));
		landingPage.selectClient(input.get("client"));
		productPage = new ProductPage(driver);
		String confMsg = productPage.addProduct(input);
		softAssert.assertEquals(confMsg, "Product Created Successfully !...");
		softAssert.assertAll();

	}
	
	@Test(dataProvider = "getDataFromJson", priority = 2)
	public void editProduct(HashMap<String, String> input) throws InterruptedException {
		driver = landingPage.login(input.get("email"), input.get("password"));
		landingPage.selectClient(input.get("client"));
		productPage = new ProductPage(driver);
		String cnfMessageString = productPage.editProduct(input);
		softAssert.assertEquals(cnfMessageString, "Product Updated Successfully !...");
		softAssert.assertAll();
		
	}
	
	@Test(dataProvider = "getDataFromJson", priority = 3)
	public void deleteProduct(HashMap<String, String> input) throws InterruptedException {
		driver = landingPage.login(input.get("email"), input.get("password"));
		landingPage.selectClient(input.get("client"));
		productPage = new ProductPage(driver);
		String cnfMessageString =  productPage.deleteProduct(input);
		softAssert.assertEquals(cnfMessageString, "product deleted successfully!...");
		softAssert.assertAll();
		 
	}

}
