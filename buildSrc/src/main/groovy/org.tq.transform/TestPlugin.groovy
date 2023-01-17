package org.tq.transform

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class TestPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("-----------TestPlugin-----------")

//        AppExtension appExtension = project.getExtensions().getByType(AppExtension.class)
//        appExtension.registerTransform(RouterMappingTransform(project))
//        androidComponents.onVariants() { variant ->
//            variant.instrumentation.transformClassesWith(
//                    RouterMappingTransform.class, InstrumentationScope.PROJECT){}
//            variant.instrumentation.setAsmFramesComputationMode(FramesComputationMode.COPY_FRAMES)
//        }
    }
}