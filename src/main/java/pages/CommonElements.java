package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonElements {
    public WebDriver driver;
    private final WebDriverWait wait;

    public CommonElements(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "page-heading")
    WebElement pageHeading;

    @FindBy(className = "page-subheading")
    WebElement subHeading;

    public boolean verifyHeading(String title) {
        wait.until(ExpectedConditions.visibilityOf(pageHeading));
        return pageHeading.getText().contentEquals(title);
    }

    public boolean verifySubHeading(String title) {
        wait.until(ExpectedConditions.visibilityOf(subHeading));
        return subHeading.getText().contentEquals(title);
    }


}
