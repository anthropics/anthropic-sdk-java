package kotlinx.kmp.util.core.component

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.*

/**
 * JSON Forms-compatible schema generation.
 *
 * Generates two schemas from an entity description:
 * 1. **JSON Schema** — describes the data structure (types, required, refs)
 * 2. **UI Schema** — describes the layout (VerticalLayout, HorizontalLayout,
 *    Control elements referencing JSON Schema properties via #/properties/X)
 *
 * These schemas are serializable via all 4 ContentFormats (JSON/CBOR/Proto/MsgPack)
 * and synchronize via [PatchEvent] for live collaborative editing.
 *
 * See: https://jsonforms.io/docs/uischema
 */
@Serializable
data class JsonFormsBundle(
    val schema: JsonObject,
    val uiSchema: JsonObject,
    val data: JsonObject = JsonObject(emptyMap()),
)

/**
 * Build a JSON Schema from property definitions.
 *
 * @param properties map of propName → type string ("string", "integer", "boolean", "array", "object")
 * @param required list of required property names
 * @param refs map of propName → $ref target (e.g. "owner_id" → "#/definitions/Owner")
 */
fun buildJsonSchema(
    title: String,
    properties: Map<String, String>,
    required: List<String> = emptyList(),
    refs: Map<String, String> = emptyMap(),
    descriptions: Map<String, String> = emptyMap(),
): JsonObject = buildJsonObject {
    put("type", JsonPrimitive("object"))
    put("title", JsonPrimitive(title))
    put("properties", buildJsonObject {
        properties.forEach { (name, type) ->
            put(name, buildJsonObject {
                if (name in refs) {
                    put("\$ref", JsonPrimitive(refs[name]))
                } else {
                    put("type", JsonPrimitive(type))
                }
                descriptions[name]?.let { put("description", JsonPrimitive(it)) }
            })
        }
    })
    if (required.isNotEmpty()) {
        put("required", JsonArray(required.map { JsonPrimitive(it) }))
    }
}

/**
 * Build a UI Schema (JSON Forms layout) from property names.
 *
 * Generates a VerticalLayout with Control elements for each property.
 * FK ($ref) properties get a special "refControl" type for navigation.
 */
fun buildUiSchema(
    properties: List<String>,
    refs: Map<String, String> = emptyMap(),
    groups: Map<String, List<String>> = emptyMap(),
): JsonObject = buildJsonObject {
    put("type", JsonPrimitive(
        if (groups.isNotEmpty()) "Categorization" else "VerticalLayout"
    ))
    if (groups.isNotEmpty()) {
        put("elements", buildJsonArray {
            groups.forEach { (groupName, groupProps) ->
                add(buildJsonObject {
                    put("type", JsonPrimitive("Category"))
                    put("label", JsonPrimitive(groupName))
                    put("elements", buildJsonArray {
                        groupProps.forEach { prop -> add(controlElement(prop, refs)) }
                    })
                })
            }
        })
    } else {
        put("elements", buildJsonArray {
            properties.forEach { prop -> add(controlElement(prop, refs)) }
        })
    }
}

private fun controlElement(prop: String, refs: Map<String, String>): JsonObject = buildJsonObject {
    put("type", JsonPrimitive(if (prop in refs) "Control" else "Control"))
    put("scope", JsonPrimitive("#/properties/$prop"))
    if (prop in refs) {
        put("options", buildJsonObject {
            put("refTarget", JsonPrimitive(refs[prop]!!))
            put("navigable", JsonPrimitive(true))
        })
    }
}

/**
 * Create a [PatchEvent] from a JSON Forms data change.
 *
 * When a form field changes, this produces a PatchEvent with a JSON Patch
 * operation that can be broadcast via SSE/WebSocket to all subscribers.
 */
fun <T> formFieldChanged(
    entityId: String,
    fieldPath: String,
    newValue: JsonElement,
    before: T? = null,
    after: T? = null,
): PatchEvent<T> = PatchEvent(
    op = "replace",
    entityId = entityId,
    before = before,
    after = after,
    patch = listOf(PatchOperation(
        op = "replace",
        path = fieldPath,
        value = newValue,
    )),
)
