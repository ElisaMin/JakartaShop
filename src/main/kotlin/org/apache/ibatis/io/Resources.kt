package org.apache.ibatis.io

import java.io.*
import java.net.URL
import java.nio.charset.Charset
import java.util.*

/**
 * Copyright 2009-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * A class to simplify access to resources through the classloader.
 *
 * @author Clinton Begin
 */
object Resources {
    private val classLoaderWrapper: ClassLoaderWrapper = ClassLoaderWrapper()

    /**
     * Charset to use when calling getResourceAsReader.
     * null means use the system default.
     */
    var charset: Charset? = null
    /**
     * Returns the default classloader (may be null).
     *
     * @return The default classloader
     */
    /**
     * Sets the default classloader
     *
     * @param defaultClassLoader - the new default ClassLoader
     */
    var defaultClassLoader: ClassLoader?
        get() = classLoaderWrapper.defaultClassLoader
        set(defaultClassLoader) {
            classLoaderWrapper.defaultClassLoader = defaultClassLoader
        }

    /**
     * Returns the URL of the resource on the classpath
     *
     * @param resource The resource to find
     * @return The resource
     * @throws java.io.IOException If the resource cannot be found or read
     */
    @Throws(IOException::class)
    fun getResourceURL(resource: String): URL {
        // issue #625
        return getResourceURL(null, resource)
    }

    /**
     * Returns the URL of the resource on the classpath
     *
     * @param loader   The classloader used to fetch the resource
     * @param resource The resource to find
     * @return The resource
     * @throws java.io.IOException If the resource cannot be found or read
     */
    @Throws(IOException::class)
    fun getResourceURL(loader: ClassLoader?, resource: String): URL {
        return classLoaderWrapper.getResourceAsURL(resource, loader)
            ?: throw IOException("Could not find resource $resource")
    }

    /**
     * Returns a resource on the classpath as a Stream object
     *
     * @param resource The resource to find
     * @return The resource
     * @throws java.io.IOException If the resource cannot be found or read
     */
    @Throws(IOException::class)
    fun getResourceAsStream(resource: String): InputStream {
        return getResourceAsStream(null, resource)
    }

    /**
     * Returns a resource on the classpath as a Stream object
     *
     * @param loader   The classloader used to fetch the resource
     * @param resource The resource to find
     * @return The resource
     * @throws java.io.IOException If the resource cannot be found or read
     */
    @Throws(IOException::class)
    fun getResourceAsStream(loader: ClassLoader?, resource: String): InputStream {
        return classLoaderWrapper.getResourceAsStream(resource, loader)
            ?: throw IOException("Could not find resource $resource")
    }

    /**
     * Returns a resource on the classpath as a Properties object
     *
     * @param resource The resource to find
     * @return The resource
     * @throws java.io.IOException If the resource cannot be found or read
     */
    @Throws(IOException::class)
    fun getResourceAsProperties(resource: String): Properties {
        val props = Properties()
        getResourceAsStream(resource).use { `in` -> props.load(`in`) }
        return props
    }

    /**
     * Returns a resource on the classpath as a Properties object
     *
     * @param loader   The classloader used to fetch the resource
     * @param resource The resource to find
     * @return The resource
     * @throws java.io.IOException If the resource cannot be found or read
     */
    @Throws(IOException::class)
    fun getResourceAsProperties(loader: ClassLoader?, resource: String): Properties {
        val props = Properties()
        getResourceAsStream(loader, resource).use { `in` -> props.load(`in`) }
        return props
    }

    /**
     * Returns a resource on the classpath as a Reader object
     *
     * @param resource The resource to find
     * @return The resource
     * @throws java.io.IOException If the resource cannot be found or read
     */
    @Throws(IOException::class)
    fun getResourceAsReader(resource: String): Reader {
        val reader: Reader
        reader = if (charset == null) {
            InputStreamReader(getResourceAsStream(resource))
        } else {
            InputStreamReader(getResourceAsStream(resource), charset)
        }
        return reader
    }

    /**
     * Returns a resource on the classpath as a Reader object
     *
     * @param loader   The classloader used to fetch the resource
     * @param resource The resource to find
     * @return The resource
     * @throws java.io.IOException If the resource cannot be found or read
     */
    @Throws(IOException::class)
    fun getResourceAsReader(loader: ClassLoader?, resource: String): Reader {
        val reader: Reader
        reader = if (charset == null) {
            InputStreamReader(getResourceAsStream(loader, resource))
        } else {
            InputStreamReader(getResourceAsStream(loader, resource), charset)
        }
        return reader
    }

    /**
     * Returns a resource on the classpath as a File object
     *
     * @param resource The resource to find
     * @return The resource
     * @throws java.io.IOException If the resource cannot be found or read
     */
    @Throws(IOException::class)
    fun getResourceAsFile(resource: String): File {
        return File(getResourceURL(resource).file)
    }

    /**
     * Returns a resource on the classpath as a File object
     *
     * @param loader   - the classloader used to fetch the resource
     * @param resource - the resource to find
     * @return The resource
     * @throws java.io.IOException If the resource cannot be found or read
     */
    @Throws(IOException::class)
    fun getResourceAsFile(loader: ClassLoader?, resource: String): File {
        return File(getResourceURL(loader, resource).file)
    }

    /**
     * Gets a URL as an input stream
     *
     * @param urlString - the URL to get
     * @return An input stream with the data from the URL
     * @throws java.io.IOException If the resource cannot be found or read
     */
    @Throws(IOException::class)
    fun getUrlAsStream(urlString: String?): InputStream {
        val url = URL(urlString)
        val conn = url.openConnection()
        return conn.getInputStream()
    }

    /**
     * Gets a URL as a Reader
     *
     * @param urlString - the URL to get
     * @return A Reader with the data from the URL
     * @throws java.io.IOException If the resource cannot be found or read
     */
    @Throws(IOException::class)
    fun getUrlAsReader(urlString: String?): Reader {
        val reader: Reader
        reader = if (charset == null) {
            InputStreamReader(getUrlAsStream(urlString))
        } else {
            InputStreamReader(getUrlAsStream(urlString), charset)
        }
        return reader
    }

    /**
     * Gets a URL as a Properties object
     *
     * @param urlString - the URL to get
     * @return A Properties object with the data from the URL
     * @throws java.io.IOException If the resource cannot be found or read
     */
    @Throws(IOException::class)
    fun getUrlAsProperties(urlString: String?): Properties {
        val props = Properties()
        getUrlAsStream(urlString).use { `in` -> props.load(`in`) }
        return props
    }

    /**
     * Loads a class
     *
     * @param className - the class to fetch
     * @return The loaded class
     * @throws ClassNotFoundException If the class cannot be found (duh!)
     */
    @Throws(ClassNotFoundException::class)
    fun classForName(className: String?): Class<*>? {
        return className?.let { classLoaderWrapper.classForName(it) }
    }
}