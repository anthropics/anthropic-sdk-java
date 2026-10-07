package com.anthropic.models.messages

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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Scroll the screen at the specified (x, y) pixel coordinate, or the current cursor position if
 * `coordinate` is omitted. Do NOT use PageUp/PageDown to scroll.
 */
class ComputerScrollInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val scrollAmount: JsonField<Long>,
    private val scrollDirection: JsonField<ComputerScrollDirection>,
    private val coordinate: JsonField<List<Long>>,
    private val text: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("scroll_amount")
        @ExcludeMissing
        scrollAmount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("scroll_direction")
        @ExcludeMissing
        scrollDirection: JsonField<ComputerScrollDirection> = JsonMissing.of(),
        @JsonProperty("coordinate")
        @ExcludeMissing
        coordinate: JsonField<List<Long>> = JsonMissing.of(),
        @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
    ) : this(scrollAmount, scrollDirection, coordinate, text, mutableMapOf())

    /**
     * Number of 'clicks' of the scroll wheel.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun scrollAmount(): Long = scrollAmount.getRequired("scroll_amount")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun scrollDirection(): ComputerScrollDirection = scrollDirection.getRequired("scroll_direction")

    /**
     * (x, y): x pixels from the left edge, y pixels from the top edge.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun coordinate(): Optional<List<Long>> = coordinate.getOptional("coordinate")

    /**
     * Optional key combination to hold down during this action (e.g. "ctrl", "shift",
     * "ctrl+shift").
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun text(): Optional<String> = text.getOptional("text")

    /**
     * Returns the raw JSON value of [scrollAmount].
     *
     * Unlike [scrollAmount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scroll_amount")
    @ExcludeMissing
    fun _scrollAmount(): JsonField<Long> = scrollAmount

    /**
     * Returns the raw JSON value of [scrollDirection].
     *
     * Unlike [scrollDirection], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scroll_direction")
    @ExcludeMissing
    fun _scrollDirection(): JsonField<ComputerScrollDirection> = scrollDirection

    /**
     * Returns the raw JSON value of [coordinate].
     *
     * Unlike [coordinate], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("coordinate")
    @ExcludeMissing
    fun _coordinate(): JsonField<List<Long>> = coordinate

    /**
     * Returns the raw JSON value of [text].
     *
     * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

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
         * Returns a mutable builder for constructing an instance of [ComputerScrollInput].
         *
         * The following fields are required:
         * ```java
         * .scrollAmount()
         * .scrollDirection()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ComputerScrollInput]. */
    class Builder internal constructor() {

        private var scrollAmount: JsonField<Long>? = null
        private var scrollDirection: JsonField<ComputerScrollDirection>? = null
        private var coordinate: JsonField<MutableList<Long>>? = null
        private var text: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(computerScrollInput: ComputerScrollInput) = apply {
            scrollAmount = computerScrollInput.scrollAmount
            scrollDirection = computerScrollInput.scrollDirection
            coordinate =
                computerScrollInput.coordinate
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            text = computerScrollInput.text
            additionalProperties = computerScrollInput.additionalProperties.toMutableMap()
        }

        /** Number of 'clicks' of the scroll wheel. */
        fun scrollAmount(scrollAmount: Long) = scrollAmount(JsonField.of(scrollAmount))

        /**
         * Sets [Builder.scrollAmount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scrollAmount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun scrollAmount(scrollAmount: JsonField<Long>) = apply { this.scrollAmount = scrollAmount }

        fun scrollDirection(scrollDirection: ComputerScrollDirection) =
            scrollDirection(JsonField.of(scrollDirection))

        /**
         * Sets [Builder.scrollDirection] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scrollDirection] with a well-typed
         * [ComputerScrollDirection] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun scrollDirection(scrollDirection: JsonField<ComputerScrollDirection>) = apply {
            this.scrollDirection = scrollDirection
        }

        /** (x, y): x pixels from the left edge, y pixels from the top edge. */
        fun coordinate(coordinate: List<Long>?) = coordinate(JsonField.ofNullable(coordinate))

        /** Alias for calling [Builder.coordinate] with `coordinate.orElse(null)`. */
        fun coordinate(coordinate: Optional<List<Long>>) = coordinate(coordinate.getOrNull())

        /**
         * Sets [Builder.coordinate] to an arbitrary JSON value.
         *
         * You should usually call [Builder.coordinate] with a well-typed `List<Long>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun coordinate(coordinate: JsonField<List<Long>>) = apply {
            this.coordinate = coordinate.map { it.toMutableList() }
        }

        /**
         * Adds a single [Long] to [Builder.coordinate].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addCoordinate(coordinate: Long) = apply {
            this.coordinate =
                (this.coordinate ?: JsonField.of(mutableListOf())).also {
                    checkKnown("coordinate", it).add(coordinate)
                }
        }

        /**
         * Optional key combination to hold down during this action (e.g. "ctrl", "shift",
         * "ctrl+shift").
         */
        fun text(text: String?) = text(JsonField.ofNullable(text))

        /** Alias for calling [Builder.text] with `text.orElse(null)`. */
        fun text(text: Optional<String>) = text(text.getOrNull())

        /**
         * Sets [Builder.text] to an arbitrary JSON value.
         *
         * You should usually call [Builder.text] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun text(text: JsonField<String>) = apply { this.text = text }

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
         * Returns an immutable instance of [ComputerScrollInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .scrollAmount()
         * .scrollDirection()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ComputerScrollInput =
            ComputerScrollInput(
                checkRequired("scrollAmount", scrollAmount),
                checkRequired("scrollDirection", scrollDirection),
                (coordinate ?: JsonMissing.of()).map { it.toImmutable() },
                text,
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
    fun validate(): ComputerScrollInput = apply {
        if (validated) {
            return@apply
        }

        scrollAmount()
        scrollDirection().validate()
        coordinate()
        text()
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
        (if (scrollAmount.asKnown().isPresent) 1 else 0) +
            (scrollDirection.asKnown().getOrNull()?.validity() ?: 0) +
            (coordinate.asKnown().getOrNull()?.size ?: 0) +
            (if (text.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ComputerScrollInput &&
            scrollAmount == other.scrollAmount &&
            scrollDirection == other.scrollDirection &&
            coordinate == other.coordinate &&
            text == other.text &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(scrollAmount, scrollDirection, coordinate, text, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ComputerScrollInput{scrollAmount=$scrollAmount, scrollDirection=$scrollDirection, coordinate=$coordinate, text=$text, additionalProperties=$additionalProperties}"
}
