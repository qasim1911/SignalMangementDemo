package SignalAutomation.TestComponents;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import SignalAutomation.AbstractComponents.AbstractComponents;

public class LandingPageComponent extends AbstractComponents {

	WebDriver driver;

	public LandingPageComponent(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void goToUrl(String url) {
		driver.get(url);

	}

	@FindBy(css = "[name*='Username']")
	WebElement username;

	@FindBy(css = "[name*='Password']")
	WebElement password;

	@FindBy(xpath = "//div[contains(@class,'mt-2')]/button")
	WebElement submitBtn;

	@FindBy(css = ".invalid-feedback")
	List<WebElement> validationErrorMsg;

	@FindBy(xpath = "//div[text()='Username is required']")
	WebElement userNameTxt;

	@FindBy(xpath = "//div[text()='Password is required']")
	WebElement passwordTxt;

	// In APCER, client btn and profile button is same
//	@FindBy(css = "#page-header-user-dropdown")
//	WebElement btnHeader;
	
	
	@FindBy(xpath = "(//button[@id='page-header-user-dropdown'])[2]")
	WebElement btnHeader;

	@FindBy(css = "[href*='logout']")
	WebElement logoutBtn;
	
	@FindBy(css = "[name*='Client']")
	WebElement clientDropdown;

	public void goTo(String url) {

		driver.get(url);
	}

	public WebDriver login(String usernameText, String passwordText) {

		username.sendKeys(usernameText);
		password.sendKeys(passwordText);
		submitBtn.click();
		//selectClient(clientName);
		return driver;

	}
	
	public void selectClient(String clientName) throws InterruptedException {
		Select select = new Select(clientDropdown);
		select.selectByVisibleText(clientName);
		//Thread.sleep(7000);
		submitBtn.click();
		waitForElementVisible(btnHeader);
		
	}

	public List<String> loginByBlankValues(String usernameText, String passwordText) {
		System.out.println(usernameText + passwordText);
		username.sendKeys(usernameText);
		password.sendKeys(passwordText);
		submitBtn.click();

		if (usernameText != "") {

			waitForElementVisible(passwordTxt);

		} else {
			waitForElementVisible(userNameTxt);

		}
		
		List<String> validationList = validationErrorMsg.stream().map(s -> s.getText()).collect(Collectors.toList());
		System.out.println(validationList.size());
		return validationList;

	}

	public void logOut() {

		waitForElementVisible(btnHeader);
		
		btnHeader.click();
		logoutBtn.click();

	}

}
