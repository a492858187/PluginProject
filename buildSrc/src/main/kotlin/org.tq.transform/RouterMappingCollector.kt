package org.tq.transform

import java.io.File
import java.util.Enumeration
import java.util.jar.JarFile

class RouterMappingCollector {

    private val PACKAGE_NAME = "org/tq/router"
    private val CLASS_NAME_PREFIX = "RouterMapping_"
    private val CLASS_FILE_SUFFIX = ".class"
    private val mappingClassNames = HashSet<String>()

    fun getMappingClassName():Set<String> {
        return mappingClassNames
    }

    fun collect(classFile: File) {
        if(classFile == null || !classFile.exists()) return
        println("Collector---classFile: ${classFile.absolutePath}")
        if(classFile.isFile){
            if(classFile.absolutePath.contains(PACKAGE_NAME)
                && classFile.name.startsWith(CLASS_NAME_PREFIX)
                &&classFile.name.endsWith(CLASS_FILE_SUFFIX)){
                val className = classFile.name.replace(CLASS_FILE_SUFFIX, "")
                mappingClassNames.add(className)
            }
        }else{
            classFile.listFiles().forEach {
                collect(it)
            }
        }
    }

    fun collectFromJarFile(jarFile: File) {
        val enumeration = JarFile(jarFile).entries()
        while (enumeration.hasMoreElements()){
            val jarEntry = enumeration.nextElement()
            val entryName = jarEntry.name
            println("Collector---entryName: $entryName")
            if(entryName.contains(PACKAGE_NAME)
                && entryName.startsWith(CLASS_NAME_PREFIX)
                && entryName.endsWith(CLASS_FILE_SUFFIX)){
                val className = entryName.replace(PACKAGE_NAME, "")
                    .replace("/","")
                    .replace(CLASS_FILE_SUFFIX, "")
                mappingClassNames.add(className)
            }
        }
    }

}