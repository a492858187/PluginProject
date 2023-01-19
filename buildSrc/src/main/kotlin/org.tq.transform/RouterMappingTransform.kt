package org.tq.transform

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import org.apache.commons.io.FileUtils

class RouterMappingTransform : Transform() {
    override fun getName(): String {
        return "RouterMappingTransform"
    }

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    override fun isIncremental(): Boolean {
        return false
    }

    override fun transform(transformInvocation: TransformInvocation?) {
        val collector = RouterMappingCollector()
        transformInvocation?.inputs?.forEach {
            it.directoryInputs.forEach { directoryInput ->
                val destDir = transformInvocation.outputProvider.getContentLocation(
                    directoryInput.name,
                    directoryInput.contentTypes,
                    directoryInput.scopes,
                    Format.DIRECTORY
                )
                collector.collect(directoryInput.file)
                FileUtils.copyDirectory(directoryInput.file, destDir)
            }

            // 二、输入源为 jar 包类型
            it.jarInputs.forEach { jarInput ->
                //1、TODO 针对 jar 包进行相关处理
                //2、构建输出路径 dest
                val destDir = transformInvocation.outputProvider.getContentLocation(
                    jarInput.name,
                    jarInput.contentTypes,
                    jarInput.scopes,
                    Format.JAR
                )
                collector.collectFromJarFile(jarInput.file)
                //3、将 jar 包复制给 dest，dest 将会传递给下一个 Transform
                FileUtils.copyFile(jarInput.file, destDir)
            }
        }
        println("$name all mapping class name = ${collector.getMappingClassName()}")
    }
}