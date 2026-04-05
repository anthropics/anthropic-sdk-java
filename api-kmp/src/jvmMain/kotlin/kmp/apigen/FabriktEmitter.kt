package kmp.apigen

import java.io.File
import java.nio.file.Path

/**
 * Fabrikt integration — delegates to Fabrikt for Kotlin model generation.
 *
 * Fabrikt excels at:
 * - `oneOf` → `sealed interface` with discriminator (SEALED_INTERFACES_FOR_ONE_OF)
 * - Jackson + kotlinx.serialization annotations
 * - Validation annotations (jakarta.validation)
 * - Spring controller stubs
 * - OkHttp client stubs
 *
 * We use Fabrikt alongside our own emitters:
 * - Fabrikt → models + client (JVM, sealed interfaces)
 * - api-gen → Component<T>, Compose UI, Database, GraphQL, gRPC, MCP, tests (KMP)
 *
 * Usage: pass OpenAPI spec file directly — Fabrikt parses it independently.
 */
class FabriktEmitter : ProtocolEmitter {
    override val protocol = "fabrikt"

    override fun emit(spec: ParsedSpec, pkg: String, outputDir: File) {
        // Fabrikt needs the raw OpenAPI file, not our ParsedSpec.
        // If the spec has a sourceFile, use it; otherwise skip.
        val specFile = spec.sourceFile ?: return

        val fabriktDir = File(outputDir, "fabrikt")
        fabriktDir.mkdirs()

        try {
            generateWithFabrikt(specFile, pkg, fabriktDir)
        } catch (e: Exception) {
            // Fabrikt not available at runtime → write stub note
            File(fabriktDir, "README.txt").writeText(
                """
                    Fabrikt generation skipped: ${e.message}

                    To generate Fabrikt models manually:
                      fabrikt --api-file ${specFile.absolutePath} \
                        --output-directory ${fabriktDir.absolutePath} \
                        --base-package $pkg \
                        --targets KOTLIN_SERIALIZATION \
                        --targets HTTP_MODELS \
                        --targets CLIENT \
                        --generation-type SEALED_INTERFACES_FOR_ONE_OF
                """.trimIndent()
            )
        }
    }

    private fun generateWithFabrikt(specFile: File, pkg: String, outputDir: File) {
        // Use Fabrikt's CodeGenerator programmatically
        val codeGenClass = Class.forName("com.cjbooms.fabrikt.cli.CodeGenerator")
        val codeGenConfigClass = Class.forName("com.cjbooms.fabrikt.cli.CodeGenConfig")

        // Build config
        val configBuilder = codeGenConfigClass.getDeclaredMethod("builder").invoke(null)
        val builderClass = configBuilder.javaClass

        builderClass.getDeclaredMethod("apiFile", Path::class.java)
            .invoke(configBuilder, specFile.toPath())
        builderClass.getDeclaredMethod("basePackage", String::class.java)
            .invoke(configBuilder, pkg)
        builderClass.getDeclaredMethod("outputDirectory", Path::class.java)
            .invoke(configBuilder, outputDir.toPath())

        val config = builderClass.getDeclaredMethod("build").invoke(configBuilder)

        // Generate
        val generator = codeGenClass.getDeclaredConstructor(codeGenConfigClass).newInstance(config)
        codeGenClass.getDeclaredMethod("generate").invoke(generator)
    }

    companion object {
        /**
         * Check if Fabrikt is available on the classpath.
         */
        fun isAvailable(): Boolean = try {
            Class.forName("com.cjbooms.fabrikt.cli.CodeGenerator")
            true
        } catch (_: ClassNotFoundException) { false }

        /**
         * Generate models only (no client/controller) via CLI args.
         * Useful for standalone invocation.
         */
        fun generateModels(
            apiFile: File,
            outputDir: File,
            basePackage: String,
            sealedInterfaces: Boolean = true,
        ) {
            val args = mutableListOf(
                "--api-file", apiFile.absolutePath,
                "--output-directory", outputDir.absolutePath,
                "--base-package", basePackage,
                "--targets", "HTTP_MODELS",
            )
            if (sealedInterfaces) {
                args += listOf("--generation-type", "SEALED_INTERFACES_FOR_ONE_OF")
            }

            // Invoke Fabrikt CLI main
            val mainClass = Class.forName("com.cjbooms.fabrikt.cli.MainKt")
            mainClass.getDeclaredMethod("main", Array<String>::class.java)
                .invoke(null, args.toTypedArray())
        }
    }
}
