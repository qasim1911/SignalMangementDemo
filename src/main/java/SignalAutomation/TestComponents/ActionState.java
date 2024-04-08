package SignalAutomation.TestComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import SignalAutomation.AbstractComponents.AbstractComponents;

public class ActionState extends AbstractComponents {
	WebDriver driver;

	public ActionState(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
