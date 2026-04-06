package kotlinx.kmp.util.core

import kotlinx.kmp.util.core.http.*
import kotlinx.kmp.util.core.json.*
import kotlinx.kmp.util.core.mcp.*
import kotlinx.kmp.util.core.errors.*
import kotlinx.kmp.util.optional.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.test.assertFails

/**
 * End-to-end test exercising ALL KMP infrastructure on the current platform.
 *
 * This test runs as commonTest — meaning it executes on JVM, JS, and Native.
 * Each test verifies a different subsystem works correctly on the platform.
 */
class PlatformE2eTest {

    @Serializable
    data class TestModel(
        @SerialName("first_name") val firstName: String,
        @SerialName("last_name") val lastName: String,
        val age: Int = 0,
    )

    // --- JSON serialization (KotlinxApiJsonBackend) ---

    @Test
    fun json_roundTrip() {
        val backend = KotlinxApiJsonBackend()
        val model = TestModel("Alice", "Smith", 30)
        val json = backend.encodeToString(model, TestModel.serializer())
        val decoded = backend.decodeFromString(json, TestModel.serializer())
        assertEquals(model, decoded)
    }

    // --- ContentFormat: JSON + CBOR + Protobuf + MsgPack ---

    @Test
    fun contentFormat_json_roundTrip() {
        val bytes = ContentFormat.JSON.encodeToByteArray(TestModel.serializer(), TestModel("B", "C"))
        val decoded = ContentFormat.JSON.decodeFromByteArray(TestModel.serializer(), bytes)
        assertEquals("B", decoded.firstName)
    }

    @Test
    fun contentFormat_cbor_roundTrip() {
        val original = TestModel("X", "Y", 99)
        val bytes = ContentFormat.CBOR.encodeToByteArray(TestModel.serializer(), original)
        val decoded = ContentFormat.CBOR.decodeFromByteArray(TestModel.serializer(), bytes)
        assertEquals(original, decoded)
    }

    @Test
    fun contentFormat_protobuf_roundTrip() {
        val original = TestModel("P", "Q", 42)
        val bytes = ContentFormat.PROTOBUF.encodeToByteArray(TestModel.serializer(), original)
        val decoded = ContentFormat.PROTOBUF.decodeFromByteArray(TestModel.serializer(), bytes)
        assertEquals(original, decoded)
    }

    @Test
    fun contentFormat_msgpack_roundTrip() {
        val original = TestModel("M", "N", 7)
        val bytes = ContentFormat.MSGPACK.encodeToByteArray(TestModel.serializer(), original)
        val decoded = ContentFormat.MSGPACK.decodeFromByteArray(TestModel.serializer(), bytes)
        assertEquals(original, decoded)
    }

    // --- JsonValue ↔ JsonElement bridge ---

    @Test
    fun jsonValue_toElement_roundTrip() {
        val value = JsonObject.of(mapOf(
            "name" to JsonString.of("test"),
            "count" to JsonNumber.of(5),
        ))
        val element = toJsonElement(value)
        val back = fromJsonElement(element)
        assertTrue(back is JsonObject)
        assertEquals("test", back.values["name"]?.asString())
    }

    // --- MCP tool types ---

    @Test
    fun mcp_toolDefinition_serializes() {
        val tool = toolDefinition(
            name = "get_weather",
            description = "Get weather",
            properties = mapOf("location" to "string"),
        )
        val json = Json.encodeToString(ToolDefinition.serializer(), tool)
        assertTrue(json.contains("get_weather"))
        assertTrue(json.contains("location"))
    }

    @Test
    fun mcp_textResult() {
        val result = textResult("hello")
        assertEquals(1, result.content.size)
        assertEquals("hello", (result.content[0] as ToolContent.Text).text)
    }

    // --- Optional (expect/actual) ---

    @Test
    fun optional_present() {
        val opt = optionalOf("hello")
        assertTrue(opt.isPresent())
        assertEquals("hello", opt.get())
    }

    @Test
    fun optional_empty() {
        val opt = emptyOptional<String>()
        assertTrue(!opt.isPresent())
    }

    // --- Error types ---

    @Test
    fun apiException_hierarchy() {
        val io = ApiIoException("network error")
        assertTrue(io is ApiException)
        assertTrue(io is Retryable)
    }

    @Test
    fun apiRetryableException_isRetryable() {
        val ex = ApiRetryableException("retry me")
        assertTrue(ex is Retryable)
    }

    // --- Utils ---

    @Test
    fun checkRequired_passes() {
        val result = checkRequired("field", "value")
        assertEquals("value", result)
    }

    @Test
    fun checkRequired_fails() {
        assertFails { checkRequired("field", null as String?) }
    }

    @Test
    fun toImmutable_list() {
        val list = mutableListOf(1, 2, 3).toImmutable()
        assertEquals(listOf(1, 2, 3), list)
    }

    @Test
    fun contentHash_consistent() {
        val h1 = contentHash("a", 1, true)
        val h2 = contentHash("a", 1, true)
        assertEquals(h1, h2)
    }

    // --- Platform time ---

    @Test
    fun currentTimeMillis_positive() {
        assertTrue(currentTimeMillis() > 0)
    }
}
