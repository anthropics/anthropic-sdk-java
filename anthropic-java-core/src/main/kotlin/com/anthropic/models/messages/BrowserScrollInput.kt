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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Scroll at a viewport position. `target` must be a coordinate target. */
class BrowserScrollInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val scrollDirection: JsonField<BrowserScrollDirection>,
    private val target: JsonField<BrowserCoordinateTarget>,
    private val scrollAmount: JsonField<Long>,
    private val tabId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("scroll_direction")
        @ExcludeMissing
        scrollDirection: JsonField<BrowserScrollDirection> = JsonMissing.of(),
        @JsonProperty("target")
        @ExcludeMissing
        target: JsonField<BrowserCoordinateTarget> = JsonMissing.of(),
        @JsonProperty("scroll_amount")
        @ExcludeMissing
        scrollAmount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("tab_id") @ExcludeMissing tabId: JsonField<String> = JsonMissing.of(),
    ) : this(scrollDirection, target, scrollAmount, tabId, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun scrollDirection(): BrowserScrollDirection = scrollDirection.getRequired("scroll_direction")

    /**
     * A point in the browser viewport, in viewport pixels (the same frame as a full-viewport
     * screenshot).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun target(): BrowserCoordinateTarget = target.getRequired("target")

    /**
     * Scroll-wheel notches (1–10). Default 3.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun scrollAmount(): Optional<Long> = scrollAmount.getOptional("scroll_amount")

    /**
     * Tab to act on. Defaults to the active tab when omitted.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tabId(): Optional<String> = tabId.getOptional("tab_id")

    /**
     * Returns the raw JSON value of [scrollDirection].
     *
     * Unlike [scrollDirection], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scroll_direction")
    @ExcludeMissing
    fun _scrollDirection(): JsonField<BrowserScrollDirection> = scrollDirection

    /**
     * Returns the raw JSON value of [target].
     *
     * Unlike [target], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("target")
    @ExcludeMissing
    fun _target(): JsonField<BrowserCoordinateTarget> = target

    /**
     * Returns the raw JSON value of [scrollAmount].
     *
     * Unlike [scrollAmount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scroll_amount")
    @ExcludeMissing
    fun _scrollAmount(): JsonField<Long> = scrollAmount

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
         * Returns a mutable builder for constructing an instance of [BrowserScrollInput].
         *
         * The following fields are required:
         * ```java
         * .scrollDirection()
         * .target()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BrowserScrollInput]. */
    class Builder internal constructor() {

        private var scrollDirection: JsonField<BrowserScrollDirection>? = null
        private var target: JsonField<BrowserCoordinateTarget>? = null
        private var scrollAmount: JsonField<Long> = JsonMissing.of()
        private var tabId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(browserScrollInput: BrowserScrollInput) = apply {
            scrollDirection = browserScrollInput.scrollDirection
            target = browserScrollInput.target
            scrollAmount = browserScrollInput.scrollAmount
            tabId = browserScrollInput.tabId
            additionalProperties = browserScrollInput.additionalProperties.toMutableMap()
        }

        fun scrollDirection(scrollDirection: BrowserScrollDirection) =
            scrollDirection(JsonField.of(scrollDirection))

        /**
         * Sets [Builder.scrollDirection] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scrollDirection] with a well-typed
         * [BrowserScrollDirection] value instead. This method is primarily for setting the field to
         * an undocumented or not yet supported value.
         */
        fun scrollDirection(scrollDirection: JsonField<BrowserScrollDirection>) = apply {
            this.scrollDirection = scrollDirection
        }

        /**
         * A point in the browser viewport, in viewport pixels (the same frame as a full-viewport
         * screenshot).
         */
        fun target(target: BrowserCoordinateTarget) = target(JsonField.of(target))

        /**
         * Sets [Builder.target] to an arbitrary JSON value.
         *
         * You should usually call [Builder.target] with a well-typed [BrowserCoordinateTarget]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun target(target: JsonField<BrowserCoordinateTarget>) = apply { this.target = target }

        /** Scroll-wheel notches (1–10). Default 3. */
        fun scrollAmount(scrollAmount: Long?) = scrollAmount(JsonField.ofNullable(scrollAmount))

        /**
         * Alias for [Builder.scrollAmount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun scrollAmount(scrollAmount: Long) = scrollAmount(scrollAmount as Long?)

        /** Alias for calling [Builder.scrollAmount] with `scrollAmount.orElse(null)`. */
        fun scrollAmount(scrollAmount: Optional<Long>) = scrollAmount(scrollAmount.getOrNull())

        /**
         * Sets [Builder.scrollAmount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scrollAmount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun scrollAmount(scrollAmount: JsonField<Long>) = apply { this.scrollAmount = scrollAmount }

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
         * Returns an immutable instance of [BrowserScrollInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .scrollDirection()
         * .target()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BrowserScrollInput =
            BrowserScrollInput(
                checkRequired("scrollDirection", scrollDirection),
                checkRequired("target", target),
                scrollAmount,
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
    fun validate(): BrowserScrollInput = apply {
        if (validated) {
            return@apply
        }

        scrollDirection().validate()
        target().validate()
        scrollAmount()
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
        (scrollDirection.asKnown().getOrNull()?.validity() ?: 0) +
            (target.asKnown().getOrNull()?.validity() ?: 0) +
            (if (scrollAmount.asKnown().isPresent) 1 else 0) +
            (if (tabId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BrowserScrollInput &&
            scrollDirection == other.scrollDirection &&
            target == other.target &&
            scrollAmount == other.scrollAmount &&
            tabId == other.tabId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(scrollDirection, target, scrollAmount, tabId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BrowserScrollInput{scrollDirection=$scrollDirection, target=$target, scrollAmount=$scrollAmount, tabId=$tabId, additionalProperties=$additionalProperties}"
}
