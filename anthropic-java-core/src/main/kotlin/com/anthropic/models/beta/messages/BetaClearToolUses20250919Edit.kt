// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
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

class BetaClearToolUses20250919Edit
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val clearAtLeast: JsonField<BetaInputTokensClearAtLeast>,
    private val clearToolInputs: JsonField<ClearToolInputs>,
    private val excludeTools: JsonField<List<String>>,
    private val keep: JsonField<BetaToolUsesKeep>,
    private val trigger: JsonField<Trigger>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("clear_at_least")
        @ExcludeMissing
        clearAtLeast: JsonField<BetaInputTokensClearAtLeast> = JsonMissing.of(),
        @JsonProperty("clear_tool_inputs")
        @ExcludeMissing
        clearToolInputs: JsonField<ClearToolInputs> = JsonMissing.of(),
        @JsonProperty("exclude_tools")
        @ExcludeMissing
        excludeTools: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("keep") @ExcludeMissing keep: JsonField<BetaToolUsesKeep> = JsonMissing.of(),
        @JsonProperty("trigger") @ExcludeMissing trigger: JsonField<Trigger> = JsonMissing.of(),
    ) : this(type, clearAtLeast, clearToolInputs, excludeTools, keep, trigger, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("clear_tool_uses_20250919")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Minimum number of tokens that must be cleared when triggered. Context will only be modified
     * if at least this many tokens can be removed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun clearAtLeast(): Optional<BetaInputTokensClearAtLeast> =
        clearAtLeast.getOptional("clear_at_least")

    /**
     * Whether to clear all tool inputs (bool) or specific tool inputs to clear (list)
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun clearToolInputs(): Optional<ClearToolInputs> =
        clearToolInputs.getOptional("clear_tool_inputs")

    /**
     * Tool names whose uses are preserved from clearing
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun excludeTools(): Optional<List<String>> = excludeTools.getOptional("exclude_tools")

    /**
     * Number of tool uses to retain in the conversation
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun keep(): Optional<BetaToolUsesKeep> = keep.getOptional("keep")

    /**
     * Condition that triggers the context management strategy
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun trigger(): Optional<Trigger> = trigger.getOptional("trigger")

    /**
     * Returns the raw JSON value of [clearAtLeast].
     *
     * Unlike [clearAtLeast], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("clear_at_least")
    @ExcludeMissing
    fun _clearAtLeast(): JsonField<BetaInputTokensClearAtLeast> = clearAtLeast

    /**
     * Returns the raw JSON value of [clearToolInputs].
     *
     * Unlike [clearToolInputs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("clear_tool_inputs")
    @ExcludeMissing
    fun _clearToolInputs(): JsonField<ClearToolInputs> = clearToolInputs

    /**
     * Returns the raw JSON value of [excludeTools].
     *
     * Unlike [excludeTools], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("exclude_tools")
    @ExcludeMissing
    fun _excludeTools(): JsonField<List<String>> = excludeTools

    /**
     * Returns the raw JSON value of [keep].
     *
     * Unlike [keep], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("keep") @ExcludeMissing fun _keep(): JsonField<BetaToolUsesKeep> = keep

    /**
     * Returns the raw JSON value of [trigger].
     *
     * Unlike [trigger], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("trigger") @ExcludeMissing fun _trigger(): JsonField<Trigger> = trigger

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
         * [BetaClearToolUses20250919Edit].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaClearToolUses20250919Edit]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("clear_tool_uses_20250919")
        private var clearAtLeast: JsonField<BetaInputTokensClearAtLeast> = JsonMissing.of()
        private var clearToolInputs: JsonField<ClearToolInputs> = JsonMissing.of()
        private var excludeTools: JsonField<MutableList<String>>? = null
        private var keep: JsonField<BetaToolUsesKeep> = JsonMissing.of()
        private var trigger: JsonField<Trigger> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaClearToolUses20250919Edit: BetaClearToolUses20250919Edit) = apply {
            type = betaClearToolUses20250919Edit.type
            clearAtLeast = betaClearToolUses20250919Edit.clearAtLeast
            clearToolInputs = betaClearToolUses20250919Edit.clearToolInputs
            excludeTools = betaClearToolUses20250919Edit.excludeTools.map { it.toMutableList() }
            keep = betaClearToolUses20250919Edit.keep
            trigger = betaClearToolUses20250919Edit.trigger
            additionalProperties = betaClearToolUses20250919Edit.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("clear_tool_uses_20250919")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Minimum number of tokens that must be cleared when triggered. Context will only be
         * modified if at least this many tokens can be removed.
         */
        fun clearAtLeast(clearAtLeast: BetaInputTokensClearAtLeast?) =
            clearAtLeast(JsonField.ofNullable(clearAtLeast))

        /** Alias for calling [Builder.clearAtLeast] with `clearAtLeast.orElse(null)`. */
        fun clearAtLeast(clearAtLeast: Optional<BetaInputTokensClearAtLeast>) =
            clearAtLeast(clearAtLeast.getOrNull())

        /**
         * Sets [Builder.clearAtLeast] to an arbitrary JSON value.
         *
         * You should usually call [Builder.clearAtLeast] with a well-typed
         * [BetaInputTokensClearAtLeast] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun clearAtLeast(clearAtLeast: JsonField<BetaInputTokensClearAtLeast>) = apply {
            this.clearAtLeast = clearAtLeast
        }

        /** Whether to clear all tool inputs (bool) or specific tool inputs to clear (list) */
        fun clearToolInputs(clearToolInputs: ClearToolInputs?) =
            clearToolInputs(JsonField.ofNullable(clearToolInputs))

        /** Alias for calling [Builder.clearToolInputs] with `clearToolInputs.orElse(null)`. */
        fun clearToolInputs(clearToolInputs: Optional<ClearToolInputs>) =
            clearToolInputs(clearToolInputs.getOrNull())

        /**
         * Sets [Builder.clearToolInputs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.clearToolInputs] with a well-typed [ClearToolInputs]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun clearToolInputs(clearToolInputs: JsonField<ClearToolInputs>) = apply {
            this.clearToolInputs = clearToolInputs
        }

        /** Alias for calling [clearToolInputs] with `ClearToolInputs.ofBool(bool)`. */
        fun clearToolInputs(bool: Boolean) = clearToolInputs(ClearToolInputs.ofBool(bool))

        /** Alias for calling [clearToolInputs] with `ClearToolInputs.ofStrings(strings)`. */
        fun clearToolInputsOfStrings(strings: List<String>) =
            clearToolInputs(ClearToolInputs.ofStrings(strings))

        /** Tool names whose uses are preserved from clearing */
        fun excludeTools(excludeTools: List<String>?) =
            excludeTools(JsonField.ofNullable(excludeTools))

        /** Alias for calling [Builder.excludeTools] with `excludeTools.orElse(null)`. */
        fun excludeTools(excludeTools: Optional<List<String>>) =
            excludeTools(excludeTools.getOrNull())

        /**
         * Sets [Builder.excludeTools] to an arbitrary JSON value.
         *
         * You should usually call [Builder.excludeTools] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun excludeTools(excludeTools: JsonField<List<String>>) = apply {
            this.excludeTools = excludeTools.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [excludeTools].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addExcludeTool(excludeTool: String) = apply {
            excludeTools =
                (excludeTools ?: JsonField.of(mutableListOf())).also {
                    checkKnown("excludeTools", it).add(excludeTool)
                }
        }

        /** Number of tool uses to retain in the conversation */
        fun keep(keep: BetaToolUsesKeep) = keep(JsonField.of(keep))

        /**
         * Sets [Builder.keep] to an arbitrary JSON value.
         *
         * You should usually call [Builder.keep] with a well-typed [BetaToolUsesKeep] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun keep(keep: JsonField<BetaToolUsesKeep>) = apply { this.keep = keep }

        /** Condition that triggers the context management strategy */
        fun trigger(trigger: Trigger) = trigger(JsonField.of(trigger))

        /**
         * Sets [Builder.trigger] to an arbitrary JSON value.
         *
         * You should usually call [Builder.trigger] with a well-typed [Trigger] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun trigger(trigger: JsonField<Trigger>) = apply { this.trigger = trigger }

        /** Alias for calling [trigger] with `Trigger.ofInputTokens(inputTokens)`. */
        fun trigger(inputTokens: BetaInputTokensTrigger) =
            trigger(Trigger.ofInputTokens(inputTokens))

        /**
         * Alias for calling [trigger] with the following:
         * ```java
         * BetaInputTokensTrigger.builder()
         *     .value(value)
         *     .build()
         * ```
         */
        fun inputTokensTrigger(value: Long) =
            trigger(BetaInputTokensTrigger.builder().value(value).build())

        /** Alias for calling [trigger] with `Trigger.ofToolUses(toolUses)`. */
        fun trigger(toolUses: BetaToolUsesTrigger) = trigger(Trigger.ofToolUses(toolUses))

        /**
         * Alias for calling [trigger] with the following:
         * ```java
         * BetaToolUsesTrigger.builder()
         *     .value(value)
         *     .build()
         * ```
         */
        fun toolUsesTrigger(value: Long) =
            trigger(BetaToolUsesTrigger.builder().value(value).build())

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
         * Returns an immutable instance of [BetaClearToolUses20250919Edit].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaClearToolUses20250919Edit =
            BetaClearToolUses20250919Edit(
                type,
                clearAtLeast,
                clearToolInputs,
                (excludeTools ?: JsonMissing.of()).map { it.toImmutable() },
                keep,
                trigger,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaClearToolUses20250919Edit = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("clear_tool_uses_20250919")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        clearAtLeast().ifPresent { it.validate() }
        clearToolInputs().ifPresent { it.validate() }
        excludeTools()
        keep().ifPresent { it.validate() }
        trigger().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("clear_tool_uses_20250919")) 1 else 0 } +
            (clearAtLeast.asKnown().getOrNull()?.validity() ?: 0) +
            (clearToolInputs.asKnown().getOrNull()?.validity() ?: 0) +
            (excludeTools.asKnown().getOrNull()?.size ?: 0) +
            (keep.asKnown().getOrNull()?.validity() ?: 0) +
            (trigger.asKnown().getOrNull()?.validity() ?: 0)

    /** Whether to clear all tool inputs (bool) or specific tool inputs to clear (list) */
    @JsonDeserialize(using = ClearToolInputs.Deserializer::class)
    @JsonSerialize(using = ClearToolInputs.Serializer::class)
    class ClearToolInputs
    private constructor(
        private val bool: Boolean? = null,
        private val strings: List<String>? = null,
        private val _json: JsonValue? = null,
    ) {

        fun bool(): Optional<Boolean> = Optional.ofNullable(bool)

        fun strings(): Optional<List<String>> = Optional.ofNullable(strings)

        fun isBool(): Boolean = bool != null

        fun isStrings(): Boolean = strings != null

        fun asBool(): Boolean = bool.getOrThrow("bool")

        fun asStrings(): List<String> = strings.getOrThrow("strings")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                bool != null -> visitor.visitBool(bool)
                strings != null -> visitor.visitStrings(strings)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): ClearToolInputs = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBool(bool: Boolean) {}

                    override fun visitStrings(strings: List<String>) {}
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
                    override fun visitBool(bool: Boolean) = 1

                    override fun visitStrings(strings: List<String>) = strings.size

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ClearToolInputs && bool == other.bool && strings == other.strings
        }

        override fun hashCode(): Int = Objects.hash(bool, strings)

        override fun toString(): String =
            when {
                bool != null -> "ClearToolInputs{bool=$bool}"
                strings != null -> "ClearToolInputs{strings=$strings}"
                _json != null -> "ClearToolInputs{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid ClearToolInputs")
            }

        companion object {

            @JvmStatic fun ofBool(bool: Boolean) = ClearToolInputs(bool = bool)

            @JvmStatic
            fun ofStrings(strings: List<String>) = ClearToolInputs(strings = strings.toImmutable())
        }

        /**
         * An interface that defines how to map each variant of [ClearToolInputs] to a value of type
         * [T].
         */
        interface Visitor<out T> {

            fun visitBool(bool: Boolean): T

            fun visitStrings(strings: List<String>): T

            /**
             * Maps an unknown variant of [ClearToolInputs] to a value of type [T].
             *
             * An instance of [ClearToolInputs] can contain an unknown variant if it was
             * deserialized from data that doesn't match any known variant. For example, if the SDK
             * is on an older version than the API, then the API may respond with new variants that
             * the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown ClearToolInputs: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<ClearToolInputs>(ClearToolInputs::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): ClearToolInputs {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<Boolean>())?.let {
                                ClearToolInputs(bool = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<List<String>>())?.let {
                                ClearToolInputs(strings = it, _json = json)
                            },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from string).
                    0 -> ClearToolInputs(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<ClearToolInputs>(ClearToolInputs::class) {

            override fun serialize(
                value: ClearToolInputs,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.bool != null -> generator.writeObject(value.bool)
                    value.strings != null -> generator.writeObject(value.strings)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid ClearToolInputs")
                }
            }
        }
    }

    /** Condition that triggers the context management strategy */
    @JsonDeserialize(using = Trigger.Deserializer::class)
    @JsonSerialize(using = Trigger.Serializer::class)
    class Trigger
    private constructor(
        private val inputTokens: BetaInputTokensTrigger? = null,
        private val toolUses: BetaToolUsesTrigger? = null,
        private val _json: JsonValue? = null,
    ) {

        fun inputTokens(): Optional<BetaInputTokensTrigger> = Optional.ofNullable(inputTokens)

        fun toolUses(): Optional<BetaToolUsesTrigger> = Optional.ofNullable(toolUses)

        fun isInputTokens(): Boolean = inputTokens != null

        fun isToolUses(): Boolean = toolUses != null

        fun asInputTokens(): BetaInputTokensTrigger = inputTokens.getOrThrow("inputTokens")

        fun asToolUses(): BetaToolUsesTrigger = toolUses.getOrThrow("toolUses")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                inputTokens != null -> visitor.visitInputTokens(inputTokens)
                toolUses != null -> visitor.visitToolUses(toolUses)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Trigger = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitInputTokens(inputTokens: BetaInputTokensTrigger) {
                        inputTokens.validate()
                    }

                    override fun visitToolUses(toolUses: BetaToolUsesTrigger) {
                        toolUses.validate()
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
                    override fun visitInputTokens(inputTokens: BetaInputTokensTrigger) =
                        inputTokens.validity()

                    override fun visitToolUses(toolUses: BetaToolUsesTrigger) = toolUses.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Trigger &&
                inputTokens == other.inputTokens &&
                toolUses == other.toolUses
        }

        override fun hashCode(): Int = Objects.hash(inputTokens, toolUses)

        override fun toString(): String =
            when {
                inputTokens != null -> "Trigger{inputTokens=$inputTokens}"
                toolUses != null -> "Trigger{toolUses=$toolUses}"
                _json != null -> "Trigger{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Trigger")
            }

        companion object {

            @JvmStatic
            fun ofInputTokens(inputTokens: BetaInputTokensTrigger) =
                Trigger(inputTokens = inputTokens)

            @JvmStatic fun ofToolUses(toolUses: BetaToolUsesTrigger) = Trigger(toolUses = toolUses)
        }

        /**
         * An interface that defines how to map each variant of [Trigger] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitInputTokens(inputTokens: BetaInputTokensTrigger): T

            fun visitToolUses(toolUses: BetaToolUsesTrigger): T

            /**
             * Maps an unknown variant of [Trigger] to a value of type [T].
             *
             * An instance of [Trigger] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Trigger: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Trigger>(Trigger::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Trigger {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "input_tokens" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaInputTokensTrigger>())?.let {
                            Trigger(inputTokens = it, _json = json)
                        } ?: Trigger(_json = json)
                    }
                    "tool_uses" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaToolUsesTrigger>())?.let {
                            Trigger(toolUses = it, _json = json)
                        } ?: Trigger(_json = json)
                    }
                }

                return Trigger(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Trigger>(Trigger::class) {

            override fun serialize(
                value: Trigger,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.inputTokens != null -> generator.writeObject(value.inputTokens)
                    value.toolUses != null -> generator.writeObject(value.toolUses)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Trigger")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClearToolUses20250919Edit &&
            type == other.type &&
            clearAtLeast == other.clearAtLeast &&
            clearToolInputs == other.clearToolInputs &&
            excludeTools == other.excludeTools &&
            keep == other.keep &&
            trigger == other.trigger &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            type,
            clearAtLeast,
            clearToolInputs,
            excludeTools,
            keep,
            trigger,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaClearToolUses20250919Edit{type=$type, clearAtLeast=$clearAtLeast, clearToolInputs=$clearToolInputs, excludeTools=$excludeTools, keep=$keep, trigger=$trigger, additionalProperties=$additionalProperties}"
}
