package com.anthropic.models.beta.organization.telemetrydestinations

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
 * The settings for the traces that a telemetry destination receives, as a create or update request
 * sets them. An omitted field takes its default on create and keeps its stored value on update.
 */
class BetaTelemetryDestinationTracesParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val contentMode: JsonField<BetaTelemetryDestinationContentMode>,
    private val enabled: JsonField<Boolean>,
    private val endpointUrl: JsonField<String>,
    private val maxContentBytes: JsonField<Int>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content_mode")
        @ExcludeMissing
        contentMode: JsonField<BetaTelemetryDestinationContentMode> = JsonMissing.of(),
        @JsonProperty("enabled") @ExcludeMissing enabled: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("endpoint_url")
        @ExcludeMissing
        endpointUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("max_content_bytes")
        @ExcludeMissing
        maxContentBytes: JsonField<Int> = JsonMissing.of(),
    ) : this(contentMode, enabled, endpointUrl, maxContentBytes, mutableMapOf())

    /**
     * What the exported spans contain. Defaults to `metadata_only` on create.
     *
     * On update, `null` sets `metadata_only`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun contentMode(): Optional<BetaTelemetryDestinationContentMode> =
        contentMode.getOptional("content_mode")

    /**
     * Whether the destination receives traces. Defaults to true on create. It cannot be null.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun enabled(): Optional<Boolean> = enabled.getOptional("enabled")

    /**
     * The full URL of your collector's traces endpoint, for a collector that takes traces on a path
     * other than `/v1/traces`. The API posts traces to it exactly as given and appends nothing.
     *
     * The same rules as for `endpoint_url` apply, except that any path is allowed. It must have the
     * same scheme, host, and port as `endpoint_url`. At most 2,048 characters.
     *
     * On create, `null` is the same as leaving it out. On update, `null` removes it. The API then
     * posts traces to `endpoint_url` with `/v1/traces` appended. A change of this URL alone keeps
     * the registered headers.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun endpointUrl(): Optional<String> = endpointUrl.getOptional("endpoint_url")

    /**
     * The most bytes of content that one exported span can contain, from 1 up to the API's maximum.
     * `null` sets no bound, so only the API's maximum applies.
     *
     * On create, leaving it out is the same as `null`. The error for a value above the maximum
     * states the maximum.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun maxContentBytes(): Optional<Int> = maxContentBytes.getOptional("max_content_bytes")

    /**
     * Returns the raw JSON value of [contentMode].
     *
     * Unlike [contentMode], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content_mode")
    @ExcludeMissing
    fun _contentMode(): JsonField<BetaTelemetryDestinationContentMode> = contentMode

    /**
     * Returns the raw JSON value of [enabled].
     *
     * Unlike [enabled], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("enabled") @ExcludeMissing fun _enabled(): JsonField<Boolean> = enabled

    /**
     * Returns the raw JSON value of [endpointUrl].
     *
     * Unlike [endpointUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("endpoint_url")
    @ExcludeMissing
    fun _endpointUrl(): JsonField<String> = endpointUrl

    /**
     * Returns the raw JSON value of [maxContentBytes].
     *
     * Unlike [maxContentBytes], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("max_content_bytes")
    @ExcludeMissing
    fun _maxContentBytes(): JsonField<Int> = maxContentBytes

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
         * [BetaTelemetryDestinationTracesParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTelemetryDestinationTracesParams]. */
    class Builder internal constructor() {

        private var contentMode: JsonField<BetaTelemetryDestinationContentMode> = JsonMissing.of()
        private var enabled: JsonField<Boolean> = JsonMissing.of()
        private var endpointUrl: JsonField<String> = JsonMissing.of()
        private var maxContentBytes: JsonField<Int> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaTelemetryDestinationTracesParams: BetaTelemetryDestinationTracesParams
        ) = apply {
            contentMode = betaTelemetryDestinationTracesParams.contentMode
            enabled = betaTelemetryDestinationTracesParams.enabled
            endpointUrl = betaTelemetryDestinationTracesParams.endpointUrl
            maxContentBytes = betaTelemetryDestinationTracesParams.maxContentBytes
            additionalProperties =
                betaTelemetryDestinationTracesParams.additionalProperties.toMutableMap()
        }

        /**
         * What the exported spans contain. Defaults to `metadata_only` on create.
         *
         * On update, `null` sets `metadata_only`.
         */
        fun contentMode(contentMode: BetaTelemetryDestinationContentMode?) =
            contentMode(JsonField.ofNullable(contentMode))

        /** Alias for calling [Builder.contentMode] with `contentMode.orElse(null)`. */
        fun contentMode(contentMode: Optional<BetaTelemetryDestinationContentMode>) =
            contentMode(contentMode.getOrNull())

        /**
         * Sets [Builder.contentMode] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contentMode] with a well-typed
         * [BetaTelemetryDestinationContentMode] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun contentMode(contentMode: JsonField<BetaTelemetryDestinationContentMode>) = apply {
            this.contentMode = contentMode
        }

        /**
         * Whether the destination receives traces. Defaults to true on create. It cannot be null.
         */
        fun enabled(enabled: Boolean) = enabled(JsonField.of(enabled))

        /**
         * Sets [Builder.enabled] to an arbitrary JSON value.
         *
         * You should usually call [Builder.enabled] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun enabled(enabled: JsonField<Boolean>) = apply { this.enabled = enabled }

        /**
         * The full URL of your collector's traces endpoint, for a collector that takes traces on a
         * path other than `/v1/traces`. The API posts traces to it exactly as given and appends
         * nothing.
         *
         * The same rules as for `endpoint_url` apply, except that any path is allowed. It must have
         * the same scheme, host, and port as `endpoint_url`. At most 2,048 characters.
         *
         * On create, `null` is the same as leaving it out. On update, `null` removes it. The API
         * then posts traces to `endpoint_url` with `/v1/traces` appended. A change of this URL
         * alone keeps the registered headers.
         */
        fun endpointUrl(endpointUrl: String?) = endpointUrl(JsonField.ofNullable(endpointUrl))

        /** Alias for calling [Builder.endpointUrl] with `endpointUrl.orElse(null)`. */
        fun endpointUrl(endpointUrl: Optional<String>) = endpointUrl(endpointUrl.getOrNull())

        /**
         * Sets [Builder.endpointUrl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.endpointUrl] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun endpointUrl(endpointUrl: JsonField<String>) = apply { this.endpointUrl = endpointUrl }

        /**
         * The most bytes of content that one exported span can contain, from 1 up to the API's
         * maximum. `null` sets no bound, so only the API's maximum applies.
         *
         * On create, leaving it out is the same as `null`. The error for a value above the maximum
         * states the maximum.
         */
        fun maxContentBytes(maxContentBytes: Int?) =
            maxContentBytes(JsonField.ofNullable(maxContentBytes))

        /**
         * Alias for [Builder.maxContentBytes].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxContentBytes(maxContentBytes: Int) = maxContentBytes(maxContentBytes as Int?)

        /** Alias for calling [Builder.maxContentBytes] with `maxContentBytes.orElse(null)`. */
        fun maxContentBytes(maxContentBytes: Optional<Int>) =
            maxContentBytes(maxContentBytes.getOrNull())

        /**
         * Sets [Builder.maxContentBytes] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxContentBytes] with a well-typed [Int] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun maxContentBytes(maxContentBytes: JsonField<Int>) = apply {
            this.maxContentBytes = maxContentBytes
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
         * Returns an immutable instance of [BetaTelemetryDestinationTracesParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaTelemetryDestinationTracesParams =
            BetaTelemetryDestinationTracesParams(
                contentMode,
                enabled,
                endpointUrl,
                maxContentBytes,
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
    fun validate(): BetaTelemetryDestinationTracesParams = apply {
        if (validated) {
            return@apply
        }

        contentMode().ifPresent { it.validate() }
        enabled()
        endpointUrl()
        maxContentBytes()
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
        (contentMode.asKnown().getOrNull()?.validity() ?: 0) +
            (if (enabled.asKnown().isPresent) 1 else 0) +
            (if (endpointUrl.asKnown().isPresent) 1 else 0) +
            (if (maxContentBytes.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTelemetryDestinationTracesParams &&
            contentMode == other.contentMode &&
            enabled == other.enabled &&
            endpointUrl == other.endpointUrl &&
            maxContentBytes == other.maxContentBytes &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(contentMode, enabled, endpointUrl, maxContentBytes, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTelemetryDestinationTracesParams{contentMode=$contentMode, enabled=$enabled, endpointUrl=$endpointUrl, maxContentBytes=$maxContentBytes, additionalProperties=$additionalProperties}"
}
