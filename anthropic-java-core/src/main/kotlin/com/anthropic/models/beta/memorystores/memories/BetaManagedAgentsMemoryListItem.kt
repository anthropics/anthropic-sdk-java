// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memories

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * One item in a [List memories](/en/api/beta/memory_stores/memories/list) response: either a
 * `memory` object or, when `depth` is set, a `memory_prefix` rollup marker.
 */
@JsonDeserialize(using = BetaManagedAgentsMemoryListItem.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsMemoryListItem.Serializer::class)
class BetaManagedAgentsMemoryListItem
private constructor(
    private val memory: BetaManagedAgentsMemory? = null,
    private val memoryPrefix: BetaManagedAgentsMemoryPrefix? = null,
    private val _json: JsonValue? = null,
) {

    /**
     * A `memory` object: a single text document at a hierarchical path inside a memory store. The
     * `content` field is populated when `view=full` and `null` when `view=basic`; the
     * `content_size_bytes` and `content_sha256` fields are always populated so sync clients can
     * diff without fetching content. Memories are addressed by their `mem_...` ID; the path is the
     * create key and can be changed via update.
     */
    fun memory(): Optional<BetaManagedAgentsMemory> = Optional.ofNullable(memory)

    /**
     * A rolled-up directory marker returned by
     * [List memories](/en/api/beta/memory_stores/memories/list) when `depth` is set. Indicates that
     * one or more memories exist deeper than the requested depth under this prefix. This is a
     * list-time rollup, not a stored resource; it has no ID and no lifecycle. Each prefix counts
     * toward the page `limit` and interleaves with `memory` items in path order.
     */
    fun memoryPrefix(): Optional<BetaManagedAgentsMemoryPrefix> = Optional.ofNullable(memoryPrefix)

    fun isMemory(): Boolean = memory != null

    fun isMemoryPrefix(): Boolean = memoryPrefix != null

    /**
     * A `memory` object: a single text document at a hierarchical path inside a memory store. The
     * `content` field is populated when `view=full` and `null` when `view=basic`; the
     * `content_size_bytes` and `content_sha256` fields are always populated so sync clients can
     * diff without fetching content. Memories are addressed by their `mem_...` ID; the path is the
     * create key and can be changed via update.
     */
    fun asMemory(): BetaManagedAgentsMemory = memory.getOrThrow("memory")

    /**
     * A rolled-up directory marker returned by
     * [List memories](/en/api/beta/memory_stores/memories/list) when `depth` is set. Indicates that
     * one or more memories exist deeper than the requested depth under this prefix. This is a
     * list-time rollup, not a stored resource; it has no ID and no lifecycle. Each prefix counts
     * toward the page `limit` and interleaves with `memory` items in path order.
     */
    fun asMemoryPrefix(): BetaManagedAgentsMemoryPrefix = memoryPrefix.getOrThrow("memoryPrefix")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaManagedAgentsMemoryListItem.accept(new BetaManagedAgentsMemoryListItem.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitMemory(BetaManagedAgentsMemory memory) {
     *         return Optional.of(memory.toString());
     *     }
     *
     *     // ...
     *
     *     @Override
     *     public Optional<String> unknown(JsonValue json) {
     *         // Or inspect the `json`.
     *         return Optional.empty();
     *     }
     * });
     * ```
     *
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
    fun <T> accept(visitor: Visitor<T>): T =
        when {
            memory != null -> visitor.visitMemory(memory)
            memoryPrefix != null -> visitor.visitMemoryPrefix(memoryPrefix)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaManagedAgentsMemoryListItem = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitMemory(memory: BetaManagedAgentsMemory) {
                    memory.validate()
                }

                override fun visitMemoryPrefix(memoryPrefix: BetaManagedAgentsMemoryPrefix) {
                    memoryPrefix.validate()
                }
            }
        )
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitMemory(memory: BetaManagedAgentsMemory) = memory.validity()

                override fun visitMemoryPrefix(memoryPrefix: BetaManagedAgentsMemoryPrefix) =
                    memoryPrefix.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMemoryListItem &&
            memory == other.memory &&
            memoryPrefix == other.memoryPrefix
    }

    override fun hashCode(): Int = Objects.hash(memory, memoryPrefix)

    override fun toString(): String =
        when {
            memory != null -> "BetaManagedAgentsMemoryListItem{memory=$memory}"
            memoryPrefix != null -> "BetaManagedAgentsMemoryListItem{memoryPrefix=$memoryPrefix}"
            _json != null -> "BetaManagedAgentsMemoryListItem{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsMemoryListItem")
        }

    companion object {

        /**
         * A `memory` object: a single text document at a hierarchical path inside a memory store.
         * The `content` field is populated when `view=full` and `null` when `view=basic`; the
         * `content_size_bytes` and `content_sha256` fields are always populated so sync clients can
         * diff without fetching content. Memories are addressed by their `mem_...` ID; the path is
         * the create key and can be changed via update.
         */
        @JvmStatic
        fun ofMemory(memory: BetaManagedAgentsMemory) =
            BetaManagedAgentsMemoryListItem(memory = memory)

        /**
         * A rolled-up directory marker returned by
         * [List memories](/en/api/beta/memory_stores/memories/list) when `depth` is set. Indicates
         * that one or more memories exist deeper than the requested depth under this prefix. This
         * is a list-time rollup, not a stored resource; it has no ID and no lifecycle. Each prefix
         * counts toward the page `limit` and interleaves with `memory` items in path order.
         */
        @JvmStatic
        fun ofMemoryPrefix(memoryPrefix: BetaManagedAgentsMemoryPrefix) =
            BetaManagedAgentsMemoryListItem(memoryPrefix = memoryPrefix)
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsMemoryListItem] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /**
         * A `memory` object: a single text document at a hierarchical path inside a memory store.
         * The `content` field is populated when `view=full` and `null` when `view=basic`; the
         * `content_size_bytes` and `content_sha256` fields are always populated so sync clients can
         * diff without fetching content. Memories are addressed by their `mem_...` ID; the path is
         * the create key and can be changed via update.
         */
        fun visitMemory(memory: BetaManagedAgentsMemory): T

        /**
         * A rolled-up directory marker returned by
         * [List memories](/en/api/beta/memory_stores/memories/list) when `depth` is set. Indicates
         * that one or more memories exist deeper than the requested depth under this prefix. This
         * is a list-time rollup, not a stored resource; it has no ID and no lifecycle. Each prefix
         * counts toward the page `limit` and interleaves with `memory` items in path order.
         */
        fun visitMemoryPrefix(memoryPrefix: BetaManagedAgentsMemoryPrefix): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsMemoryListItem] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsMemoryListItem] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaManagedAgentsMemoryListItem: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsMemoryListItem>(BetaManagedAgentsMemoryListItem::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsMemoryListItem {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "memory" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsMemory>())?.let {
                        BetaManagedAgentsMemoryListItem(memory = it, _json = json)
                    } ?: BetaManagedAgentsMemoryListItem(_json = json)
                }
                "memory_prefix" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsMemoryPrefix>())
                        ?.let { BetaManagedAgentsMemoryListItem(memoryPrefix = it, _json = json) }
                        ?: BetaManagedAgentsMemoryListItem(_json = json)
                }
            }

            return BetaManagedAgentsMemoryListItem(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsMemoryListItem>(BetaManagedAgentsMemoryListItem::class) {

        override fun serialize(
            value: BetaManagedAgentsMemoryListItem,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.memory != null -> generator.writeObject(value.memory)
                value.memoryPrefix != null -> generator.writeObject(value.memoryPrefix)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsMemoryListItem")
            }
        }
    }
}
