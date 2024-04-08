package SignalAutomation.TestComponents;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import SignalAutomation.AbstractComponents.AbstractComponents;

public class ProductPage extends AbstractComponents {

	WebDriver driver;

	public ProductPage(WebDriver driver) {

		super(driver);
		// System.out.println("ProductPage");
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder*='Enter  Name']")
	WebElement nameTextbox;

	@FindBy(css = "[name*='Manufacturer Name']")
	WebElement manufacturerTextbox;

	@FindBy(css = "[name*='Trade Name']")
	WebElement tradeTextbox;

	@FindBy(css = "[name*='ATC Code']")
	WebElement atcTextbox;

	@FindBy(css = "[name*='Class Drug']")
	WebElement classDropdown;

	@FindBy(css = "[name*='Description']")
	WebElement descriptionTextbox;

	@FindBy(css = "[name*='Assignee']")
	WebElement assigneeDropdown;

	@FindBy(xpath = "//span[text()='Add  Drugs']/parent::button")
	WebElement addDrugBtn;

	@FindBy(xpath = "//i[contains(@class,'fa-times')]/parent::button")
	WebElement closeBtn;

	@FindBy(css = "[role='status']")
	WebElement confirmMessage;

	@FindBy(xpath = "//table //tr//td[2]")
	List<WebElement> productList;
	
	@FindBy(xpath = "//span[contains(text(),'Edit')]/parent::li")
	WebElement editButton;
	
	@FindBy(xpath = "//span[text()='Update  Drugs']/parent::button")
	WebElement editDrugBtn;
	
	@FindBy(xpath = "//span[text()='Delete']/parent::li")
	WebElement deleteButton;
	
	@FindBy(xpath = "//span[text()='Yes']/parent::button")
	WebElement yesButton;
	
	@FindBy(xpath = "//span[text()='No']/parent::button")
	WebElement noButton;

	By selectedLink = By.cssSelector("a");
	By kebabIconBy = By.xpath("preceding-sibling::td[1]/span");

	public String addProduct(HashMap<String, String> inputData) {
		System.out.println("test start");
		int index = Integer.parseInt(inputData.get("headerLinkIndex"));
		goToNavigationLink(inputData.get("goToNavigationLink"));

		WebElement addBtnElement = goToHeaderLinks(index);
		addBtnElement.findElement(selectedLink).click();

		nameTextbox.sendKeys(inputData.get("productName"));
		manufacturerTextbox.sendKeys(inputData.get("manufaturerName"));
		tradeTextbox.sendKeys(inputData.get("tradeName"));
		atcTextbox.sendKeys(inputData.get("atcName"));

		Select selectDrug = new Select(classDropdown);
		selectDrug.selectByVisibleText(inputData.get("classDrug"));

		descriptionTextbox.sendKeys(inputData.get("productDescription"));

		Select selectAssignee = new Select(assigneeDropdown);
		selectAssignee.selectByVisibleText(inputData.get("assigneeName"));

		addDrugBtn.click();

		waitForElementVisible(confirmMessage);
		String cnfString = confirmMessage.getText();
		System.out.println("test end");

		return cnfString;

	}

	public String editProduct(HashMap<String, String> inputData) throws InterruptedException {
		String productName = inputData.get("productName");
		// int index = Integer.parseInt(inputData.get("headerLinkIndex"));
		goToNavigationLink(inputData.get("goToNavigationLink"));

		waitForElementVisible(productList.get(0));

		 WebElement matchedElement = getMatchElementFromList(productList, productName);
		WebElement kebabIconElement = matchedElement.findElement(kebabIconBy);
		
		kebabIconElement.click();
		waitForElementVisible(editButton);
		editButton.click();
		//Thread.sleep(5000);
		waitForElementVisible(nameTextbox);
		clearProduct();
		
		nameTextbox.clear();
		nameTextbox.sendKeys(inputData.get("editproductName"));
		
		manufacturerTextbox.clear();
		manufacturerTextbox.sendKeys(inputData.get("editmanufaturerName"));
		
		tradeTextbox.clear();
		tradeTextbox.sendKeys(inputData.get("edittradeName"));
		
		atcTextbox.clear();
		atcTextbox.sendKeys(inputData.get("editatcName"));

		Select selectDrug = new Select(classDropdown);
		selectDrug.selectByVisibleText(inputData.get("editclassDrug"));
		descriptionTextbox.clear();
		descriptionTextbox.sendKeys(inputData.get("editproductDescription"));

		Select selectAssignee = new Select(assigneeDropdown);
		selectAssignee.selectByVisibleText(inputData.get("editassigneeName"));
		//Thread.sleep(5000);
		editDrugBtn.click();
		
		waitForElementVisible(confirmMessage);
		String cnfMsgString = confirmMessage.getText();
		return cnfMsgString;
		

	}
	
	public String deleteProduct(HashMap<String, String> inputData) {
		String productName = inputData.get("editproductName");
		// int index = Integer.parseInt(inputData.get("headerLinkIndex"));
		goToNavigationLink(inputData.get("goToNavigationLink"));

		waitForElementVisible(productList.get(0));

		 WebElement matchedElement = getMatchElementFromList(productList, productName);
		WebElement kebabIconElement = matchedElement.findElement(kebabIconBy);
		
		kebabIconElement.click();
		deleteButton.click();
		
		waitForElementVisible(yesButton);
		yesButton.click();
		waitForElementVisible(confirmMessage);		
		String cnfMsgString = confirmMessage.getText();
		return cnfMsgString;
	}
	
	public void clearProduct() {
//		nameTextbox.clear();
		System.out.println("clear start");
//		manufacturerTextbox.clear();
//		tradeTextbox.clear();
//		atcTextbox.clear();

		Select selectDrug = new Select(classDropdown);
		selectDrug.selectByVisibleText("--Select--");

		//descriptionTextbox.clear();

		Select selectAssignee = new Select(assigneeDropdown);
		selectAssignee.selectByVisibleText("--Select--");
		System.out.println("clear end");
	}

}
