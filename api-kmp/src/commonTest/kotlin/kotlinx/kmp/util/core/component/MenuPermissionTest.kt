package kotlinx.kmp.util.core.component

import kotlinx.serialization.json.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class MenuPermissionTest {

    // === Parsing ===

    @Test fun parse_full_claim() {
        val p = MenuPermission.fromClaim("file;upload;img;png")
        assertEquals("file", p.obj)
        assertEquals("upload", p.action)
        assertEquals("img", p.category)
        assertEquals("png", p.pattern)
    }

    @Test fun parse_wildcard_claim() {
        val p = MenuPermission.fromClaim("admin;*;*;*")
        assertEquals("admin", p.obj)
        assertEquals("*", p.action)
    }

    @Test fun parse_partial_claim() {
        val p = MenuPermission.fromClaim("pet;create")
        assertEquals("pet", p.obj)
        assertEquals("create", p.action)
        assertEquals("*", p.category)
        assertEquals("*", p.pattern)
    }

    @Test fun toClaim_roundTrip() {
        val p = MenuPermission("file", "upload", "img", "png")
        assertEquals("file;upload;img;png", p.toClaim())
        assertEquals(p, MenuPermission.fromClaim(p.toClaim()))
    }

    // === Matching ===

    @Test fun exact_match() {
        val perm = MenuPermission("file", "upload", "img", "png")
        val req = MenuPermission("file", "upload", "img", "png")
        assertTrue(perm.matches(req))
    }

    @Test fun wildcard_action_matches() {
        val perm = MenuPermission("admin", "*", "*", "*")
        assertTrue(perm.matches(MenuPermission("admin", "delete", "user", "all")))
    }

    @Test fun wildcard_category_matches() {
        val perm = MenuPermission("file", "upload", "*", "*")
        assertTrue(perm.matches(MenuPermission("file", "upload", "img", "png")))
    }

    @Test fun no_match_wrong_object() {
        val perm = MenuPermission("file", "upload", "img", "png")
        assertFalse(perm.matches(MenuPermission("pet", "upload", "img", "png")))
    }

    @Test fun no_match_wrong_action() {
        val perm = MenuPermission("file", "upload", "img", "png")
        assertFalse(perm.matches(MenuPermission("file", "download", "img", "png")))
    }

    // === JWT Claims parsing ===

    @Test fun fromJwtClaims_array() {
        val claims = buildJsonObject {
            put("permissions", buildJsonArray {
                add("file;upload;img;png")
                add("file;upload;code;puml")
                add("pet;create;*;*")
            })
        }
        val perms = MenuPermission.fromJwtClaims(claims)
        assertEquals(3, perms.size)
        assertEquals("file", perms[0].obj)
        assertEquals("png", perms[0].pattern)
        assertEquals("puml", perms[1].pattern)
    }

    @Test fun fromJwtClaims_csv() {
        val claims = buildJsonObject {
            put("permissions", "file;upload;img;png, pet;create;*;*")
        }
        val perms = MenuPermission.fromJwtClaims(claims)
        assertEquals(2, perms.size)
    }

    @Test fun fromJwtClaims_missing() {
        val perms = MenuPermission.fromJwtClaims(buildJsonObject { })
        assertTrue(perms.isEmpty())
    }

    // === MIME types ===

    @Test fun mimeType_png() {
        assertEquals("image/png", MenuPermission("file", "upload", "img", "png").mimeType)
    }

    @Test fun mimeType_puml() {
        assertEquals("text/x-plantuml", MenuPermission("file", "upload", "code", "puml").mimeType)
    }

    @Test fun mimeType_pdf() {
        assertEquals("application/pdf", MenuPermission("file", "download", "doc", "pdf").mimeType)
    }

    @Test fun mimeType_category_fallback() {
        assertEquals("image/*", MenuPermission("file", "upload", "img", "unknown").mimeType)
    }

    @Test fun mimeType_wildcard_null() {
        assertNull(MenuPermission("pet", "create", "*", "*").mimeType)
    }

    @Test fun fileExtension() {
        assertEquals(".png", MenuPermission("file", "upload", "img", "png").fileExtension)
        assertNull(MenuPermission("pet", "create", "*", "*").fileExtension)
    }

    // === Operation ID ===

    @Test fun operationId() {
        assertEquals("file_upload", MenuPermission("file", "upload", "img", "png").operationId)
        assertEquals("pet_create", MenuPermission("pet", "create", "*", "*").operationId)
    }

    // === Menu Tree ===

    @Test fun menuTree_groups_by_object() {
        val perms = listOf(
            MenuPermission.fromClaim("file;upload;img;png"),
            MenuPermission.fromClaim("file;upload;img;jpg"),
            MenuPermission.fromClaim("file;upload;code;puml"),
            MenuPermission.fromClaim("pet;create;*;*"),
        )
        val tree = buildMenuTree(perms)

        assertEquals(2, tree.size) // File + Pet
        assertEquals("File", tree[0].label)
        assertEquals("Pet", tree[1].label)
    }

    @Test fun menuTree_groups_by_action() {
        val perms = listOf(
            MenuPermission.fromClaim("file;upload;img;png"),
            MenuPermission.fromClaim("file;download;doc;pdf"),
        )
        val tree = buildMenuTree(perms)
        assertEquals(1, tree.size)
        assertEquals(2, tree[0].children.size) // Upload + Download
    }

    @Test fun menuTree_groups_by_category() {
        val perms = listOf(
            MenuPermission.fromClaim("file;upload;img;png"),
            MenuPermission.fromClaim("file;upload;img;jpg"),
            MenuPermission.fromClaim("file;upload;code;puml"),
        )
        val tree = buildMenuTree(perms)
        val upload = tree[0].children[0]
        assertEquals("Upload", upload.label)
        assertEquals(2, upload.children.size) // Img + Code

        val img = upload.children.find { it.label == "Img" }!!
        assertEquals(2, img.children.size) // .png + .jpg
        assertEquals(".png", img.children[0].label)
    }

    @Test fun menuTree_wildcard_action_leaf() {
        val perms = listOf(MenuPermission.fromClaim("admin;*;*;*"))
        val tree = buildMenuTree(perms)
        assertEquals("Admin", tree[0].label)
        assertEquals("*", tree[0].children[0].label)
        assertNotNull(tree[0].children[0].permission)
    }

    @Test fun menuTree_serializes() {
        val tree = buildMenuTree(listOf(
            MenuPermission.fromClaim("file;upload;img;png"),
        ))
        val json = Json.encodeToString(kotlinx.serialization.builtins.ListSerializer(MenuItem.serializer()), tree)
        assertTrue(json.contains("File"))
        assertTrue(json.contains(".png"))
    }
}
