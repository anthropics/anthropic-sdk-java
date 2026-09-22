import java.io.File
import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.Classpath
import org.gradle.api.tasks.IgnoreEmptyDirectories
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.SkipWhenEmpty
import org.gradle.api.tasks.TaskAction
import org.gradle.work.DisableCachingByDefault
import org.gradle.work.Incremental
import org.gradle.work.InputChanges

/**
 * Lists the Kotlin files that changed since a `detektChanged*` task last passed, for that task to
 * check. It lists every file when Gradle cannot tell what changed.
 */
@DisableCachingByDefault(because = "The list depends on what this checkout has checked before")
abstract class DetektChangedFiles : DefaultTask() {
    // Gradle reports changes file by file for an input marked @SkipWhenEmpty or @Incremental (it
    // rejects both together). A change to any other input makes it report every file as added.
    // So a change to `settings` lists every file, and a change to `classpath` lists none.
    @get:PathSensitive(PathSensitivity.RELATIVE)
    @get:SkipWhenEmpty
    @get:IgnoreEmptyDirectories
    @get:InputFiles
    abstract val source: ConfigurableFileCollection

    /**
     * The detekt task's classpath. What changed in it is ignored: it is an input so that this task
     * runs, and writes a new list, whenever the detekt task would run again. Otherwise an edit in
     * one module would make every module that depends on it check its whole last list again.
     */
    @get:Incremental @get:Classpath abstract val classpath: ConfigurableFileCollection

    /** The files that affect how detekt judges every file: a change to them lists every file. */
    @get:PathSensitive(PathSensitivity.NONE)
    @get:InputFiles
    abstract val settings: ConfigurableFileCollection

    /**
     * A file that the detekt task writes when it passes, and that this task deletes when it writes
     * a new list. So it is there only if detekt has passed with the last list.
     */
    @get:Internal abstract val passed: RegularFileProperty

    @get:OutputFile abstract val fileList: RegularFileProperty

    @TaskAction
    fun writeFileList(changes: InputChanges) {
        val listFile = fileList.get().asFile
        // When Gradle reports every file, it has deleted the last list.
        val pending =
            if (!changes.isIncremental || passed.get().asFile.exists()) emptyList()
            else listFile.readLines().map(::File)
        // Gradle also reports directories and the files that were removed.
        val reported = changes.getFileChanges(source).map { it.file }
        val files = (pending + reported).filter(File::isFile).toSet()
        if (files.isNotEmpty()) {
            val total = source.files.size
            logger.lifecycle("detekt: checking {} of {} Kotlin files", files.size, total)
        }
        passed.get().asFile.delete()
        listFile.writeText(files.joinToString("\n"))
    }
}
