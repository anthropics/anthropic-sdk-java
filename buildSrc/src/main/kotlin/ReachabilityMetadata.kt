import groovy.json.JsonOutput
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.the
import org.objectweb.asm.ClassReader
import org.objectweb.asm.Type
import org.objectweb.asm.tree.AnnotationNode
import org.objectweb.asm.tree.ClassNode
import org.objectweb.asm.tree.MethodNode

fun Project.configureReachabilityMetadata() {
    val mainClassesDirs = the<SourceSetContainer>()["main"].output.classesDirs
    val generateReachabilityMetadata =
        tasks.register<GenerateReachabilityMetadata>("generateReachabilityMetadata") {
            classes.from(mainClassesDirs)
            reflectConfig.set(
                layout.buildDirectory.file("reachability-metadata/reflect-config.json")
            )
        }
    val directory = "META-INF/native-image/$group/$name"
    tasks.named<Jar>("jar") { into(directory) { from(generateReachabilityMetadata) } }
}

/**
 * Registers what Jackson reflects on. For a class with a Jackson annotation on it or on a member,
 * that is the methods that carry one, every field and every constructor (Kotlin reflection calls
 * the synthetic one to apply default arguments). The overloads of those methods can be looked up
 * but not called: to find the Kotlin function behind a method, Kotlin reflection looks up the
 * method behind each function of that name, and throws if one is missing. For a class that an
 * annotation names for Jackson to instantiate, it is every constructor.
 *
 * The `typeReachable` condition on each entry keeps unused classes out of the image.
 * `reachability-metadata.json` can't express it: its only condition is `typeReached`, which a class
 * doesn't meet yet when Jackson first reflects on it.
 */
abstract class GenerateReachabilityMetadata : DefaultTask() {
    @get:Classpath abstract val classes: ConfigurableFileCollection

    @get:OutputFile abstract val reflectConfig: RegularFileProperty

    @TaskAction
    fun generate() {
        val classNodes =
            classes.asFileTree
                .matching { include("**/*.class") }
                .files
                .map {
                    ClassNode().apply {
                        ClassReader(it.readBytes()).accept(this, ClassReader.SKIP_CODE)
                    }
                }
                .associateBy { it.name }
        val reflected = classNodes.values.filter { it.jackson().isNotEmpty() }.sortedBy { it.name }
        val entries =
            reflected.flatMap { classNode ->
                val (annotated, others) =
                    classNode.methods
                        .filter { it.name != "<init>" }
                        .partition { it.jackson().isNotEmpty() }
                val overloads = others.filter { other -> annotated.any { it.name == other.name } }
                val instantiated =
                    classNode.jackson().flatMap { it.values.orEmpty() }.filterIsInstance<Type>()
                listOf(
                    entry(classNode.name) +
                        mapOf(
                            "allDeclaredFields" to true,
                            "queriedMethods" to overloads.map { it.signature() },
                            "methods" to annotated.map { it.signature() },
                        )
                ) +
                    instantiated
                        .map { it.internalName }
                        .toSortedSet()
                        .map { entry(it, classNode.name) }
            } +
                // Kotlin reflection finds a nested class by name through the classes around it.
                reflected
                    .flatMap {
                        generateSequence(it.outer()) { outer -> classNodes.getValue(outer).outer() }
                    }
                    .minus(reflected.map { it.name }.toSet())
                    .toSortedSet()
                    .map { entry(it) }
        if (entries.isNotEmpty()) {
            reflectConfig.get().asFile.writeText(JsonOutput.prettyPrint(JsonOutput.toJson(entries)))
        }
    }

    private fun entry(name: String, reachable: String = name) =
        mapOf(
            "condition" to mapOf("typeReachable" to Type.getObjectType(reachable).className),
            "name" to Type.getObjectType(name).className,
            "allDeclaredConstructors" to true,
        )
}

private fun ClassNode.outer() = innerClasses.find { it.name == name }?.outerName

private fun ClassNode.jackson() =
    visibleAnnotations.jackson() +
        fields.flatMap { it.visibleAnnotations.jackson() } +
        methods.flatMap { it.jackson() }

private fun MethodNode.jackson() =
    visibleAnnotations.jackson() + visibleParameterAnnotations.orEmpty().flatMap { it.jackson() }

private fun MethodNode.signature() =
    mapOf("name" to name, "parameterTypes" to Type.getArgumentTypes(desc).map { it.className })

private fun List<AnnotationNode>?.jackson() =
    orEmpty().filter { it.desc.startsWith("Lcom/fasterxml/jackson/") }
