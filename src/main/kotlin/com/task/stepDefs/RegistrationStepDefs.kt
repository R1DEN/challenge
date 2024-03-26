package com.task.stepDefs

import com.task.TestRunConfig
import com.task.context.TestContext
import com.task.pages.RegistrationPage
import com.task.utils.AllureService
import com.task.utils.ScreenshotService
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Assert

class RegistrationStepDefs(private val tc: TestContext) : AbstractSteps(tc) {

    private val registrationPage: RegistrationPage = tc.pageObjectManager.getPomOf()

    @When("^user opens home page$")
    fun openHomePage() {
        webDriver.navigate().to(TestRunConfig.url)
        AllureService.attachScreenshot("homePage", ScreenshotService.takeFullPageScreenshot(webDriver))
    }

    @When("^user fills first name with \"(.*)\"$")
    fun userFillsFirstName(name: String) {
        tc.userData.firstName = name
        registrationPage.fillName(name)
    }

    @When("^user fills last name with \"(.*)\"$")
    fun userFillsLastName(name: String) {
        tc.userData.lastName = name
        registrationPage.fillLastName(name)
    }

    @When("^user fills email with \"(.*)\"$")
    fun userFillsEmail(email: String) {
        tc.userData.email = email
        registrationPage.fillEmail(email)
    }

    @When("^user fills password with \"(.*)\"$")
    fun userFillsPassword(pwd: String) {
        registrationPage.fillPassword(pwd)
    }

    @When("^user fills password confirmation with \"(.*)\"$")
    fun userFillsPasswordConfirmation(pwd: String) {
        registrationPage.fillConfirmPassword(pwd)
    }

    @When("^user uploads avatar from \"(.*)\" file$")
    fun userUploadsAvatar(type: String) {
        registrationPage.uploadAvatar(type)
    }

    @When("^user slides captcha slider$")
    fun userSlidesCaptcha() {
        registrationPage.slideSlider()
    }

    @When("^user submits the form$")
    fun userSubmitsForm() {
        AllureService.attachScreenshot("filled form", ScreenshotService.takeFullPageScreenshot(webDriver))
        registrationPage.submitForm()
    }

    @Then("^error message \"(.*)\" is displayed$")
    fun errorDisplayed(error: String) {
        AllureService.attachScreenshot("error", ScreenshotService.takeFullPageScreenshot(webDriver))
        Assert.assertEquals(error, registrationPage.error)
    }
}