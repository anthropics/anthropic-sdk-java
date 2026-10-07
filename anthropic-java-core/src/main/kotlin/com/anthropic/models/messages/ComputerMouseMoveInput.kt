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
import kotlin.jvm.optionals.getOrNull

/**
 * Move the cursor to a specified (x, y) pixel coordinate. Use this ONLY to hover without clicking;
 * otherwise use a click action directly.
 */
class ComputerMouseMoveInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val coordinate: JsonField<List<Long>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("coordinate")
        @ExcludeMissing
        coordinate: JsonField<List<Long>> = JsonMissing.of()
    ) : this(coordinate, mutableMapOf())

    /**
     * (x, y): x pixels from the left edge, y pixels from the top edge.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun coordinate(): List<Long> = coordinate.getRequired("coordinate")

    /**
     * Returns the raw JSON value of [coordinate].
     *
     * Unlike [coordinate], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("coordinate")
    @ExcludeMissing
    fun _coordinate(): JsonField<List<Long>> = coordinate

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
         * Returns a mutable builder for constructing an instance of [ComputerMouseMoveInput].
         *
         * The following fields are required:
         * ```java
         * .coordinate()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [ComputerMouseMoveInput] with the required [coordinate]
         * set to the given value.
         */
        @JvmStatic fun of(coordinate: List<Long>) = builder().coordinate(coordinate).build()
    }

    /** A builder for [ComputerMouseMoveInput]. */
    class Builder internal constructor() {

        private var coordinate: JsonField<MutableList<Long>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(computerMouseMoveInput: ComputerMouseMoveInput) = apply {
            coordinate =
                computerMouseMoveInput.coordinate
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            additionalProperties = computerMouseMoveInput.additionalProperties.toMutableMap()
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
         * Returns an immutable instance of [ComputerMouseMoveInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .coordinate()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ComputerMouseMoveInput =
            ComputerMouseMoveInput(
                checkRequired("coordinate", coordinate).map { it.toImmutable() },
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
    fun validate(): ComputerMouseMoveInput = apply {
        if (validated) {
            return@apply
        }

        coordinate()
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
    @JvmSynthetic internal fun validity(): Int = (coordinate.asKnown().getOrNull()?.size ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ComputerMouseMoveInput &&
            coordinate == other.coordinate &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(coordinate, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ComputerMouseMoveInput{coordinate=$coordinate, additionalProperties=$additionalProperties}"
}
