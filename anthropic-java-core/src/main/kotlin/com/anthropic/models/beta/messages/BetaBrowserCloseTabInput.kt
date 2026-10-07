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

/** Close the tab with the given tab_id. */
class BetaBrowserCloseTabInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val tabId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("tab_id") @ExcludeMissing tabId: JsonField<String> = JsonMissing.of()
    ) : this(tabId, mutableMapOf())

    /**
     * The tab to close.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tabId(): String = tabId.getRequired("tab_id")

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
         * Returns a mutable builder for constructing an instance of [BetaBrowserCloseTabInput].
         *
         * The following fields are required:
         * ```java
         * .tabId()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaBrowserCloseTabInput] with the required [tabId] set
         * to the given value.
         */
        @JvmStatic fun of(tabId: String) = builder().tabId(tabId).build()
    }

    /** A builder for [BetaBrowserCloseTabInput]. */
    class Builder internal constructor() {

        private var tabId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaBrowserCloseTabInput: BetaBrowserCloseTabInput) = apply {
            tabId = betaBrowserCloseTabInput.tabId
            additionalProperties = betaBrowserCloseTabInput.additionalProperties.toMutableMap()
        }

        /** The tab to close. */
        fun tabId(tabId: String) = tabId(JsonField.of(tabId))

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
         * Returns an immutable instance of [BetaBrowserCloseTabInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .tabId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaBrowserCloseTabInput =
            BetaBrowserCloseTabInput(
                checkRequired("tabId", tabId),
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
    fun validate(): BetaBrowserCloseTabInput = apply {
        if (validated) {
            return@apply
        }

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
    @JvmSynthetic internal fun validity(): Int = (if (tabId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaBrowserCloseTabInput &&
            tabId == other.tabId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(tabId, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaBrowserCloseTabInput{tabId=$tabId, additionalProperties=$additionalProperties}"
}
