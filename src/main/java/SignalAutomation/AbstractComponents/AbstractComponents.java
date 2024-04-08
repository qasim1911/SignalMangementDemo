package SignalAutomation.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		// System.out.println("AbstractComponents");
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "ul#side-menu a[href*='user-dashboard']")
	WebElement userDashboardLink;

	@FindBy(css = "ul#side-menu a[href*='signal']")
	WebElement signalLink;

	@FindBy(css = "ul#side-menu a[href*='regulatory-intel']")
	WebElement regIntelLink;

	@FindBy(css = "a[href*='workflow-configurations']")
	WebElement workflowConfigLink;

	@FindBy(css = "ul#side-menu a[href*='projects']")
	WebElement projectsLink;

	@FindBy(css = "ul#side-menu a[href*='products']")
	WebElement productsLink;

	@FindBy(css = "ul#side-menu a[href*='users']")
	WebElement usersLink;

	@FindBy(css = "ul#side-menu a[href*='imdrf']")
	WebElement imdrfLink;

	@FindBy(xpath = "//div[contains(@class,'shadow')] //ul[contains(@class,'nav-pills')]/li")
	List<WebElement> headerMenuList;

	public void waitForElementVisible(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	public void waitForListElementVisible(List<WebElement> findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(findBy));

	}

	public void goToNavigationLink(String linkName) {

		if (linkName.contains("dashboard")) {
			userDashboardLink.click();

		} else if (linkName.contains("signal")) {
			signalLink.click();

		} else if (linkName.contains("regulatory-intel")) {
			regIntelLink.click();

		} else if (linkName.contains("workflow")) {
			workflowConfigLink.click();

		} else if (linkName.contains("projects")) {
			projectsLink.click();

		} else if (linkName.contains("products")) {
			productsLink.click();

		} else if (linkName.contains("users")) {
			usersLink.click();

		} else if (linkName.contains("imdrf")) {
			imdrfLink.click();

		}

	}

	public WebElement goToHeaderLinks(int getHeaderElement) {
		WebElement returnDataElement = null;
		for (int i = 0; i < headerMenuList.size(); i++) {
			if (getHeaderElement == i) {
				returnDataElement = headerMenuList.get(i);
				return returnDataElement;

			}
			// System.out.println(i);
		}
		return null;
	}

	public void searchWithInServer() {

	}

	public WebElement getMatchElementFromList(List<WebElement> recordList, String recordName) {
		System.out.println(recordName);
		System.out.println(recordList.get(0).getText());
		WebElement matchedElement = recordList.stream().filter(s -> s.getText().contains(recordName)).findFirst()
				.orElse(null);

		return matchedElement;
	}

}
