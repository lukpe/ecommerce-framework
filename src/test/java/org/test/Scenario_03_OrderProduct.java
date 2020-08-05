package org.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class Scenario_03_OrderProduct extends Base {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;
    CommonElements ce;
    HomePage hp;
    LoginPage lp;
    MyAccountPage map;
    SearchPage srchp;
    SummaryPage sump;
    AddressPage ap;
    ShippingPage shp;

    @BeforeClass
    public void setUp() throws IOException {
        driver = initializeDriver();
        wait = new WebDriverWait(driver, timeOut);
        ce = new CommonElements(driver, wait);
        hp = new HomePage(driver);
        lp = new LoginPage(driver, wait);
        map = new MyAccountPage(driver);
        srchp = new SearchPage(driver, wait);
        sump = new SummaryPage(driver, wait);
        ap = new AddressPage(driver);
        shp = new ShippingPage(driver, wait);
        actions = new Actions(driver);
    }

    @Parameters({"product"})
    @Test(priority = 1)
    public void addToCart(String product) {
        product = product.toLowerCase();
        hp.searchProduct(product);
        assertTrue(srchp.checkSearchResult(product));
        srchp.addProductToCart();
        assertTrue(srchp.verifyMessageHeader("Product successfully added to your shopping cart"));
        srchp.getContinueShopping().click();
        assertTrue(hp.checkCartQuantity("1"));
        hp.proceedCheckOut();
    }

    @Parameters({"product"})
    @Test(priority = 2)
    public void summaryPage(String product) {
        product = product.toLowerCase();
        //Summary
        assertTrue(sump.verifyProductQtyTitle("1 Product"));
        assertTrue(sump.verifyProductName(product));
        assertTrue(sump.verifyProductQty(1));
        sump.addProduct();
        assertTrue(sump.verifyProductQty(2));
        assertTrue(sump.verifyProductQtyTitle("2 Products"));
        sump.removeProduct();
        assertTrue(sump.verifyProductQty(1));
        assertTrue(sump.verifyProductQtyTitle("1 Product"));
        sump.proceedCheckOut();
    }

    @Test(priority = 3)
    public void signInPage() {
        lp.signIn();
    }

    @Test(priority = 4)
    public void addressPage() {
        assertTrue(ce.verifyTitle("ADDRESSES"));
        assertTrue(ap.verifyAddressData());
        ap.proceedCheckout();
    }

    @Test(priority = 5)
    public void shippingPage() {
        assertTrue(ce.verifyTitle("SHIPPING"));
        assertTrue(shp.verifyTermsAndConditions());
        shp.proceedCheckOut();
        assertTrue(shp.verifyErrorMessage("You must agree to the terms of service before continuing."));
        shp.acceptTermsAndConditions();
        shp.proceedCheckOut();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
