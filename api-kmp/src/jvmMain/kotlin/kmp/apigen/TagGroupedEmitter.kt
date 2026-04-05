package kmp.apigen

import java.io.File

/**
 * Base class for protocol emitters that group OpenAPI paths by their
 * first tag and emit **one file per tag** into a sub-package of the
 * output directory.
 *
 * Pattern extracted from the shared structure of [ServerRouteEmitter] and
 * [CamelRouteEmitter], both of which previously had copies of:
 *
 * ```kotlin
 * val dir = File(outputDir, subPackage.replace(".", "/"))
 * dir.mkdirs()
 * val byTag = spec.paths.values.groupBy { it.tags.firstOrNull() ?: "Default" }
 * byTag.forEach { (tag, ops) ->
 *     File(dir, "${tag.capitalize()}<Suffix>.kt").writeText(buildFile(...))
 * }
 * ```
 *
 * Concrete emitters only have to override [subPackageName], [fileNameFor],
 * and [buildTagFile] — the grouping + directory creation + per-tag file
 * emission is done once here.
 *
 * The base also calls [afterTagFiles] once after all per-tag files are
 * written, so emitters can drop a top-level entrypoint (e.g.
 * ServerRouteEmitter's `GeneratedApplication.kt`, CamelRouteEmitter's
 * `llm-providers.properties`).
 */
abstract class TagGroupedEmitter : ProtocolEmitter {

    /** Sub-package appended to `pkg` (e.g. `"server"`, `"camel"`). */
    protected abstract val subPackageName: String

    /**
     * Build the contents of one tag's output file. Called once per tag
     * with the operations belonging to that tag.
     */
    protected abstract fun buildTagFile(
        fullPkg: String,
        tag: String,
        operations: List<ParsedPath>,
        spec: ParsedSpec,
    ): String

    /** File name for a given tag — e.g. `"${Tag}Routes.kt"` or `"${Tag}RouteBuilder.kt"`. */
    protected abstract fun fileNameFor(tag: String): String

    /**
     * Optional hook invoked once after all per-tag files are written.
     * Default: no-op. Emitters use it to drop a top-level entrypoint
     * (e.g. GeneratedApplication.kt, llm-providers.properties).
     */
    protected open fun afterTagFiles(
        tags: Set<String>,
        fullPkg: String,
        dir: File,
        spec: ParsedSpec,
    ) {}

    final override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        val fullPkg = "$pkg.$subPackageName"
        val dir = File(outputDir, fullPkg.replace(".", "/"))
        dir.mkdirs()

        // Group paths by first tag → one file per tag
        val byTag = spec.paths.values.groupBy { it.tags.firstOrNull() ?: "Default" }

        byTag.forEach { (tag, ops) ->
            File(dir, fileNameFor(tag)).writeText(buildTagFile(fullPkg, tag, ops, spec))
        }

        afterTagFiles(byTag.keys, fullPkg, dir, spec)
    }
}
