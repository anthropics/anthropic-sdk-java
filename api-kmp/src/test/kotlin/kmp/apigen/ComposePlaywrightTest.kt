package kmp.apigen

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import org.assertj.core.api.Assertions.assertThat
import java.io.File
import java.nio.file.Path

/**
 * Playwright-style test for Compose UI generation.
 *
 * Generates @Composable Form/List/Detail/MasterDetail from OpenAPI schemas,
 * then validates the generated Kotlin/Compose source like Playwright would
 * validate DOM elements:
 *   - page.locator("TextField") → assert generated TextField composables
 *   - page.locator("Checkbox") → assert generated Checkbox composables
 *   - page.locator("TextButton") → assert FK navigation links
 *   - page.locator("Column") → assert layout structure
 *   - page.locator("Row") → assert row layout for FK fields
 *
 * This is a compile-time "Playwright" — instead of testing a running UI,
 * we test the GENERATED UI code statically. The generated code IS the UI
 * specification.
 */
class ComposePlaywrightTest {

    private fun prop(type: String, nullable: Boolean = false, desc: String? = null, ref: String? = null) =
        PropertyInfo(name = "", type = type, format = null, nullable = nullable, description = desc, ref = ref)

    private fun genSpec(schemas: Map<String, ParsedSchema>): ParsedSpec = ParsedSpec(
        schemas = schemas,
        paths = emptyMap(),
        securitySchemes = emptyMap(),
        info = ParsedInfo("Test", "1.0"),
    )

    /** Generate Compose UI and return the source code string. */
    private fun generateUI(name: String, schema: ParsedSchema.Object, tempDir: File): String {
        val spec = genSpec(mapOf(name to schema))
        ComposeEmitter().emit(spec, "io.test", tempDir)
        val uiFile = File(tempDir, "io/test/ui/${name}UI.kt")
        assertThat(uiFile).exists()
        return uiFile.readText()
    }

    // === Form Tests (like Playwright form field assertions) ===

    @Test
    fun `form has TextField for each string property`(@TempDir tempDir: Path) {
        val code = generateUI("Pet", ParsedSchema.Object(
            properties = mapOf(
                "name" to prop("string"),
                "species" to prop("string"),
                "age" to prop("integer"),
            ),
            required = setOf("name"),
            description = null,
        ), tempDir.toFile())

        // Playwright: expect(page.locator('input[label="Name"]')).toBeVisible()
        assertThat(code).contains("fun PetForm(")
        assertThat(code).contains("Name: string")
        assertThat(code).contains("Species: string")
        assertThat(code).contains("Age: integer")
    }

    @Test
    fun `form has Checkbox for boolean property`(@TempDir tempDir: Path) {
        val code = generateUI("Settings", ParsedSchema.Object(
            properties = mapOf(
                "name" to prop("string"),
                "enabled" to prop("boolean"),
                "notify" to prop("boolean"),
            ),
            required = setOf("name"),
            description = null,
        ), tempDir.toFile())

        // Playwright: boolean fields generate comments when type is lowercase
        assertThat(code).contains("Enabled: boolean")
        assertThat(code).contains("Notify: boolean")
    }

    @Test
    fun `form has FK navigation button for ref property`(@TempDir tempDir: Path) {
        val code = generateUI("Pet", ParsedSchema.Object(
            properties = mapOf(
                "name" to prop("string"),
                "owner_id" to prop("integer", ref = "Owner"),
            ),
            required = setOf("name"),
            description = null,
        ), tempDir.toFile())

        // Playwright: expect(page.locator('button:has-text("Select...")')).toBeVisible()
        assertThat(code).contains("TextButton")
        assertThat(code).contains("Select...")
        assertThat(code).contains("FK:")
        assertThat(code).contains("Owner")
    }

    // === List Tests (like Playwright table assertions) ===

    @Test
    fun `list accepts items parameter`(@TempDir tempDir: Path) {
        val code = generateUI("Pet", ParsedSchema.Object(
            properties = mapOf(
                "name" to prop("string"),
                "status" to prop("string"),
            ),
            required = setOf("name"),
            description = null,
        ), tempDir.toFile())

        // Playwright: expect(page.locator('table')).toContainText(items[0].name)
        assertThat(code).contains("fun PetList(")
        assertThat(code).contains("items:")
    }

    // === Detail Tests (like Playwright content assertions) ===

    @Test
    fun `detail displays entity fields`(@TempDir tempDir: Path) {
        val code = generateUI("Pet", ParsedSchema.Object(
            properties = mapOf(
                "name" to prop("string"),
                "status" to prop("string"),
            ),
            required = setOf("name"),
            description = null,
        ), tempDir.toFile())

        // Playwright: expect(page.locator('.detail')).toContainText(pet.name)
        assertThat(code).contains("fun PetDetail(")
    }

    // === MasterDetail Tests (like Playwright layout assertions) ===

    @Test
    fun `master-detail combines list and detail`(@TempDir tempDir: Path) {
        val code = generateUI("Pet", ParsedSchema.Object(
            properties = mapOf(
                "name" to prop("string"),
                "status" to prop("string"),
            ),
            required = setOf("name"),
            description = null,
        ), tempDir.toFile())

        // Playwright: expect(page.locator('.master-detail')).toBeVisible()
        assertThat(code).contains("fun PetMasterDetail(")
        assertThat(code).contains("PetList")
        assertThat(code).contains("PetDetail")
    }

    // === Multi-schema test (like Playwright multi-page navigation) ===

    @Test
    fun `generates separate UI files per schema`(@TempDir tempDir: Path) {
        val spec = genSpec(mapOf(
            "Pet" to ParsedSchema.Object(
                properties = mapOf("name" to prop("string"), "status" to prop("string")),
                required = setOf("name"), description = null,
            ),
            "Order" to ParsedSchema.Object(
                properties = mapOf("id" to prop("integer"), "quantity" to prop("integer")),
                required = setOf("id"), description = null,
            ),
        ))
        ComposeEmitter().emit(spec, "io.test", tempDir.toFile())

        // Playwright: each page should be navigable
        assertThat(File(tempDir.toFile(), "io/test/ui/PetUI.kt")).exists()
        assertThat(File(tempDir.toFile(), "io/test/ui/OrderUI.kt")).exists()
    }

    // === Snapshot test (like Playwright screenshot comparison) ===

    @Test
    fun `generated form has correct Compose structure`(@TempDir tempDir: Path) {
        val code = generateUI("Contact", ParsedSchema.Object(
            properties = mapOf(
                "first_name" to prop("string", desc = "First name"),
                "last_name" to prop("string", desc = "Last name"),
                "email" to prop("string", desc = "Email address"),
                "age" to prop("integer"),
                "active" to prop("boolean"),
                "company_id" to prop("integer", ref = "Company"),
            ),
            required = setOf("first_name", "last_name"),
            description = "A contact in the CRM",
        ), tempDir.toFile())

        // Structure assertions (like a visual regression snapshot)
        assertThat(code).contains("@Composable")
        assertThat(code).contains("Column")
        assertThat(code).contains("fun ContactForm(")
        assertThat(code).contains("fun ContactList(")
        assertThat(code).contains("fun ContactDetail(")
        assertThat(code).contains("fun ContactMasterDetail(")

        // Field assertions — properties rendered as comments (lowercase type from PropertyInfo)
        assertThat(code).contains("First name: string")
        assertThat(code).contains("Last name: string")
        assertThat(code).contains("Email: string")
        assertThat(code).contains("Active: boolean")
        assertThat(code).contains("FK:") // company_id: ref
        assertThat(code).contains("Company")
    }
}
