package com.anthropic.models.organization.analytics

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
 * Office Agent activity metrics for a single connector on a given day, broken out by Office
 * product.
 */
class AnalyticsConnectorOfficeMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val excel: JsonField<AnalyticsConnectorOfficeProductMetrics>,
    private val outlook: JsonField<AnalyticsConnectorOfficeProductMetrics>,
    private val powerpoint: JsonField<AnalyticsConnectorOfficeProductMetrics>,
    private val word: JsonField<AnalyticsConnectorOfficeProductMetrics>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("excel")
        @ExcludeMissing
        excel: JsonField<AnalyticsConnectorOfficeProductMetrics> = JsonMissing.of(),
        @JsonProperty("outlook")
        @ExcludeMissing
        outlook: JsonField<AnalyticsConnectorOfficeProductMetrics> = JsonMissing.of(),
        @JsonProperty("powerpoint")
        @ExcludeMissing
        powerpoint: JsonField<AnalyticsConnectorOfficeProductMetrics> = JsonMissing.of(),
        @JsonProperty("word")
        @ExcludeMissing
        word: JsonField<AnalyticsConnectorOfficeProductMetrics> = JsonMissing.of(),
    ) : this(excel, outlook, powerpoint, word, mutableMapOf())

    /**
     * Office Agent activity metrics for a single connector on a given day within one Office
     * product.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun excel(): AnalyticsConnectorOfficeProductMetrics = excel.getRequired("excel")

    /**
     * Office Agent activity metrics for a single connector on a given day within one Office
     * product.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun outlook(): AnalyticsConnectorOfficeProductMetrics = outlook.getRequired("outlook")

    /**
     * Office Agent activity metrics for a single connector on a given day within one Office
     * product.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun powerpoint(): AnalyticsConnectorOfficeProductMetrics = powerpoint.getRequired("powerpoint")

    /**
     * Office Agent activity metrics for a single connector on a given day within one Office
     * product.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun word(): AnalyticsConnectorOfficeProductMetrics = word.getRequired("word")

    /**
     * Returns the raw JSON value of [excel].
     *
     * Unlike [excel], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("excel")
    @ExcludeMissing
    fun _excel(): JsonField<AnalyticsConnectorOfficeProductMetrics> = excel

    /**
     * Returns the raw JSON value of [outlook].
     *
     * Unlike [outlook], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("outlook")
    @ExcludeMissing
    fun _outlook(): JsonField<AnalyticsConnectorOfficeProductMetrics> = outlook

    /**
     * Returns the raw JSON value of [powerpoint].
     *
     * Unlike [powerpoint], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("powerpoint")
    @ExcludeMissing
    fun _powerpoint(): JsonField<AnalyticsConnectorOfficeProductMetrics> = powerpoint

    /**
     * Returns the raw JSON value of [word].
     *
     * Unlike [word], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("word")
    @ExcludeMissing
    fun _word(): JsonField<AnalyticsConnectorOfficeProductMetrics> = word

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
         * [AnalyticsConnectorOfficeMetrics].
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

    /** A builder for [AnalyticsConnectorOfficeMetrics]. */
    class Builder internal constructor() {

        private var excel: JsonField<AnalyticsConnectorOfficeProductMetrics>? = null
        private var outlook: JsonField<AnalyticsConnectorOfficeProductMetrics>? = null
        private var powerpoint: JsonField<AnalyticsConnectorOfficeProductMetrics>? = null
        private var word: JsonField<AnalyticsConnectorOfficeProductMetrics>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(analyticsConnectorOfficeMetrics: AnalyticsConnectorOfficeMetrics) =
            apply {
                excel = analyticsConnectorOfficeMetrics.excel
                outlook = analyticsConnectorOfficeMetrics.outlook
                powerpoint = analyticsConnectorOfficeMetrics.powerpoint
                word = analyticsConnectorOfficeMetrics.word
                additionalProperties =
                    analyticsConnectorOfficeMetrics.additionalProperties.toMutableMap()
            }

        /**
         * Office Agent activity metrics for a single connector on a given day within one Office
         * product.
         */
        fun excel(excel: AnalyticsConnectorOfficeProductMetrics) = excel(JsonField.of(excel))

        /**
         * Sets [Builder.excel] to an arbitrary JSON value.
         *
         * You should usually call [Builder.excel] with a well-typed
         * [AnalyticsConnectorOfficeProductMetrics] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun excel(excel: JsonField<AnalyticsConnectorOfficeProductMetrics>) = apply {
            this.excel = excel
        }

        /**
         * Office Agent activity metrics for a single connector on a given day within one Office
         * product.
         */
        fun outlook(outlook: AnalyticsConnectorOfficeProductMetrics) =
            outlook(JsonField.of(outlook))

        /**
         * Sets [Builder.outlook] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outlook] with a well-typed
         * [AnalyticsConnectorOfficeProductMetrics] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun outlook(outlook: JsonField<AnalyticsConnectorOfficeProductMetrics>) = apply {
            this.outlook = outlook
        }

        /**
         * Office Agent activity metrics for a single connector on a given day within one Office
         * product.
         */
        fun powerpoint(powerpoint: AnalyticsConnectorOfficeProductMetrics) =
            powerpoint(JsonField.of(powerpoint))

        /**
         * Sets [Builder.powerpoint] to an arbitrary JSON value.
         *
         * You should usually call [Builder.powerpoint] with a well-typed
         * [AnalyticsConnectorOfficeProductMetrics] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun powerpoint(powerpoint: JsonField<AnalyticsConnectorOfficeProductMetrics>) = apply {
            this.powerpoint = powerpoint
        }

        /**
         * Office Agent activity metrics for a single connector on a given day within one Office
         * product.
         */
        fun word(word: AnalyticsConnectorOfficeProductMetrics) = word(JsonField.of(word))

        /**
         * Sets [Builder.word] to an arbitrary JSON value.
         *
         * You should usually call [Builder.word] with a well-typed
         * [AnalyticsConnectorOfficeProductMetrics] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun word(word: JsonField<AnalyticsConnectorOfficeProductMetrics>) = apply {
            this.word = word
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
         * Returns an immutable instance of [AnalyticsConnectorOfficeMetrics].
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
        fun build(): AnalyticsConnectorOfficeMetrics =
            AnalyticsConnectorOfficeMetrics(
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
    fun validate(): AnalyticsConnectorOfficeMetrics = apply {
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

        return other is AnalyticsConnectorOfficeMetrics &&
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
        "AnalyticsConnectorOfficeMetrics{excel=$excel, outlook=$outlook, powerpoint=$powerpoint, word=$word, additionalProperties=$additionalProperties}"
}
