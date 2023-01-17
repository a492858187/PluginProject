package org.tq.router.processor;

import com.google.auto.service.AutoService;

import org.tq.router.annotations.Destination;

import java.io.Writer;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class DestinationProcessor extends AbstractProcessor {

    private static final String TAG = "DestinationProcessor";

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Destination.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if(roundEnv.processingOver()) {
            return false;
        }
        System.out.println(TAG + ">>> process start ...");

        String rootDir = processingEnv.getOptions().get("root_project_dir");
        if(rootDir != null){
            System.out.println(TAG + "rootDir: " + rootDir);
            return false;
        }

        Set<Element> allDestinationElements = (Set<Element>) roundEnv.getElementsAnnotatedWith(Destination.class);
        System.out.println(TAG + ">>> all Destination elements count = " + allDestinationElements.size());
        if(allDestinationElements.size() < 1) {
            return false;
        }

        String className = "RouterMapping_" + System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        builder.append("package org.tq.pluginproject.mapping;\n\n");
        builder.append("import java.util.HashMap;\n");
        builder.append("import java.util.Map;\n");
        builder.append("public class ").append(className).append("{\n\n");
        builder.append("    public static Map<String, String> get() {\n\n");
        builder.append("        Map<String, String> mapping = new HashMap<>();\n\n");


        for(Element element: allDestinationElements){
            final TypeElement typeElement = (TypeElement) element;
            final Destination destination = typeElement.getAnnotation(Destination.class);
            if(destination == null) continue;
            final String url = destination.url();
            final String description = destination.description();
            final String realPath = typeElement.getQualifiedName().toString();

            System.out.println(TAG + ">>> url = " + url);
            System.out.println(TAG + ">>> description = " + description);
            System.out.println(TAG + ">>> realPath = " + realPath);
            builder.append("        ")
                    .append("mapping.put(")
                    .append("\"" + url + "\"")
                    .append(", ")
                    .append("\"" + realPath + "\"")
                    .append(");\n");
        }
        builder.append("        return mapping;\n");
        builder.append("    }\n");
        builder.append("}");

        String mappingFullClassName = "org.tq.router.mapping." + className;

        try {
            JavaFileObject source = processingEnv.getFiler().createSourceFile(mappingFullClassName);
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println(TAG + ">>> process finished ...");

        return false;
    }

}
