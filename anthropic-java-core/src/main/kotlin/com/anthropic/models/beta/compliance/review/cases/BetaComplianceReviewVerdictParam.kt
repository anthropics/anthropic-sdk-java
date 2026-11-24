package com.anthropic.models.beta.compliance.review.cases

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

/** Your conclusion on one message of a review case. */
class BetaComplianceReviewVerdictParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val determination: JsonField<BetaComplianceReviewDetermination>,
    private val itemId: JsonField<String>,
    private val policyCategory: JsonField<BetaComplianceReviewViolatedPolicyCategory>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("determination")
        @ExcludeMissing
        determination: JsonField<BetaComplianceReviewDetermination> = JsonMissing.of(),
        @JsonProperty("item_id") @ExcludeMissing itemId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("policy_category")
        @ExcludeMissing
        policyCategory: JsonField<BetaComplianceReviewViolatedPolicyCategory> = JsonMissing.of(),
    ) : this(determination, itemId, policyCategory, mutableMapOf())

    /**
     * Your conclusion on this message.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun determination(): BetaComplianceReviewDetermination =
        determination.getRequired("determination")

    /**
     * The `item_id` of one of the case's `transcripts`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun itemId(): String = itemId.getRequired("item_id")

    /**
     * The policy category that this message violated, from the values that the request's
     * `policy_categories` accepts. Optional with `violation`. Leave it out with `no_violation`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun policyCategory(): Optional<BetaComplianceReviewViolatedPolicyCategory> =
        policyCategory.getOptional("policy_category")

    /**
     * Returns the raw JSON value of [determination].
     *
     * Unlike [determination], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("determination")
    @ExcludeMissing
    fun _determination(): JsonField<BetaComplianceReviewDetermination> = determination

    /**
     * Returns the raw JSON value of [itemId].
     *
     * Unlike [itemId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("item_id") @ExcludeMissing fun _itemId(): JsonField<String> = itemId

    /**
     * Returns the raw JSON value of [policyCategory].
     *
     * Unlike [policyCategory], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("policy_category")
    @ExcludeMissing
    fun _policyCategory(): JsonField<BetaComplianceReviewViolatedPolicyCategory> = policyCategory

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
         * [BetaComplianceReviewVerdictParam].
         *
         * The following fields are required:
         * ```java
         * .determination()
         * .itemId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaComplianceReviewVerdictParam]. */
    class Builder internal constructor() {

        private var determination: JsonField<BetaComplianceReviewDetermination>? = null
        private var itemId: JsonField<String>? = null
        private var policyCategory: JsonField<BetaComplianceReviewViolatedPolicyCategory> =
            JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaComplianceReviewVerdictParam: BetaComplianceReviewVerdictParam) =
            apply {
                determination = betaComplianceReviewVerdictParam.determination
                itemId = betaComplianceReviewVerdictParam.itemId
                policyCategory = betaComplianceReviewVerdictParam.policyCategory
                additionalProperties =
                    betaComplianceReviewVerdictParam.additionalProperties.toMutableMap()
            }

        /** Your conclusion on this message. */
        fun determination(determination: BetaComplianceReviewDetermination) =
            determination(JsonField.of(determination))

        /**
         * Sets [Builder.determination] to an arbitrary JSON value.
         *
         * You should usually call [Builder.determination] with a well-typed
         * [BetaComplianceReviewDetermination] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun determination(determination: JsonField<BetaComplianceReviewDetermination>) = apply {
            this.determination = determination
        }

        /** The `item_id` of one of the case's `transcripts`. */
        fun itemId(itemId: String) = itemId(JsonField.of(itemId))

        /**
         * Sets [Builder.itemId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.itemId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun itemId(itemId: JsonField<String>) = apply { this.itemId = itemId }

        /**
         * The policy category that this message violated, from the values that the request's
         * `policy_categories` accepts. Optional with `violation`. Leave it out with `no_violation`.
         */
        fun policyCategory(policyCategory: BetaComplianceReviewViolatedPolicyCategory?) =
            policyCategory(JsonField.ofNullable(policyCategory))

        /** Alias for calling [Builder.policyCategory] with `policyCategory.orElse(null)`. */
        fun policyCategory(policyCategory: Optional<BetaComplianceReviewViolatedPolicyCategory>) =
            policyCategory(policyCategory.getOrNull())

        /**
         * Sets [Builder.policyCategory] to an arbitrary JSON value.
         *
         * You should usually call [Builder.policyCategory] with a well-typed
         * [BetaComplianceReviewViolatedPolicyCategory] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun policyCategory(policyCategory: JsonField<BetaComplianceReviewViolatedPolicyCategory>) =
            apply {
                this.policyCategory = policyCategory
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
         * Returns an immutable instance of [BetaComplianceReviewVerdictParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .determination()
         * .itemId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaComplianceReviewVerdictParam =
            BetaComplianceReviewVerdictParam(
                checkRequired("determination", determination),
                checkRequired("itemId", itemId),
                policyCategory,
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
    fun validate(): BetaComplianceReviewVerdictParam = apply {
        if (validated) {
            return@apply
        }

        determination().validate()
        itemId()
        policyCategory().ifPresent { it.validate() }
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
        (determination.asKnown().getOrNull()?.validity() ?: 0) +
            (if (itemId.asKnown().isPresent) 1 else 0) +
            (policyCategory.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceReviewVerdictParam &&
            determination == other.determination &&
            itemId == other.itemId &&
            policyCategory == other.policyCategory &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(determination, itemId, policyCategory, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaComplianceReviewVerdictParam{determination=$determination, itemId=$itemId, policyCategory=$policyCategory, additionalProperties=$additionalProperties}"
}
