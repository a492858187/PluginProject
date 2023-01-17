package org.tq.transform

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.InstrumentationParameters
import org.objectweb.asm.ClassVisitor

abstract class RouterMappingTransform implements AsmClassVisitorFactory<InstrumentationParameters.None> {

    @Override
    ClassVisitor createClassVisitor( ClassContext classContext, ClassVisitor classVisitor) {
        return null
    }

    @Override
    boolean isInstrumentable(ClassData classData) {
        return false
    }
}