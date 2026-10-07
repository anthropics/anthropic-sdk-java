package com.anthropic.models.beta.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/**
 * Take a screenshot of a rectangular region. Region coordinates are in the full-screenshot space
 * (not physical display pixels). The crop is scaled up to fill the image budget so fine details
 * become legible.
 */
class BetaComputerZoomInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val region: JsonField<List<Long>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("region") @ExcludeMissing region: JsonField<List<Long>> = JsonMissing.of()
    ) : this(region, mutableMapOf())

    /**
     * (x0, y0, x1, y1): The region to capture.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun region(): List<Long> = region.getRequired("region")

    /**
     * Returns the raw JSON value of [region].
     *
     * Unlike [region], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("region") @ExcludeMissing fun _region(): JsonField<List<Long>> = region

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
         * Returns a mutable builder for constructing an instance of [BetaComputerZoomInput].
         *
         * The following fields are required:
         * ```java
         * .region()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaComputerZoomInput] with the required [region] set
         * to the given value.
         */
        @JvmStatic fun of(region: List<Long>) = builder().region(region).build()
    }

    /** A builder for [BetaComputerZoomInput]. */
    class Builder internal constructor() {

        private var region: JsonField<MutableList<Long>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaComputerZoomInput: BetaComputerZoomInput) = apply {
            region =
                betaComputerZoomInput.region
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            additionalProperties = betaComputerZoomInput.additionalProperties.toMutableMap()
        }

        /** (x0, y0, x1, y1): The region to capture. */
        fun region(region: List<Long>) = region(JsonField.of(region))

        /**
         * Sets [Builder.region] to an arbitrary JSON value.
         *
         * You should usually call [Builder.region] with a well-typed `List<Long>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun region(region: JsonField<List<Long>>) = apply {
            this.region = region.map { it.toMutableList() }
        }

        /**
         * Adds a single [Long] to [Builder.region].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addRegion(region: Long) = apply {
            this.region =
                (this.region ?: JsonField.of(mutableListOf())).also {
                    checkKnown("region", it).add(region)
                }
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
         * Returns an immutable instance of [BetaComputerZoomInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .region()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaComputerZoomInput =
            BetaComputerZoomInput(
                checkRequired("region", region).map { it.toImmutable() },
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
    fun validate(): BetaComputerZoomInput = apply {
        if (validated) {
            return@apply
        }

        region()
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
    @JvmSynthetic internal fun validity(): Int = (region.asKnown().getOrNull()?.size ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComputerZoomInput &&
            region == other.region &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(region, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaComputerZoomInput{region=$region, additionalProperties=$additionalProperties}"
}
