import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import java.lang.module.ModuleFinder
import java.util.jar.JarFile
import java.util.spi.ToolProvider
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.attributes.LibraryElements
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.the
import org.gradle.process.CommandLineArgumentProvider

/**
 * Makes the project's jar a JPMS module when it has a `src/main/java9/module-info.java`, without
 * giving up Java 8 support: the classes stay Java 8 bytecode and the descriptor, compiled for Java
 * 9, is added under `META-INF/versions/9/` (a multi-release jar, the same layout as jackson-core
 * and kotlin-stdlib). Java 8 ignores that entry; the Java 9+ module path sees a real module.
 */
fun Project.configureModuleInfo() {
    val moduleInfo = layout.projectDirectory.file("src/main/java9/module-info.java")
    if (!moduleInfo.asFile.exists()) {
        return
    }
    // `--patch-module` needs the name the descriptor declares.
    val moduleName =
        Regex("""^\s*(?:open\s+)?module\s+([\w.]+)""", RegexOption.MULTILINE)
            .find(providers.fileContents(moduleInfo).asText.get())
            ?.groupValues
            ?.get(1)
            ?: throw GradleException("No `module <name>` declaration in ${moduleInfo.asFile}")
    val mainClassesDirs = the<SourceSetContainer>()["main"].output.classesDirs
    // Jars rather than class directories: a sibling module's descriptor only exists in its jar.
    val modulePath =
        configurations["compileClasspath"]
            .incoming
            .artifactView {
                attributes {
                    attribute(
                        LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE,
                        objects.named(LibraryElements::class.java, LibraryElements.JAR),
                    )
                }
            }
            .files
    val compileModuleInfo =
        tasks.register<JavaCompile>("compileModuleInfo") {
            source("src/main/java9")
            classpath = files()
            destinationDirectory.set(layout.buildDirectory.dir("classes/java/moduleInfo"))
            // `module-info.java` needs Java 9+; everything else still uses `--release 8`.
            options.release.set(9)
            // Most dependencies are automatic modules (no own `module-info.class`); expected.
            options.compilerArgs.add("-Xlint:-requires-automatic,-requires-transitive-automatic")
            modularity.inferModulePath.set(false)
            options.compilerArgumentProviders.add(
                ModuleInfoCompilerArguments(moduleName, modulePath, mainClassesDirs)
            )
        }
    tasks.named<Jar>("jar") {
        manifest.attributes(mapOf("Multi-Release" to "true"))
        into("META-INF/versions/9") { from(compileModuleInfo.flatMap { it.destinationDirectory }) }
    }
}

class ModuleInfoCompilerArguments(
    @get:Input val moduleName: String,
    @get:Classpath val modulePath: FileCollection,
    @get:InputFiles @get:PathSensitive(PathSensitivity.RELATIVE) val patchDirs: FileCollection,
) : CommandLineArgumentProvider {
    override fun asArguments(): Iterable<String> = buildList {
        add("--module-path")
        add(modulePath.asPath)
        // `exports` must name packages javac can see, so overlay the compiled classes.
        val classesDirs = patchDirs.filter { it.exists() }
        if (!classesDirs.isEmpty) {
            add("--patch-module")
            add("$moduleName=${classesDirs.asPath}")
        }
    }
}

/**
 * Fails when a jar's static module descriptor has fallen behind its code: the bytecode uses a
 * module the descriptor doesn't read, or the jar has a class package the descriptor doesn't export.
 */
abstract class CheckModuleInfo : DefaultTask() {
    /** The jars to check. */
    @get:Classpath abstract val jars: ConfigurableFileCollection

    /** Everything the jars run against. */
    @get:Classpath abstract val modulePath: ConfigurableFileCollection

    @TaskAction
    fun check() {
        val problems = jars.files.sorted().flatMap(::problems)
        if (problems.isNotEmpty()) {
            throw GradleException(
                problems.joinToString("\n", prefix = "Incomplete module descriptors:\n") { "  $it" }
            )
        }
    }

    private fun problems(jar: File): List<String> {
        val descriptor = ModuleFinder.of(jar.toPath()).findAll().single().descriptor()
        if (descriptor.isAutomatic) {
            return listOf("${jar.name} has no module descriptor")
        }
        val name = descriptor.name()
        val otherJars = modulePath.files.filter { it != jar }
        val unreadModules =
            unreadPackages(jar, otherJars).map { moduleOf(it, otherJars) }.toSortedSet()
        val unexportedPackages =
            classPackages(jar) - descriptor.exports().map { it.source() }.toSet()
        return unreadModules.map { "$name uses $it but doesn't require it" } +
            unexportedPackages.sorted().map { "$name has classes in $it but doesn't export it" }
    }

    /**
     * Packages the bytecode uses from modules the descriptor doesn't read. jdeps resolves the jar
     * as the module it declares, so it reports each such package as "not found".
     */
    private fun unreadPackages(jar: File, otherJars: List<File>): Set<String> {
        val output = StringWriter()
        val writer = PrintWriter(output)
        val exitCode =
            ToolProvider.findFirst("jdeps")
                .orElseThrow { GradleException("jdeps is missing; run Gradle on a full JDK.") }
                .run(
                    writer,
                    writer,
                    "--multi-release",
                    "9",
                    "--module-path",
                    otherJars.joinToString(File.pathSeparator),
                    "-verbose:package",
                    jar.path,
                )
        if (exitCode != 0) {
            throw GradleException("jdeps failed on ${jar.name}:\n$output")
        }
        // Lines look like `<package> -> <package> <module or "not found">`.
        return output
            .toString()
            .lines()
            .map { it.trim().split(Regex("\\s+")) }
            .filter { it.size == 5 && it[1] == "->" && it[3] == "not" && it[4] == "found" }
            .map { it[2] }
            .toSet()
    }

    private fun moduleOf(packageName: String, otherJars: List<File>): String =
        ModuleFinder.compose(
                ModuleFinder.of(*otherJars.map { it.toPath() }.toTypedArray()),
                ModuleFinder.ofSystem(),
            )
            .findAll()
            .firstOrNull { packageName in it.descriptor().packages() }
            ?.descriptor()
            ?.name() ?: "the module of $packageName (not on the module path)"

    private fun classPackages(jar: File): Set<String> =
        JarFile(jar).use { file ->
            file
                .entries()
                .asSequence()
                .map { it.name }
                .filter { it.endsWith(".class") && it.contains('/') && !it.startsWith("META-INF/") }
                .map { it.substringBeforeLast('/').replace('/', '.') }
                .toSet()
        }
}
