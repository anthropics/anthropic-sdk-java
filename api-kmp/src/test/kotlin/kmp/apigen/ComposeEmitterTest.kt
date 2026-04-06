package kmp.apigen

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.assertj.core.api.Assertions.assertThat
import java.io.File
import java.nio.file.Path

class ComposeEmitterTest {

    private fun prop(type: String, nullable: Boolean = false, desc: String? = null, ref: String? = null) =
        PropertyInfo(name = "", type = type, format = null, nullable = nullable, description = desc, ref = ref)

    @Test
    fun `generates Form + List + Detail + MasterDetail for object schema`(@TempDir tempDir: Path) {
        val spec = ParsedSpec(
            schemas = mapOf(
                "Pet" to ParsedSchema.Object(
                    properties = mapOf(
                        "id" to prop("integer", desc = "Pet ID"),
                        "name" to prop("string", desc = "Pet name"),
                        "status" to prop("string", nullable = true),
                        "owner_id" to prop("integer", nullable = true, ref = "Owner"),
                    ),
                    required = setOf("id", "name"),
                    description = "A pet in the store",
                ),
            ),
            paths = emptyMap(),
            securitySchemes = emptyMap(),
            info = ParsedInfo("Petstore", "1.0"),
        )
        val output = tempDir.toFile()
        ComposeEmitter().emit(spec, "io.petstore", output)

        val uiFile = File(output, "io/petstore/ui/PetUI.kt")
        assertThat(uiFile).exists()
        val code = uiFile.readText()
        assertThat(code).contains("fun PetForm(")
        assertThat(code).contains("@Composable")
        assertThat(code).contains("fun PetList(")
        assertThat(code).contains("fun PetDetail(")
        assertThat(code).contains("fun PetMasterDetail(")
    }

    @Test
    fun `skips schemas with fewer than 2 properties`(@TempDir tempDir: Path) {
        val spec = ParsedSpec(
            schemas = mapOf(
                "Tag" to ParsedSchema.Object(
                    properties = mapOf("name" to prop("string")),
                    required = setOf("name"),
                    description = null,
                ),
            ),
            paths = emptyMap(),
            securitySchemes = emptyMap(),
        )
        val output = tempDir.toFile()
        ComposeEmitter().emit(spec, "io.test", output)
        assertThat(File(output, "io/test/ui")).doesNotExist()
    }

    @Test
    fun `skips non-object schemas`(@TempDir tempDir: Path) {
        val spec = ParsedSpec(
            schemas = mapOf("Status" to ParsedSchema.Enum(values = listOf("active", "inactive"), description = null)),
            paths = emptyMap(),
            securitySchemes = emptyMap(),
        )
        val output = tempDir.toFile()
        ComposeEmitter().emit(spec, "io.test", output)
        assertThat(File(output, "io/test/ui")).doesNotExist()
    }
}
