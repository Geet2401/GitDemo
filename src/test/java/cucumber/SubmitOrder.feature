
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  Background:
  Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test for submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FROM THE ORDER." message is displayed on the ConfirmationPage.

    Examples: 
      | name  								| password 					| productName      |
      | geetahans22@gmail.com | India@1234 				| ZARA COAT 3			 |
      
