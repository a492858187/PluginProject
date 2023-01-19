package org.tq.transform

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.AppPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.attributes.Attribute

class TestPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        println("-----------TestPlugin-----------")

//        val appExtension = project.extensions.getByType(AndroidComponentsExtension::class.java)
//        appExtension.onVariants { variant ->
//            variant.instrumentation.transformClassesWith(
//                RouterMappingTransform::class.java, InstrumentationScope.PROJECT
//            ) {}
//            variant.instrumentation.setAsmFramesComputationMode(FramesComputationMode.COPY_FRAMES)
//        }
//        project.run {
//            val artifactType = Attribute.of("artifactType", String::class.java)
//            println("-----------TestPlugin----------- artifactType: $artifactType")
//            dependencies.registerTransform(RouterTransform::class.java){
//                it.from.attribute(artifactType, "jar")
//                it.to.attribute(artifactType, "my-custom-type")
//            }
//            println("-----------TestPlugin----------- transform register success")
//        }
        if(project.plugins.hasPlugin(AppPlugin::class.java)){
            val appExtension = project.extensions.getByType(AppExtension::class.java)
            val transform = RouterMappingTransform()
            appExtension.registerTransform(transform)
        }
    }
}