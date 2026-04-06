package kotlinx.kmp.util.core.component

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.*

/**
 * JWT claims → menu UI mapping.
 *
 * Each JWT permission claim is a semicolon-delimited string:
 *   `object;action;category;pattern`
 *
 * Examples:
 *   `file;upload;img;png`       → File menu → Upload → Image → .png filter
 *   `file;upload;code;puml`     → File menu → Upload → Code → .puml filter
 *   `file;download;doc;pdf`     → File menu → Download → Document → .pdf
 *   `pet;create;*;*`           → Pet menu → Create (all categories/patterns)
 *   `message;send;text;*`      → Message menu → Send → Text
 *   `admin;*;*;*`              → Admin menu → all actions (superuser)
 *
 * The menu UI is generated from the set of permissions in the JWT token.
 * Wildcard `*` matches any value at that level.
 *
 * This maps directly to:
 * - OpenAPI operationId: `{object}_{action}` (e.g. `file_upload`)
 * - JSON Schema: `#/components/schemas/{Object}`
 * - UI Schema: category → JSON Forms Categorization
 * - Content negotiation: pattern → MIME type / file extension
 * - MCP tool: `{object}_{action}` tool name
 */

@Serializable
data class MenuPermission(
    /** Resource/entity (e.g. "file", "pet", "message", "admin") */
    val obj: String,
    /** Action verb (e.g. "upload", "download", "create", "send", "*") */
    val action: String,
    /** Category/subtype (e.g. "img", "code", "doc", "text", "*") */
    val category: String = "*",
    /** Pattern/extension (e.g. "png", "puml", "pdf", "*") */
    val pattern: String = "*",
) {
    /** Semicolon-delimited claim string */
    fun toClaim(): String = "$obj;$action;$category;$pattern"

    /** Check if this permission matches a request */
    fun matches(request: MenuPermission): Boolean =
        (obj == "*" || obj == request.obj) &&
        (action == "*" || action == request.action) &&
        (category == "*" || category == request.category) &&
        (pattern == "*" || pattern == request.pattern)

    /** OpenAPI operationId equivalent */
    val operationId: String get() = "${obj}_$action"

    /** MIME type hint from category + pattern */
    val mimeType: String? get() = MIME_MAP["$category/$pattern"]
        ?: CATEGORY_MIME[category]

    /** File extension filter */
    val fileExtension: String? get() = if (pattern != "*") ".$pattern" else null

    companion object {
        /** Parse "object;action;category;pattern" claim string */
        fun fromClaim(claim: String): MenuPermission {
            val parts = claim.split(";")
            return MenuPermission(
                obj = parts.getOrElse(0) { "*" },
                action = parts.getOrElse(1) { "*" },
                category = parts.getOrElse(2) { "*" },
                pattern = parts.getOrElse(3) { "*" },
            )
        }

        /** Parse all permission claims from JWT payload */
        fun fromJwtClaims(claims: JsonObject, claimKey: String = "permissions"): List<MenuPermission> {
            val perms = claims[claimKey] ?: return emptyList()
            return when (perms) {
                is JsonArray -> perms.map { fromClaim(it.jsonPrimitive.content) }
                is JsonPrimitive -> perms.content.split(",").map { fromClaim(it.trim()) }
                else -> emptyList()
            }
        }
    }
}

/** Category → MIME type mapping */
private val CATEGORY_MIME = mapOf(
    "img" to "image/*",
    "code" to "text/plain",
    "doc" to "application/octet-stream",
    "text" to "text/plain",
    "audio" to "audio/*",
    "video" to "video/*",
    "data" to "application/json",
    "archive" to "application/zip",
)

/** Category/pattern → specific MIME type */
private val MIME_MAP = mapOf(
    "img/png" to "image/png",
    "img/jpg" to "image/jpeg",
    "img/jpeg" to "image/jpeg",
    "img/gif" to "image/gif",
    "img/svg" to "image/svg+xml",
    "img/webp" to "image/webp",
    "code/puml" to "text/x-plantuml",
    "code/kt" to "text/x-kotlin",
    "code/java" to "text/x-java",
    "code/py" to "text/x-python",
    "code/js" to "text/javascript",
    "code/ts" to "text/typescript",
    "code/json" to "application/json",
    "code/yaml" to "application/x-yaml",
    "code/xml" to "application/xml",
    "code/sql" to "text/x-sql",
    "code/proto" to "text/x-protobuf",
    "doc/pdf" to "application/pdf",
    "doc/docx" to "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
    "doc/xlsx" to "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
    "doc/csv" to "text/csv",
    "doc/md" to "text/markdown",
    "audio/mp3" to "audio/mpeg",
    "audio/wav" to "audio/wav",
    "audio/ogg" to "audio/ogg",
    "video/mp4" to "video/mp4",
    "video/webm" to "video/webm",
    "data/json" to "application/json",
    "data/cbor" to "application/cbor",
    "data/proto" to "application/protobuf",
    "data/msgpack" to "application/msgpack",
    "archive/zip" to "application/zip",
    "archive/tar" to "application/x-tar",
    "archive/gz" to "application/gzip",
)

/**
 * Build a menu tree from JWT permission claims.
 *
 * Groups permissions by object → action → category → pattern
 * to generate a hierarchical menu structure.
 */
@Serializable
data class MenuItem(
    val label: String,
    val permission: MenuPermission? = null,
    val children: List<MenuItem> = emptyList(),
    val icon: String? = null,
    val mimeType: String? = null,
    val fileExtension: String? = null,
)

fun buildMenuTree(permissions: List<MenuPermission>): List<MenuItem> {
    return permissions
        .groupBy { it.obj }
        .map { (obj, objPerms) ->
            MenuItem(
                label = obj.replaceFirstChar { it.uppercase() },
                children = objPerms
                    .groupBy { it.action }
                    .map { (action, actionPerms) ->
                        if (actionPerms.size == 1 && actionPerms[0].category == "*") {
                            // Single wildcard action → leaf
                            MenuItem(
                                label = action.replaceFirstChar { it.uppercase() },
                                permission = actionPerms[0],
                            )
                        } else {
                            // Multiple categories → submenu
                            MenuItem(
                                label = action.replaceFirstChar { it.uppercase() },
                                children = actionPerms
                                    .groupBy { it.category }
                                    .map { (cat, catPerms) ->
                                        if (catPerms.size == 1) {
                                            MenuItem(
                                                label = cat.replaceFirstChar { it.uppercase() },
                                                permission = catPerms[0],
                                                mimeType = catPerms[0].mimeType,
                                                fileExtension = catPerms[0].fileExtension,
                                            )
                                        } else {
                                            MenuItem(
                                                label = cat.replaceFirstChar { it.uppercase() },
                                                children = catPerms.map { p ->
                                                    MenuItem(
                                                        label = ".${p.pattern}",
                                                        permission = p,
                                                        mimeType = p.mimeType,
                                                        fileExtension = p.fileExtension,
                                                    )
                                                },
                                            )
                                        }
                                    },
                            )
                        }
                    },
            )
        }
}
