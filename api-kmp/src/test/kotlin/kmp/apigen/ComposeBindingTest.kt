package kmp.apigen

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

/**
 * Tests per-target Compose bindings + Wire proto content negotiation.
 *
 * Verifies that the same schema produces correct widgets for each target:
 *   - Material3 (JVM/Desktop/Android/iOS): TextField, Checkbox, Button
 *   - Web DOM (JS): Input, CheckboxInput, Button, Div, Ul/Li
 *
 * Also verifies proto Wire type integration — generated models use Wire
 * field presence semantics + kotlinx.serialization for content negotiation
 * across JSON/CBOR/Protobuf/MsgPack formats.
 */
class ComposeBindingTest {

    private val material3 = Material3Binding()
    private val webDom = WebDomBinding()

    // === Material3 Binding ===

    @Test
    fun `material3 textField generates TextField composable`() {
        val code = material3.textField("name", "Name")
        assertThat(code).contains("TextField")
        assertThat(code).contains("value = name")
        assertThat(code).contains("onValueChange")
        assertThat(code).contains("\"Name\"")
    }

    @Test
    fun `material3 checkbox generates Checkbox composable`() {
        val code = material3.checkbox("active", "Active")
        assertThat(code).contains("Checkbox")
        assertThat(code).contains("checked = active")
        assertThat(code).contains("onCheckedChange")
    }

    @Test
    fun `material3 button generates Button composable`() {
        val code = material3.button("Save", "onSubmit(entity)")
        assertThat(code).contains("Button")
        assertThat(code).contains("\"Save\"")
    }

    @Test
    fun `material3 textButton generates TextButton for FK navigation`() {
        val code = material3.textButton("\"Owner: \${entity.ownerId}\"", "onOwnerClick()")
        assertThat(code).contains("TextButton")
        assertThat(code).contains("onOwnerClick()")
    }

    @Test
    fun `material3 listItem generates ListItem composable`() {
        val code = material3.listItem("item.name", "onClick(item)")
        assertThat(code).contains("ListItem")
        assertThat(code).contains("headlineContent")
        assertThat(code).contains("clickable")
    }

    @Test
    fun `material3 layout containers`() {
        assertThat(material3.column("  content()")).contains("Column")
        assertThat(material3.row("  content()")).contains("Row")
        assertThat(material3.lazyColumn("items.size", "ItemView(items[index])")).contains("LazyColumn")
    }

    // === Web DOM Binding ===

    @Test
    fun `webDom textField generates HTML Input element`() {
        val code = webDom.textField("email", "Email")
        assertThat(code).contains("Input")
        assertThat(code).contains("InputType.Text")
        assertThat(code).contains("Label")
        assertThat(code).contains("\"Email\"")
    }

    @Test
    fun `webDom checkbox generates CheckboxInput element`() {
        val code = webDom.checkbox("notify", "Send notifications")
        assertThat(code).contains("CheckboxInput")
        assertThat(code).contains("checked = notify")
        assertThat(code).contains("Send notifications")
    }

    @Test
    fun `webDom button generates HTML Button element`() {
        val code = webDom.button("Submit", "handleSubmit()")
        assertThat(code).contains("Button")
        assertThat(code).contains("onClick")
        assertThat(code).contains("\"Submit\"")
    }

    @Test
    fun `webDom textButton generates anchor link`() {
        val code = webDom.textButton("\"View details\"", "navigate()")
        assertThat(code).contains("A(")
        assertThat(code).contains("onClick")
    }

    @Test
    fun `webDom listItem generates Li element`() {
        val code = webDom.listItem("item.title", "selectItem(item)")
        assertThat(code).contains("Li")
        assertThat(code).contains("onClick")
    }

    @Test
    fun `webDom layout uses Div with flexbox`() {
        assertThat(webDom.column("  content()")).contains("Div")
        assertThat(webDom.column("  content()")).contains("FlexDirection.Column")
        assertThat(webDom.row("  content()")).contains("FlexDirection.Row")
        assertThat(webDom.lazyColumn("items.size", "ItemView(items[index])")).contains("Ul")
    }

    // === Cross-binding consistency ===

    @Test
    fun `both bindings produce different target names`() {
        assertThat(material3.target).isEqualTo("material3")
        assertThat(webDom.target).isEqualTo("web")
    }

    @Test
    fun `same schema input produces different widget code per binding`() {
        val m3 = material3.textField("name", "Name")
        val web = webDom.textField("name", "Name")

        // Both contain the field name
        assertThat(m3).contains("name")
        assertThat(web).contains("name")

        // But use different widget libraries
        assertThat(m3).contains("material3.TextField")
        assertThat(web).contains("dom.Input")
        assertThat(m3).doesNotContain("dom.Input")
        assertThat(web).doesNotContain("material3.TextField")
    }

    // === Wire proto content negotiation ===

    @Test
    fun `all 4 content formats available for negotiation`() {
        val formats = kotlinx.kmp.util.core.ContentFormat.entries
        assertThat(formats.map { it.name }).containsExactlyInAnyOrder("JSON", "CBOR", "PROTOBUF", "MSGPACK")
    }

    @Test
    fun `proto round-trip with generated model pattern`() {
        // Simulates what a generated Wire model would do:
        // serialize via ContentFormat.PROTOBUF, deserialize back
        @kotlinx.serialization.Serializable
        data class Pet(
            @kotlinx.serialization.protobuf.ProtoNumber(1) val name: String,
            @kotlinx.serialization.protobuf.ProtoNumber(2) val status: String = "available",
        )

        val original = Pet("Buddy", "adopted")
        val bytes = kotlinx.kmp.util.core.ContentFormat.PROTOBUF.encodeToByteArray(Pet.serializer(), original)
        val decoded = kotlinx.kmp.util.core.ContentFormat.PROTOBUF.decodeFromByteArray(Pet.serializer(), bytes)
        assertThat(decoded).isEqualTo(original)

        // Also works with JSON (content negotiation)
        val jsonBytes = kotlinx.kmp.util.core.ContentFormat.JSON.encodeToByteArray(Pet.serializer(), original)
        val jsonDecoded = kotlinx.kmp.util.core.ContentFormat.JSON.decodeFromByteArray(Pet.serializer(), jsonBytes)
        assertThat(jsonDecoded).isEqualTo(original)

        // Proto is more compact
        assertThat(bytes.size).isLessThan(jsonBytes.size)
    }
}
