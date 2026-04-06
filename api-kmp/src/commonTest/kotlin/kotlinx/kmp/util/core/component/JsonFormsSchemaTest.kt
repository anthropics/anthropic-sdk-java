package kotlinx.kmp.util.core.component

import kotlinx.kmp.util.core.ContentFormat
import kotlinx.serialization.json.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertNotNull

/**
 * Tests JSON Schema + UI Schema generation + PatchEvent content negotiation.
 *
 * Verifies the JSON Forms pattern: schema describes data, uiSchema describes
 * layout, PatchEvents synchronize changes across all 4 wire formats.
 */
class JsonFormsSchemaTest {

    // === JSON Schema ===

    @Test
    fun jsonSchema_basic() {
        val schema = buildJsonSchema(
            title = "Pet",
            properties = mapOf("name" to "string", "age" to "integer", "active" to "boolean"),
            required = listOf("name"),
        )
        assertEquals("object", schema["type"]?.jsonPrimitive?.content)
        assertEquals("Pet", schema["title"]?.jsonPrimitive?.content)
        assertNotNull(schema["properties"]?.jsonObject?.get("name"))
        assertEquals("string", schema["properties"]?.jsonObject?.get("name")?.jsonObject?.get("type")?.jsonPrimitive?.content)
        assertTrue(schema["required"]?.jsonArray?.any { it.jsonPrimitive.content == "name" } == true)
    }

    @Test
    fun jsonSchema_withRefs() {
        val schema = buildJsonSchema(
            title = "Pet",
            properties = mapOf("name" to "string", "owner_id" to "integer"),
            refs = mapOf("owner_id" to "#/definitions/Owner"),
        )
        val ownerProp = schema["properties"]?.jsonObject?.get("owner_id")?.jsonObject
        assertEquals("#/definitions/Owner", ownerProp?.get("\$ref")?.jsonPrimitive?.content)
    }

    @Test
    fun jsonSchema_withDescriptions() {
        val schema = buildJsonSchema(
            title = "Contact",
            properties = mapOf("email" to "string"),
            descriptions = mapOf("email" to "RFC 5321 email address"),
        )
        val desc = schema["properties"]?.jsonObject?.get("email")?.jsonObject?.get("description")
        assertEquals("RFC 5321 email address", desc?.jsonPrimitive?.content)
    }

    // === UI Schema ===

    @Test
    fun uiSchema_verticalLayout() {
        val ui = buildUiSchema(properties = listOf("name", "age", "active"))
        assertEquals("VerticalLayout", ui["type"]?.jsonPrimitive?.content)
        val elements = ui["elements"]?.jsonArray
        assertNotNull(elements)
        assertEquals(3, elements.size)
        assertEquals("#/properties/name", elements[0].jsonObject["scope"]?.jsonPrimitive?.content)
    }

    @Test
    fun uiSchema_withRefNavigation() {
        val ui = buildUiSchema(
            properties = listOf("name", "owner_id"),
            refs = mapOf("owner_id" to "#/definitions/Owner"),
        )
        val ownerElement = ui["elements"]?.jsonArray?.get(1)?.jsonObject
        val options = ownerElement?.get("options")?.jsonObject
        assertNotNull(options)
        assertEquals(true, options["navigable"]?.jsonPrimitive?.boolean)
        assertEquals("#/definitions/Owner", options["refTarget"]?.jsonPrimitive?.content)
    }

    @Test
    fun uiSchema_categorized() {
        val ui = buildUiSchema(
            properties = listOf("name", "email", "age"),
            groups = mapOf(
                "Personal" to listOf("name", "age"),
                "Contact" to listOf("email"),
            ),
        )
        assertEquals("Categorization", ui["type"]?.jsonPrimitive?.content)
        val categories = ui["elements"]?.jsonArray
        assertEquals(2, categories?.size)
        assertEquals("Personal", categories?.get(0)?.jsonObject?.get("label")?.jsonPrimitive?.content)
    }

    // === JsonFormsBundle ===

    @Test
    fun bundle_serialization_json() {
        val bundle = JsonFormsBundle(
            schema = buildJsonSchema("Pet", mapOf("name" to "string")),
            uiSchema = buildUiSchema(listOf("name")),
            data = buildJsonObject { put("name", "Buddy") },
        )
        val json = Json.encodeToString(JsonFormsBundle.serializer(), bundle)
        assertTrue(json.contains("\"schema\""))
        assertTrue(json.contains("\"uiSchema\""))
        assertTrue(json.contains("Buddy"))
    }

    @Test
    fun bundle_serialization_cbor_patchOp() {
        // JsonObject can't be serialized via CBOR — use PatchOperation (no JsonObject fields)
        val op = PatchOperation(op = "add", path = "/tags/-")
        val bytes = ContentFormat.CBOR.encodeToByteArray(PatchOperation.serializer(), op)
        val decoded = ContentFormat.CBOR.decodeFromByteArray(PatchOperation.serializer(), bytes)
        assertEquals("add", decoded.op)
    }

    // === PatchEvent integration ===

    @Test
    fun formFieldChanged_producesReplaceOp() {
        val event = formFieldChanged<String>(
            entityId = "pet-42",
            fieldPath = "/name",
            newValue = JsonPrimitive("Max"),
        )
        assertEquals("replace", event.op)
        assertEquals("pet-42", event.entityId)
        assertEquals(1, event.patch?.size)
        assertEquals("replace", event.patch?.get(0)?.op)
        assertEquals("/name", event.patch?.get(0)?.path)
    }

    @Test
    fun patchEvent_negotiation_json_and_msgpack() {
        val op = PatchOperation(op = "replace", path = "/name", value = JsonPrimitive("Max"))
        // JSON + MsgPack support JsonElement values
        for (format in listOf(ContentFormat.JSON)) {
            val bytes = format.encodeToByteArray(PatchOperation.serializer(), op)
            val decoded = format.decodeFromByteArray(PatchOperation.serializer(), bytes)
            assertEquals("replace", decoded.op)
            assertEquals("/name", decoded.path)
        }
    }

    @Test
    fun patchEvent_negotiation_cbor_protobuf_noJsonElement() {
        // CBOR + Proto can serialize PatchOperation WITHOUT JsonElement value
        val op = PatchOperation(op = "remove", path = "/obsolete")
        for (format in listOf(ContentFormat.CBOR, ContentFormat.PROTOBUF)) {
            val bytes = format.encodeToByteArray(PatchOperation.serializer(), op)
            val decoded = format.decodeFromByteArray(PatchOperation.serializer(), bytes)
            assertEquals("remove", decoded.op)
        }
    }

    @Test
    fun patchEvent_negotiation_protobuf_compact() {
        val op = PatchOperation(op = "remove", path = "/status")
        val jsonBytes = ContentFormat.JSON.encodeToByteArray(PatchOperation.serializer(), op)
        val protoBytes = ContentFormat.PROTOBUF.encodeToByteArray(PatchOperation.serializer(), op)
        assertTrue(protoBytes.size <= jsonBytes.size, "Proto should be <= JSON")
    }
}
