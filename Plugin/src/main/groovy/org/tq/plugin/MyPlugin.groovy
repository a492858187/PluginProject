package org.tq.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("this is MyPlugin")
        if(project.extensions.findByName("kapt") != null) {
            project.extensions.findByName("kapt").arguments {
                arg("root_project_dir", project.rootProject.projectDir.absolutePath)
            }
        }

        project.clean.doFirst {
            File routerMappingDir = new File(project.rootProject.projectDir, "router_mapping")
            if(routerMappingDir.exists()){
                routerMappingDir.deleteDir()
            }
        }

        project.getExtensions().create("router", RouterExtension)
        project.afterEvaluate {
            RouterExtension extension = project["router"]

            println("用户设置的路径： ${extension.wikiDir}")

            project.tasks.findAll {task->
                task.name.startsWith('compile') && task.name.endsWith('JavaWithJavac')
            }.each {task->
                task.doLast {
                    File routerMappingDir = new File(project.rootProject.projectDir, "router_mapping")
                    if(!routerMappingDir.exists()){
                        return
                    }
                    File[] allChildFiles = routerMappingDir.listFiles()

                    if(allChildFiles.length < 1){
                        return
                    }
                    //开始输出markdown文档
                }
            }
        }
    }
}