package com.task.context

import com.task.TestRunConfig
import com.task.webdriver.WebDriverFactory
import com.task.webdriver.WebDriverHolder
import com.task.pages.PageObjectManager
import java.util.*

class TestContext {
    val driverHolder: WebDriverHolder by lazy {
        WebDriverFactory.getWebDriverHolder(
            TestRunConfig.remote
        ).also {
            System.getProperty("webdriver.timeouts.implicitlywait")
                ?: System.setProperty("webdriver.timeouts.implicitlywait", "1")
        }

    }
    val pageObjectManager: PageObjectManager by lazy { PageObjectManager(driverHolder.driver) }

    val userData = UserData()
}