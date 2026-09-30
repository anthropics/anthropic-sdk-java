package com.anthropic.models.beta.organization.analytics

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

/** Office Agent activity metrics for a single user on a given day, broken out by Office product. */
class BetaAnalyticsOfficeMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val excel: JsonField<BetaAnalyticsOfficeProductMetrics>,
    private val outlook: JsonField<BetaAnalyticsOfficeProductMetrics>,
    private val powerpoint: JsonField<BetaAnalyticsOfficeProductMetrics>,
    private val word: JsonField<BetaAnalyticsOfficeProductMetrics>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("excel")
        @ExcludeMissing
        excel: JsonField<BetaAnalyticsOfficeProductMetrics> = JsonMissing.of(),
        @JsonProperty("outlook")
        @ExcludeMissing
        outlook: JsonField<BetaAnalyticsOfficeProductMetrics> = JsonMissing.of(),
        @JsonProperty("powerpoint")
        @ExcludeMissing
        powerpoint: JsonField<BetaAnalyticsOfficeProductMetrics> = JsonMissing.of(),
        @JsonProperty("word")
        @ExcludeMissing
        word: JsonField<BetaAnalyticsOfficeProductMetrics> = JsonMissing.of(),
    ) : this(excel, outlook, powerpoint, word, mutableMapOf())

    /**
     * Office Agent activity metrics for a single user on a given day within one Office product.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun excel(): BetaAnalyticsOfficeProductMetrics = excel.getRequired("excel")

    /**
     * Office Agent activity metrics for a single user on a given day within one Office product.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun outlook(): BetaAnalyticsOfficeProductMetrics = outlook.getRequired("outlook")

    /**
     * Office Agent activity metrics for a single user on a given day within one Office product.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun powerpoint(): BetaAnalyticsOfficeProductMetrics = powerpoint.getRequired("powerpoint")

    /**
     * Office Agent activity metrics for a single user on a given day within one Office product.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun word(): BetaAnalyticsOfficeProductMetrics = word.getRequired("word")

    /**
     * Returns the raw JSON value of [excel].
     *
     * Unlike [excel], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("excel")
    @ExcludeMissing
    fun _excel(): JsonField<BetaAnalyticsOfficeProductMetrics> = excel

    /**
     * Returns the raw JSON value of [outlook].
     *
     * Unlike [outlook], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("outlook")
    @ExcludeMissing
    fun _outlook(): JsonField<BetaAnalyticsOfficeProductMetrics> = outlook

    /**
     * Returns the raw JSON value of [powerpoint].
     *
     * Unlike [powerpoint], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("powerpoint")
    @ExcludeMissing
    fun _powerpoint(): JsonField<BetaAnalyticsOfficeProductMetrics> = powerpoint

    /**
     * Returns the raw JSON value of [word].
     *
     * Unlike [word], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("word")
    @ExcludeMissing
    fun _word(): JsonField<BetaAnalyticsOfficeProductMetrics> = word

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsOfficeMetrics].
         *
         * The following fields are required:
         * ```java
         * .excel()
         * .outlook()
         * .powerpoint()
         * .word()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsOfficeMetrics]. */
    class Builder internal constructor() {

        private var excel: JsonField<BetaAnalyticsOfficeProductMetrics>? = null
        private var outlook: JsonField<BetaAnalyticsOfficeProductMetrics>? = null
        private var powerpoint: JsonField<BetaAnalyticsOfficeProductMetrics>? = null
        private var word: JsonField<BetaAnalyticsOfficeProductMetrics>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsOfficeMetrics: BetaAnalyticsOfficeMetrics) = apply {
            excel = betaAnalyticsOfficeMetrics.excel
            outlook = betaAnalyticsOfficeMetrics.outlook
            powerpoint = betaAnalyticsOfficeMetrics.powerpoint
            word = betaAnalyticsOfficeMetrics.word
            additionalProperties = betaAnalyticsOfficeMetrics.additionalProperties.toMutableMap()
        }

        /**
         * Office Agent activity metrics for a single user on a given day within one Office product.
         */
        fun excel(excel: BetaAnalyticsOfficeProductMetrics) = excel(JsonField.of(excel))

        /**
         * Sets [Builder.excel] to an arbitrary JSON value.
         *
         * You should usually call [Builder.excel] with a well-typed
         * [BetaAnalyticsOfficeProductMetrics] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun excel(excel: JsonField<BetaAnalyticsOfficeProductMetrics>) = apply {
            this.excel = excel
        }

        /**
         * Office Agent activity metrics for a single user on a given day within one Office product.
         */
        fun outlook(outlook: BetaAnalyticsOfficeProductMetrics) = outlook(JsonField.of(outlook))

        /**
         * Sets [Builder.outlook] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outlook] with a well-typed
         * [BetaAnalyticsOfficeProductMetrics] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun outlook(outlook: JsonField<BetaAnalyticsOfficeProductMetrics>) = apply {
            this.outlook = outlook
        }

        /**
         * Office Agent activity metrics for a single user on a given day within one Office product.
         */
        fun powerpoint(powerpoint: BetaAnalyticsOfficeProductMetrics) =
            powerpoint(JsonField.of(powerpoint))

        /**
         * Sets [Builder.powerpoint] to an arbitrary JSON value.
         *
         * You should usually call [Builder.powerpoint] with a well-typed
         * [BetaAnalyticsOfficeProductMetrics] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun powerpoint(powerpoint: JsonField<BetaAnalyticsOfficeProductMetrics>) = apply {
            this.powerpoint = powerpoint
        }

        /**
         * Office Agent activity metrics for a single user on a given day within one Office product.
         */
        fun word(word: BetaAnalyticsOfficeProductMetrics) = word(JsonField.of(word))

        /**
         * Sets [Builder.word] to an arbitrary JSON value.
         *
         * You should usually call [Builder.word] with a well-typed
         * [BetaAnalyticsOfficeProductMetrics] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun word(word: JsonField<BetaAnalyticsOfficeProductMetrics>) = apply { this.word = word }

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
         * Returns an immutable instance of [BetaAnalyticsOfficeMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .excel()
         * .outlook()
         * .powerpoint()
         * .word()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsOfficeMetrics =
            BetaAnalyticsOfficeMetrics(
                checkRequired("excel", excel),
                checkRequired("outlook", outlook),
                checkRequired("powerpoint", powerpoint),
                checkRequired("word", word),
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
    fun validate(): BetaAnalyticsOfficeMetrics = apply {
        if (validated) {
            return@apply
        }

        excel().validate()
        outlook().validate()
        powerpoint().validate()
        word().validate()
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
        (excel.asKnown().getOrNull()?.validity() ?: 0) +
            (outlook.asKnown().getOrNull()?.validity() ?: 0) +
            (powerpoint.asKnown().getOrNull()?.validity() ?: 0) +
            (word.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsOfficeMetrics &&
            excel == other.excel &&
            outlook == other.outlook &&
            powerpoint == other.powerpoint &&
            word == other.word &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(excel, outlook, powerpoint, word, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsOfficeMetrics{excel=$excel, outlook=$outlook, powerpoint=$powerpoint, word=$word, additionalProperties=$additionalProperties}"
}
