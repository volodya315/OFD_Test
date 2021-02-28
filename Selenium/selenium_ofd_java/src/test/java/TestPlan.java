import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestPlan {
    private static final WebDriver driver = new ChromeDriver();
    CatalogPage catalogPage = new CatalogPage(driver);

    @BeforeSuite
    private static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Checks all checkboxes")
    private void CheckBoxesTest() {
        driver.get(Utils.BASE_URL);
        catalogPage.CheckBoxesValidation();
    }

    @Test(testName = "Filters by fiscal storage device")
    private void FilterFiscal(){
        catalogPage.FilterFirstItem();
    }

    @AfterSuite
    private static void Quit(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}

