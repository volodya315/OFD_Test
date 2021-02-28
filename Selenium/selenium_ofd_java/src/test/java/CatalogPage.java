import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CatalogPage {
    private String checkboxes = ".filter_show-item > :nth-child(2) > .filter-item-content > ul > li";

    @FindBy(css = ".filter_show-item > :nth-child(2) > .filter-item-content > .filter-values > :nth-child(1) > .filter-value-label")
    private WebElement fiscalStorage;

    @FindBy(css = ".product_card")
    private WebElement productCard;

    private WebDriver driver;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void CheckBoxesValidation() {
        var Boxes = driver.findElements(By.cssSelector(checkboxes));
        for (int i = 0; i < Boxes.size(); i++) {
            Assert.assertEquals(Utils.items[i], Boxes.get(i).getText());
        }
    }

    public void FilterFirstItem() {
        fiscalStorage.click();
        //ожидаем с тайм аутом 5 секунд, пока не останется только 1 карточка
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                if (driver.findElements(By.cssSelector(".product_card")).size() != 1) {
                    return false;
                } else {
                    Assert.assertEquals(1, driver.findElements(By.cssSelector(".product_card")).size());
                    return true;
                }
            }
        });
        Assert.assertTrue(productCard.getText().contains("Фискальный накопитель на 15 месяцев"));
        Assert.assertTrue(productCard.getText().contains("6 890 – 8 650"));
    }
}
