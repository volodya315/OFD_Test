import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestPlan {
    private static final WebDriver driver = new ChromeDriver();
    CatalogPage catalogPage = new CatalogPage(driver);

    @BeforeSuite
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Checks all checkboxes")
    public void CheckBoxesTest() {
        driver.get(Utils.BASE_URL);
        catalogPage.CheckBoxesValidation();
    }

    @Test(testName = "Filters by fiscal storage device")
    public void FilterFiscal(){
        catalogPage.FilterFirstItem();
    }

    @AfterSuite
    public static void Quit(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}

