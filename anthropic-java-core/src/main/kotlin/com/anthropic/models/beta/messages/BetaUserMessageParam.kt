package com.anthropic.models.beta.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

class BetaUserMessageParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val content: JsonField<BetaMessageContentParam>,
    private val role: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content")
        @ExcludeMissing
        content: JsonField<BetaMessageContentParam> = JsonMissing.of(),
        @JsonProperty("role") @ExcludeMissing role: JsonValue = JsonMissing.of(),
    ) : this(content, role, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): BetaMessageContentParam = content.getRequired("content")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("user")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("role") @ExcludeMissing fun _role(): JsonValue = role

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content")
    @ExcludeMissing
    fun _content(): JsonField<BetaMessageContentParam> = content

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
         * Returns a mutable builder for constructing an instance of [BetaUserMessageParam].
         *
         * The following fields are required:
         * ```java
         * .content()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaUserMessageParam] with the required [content] set
         * to the given value.
         */
        @JvmStatic fun of(content: BetaMessageContentParam) = builder().content(content).build()

        /** Alias for calling [of] with `BetaMessageContentParam.ofString(string)`. */
        @JvmStatic fun of(string: String) = of(BetaMessageContentParam.ofString(string))

        /**
         * Alias for calling [of] with
         * `BetaMessageContentParam.ofContentBlockParams(contentBlockParams)`.
         */
        @JvmStatic
        fun ofContentBlockParams(contentBlockParams: List<BetaContentBlockParam>) =
            of(BetaMessageContentParam.ofContentBlockParams(contentBlockParams))
    }

    /** A builder for [BetaUserMessageParam]. */
    class Builder internal constructor() {

        private var content: JsonField<BetaMessageContentParam>? = null
        private var role: JsonValue = JsonValue.from("user")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaUserMessageParam: BetaUserMessageParam) = apply {
            content = betaUserMessageParam.content
            role = betaUserMessageParam.role
            additionalProperties = betaUserMessageParam.additionalProperties.toMutableMap()
        }

        fun content(content: BetaMessageContentParam) = content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed [BetaMessageContentParam]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun content(content: JsonField<BetaMessageContentParam>) = apply { this.content = content }

        /** Alias for calling [content] with `BetaMessageContentParam.ofString(string)`. */
        fun content(string: String) = content(BetaMessageContentParam.ofString(string))

        /**
         * Alias for calling [content] with
         * `BetaMessageContentParam.ofContentBlockParams(contentBlockParams)`.
         */
        fun contentOfContentBlockParams(contentBlockParams: List<BetaContentBlockParam>) =
            content(BetaMessageContentParam.ofContentBlockParams(contentBlockParams))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("user")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun role(role: JsonValue) = apply { this.role = role }

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
         * Returns an immutable instance of [BetaUserMessageParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaUserMessageParam =
            BetaUserMessageParam(
                checkRequired("content", content),
                role,
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
    fun validate(): BetaUserMessageParam = apply {
        if (validated) {
            return@apply
        }

        content().validate()
        _role().let {
            if (it != JsonValue.from("user")) {
                throw AnthropicInvalidDataException("'role' is invalid, received $it")
            }
        }
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
            role.let { if (it == JsonValue.from("user")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaUserMessageParam &&
            content == other.content &&
            role == other.role &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(content, role, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaUserMessageParam{content=$content, role=$role, additionalProperties=$additionalProperties}"
}
