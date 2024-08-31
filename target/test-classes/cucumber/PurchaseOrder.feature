@tag
Feature: Purchase the order from ecommerce website
  I want to use this template for my feature file
	
	Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Posite test of submitting the order
    Given I logged with the username "<name>" and password "<password>"
    When I add the product "<productName>" to cart
    And checkout "<productName>" and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples: 
      | name  												| password 	 | productName  |
      | testnotificationsit@gmail.com | Navya@123	 | ZARA COAT 3 	|
      
