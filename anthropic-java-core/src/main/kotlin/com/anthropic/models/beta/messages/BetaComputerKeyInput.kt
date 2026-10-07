package com.anthropic.models.beta.messages

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

/**
 * Press a key or key-combination on the keyboard. Use "+" to combine modifiers with a key (e.g.
 * "ctrl+s", "alt+Tab", "ctrl+shift+Escape"). Key names are case-insensitive; common names like
 * "Return", "Tab", "Escape", "Up", "Down", "Left", "Right", "Home", "End", "Page_Up", "Page_Down",
 * "Delete", "BackSpace" are supported.
 */
class BetaComputerKeyInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val text: JsonField<String>,
    private val repeat: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("text") @ExcludeMissing text: JsonField<String> = JsonMissing.of(),
        @JsonProperty("repeat") @ExcludeMissing repeat: JsonField<Long> = JsonMissing.of(),
    ) : this(text, repeat, mutableMapOf())

    /**
     * The key or key-combination to press.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun text(): String = text.getRequired("text")

    /**
     * Number of times to repeat the key press. Default is 1.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun repeat(): Optional<Long> = repeat.getOptional("repeat")

    /**
     * Returns the raw JSON value of [text].
     *
     * Unlike [text], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("text") @ExcludeMissing fun _text(): JsonField<String> = text

    /**
     * Returns the raw JSON value of [repeat].
     *
     * Unlike [repeat], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("repeat") @ExcludeMissing fun _repeat(): JsonField<Long> = repeat

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
         * Returns a mutable builder for constructing an instance of [BetaComputerKeyInput].
         *
         * The following fields are required:
         * ```java
         * .text()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaComputerKeyInput] with the required [text] set to
         * the given value.
         */
        @JvmStatic fun of(text: String) = builder().text(text).build()
    }

    /** A builder for [BetaComputerKeyInput]. */
    class Builder internal constructor() {

        private var text: JsonField<String>? = null
        private var repeat: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaComputerKeyInput: BetaComputerKeyInput) = apply {
            text = betaComputerKeyInput.text
            repeat = betaComputerKeyInput.repeat
            additionalProperties = betaComputerKeyInput.additionalProperties.toMutableMap()
        }

        /** The key or key-combination to press. */
        fun text(text: String) = text(JsonField.of(text))

        /**
         * Sets [Builder.text] to an arbitrary JSON value.
         *
         * You should usually call [Builder.text] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun text(text: JsonField<String>) = apply { this.text = text }

        /** Number of times to repeat the key press. Default is 1. */
        fun repeat(repeat: Long?) = repeat(JsonField.ofNullable(repeat))

        /**
         * Alias for [Builder.repeat].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun repeat(repeat: Long) = repeat(repeat as Long?)

        /** Alias for calling [Builder.repeat] with `repeat.orElse(null)`. */
        fun repeat(repeat: Optional<Long>) = repeat(repeat.getOrNull())

        /**
         * Sets [Builder.repeat] to an arbitrary JSON value.
         *
         * You should usually call [Builder.repeat] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun repeat(repeat: JsonField<Long>) = apply { this.repeat = repeat }

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
         * Returns an immutable instance of [BetaComputerKeyInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .text()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaComputerKeyInput =
            BetaComputerKeyInput(
                checkRequired("text", text),
                repeat,
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
    fun validate(): BetaComputerKeyInput = apply {
        if (validated) {
            return@apply
        }

        text()
        repeat()
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
        (if (text.asKnown().isPresent) 1 else 0) + (if (repeat.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComputerKeyInput &&
            text == other.text &&
            repeat == other.repeat &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(text, repeat, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaComputerKeyInput{text=$text, repeat=$repeat, additionalProperties=$additionalProperties}"
}
