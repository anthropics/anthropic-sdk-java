// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.Enum
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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Structured information about a refusal. */
class BetaRefusalStopDetails
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val category: JsonField<Category>,
    private val explanation: JsonField<String>,
    private val fallbackCreditToken: JsonField<String>,
    private val fallbackHasPrefillClaim: JsonField<Boolean>,
    private val recommendedModel: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("category") @ExcludeMissing category: JsonField<Category> = JsonMissing.of(),
        @JsonProperty("explanation")
        @ExcludeMissing
        explanation: JsonField<String> = JsonMissing.of(),
        @JsonProperty("fallback_credit_token")
        @ExcludeMissing
        fallbackCreditToken: JsonField<String> = JsonMissing.of(),
        @JsonProperty("fallback_has_prefill_claim")
        @ExcludeMissing
        fallbackHasPrefillClaim: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("recommended_model")
        @ExcludeMissing
        recommendedModel: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        category,
        explanation,
        fallbackCreditToken,
        fallbackHasPrefillClaim,
        recommendedModel,
        type,
        mutableMapOf(),
    )

    /**
     * The policy category that triggered a refusal.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun category(): Optional<Category> = category.getOptional("category")

    /**
     * Human-readable explanation of the refusal.
     *
     * This text is not guaranteed to be stable. `null` when no explanation is available for the
     * category.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun explanation(): Optional<String> = explanation.getOptional("explanation")

    /**
     * Opaque code that refunds the cache-miss cost when retrying this refused request on the
     * fallback model. Pass it as `fallback_credit_token` on the retry request. Expires 5 minutes
     * after the refusal.
     *
     * The retry is sent either with the same request body (`system`, `messages`, `tools`, and other
     * render-shaping fields), or with the same body plus one appended `assistant` message whose
     * content is the partial text (with any trailing whitespace stripped from the final text block)
     * and paired server-tool blocks from this refusal — which also authorizes that appended turn as
     * an assistant-prefill continuation on models that otherwise disallow prefill. A token minted
     * mid-server-tool-loop whose partial content was continuable may only be redeemed the second
     * way — if a same-body retry is rejected with a 400 saying the token must be redeemed by
     * continuing the partial response, retry the second way instead. Either way: same workspace,
     * same platform; a mismatch is a 400. Resending a token for an already-warm prefix is permitted
     * but yields no additional credit.
     *
     * `null` when the refused model isn't eligible for a fallback credit.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun fallbackCreditToken(): Optional<String> =
        fallbackCreditToken.getOptional("fallback_credit_token")

    /**
     * Whether the accompanying `fallback_credit_token` may be redeemed with the appended-assistant
     * retry form. Only set when `fallback_credit_token` is present.
     *
     * `true`: retry by resending the same request body plus one appended `assistant` message whose
     * content is this response's `content` with any trailing whitespace stripped from the final
     * text block and unpaired `tool_use` blocks omitted (the same appended-turn shape described on
     * `fallback_credit_token`), with the token attached. `false`: retry by resending the original
     * request body unchanged, with the token attached — the appended-assistant form is not
     * available for this refusal (no continuable partial content, or the request uses
     * `output_format` or a `tool_choice` that forces tool use). One exception: when the request
     * used `output_format` or a forced `tool_choice` and the refusal arrived after server tools
     * (including MCP connector tools) had already executed, the token may not be redeemable by
     * either retry form; if the exact-body retry is then rejected with a 400 saying the token must
     * be redeemed by continuing the partial response, discard the token and retry without it.
     *
     * Advisory: if an appended-assistant retry is rejected with a 400 despite `true`, fall back to
     * resending the original request body with the token.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun fallbackHasPrefillClaim(): Optional<Boolean> =
        fallbackHasPrefillClaim.getOptional("fallback_has_prefill_claim")

    /**
     * The server's suggested retry target for this refusal. Populated when a fallback attempt could
     * not be made (the fallback model's rate limit was exhausted, or it was overloaded); names the
     * fallback model the caller can retry directly. Null otherwise.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun recommendedModel(): Optional<String> = recommendedModel.getOptional("recommended_model")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("refusal")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [category].
     *
     * Unlike [category], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("category") @ExcludeMissing fun _category(): JsonField<Category> = category

    /**
     * Returns the raw JSON value of [explanation].
     *
     * Unlike [explanation], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("explanation") @ExcludeMissing fun _explanation(): JsonField<String> = explanation

    /**
     * Returns the raw JSON value of [fallbackCreditToken].
     *
     * Unlike [fallbackCreditToken], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("fallback_credit_token")
    @ExcludeMissing
    fun _fallbackCreditToken(): JsonField<String> = fallbackCreditToken

    /**
     * Returns the raw JSON value of [fallbackHasPrefillClaim].
     *
     * Unlike [fallbackHasPrefillClaim], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("fallback_has_prefill_claim")
    @ExcludeMissing
    fun _fallbackHasPrefillClaim(): JsonField<Boolean> = fallbackHasPrefillClaim

    /**
     * Returns the raw JSON value of [recommendedModel].
     *
     * Unlike [recommendedModel], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("recommended_model")
    @ExcludeMissing
    fun _recommendedModel(): JsonField<String> = recommendedModel

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
         * Returns a mutable builder for constructing an instance of [BetaRefusalStopDetails].
         *
         * The following fields are required:
         * ```java
         * .category()
         * .explanation()
         * .fallbackCreditToken()
         * .fallbackHasPrefillClaim()
         * .recommendedModel()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRefusalStopDetails]. */
    class Builder internal constructor() {

        private var category: JsonField<Category>? = null
        private var explanation: JsonField<String>? = null
        private var fallbackCreditToken: JsonField<String>? = null
        private var fallbackHasPrefillClaim: JsonField<Boolean>? = null
        private var recommendedModel: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("refusal")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaRefusalStopDetails: BetaRefusalStopDetails) = apply {
            category = betaRefusalStopDetails.category
            explanation = betaRefusalStopDetails.explanation
            fallbackCreditToken = betaRefusalStopDetails.fallbackCreditToken
            fallbackHasPrefillClaim = betaRefusalStopDetails.fallbackHasPrefillClaim
            recommendedModel = betaRefusalStopDetails.recommendedModel
            type = betaRefusalStopDetails.type
            additionalProperties = betaRefusalStopDetails.additionalProperties.toMutableMap()
        }

        /** The policy category that triggered a refusal. */
        fun category(category: Category?) = category(JsonField.ofNullable(category))

        /** Alias for calling [Builder.category] with `category.orElse(null)`. */
        fun category(category: Optional<Category>) = category(category.getOrNull())

        /**
         * Sets [Builder.category] to an arbitrary JSON value.
         *
         * You should usually call [Builder.category] with a well-typed [Category] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun category(category: JsonField<Category>) = apply { this.category = category }

        /**
         * Human-readable explanation of the refusal.
         *
         * This text is not guaranteed to be stable. `null` when no explanation is available for the
         * category.
         */
        fun explanation(explanation: String?) = explanation(JsonField.ofNullable(explanation))

        /** Alias for calling [Builder.explanation] with `explanation.orElse(null)`. */
        fun explanation(explanation: Optional<String>) = explanation(explanation.getOrNull())

        /**
         * Sets [Builder.explanation] to an arbitrary JSON value.
         *
         * You should usually call [Builder.explanation] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun explanation(explanation: JsonField<String>) = apply { this.explanation = explanation }

        /**
         * Opaque code that refunds the cache-miss cost when retrying this refused request on the
         * fallback model. Pass it as `fallback_credit_token` on the retry request. Expires 5
         * minutes after the refusal.
         *
         * The retry is sent either with the same request body (`system`, `messages`, `tools`, and
         * other render-shaping fields), or with the same body plus one appended `assistant` message
         * whose content is the partial text (with any trailing whitespace stripped from the final
         * text block) and paired server-tool blocks from this refusal — which also authorizes that
         * appended turn as an assistant-prefill continuation on models that otherwise disallow
         * prefill. A token minted mid-server-tool-loop whose partial content was continuable may
         * only be redeemed the second way — if a same-body retry is rejected with a 400 saying the
         * token must be redeemed by continuing the partial response, retry the second way instead.
         * Either way: same workspace, same platform; a mismatch is a 400. Resending a token for an
         * already-warm prefix is permitted but yields no additional credit.
         *
         * `null` when the refused model isn't eligible for a fallback credit.
         */
        fun fallbackCreditToken(fallbackCreditToken: String?) =
            fallbackCreditToken(JsonField.ofNullable(fallbackCreditToken))

        /**
         * Alias for calling [Builder.fallbackCreditToken] with `fallbackCreditToken.orElse(null)`.
         */
        fun fallbackCreditToken(fallbackCreditToken: Optional<String>) =
            fallbackCreditToken(fallbackCreditToken.getOrNull())

        /**
         * Sets [Builder.fallbackCreditToken] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fallbackCreditToken] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun fallbackCreditToken(fallbackCreditToken: JsonField<String>) = apply {
            this.fallbackCreditToken = fallbackCreditToken
        }

        /**
         * Whether the accompanying `fallback_credit_token` may be redeemed with the
         * appended-assistant retry form. Only set when `fallback_credit_token` is present.
         *
         * `true`: retry by resending the same request body plus one appended `assistant` message
         * whose content is this response's `content` with any trailing whitespace stripped from the
         * final text block and unpaired `tool_use` blocks omitted (the same appended-turn shape
         * described on `fallback_credit_token`), with the token attached. `false`: retry by
         * resending the original request body unchanged, with the token attached — the
         * appended-assistant form is not available for this refusal (no continuable partial
         * content, or the request uses `output_format` or a `tool_choice` that forces tool use).
         * One exception: when the request used `output_format` or a forced `tool_choice` and the
         * refusal arrived after server tools (including MCP connector tools) had already executed,
         * the token may not be redeemable by either retry form; if the exact-body retry is then
         * rejected with a 400 saying the token must be redeemed by continuing the partial response,
         * discard the token and retry without it.
         *
         * Advisory: if an appended-assistant retry is rejected with a 400 despite `true`, fall back
         * to resending the original request body with the token.
         */
        fun fallbackHasPrefillClaim(fallbackHasPrefillClaim: Boolean?) =
            fallbackHasPrefillClaim(JsonField.ofNullable(fallbackHasPrefillClaim))

        /**
         * Alias for [Builder.fallbackHasPrefillClaim].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun fallbackHasPrefillClaim(fallbackHasPrefillClaim: Boolean) =
            fallbackHasPrefillClaim(fallbackHasPrefillClaim as Boolean?)

        /**
         * Alias for calling [Builder.fallbackHasPrefillClaim] with
         * `fallbackHasPrefillClaim.orElse(null)`.
         */
        fun fallbackHasPrefillClaim(fallbackHasPrefillClaim: Optional<Boolean>) =
            fallbackHasPrefillClaim(fallbackHasPrefillClaim.getOrNull())

        /**
         * Sets [Builder.fallbackHasPrefillClaim] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fallbackHasPrefillClaim] with a well-typed [Boolean]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun fallbackHasPrefillClaim(fallbackHasPrefillClaim: JsonField<Boolean>) = apply {
            this.fallbackHasPrefillClaim = fallbackHasPrefillClaim
        }

        /**
         * The server's suggested retry target for this refusal. Populated when a fallback attempt
         * could not be made (the fallback model's rate limit was exhausted, or it was overloaded);
         * names the fallback model the caller can retry directly. Null otherwise.
         */
        fun recommendedModel(recommendedModel: String?) =
            recommendedModel(JsonField.ofNullable(recommendedModel))

        /** Alias for calling [Builder.recommendedModel] with `recommendedModel.orElse(null)`. */
        fun recommendedModel(recommendedModel: Optional<String>) =
            recommendedModel(recommendedModel.getOrNull())

        /**
         * Sets [Builder.recommendedModel] to an arbitrary JSON value.
         *
         * You should usually call [Builder.recommendedModel] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun recommendedModel(recommendedModel: JsonField<String>) = apply {
            this.recommendedModel = recommendedModel
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("refusal")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaRefusalStopDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .category()
         * .explanation()
         * .fallbackCreditToken()
         * .fallbackHasPrefillClaim()
         * .recommendedModel()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaRefusalStopDetails =
            BetaRefusalStopDetails(
                checkRequired("category", category),
                checkRequired("explanation", explanation),
                checkRequired("fallbackCreditToken", fallbackCreditToken),
                checkRequired("fallbackHasPrefillClaim", fallbackHasPrefillClaim),
                checkRequired("recommendedModel", recommendedModel),
                type,
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
    fun validate(): BetaRefusalStopDetails = apply {
        if (validated) {
            return@apply
        }

        category().ifPresent { it.validate() }
        explanation()
        fallbackCreditToken()
        fallbackHasPrefillClaim()
        recommendedModel()
        _type().let {
            if (it != JsonValue.from("refusal")) {
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
        (category.asKnown().getOrNull()?.validity() ?: 0) +
            (if (explanation.asKnown().isPresent) 1 else 0) +
            (if (fallbackCreditToken.asKnown().isPresent) 1 else 0) +
            (if (fallbackHasPrefillClaim.asKnown().isPresent) 1 else 0) +
            (if (recommendedModel.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("refusal")) 1 else 0 }

    /** The policy category that triggered a refusal. */
    class Category @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            /**
             * The request could enable cyber harm, such as malware or exploit development. Benign
             * cybersecurity work can also trigger this category.
             */
            @JvmField val CYBER = of("cyber")

            /**
             * The request could enable biological harm, such as dangerous lab methods. Beneficial
             * life sciences work can also trigger this category.
             */
            @JvmField val BIO = of("bio")

            /**
             * The request could assist the development of competing AI models, which is restricted
             * under
             * [Anthropic's commercial terms](https://www.anthropic.com/legal/commercial-terms).
             * Benign machine learning work can also trigger this category.
             */
            @JvmField val FRONTIER_LLM = of("frontier_llm")

            /**
             * The request asks the model to reproduce its internal reasoning in the response text.
             * To get reasoning in a structured form instead, use
             * [adaptive thinking](https://platform.claude.com/docs/en/build-with-claude/adaptive-thinking).
             */
            @JvmField val REASONING_EXTRACTION = of("reasoning_extraction")

            @JvmStatic fun of(value: String) = Category(JsonField.of(value))
        }

        /** An enum containing [Category]'s known values. */
        enum class Known {
            /**
             * The request could enable cyber harm, such as malware or exploit development. Benign
             * cybersecurity work can also trigger this category.
             */
            CYBER,
            /**
             * The request could enable biological harm, such as dangerous lab methods. Beneficial
             * life sciences work can also trigger this category.
             */
            BIO,
            /**
             * The request could assist the development of competing AI models, which is restricted
             * under
             * [Anthropic's commercial terms](https://www.anthropic.com/legal/commercial-terms).
             * Benign machine learning work can also trigger this category.
             */
            FRONTIER_LLM,
            /**
             * The request asks the model to reproduce its internal reasoning in the response text.
             * To get reasoning in a structured form instead, use
             * [adaptive thinking](https://platform.claude.com/docs/en/build-with-claude/adaptive-thinking).
             */
            REASONING_EXTRACTION,
        }

        /**
         * An enum containing [Category]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Category] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            /**
             * The request could enable cyber harm, such as malware or exploit development. Benign
             * cybersecurity work can also trigger this category.
             */
            CYBER,
            /**
             * The request could enable biological harm, such as dangerous lab methods. Beneficial
             * life sciences work can also trigger this category.
             */
            BIO,
            /**
             * The request could assist the development of competing AI models, which is restricted
             * under
             * [Anthropic's commercial terms](https://www.anthropic.com/legal/commercial-terms).
             * Benign machine learning work can also trigger this category.
             */
            FRONTIER_LLM,
            /**
             * The request asks the model to reproduce its internal reasoning in the response text.
             * To get reasoning in a structured form instead, use
             * [adaptive thinking](https://platform.claude.com/docs/en/build-with-claude/adaptive-thinking).
             */
            REASONING_EXTRACTION,
            /** An enum member indicating that [Category] was instantiated with an unknown value. */
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
                CYBER -> Value.CYBER
                BIO -> Value.BIO
                FRONTIER_LLM -> Value.FRONTIER_LLM
                REASONING_EXTRACTION -> Value.REASONING_EXTRACTION
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
                CYBER -> Known.CYBER
                BIO -> Known.BIO
                FRONTIER_LLM -> Known.FRONTIER_LLM
                REASONING_EXTRACTION -> Known.REASONING_EXTRACTION
                else -> throw AnthropicInvalidDataException("Unknown Category: $value")
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
        fun validate(): Category = apply {
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

            return other is Category && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRefusalStopDetails &&
            category == other.category &&
            explanation == other.explanation &&
            fallbackCreditToken == other.fallbackCreditToken &&
            fallbackHasPrefillClaim == other.fallbackHasPrefillClaim &&
            recommendedModel == other.recommendedModel &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            category,
            explanation,
            fallbackCreditToken,
            fallbackHasPrefillClaim,
            recommendedModel,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRefusalStopDetails{category=$category, explanation=$explanation, fallbackCreditToken=$fallbackCreditToken, fallbackHasPrefillClaim=$fallbackHasPrefillClaim, recommendedModel=$recommendedModel, type=$type, additionalProperties=$additionalProperties}"
}
