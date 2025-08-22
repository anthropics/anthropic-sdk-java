// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.files

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

class FileListPageResponse
private constructor(
    private val data: JsonField<List<FileMetadata>>,
    private val firstId: JsonField<String>,
    private val hasMore: JsonField<Boolean>,
    private val lastId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("data")
        @ExcludeMissing
        data: JsonField<List<FileMetadata>> = JsonMissing.of(),
        @JsonProperty("first_id") @ExcludeMissing firstId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("has_more") @ExcludeMissing hasMore: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("last_id") @ExcludeMissing lastId: JsonField<String> = JsonMissing.of(),
    ) : this(data, firstId, hasMore, lastId, mutableMapOf())

    /**
     * List of file metadata objects.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun data(): List<FileMetadata> = data.getRequired("data")

    /**
     * ID of the first file in this page of results.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun firstId(): Optional<String> = firstId.getOptional("first_id")

    /**
     * Whether there are more results available.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun hasMore(): Optional<Boolean> = hasMore.getOptional("has_more")

    /**
     * ID of the last file in this page of results.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun lastId(): Optional<String> = lastId.getOptional("last_id")

    /**
     * Returns the raw JSON value of [data].
     *
     * Unlike [data], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<List<FileMetadata>> = data

    /**
     * Returns the raw JSON value of [firstId].
     *
     * Unlike [firstId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("first_id") @ExcludeMissing fun _firstId(): JsonField<String> = firstId

    /**
     * Returns the raw JSON value of [hasMore].
     *
     * Unlike [hasMore], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("has_more") @ExcludeMissing fun _hasMore(): JsonField<Boolean> = hasMore

    /**
     * Returns the raw JSON value of [lastId].
     *
     * Unlike [lastId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("last_id") @ExcludeMissing fun _lastId(): JsonField<String> = lastId

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
         * Returns a mutable builder for constructing an instance of [FileListPageResponse].
         *
         * The following fields are required:
         * ```java
         * .data()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [FileListPageResponse]. */
    class Builder internal constructor() {

        private var data: JsonField<MutableList<FileMetadata>>? = null
        private var firstId: JsonField<String> = JsonMissing.of()
        private var hasMore: JsonField<Boolean> = JsonMissing.of()
        private var lastId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(fileListPageResponse: FileListPageResponse) = apply {
            data = fileListPageResponse.data.map { it.toMutableList() }
            firstId = fileListPageResponse.firstId
            hasMore = fileListPageResponse.hasMore
            lastId = fileListPageResponse.lastId
            additionalProperties = fileListPageResponse.additionalProperties.toMutableMap()
        }

        /** List of file metadata objects. */
        fun data(data: List<FileMetadata>) = data(JsonField.of(data))

        /**
         * Sets [Builder.data] to an arbitrary JSON value.
         *
         * You should usually call [Builder.data] with a well-typed `List<FileMetadata>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun data(data: JsonField<List<FileMetadata>>) = apply {
            this.data = data.map { it.toMutableList() }
        }

        /**
         * Adds a single [FileMetadata] to [Builder.data].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addData(data: FileMetadata) = apply {
            this.data =
                (this.data ?: JsonField.of(mutableListOf())).also {
                    checkKnown("data", it).add(data)
                }
        }

        /** ID of the first file in this page of results. */
        fun firstId(firstId: String?) = firstId(JsonField.ofNullable(firstId))

        /** Alias for calling [Builder.firstId] with `firstId.orElse(null)`. */
        fun firstId(firstId: Optional<String>) = firstId(firstId.getOrNull())

        /**
         * Sets [Builder.firstId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.firstId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun firstId(firstId: JsonField<String>) = apply { this.firstId = firstId }

        /** Whether there are more results available. */
        fun hasMore(hasMore: Boolean) = hasMore(JsonField.of(hasMore))

        /**
         * Sets [Builder.hasMore] to an arbitrary JSON value.
         *
         * You should usually call [Builder.hasMore] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun hasMore(hasMore: JsonField<Boolean>) = apply { this.hasMore = hasMore }

        /** ID of the last file in this page of results. */
        fun lastId(lastId: String?) = lastId(JsonField.ofNullable(lastId))

        /** Alias for calling [Builder.lastId] with `lastId.orElse(null)`. */
        fun lastId(lastId: Optional<String>) = lastId(lastId.getOrNull())

        /**
         * Sets [Builder.lastId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.lastId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun lastId(lastId: JsonField<String>) = apply { this.lastId = lastId }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAlladditional_properties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAlladditional_properties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAlladditional_properties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [FileListPageResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .data()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): FileListPageResponse =
            FileListPageResponse(
                checkRequired("data", data).map { it.toImmutable() },
                firstId,
                hasMore,
                lastId,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): FileListPageResponse = apply {
        if (validated) {
            return@apply
        }

        data().forEach { it.validate() }
        firstId()
        hasMore()
        lastId()
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
        (data.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (firstId.asKnown().isPresent) 1 else 0) +
            (if (hasMore.asKnown().isPresent) 1 else 0) +
            (if (lastId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FileListPageResponse &&
            data == other.data &&
            firstId == other.firstId &&
            hasMore == other.hasMore &&
            lastId == other.lastId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(data, firstId, hasMore, lastId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "FileListPageResponse{data=$data, firstId=$firstId, hasMore=$hasMore, lastId=$lastId, additionalProperties=$additionalProperties}"
}
