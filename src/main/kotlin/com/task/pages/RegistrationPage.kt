package com.task.pages

import com.task.utils.ResourceAccessor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy


class RegistrationPage(webDriver: WebDriver) : AbstractPOM(webDriver) {
    @FindBy(xpath = "//input[@name='first_name']")
    private lateinit var firstNameInput: WebElement

    @FindBy(xpath = "//input[@name='last_name']")
    private lateinit var lastNameInput: WebElement

    @FindBy(xpath = "//input[@name='email']")
    private lateinit var emailInput: WebElement

    @FindBy(xpath = "//input[@name='password']")
    private lateinit var passwordInput: WebElement

    @FindBy(xpath = "//input[@name='confirm_password']")
    private lateinit var confirmPasswordInput: WebElement

    @FindBy(xpath = "//input[@name='avatar']")
    private lateinit var avatar: WebElement

    @FindBy(id = "slider-track")
    private lateinit var sliderTrack: WebElement

    @FindBy(id = "slider-thumb")
    private lateinit var slider: WebElement

    @FindBy(xpath = "//input[@type='submit']")
    private lateinit var submitBtn: WebElement

    @FindBy(xpath = "//div/ul/li")
    private lateinit var errorText: WebElement

    fun fillName(firstName: String) {
        firstNameInput.sendKeys(firstName)
    }

    fun fillLastName(lastName: String) {
        lastNameInput.sendKeys(lastName)
    }

    fun fillEmail(email: String) {
        emailInput.sendKeys(email)
    }

    fun fillPassword(pwd: String) {
        passwordInput.sendKeys(pwd)
    }

    fun fillConfirmPassword(pwd: String) {
        confirmPasswordInput.sendKeys(pwd)
    }

    fun uploadAvatar(fileName: String) {
        ResourceAccessor.listFiles("avatars").first { it.name.contains(fileName) }.let {
            avatar.sendKeys(it.absolutePath)
        }
    }

    fun slideSlider() {
        val dist = sliderTrack.size.width
        Actions(webDriver)
            .dragAndDropBy(slider, dist, 0)
            .perform()
    }

    fun submitForm() {
        submitBtn.click()
    }

    val error: String
        get() = errorText.text
}