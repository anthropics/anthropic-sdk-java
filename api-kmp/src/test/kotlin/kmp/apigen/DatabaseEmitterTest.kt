package kmp.apigen

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.assertj.core.api.Assertions.assertThat
import java.io.File
import java.nio.file.Path

class DatabaseEmitterTest {

    private fun prop(type: String, nullable: Boolean = false, ref: String? = null) =
        PropertyInfo(name = "", type = type, format = null, nullable = nullable, description = null, ref = ref)

    @Test
    fun `generates Exposed table + SQLDelight sq for object schema`(@TempDir tempDir: Path) {
        val spec = ParsedSpec(
            schemas = mapOf(
                "Pet" to ParsedSchema.Object(
                    properties = mapOf(
                        "id" to prop("integer"),
                        "name" to prop("string"),
                        "status" to prop("string", nullable = true),
                        "tags" to prop("array", nullable = true),
                        "category_id" to prop("integer", nullable = true, ref = "Category"),
                    ),
                    required = setOf("id", "name"),
                    description = "A pet in the store",
                ),
                "Category" to ParsedSchema.Object(
                    properties = mapOf(
                        "id" to prop("integer"),
                        "name" to prop("string"),
                    ),
                    required = setOf("id", "name"),
                    description = null,
                ),
            ),
            paths = emptyMap(),
            securitySchemes = emptyMap(),
            info = ParsedInfo("Petstore", "1.0"),
        )
        // Use a subdirectory so ../sqldelight resolves within tempDir
        val output = File(tempDir.toFile(), "gen")
        output.mkdirs()
        DatabaseEmitter().emit(spec, "io.petstore", output)

        // Exposed table (Kotlin)
        val exposedFile = File(output, "io/petstore/db/Tables.kt")
        assertThat(exposedFile).exists()
        val exposed = exposedFile.readText()
        assertThat(exposed).contains("Pets")
        assertThat(exposed).contains("id")
        assertThat(exposed).contains("name")

        // SQLDelight .sq — emitter writes to ../sqldelight relative to output
        // Walk temp dir tree to find .sq files regardless of exact path resolution
        val sqFiles = tempDir.toFile().walkTopDown().filter { it.extension == "sq" }.toList()
        assertThat(sqFiles).isNotEmpty()
        val petSq = sqFiles.find { it.name.startsWith("pet") }
        assertThat(petSq).isNotNull()
        val sq = petSq!!.readText()
        assertThat(sq).contains("CREATE TABLE")
        assertThat(sq).contains("id")
    }

    @Test
    fun `generates jsonb columns for RFC types`(@TempDir tempDir: Path) {
        val spec = ParsedSpec(
            schemas = mapOf(
                "Contact" to ParsedSchema.Object(
                    properties = mapOf(
                        "id" to prop("integer"),
                        "vcard" to prop("object", nullable = true, ref = "VCardContact"),
                    ),
                    required = setOf("id"),
                    description = null,
                ),
            ),
            paths = emptyMap(),
            securitySchemes = emptyMap(),
        )
        val output = tempDir.toFile()
        DatabaseEmitter().emit(spec, "io.test", output)

        val exposedFile = File(output, "io/test/db/Tables.kt")
        assertThat(exposedFile).exists()
        val exposed = exposedFile.readText()
        assertThat(exposed).contains("jsonb")
    }

    @Test
    fun `skips non-object schemas`(@TempDir tempDir: Path) {
        val spec = ParsedSpec(
            schemas = mapOf("Status" to ParsedSchema.Enum(values = listOf("a", "b"), description = null)),
            paths = emptyMap(),
            securitySchemes = emptyMap(),
        )
        val output = tempDir.toFile()
        DatabaseEmitter().emit(spec, "io.test", output)
        val tablesFile = File(output, "io/test/db/Tables.kt")
        if (tablesFile.exists()) {
            assertThat(tablesFile.readText()).doesNotContain("object Status")
        }
    }
}
