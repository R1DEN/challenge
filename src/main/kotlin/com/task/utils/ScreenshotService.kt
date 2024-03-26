package com.task.utils

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.shooting.ShootingStrategies
import ru.yandex.qatools.ashot.shooting.SimpleShootingStrategy
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.time.Duration
import javax.imageio.ImageIO

object ScreenshotService {
    fun takeFullPageScreenshot(webDriver: WebDriver): ByteArray {
        WebDriverWait(webDriver, Duration.ofSeconds(5))
            .withMessage("document.body!=null and document.documentElement!=null to return true, but one or both of them didn't")
            .until {
                val bodyPresent =
                    (webDriver as JavascriptExecutor).executeScript("return document.body!=null") as Boolean
                val documentPresent =
                    (webDriver as JavascriptExecutor).executeScript("return document.documentElement!=null") as Boolean
                bodyPresent && documentPresent
            }
        val fpScreenshot =
            AShot().shootingStrategy(SimpleShootingStrategy())
                .takeScreenshot(webDriver)
        val originalImage = fpScreenshot.image
        return imageToByteArray(originalImage)
    }

    fun takeCurrentViewPortScreenshot(webDriver: WebDriver): ByteArray {
        val fpScreenshot = AShot().shootingStrategy(ShootingStrategies.simple()).takeScreenshot(webDriver)
        val originalImage = fpScreenshot.image
        return imageToByteArray(originalImage)
    }

    private fun imageToByteArray(originalImage: BufferedImage): ByteArray {
        ByteArrayOutputStream().use { baos ->
            ImageIO.write(originalImage, "png", baos)
            baos.flush()
            return baos.toByteArray()
        }
    }
}