// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
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

class BetaContextManagementResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val appliedEdits: JsonField<List<AppliedEdit>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("applied_edits")
        @ExcludeMissing
        appliedEdits: JsonField<List<AppliedEdit>> = JsonMissing.of()
    ) : this(appliedEdits, mutableMapOf())

    /**
     * List of context management edits that were applied.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun appliedEdits(): List<AppliedEdit> = appliedEdits.getRequired("applied_edits")

    /**
     * Returns the raw JSON value of [appliedEdits].
     *
     * Unlike [appliedEdits], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("applied_edits")
    @ExcludeMissing
    fun _appliedEdits(): JsonField<List<AppliedEdit>> = appliedEdits

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
         * Returns a mutable builder for constructing an instance of
         * [BetaContextManagementResponse].
         *
         * The following fields are required:
         * ```java
         * .appliedEdits()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaContextManagementResponse] with the required
         * [appliedEdits] set to the given value.
         */
        @JvmStatic
        fun of(appliedEdits: List<AppliedEdit>) = builder().appliedEdits(appliedEdits).build()
    }

    /** A builder for [BetaContextManagementResponse]. */
    class Builder internal constructor() {

        private var appliedEdits: JsonField<MutableList<AppliedEdit>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaContextManagementResponse: BetaContextManagementResponse) = apply {
            appliedEdits =
                betaContextManagementResponse.appliedEdits
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            additionalProperties = betaContextManagementResponse.additionalProperties.toMutableMap()
        }

        /** List of context management edits that were applied. */
        fun appliedEdits(appliedEdits: List<AppliedEdit>) = appliedEdits(JsonField.of(appliedEdits))

        /**
         * Sets [Builder.appliedEdits] to an arbitrary JSON value.
         *
         * You should usually call [Builder.appliedEdits] with a well-typed `List<AppliedEdit>`
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun appliedEdits(appliedEdits: JsonField<List<AppliedEdit>>) = apply {
            this.appliedEdits = appliedEdits.map { it.toMutableList() }
        }

        /**
         * Adds a single [AppliedEdit] to [appliedEdits].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAppliedEdit(appliedEdit: AppliedEdit) = apply {
            appliedEdits =
                (appliedEdits ?: JsonField.of(mutableListOf())).also {
                    checkKnown("appliedEdits", it).add(appliedEdit)
                }
        }

        /**
         * Alias for calling [addAppliedEdit] with
         * `AppliedEdit.ofClearToolUses20250919(clearToolUses20250919)`.
         */
        fun addAppliedEdit(clearToolUses20250919: BetaClearToolUses20250919EditResponse) =
            addAppliedEdit(AppliedEdit.ofClearToolUses20250919(clearToolUses20250919))

        /**
         * Alias for calling [addAppliedEdit] with
         * `AppliedEdit.ofClearThinking20251015(clearThinking20251015)`.
         */
        fun addAppliedEdit(clearThinking20251015: BetaClearThinking20251015EditResponse) =
            addAppliedEdit(AppliedEdit.ofClearThinking20251015(clearThinking20251015))

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
         * Returns an immutable instance of [BetaContextManagementResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .appliedEdits()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaContextManagementResponse =
            BetaContextManagementResponse(
                checkRequired("appliedEdits", appliedEdits).map { it.toImmutable() },
                additionalProperties.toMutableMap(),
            )
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
    fun validate(): BetaContextManagementResponse = apply {
        if (validated) {
            return@apply
        }

        appliedEdits().forEach { it.validate() }
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
        (appliedEdits.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

    @JsonDeserialize(using = AppliedEdit.Deserializer::class)
    @JsonSerialize(using = AppliedEdit.Serializer::class)
    class AppliedEdit
    private constructor(
        private val clearToolUses20250919: BetaClearToolUses20250919EditResponse? = null,
        private val clearThinking20251015: BetaClearThinking20251015EditResponse? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitClearToolUses20250919(
                        clearToolUses20250919: BetaClearToolUses20250919EditResponse
                    ): Type = Type.CLEAR_TOOL_USES_20250919

                    override fun visitClearThinking20251015(
                        clearThinking20251015: BetaClearThinking20251015EditResponse
                    ): Type = Type.CLEAR_THINKING_20251015

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun clearedInputTokens(): Long =
            accept(
                object : Visitor<Long> {
                    override fun visitClearToolUses20250919(
                        clearToolUses20250919: BetaClearToolUses20250919EditResponse
                    ): Long = clearToolUses20250919.clearedInputTokens()

                    override fun visitClearThinking20251015(
                        clearThinking20251015: BetaClearThinking20251015EditResponse
                    ): Long = clearThinking20251015.clearedInputTokens()

                    override fun unknown(json: JsonValue?): Long =
                        json
                            .getProperty<Long>("cleared_input_tokens")
                            .getRequired("cleared_input_tokens")
                }
            )

        fun clearToolUses20250919(): Optional<BetaClearToolUses20250919EditResponse> =
            Optional.ofNullable(clearToolUses20250919)

        fun clearThinking20251015(): Optional<BetaClearThinking20251015EditResponse> =
            Optional.ofNullable(clearThinking20251015)

        fun isClearToolUses20250919(): Boolean = clearToolUses20250919 != null

        fun isClearThinking20251015(): Boolean = clearThinking20251015 != null

        fun asClearToolUses20250919(): BetaClearToolUses20250919EditResponse =
            clearToolUses20250919.getOrThrow("clearToolUses20250919")

        fun asClearThinking20251015(): BetaClearThinking20251015EditResponse =
            clearThinking20251015.getOrThrow("clearThinking20251015")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = appliedEdit.accept(new AppliedEdit.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitClearToolUses20250919(BetaClearToolUses20250919EditResponse clearToolUses20250919) {
         *         return Optional.of(clearToolUses20250919.toString());
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
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                clearToolUses20250919 != null ->
                    visitor.visitClearToolUses20250919(clearToolUses20250919)
                clearThinking20251015 != null ->
                    visitor.visitClearThinking20251015(clearThinking20251015)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): AppliedEdit = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitClearToolUses20250919(
                        clearToolUses20250919: BetaClearToolUses20250919EditResponse
                    ) {
                        clearToolUses20250919.validate()
                    }

                    override fun visitClearThinking20251015(
                        clearThinking20251015: BetaClearThinking20251015EditResponse
                    ) {
                        clearThinking20251015.validate()
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
                        clearToolUses20250919: BetaClearToolUses20250919EditResponse
                    ) = clearToolUses20250919.validity()

                    override fun visitClearThinking20251015(
                        clearThinking20251015: BetaClearThinking20251015EditResponse
                    ) = clearThinking20251015.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is AppliedEdit &&
                clearToolUses20250919 == other.clearToolUses20250919 &&
                clearThinking20251015 == other.clearThinking20251015
        }

        override fun hashCode(): Int = Objects.hash(clearToolUses20250919, clearThinking20251015)

        override fun toString(): String =
            when {
                clearToolUses20250919 != null ->
                    "AppliedEdit{clearToolUses20250919=$clearToolUses20250919}"
                clearThinking20251015 != null ->
                    "AppliedEdit{clearThinking20251015=$clearThinking20251015}"
                _json != null -> "AppliedEdit{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid AppliedEdit")
            }

        companion object {

            @JvmStatic
            fun ofClearToolUses20250919(
                clearToolUses20250919: BetaClearToolUses20250919EditResponse
            ) = AppliedEdit(clearToolUses20250919 = clearToolUses20250919)

            @JvmStatic
            fun ofClearThinking20251015(
                clearThinking20251015: BetaClearThinking20251015EditResponse
            ) = AppliedEdit(clearThinking20251015 = clearThinking20251015)
        }

        /**
         * An interface that defines how to map each variant of [AppliedEdit] to a value of type
         * [T].
         */
        interface Visitor<out T> {

            fun visitClearToolUses20250919(
                clearToolUses20250919: BetaClearToolUses20250919EditResponse
            ): T

            fun visitClearThinking20251015(
                clearThinking20251015: BetaClearThinking20251015EditResponse
            ): T

            /**
             * Maps an unknown variant of [AppliedEdit] to a value of type [T].
             *
             * An instance of [AppliedEdit] can contain an unknown variant if it was deserialized
             * from data that doesn't match any known variant. For example, if the SDK is on an
             * older version than the API, then the API may respond with new variants that the SDK
             * is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown AppliedEdit: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<AppliedEdit>(AppliedEdit::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): AppliedEdit {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "clear_tool_uses_20250919" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaClearToolUses20250919EditResponse>(),
                            )
                            ?.let { AppliedEdit(clearToolUses20250919 = it, _json = json) }
                            ?: AppliedEdit(_json = json)
                    }
                    "clear_thinking_20251015" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaClearThinking20251015EditResponse>(),
                            )
                            ?.let { AppliedEdit(clearThinking20251015 = it, _json = json) }
                            ?: AppliedEdit(_json = json)
                    }
                }

                return AppliedEdit(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<AppliedEdit>(AppliedEdit::class) {

            override fun serialize(
                value: AppliedEdit,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.clearToolUses20250919 != null ->
                        generator.writeObject(value.clearToolUses20250919)
                    value.clearThinking20251015 != null ->
                        generator.writeObject(value.clearThinking20251015)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid AppliedEdit")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val CLEAR_TOOL_USES_20250919 = of("clear_tool_uses_20250919")

                @JvmField val CLEAR_THINKING_20251015 = of("clear_thinking_20251015")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                CLEAR_TOOL_USES_20250919,
                CLEAR_THINKING_20251015,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                CLEAR_TOOL_USES_20250919,
                CLEAR_THINKING_20251015,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    CLEAR_TOOL_USES_20250919 -> Value.CLEAR_TOOL_USES_20250919
                    CLEAR_THINKING_20251015 -> Value.CLEAR_THINKING_20251015
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    CLEAR_TOOL_USES_20250919 -> Known.CLEAR_TOOL_USES_20250919
                    CLEAR_THINKING_20251015 -> Known.CLEAR_THINKING_20251015
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
             */
            fun asString(): String =
                _value().asString().orElseThrow {
                    AnthropicInvalidDataException("Value is not a String")
                }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Type = apply {
                if (validated) {
                    return@apply
                }

                known()
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
            @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaContextManagementResponse &&
            appliedEdits == other.appliedEdits &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(appliedEdits, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaContextManagementResponse{appliedEdits=$appliedEdits, additionalProperties=$additionalProperties}"
}
