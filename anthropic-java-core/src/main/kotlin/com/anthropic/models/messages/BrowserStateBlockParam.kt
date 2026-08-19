// File generated from our OpenAPI spec by Stainless.

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
 * The caller's browser state after a browser toolset member call — the full inventory of open tabs,
 * which tab is active, and any side effects (tabs opened, download state changes) the call
 * produced.
 *
 * At most one per `tool_result`, only on a non-error result answering a browser toolset member
 * `tool_use`. The server renders the model-visible text from it; the model never sees the raw
 * fields.
 */
class BrowserStateBlockParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val tabs: JsonField<List<BrowserStateTabEntry>>,
    private val type: JsonValue,
    private val cacheControl: JsonField<CacheControlEphemeral>,
    private val stateChanges: JsonField<List<BrowserStateChange>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("tabs")
        @ExcludeMissing
        tabs: JsonField<List<BrowserStateTabEntry>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("state_changes")
        @ExcludeMissing
        stateChanges: JsonField<List<BrowserStateChange>> = JsonMissing.of(),
    ) : this(tabs, type, cacheControl, stateChanges, mutableMapOf())

    /**
     * All tabs open in the browser after this call — the full inventory, not a delta. May be empty.
     * Whenever non-empty, exactly one entry carries `active: true`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tabs(): List<BrowserStateTabEntry> = tabs.getRequired("tabs")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("browser_state")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Create a cache control breakpoint at this content block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<CacheControlEphemeral> = cacheControl.getOptional("cache_control")

    /**
     * Tabs opened and download state changes during this call. "Nothing to report" is expressed by
     * omitting the field, never by an empty list.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun stateChanges(): Optional<List<BrowserStateChange>> =
        stateChanges.getOptional("state_changes")

    /**
     * Returns the raw JSON value of [tabs].
     *
     * Unlike [tabs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tabs") @ExcludeMissing fun _tabs(): JsonField<List<BrowserStateTabEntry>> = tabs

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<CacheControlEphemeral> = cacheControl

    /**
     * Returns the raw JSON value of [stateChanges].
     *
     * Unlike [stateChanges], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("state_changes")
    @ExcludeMissing
    fun _stateChanges(): JsonField<List<BrowserStateChange>> = stateChanges

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
         * Returns a mutable builder for constructing an instance of [BrowserStateBlockParam].
         *
         * The following fields are required:
         * ```java
         * .tabs()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BrowserStateBlockParam] with the required [tabs] set to
         * the given value.
         */
        @JvmStatic fun of(tabs: List<BrowserStateTabEntry>) = builder().tabs(tabs).build()
    }

    /** A builder for [BrowserStateBlockParam]. */
    class Builder internal constructor() {

        private var tabs: JsonField<MutableList<BrowserStateTabEntry>>? = null
        private var type: JsonValue = JsonValue.from("browser_state")
        private var cacheControl: JsonField<CacheControlEphemeral> = JsonMissing.of()
        private var stateChanges: JsonField<MutableList<BrowserStateChange>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(browserStateBlockParam: BrowserStateBlockParam) = apply {
            tabs =
                browserStateBlockParam.tabs.map { it.toMutableList() }.takeUnless { it.isMissing() }
            type = browserStateBlockParam.type
            cacheControl = browserStateBlockParam.cacheControl
            stateChanges =
                browserStateBlockParam.stateChanges
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            additionalProperties = browserStateBlockParam.additionalProperties.toMutableMap()
        }

        /**
         * All tabs open in the browser after this call — the full inventory, not a delta. May be
         * empty. Whenever non-empty, exactly one entry carries `active: true`.
         */
        fun tabs(tabs: List<BrowserStateTabEntry>) = tabs(JsonField.of(tabs))

        /**
         * Sets [Builder.tabs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tabs] with a well-typed `List<BrowserStateTabEntry>`
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun tabs(tabs: JsonField<List<BrowserStateTabEntry>>) = apply {
            this.tabs = tabs.map { it.toMutableList() }
        }

        /**
         * Adds a single [BrowserStateTabEntry] to [tabs].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addTab(tab: BrowserStateTabEntry) = apply {
            tabs = (tabs ?: JsonField.of(mutableListOf())).also { checkKnown("tabs", it).add(tab) }
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("browser_state")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Create a cache control breakpoint at this content block. */
        fun cacheControl(cacheControl: CacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<CacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed [CacheControlEphemeral]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<CacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        /**
         * Tabs opened and download state changes during this call. "Nothing to report" is expressed
         * by omitting the field, never by an empty list.
         */
        fun stateChanges(stateChanges: List<BrowserStateChange>?) =
            stateChanges(JsonField.ofNullable(stateChanges))

        /** Alias for calling [Builder.stateChanges] with `stateChanges.orElse(null)`. */
        fun stateChanges(stateChanges: Optional<List<BrowserStateChange>>) =
            stateChanges(stateChanges.getOrNull())

        /**
         * Sets [Builder.stateChanges] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stateChanges] with a well-typed
         * `List<BrowserStateChange>` value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun stateChanges(stateChanges: JsonField<List<BrowserStateChange>>) = apply {
            this.stateChanges = stateChanges.map { it.toMutableList() }
        }

        /**
         * Adds a single [BrowserStateChange] to [stateChanges].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addStateChange(stateChange: BrowserStateChange) = apply {
            stateChanges =
                (stateChanges ?: JsonField.of(mutableListOf())).also {
                    checkKnown("stateChanges", it).add(stateChange)
                }
        }

        /** Alias for calling [addStateChange] with `BrowserStateChange.ofTabOpened(tabOpened)`. */
        fun addStateChange(tabOpened: BrowserStateChangeTabOpened) =
            addStateChange(BrowserStateChange.ofTabOpened(tabOpened))

        /**
         * Alias for calling [addStateChange] with the following:
         * ```java
         * BrowserStateChangeTabOpened.builder()
         *     .tabId(tabId)
         *     .build()
         * ```
         */
        fun addTabOpenedStateChange(tabId: String) =
            addStateChange(BrowserStateChangeTabOpened.builder().tabId(tabId).build())

        /**
         * Alias for calling [addStateChange] with
         * `BrowserStateChange.ofDownloadStarted(downloadStarted)`.
         */
        fun addStateChange(downloadStarted: BrowserStateChangeDownloadStarted) =
            addStateChange(BrowserStateChange.ofDownloadStarted(downloadStarted))

        /**
         * Alias for calling [addStateChange] with
         * `BrowserStateChange.ofDownloadCompleted(downloadCompleted)`.
         */
        fun addStateChange(downloadCompleted: BrowserStateChangeDownloadCompleted) =
            addStateChange(BrowserStateChange.ofDownloadCompleted(downloadCompleted))

        /**
         * Alias for calling [addStateChange] with
         * `BrowserStateChange.ofDownloadFailed(downloadFailed)`.
         */
        fun addStateChange(downloadFailed: BrowserStateChangeDownloadFailed) =
            addStateChange(BrowserStateChange.ofDownloadFailed(downloadFailed))

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
         * Returns an immutable instance of [BrowserStateBlockParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .tabs()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BrowserStateBlockParam =
            BrowserStateBlockParam(
                checkRequired("tabs", tabs).map { it.toImmutable() },
                type,
                cacheControl,
                (stateChanges ?: JsonMissing.of()).map { it.toImmutable() },
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
    fun validate(): BrowserStateBlockParam = apply {
        if (validated) {
            return@apply
        }

        tabs().forEach { it.validate() }
        _type().let {
            if (it != JsonValue.from("browser_state")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
        stateChanges().ifPresent { it.forEach { it.validate() } }
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
        (tabs.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            type.let { if (it == JsonValue.from("browser_state")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
            (stateChanges.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BrowserStateBlockParam &&
            tabs == other.tabs &&
            type == other.type &&
            cacheControl == other.cacheControl &&
            stateChanges == other.stateChanges &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(tabs, type, cacheControl, stateChanges, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BrowserStateBlockParam{tabs=$tabs, type=$type, cacheControl=$cacheControl, stateChanges=$stateChanges, additionalProperties=$additionalProperties}"
}
