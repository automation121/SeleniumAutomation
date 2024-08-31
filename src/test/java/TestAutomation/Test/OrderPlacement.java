package TestAutomation.Test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import TestAutomation.TestComponents.BaseTest;
import testautomation.packageobjects.CartPage;
import testautomation.packageobjects.CheckoutPage;
import testautomation.packageobjects.ConfirmationPage;
import testautomation.packageobjects.LandingPage;
import testautomation.packageobjects.OrderPage;
import testautomation.packageobjects.ProductCatalogue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPlacement extends BaseTest{
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void placeOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage =checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"placeOrder"})
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("testnotificationsit@gmail.com", "Navya@123");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
//just for checking the webhook
	}
	
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\TestAutomation\\Data\\PurchaseOrder.json");
		
		return new Object [][] {{data.get(0)},{data.get(1)}};
	}

}

/*
 * HashMap<String, String> map= new HashMap<String,String>();
 * map.put("email","testnotificationsit@gmail.com"); map.put("password",
 * "Navya@123"); map.put("productName", "ZARA COAT 3");
 * 
 * HashMap<String, String> map1= new HashMap<String,String>();
 * map1.put("email","mantesting@gmail.com"); map1.put("password", "Navya@123");
 * map1.put("productName", "ADIDAS ORIGINAL");
 */
