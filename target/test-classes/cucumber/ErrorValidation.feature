@tag
Feature: Purchase the order from ecommerce website
  I want to use this template for my feature file
	
  @ErrorValidation
  Scenario Outline: Posite test of submitting the order
  	Given I landed on Ecommerce Page
    When I logged with the username "<name>" and password "<password>"
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  												| password 	 |
      | testnotification@gmail.com 		| Navya@123	 |
      
