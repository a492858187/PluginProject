package org.tq.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("this is MyPlugin")
    }

}