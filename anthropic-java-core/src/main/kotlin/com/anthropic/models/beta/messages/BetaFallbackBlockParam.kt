// File generated from our OpenAPI spec by Stainless.

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

/**
 * A `fallback` block echoed back from a prior response.
 *
 * Accepted in `messages[].content` and not rendered into the prompt; not validated against the
 * request's `fallbacks` chain or top-level `model`.
 *
 * Echo the assistant turn back verbatim, including this block in its original position. The block
 * marks the boundary between content produced before and after a fallback hop, and the server
 * relies on that boundary to validate the turn: when thinking runs flank the boundary, omitting the
 * block merges them into one span the server cannot validate (the request is rejected), and moving
 * it into the middle of a single run is likewise rejected; between non-thinking blocks the block's
 * placement has no validation effect.
 */
class BetaFallbackBlockParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val from: JsonField<BetaFallbackInfoParam>,
    private val to: JsonField<BetaFallbackInfoParam>,
    private val type: JsonValue,
    private val trigger: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("from")
        @ExcludeMissing
        from: JsonField<BetaFallbackInfoParam> = JsonMissing.of(),
        @JsonProperty("to") @ExcludeMissing to: JsonField<BetaFallbackInfoParam> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("trigger") @ExcludeMissing trigger: JsonValue = JsonMissing.of(),
    ) : this(from, to, type, trigger, mutableMapOf())

    /**
     * Identifies one hop of a fallback transition.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun from(): BetaFallbackInfoParam = from.getRequired("from")

    /**
     * Identifies one hop of a fallback transition.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun to(): BetaFallbackInfoParam = to.getRequired("to")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("fallback")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * The response block's `trigger`, echoed verbatim. Accepted and ignored by the server; any
     * object or `null` is allowed.
     *
     * This arbitrary value can be deserialized into a custom type using the `convert` method:
     * ```java
     * MyClass myObject = betaFallbackBlockParam.trigger().convert(MyClass.class);
     * ```
     */
    @JsonProperty("trigger") @ExcludeMissing fun _trigger(): JsonValue = trigger

    /**
     * Returns the raw JSON value of [from].
     *
     * Unlike [from], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("from") @ExcludeMissing fun _from(): JsonField<BetaFallbackInfoParam> = from

    /**
     * Returns the raw JSON value of [to].
     *
     * Unlike [to], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("to") @ExcludeMissing fun _to(): JsonField<BetaFallbackInfoParam> = to

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
         * Returns a mutable builder for constructing an instance of [BetaFallbackBlockParam].
         *
         * The following fields are required:
         * ```java
         * .from()
         * .to()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaFallbackBlockParam]. */
    class Builder internal constructor() {

        private var from: JsonField<BetaFallbackInfoParam>? = null
        private var to: JsonField<BetaFallbackInfoParam>? = null
        private var type: JsonValue = JsonValue.from("fallback")
        private var trigger: JsonValue = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaFallbackBlockParam: BetaFallbackBlockParam) = apply {
            from = betaFallbackBlockParam.from
            to = betaFallbackBlockParam.to
            type = betaFallbackBlockParam.type
            trigger = betaFallbackBlockParam.trigger
            additionalProperties = betaFallbackBlockParam.additionalProperties.toMutableMap()
        }

        /** Identifies one hop of a fallback transition. */
        fun from(from: BetaFallbackInfoParam) = from(JsonField.of(from))

        /**
         * Sets [Builder.from] to an arbitrary JSON value.
         *
         * You should usually call [Builder.from] with a well-typed [BetaFallbackInfoParam] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun from(from: JsonField<BetaFallbackInfoParam>) = apply { this.from = from }

        /** Alias for calling [Builder.from] with `from.toParam()`. */
        fun from(from: BetaFallbackInfo) = from(from.toParam())

        /** Identifies one hop of a fallback transition. */
        fun to(to: BetaFallbackInfoParam) = to(JsonField.of(to))

        /**
         * Sets [Builder.to] to an arbitrary JSON value.
         *
         * You should usually call [Builder.to] with a well-typed [BetaFallbackInfoParam] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun to(to: JsonField<BetaFallbackInfoParam>) = apply { this.to = to }

        /** Alias for calling [Builder.to] with `to.toParam()`. */
        fun to(to: BetaFallbackInfo) = to(to.toParam())

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("fallback")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * The response block's `trigger`, echoed verbatim. Accepted and ignored by the server; any
         * object or `null` is allowed.
         */
        fun trigger(trigger: JsonValue) = apply { this.trigger = trigger }

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
         * Returns an immutable instance of [BetaFallbackBlockParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .from()
         * .to()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaFallbackBlockParam =
            BetaFallbackBlockParam(
                checkRequired("from", from),
                checkRequired("to", to),
                type,
                trigger,
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
    fun validate(): BetaFallbackBlockParam = apply {
        if (validated) {
            return@apply
        }

        from().validate()
        to().validate()
        _type().let {
            if (it != JsonValue.from("fallback")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
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
        (from.asKnown().getOrNull()?.validity() ?: 0) +
            (to.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("fallback")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaFallbackBlockParam &&
            from == other.from &&
            to == other.to &&
            type == other.type &&
            trigger == other.trigger &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(from, to, type, trigger, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaFallbackBlockParam{from=$from, to=$to, type=$type, trigger=$trigger, additionalProperties=$additionalProperties}"
}
