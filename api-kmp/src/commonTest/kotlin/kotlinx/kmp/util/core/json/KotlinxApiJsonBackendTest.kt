package kotlinx.kmp.util.core.json

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class KotlinxApiJsonBackendTest {

    @Serializable
    data class SimpleModel(
        @SerialName("first_name") val firstName: String,
        @SerialName("last_name") val lastName: String,
        val age: Int = 0,
    )

    @Serializable
    data class NestedModel(
        val id: String,
        val items: List<SimpleModel> = emptyList(),
    )

    private val backend = KotlinxApiJsonBackend()

    @Test
    fun encodeSimple() {
        val model = SimpleModel("John", "Doe", 30)
        val json = backend.encodeToString(model, SimpleModel.serializer())
        assert(json.contains("\"first_name\":\"John\"")) { "Expected first_name in $json" }
        assert(json.contains("\"last_name\":\"Doe\"")) { "Expected last_name in $json" }
        assert(json.contains("\"age\":30")) { "Expected age in $json" }
    }

    @Test
    fun decodeSimple() {
        val json = """{"first_name":"Jane","last_name":"Smith","age":25}"""
        val model = backend.decodeFromString(json, SimpleModel.serializer())
        assertEquals("Jane", model.firstName)
        assertEquals("Smith", model.lastName)
        assertEquals(25, model.age)
    }

    @Test
    fun roundTrip() {
        val original = NestedModel("123", listOf(SimpleModel("A", "B", 1), SimpleModel("C", "D", 2)))
        val json = backend.encodeToString(original, NestedModel.serializer())
        val decoded = backend.decodeFromString(json, NestedModel.serializer())
        assertEquals(original, decoded)
    }

    @Test
    fun decodeIgnoresUnknownKeys() {
        val json = """{"first_name":"X","last_name":"Y","unknown_field":"ignored"}"""
        val model = backend.decodeFromString(json, SimpleModel.serializer())
        assertEquals("X", model.firstName)
    }

    @Test
    fun decodeCoercesDefaults() {
        val json = """{"first_name":"X","last_name":"Y"}"""
        val model = backend.decodeFromString(json, SimpleModel.serializer())
        assertEquals(0, model.age)
    }

    @Test
    fun parseToJsonElement() {
        val element = backend.parseToJsonElement("""{"key":"value","num":42}""")
        assertNotNull(element)
    }

    @Test
    fun encodePretty() {
        val model = SimpleModel("A", "B")
        val pretty = backend.encodePretty(model)
        assert(pretty.contains("\n")) { "Expected newlines in pretty output: $pretty" }
    }

    @Test
    fun decodeViaTypeDescriptor() {
        val json = """{"first_name":"Z","last_name":"W"}"""
        val model: SimpleModel = backend.decodeFromString(json, SimpleModel.serializer() as Any)
        assertEquals("Z", model.firstName)
    }
}
