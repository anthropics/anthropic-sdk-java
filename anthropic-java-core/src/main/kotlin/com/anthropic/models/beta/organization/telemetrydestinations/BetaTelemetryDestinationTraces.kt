package com.anthropic.models.beta.organization.telemetrydestinations

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

/** A telemetry destination's settings for the traces it receives. */
class BetaTelemetryDestinationTraces
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
     * What the exported spans contain.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun contentMode(): BetaTelemetryDestinationContentMode = contentMode.getRequired("content_mode")

    /**
     * Whether the destination receives traces. A destination whose `enabled` is false keeps its
     * other settings.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun enabled(): Boolean = enabled.getRequired("enabled")

    /**
     * The full URL that the API posts traces to, exactly as registered, or `null` if the
     * destination doesn't have one.
     *
     * When it is `null`, the API posts traces to `endpoint_url` with `/v1/traces` appended.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun endpointUrl(): Optional<String> = endpointUrl.getOptional("endpoint_url")

    /**
     * The most bytes of content that one exported span can contain, or `null` if only the API's
     * maximum applies.
     *
     * Content means model prompts and responses, and tool inputs and outputs. The API shortens
     * longer content to this size before it delivers the span. A destination whose `content_mode`
     * is `metadata_only` keeps this value. It applies once `content_mode` is `content`.
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
         * [BetaTelemetryDestinationTraces].
         *
         * The following fields are required:
         * ```java
         * .contentMode()
         * .enabled()
         * .endpointUrl()
         * .maxContentBytes()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTelemetryDestinationTraces]. */
    class Builder internal constructor() {

        private var contentMode: JsonField<BetaTelemetryDestinationContentMode>? = null
        private var enabled: JsonField<Boolean>? = null
        private var endpointUrl: JsonField<String>? = null
        private var maxContentBytes: JsonField<Int>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaTelemetryDestinationTraces: BetaTelemetryDestinationTraces) = apply {
            contentMode = betaTelemetryDestinationTraces.contentMode
            enabled = betaTelemetryDestinationTraces.enabled
            endpointUrl = betaTelemetryDestinationTraces.endpointUrl
            maxContentBytes = betaTelemetryDestinationTraces.maxContentBytes
            additionalProperties =
                betaTelemetryDestinationTraces.additionalProperties.toMutableMap()
        }

        /** What the exported spans contain. */
        fun contentMode(contentMode: BetaTelemetryDestinationContentMode) =
            contentMode(JsonField.of(contentMode))

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
         * Whether the destination receives traces. A destination whose `enabled` is false keeps its
         * other settings.
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
         * The full URL that the API posts traces to, exactly as registered, or `null` if the
         * destination doesn't have one.
         *
         * When it is `null`, the API posts traces to `endpoint_url` with `/v1/traces` appended.
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
         * The most bytes of content that one exported span can contain, or `null` if only the API's
         * maximum applies.
         *
         * Content means model prompts and responses, and tool inputs and outputs. The API shortens
         * longer content to this size before it delivers the span. A destination whose
         * `content_mode` is `metadata_only` keeps this value. It applies once `content_mode` is
         * `content`.
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
         * Returns an immutable instance of [BetaTelemetryDestinationTraces].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .contentMode()
         * .enabled()
         * .endpointUrl()
         * .maxContentBytes()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaTelemetryDestinationTraces =
            BetaTelemetryDestinationTraces(
                checkRequired("contentMode", contentMode),
                checkRequired("enabled", enabled),
                checkRequired("endpointUrl", endpointUrl),
                checkRequired("maxContentBytes", maxContentBytes),
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
    fun validate(): BetaTelemetryDestinationTraces = apply {
        if (validated) {
            return@apply
        }

        contentMode().validate()
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

        return other is BetaTelemetryDestinationTraces &&
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
        "BetaTelemetryDestinationTraces{contentMode=$contentMode, enabled=$enabled, endpointUrl=$endpointUrl, maxContentBytes=$maxContentBytes, additionalProperties=$additionalProperties}"
}
