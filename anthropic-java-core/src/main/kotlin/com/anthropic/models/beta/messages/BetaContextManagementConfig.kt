// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.getOrThrow
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaContextManagementConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val edits: JsonField<List<Edit>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("edits") @ExcludeMissing edits: JsonField<List<Edit>> = JsonMissing.of()
    ) : this(edits, mutableMapOf())

    /**
     * List of context management edits to apply
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun edits(): Optional<List<Edit>> = edits.getOptional("edits")

    /**
     * Returns the raw JSON value of [edits].
     *
     * Unlike [edits], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("edits") @ExcludeMissing fun _edits(): JsonField<List<Edit>> = edits

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BetaContextManagementConfig].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaContextManagementConfig]. */
    class Builder internal constructor() {

        private var edits: JsonField<MutableList<Edit>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaContextManagementConfig: BetaContextManagementConfig) = apply {
            edits = betaContextManagementConfig.edits.map { it.toMutableList() }
            additionalProperties = betaContextManagementConfig.additionalProperties.toMutableMap()
        }

        /** List of context management edits to apply */
        fun edits(edits: List<Edit>) = edits(JsonField.of(edits))

        /**
         * Sets [Builder.edits] to an arbitrary JSON value.
         *
         * You should usually call [Builder.edits] with a well-typed `List<Edit>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun edits(edits: JsonField<List<Edit>>) = apply {
            this.edits = edits.map { it.toMutableList() }
        }

        /**
         * Adds a single [Edit] to [edits].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addEdit(edit: Edit) = apply {
            edits =
                (edits ?: JsonField.of(mutableListOf())).also { checkKnown("edits", it).add(edit) }
        }

        /**
         * Alias for calling [addEdit] with `Edit.ofClearToolUses20250919(clearToolUses20250919)`.
         */
        fun addEdit(clearToolUses20250919: BetaClearToolUses20250919Edit) =
            addEdit(Edit.ofClearToolUses20250919(clearToolUses20250919))

        /**
         * Alias for calling [addEdit] with `Edit.ofClearThinking20251015(clearThinking20251015)`.
         */
        fun addEdit(clearThinking20251015: BetaClearThinking20251015Edit) =
            addEdit(Edit.ofClearThinking20251015(clearThinking20251015))

        /** Alias for calling [addEdit] with `Edit.ofCompact20260112(compact20260112)`. */
        fun addEdit(compact20260112: BetaCompact20260112Edit) =
            addEdit(Edit.ofCompact20260112(compact20260112))

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaContextManagementConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaContextManagementConfig =
            BetaContextManagementConfig(
                (edits ?: JsonMissing.of()).map { it.toImmutable() },
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaContextManagementConfig = apply {
        if (validated) {
            return@apply
        }

        edits().ifPresent { it.forEach { it.validate() } }
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
        (edits.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

    /** Automatically compact older context when reaching the configured trigger threshold. */
    @JsonDeserialize(using = Edit.Deserializer::class)
    @JsonSerialize(using = Edit.Serializer::class)
    class Edit
    private constructor(
        private val clearToolUses20250919: BetaClearToolUses20250919Edit? = null,
        private val clearThinking20251015: BetaClearThinking20251015Edit? = null,
        private val compact20260112: BetaCompact20260112Edit? = null,
        private val _json: JsonValue? = null,
    ) {

        fun clearToolUses20250919(): Optional<BetaClearToolUses20250919Edit> =
            Optional.ofNullable(clearToolUses20250919)

        fun clearThinking20251015(): Optional<BetaClearThinking20251015Edit> =
            Optional.ofNullable(clearThinking20251015)

        /** Automatically compact older context when reaching the configured trigger threshold. */
        fun compact20260112(): Optional<BetaCompact20260112Edit> =
            Optional.ofNullable(compact20260112)

        fun isClearToolUses20250919(): Boolean = clearToolUses20250919 != null

        fun isClearThinking20251015(): Boolean = clearThinking20251015 != null

        fun isCompact20260112(): Boolean = compact20260112 != null

        fun asClearToolUses20250919(): BetaClearToolUses20250919Edit =
            clearToolUses20250919.getOrThrow("clearToolUses20250919")

        fun asClearThinking20251015(): BetaClearThinking20251015Edit =
            clearThinking20251015.getOrThrow("clearThinking20251015")

        /** Automatically compact older context when reaching the configured trigger threshold. */
        fun asCompact20260112(): BetaCompact20260112Edit =
            compact20260112.getOrThrow("compact20260112")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                clearToolUses20250919 != null ->
                    visitor.visitClearToolUses20250919(clearToolUses20250919)
                clearThinking20251015 != null ->
                    visitor.visitClearThinking20251015(clearThinking20251015)
                compact20260112 != null -> visitor.visitCompact20260112(compact20260112)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Edit = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitClearToolUses20250919(
                        clearToolUses20250919: BetaClearToolUses20250919Edit
                    ) {
                        clearToolUses20250919.validate()
                    }

                    override fun visitClearThinking20251015(
                        clearThinking20251015: BetaClearThinking20251015Edit
                    ) {
                        clearThinking20251015.validate()
                    }

                    override fun visitCompact20260112(compact20260112: BetaCompact20260112Edit) {
                        compact20260112.validate()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitClearToolUses20250919(
                        clearToolUses20250919: BetaClearToolUses20250919Edit
                    ) = clearToolUses20250919.validity()

                    override fun visitClearThinking20251015(
                        clearThinking20251015: BetaClearThinking20251015Edit
                    ) = clearThinking20251015.validity()

                    override fun visitCompact20260112(compact20260112: BetaCompact20260112Edit) =
                        compact20260112.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Edit &&
                clearToolUses20250919 == other.clearToolUses20250919 &&
                clearThinking20251015 == other.clearThinking20251015 &&
                compact20260112 == other.compact20260112
        }

        override fun hashCode(): Int =
            Objects.hash(clearToolUses20250919, clearThinking20251015, compact20260112)

        override fun toString(): String =
            when {
                clearToolUses20250919 != null ->
                    "Edit{clearToolUses20250919=$clearToolUses20250919}"
                clearThinking20251015 != null ->
                    "Edit{clearThinking20251015=$clearThinking20251015}"
                compact20260112 != null -> "Edit{compact20260112=$compact20260112}"
                _json != null -> "Edit{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Edit")
            }

        companion object {

            @JvmStatic
            fun ofClearToolUses20250919(clearToolUses20250919: BetaClearToolUses20250919Edit) =
                Edit(clearToolUses20250919 = clearToolUses20250919)

            @JvmStatic
            fun ofClearThinking20251015(clearThinking20251015: BetaClearThinking20251015Edit) =
                Edit(clearThinking20251015 = clearThinking20251015)

            /**
             * Automatically compact older context when reaching the configured trigger threshold.
             */
            @JvmStatic
            fun ofCompact20260112(compact20260112: BetaCompact20260112Edit) =
                Edit(compact20260112 = compact20260112)
        }

        /** An interface that defines how to map each variant of [Edit] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitClearToolUses20250919(clearToolUses20250919: BetaClearToolUses20250919Edit): T

            fun visitClearThinking20251015(clearThinking20251015: BetaClearThinking20251015Edit): T

            /**
             * Automatically compact older context when reaching the configured trigger threshold.
             */
            fun visitCompact20260112(compact20260112: BetaCompact20260112Edit): T

            /**
             * Maps an unknown variant of [Edit] to a value of type [T].
             *
             * An instance of [Edit] can contain an unknown variant if it was deserialized from data
             * that doesn't match any known variant. For example, if the SDK is on an older version
             * than the API, then the API may respond with new variants that the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Edit: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Edit>(Edit::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Edit {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "clear_tool_uses_20250919" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaClearToolUses20250919Edit>())
                            ?.let { Edit(clearToolUses20250919 = it, _json = json) }
                            ?: Edit(_json = json)
                    }
                    "clear_thinking_20251015" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaClearThinking20251015Edit>())
                            ?.let { Edit(clearThinking20251015 = it, _json = json) }
                            ?: Edit(_json = json)
                    }
                    "compact_20260112" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaCompact20260112Edit>())
                            ?.let { Edit(compact20260112 = it, _json = json) } ?: Edit(_json = json)
                    }
                }

                return Edit(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Edit>(Edit::class) {

            override fun serialize(
                value: Edit,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.clearToolUses20250919 != null ->
                        generator.writeObject(value.clearToolUses20250919)
                    value.clearThinking20251015 != null ->
                        generator.writeObject(value.clearThinking20251015)
                    value.compact20260112 != null -> generator.writeObject(value.compact20260112)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Edit")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaContextManagementConfig &&
            edits == other.edits &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(edits, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaContextManagementConfig{edits=$edits, additionalProperties=$additionalProperties}"
}
