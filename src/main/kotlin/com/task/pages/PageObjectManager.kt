package com.task.pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator
import org.reflections.Reflections

class PageObjectManager(webDriver: WebDriver) {
    val poms: MutableMap<Class<out AbstractPOM>, AbstractPOM> = HashMap()

    //automatically initialize all page objects
    init {
        val reflections = Reflections("com.task.pages")
        val classes = reflections.getSubTypesOf(AbstractPOM::class.java)
        classes.filter { !it.kotlin.isAbstract }.forEach { clazz ->
            poms[clazz] = clazz.getConstructor(WebDriver::class.java).newInstance(webDriver)
                .also { PageFactory.initElements(DefaultFieldDecorator(DefaultElementLocatorFactory(webDriver)), it) }
        }
    }

    inline fun <reified T : AbstractPOM> getPomOf(): T {
        val pom = poms[T::class.java]
        return if (T::class.java.isInstance(pom)) pom as T else throw RuntimeException(
            "Some woodoo magic happened. " +
                    "Expected to find [${T::class.java.name}] in POM list.\n" +
                    "But found ${poms.keys.joinToString(",") { it.name }}"
        )
    }
}