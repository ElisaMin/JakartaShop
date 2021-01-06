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
package org.apache.ibatis.io

import java.io.InputStream
import java.net.URL

/**
 * A class to wrap access to multiple class loaders making them work as one
 *
 * @author Clinton Begin
 */
class ClassLoaderWrapper internal constructor() {
    var defaultClassLoader: ClassLoader? = null
    var systemClassLoader: ClassLoader? = null

    /**
     * Get a resource as a URL using the current class path
     *
     * @param resource - the resource to locate
     * @return the resource or null
     */
    fun getResourceAsURL(resource: String): URL? {
        return getResourceAsURL(resource, getClassLoaders(null))
    }

    /**
     * Get a resource from the classpath, starting with a specific class loader
     *
     * @param resource    - the resource to find
     * @param classLoader - the first classloader to try
     * @return the stream or null
     */
    fun getResourceAsURL(resource: String, classLoader: ClassLoader?): URL? {
        return getResourceAsURL(resource, getClassLoaders(classLoader))
    }

    /**
     * Get a resource from the classpath
     *
     * @param resource - the resource to find
     * @return the stream or null
     */
    fun getResourceAsStream(resource: String): InputStream? {
        return getResourceAsStream(resource, getClassLoaders(null))
    }

    /**
     * Get a resource from the classpath, starting with a specific class loader
     *
     * @param resource    - the resource to find
     * @param classLoader - the first class loader to try
     * @return the stream or null
     */
    fun getResourceAsStream(resource: String, classLoader: ClassLoader?): InputStream? {
        return getResourceAsStream(resource, getClassLoaders(classLoader))
    }

    /**
     * Find a class on the classpath (or die trying)
     *
     * @param name - the class to look for
     * @return - the class
     * @throws ClassNotFoundException Duh.
     */
    @Throws(ClassNotFoundException::class)
    fun classForName(name: String): Class<*> {
        return classForName(name, getClassLoaders(null))
    }

    /**
     * Find a class on the classpath, starting with a specific classloader (or die trying)
     *
     * @param name        - the class to look for
     * @param classLoader - the first classloader to try
     * @return - the class
     * @throws ClassNotFoundException Duh.
     */
    @Throws(ClassNotFoundException::class)
    fun classForName(name: String, classLoader: ClassLoader?): Class<*> {
        return classForName(name, getClassLoaders(classLoader))
    }

    /**
     * Try to get a resource from a group of classloaders
     *
     * @param resource    - the resource to get
     * @param classLoader - the classloaders to examine
     * @return the resource or null
     */
    fun getResourceAsStream(resource: String, classLoader: Array<ClassLoader?>): InputStream? {
        for (cl in classLoader) {
            if (null != cl) {

                // try to find the resource as passed
                var returnValue = cl.getResourceAsStream(resource)

                // now, some class loaders want this leading "/", so we'll add it and try again if we didn't find the resource
                if (null == returnValue) {
                    returnValue = cl.getResourceAsStream("/$resource")
                }
                if (null != returnValue) {
                    return returnValue
                }
            }
        }
        return null
    }

    /**
     * Get a resource as a URL using the current class path
     *
     * @param resource    - the resource to locate
     * @param classLoader - the class loaders to examine
     * @return the resource or null
     */
    fun getResourceAsURL(resource: String, classLoader: Array<ClassLoader?>): URL? {
        var url: URL
        for (cl in classLoader) {
            if (null != cl) {

                // look for the resource as passed in...
                url = cl.getResource(resource)

                // ...but some class loaders want this leading "/", so we'll add it
                // and try again if we didn't find the resource
                if (null == url) {
                    url = cl.getResource("/$resource")
                }

                // "It's always in the last place I look for it!"
                // ... because only an idiot would keep looking for it after finding it, so stop looking already.
                if (null != url) {
                    return url
                }
            }
        }

        // didn't find it anywhere.
        return null
    }

    /**
     * Attempt to load a class from a group of classloaders
     *
     * @param name        - the class to load
     * @param classLoader - the group of classloaders to examine
     * @return the class
     * @throws ClassNotFoundException - Remember the wisdom of Judge Smails: Well, the world needs ditch diggers, too.
     */
    @Throws(ClassNotFoundException::class)
    fun classForName(name: String, classLoader: Array<ClassLoader?>): Class<*> {
        for (cl in classLoader) {
            if (null != cl) {
                try {
                    return Class.forName(name, true, cl)
                } catch (e: ClassNotFoundException) {
                    // we'll ignore this until all classloaders fail to locate the class
                }
            }
        }
        throw ClassNotFoundException("Cannot find class: $name")
    }

    fun getClassLoaders(classLoader: ClassLoader?): Array<ClassLoader?> {
        return arrayOf(
            classLoader,
            defaultClassLoader,
            Thread.currentThread().contextClassLoader,
            javaClass.classLoader,
            systemClassLoader
        )
    }

    init {
        try {
            systemClassLoader = ClassLoader.getSystemClassLoader()
        } catch (ignored: SecurityException) {
            // AccessControlException on Google App Engine
        }
    }
}