package com.task.webdriver

import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URI


class RemoteWebDriverHolder() : WebDriverHolder() {


    override var driver: WebDriver

    //Browser container
    init {
        driver = RemoteWebDriver(URI("http://localhost:4444").toURL(), remoteChromeDriverCapabilities)
        maximizeBrowser()
    }

    override fun closeHolder() {
        driver.quit()
    }
}