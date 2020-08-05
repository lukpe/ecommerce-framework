package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SummaryPage {
    public WebDriver driver;
    private final WebDriverWait wait;


    public SummaryPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[@id='cart_title']")
    WebElement cartTitle;

    @FindBy(xpath = "//td[@class='cart_description']//p[@class='product-name']")
    WebElement productName;

    @FindBy(css = ".cart_quantity_input.form-control.grey")
    WebElement productQuantity;

    @FindBy(xpath = "//i[@class='icon-plus']")
    WebElement iconPlus;

    @FindBy(xpath = "//i[@class='icon-minus']")
    WebElement iconMinus;

    @FindBy(xpath = "//p//a[@title='Proceed to checkout']")
    WebElement checkOut;

    public boolean verifyProductQtyTitle(String productNumber) {
        wait.until(ExpectedConditions.visibilityOf(cartTitle));
        return cartTitle.getText().contentEquals("SHOPPING-CART SUMMARY\nYour shopping cart contains: " + productNumber);
    }

    public boolean verifyProductName(String input) {
        return productName.getText().matches(".*(?i)" + input + "(?-i).*");
    }

    public boolean verifyProductQty(int quantity) {
        return productQuantity.getAttribute("value").contentEquals(String.valueOf(quantity));
    }

    public void addProduct() {
        new Actions(driver).moveToElement(iconPlus).click().pause(2000).perform();
    }

    public void removeProduct() {
        new Actions(driver).moveToElement(iconMinus).click().pause(2000).perform();
    }

    public void proceedCheckOut() {
        checkOut.click();
    }
}
