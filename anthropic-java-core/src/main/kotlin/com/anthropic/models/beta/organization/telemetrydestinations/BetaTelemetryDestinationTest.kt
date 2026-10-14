package com.anthropic.models.beta.organization.telemetrydestinations

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

/** The result of one test span sent to a destination. */
class BetaTelemetryDestinationTest
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val result: JsonField<BetaTelemetryDestinationTestResult>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("result")
        @ExcludeMissing
        result: JsonField<BetaTelemetryDestinationTestResult> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(result, type, mutableMapOf())

    /**
     * How the test went: `success` when the destination answered with a 2xx status, else `failure`
     * with the reason.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun result(): BetaTelemetryDestinationTestResult = result.getRequired("result")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("telemetry_destination_test")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [result].
     *
     * Unlike [result], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("result")
    @ExcludeMissing
    fun _result(): JsonField<BetaTelemetryDestinationTestResult> = result

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
         * Returns a mutable builder for constructing an instance of [BetaTelemetryDestinationTest].
         *
         * The following fields are required:
         * ```java
         * .result()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaTelemetryDestinationTest] with the required
         * [result] set to the given value.
         */
        @JvmStatic
        fun of(result: BetaTelemetryDestinationTestResult) = builder().result(result).build()

        /** Alias for calling [of] with `BetaTelemetryDestinationTestResult.ofSuccess(success)`. */
        @JvmStatic
        fun of(success: BetaTelemetryDestinationTestSuccess) =
            of(BetaTelemetryDestinationTestResult.ofSuccess(success))

        /** Alias for calling [of] with `BetaTelemetryDestinationTestResult.ofFailure(failure)`. */
        @JvmStatic
        fun of(failure: BetaTelemetryDestinationTestFailure) =
            of(BetaTelemetryDestinationTestResult.ofFailure(failure))
    }

    /** A builder for [BetaTelemetryDestinationTest]. */
    class Builder internal constructor() {

        private var result: JsonField<BetaTelemetryDestinationTestResult>? = null
        private var type: JsonValue = JsonValue.from("telemetry_destination_test")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaTelemetryDestinationTest: BetaTelemetryDestinationTest) = apply {
            result = betaTelemetryDestinationTest.result
            type = betaTelemetryDestinationTest.type
            additionalProperties = betaTelemetryDestinationTest.additionalProperties.toMutableMap()
        }

        /**
         * How the test went: `success` when the destination answered with a 2xx status, else
         * `failure` with the reason.
         */
        fun result(result: BetaTelemetryDestinationTestResult) = result(JsonField.of(result))

        /**
         * Sets [Builder.result] to an arbitrary JSON value.
         *
         * You should usually call [Builder.result] with a well-typed
         * [BetaTelemetryDestinationTestResult] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun result(result: JsonField<BetaTelemetryDestinationTestResult>) = apply {
            this.result = result
        }

        /**
         * Alias for calling [result] with `BetaTelemetryDestinationTestResult.ofSuccess(success)`.
         */
        fun result(success: BetaTelemetryDestinationTestSuccess) =
            result(BetaTelemetryDestinationTestResult.ofSuccess(success))

        /**
         * Alias for calling [result] with `BetaTelemetryDestinationTestResult.ofFailure(failure)`.
         */
        fun result(failure: BetaTelemetryDestinationTestFailure) =
            result(BetaTelemetryDestinationTestResult.ofFailure(failure))

        /**
         * Alias for calling [result] with the following:
         * ```java
         * BetaTelemetryDestinationTestFailure.builder()
         *     .error(error)
         *     .build()
         * ```
         */
        fun failureResult(error: String) =
            result(BetaTelemetryDestinationTestFailure.builder().error(error).build())

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("telemetry_destination_test")
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
         * Returns an immutable instance of [BetaTelemetryDestinationTest].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .result()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaTelemetryDestinationTest =
            BetaTelemetryDestinationTest(
                checkRequired("result", result),
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
    fun validate(): BetaTelemetryDestinationTest = apply {
        if (validated) {
            return@apply
        }

        result().validate()
        _type().let {
            if (it != JsonValue.from("telemetry_destination_test")) {
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
        (result.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("telemetry_destination_test")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTelemetryDestinationTest &&
            result == other.result &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(result, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTelemetryDestinationTest{result=$result, type=$type, additionalProperties=$additionalProperties}"
}
