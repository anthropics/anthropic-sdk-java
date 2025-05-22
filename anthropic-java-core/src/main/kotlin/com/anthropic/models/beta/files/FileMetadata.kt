// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.files

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional

class FileMetadata
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val filename: JsonField<String>,
    private val mimeType: JsonField<String>,
    private val sizeBytes: JsonField<Long>,
    private val type: JsonValue,
    private val downloadable: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("filename") @ExcludeMissing filename: JsonField<String> = JsonMissing.of(),
        @JsonProperty("mime_type") @ExcludeMissing mimeType: JsonField<String> = JsonMissing.of(),
        @JsonProperty("size_bytes") @ExcludeMissing sizeBytes: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("downloadable")
        @ExcludeMissing
        downloadable: JsonField<Boolean> = JsonMissing.of(),
    ) : this(id, createdAt, filename, mimeType, sizeBytes, type, downloadable, mutableMapOf())

    /**
     * Unique object identifier.
     *
     * The format and length of IDs may change over time.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * RFC 3339 datetime string representing when the file was created.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Original filename of the uploaded file.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun filename(): String = filename.getRequired("filename")

    /**
     * MIME type of the file.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun mimeType(): String = mimeType.getRequired("mime_type")

    /**
     * Size of the file in bytes.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun sizeBytes(): Long = sizeBytes.getRequired("size_bytes")

    /**
     * Object type.
     *
     * For files, this is always `"file"`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("file")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Whether the file can be downloaded.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun downloadable(): Optional<Boolean> = downloadable.getOptional("downloadable")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [filename].
     *
     * Unlike [filename], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("filename") @ExcludeMissing fun _filename(): JsonField<String> = filename

    /**
     * Returns the raw JSON value of [mimeType].
     *
     * Unlike [mimeType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mime_type") @ExcludeMissing fun _mimeType(): JsonField<String> = mimeType

    /**
     * Returns the raw JSON value of [sizeBytes].
     *
     * Unlike [sizeBytes], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("size_bytes") @ExcludeMissing fun _sizeBytes(): JsonField<Long> = sizeBytes

    /**
     * Returns the raw JSON value of [downloadable].
     *
     * Unlike [downloadable], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("downloadable")
    @ExcludeMissing
    fun _downloadable(): JsonField<Boolean> = downloadable

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
         * Returns a mutable builder for constructing an instance of [FileMetadata].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .filename()
         * .mimeType()
         * .sizeBytes()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [FileMetadata]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var filename: JsonField<String>? = null
        private var mimeType: JsonField<String>? = null
        private var sizeBytes: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("file")
        private var downloadable: JsonField<Boolean> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(fileMetadata: FileMetadata) = apply {
            id = fileMetadata.id
            createdAt = fileMetadata.createdAt
            filename = fileMetadata.filename
            mimeType = fileMetadata.mimeType
            sizeBytes = fileMetadata.sizeBytes
            type = fileMetadata.type
            downloadable = fileMetadata.downloadable
            additionalProperties = fileMetadata.additionalProperties.toMutableMap()
        }

        /**
         * Unique object identifier.
         *
         * The format and length of IDs may change over time.
         */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** RFC 3339 datetime string representing when the file was created. */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** Original filename of the uploaded file. */
        fun filename(filename: String) = filename(JsonField.of(filename))

        /**
         * Sets [Builder.filename] to an arbitrary JSON value.
         *
         * You should usually call [Builder.filename] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun filename(filename: JsonField<String>) = apply { this.filename = filename }

        /** MIME type of the file. */
        fun mimeType(mimeType: String) = mimeType(JsonField.of(mimeType))

        /**
         * Sets [Builder.mimeType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mimeType] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun mimeType(mimeType: JsonField<String>) = apply { this.mimeType = mimeType }

        /** Size of the file in bytes. */
        fun sizeBytes(sizeBytes: Long) = sizeBytes(JsonField.of(sizeBytes))

        /**
         * Sets [Builder.sizeBytes] to an arbitrary JSON value.
         *
         * You should usually call [Builder.sizeBytes] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun sizeBytes(sizeBytes: JsonField<Long>) = apply { this.sizeBytes = sizeBytes }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("file")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Whether the file can be downloaded. */
        fun downloadable(downloadable: Boolean) = downloadable(JsonField.of(downloadable))

        /**
         * Sets [Builder.downloadable] to an arbitrary JSON value.
         *
         * You should usually call [Builder.downloadable] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun downloadable(downloadable: JsonField<Boolean>) = apply {
            this.downloadable = downloadable
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
         * Returns an immutable instance of [FileMetadata].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .filename()
         * .mimeType()
         * .sizeBytes()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): FileMetadata =
            FileMetadata(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("filename", filename),
                checkRequired("mimeType", mimeType),
                checkRequired("sizeBytes", sizeBytes),
                type,
                downloadable,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): FileMetadata = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        filename()
        mimeType()
        sizeBytes()
        _type().let {
            if (it != JsonValue.from("file")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        downloadable()
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (filename.asKnown().isPresent) 1 else 0) +
            (if (mimeType.asKnown().isPresent) 1 else 0) +
            (if (sizeBytes.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("file")) 1 else 0 } +
            (if (downloadable.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is FileMetadata && id == other.id && createdAt == other.createdAt && filename == other.filename && mimeType == other.mimeType && sizeBytes == other.sizeBytes && type == other.type && downloadable == other.downloadable && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(id, createdAt, filename, mimeType, sizeBytes, type, downloadable, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "FileMetadata{id=$id, createdAt=$createdAt, filename=$filename, mimeType=$mimeType, sizeBytes=$sizeBytes, type=$type, downloadable=$downloadable, additionalProperties=$additionalProperties}"
}
