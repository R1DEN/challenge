package com.task.webdriver

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class LocalWebDriverHolder() : WebDriverHolder() {
    override val driver: WebDriver
    override fun closeHolder() {
        driver.quit()
    }


    init {
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup()
        driver = ChromeDriver(remoteChromeDriverCapabilities)
        maximizeBrowser()
    }
}