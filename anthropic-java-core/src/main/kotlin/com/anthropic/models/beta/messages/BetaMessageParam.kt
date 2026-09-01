// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
import com.anthropic.core.checkRequired
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

class BetaMessageParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val content: JsonField<Content>,
    private val role: JsonField<Role>,
    private val clearAt: JsonField<ClearAt>,
    private val outputConfig: JsonField<BetaSystemMessageOutputConfig>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content") @ExcludeMissing content: JsonField<Content> = JsonMissing.of(),
        @JsonProperty("role") @ExcludeMissing role: JsonField<Role> = JsonMissing.of(),
        @JsonProperty("clear_at") @ExcludeMissing clearAt: JsonField<ClearAt> = JsonMissing.of(),
        @JsonProperty("output_config")
        @ExcludeMissing
        outputConfig: JsonField<BetaSystemMessageOutputConfig> = JsonMissing.of(),
    ) : this(content, role, clearAt, outputConfig, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): Content = content.getRequired("content")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun role(): Role = role.getRequired("role")

    /**
     * How long this system message's text stays in front of the model. `"never"` (the default)
     * renders it on every request that includes it. `"next_user_message"` renders it only for the
     * user turn it follows: once a later `role: "user"` message exists in `messages` the message
     * stays in the array (send it unchanged) but is no longer shown to the model. Only permitted on
     * `role: "system"` messages.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun clearAt(): Optional<ClearAt> = clearAt.getOptional("clear_at")

    /**
     * Per-message output configuration on a role:"system" input message.
     *
     * Fields here apply per-turn; ``format`` remains top-level only. An empty ``{}`` is accepted on
     * a message that carries content; a message with neither content nor output_config fields is
     * rejected.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun outputConfig(): Optional<BetaSystemMessageOutputConfig> =
        outputConfig.getOptional("output_config")

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<Content> = content

    /**
     * Returns the raw JSON value of [role].
     *
     * Unlike [role], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("role") @ExcludeMissing fun _role(): JsonField<Role> = role

    /**
     * Returns the raw JSON value of [clearAt].
     *
     * Unlike [clearAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("clear_at") @ExcludeMissing fun _clearAt(): JsonField<ClearAt> = clearAt

    /**
     * Returns the raw JSON value of [outputConfig].
     *
     * Unlike [outputConfig], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("output_config")
    @ExcludeMissing
    fun _outputConfig(): JsonField<BetaSystemMessageOutputConfig> = outputConfig

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
         * Returns a mutable builder for constructing an instance of [BetaMessageParam].
         *
         * The following fields are required:
         * ```java
         * .content()
         * .role()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMessageParam]. */
    class Builder internal constructor() {

        private var content: JsonField<Content>? = null
        private var role: JsonField<Role>? = null
        private var clearAt: JsonField<ClearAt> = JsonMissing.of()
        private var outputConfig: JsonField<BetaSystemMessageOutputConfig> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMessageParam: BetaMessageParam) = apply {
            content = betaMessageParam.content
            role = betaMessageParam.role
            clearAt = betaMessageParam.clearAt
            outputConfig = betaMessageParam.outputConfig
            additionalProperties = betaMessageParam.additionalProperties.toMutableMap()
        }

        fun content(content: Content) = content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed [Content] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun content(content: JsonField<Content>) = apply { this.content = content }

        /** Alias for calling [content] with `Content.ofString(string)`. */
        fun content(string: String) = content(Content.ofString(string))

        /**
         * Alias for calling [content] with
         * `Content.ofBetaContentBlockParams(betaContentBlockParams)`.
         */
        fun contentOfBetaContentBlockParams(betaContentBlockParams: List<BetaContentBlockParam>) =
            content(Content.ofBetaContentBlockParams(betaContentBlockParams))

        fun role(role: Role) = role(JsonField.of(role))

        /**
         * Sets [Builder.role] to an arbitrary JSON value.
         *
         * You should usually call [Builder.role] with a well-typed [Role] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun role(role: JsonField<Role>) = apply { this.role = role }

        /**
         * How long this system message's text stays in front of the model. `"never"` (the default)
         * renders it on every request that includes it. `"next_user_message"` renders it only for
         * the user turn it follows: once a later `role: "user"` message exists in `messages` the
         * message stays in the array (send it unchanged) but is no longer shown to the model. Only
         * permitted on `role: "system"` messages.
         */
        fun clearAt(clearAt: ClearAt?) = clearAt(JsonField.ofNullable(clearAt))

        /** Alias for calling [Builder.clearAt] with `clearAt.orElse(null)`. */
        fun clearAt(clearAt: Optional<ClearAt>) = clearAt(clearAt.getOrNull())

        /**
         * Sets [Builder.clearAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.clearAt] with a well-typed [ClearAt] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun clearAt(clearAt: JsonField<ClearAt>) = apply { this.clearAt = clearAt }

        /**
         * Per-message output configuration on a role:"system" input message.
         *
         * Fields here apply per-turn; ``format`` remains top-level only. An empty ``{}`` is
         * accepted on a message that carries content; a message with neither content nor
         * output_config fields is rejected.
         */
        fun outputConfig(outputConfig: BetaSystemMessageOutputConfig?) =
            outputConfig(JsonField.ofNullable(outputConfig))

        /** Alias for calling [Builder.outputConfig] with `outputConfig.orElse(null)`. */
        fun outputConfig(outputConfig: Optional<BetaSystemMessageOutputConfig>) =
            outputConfig(outputConfig.getOrNull())

        /**
         * Sets [Builder.outputConfig] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outputConfig] with a well-typed
         * [BetaSystemMessageOutputConfig] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun outputConfig(outputConfig: JsonField<BetaSystemMessageOutputConfig>) = apply {
            this.outputConfig = outputConfig
        }

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
         * Returns an immutable instance of [BetaMessageParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * .role()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaMessageParam =
            BetaMessageParam(
                checkRequired("content", content),
                checkRequired("role", role),
                clearAt,
                outputConfig,
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
    fun validate(): BetaMessageParam = apply {
        if (validated) {
            return@apply
        }

        content().validate()
        role().validate()
        clearAt().ifPresent { it.validate() }
        outputConfig().ifPresent { it.validate() }
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
        (content.asKnown().getOrNull()?.validity() ?: 0) +
            (role.asKnown().getOrNull()?.validity() ?: 0) +
            (clearAt.asKnown().getOrNull()?.validity() ?: 0) +
            (outputConfig.asKnown().getOrNull()?.validity() ?: 0)

    @JsonDeserialize(using = Content.Deserializer::class)
    @JsonSerialize(using = Content.Serializer::class)
    class Content
    private constructor(
        private val string: String? = null,
        private val betaContentBlockParams: List<BetaContentBlockParam>? = null,
        private val _json: JsonValue? = null,
    ) {

        fun string(): Optional<String> = Optional.ofNullable(string)

        fun betaContentBlockParams(): Optional<List<BetaContentBlockParam>> =
            Optional.ofNullable(betaContentBlockParams)

        fun isString(): Boolean = string != null

        fun isBetaContentBlockParams(): Boolean = betaContentBlockParams != null

        fun asString(): String = string.getOrThrow("string")

        fun asBetaContentBlockParams(): List<BetaContentBlockParam> =
            betaContentBlockParams.getOrThrow("betaContentBlockParams")

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
         * Optional<String> result = content.accept(new Content.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitString(String string) {
         *         return Optional.of(string.toString());
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
                string != null -> visitor.visitString(string)
                betaContentBlockParams != null ->
                    visitor.visitBetaContentBlockParams(betaContentBlockParams)
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
        fun validate(): Content = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitString(string: String) {}

                    override fun visitBetaContentBlockParams(
                        betaContentBlockParams: List<BetaContentBlockParam>
                    ) {
                        betaContentBlockParams.forEach { it.validate() }
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
                    override fun visitString(string: String) = 1

                    override fun visitBetaContentBlockParams(
                        betaContentBlockParams: List<BetaContentBlockParam>
                    ) = betaContentBlockParams.sumOf { it.validity().toInt() }

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Content &&
                string == other.string &&
                betaContentBlockParams == other.betaContentBlockParams
        }

        override fun hashCode(): Int = Objects.hash(string, betaContentBlockParams)

        override fun toString(): String =
            when {
                string != null -> "Content{string=$string}"
                betaContentBlockParams != null ->
                    "Content{betaContentBlockParams=$betaContentBlockParams}"
                _json != null -> "Content{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Content")
            }

        companion object {

            @JvmStatic fun ofString(string: String) = Content(string = string)

            @JvmStatic
            fun ofBetaContentBlockParams(betaContentBlockParams: List<BetaContentBlockParam>) =
                Content(betaContentBlockParams = betaContentBlockParams.toImmutable())
        }

        /**
         * An interface that defines how to map each variant of [Content] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitString(string: String): T

            fun visitBetaContentBlockParams(betaContentBlockParams: List<BetaContentBlockParam>): T

            /**
             * Maps an unknown variant of [Content] to a value of type [T].
             *
             * An instance of [Content] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Content: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Content>(Content::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Content {
                val json = JsonValue.fromJsonNode(node)

                val bestMatches =
                    sequenceOf(
                            tryDeserialize(node, jacksonTypeRef<String>())?.let {
                                Content(string = it, _json = json)
                            },
                            tryDeserialize(node, jacksonTypeRef<List<BetaContentBlockParam>>())
                                ?.let { Content(betaContentBlockParams = it, _json = json) },
                        )
                        .filterNotNull()
                        .allMaxBy { it.validity() }
                        .toList()
                return when (bestMatches.size) {
                    // This can happen if what we're deserializing is completely incompatible with
                    // all the possible variants (e.g. deserializing from boolean).
                    0 -> Content(_json = json)
                    1 -> bestMatches.single()
                    // If there's more than one match with the highest validity, then use the first
                    // completely valid match, or simply the first match if none are completely
                    // valid.
                    else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
                }
            }
        }

        internal class Serializer : BaseSerializer<Content>(Content::class) {

            override fun serialize(
                value: Content,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.string != null -> generator.writeObject(value.string)
                    value.betaContentBlockParams != null ->
                        generator.writeObject(value.betaContentBlockParams)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Content")
                }
            }
        }
    }

    class Role @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val USER = of("user")

            @JvmField val ASSISTANT = of("assistant")

            @JvmField val SYSTEM = of("system")

            @JvmStatic fun of(value: String) = Role(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Role =
                value.asString().getOrNull()?.let { of(it) } ?: Role(value)
        }

        /** An enum containing [Role]'s known values. */
        enum class Known {
            USER,
            ASSISTANT,
            SYSTEM,
        }

        /**
         * An enum containing [Role]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Role] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            USER,
            ASSISTANT,
            SYSTEM,
            /** An enum member indicating that [Role] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                USER -> Value.USER
                ASSISTANT -> Value.ASSISTANT
                SYSTEM -> Value.SYSTEM
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                USER -> Known.USER
                ASSISTANT -> Known.ASSISTANT
                SYSTEM -> Known.SYSTEM
                else -> throw AnthropicInvalidDataException("Unknown Role: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
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
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Role = apply {
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

            return other is Role && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /**
     * How long this system message's text stays in front of the model. `"never"` (the default)
     * renders it on every request that includes it. `"next_user_message"` renders it only for the
     * user turn it follows: once a later `role: "user"` message exists in `messages` the message
     * stays in the array (send it unchanged) but is no longer shown to the model. Only permitted on
     * `role: "system"` messages.
     */
    class ClearAt @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val NEXT_USER_MESSAGE = of("next_user_message")

            @JvmField val NEVER = of("never")

            @JvmStatic fun of(value: String) = ClearAt(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): ClearAt =
                value.asString().getOrNull()?.let { of(it) } ?: ClearAt(value)
        }

        /** An enum containing [ClearAt]'s known values. */
        enum class Known {
            NEXT_USER_MESSAGE,
            NEVER,
        }

        /**
         * An enum containing [ClearAt]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [ClearAt] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            NEXT_USER_MESSAGE,
            NEVER,
            /** An enum member indicating that [ClearAt] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                NEXT_USER_MESSAGE -> Value.NEXT_USER_MESSAGE
                NEVER -> Value.NEVER
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                NEXT_USER_MESSAGE -> Known.NEXT_USER_MESSAGE
                NEVER -> Known.NEVER
                else -> throw AnthropicInvalidDataException("Unknown ClearAt: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
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
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): ClearAt = apply {
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

            return other is ClearAt && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMessageParam &&
            content == other.content &&
            role == other.role &&
            clearAt == other.clearAt &&
            outputConfig == other.outputConfig &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(content, role, clearAt, outputConfig, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMessageParam{content=$content, role=$role, clearAt=$clearAt, outputConfig=$outputConfig, additionalProperties=$additionalProperties}"
}
