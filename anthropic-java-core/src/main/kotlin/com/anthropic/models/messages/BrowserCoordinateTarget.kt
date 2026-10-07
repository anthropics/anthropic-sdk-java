package com.anthropic.models.messages

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

/**
 * A point in the browser viewport, in viewport pixels (the same frame as a full-viewport
 * screenshot).
 */
class BrowserCoordinateTarget
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val x: JsonField<Long>,
    private val y: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("x") @ExcludeMissing x: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("y") @ExcludeMissing y: JsonField<Long> = JsonMissing.of(),
    ) : this(type, x, y, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("coordinate")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Pixels from the left edge of the viewport.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun x(): Long = x.getRequired("x")

    /**
     * Pixels from the top edge of the viewport.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun y(): Long = y.getRequired("y")

    /**
     * Returns the raw JSON value of [x].
     *
     * Unlike [x], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("x") @ExcludeMissing fun _x(): JsonField<Long> = x

    /**
     * Returns the raw JSON value of [y].
     *
     * Unlike [y], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("y") @ExcludeMissing fun _y(): JsonField<Long> = y

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
         * Returns a mutable builder for constructing an instance of [BrowserCoordinateTarget].
         *
         * The following fields are required:
         * ```java
         * .x()
         * .y()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BrowserCoordinateTarget]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("coordinate")
        private var x: JsonField<Long>? = null
        private var y: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(browserCoordinateTarget: BrowserCoordinateTarget) = apply {
            type = browserCoordinateTarget.type
            x = browserCoordinateTarget.x
            y = browserCoordinateTarget.y
            additionalProperties = browserCoordinateTarget.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("coordinate")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Pixels from the left edge of the viewport. */
        fun x(x: Long) = x(JsonField.of(x))

        /**
         * Sets [Builder.x] to an arbitrary JSON value.
         *
         * You should usually call [Builder.x] with a well-typed [Long] value instead. This method
         * is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun x(x: JsonField<Long>) = apply { this.x = x }

        /** Pixels from the top edge of the viewport. */
        fun y(y: Long) = y(JsonField.of(y))

        /**
         * Sets [Builder.y] to an arbitrary JSON value.
         *
         * You should usually call [Builder.y] with a well-typed [Long] value instead. This method
         * is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun y(y: JsonField<Long>) = apply { this.y = y }

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
         * Returns an immutable instance of [BrowserCoordinateTarget].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .x()
         * .y()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BrowserCoordinateTarget =
            BrowserCoordinateTarget(
                type,
                checkRequired("x", x),
                checkRequired("y", y),
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
    fun validate(): BrowserCoordinateTarget = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("coordinate")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        x()
        y()
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
        type.let { if (it == JsonValue.from("coordinate")) 1 else 0 } +
            (if (x.asKnown().isPresent) 1 else 0) +
            (if (y.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BrowserCoordinateTarget &&
            type == other.type &&
            x == other.x &&
            y == other.y &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(type, x, y, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BrowserCoordinateTarget{type=$type, x=$x, y=$y, additionalProperties=$additionalProperties}"
}
