package com.task.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class SuccessPage(webDriver: WebDriver) : AbstractPOM(webDriver) {
    @FindBy(xpath = "//div/h1")
    private lateinit var successMessageEl: WebElement

    @FindBy(xpath = "//div/ul/li")
    private lateinit var userData: WebElement

    val successMessage: String
        get() = successMessageEl.text

    val name: String
        get() = userData.text.split("\n").zipWithNext().first { it.first.contains("Name") }.second.trim()
    val email: String
        get() = userData.text.split("\n").zipWithNext().first { it.first.contains("Email") }.second.trim()

}

