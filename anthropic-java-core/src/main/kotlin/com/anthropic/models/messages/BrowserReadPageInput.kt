package com.anthropic.models.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
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
 * Return a structured accessibility tree of the page (or the subtree rooted at `ref`), with element
 * references like [ref_7] that can be used as targets on later actions. Output is capped at 50,000
 * characters — narrow with `ref` or a smaller `depth` when exceeded.
 */
class BrowserReadPageInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val depth: JsonField<Long>,
    private val filter: JsonField<BrowserReadPageFilter>,
    private val ref: JsonField<String>,
    private val tabId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("depth") @ExcludeMissing depth: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("filter")
        @ExcludeMissing
        filter: JsonField<BrowserReadPageFilter> = JsonMissing.of(),
        @JsonProperty("ref") @ExcludeMissing ref: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tab_id") @ExcludeMissing tabId: JsonField<String> = JsonMissing.of(),
    ) : this(depth, filter, ref, tabId, mutableMapOf())

    /**
     * Maximum tree depth. Default 15.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun depth(): Optional<Long> = depth.getOptional("depth")

    /**
     * Which elements to include. Omitted: every visible element. "interactive": interactive
     * elements only. "all": additionally includes off-viewport elements.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun filter(): Optional<BrowserReadPageFilter> = filter.getOptional("filter")

    /**
     * Element reference to read a subtree from. Omit to read from the page root.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun ref(): Optional<String> = ref.getOptional("ref")

    /**
     * Tab to act on. Defaults to the active tab when omitted.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tabId(): Optional<String> = tabId.getOptional("tab_id")

    /**
     * Returns the raw JSON value of [depth].
     *
     * Unlike [depth], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("depth") @ExcludeMissing fun _depth(): JsonField<Long> = depth

    /**
     * Returns the raw JSON value of [filter].
     *
     * Unlike [filter], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("filter") @ExcludeMissing fun _filter(): JsonField<BrowserReadPageFilter> = filter

    /**
     * Returns the raw JSON value of [ref].
     *
     * Unlike [ref], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("ref") @ExcludeMissing fun _ref(): JsonField<String> = ref

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

        /** Returns a mutable builder for constructing an instance of [BrowserReadPageInput]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BrowserReadPageInput]. */
    class Builder internal constructor() {

        private var depth: JsonField<Long> = JsonMissing.of()
        private var filter: JsonField<BrowserReadPageFilter> = JsonMissing.of()
        private var ref: JsonField<String> = JsonMissing.of()
        private var tabId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(browserReadPageInput: BrowserReadPageInput) = apply {
            depth = browserReadPageInput.depth
            filter = browserReadPageInput.filter
            ref = browserReadPageInput.ref
            tabId = browserReadPageInput.tabId
            additionalProperties = browserReadPageInput.additionalProperties.toMutableMap()
        }

        /** Maximum tree depth. Default 15. */
        fun depth(depth: Long?) = depth(JsonField.ofNullable(depth))

        /**
         * Alias for [Builder.depth].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun depth(depth: Long) = depth(depth as Long?)

        /** Alias for calling [Builder.depth] with `depth.orElse(null)`. */
        fun depth(depth: Optional<Long>) = depth(depth.getOrNull())

        /**
         * Sets [Builder.depth] to an arbitrary JSON value.
         *
         * You should usually call [Builder.depth] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun depth(depth: JsonField<Long>) = apply { this.depth = depth }

        /**
         * Which elements to include. Omitted: every visible element. "interactive": interactive
         * elements only. "all": additionally includes off-viewport elements.
         */
        fun filter(filter: BrowserReadPageFilter?) = filter(JsonField.ofNullable(filter))

        /** Alias for calling [Builder.filter] with `filter.orElse(null)`. */
        fun filter(filter: Optional<BrowserReadPageFilter>) = filter(filter.getOrNull())

        /**
         * Sets [Builder.filter] to an arbitrary JSON value.
         *
         * You should usually call [Builder.filter] with a well-typed [BrowserReadPageFilter] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun filter(filter: JsonField<BrowserReadPageFilter>) = apply { this.filter = filter }

        /** Element reference to read a subtree from. Omit to read from the page root. */
        fun ref(ref: String?) = ref(JsonField.ofNullable(ref))

        /** Alias for calling [Builder.ref] with `ref.orElse(null)`. */
        fun ref(ref: Optional<String>) = ref(ref.getOrNull())

        /**
         * Sets [Builder.ref] to an arbitrary JSON value.
         *
         * You should usually call [Builder.ref] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun ref(ref: JsonField<String>) = apply { this.ref = ref }

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
         * Returns an immutable instance of [BrowserReadPageInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BrowserReadPageInput =
            BrowserReadPageInput(depth, filter, ref, tabId, additionalProperties.toMutableMap())
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
    fun validate(): BrowserReadPageInput = apply {
        if (validated) {
            return@apply
        }

        depth()
        filter().ifPresent { it.validate() }
        ref()
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
        (if (depth.asKnown().isPresent) 1 else 0) +
            (filter.asKnown().getOrNull()?.validity() ?: 0) +
            (if (ref.asKnown().isPresent) 1 else 0) +
            (if (tabId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BrowserReadPageInput &&
            depth == other.depth &&
            filter == other.filter &&
            ref == other.ref &&
            tabId == other.tabId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(depth, filter, ref, tabId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BrowserReadPageInput{depth=$depth, filter=$filter, ref=$ref, tabId=$tabId, additionalProperties=$additionalProperties}"
}
