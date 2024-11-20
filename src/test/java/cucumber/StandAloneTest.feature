
@tag
Feature: Purchase Order from eCommerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting order
    Given logged in with username <name> and password <password>
    When I add the product <productName> to Cart
    And checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on the confirmation page

    Examples: 
      | name  				| password 		| productName |
      | roshan177@gmail.com |    Rahul1!2 | ZARA COAT 3 |
      
