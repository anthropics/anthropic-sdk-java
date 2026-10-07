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
 * Set the value of a form element (input, textarea, select, checkbox). Use a boolean for
 * checkboxes, an option value or text for selects.
 */
class BetaBrowserFormInputInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val target: JsonField<BetaBrowserRefTarget>,
    private val value: JsonField<BetaBrowserFormInputValue>,
    private val tabId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("target")
        @ExcludeMissing
        target: JsonField<BetaBrowserRefTarget> = JsonMissing.of(),
        @JsonProperty("value")
        @ExcludeMissing
        value: JsonField<BetaBrowserFormInputValue> = JsonMissing.of(),
        @JsonProperty("tab_id") @ExcludeMissing tabId: JsonField<String> = JsonMissing.of(),
    ) : this(target, value, tabId, mutableMapOf())

    /**
     * An element on the page, identified by a reference from a prior `read_page` or `find` result.
     * References are scoped to the tab that produced them and become stale after navigation or a
     * major re-render.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun target(): BetaBrowserRefTarget = target.getRequired("target")

    /**
     * The value to set.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun value(): BetaBrowserFormInputValue = value.getRequired("value")

    /**
     * Tab to act on. Defaults to the active tab when omitted.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tabId(): Optional<String> = tabId.getOptional("tab_id")

    /**
     * Returns the raw JSON value of [target].
     *
     * Unlike [target], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<BetaBrowserRefTarget> = target

    /**
     * Returns the raw JSON value of [value].
     *
     * Unlike [value], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("value")
    @ExcludeMissing
    fun _value(): JsonField<BetaBrowserFormInputValue> = value

    /**
     * Returns the raw JSON value of [tabId].
     *
     * Unlike [tabId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tab_id") @ExcludeMissing fun _tabId(): JsonField<String> = tabId

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
         * Returns a mutable builder for constructing an instance of [BetaBrowserFormInputInput].
         *
         * The following fields are required:
         * ```java
         * .target()
         * .value()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaBrowserFormInputInput]. */
    class Builder internal constructor() {

        private var target: JsonField<BetaBrowserRefTarget>? = null
        private var value: JsonField<BetaBrowserFormInputValue>? = null
        private var tabId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaBrowserFormInputInput: BetaBrowserFormInputInput) = apply {
            target = betaBrowserFormInputInput.target
            value = betaBrowserFormInputInput.value
            tabId = betaBrowserFormInputInput.tabId
            additionalProperties = betaBrowserFormInputInput.additionalProperties.toMutableMap()
        }

        /**
         * An element on the page, identified by a reference from a prior `read_page` or `find`
         * result. References are scoped to the tab that produced them and become stale after
         * navigation or a major re-render.
         */
        fun target(target: BetaBrowserRefTarget) = target(JsonField.of(target))

        /**
         * Sets [Builder.target] to an arbitrary JSON value.
         *
         * You should usually call [Builder.target] with a well-typed [BetaBrowserRefTarget] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun target(target: JsonField<BetaBrowserRefTarget>) = apply { this.target = target }

        /** The value to set. */
        fun value(value: BetaBrowserFormInputValue) = value(JsonField.of(value))

        /**
         * Sets [Builder.value] to an arbitrary JSON value.
         *
         * You should usually call [Builder.value] with a well-typed [BetaBrowserFormInputValue]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun value(value: JsonField<BetaBrowserFormInputValue>) = apply { this.value = value }

        /** Alias for calling [value] with `BetaBrowserFormInputValue.ofString(string)`. */
        fun value(string: String) = value(BetaBrowserFormInputValue.ofString(string))

        /** Alias for calling [value] with `BetaBrowserFormInputValue.ofNumber(number)`. */
        fun value(number: Double) = value(BetaBrowserFormInputValue.ofNumber(number))

        /** Alias for calling [value] with `BetaBrowserFormInputValue.ofBool(bool)`. */
        fun value(bool: Boolean) = value(BetaBrowserFormInputValue.ofBool(bool))

        /** Tab to act on. Defaults to the active tab when omitted. */
        fun tabId(tabId: String?) = tabId(JsonField.ofNullable(tabId))

        /** Alias for calling [Builder.tabId] with `tabId.orElse(null)`. */
        fun tabId(tabId: Optional<String>) = tabId(tabId.getOrNull())

        /**
         * Sets [Builder.tabId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tabId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun tabId(tabId: JsonField<String>) = apply { this.tabId = tabId }

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
         * Returns an immutable instance of [BetaBrowserFormInputInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .target()
         * .value()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaBrowserFormInputInput =
            BetaBrowserFormInputInput(
                checkRequired("target", target),
                checkRequired("value", value),
                tabId,
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
    fun validate(): BetaBrowserFormInputInput = apply {
        if (validated) {
            return@apply
        }

        target().validate()
        value().validate()
        tabId()
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
        (target.asKnown().getOrNull()?.validity() ?: 0) +
            (value.asKnown().getOrNull()?.validity() ?: 0) +
            (if (tabId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaBrowserFormInputInput &&
            target == other.target &&
            value == other.value &&
            tabId == other.tabId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(target, value, tabId, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaBrowserFormInputInput{target=$target, value=$value, tabId=$tabId, additionalProperties=$additionalProperties}"
}
