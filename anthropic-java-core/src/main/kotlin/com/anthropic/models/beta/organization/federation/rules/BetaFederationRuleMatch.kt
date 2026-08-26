// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.rules

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Does the incoming JWT qualify?
 *
 * All populated fields must pass; omitted fields are skipped. At least one of `subject_prefix`
 * (other than a wildcard-only value like `*`), `claims`, or `condition` is required; `audience`
 * alone is not sufficient.
 */
class BetaFederationRuleMatch
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val audience: JsonField<String>,
    private val claims: JsonField<Claims>,
    private val condition: JsonField<String>,
    private val subjectPrefix: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("audience") @ExcludeMissing audience: JsonField<String> = JsonMissing.of(),
        @JsonProperty("claims") @ExcludeMissing claims: JsonField<Claims> = JsonMissing.of(),
        @JsonProperty("condition") @ExcludeMissing condition: JsonField<String> = JsonMissing.of(),
        @JsonProperty("subject_prefix")
        @ExcludeMissing
        subjectPrefix: JsonField<String> = JsonMissing.of(),
    ) : this(audience, claims, condition, subjectPrefix, mutableMapOf())

    /**
     * Exact match against the `aud` claim (any element if array). When omitted, the JWT's `aud`
     * must still equal Anthropic's expected audience for the issuer; setting this field overrides
     * that default.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun audience(): Optional<String> = audience.getOptional("audience")

    /**
     * Exact-match `{claim: value}` pairs against top-level claims. Only string-valued claims can be
     * matched; use `condition` for non-string claims.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun claims(): Optional<Claims> = claims.getOptional("claims")

    /**
     * CEL expression over claims for logic the structural fields can't express. Must evaluate to a
     * boolean and may reference only the `claims` variable; a constant-true expression (such as
     * `true`) is rejected with 400.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun condition(): Optional<String> = condition.getOptional("condition")

    /**
     * Match the verified JWT `sub` claim. Exact match unless the value ends with `*`, in which case
     * it is a prefix match. Example: `repo:my-org/my-repo:ref:refs/heads/main`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun subjectPrefix(): Optional<String> = subjectPrefix.getOptional("subject_prefix")

    /**
     * Returns the raw JSON value of [audience].
     *
     * Unlike [audience], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("audience") @ExcludeMissing fun _audience(): JsonField<String> = audience

    /**
     * Returns the raw JSON value of [claims].
     *
     * Unlike [claims], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("claims") @ExcludeMissing fun _claims(): JsonField<Claims> = claims

    /**
     * Returns the raw JSON value of [condition].
     *
     * Unlike [condition], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("condition") @ExcludeMissing fun _condition(): JsonField<String> = condition

    /**
     * Returns the raw JSON value of [subjectPrefix].
     *
     * Unlike [subjectPrefix], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("subject_prefix")
    @ExcludeMissing
    fun _subjectPrefix(): JsonField<String> = subjectPrefix

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

        /** Returns a mutable builder for constructing an instance of [BetaFederationRuleMatch]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaFederationRuleMatch]. */
    class Builder internal constructor() {

        private var audience: JsonField<String> = JsonMissing.of()
        private var claims: JsonField<Claims> = JsonMissing.of()
        private var condition: JsonField<String> = JsonMissing.of()
        private var subjectPrefix: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaFederationRuleMatch: BetaFederationRuleMatch) = apply {
            audience = betaFederationRuleMatch.audience
            claims = betaFederationRuleMatch.claims
            condition = betaFederationRuleMatch.condition
            subjectPrefix = betaFederationRuleMatch.subjectPrefix
            additionalProperties = betaFederationRuleMatch.additionalProperties.toMutableMap()
        }

        /**
         * Exact match against the `aud` claim (any element if array). When omitted, the JWT's `aud`
         * must still equal Anthropic's expected audience for the issuer; setting this field
         * overrides that default.
         */
        fun audience(audience: String?) = audience(JsonField.ofNullable(audience))

        /** Alias for calling [Builder.audience] with `audience.orElse(null)`. */
        fun audience(audience: Optional<String>) = audience(audience.getOrNull())

        /**
         * Sets [Builder.audience] to an arbitrary JSON value.
         *
         * You should usually call [Builder.audience] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun audience(audience: JsonField<String>) = apply { this.audience = audience }

        /**
         * Exact-match `{claim: value}` pairs against top-level claims. Only string-valued claims
         * can be matched; use `condition` for non-string claims.
         */
        fun claims(claims: Claims?) = claims(JsonField.ofNullable(claims))

        /** Alias for calling [Builder.claims] with `claims.orElse(null)`. */
        fun claims(claims: Optional<Claims>) = claims(claims.getOrNull())

        /**
         * Sets [Builder.claims] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claims] with a well-typed [Claims] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun claims(claims: JsonField<Claims>) = apply { this.claims = claims }

        /**
         * CEL expression over claims for logic the structural fields can't express. Must evaluate
         * to a boolean and may reference only the `claims` variable; a constant-true expression
         * (such as `true`) is rejected with 400.
         */
        fun condition(condition: String?) = condition(JsonField.ofNullable(condition))

        /** Alias for calling [Builder.condition] with `condition.orElse(null)`. */
        fun condition(condition: Optional<String>) = condition(condition.getOrNull())

        /**
         * Sets [Builder.condition] to an arbitrary JSON value.
         *
         * You should usually call [Builder.condition] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun condition(condition: JsonField<String>) = apply { this.condition = condition }

        /**
         * Match the verified JWT `sub` claim. Exact match unless the value ends with `*`, in which
         * case it is a prefix match. Example: `repo:my-org/my-repo:ref:refs/heads/main`.
         */
        fun subjectPrefix(subjectPrefix: String?) =
            subjectPrefix(JsonField.ofNullable(subjectPrefix))

        /** Alias for calling [Builder.subjectPrefix] with `subjectPrefix.orElse(null)`. */
        fun subjectPrefix(subjectPrefix: Optional<String>) =
            subjectPrefix(subjectPrefix.getOrNull())

        /**
         * Sets [Builder.subjectPrefix] to an arbitrary JSON value.
         *
         * You should usually call [Builder.subjectPrefix] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun subjectPrefix(subjectPrefix: JsonField<String>) = apply {
            this.subjectPrefix = subjectPrefix
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
         * Returns an immutable instance of [BetaFederationRuleMatch].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaFederationRuleMatch =
            BetaFederationRuleMatch(
                audience,
                claims,
                condition,
                subjectPrefix,
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
    fun validate(): BetaFederationRuleMatch = apply {
        if (validated) {
            return@apply
        }

        audience()
        claims().ifPresent { it.validate() }
        condition()
        subjectPrefix()
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
        (if (audience.asKnown().isPresent) 1 else 0) +
            (claims.asKnown().getOrNull()?.validity() ?: 0) +
            (if (condition.asKnown().isPresent) 1 else 0) +
            (if (subjectPrefix.asKnown().isPresent) 1 else 0)

    /**
     * Exact-match `{claim: value}` pairs against top-level claims. Only string-valued claims can be
     * matched; use `condition` for non-string claims.
     */
    class Claims
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Claims]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Claims]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(claims: Claims) = apply {
                additionalProperties = claims.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Claims].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Claims = Claims(additionalProperties.toImmutable())
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
        fun validate(): Claims = apply {
            if (validated) {
                return@apply
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Claims && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Claims{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaFederationRuleMatch &&
            audience == other.audience &&
            claims == other.claims &&
            condition == other.condition &&
            subjectPrefix == other.subjectPrefix &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(audience, claims, condition, subjectPrefix, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaFederationRuleMatch{audience=$audience, claims=$claims, condition=$condition, subjectPrefix=$subjectPrefix, additionalProperties=$additionalProperties}"
}
