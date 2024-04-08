package SignalAutomation.SignalManagementTest;

import java.util.HashMap;

import org.testng.annotations.Test;

import SignalAutomation.BaseComponent.BaseComponent;

public class ActionStateTest extends BaseComponent {

	@Test(dataProvider = "getDataFromJson", priority = 1)
	public void addActionState(HashMap<String, String> input) {
		
	}
	
	@Test(dataProvider = "getDataFromJson", priority = 2)
	public void editActionState(HashMap<String, String> input) {
		
	}
	
	@Test(dataProvider = "getDataFromJson", priority = 3)
	public void deleteActionState(HashMap<String, String> input) {
		
	}
}
