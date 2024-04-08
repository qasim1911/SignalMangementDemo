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

public class WorkflowConfig extends AbstractComponents {
	WebDriver driver;

	public WorkflowConfig(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[name*='Name']")
	WebElement workflowName;

	@FindBy(css = "[name*='Description']")
	WebElement workflowDescription;

	@FindBy(css = "[name*='Stage']")
	WebElement stageDropdown;

	@FindBy(xpath = "//span[text()='Add  Workflow']/parent::button")
	WebElement addButton;

	@FindBy(xpath = "//span[text()='Add  Workflow']/parent::button //following-sibling::button")
	WebElement closeButton;

	@FindBy(css = "[role='status']")
	WebElement confirmationMessage;

	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> workflowConfigList;

	@FindBy(xpath = "//span[text()='Edit']/parent::li")
	WebElement editButton;

	@FindBy(xpath = "//span[text()='Delete']/parent::li")
	WebElement deleteButton;
	
	@FindBy(xpath = "//span[text()='Yes']/parent::button")
	WebElement yesButton;
	
	@FindBy(xpath = "//span[text()='No']/parent::button")
	WebElement noButton;

	@FindBy(xpath = "//span[text()='Update  Workflow']/parent::button")
	WebElement updateButton;
	

	By selectedLink = By.cssSelector("a");
	By kebabIcon = By.xpath("preceding-sibling::td[1]");
	By closeBtn = By.xpath("following-sibling::button");

	public String addWorkflow(HashMap<String, String> inputData) {

		int index = Integer.parseInt(inputData.get("headerLinkIndex"));
		goToNavigationLink(inputData.get("goToNavigationLink"));

		WebElement matchElement = goToHeaderLinks(index);
		matchElement.findElement(selectedLink).click();
		waitForElementVisible(workflowName);

		workflowName.sendKeys(inputData.get("workflowName"));
		workflowDescription.sendKeys(inputData.get("workflowDesc"));
		Select optionSelect = new Select(stageDropdown);
		optionSelect.selectByVisibleText(inputData.get("workflowStage"));

		addButton.click();
		waitForElementVisible(confirmationMessage);
		String cnfString = confirmationMessage.getText();
		return cnfString;

	}

	public String editWorkflow(HashMap<String, String> inputData) throws InterruptedException {
		goToNavigationLink(inputData.get("goToNavigationLink"));
		waitForElementVisible(workflowConfigList.get(0));

		WebElement matchedElement = getMatchElementFromList(workflowConfigList, inputData.get("workflowName"));
		matchedElement.findElement(kebabIcon).click();
		waitForElementVisible(editButton);
		editButton.click();

		// clear fields

		workflowName.clear();
		workflowName.sendKeys(inputData.get("editworkflowName"));

		workflowDescription.clear();
		workflowDescription.sendKeys(inputData.get("editworkflowDesc"));

		Select optionSelect = new Select(stageDropdown);
		optionSelect.selectByVisibleText("--Select--");
		optionSelect.selectByVisibleText(inputData.get("editworkflowStage"));
		// updateButton.findElement(closeBtn).click();
		updateButton.click();
		waitForElementVisible(confirmationMessage);
		String cnfString = confirmationMessage.getText();
		return cnfString;

	}

	public String deleteWorkflow(HashMap<String, String> inputData) {
		// TODO Auto-generated method stub
		goToNavigationLink(inputData.get("goToNavigationLink"));
		waitForElementVisible(workflowConfigList.get(0));
		System.out.println("list size: " + workflowConfigList.size());
		WebElement matchedElement = getMatchElementFromList(workflowConfigList, inputData.get("editworkflowName"));
		matchedElement.findElement(kebabIcon).click();
		waitForElementVisible(deleteButton);
		deleteButton.click();
		yesButton.click();
		waitForElementVisible(confirmationMessage);
		String cnfMessage = confirmationMessage.getText();

		return cnfMessage;

	}

}
