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

/** Click and drag the cursor from `start_coordinate` to `coordinate`. */
class ComputerLeftClickDragInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val coordinate: JsonField<List<Long>>,
    private val startCoordinate: JsonField<List<Long>>,
    private val text: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("coordinate")
        @ExcludeMissing
        coordinate: JsonField<List<Long>> = JsonMissing.of(),
        @JsonProperty("start_coordinate")
        @ExcludeMissing
        startCoordinate: JsonField<List<Long>> = JsonMissing.of(),
        @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
    ) : this(coordinate, startCoordinate, text, mutableMapOf())

    /**
     * (x, y): x pixels from the left edge, y pixels from the top edge.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun coordinate(): List<Long> = coordinate.getRequired("coordinate")

    /**
     * (x, y): x pixels from the left edge, y pixels from the top edge.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun startCoordinate(): List<Long> = startCoordinate.getRequired("start_coordinate")

    /**
     * Optional key combination to hold down during this action (e.g. "ctrl", "shift",
     * "ctrl+shift").
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun text(): Optional<String> = text.getOptional("text")

    /**
     * Returns the raw JSON value of [coordinate].
     *
     * Unlike [coordinate], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("coordinate")
    @ExcludeMissing
    fun _coordinate(): JsonField<List<Long>> = coordinate

    /**
     * Returns the raw JSON value of [startCoordinate].
     *
     * Unlike [startCoordinate], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("start_coordinate")
    @ExcludeMissing
    fun _startCoordinate(): JsonField<List<Long>> = startCoordinate

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
         * Returns a mutable builder for constructing an instance of [ComputerLeftClickDragInput].
         *
         * The following fields are required:
         * ```java
         * .coordinate()
         * .startCoordinate()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ComputerLeftClickDragInput]. */
    class Builder internal constructor() {

        private var coordinate: JsonField<MutableList<Long>>? = null
        private var startCoordinate: JsonField<MutableList<Long>>? = null
        private var text: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(computerLeftClickDragInput: ComputerLeftClickDragInput) = apply {
            coordinate =
                computerLeftClickDragInput.coordinate
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            startCoordinate =
                computerLeftClickDragInput.startCoordinate
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            text = computerLeftClickDragInput.text
            additionalProperties = computerLeftClickDragInput.additionalProperties.toMutableMap()
        }

        /** (x, y): x pixels from the left edge, y pixels from the top edge. */
        fun coordinate(coordinate: List<Long>) = coordinate(JsonField.of(coordinate))

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

        /** (x, y): x pixels from the left edge, y pixels from the top edge. */
        fun startCoordinate(startCoordinate: List<Long>) =
            startCoordinate(JsonField.of(startCoordinate))

        /**
         * Sets [Builder.startCoordinate] to an arbitrary JSON value.
         *
         * You should usually call [Builder.startCoordinate] with a well-typed `List<Long>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun startCoordinate(startCoordinate: JsonField<List<Long>>) = apply {
            this.startCoordinate = startCoordinate.map { it.toMutableList() }
        }

        /**
         * Adds a single [Long] to [Builder.startCoordinate].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addStartCoordinate(startCoordinate: Long) = apply {
            this.startCoordinate =
                (this.startCoordinate ?: JsonField.of(mutableListOf())).also {
                    checkKnown("startCoordinate", it).add(startCoordinate)
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
         * Returns an immutable instance of [ComputerLeftClickDragInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .coordinate()
         * .startCoordinate()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ComputerLeftClickDragInput =
            ComputerLeftClickDragInput(
                checkRequired("coordinate", coordinate).map { it.toImmutable() },
                checkRequired("startCoordinate", startCoordinate).map { it.toImmutable() },
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
    fun validate(): ComputerLeftClickDragInput = apply {
        if (validated) {
            return@apply
        }

        coordinate()
        startCoordinate()
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
        (coordinate.asKnown().getOrNull()?.size ?: 0) +
            (startCoordinate.asKnown().getOrNull()?.size ?: 0) +
            (if (text.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ComputerLeftClickDragInput &&
            coordinate == other.coordinate &&
            startCoordinate == other.startCoordinate &&
            text == other.text &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(coordinate, startCoordinate, text, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ComputerLeftClickDragInput{coordinate=$coordinate, startCoordinate=$startCoordinate, text=$text, additionalProperties=$additionalProperties}"
}
