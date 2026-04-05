package kotlinx.kmp.util.core.component

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * PatchEvent — the universal mutation event.
 *
 * Every mutation (add/replace/remove/patch) produces a PatchEvent that:
 * 1. Returns to caller as the response
 * 2. Broadcasts via SSE/WS to all Flow subscribers
 * 3. Contains JSON Patch ops so subscribers apply changes locally
 * 4. Persists as audit record
 *
 * The event IS the patch. The patch IS the event.
 */
@Serializable
data class PatchEvent<T>(
    /** Mutation type: add, replace, remove, patch */
    val op: String,
    /** Entity identifier */
    val entityId: String,
    /** State before mutation (null for add) */
    val before: T? = null,
    /** State after mutation (null for remove) */
    val after: T? = null,
    /** JSON Patch operations (RFC 6902) — for local apply by subscribers */
    val patch: List<PatchOperation>? = null,
    /** ISO 8601 timestamp */
    val timestamp: String = kotlinx.datetime.Clock.System.now().toString(),
)

/**
 * JSON Patch operation (RFC 6902).
 */
@Serializable
data class PatchOperation(
    /** Operation: add, remove, replace, move, copy, test */
    val op: String,
    /** JSON Pointer path (e.g. "/name", "/category/id") */
    val path: String,
    /** Value for add/replace/test */
    val value: JsonElement? = null,
    /** Source path for move/copy */
    val from: String? = null,
)
