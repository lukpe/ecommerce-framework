# selenium-testng-maven
Selenium framework for testing e-commerce page ([automationpractice.com](http://automationpractice.com))

## requirements
* Java 11
* Web browser

## how to run
* `git clone https://github.com/lukpe/selenium-testng-maven.git`
* `mvnw clean test -Dbrowser={browser} -Dremote={true?}`

###### supported `{browser}` values
`chrome` `firefox` `edge` `opera`

## configuration
[test.properties]: src/test/resources/test.properties
* If no `-Dbrowser` then the test will run on browser specified in [test.properties]
* If `-Dremote=true` then  _gridURL_ parameter is taken from [test.properties]
* `homePageURL` & `timeOut` are also set in [test.properties]

## main features
* [Apache Maven](https://maven.apache.org/)
* [Maven Wrapper Plugin](https://github.com/takari/takari-maven-plugin)
* [Maven Surefire Reports](https://maven.apache.org/surefire/maven-surefire-report-plugin/)
* [Page Factory](https://github.com/SeleniumHQ/selenium/wiki/PageFactory)
* [TestNG](https://testng.org/doc/)
* [webdrivermanager](https://github.com/bonigarcia/webdrivermanager)
* [Apache Log4j2 ](https://logging.apache.org/log4j/2.x/) logs -> `./target/logs`
* screenshots on test fail -> `./target/screenshots`
* generated data (login credentials, address, etc.) saved to an Excel sheet -> `./target/test-data/TestData.xls`
* configuration file -> `./src/test/resources/test.properties`

| logs |
|------|
|`*_method.log` - saved by _@Test_|
|`*_suite.log` - merged _@AfterSuite_|

## test scenarios
### [Scenario_01_VerifyHomePage](/src/test/java/org/test/Scenario_01_VerifyHomePage.java)
* Check for presence/validate: _page title, logo image, search bar, shopping cart ,"Sign in" link_
### [Scenario_02_CreateAccount](/src/test/java/org/test/Scenario_02_CreateAccount.java)
1. Create account and save generated data to TestData.xls
2. Verify if personal information is correctly displayed on the account page.
### [Scenario_03_OrderProduct](src/test/java/org/test/Scenario_03_OrderProduct.java)
[testng.xml]: ./testng.xml
[TestData.xls]: src/test/resources/TestData.xls
###### Product `name`, `quantity` and `payment` method parametrized in [testng.xml]
1. Search for a product, save its name, price and desired quantity to [TestData.xls] and proceed to checkout.
2. Verify the summary page before and after adding product quantity (`if quantity > 1`) also verify total prices with and without tax.
3. Log in.
4. Verify address data.
5. Verify shipping price and terms and conditions acceptance requirement.
6. Choose payment method and verify payment confirmation, total price and extract order reference number.
