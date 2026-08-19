// File generated from our OpenAPI spec by Stainless.

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

/** A file download that failed — or was cancelled — during this call. */
class BetaBrowserStateChangeDownloadFailed
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val downloadId: JsonField<String>,
    private val type: JsonValue,
    private val url: JsonField<String>,
    private val error: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("download_id")
        @ExcludeMissing
        downloadId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        @JsonProperty("error") @ExcludeMissing error: JsonField<String> = JsonMissing.of(),
    ) : this(downloadId, type, url, error, mutableMapOf())

    /**
     * The caller-assigned identifier for this download, stable across the state changes reporting
     * it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun downloadId(): String = downloadId.getRequired("download_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("download_failed")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * The final post-redirect URL the download was served from.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun url(): String = url.getRequired("url")

    /**
     * The failure or cancellation detail, when known.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun error(): Optional<String> = error.getOptional("error")

    /**
     * Returns the raw JSON value of [downloadId].
     *
     * Unlike [downloadId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("download_id") @ExcludeMissing fun _downloadId(): JsonField<String> = downloadId

    /**
     * Returns the raw JSON value of [url].
     *
     * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

    /**
     * Returns the raw JSON value of [error].
     *
     * Unlike [error], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<String> = error

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
         * [BetaBrowserStateChangeDownloadFailed].
         *
         * The following fields are required:
         * ```java
         * .downloadId()
         * .url()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaBrowserStateChangeDownloadFailed]. */
    class Builder internal constructor() {

        private var downloadId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("download_failed")
        private var url: JsonField<String>? = null
        private var error: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaBrowserStateChangeDownloadFailed: BetaBrowserStateChangeDownloadFailed
        ) = apply {
            downloadId = betaBrowserStateChangeDownloadFailed.downloadId
            type = betaBrowserStateChangeDownloadFailed.type
            url = betaBrowserStateChangeDownloadFailed.url
            error = betaBrowserStateChangeDownloadFailed.error
            additionalProperties =
                betaBrowserStateChangeDownloadFailed.additionalProperties.toMutableMap()
        }

        /**
         * The caller-assigned identifier for this download, stable across the state changes
         * reporting it.
         */
        fun downloadId(downloadId: String) = downloadId(JsonField.of(downloadId))

        /**
         * Sets [Builder.downloadId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.downloadId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun downloadId(downloadId: JsonField<String>) = apply { this.downloadId = downloadId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("download_failed")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** The final post-redirect URL the download was served from. */
        fun url(url: String) = url(JsonField.of(url))

        /**
         * Sets [Builder.url] to an arbitrary JSON value.
         *
         * You should usually call [Builder.url] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun url(url: JsonField<String>) = apply { this.url = url }

        /** The failure or cancellation detail, when known. */
        fun error(error: String?) = error(JsonField.ofNullable(error))

        /** Alias for calling [Builder.error] with `error.orElse(null)`. */
        fun error(error: Optional<String>) = error(error.getOrNull())

        /**
         * Sets [Builder.error] to an arbitrary JSON value.
         *
         * You should usually call [Builder.error] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun error(error: JsonField<String>) = apply { this.error = error }

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
         * Returns an immutable instance of [BetaBrowserStateChangeDownloadFailed].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .downloadId()
         * .url()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaBrowserStateChangeDownloadFailed =
            BetaBrowserStateChangeDownloadFailed(
                checkRequired("downloadId", downloadId),
                type,
                checkRequired("url", url),
                error,
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
    fun validate(): BetaBrowserStateChangeDownloadFailed = apply {
        if (validated) {
            return@apply
        }

        downloadId()
        _type().let {
            if (it != JsonValue.from("download_failed")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        url()
        error()
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
        (if (downloadId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("download_failed")) 1 else 0 } +
            (if (url.asKnown().isPresent) 1 else 0) +
            (if (error.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaBrowserStateChangeDownloadFailed &&
            downloadId == other.downloadId &&
            type == other.type &&
            url == other.url &&
            error == other.error &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(downloadId, type, url, error, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaBrowserStateChangeDownloadFailed{downloadId=$downloadId, type=$type, url=$url, error=$error, additionalProperties=$additionalProperties}"
}
