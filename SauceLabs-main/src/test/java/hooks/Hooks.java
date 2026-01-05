package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utility.PropertyReader;

public class Hooks {

    public static WebDriver driver;
    public static PropertyReader reader;
    public static PropertyReader elementReader;

    @Before
    public void setUp() {
        reader = new PropertyReader("src/test/resources/properties/testdata.properties");
        elementReader = new PropertyReader("src/test/resources/properties/locators.properties");

        ChromeOptions options = new ChromeOptions();

        // Detect if running in CI/CD (Azure DevOps sets CI=true)
        String ciEnv = System.getenv("CI");
        if (ciEnv != null && ciEnv.equalsIgnoreCase("true")) {
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }

        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}