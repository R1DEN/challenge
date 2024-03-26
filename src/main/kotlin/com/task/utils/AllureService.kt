package com.task.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.task.logger
import io.qameta.allure.Allure
import java.io.ByteArrayInputStream

object AllureService {
    private val prettyPrinter: Gson = protoPrinter
        .setPrettyPrinting()
        .create()

    private val printer: Gson = protoPrinter
        .create()

    private val protoPrinter
        get() = GsonBuilder()
            .disableHtmlEscaping()

    fun attachScreenshot(name: String, screenshotByte: ByteArray) {
        Allure.addAttachment(name, "image/png", ByteArrayInputStream(screenshotByte), ".png")
    }

    /**
     * Also logs in debug level
     */
    fun attachObjToJson(name: String, dumpObject: Any) {
        logger().debug(name + "\n\n" + printer.toJson(dumpObject))
        Allure.addAttachment(name, "application/json", prettyPrinter.toJson(dumpObject))
    }

    /**
     * Also logs in debug level
     */
    fun attachString(name: String, dumpString: String) {
        logger().debug(name + "\n\n" + dumpString)
        Allure.addAttachment(name, "text/plain", dumpString)
    }

    fun attachFile(name: String, byteArray: ByteArray) {
        Allure.addAttachment(name, byteArray.inputStream())
    }

    fun attachFile(name: String, type: String, byteArray: ByteArray, fileExtension: String) {
        Allure.addAttachment(name, type, byteArray.inputStream(), fileExtension)
    }
}