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

/**
 * A file download that finished during this call, reported with the same `download_id` as its
 * `download_started` — or without a prior `download_started`, when the download finished during the
 * call that started it (at most one state change per `download_id` per result).
 */
class BetaBrowserStateChangeDownloadCompleted
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val downloadId: JsonField<String>,
    private val type: JsonValue,
    private val url: JsonField<String>,
    private val path: JsonField<String>,
    private val sizeBytes: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("download_id")
        @ExcludeMissing
        downloadId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        @JsonProperty("path") @ExcludeMissing path: JsonField<String> = JsonMissing.of(),
        @JsonProperty("size_bytes") @ExcludeMissing sizeBytes: JsonField<Long> = JsonMissing.of(),
    ) : this(downloadId, type, url, path, sizeBytes, mutableMapOf())

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
     * JsonValue.from("download_completed")
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
     * Where the executor saved the file, on the executor's filesystem. Only included when another
     * tool in the same environment can read the file at that path.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun path(): Optional<String> = path.getOptional("path")

    /**
     * The completed download's size.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun sizeBytes(): Optional<Long> = sizeBytes.getOptional("size_bytes")

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
     * Returns the raw JSON value of [path].
     *
     * Unlike [path], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("path") @ExcludeMissing fun _path(): JsonField<String> = path

    /**
     * Returns the raw JSON value of [sizeBytes].
     *
     * Unlike [sizeBytes], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("size_bytes") @ExcludeMissing fun _sizeBytes(): JsonField<Long> = sizeBytes

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
         * [BetaBrowserStateChangeDownloadCompleted].
         *
         * The following fields are required:
         * ```java
         * .downloadId()
         * .url()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaBrowserStateChangeDownloadCompleted]. */
    class Builder internal constructor() {

        private var downloadId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("download_completed")
        private var url: JsonField<String>? = null
        private var path: JsonField<String> = JsonMissing.of()
        private var sizeBytes: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaBrowserStateChangeDownloadCompleted: BetaBrowserStateChangeDownloadCompleted
        ) = apply {
            downloadId = betaBrowserStateChangeDownloadCompleted.downloadId
            type = betaBrowserStateChangeDownloadCompleted.type
            url = betaBrowserStateChangeDownloadCompleted.url
            path = betaBrowserStateChangeDownloadCompleted.path
            sizeBytes = betaBrowserStateChangeDownloadCompleted.sizeBytes
            additionalProperties =
                betaBrowserStateChangeDownloadCompleted.additionalProperties.toMutableMap()
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
         * JsonValue.from("download_completed")
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

        /**
         * Where the executor saved the file, on the executor's filesystem. Only included when
         * another tool in the same environment can read the file at that path.
         */
        fun path(path: String?) = path(JsonField.ofNullable(path))

        /** Alias for calling [Builder.path] with `path.orElse(null)`. */
        fun path(path: Optional<String>) = path(path.getOrNull())

        /**
         * Sets [Builder.path] to an arbitrary JSON value.
         *
         * You should usually call [Builder.path] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun path(path: JsonField<String>) = apply { this.path = path }

        /** The completed download's size. */
        fun sizeBytes(sizeBytes: Long?) = sizeBytes(JsonField.ofNullable(sizeBytes))

        /**
         * Alias for [Builder.sizeBytes].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun sizeBytes(sizeBytes: Long) = sizeBytes(sizeBytes as Long?)

        /** Alias for calling [Builder.sizeBytes] with `sizeBytes.orElse(null)`. */
        fun sizeBytes(sizeBytes: Optional<Long>) = sizeBytes(sizeBytes.getOrNull())

        /**
         * Sets [Builder.sizeBytes] to an arbitrary JSON value.
         *
         * You should usually call [Builder.sizeBytes] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun sizeBytes(sizeBytes: JsonField<Long>) = apply { this.sizeBytes = sizeBytes }

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
         * Returns an immutable instance of [BetaBrowserStateChangeDownloadCompleted].
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
        fun build(): BetaBrowserStateChangeDownloadCompleted =
            BetaBrowserStateChangeDownloadCompleted(
                checkRequired("downloadId", downloadId),
                type,
                checkRequired("url", url),
                path,
                sizeBytes,
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
    fun validate(): BetaBrowserStateChangeDownloadCompleted = apply {
        if (validated) {
            return@apply
        }

        downloadId()
        _type().let {
            if (it != JsonValue.from("download_completed")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        url()
        path()
        sizeBytes()
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
            type.let { if (it == JsonValue.from("download_completed")) 1 else 0 } +
            (if (url.asKnown().isPresent) 1 else 0) +
            (if (path.asKnown().isPresent) 1 else 0) +
            (if (sizeBytes.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaBrowserStateChangeDownloadCompleted &&
            downloadId == other.downloadId &&
            type == other.type &&
            url == other.url &&
            path == other.path &&
            sizeBytes == other.sizeBytes &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(downloadId, type, url, path, sizeBytes, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaBrowserStateChangeDownloadCompleted{downloadId=$downloadId, type=$type, url=$url, path=$path, sizeBytes=$sizeBytes, additionalProperties=$additionalProperties}"
}
