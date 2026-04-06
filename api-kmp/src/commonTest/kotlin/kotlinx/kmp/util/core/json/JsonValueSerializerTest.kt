package kotlinx.kmp.util.core.json

import kotlinx.kmp.util.core.*
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class JsonValueSerializerTest {

    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun roundTripNull() {
        val value = JsonNull.of()
        val encoded = json.encodeToString(JsonValueSerializer, value)
        assertEquals("null", encoded)
        val decoded = json.decodeFromString(JsonValueSerializer, encoded)
        assertTrue(decoded is JsonNull)
    }

    @Test
    fun roundTripBoolean() {
        val value = JsonBoolean.of(true)
        val encoded = json.encodeToString(JsonValueSerializer, value)
        assertEquals("true", encoded)
        val decoded = json.decodeFromString(JsonValueSerializer, encoded)
        assertTrue(decoded is JsonBoolean)
        assertEquals(true, (decoded as JsonBoolean).value)
    }

    @Test
    fun roundTripNumber() {
        val value = JsonNumber.of(42)
        val encoded = json.encodeToString(JsonValueSerializer, value)
        assertEquals("42", encoded)
        val decoded = json.decodeFromString(JsonValueSerializer, encoded)
        assertTrue(decoded is JsonNumber)
    }

    @Test
    fun roundTripString() {
        val value = JsonString.of("hello")
        val encoded = json.encodeToString(JsonValueSerializer, value)
        assertEquals("\"hello\"", encoded)
        val decoded = json.decodeFromString(JsonValueSerializer, encoded)
        assertTrue(decoded is JsonString)
        assertEquals("hello", (decoded as JsonString).value)
    }

    @Test
    fun roundTripArray() {
        val value = JsonArray.of(listOf(JsonNumber.of(1), JsonString.of("two"), JsonBoolean.of(false)))
        val encoded = json.encodeToString(JsonValueSerializer, value)
        assertEquals("[1,\"two\",false]", encoded)
        val decoded = json.decodeFromString(JsonValueSerializer, encoded)
        assertTrue(decoded is JsonArray)
        assertEquals(3, (decoded as JsonArray).values.size)
    }

    @Test
    fun roundTripObject() {
        val value = JsonObject.of(mapOf(
            "name" to JsonString.of("test"),
            "count" to JsonNumber.of(5),
            "active" to JsonBoolean.of(true),
        ))
        val encoded = json.encodeToString(JsonValueSerializer, value)
        assertTrue(encoded.contains("\"name\":\"test\""))
        val decoded = json.decodeFromString(JsonValueSerializer, encoded)
        assertTrue(decoded is JsonObject)
        assertEquals("test", (decoded as JsonObject).values["name"]?.asString())
    }

    @Test
    fun roundTripNested() {
        val value = JsonObject.of(mapOf(
            "items" to JsonArray.of(listOf(
                JsonObject.of(mapOf("id" to JsonNumber.of(1))),
                JsonObject.of(mapOf("id" to JsonNumber.of(2))),
            ))
        ))
        val encoded = json.encodeToString(JsonValueSerializer, value)
        val decoded = json.decodeFromString(JsonValueSerializer, encoded)
        assertTrue(decoded is JsonObject)
        val items = (decoded as JsonObject).values["items"]
        assertTrue(items is JsonArray)
        assertEquals(2, (items as JsonArray).values.size)
    }

    @Test
    fun fromJsonElement_primitiveTypes() {
        val boolElement = kotlinx.serialization.json.JsonPrimitive(true)
        val result = fromJsonElement(boolElement)
        assertTrue(result is JsonBoolean)
        assertEquals(true, (result as JsonBoolean).value)

        val numElement = kotlinx.serialization.json.JsonPrimitive(3.14)
        val numResult = fromJsonElement(numElement)
        assertTrue(numResult is JsonNumber)

        val strElement = kotlinx.serialization.json.JsonPrimitive("hello")
        val strResult = fromJsonElement(strElement)
        assertTrue(strResult is JsonString)
        assertEquals("hello", (strResult as JsonString).value)
    }
}
