package com.task.stepDefs

import com.task.context.TestContext
import org.openqa.selenium.WebDriver

open class AbstractSteps(tc: TestContext) {
    protected val webDriver: WebDriver by lazy { tc.driverHolder.driver }
}
