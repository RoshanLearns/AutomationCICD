@tag
Feature: Error Validations
  I want to use this template for my feature file



  @ErrorValidation
  Scenario Outline: Check for error validations
  	Given I landed on Ecommerce page
    When logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  				| password 		| 
      | roshan177@gmail.com |    ahul1!2 | 