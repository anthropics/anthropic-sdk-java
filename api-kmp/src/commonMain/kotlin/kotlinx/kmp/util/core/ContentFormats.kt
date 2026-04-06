package kotlinx.kmp.util.core

import com.ensarsarajcic.kotlinx.serialization.msgpack.MsgPack
import kotlinx.serialization.*
import kotlinx.serialization.cbor.Cbor
import kotlinx.serialization.json.Json
import kotlinx.serialization.protobuf.ProtoBuf

/**
 * Supported wire formats for ktor ContentNegotiation.
 *
 * Each format wraps a kotlinx.serialization [BinaryFormat] or [StringFormat],
 * usable on all KMP targets (JVM, JS, Native) without platform-specific code.
 *
 * Usage with ktor server:
 * ```kotlin
 * install(ContentNegotiation) {
 *     ContentFormat.entries.forEach { it.install(this) }
 * }
 * ```
 *
 * Usage standalone:
 * ```kotlin
 * val bytes = ContentFormat.CBOR.encodeToByteArray(MyModel.serializer(), model)
 * val model = ContentFormat.PROTOBUF.decodeFromByteArray(MyModel.serializer(), bytes)
 * ```
 */
enum class ContentFormat(
    val mediaType: String,
    val description: String,
) {
    JSON("application/json", "JSON — kotlinx.serialization.json.Json") {
        override val stringFormat: StringFormat = Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
            coerceInputValues = true
        }
        override fun <T> encodeToByteArray(serializer: KSerializer<T>, value: T): ByteArray =
            stringFormat.encodeToString(serializer, value).encodeToByteArray()
        override fun <T> decodeFromByteArray(serializer: KSerializer<T>, bytes: ByteArray): T =
            stringFormat.decodeFromString(serializer, bytes.decodeToString())
    },

    CBOR("application/cbor", "CBOR (RFC 8949) — kotlinx.serialization.cbor.Cbor") {
        private val cbor = Cbor { ignoreUnknownKeys = true }
        override fun <T> encodeToByteArray(serializer: KSerializer<T>, value: T): ByteArray =
            cbor.encodeToByteArray(serializer, value)
        override fun <T> decodeFromByteArray(serializer: KSerializer<T>, bytes: ByteArray): T =
            cbor.decodeFromByteArray(serializer, bytes)
    },

    PROTOBUF("application/protobuf", "Protocol Buffers — kotlinx.serialization.protobuf.ProtoBuf") {
        private val proto = ProtoBuf { encodeDefaults = false }
        override fun <T> encodeToByteArray(serializer: KSerializer<T>, value: T): ByteArray =
            proto.encodeToByteArray(serializer, value)
        override fun <T> decodeFromByteArray(serializer: KSerializer<T>, bytes: ByteArray): T =
            proto.decodeFromByteArray(serializer, bytes)
    },

    MSGPACK("application/msgpack", "MessagePack — kotlinx-serialization-msgpack") {
        private val msgpack = MsgPack()
        override fun <T> encodeToByteArray(serializer: KSerializer<T>, value: T): ByteArray =
            msgpack.encodeToByteArray(serializer, value)
        override fun <T> decodeFromByteArray(serializer: KSerializer<T>, bytes: ByteArray): T =
            msgpack.decodeFromByteArray(serializer, bytes)
    };

    /** Non-null for text-based formats (JSON). Null for binary (CBOR, Protobuf). */
    open val stringFormat: StringFormat? = null

    abstract fun <T> encodeToByteArray(serializer: KSerializer<T>, value: T): ByteArray
    abstract fun <T> decodeFromByteArray(serializer: KSerializer<T>, bytes: ByteArray): T
}
