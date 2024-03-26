package com.task.stepDefs

import com.task.TestRunConfig
import com.task.context.TestContext
import com.task.pages.RegistrationPage
import com.task.pages.SuccessPage
import com.task.utils.AllureService
import com.task.utils.ScreenshotService
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Assert

class SuccessPageStepDefs(private val tc: TestContext) : AbstractSteps(tc) {

    private val successPage: SuccessPage = tc.pageObjectManager.getPomOf()

    @Then("^user is successfully registered$")
    fun userIsRegistereed() {
        Assert.assertEquals("Successful Form Submissions", successPage.successMessage)
        AllureService.attachScreenshot("ifconfig", ScreenshotService.takeFullPageScreenshot(webDriver))
    }

    @Then("^correct data is displayed on the success page$")
    fun checkDataIsCorrect() {

        Assert.assertEquals(
            "${tc.userData.firstName} ${tc.userData.lastName}",
            successPage.name
        )

        Assert.assertEquals(
            tc.userData.email,
            successPage.email
        )
    }
}