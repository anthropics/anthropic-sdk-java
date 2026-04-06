package kotlinx.kmp.util.core.component

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class PatchEventTest {

    @Test
    fun patchOperation_creation() {
        val op = PatchOperation(op = "replace", path = "/name", value = JsonPrimitive("Max"))
        assertEquals("replace", op.op)
        assertEquals("/name", op.path)
        assertNotNull(op.value)
    }

    @Test
    fun patchOperation_serialization() {
        val op = PatchOperation(op = "add", path = "/tags/-", value = JsonPrimitive("fluffy"))
        val json = kotlinx.serialization.json.Json.encodeToString(PatchOperation.serializer(), op)
        assertTrue(json.contains("\"op\":\"add\""))
        assertTrue(json.contains("/tags/-"))
        val decoded = kotlinx.serialization.json.Json.decodeFromString(PatchOperation.serializer(), json)
        assertEquals(op.op, decoded.op)
    }

    @Test
    fun patchOperation_move() {
        val op = PatchOperation(op = "move", path = "/new", from = "/old")
        assertEquals("move", op.op)
        assertEquals("/old", op.from)
    }

    @Test
    fun patchOperation_remove() {
        val op = PatchOperation(op = "remove", path = "/obsolete")
        assertEquals("remove", op.op)
    }
}
