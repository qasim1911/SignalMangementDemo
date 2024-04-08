package SignalAutomation.BaseComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SignalAutomation.TestComponents.LandingPageComponent;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseComponent {
	String urlString;
	public WebDriver driver;
	public LandingPageComponent landingPage;
	public SoftAssert softAssert;
	FileInputStream fileInputStream;
	String jsonFile;

	public WebDriver initializeDriver() throws IOException {

		Properties psProperties = new Properties();
		fileInputStream = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\SignalAutomation\\Resources\\Global.properties");
		psProperties.load(fileInputStream);

		String browserNameString = System.getProperty("browser") != null ? System.getProperty("browser")
				: psProperties.getProperty("browser");

		urlString = psProperties.getProperty("url");
		jsonFile = psProperties.getProperty("jsonFile");
		System.out.println(urlString + browserNameString + jsonFile);

		if(browserNameString.equalsIgnoreCase("chrome")) {
		//	ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			//options.addArguments("headless");
			//driver = new ChromeDriver(options);
			driver = new ChromeDriver();
		}
		else if(browserNameString.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		
		// WebDriverManager.firefoxdriver().setup();
		// driver = new FirefoxDriver();
		

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

	@BeforeMethod
	public LandingPageComponent launchBrowser() throws IOException {

		driver = initializeDriver();
		softAssert = new SoftAssert();
		landingPage = new LandingPageComponent(driver);
		landingPage.goTo(urlString);
		return landingPage;

	}

	@AfterMethod
	public void closeBrowser() {
		// landingPage.logOut();
		driver.close();
	}

	@DataProvider
	public Object[][] getDataFromJson() throws IOException {
		jsonFile = "WorkflowConfigTestData";

		//System.out.println(jsonFile + "dxcfvgbhnj");
		String pathString = System.getProperty("user.dir")
				+ "\\src\\test\\java\\SignalAutomation\\BaseComponent\\WorkflowConfigTestData"; // + jsonFile;

		String fileString = FileUtils.readFileToString(new File(pathString), StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashMap<String, String>> mapperHashMaps = objectMapper.readValue(fileString,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return new Object[][] { { mapperHashMaps.get(0) } };

	}

	@DataProvider
	public Object[][] getDataFromHashMap() {
		// ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> mapperHashMap = new HashMap<String, String>();
		mapperHashMap.put("email", "syed.mohd");
		mapperHashMap.put("password", "Welcom@2024");
		mapperHashMap.put("client", "ClientA");
		return new Object[][] { { mapperHashMap } };
	}

	public String takeScreenshot(String testName, WebDriver driver) throws IOException {

		System.out.println("testName" + testName);
		TakesScreenshot tScreenshot = (TakesScreenshot) driver;
		File src = tScreenshot.getScreenshotAs(OutputType.FILE);
		String pathString = System.getProperty("user.dir") + "//Report//screenshot//" + testName + ".png";
		File file = new File(pathString);
		FileUtils.copyFile(src, file);
		return pathString;
	}

}
