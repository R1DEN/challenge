package com.task

import com.task.context.TestContext
import com.task.utils.AllureService
import com.task.utils.ScreenshotService
import io.cucumber.java.After
import io.cucumber.java.Scenario
import org.apache.commons.lang3.exception.ExceptionUtils
import org.openqa.selenium.UnhandledAlertException
import java.time.Instant
import kotlin.reflect.jvm.isAccessible

class CucumberScenarioHooks(private val testContext: TestContext) {
    @After(order = 1)
    fun tearDown(scenario: Scenario) {
        logger().info("Shutdown Hook")
        var ex: Throwable? = null
        if (scenario.isFailed) {
            dumpTimeOfFailure()
            if ((testContext::driverHolder.apply { isAccessible = true }.getDelegate() as Lazy<*>).isInitialized()) {
                try {
                    try {
                        dumpCurrentUrl()
                    } catch (e: UnhandledAlertException) {
                        testContext.driverHolder.driver.switchTo().alert().accept()
                        testContext.driverHolder.driver.switchTo().defaultContent()
                        dumpCurrentUrl()
                    }
                    dumpHTML()
                    dumpCookies()
                    embedScreenshot()
                } catch (e: Throwable) {
                    try {
                        embedScreenshot()
                    } catch (screenshotError: Throwable) {
                        logger().error(
                            "taking a screenshot failed in Catch. Trace:\n${ExceptionUtils.getStackTrace(screenshotError)}"
                        )
                    }
                    ex = e
                }
            }
        }
        if ((testContext::driverHolder.apply { isAccessible = true }.getDelegate() as Lazy<*>).isInitialized()) {
            testContext.driverHolder.closeHolder()
        }
        ex?.let { throw it }
    }

    private fun dumpHTML() {
        AllureService.attachObjToJson("HTML", testContext.driverHolder.driver.pageSource)
    }

    private fun dumpCurrentUrl() {
        AllureService.attachString("Failure URL", testContext.driverHolder.driver.currentUrl)
    }


    private fun dumpTimeOfFailure() {
        AllureService.attachString("Time of scenario failure", Instant.now().toString())
    }

    private fun dumpCookies() {
        AllureService.attachObjToJson("Cookies Dump", testContext.driverHolder.driver.manage().cookies)
    }

    private fun embedScreenshot() {
        testContext.driverHolder.driver.switchTo().defaultContent()
        AllureService.attachScreenshot(
            "Screenshot",
            ScreenshotService.takeFullPageScreenshot(testContext.driverHolder.driver)
        )
        logger().info("Screenshot was attached")
    }
}