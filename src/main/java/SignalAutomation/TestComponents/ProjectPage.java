package SignalAutomation.TestComponents;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import SignalAutomation.AbstractComponents.AbstractComponents;

public class ProjectPage extends AbstractComponents {

	WebDriver driver;
	
	public ProjectPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css = "[name*='Workflow Configuration']")
	WebElement workflowDropdown;

	@FindBy(css = "[name*='Name']")
	WebElement projectName;

	@FindBy(css = "[name*='Description']")
	WebElement projectDesc;

	@FindBy(css = "button.me-3")
	WebElement addProjectBtn;

	@FindBy(xpath = "//*[@role='status']")
	WebElement confirmMsg;

	@FindBy(xpath = "//table //tr//td[2]")
	List<WebElement> projectList;

	@FindBy(css = "li[role='button']:first-child")
	WebElement editBtn;

	@FindBy(css = "li[role='button']:last-of-type")
	WebElement deleteBtn;

	@FindBy(css = "button.me-2")
	WebElement confirmationBtn;

	@FindBy(xpath = "//*[contains(@placeholder,'Search in server')]")
	WebElement searchInServerText;

	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> searchResults;

	@FindBy(css = "[placeholder*='Search in table']")
	WebElement searchInTableText;

	By selectedLink = By.cssSelector("a");

	By kebabIcon = By.xpath("preceding-sibling::td[1]");

	By searchBtnIcon = By.xpath("following-sibling::i[1]");

	public WebElement addProject(HashMap<String, String> input) throws InterruptedException {
		int index = Integer.parseInt(input.get("headerLinkIndex"));
		goToNavigationLink(input.get("goToNavigationLink"));
		Thread.sleep(5000);
		WebElement addBtnElement = goToHeaderLinks(index);

		addBtnElement.findElement(selectedLink).click();
		Select optioSelect = new Select(workflowDropdown);
		optioSelect.selectByVisibleText(input.get("workflowConfigValue"));
		projectName.sendKeys(input.get("projectName"));
		projectDesc.sendKeys(input.get("projectDesc"));
		addProjectBtn.click();

		waitForElementVisible(confirmMsg);

		return confirmMsg;

	}

	public WebElement editProject(HashMap<String, String> input) throws InterruptedException {
		goToNavigationLink(input.get("goToNavigationLink"));
		waitForElementVisible(projectList.get(0));

		WebElement matchElement = projectList.stream().filter(s -> s.getText().contains(input.get("projectName")))
				.findFirst().orElse(null);

		WebElement precedingElement = matchElement.findElement(kebabIcon);
		precedingElement.click();

		waitForElementVisible(editBtn);
		editBtn.click();

		Select optioSelect = new Select(workflowDropdown);
		optioSelect.selectByVisibleText(input.get("editWorkflowConfigOption"));
		projectName.clear();
		projectName.sendKeys(input.get("editProjectName"));
		projectDesc.clear();
		projectDesc.sendKeys(input.get("editProjectDesc"));
		addProjectBtn.click();

		waitForElementVisible(confirmMsg);

		return confirmMsg;

	}

	public WebElement deleteProject(HashMap<String, String> input) {
		goToNavigationLink(input.get("goToNavigationLink"));
		waitForElementVisible(projectList.get(0));

		WebElement matchElement = projectList.stream()
				.filter(s -> s.getText().contains(input.get("deletedProjectName"))).findFirst().orElse(null);

		WebElement precedingElement = matchElement.findElement(kebabIcon);
		precedingElement.click();

		waitForElementVisible(deleteBtn);
		deleteBtn.click();
		confirmationBtn.click();

		waitForElementVisible(confirmMsg);

		return confirmMsg;

	}

	public boolean searchInServer(HashMap<String, String> input) {

		int index = Integer.parseInt(input.get("headerLinkIndex"));
		goToNavigationLink(input.get("goToNavigationLink"));
		WebElement searchBtn = goToHeaderLinks(index);

		waitForElementVisible(searchBtn);
		searchBtn.findElement(selectedLink).click();

		searchInServerText.sendKeys(input.get("searchProjectName"));

		WebElement searchBtn1 = searchInServerText.findElement(searchBtnIcon);
		searchBtn1.click();
		waitForListElementVisible(searchResults);
		boolean flag = true;

		for (int i = 0; i < searchResults.size(); i++) {

			if (!searchResults.get(i).getText().toLowerCase().contains(input.get("searchProjectName").toLowerCase())) {
				flag = false;
				System.out.println("ValueOfI" + i);
				break;
			}
		}

		return flag;

	}

	public boolean searchWithInPage(HashMap<String, String> input) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		goToNavigationLink(input.get("goToNavigationLink"));
		waitForElementVisible(searchInTableText);
		//WebElement searchlist  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("[placeholder*='Search in table']")));
        // enter the search word
		
		searchInTableText.sendKeys(input.get("searchProjectName"));

		// Thread.sleep(10000);
		
		
		
		//wait.until(ExpectedConditions.visibilityOfAllElements());

		 wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr/td[2]")));
		
	//	System.out.println("elements" + elements.size());

		List<WebElement> lstElements = driver.findElements(By.xpath("//tr/td[2]"));
		waitForListElementVisible(lstElements);

		System.out.println("lstElements" + lstElements.size());
		boolean flag = true;

//		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
//				.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);

		for (int i = 0; i < lstElements.size(); i++) {

			if (!lstElements.get(i).getText().toLowerCase().contains(input.get("searchProjectName").toLowerCase())) {
				flag = false;
				System.out.println("ValueOfI" + i);
				break;
			}
		}

		return flag;

	}

}



