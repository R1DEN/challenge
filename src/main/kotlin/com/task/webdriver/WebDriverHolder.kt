package com.task.webdriver

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions

abstract class WebDriverHolder {
    //Abstract
    abstract val driver: WebDriver
    abstract fun closeHolder()

    //WebDriver

    protected val remoteChromeDriverCapabilities: ChromeOptions
        get() {
            val options = ChromeOptions()
            options.addArguments(
                "--disable-extensions", "disable-infobars",
                "--disable-cache", "--disable-setuid-sandbox"
            )
            options.addArguments("--dns-prefetch-disable")
            options.addArguments("--disable-gpu")
            options.addArguments("--disable-dev-shm-usage")
            options.addArguments("--disable-browser-side-navigation")
            options.addArguments("--no-sandbox")
            options.addArguments("incognito")
            options.addArguments("--start-maximized")
            options.setExperimentalOption("useAutomationExtension", false)
            val prefs = HashMap<String, Any>()
            prefs["profile.default_content_settings.popups"] = "0"
            prefs["profile.default_content_setting_values.automatic_downloads"] = "1"
            prefs["download.prompt_for_download"] = false
            prefs["safebrowsing.enabled"] = false
            options.setExperimentalOption("prefs", prefs)
            options.setAcceptInsecureCerts(true)
            return options
        }


    protected fun maximizeBrowser() {
        //This is needed temporary to test if this fixes the problem with browser not starting maximized
        //If this is left here long enough - then it worked and no one bothered to delete this comment :)
        driver.manage().window().maximize()
    }
}

enum class WebDriverType {
    LOCAL, REMOTE
}