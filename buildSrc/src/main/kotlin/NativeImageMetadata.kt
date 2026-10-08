import groovy.json.JsonOutput
import java.lang.reflect.AnnotatedElement
import java.lang.reflect.Constructor
import java.lang.reflect.Modifier
import java.net.URLClassLoader
import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

/**
 * Writes the GraalVM Native Image reachability metadata that Jackson and Kotlin reflection need for
 * a module's classes, at `META-INF/native-image/<group>/<name>/reflect-config.json` under
 * [outputDirectory].
 *
 * It registers what `META-INF/proguard/anthropic-java-core.pro` keeps, read from the compiled
 * classes so it covers every class without relying on what a test run happens to exercise:
 * - every class, so Kotlin reflection can load the classes a `@Metadata` annotation names;
 * - the constructors (including Kotlin's synthetic default-argument one), fields and annotated
 *   methods of each class with a Jackson annotation, which Jackson calls reflectively; and
 * - the no-argument constructor of each class that a Jackson annotation refers to (serializers,
 *   deserializers and filters), which Jackson instantiates reflectively.
 */
@CacheableTask
abstract class NativeImageMetadata : DefaultTask() {
    @get:Classpath abstract val classesDirs: ConfigurableFileCollection

    /** The classes that [classesDirs] refer to, so they load. */
    @get:Classpath abstract val runtimeClasspath: ConfigurableFileCollection

    @get:Input abstract val groupId: Property<String>

    @get:Input abstract val artifactId: Property<String>

    @get:OutputDirectory abstract val outputDirectory: DirectoryProperty

    @TaskAction
    fun generate() {
        val classNames =
            classesDirs.files
                .flatMap { dir ->
                    dir.walk()
                        .filter { it.isFile && it.extension == "class" }
                        .map { it.relativeTo(dir).invariantSeparatorsPath }
                }
                // Skip `module-info` and the multi-release copies of the same classes.
                .filter { !it.startsWith("META-INF/") && !it.endsWith("module-info.class") }
                .map { it.removeSuffix(".class").replace('/', '.') }
                .toSortedSet()

        val urls = (classesDirs + runtimeClasspath).map { it.toURI().toURL() }.toTypedArray()
        val entries =
            URLClassLoader(urls, ClassLoader.getPlatformClassLoader()).use { loader ->
                val classes =
                    classNames
                        .map { Class.forName(it, false, loader) }
                        .filter { !it.isAnonymousClass && !it.isLocalClass && !it.isSynthetic }
                val entries = classes.associate { it.name to Entry(it.name) }.toSortedMap()

                for (cls in classes) {
                    val entry = entries.getValue(cls.name)
                    val annotatedMethods = cls.declaredMethods.filter { it.hasJacksonAnnotation() }
                    val hasJacksonAnnotation =
                        cls.hasJacksonAnnotation() ||
                            annotatedMethods.isNotEmpty() ||
                            cls.declaredFields.any { it.hasJacksonAnnotation() } ||
                            cls.declaredConstructors.any { constructor ->
                                constructor.hasJacksonAnnotation() ||
                                    constructor.parameterAnnotations.flatten().any {
                                        it.isJackson()
                                    }
                            }
                    if (hasJacksonAnnotation) {
                        entry.allConstructorsAndFields = true
                        annotatedMethods.forEach { entry.addMethod(it.name, it.parameterTypes) }
                    }

                    for (referenced in cls.referencedByJacksonAnnotations()) {
                        val noArgConstructor = referenced.noArgConstructor() ?: continue
                        entries
                            .getOrPut(referenced.name) { Entry(referenced.name) }
                            .addMethod("<init>", noArgConstructor.parameterTypes)
                    }
                }
                entries.values.map { it.toJson() }
            }

        val file =
            outputDirectory
                .file(
                    "META-INF/native-image/${groupId.get()}/${artifactId.get()}/reflect-config.json"
                )
                .get()
                .asFile
        file.parentFile.mkdirs()
        file.writeText(JsonOutput.prettyPrint(JsonOutput.toJson(entries)) + "\n")
    }

    private class Entry(val name: String) {
        var allConstructorsAndFields = false
        private val methods = sortedMapOf<String, List<String>>()

        fun addMethod(name: String, parameterTypes: Array<Class<*>>) {
            val typeNames = parameterTypes.map { it.typeName }
            methods["$name(${typeNames.joinToString()})"] = typeNames
        }

        fun toJson(): Map<String, Any> = buildMap {
            // Only when the image can reach the class, so unused API types add nothing to it.
            put("condition", mapOf("typeReachable" to name))
            put("name", name)
            if (allConstructorsAndFields) {
                put("allDeclaredConstructors", true)
                put("allDeclaredFields", true)
                // Kotlin reflection looks the methods up to match them to functions and properties.
                put("queryAllDeclaredMethods", true)
            }
            if (methods.isNotEmpty()) {
                put(
                    "methods",
                    methods.map { (signature, parameterTypes) ->
                        mapOf(
                            "name" to signature.substringBefore('('),
                            "parameterTypes" to parameterTypes,
                        )
                    },
                )
            }
        }
    }
}

private fun Annotation.isJackson() =
    annotationClass.java.name.startsWith("com.fasterxml.jackson.annotation.") ||
        annotationClass.java.name.startsWith("com.fasterxml.jackson.databind.annotation.")

private fun AnnotatedElement.hasJacksonAnnotation() = declaredAnnotations.any { it.isJackson() }

/** The classes that a non-default `Class` value of a Jackson annotation on [this] refers to. */
private fun Class<*>.referencedByJacksonAnnotations(): Sequence<Class<*>> {
    val annotations =
        declaredAnnotations.asSequence() +
            declaredMethods.asSequence().flatMap { it.declaredAnnotations.asSequence() } +
            declaredFields.asSequence().flatMap { it.declaredAnnotations.asSequence() } +
            declaredConstructors.asSequence().flatMap { constructor ->
                constructor.declaredAnnotations.asSequence() +
                    constructor.parameterAnnotations.asSequence().flatMap { it.asSequence() }
            }
    return annotations
        .filter { it.isJackson() }
        .flatMap { annotation ->
            annotation.annotationClass.java.declaredMethods.asSequence().flatMap { element ->
                val value = element.invoke(annotation)
                if (value == element.defaultValue) {
                    return@flatMap emptySequence()
                }
                when (value) {
                    is Class<*> -> sequenceOf(value)
                    is Array<*> -> value.asSequence().filterIsInstance<Class<*>>()
                    else -> emptySequence()
                }
            }
        }
}

private fun Class<*>.noArgConstructor(): Constructor<*>? =
    if (isInterface || Modifier.isAbstract(modifiers)) null
    else declaredConstructors.firstOrNull { it.parameterCount == 0 }
