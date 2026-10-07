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

/** Press at `from`, drag to `target`, release. Both must be coordinate targets. */
class BetaBrowserLeftClickDragInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val from: JsonField<BetaBrowserCoordinateTarget>,
    private val target: JsonField<BetaBrowserCoordinateTarget>,
    private val tabId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("from")
        @ExcludeMissing
        from: JsonField<BetaBrowserCoordinateTarget> = JsonMissing.of(),
        @JsonProperty("target")
        @ExcludeMissing
        target: JsonField<BetaBrowserCoordinateTarget> = JsonMissing.of(),
        @JsonProperty("tab_id") @ExcludeMissing tabId: JsonField<String> = JsonMissing.of(),
    ) : this(from, target, tabId, mutableMapOf())

    /**
     * A point in the browser viewport, in viewport pixels (the same frame as a full-viewport
     * screenshot).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun from(): BetaBrowserCoordinateTarget = from.getRequired("from")

    /**
     * A point in the browser viewport, in viewport pixels (the same frame as a full-viewport
     * screenshot).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun target(): BetaBrowserCoordinateTarget = target.getRequired("target")

    /**
     * Tab to act on. Defaults to the active tab when omitted.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tabId(): Optional<String> = tabId.getOptional("tab_id")

    /**
     * Returns the raw JSON value of [from].
     *
     * Unlike [from], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("from") @ExcludeMissing fun _from(): JsonField<BetaBrowserCoordinateTarget> = from

    /**
     * Returns the raw JSON value of [target].
     *
     * Unlike [target], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("target")
    @ExcludeMissing
    fun _target(): JsonField<BetaBrowserCoordinateTarget> = target

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
         * Returns a mutable builder for constructing an instance of
         * [BetaBrowserLeftClickDragInput].
         *
         * The following fields are required:
         * ```java
         * .from()
         * .target()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaBrowserLeftClickDragInput]. */
    class Builder internal constructor() {

        private var from: JsonField<BetaBrowserCoordinateTarget>? = null
        private var target: JsonField<BetaBrowserCoordinateTarget>? = null
        private var tabId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaBrowserLeftClickDragInput: BetaBrowserLeftClickDragInput) = apply {
            from = betaBrowserLeftClickDragInput.from
            target = betaBrowserLeftClickDragInput.target
            tabId = betaBrowserLeftClickDragInput.tabId
            additionalProperties = betaBrowserLeftClickDragInput.additionalProperties.toMutableMap()
        }

        /**
         * A point in the browser viewport, in viewport pixels (the same frame as a full-viewport
         * screenshot).
         */
        fun from(from: BetaBrowserCoordinateTarget) = from(JsonField.of(from))

        /**
         * Sets [Builder.from] to an arbitrary JSON value.
         *
         * You should usually call [Builder.from] with a well-typed [BetaBrowserCoordinateTarget]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun from(from: JsonField<BetaBrowserCoordinateTarget>) = apply { this.from = from }

        /**
         * A point in the browser viewport, in viewport pixels (the same frame as a full-viewport
         * screenshot).
         */
        fun target(target: BetaBrowserCoordinateTarget) = target(JsonField.of(target))

        /**
         * Sets [Builder.target] to an arbitrary JSON value.
         *
         * You should usually call [Builder.target] with a well-typed [BetaBrowserCoordinateTarget]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun target(target: JsonField<BetaBrowserCoordinateTarget>) = apply { this.target = target }

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
         * Returns an immutable instance of [BetaBrowserLeftClickDragInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .from()
         * .target()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaBrowserLeftClickDragInput =
            BetaBrowserLeftClickDragInput(
                checkRequired("from", from),
                checkRequired("target", target),
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
    fun validate(): BetaBrowserLeftClickDragInput = apply {
        if (validated) {
            return@apply
        }

        from().validate()
        target().validate()
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
        (from.asKnown().getOrNull()?.validity() ?: 0) +
            (target.asKnown().getOrNull()?.validity() ?: 0) +
            (if (tabId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaBrowserLeftClickDragInput &&
            from == other.from &&
            target == other.target &&
            tabId == other.tabId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(from, target, tabId, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaBrowserLeftClickDragInput{from=$from, target=$target, tabId=$tabId, additionalProperties=$additionalProperties}"
}
