package com.task

import com.task.utils.ResourceAccessor
import com.task.webdriver.WebDriverType
import java.util.*

object TestRunConfig {
    val remote
        get() = ResourceAccessor.getProperty("driver")?.let { WebDriverType.valueOf(it) }!!

    val testRunId = UUID.randomUUID().toString()

    val url: String?
        get() = ResourceAccessor.getProperty("url")
}