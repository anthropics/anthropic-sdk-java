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

/** Left-click at a viewport coordinate or on an element by reference. */
class BrowserLeftClickInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val target: JsonField<BrowserClickTarget>,
    private val modifiers: JsonField<String>,
    private val tabId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("target")
        @ExcludeMissing
        target: JsonField<BrowserClickTarget> = JsonMissing.of(),
        @JsonProperty("modifiers") @ExcludeMissing modifiers: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tab_id") @ExcludeMissing tabId: JsonField<String> = JsonMissing.of(),
    ) : this(target, modifiers, tabId, mutableMapOf())

    /**
     * Where to act: either a viewport coordinate or an element reference.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun target(): BrowserClickTarget = target.getRequired("target")

    /**
     * Optional modifier key chord to hold for the duration of this action (e.g. "shift",
     * "ctrl+shift", "cmd+alt").
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun modifiers(): Optional<String> = modifiers.getOptional("modifiers")

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
    @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<BrowserClickTarget> = target

    /**
     * Returns the raw JSON value of [modifiers].
     *
     * Unlike [modifiers], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("modifiers") @ExcludeMissing fun _modifiers(): JsonField<String> = modifiers

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
         * Returns a mutable builder for constructing an instance of [BrowserLeftClickInput].
         *
         * The following fields are required:
         * ```java
         * .target()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BrowserLeftClickInput] with the required [target] set
         * to the given value.
         */
        @JvmStatic fun of(target: BrowserClickTarget) = builder().target(target).build()
    }

    /** A builder for [BrowserLeftClickInput]. */
    class Builder internal constructor() {

        private var target: JsonField<BrowserClickTarget>? = null
        private var modifiers: JsonField<String> = JsonMissing.of()
        private var tabId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(browserLeftClickInput: BrowserLeftClickInput) = apply {
            target = browserLeftClickInput.target
            modifiers = browserLeftClickInput.modifiers
            tabId = browserLeftClickInput.tabId
            additionalProperties = browserLeftClickInput.additionalProperties.toMutableMap()
        }

        /** Where to act: either a viewport coordinate or an element reference. */
        fun target(target: BrowserClickTarget) = target(JsonField.of(target))

        /**
         * Sets [Builder.target] to an arbitrary JSON value.
         *
         * You should usually call [Builder.target] with a well-typed [BrowserClickTarget] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun target(target: JsonField<BrowserClickTarget>) = apply { this.target = target }

        /** Alias for calling [target] with `BrowserClickTarget.ofCoordinate(coordinate)`. */
        fun target(coordinate: BrowserCoordinateTarget) =
            target(BrowserClickTarget.ofCoordinate(coordinate))

        /** Alias for calling [target] with `BrowserClickTarget.ofRef(ref)`. */
        fun target(ref: BrowserRefTarget) = target(BrowserClickTarget.ofRef(ref))

        /**
         * Alias for calling [target] with the following:
         * ```java
         * BrowserRefTarget.builder()
         *     .ref(ref)
         *     .build()
         * ```
         */
        fun refTarget(ref: String) = target(BrowserRefTarget.builder().ref(ref).build())

        /**
         * Optional modifier key chord to hold for the duration of this action (e.g. "shift",
         * "ctrl+shift", "cmd+alt").
         */
        fun modifiers(modifiers: String?) = modifiers(JsonField.ofNullable(modifiers))

        /** Alias for calling [Builder.modifiers] with `modifiers.orElse(null)`. */
        fun modifiers(modifiers: Optional<String>) = modifiers(modifiers.getOrNull())

        /**
         * Sets [Builder.modifiers] to an arbitrary JSON value.
         *
         * You should usually call [Builder.modifiers] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun modifiers(modifiers: JsonField<String>) = apply { this.modifiers = modifiers }

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
         * Returns an immutable instance of [BrowserLeftClickInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .target()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BrowserLeftClickInput =
            BrowserLeftClickInput(
                checkRequired("target", target),
                modifiers,
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
    fun validate(): BrowserLeftClickInput = apply {
        if (validated) {
            return@apply
        }

        target().validate()
        modifiers()
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
            (if (modifiers.asKnown().isPresent) 1 else 0) +
            (if (tabId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BrowserLeftClickInput &&
            target == other.target &&
            modifiers == other.modifiers &&
            tabId == other.tabId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(target, modifiers, tabId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BrowserLeftClickInput{target=$target, modifiers=$modifiers, tabId=$tabId, additionalProperties=$additionalProperties}"
}
