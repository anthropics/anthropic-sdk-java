package kotlinx.kmp.util.core

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.protobuf.ProtoNumber
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ContentFormatsTest {

    @Serializable
    data class Pet(
        @ProtoNumber(1) @SerialName("name") val name: String,
        @ProtoNumber(2) @SerialName("status") val status: String = "available",
        @ProtoNumber(3) @SerialName("id") val id: Int = 0,
    )

    // --- JSON ---

    @Test
    fun json_roundTrip() {
        val pet = Pet("Buddy", "adopted", 42)
        val bytes = ContentFormat.JSON.encodeToByteArray(Pet.serializer(), pet)
        val decoded = ContentFormat.JSON.decodeFromByteArray(Pet.serializer(), bytes)
        assertEquals(pet, decoded)
    }

    @Test
    fun json_mediaType() {
        assertEquals("application/json", ContentFormat.JSON.mediaType)
    }

    @Test
    fun json_stringFormat_notNull() {
        assertNotNull(ContentFormat.JSON.stringFormat)
    }

    // --- CBOR ---

    @Test
    fun cbor_roundTrip() {
        val pet = Pet("Max", "pending", 7)
        val bytes = ContentFormat.CBOR.encodeToByteArray(Pet.serializer(), pet)
        assertTrue(bytes.isNotEmpty())
        val decoded = ContentFormat.CBOR.decodeFromByteArray(Pet.serializer(), bytes)
        assertEquals(pet, decoded)
    }

    @Test
    fun cbor_mediaType() {
        assertEquals("application/cbor", ContentFormat.CBOR.mediaType)
    }

    @Test
    fun cbor_binary_smaller_than_json() {
        val pet = Pet("A very long name for testing compression", "available", 999)
        val jsonBytes = ContentFormat.JSON.encodeToByteArray(Pet.serializer(), pet)
        val cborBytes = ContentFormat.CBOR.encodeToByteArray(Pet.serializer(), pet)
        assertTrue(cborBytes.size <= jsonBytes.size, "CBOR should be <= JSON size")
    }

    // --- Protobuf ---

    @Test
    fun protobuf_roundTrip() {
        val pet = Pet("Rex", "sold", 100)
        val bytes = ContentFormat.PROTOBUF.encodeToByteArray(Pet.serializer(), pet)
        assertTrue(bytes.isNotEmpty())
        val decoded = ContentFormat.PROTOBUF.decodeFromByteArray(Pet.serializer(), bytes)
        assertEquals(pet, decoded)
    }

    @Test
    fun protobuf_mediaType() {
        assertEquals("application/protobuf", ContentFormat.PROTOBUF.mediaType)
    }

    @Test
    fun protobuf_compact() {
        val pet = Pet("A", "B", 1)
        val protoBytes = ContentFormat.PROTOBUF.encodeToByteArray(Pet.serializer(), pet)
        val jsonBytes = ContentFormat.JSON.encodeToByteArray(Pet.serializer(), pet)
        assertTrue(protoBytes.size < jsonBytes.size, "Protobuf should be smaller than JSON")
    }

    // --- MsgPack ---

    @Test
    fun msgpack_roundTrip() {
        val pet = Pet("Luna", "available", 55)
        val bytes = ContentFormat.MSGPACK.encodeToByteArray(Pet.serializer(), pet)
        assertTrue(bytes.isNotEmpty())
        val decoded = ContentFormat.MSGPACK.decodeFromByteArray(Pet.serializer(), bytes)
        assertEquals(pet, decoded)
    }

    @Test
    fun msgpack_mediaType() {
        assertEquals("application/msgpack", ContentFormat.MSGPACK.mediaType)
    }

    // --- Cross-format ---

    @Test
    fun all_formats_registered() {
        assertEquals(4, ContentFormat.entries.size)
        assertEquals(
            setOf("application/json", "application/cbor", "application/protobuf", "application/msgpack"),
            ContentFormat.entries.map { it.mediaType }.toSet()
        )
    }
}
