package com.task.utils

import java.io.File
import java.net.URL
import java.util.*


object ResourceAccessor {
    fun listFiles(directory: String): List<File> {
        val loader = Thread.currentThread().contextClassLoader
        val url: URL = loader.getResource(directory) ?: throw RuntimeException("No such directory")
        val path: String = url.path
        return File(path).listFiles()!!.toList()
    }

    fun getProperty(property: String): String? {
        this::class.java.classLoader.getResourceAsStream("config.properties").use { input ->
            val prop = Properties()
            prop.load(input)
            return prop.getProperty(property)
        }

    }
}