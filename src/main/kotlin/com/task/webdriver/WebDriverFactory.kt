package com.task.webdriver

object WebDriverFactory {
    fun getWebDriverHolder(type: WebDriverType): WebDriverHolder {
        return when (type) {
            WebDriverType.LOCAL -> LocalWebDriverHolder()
            WebDriverType.REMOTE -> RemoteWebDriverHolder()
        }
    }
}