package TestAutomation.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestAutomation.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testautomation.packageobjects.CartPage;
import testautomation.packageobjects.CheckoutPage;
import testautomation.packageobjects.ConfirmationPage;
import testautomation.packageobjects.LandingPage;
import testautomation.packageobjects.ProductCatalogue;

public class StepDefinitionImpli extends BaseTest{
		
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {
		landingPage =launchApplication();
	}
	@Given("I logged with the username {string} and password {string}")
	public void i_logged_with_the_username_and_password(String name, String password) {
		productCatalogue = landingPage.loginApplication(name, password);
	}
	@When("I add the product {string} to cart")
	public void i_add_the_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	@When("checkout {string} and submit the order")
	public void checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =cartPage.goToCheckOut();
		checkoutPage.selectCountry("India");
		confirmationPage =checkoutPage.submitOrder();
	    
	}
	@Then("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String message) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String errorMessage) {
		Assert.assertEquals(errorMessage, landingPage.getErrorMessgae());
		driver.close();
	}
}
















