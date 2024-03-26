@Challenge
Feature: Tests

  Scenario: Submit form with valid data and verify that the submission is successful and the data is correctly displayed on the success page
    When user opens home page
    And user fills first name with "John"
    And user fills last name with "Smith"
    And user fills email with "john.smith@mail.net"
    And user fills password with "MyPassword"
    And user fills password confirmation with "MyPassword"
    And user uploads avatar from "png" file
    And user slides captcha slider
    And user submits the form
    Then user is successfully registered
    And correct data is displayed on the success page

  Scenario: Submit form with invalid data and verify that the submission fails and appropriate validation messages are displayed.
    When user opens home page
    And user fills first name with "John"
    And user fills last name with "Smith"
    And user fills email with "john.smith@mail.net"
    And user fills password with "MyPassword"
    And user fills password confirmation with "MyPasswordNope"
    And user uploads avatar from "png" file
    And user slides captcha slider
    And user submits the form
    Then error message "Passwords do not match!" is displayed

  Scenario: Submit form without sliding the captcha to the end and verify that the form submission is blocked.
    When user opens home page
    And user fills first name with "John"
    And user fills last name with "Smith"
    And user fills email with "john.smith@mail.net"
    And user fills password with "MyPassword"
    And user fills password confirmation with "MyPassword"
    And user uploads avatar from "png" file
    And user submits the form
    Then error message "Please solve the captcha!" is displayed

  Scenario: Test the form with various types of images for the avatar and verify that the uploaded image is correctly displayed on the success page.
    When user opens home page
    And user fills first name with "John"
    And user fills last name with "Smith"
    And user fills email with "john.smith@mail.net"
    And user fills password with "MyPassword"
    And user fills password confirmation with "MyPassword"
    And user uploads avatar from "jpg" file
    And user slides captcha slider
    And user submits the form
    Then user is successfully registered
    And correct data is displayed on the success page