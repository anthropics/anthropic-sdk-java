package com.anthropic.models.beta.memorystores.memories

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
import kotlin.jvm.optionals.getOrNull

/**
 * A text document at a path inside a memory store.
 *
 * `content` is `null` when the response uses `view=basic`. `content_size_bytes` and
 * `content_sha256` are always set, so you can detect changes without fetching the content. To
 * retrieve, update, or delete a memory, pass its ID, which stays the same when its path changes.
 *
 * See the
 * [memory guide](https://platform.claude.com/docs/en/managed-agents/memory#view-and-edit-memories)
 * to learn more about working with memories.
 */
class BetaManagedAgentsMemory
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val contentSha256: JsonField<String>,
    private val contentSizeBytes: JsonField<Int>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val memoryStoreId: JsonField<String>,
    private val memoryVersionId: JsonField<String>,
    private val path: JsonField<String>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val content: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("content_sha256")
        @ExcludeMissing
        contentSha256: JsonField<String> = JsonMissing.of(),
        @JsonProperty("content_size_bytes")
        @ExcludeMissing
        contentSizeBytes: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("memory_store_id")
        @ExcludeMissing
        memoryStoreId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("memory_version_id")
        @ExcludeMissing
        memoryVersionId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("path") @ExcludeMissing path: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("content") @ExcludeMissing content: JsonField<String> = JsonMissing.of(),
    ) : this(
        id,
        contentSha256,
        contentSizeBytes,
        createdAt,
        memoryStoreId,
        memoryVersionId,
        path,
        type,
        updatedAt,
        content,
        mutableMapOf(),
    )

    /**
     * The unique ID of the memory (`mem_...`). It stays the same when the memory's path changes.
     *
     * To retrieve, update, or delete the memory, pass this ID.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Lowercase hex SHA-256 digest of the UTF-8 `content` bytes (64 characters). The server applies
     * no normalization, so clients can compute the same hash locally for staleness checks and as
     * the value for a `content_sha256` precondition on update. Always populated, regardless of
     * `view`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun contentSha256(): String = contentSha256.getRequired("content_sha256")

    /**
     * The size of the memory's `content` in UTF-8 bytes. It is set regardless of `view`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun contentSizeBytes(): Int = contentSizeBytes.getRequired("content_size_bytes")

    /**
     * When this memory was created, in RFC 3339 format.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * ID of the memory store this memory belongs to (a `memstore_...` value).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun memoryStoreId(): String = memoryStoreId.getRequired("memory_store_id")

    /**
     * The ID of the memory version that holds the memory's current content (`memver_...`).
     *
     * Memory versions don't have a flag that marks the latest one. Compare their IDs with this
     * field instead.
     *
     * See the
     * [memory guide](https://platform.claude.com/docs/en/managed-agents/memory#audit-memory-changes)
     * to learn more about memory versions.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun memoryVersionId(): String = memoryVersionId.getRequired("memory_version_id")

    /**
     * Hierarchical path of the memory within the store, e.g. `/projects/foo/notes.md`. Always
     * starts with `/`. Paths are case-sensitive and unique within a store. Maximum 1,024 bytes.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun path(): String = path.getRequired("path")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("memory")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * When the memory was last changed, in RFC 3339.
     *
     * To find out who made the change, look at `created_by` on the memory version that
     * `memory_version_id` references.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * The memory's UTF-8 text content, or `null` when the response uses `view=basic`.
     *
     * The maximum size is 100 kB (102,400 bytes).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun content(): Optional<String> = content.getOptional("content")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [contentSha256].
     *
     * Unlike [contentSha256], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content_sha256")
    @ExcludeMissing
    fun _contentSha256(): JsonField<String> = contentSha256

    /**
     * Returns the raw JSON value of [contentSizeBytes].
     *
     * Unlike [contentSizeBytes], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("content_size_bytes")
    @ExcludeMissing
    fun _contentSizeBytes(): JsonField<Int> = contentSizeBytes

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [memoryStoreId].
     *
     * Unlike [memoryStoreId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("memory_store_id")
    @ExcludeMissing
    fun _memoryStoreId(): JsonField<String> = memoryStoreId

    /**
     * Returns the raw JSON value of [memoryVersionId].
     *
     * Unlike [memoryVersionId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("memory_version_id")
    @ExcludeMissing
    fun _memoryVersionId(): JsonField<String> = memoryVersionId

    /**
     * Returns the raw JSON value of [path].
     *
     * Unlike [path], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("path") @ExcludeMissing fun _path(): JsonField<String> = path

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<String> = content

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
         * Returns a mutable builder for constructing an instance of [BetaManagedAgentsMemory].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .contentSha256()
         * .contentSizeBytes()
         * .createdAt()
         * .memoryStoreId()
         * .memoryVersionId()
         * .path()
         * .updatedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsMemory]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var contentSha256: JsonField<String>? = null
        private var contentSizeBytes: JsonField<Int>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var memoryStoreId: JsonField<String>? = null
        private var memoryVersionId: JsonField<String>? = null
        private var path: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("memory")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var content: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsMemory: BetaManagedAgentsMemory) = apply {
            id = betaManagedAgentsMemory.id
            contentSha256 = betaManagedAgentsMemory.contentSha256
            contentSizeBytes = betaManagedAgentsMemory.contentSizeBytes
            createdAt = betaManagedAgentsMemory.createdAt
            memoryStoreId = betaManagedAgentsMemory.memoryStoreId
            memoryVersionId = betaManagedAgentsMemory.memoryVersionId
            path = betaManagedAgentsMemory.path
            type = betaManagedAgentsMemory.type
            updatedAt = betaManagedAgentsMemory.updatedAt
            content = betaManagedAgentsMemory.content
            additionalProperties = betaManagedAgentsMemory.additionalProperties.toMutableMap()
        }

        /**
         * The unique ID of the memory (`mem_...`). It stays the same when the memory's path
         * changes.
         *
         * To retrieve, update, or delete the memory, pass this ID.
         */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * Lowercase hex SHA-256 digest of the UTF-8 `content` bytes (64 characters). The server
         * applies no normalization, so clients can compute the same hash locally for staleness
         * checks and as the value for a `content_sha256` precondition on update. Always populated,
         * regardless of `view`.
         */
        fun contentSha256(contentSha256: String) = contentSha256(JsonField.of(contentSha256))

        /**
         * Sets [Builder.contentSha256] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contentSha256] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun contentSha256(contentSha256: JsonField<String>) = apply {
            this.contentSha256 = contentSha256
        }

        /** The size of the memory's `content` in UTF-8 bytes. It is set regardless of `view`. */
        fun contentSizeBytes(contentSizeBytes: Int) =
            contentSizeBytes(JsonField.of(contentSizeBytes))

        /**
         * Sets [Builder.contentSizeBytes] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contentSizeBytes] with a well-typed [Int] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun contentSizeBytes(contentSizeBytes: JsonField<Int>) = apply {
            this.contentSizeBytes = contentSizeBytes
        }

        /** When this memory was created, in RFC 3339 format. */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** ID of the memory store this memory belongs to (a `memstore_...` value). */
        fun memoryStoreId(memoryStoreId: String) = memoryStoreId(JsonField.of(memoryStoreId))

        /**
         * Sets [Builder.memoryStoreId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.memoryStoreId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun memoryStoreId(memoryStoreId: JsonField<String>) = apply {
            this.memoryStoreId = memoryStoreId
        }

        /**
         * The ID of the memory version that holds the memory's current content (`memver_...`).
         *
         * Memory versions don't have a flag that marks the latest one. Compare their IDs with this
         * field instead.
         *
         * See the
         * [memory guide](https://platform.claude.com/docs/en/managed-agents/memory#audit-memory-changes)
         * to learn more about memory versions.
         */
        fun memoryVersionId(memoryVersionId: String) =
            memoryVersionId(JsonField.of(memoryVersionId))

        /**
         * Sets [Builder.memoryVersionId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.memoryVersionId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun memoryVersionId(memoryVersionId: JsonField<String>) = apply {
            this.memoryVersionId = memoryVersionId
        }

        /**
         * Hierarchical path of the memory within the store, e.g. `/projects/foo/notes.md`. Always
         * starts with `/`. Paths are case-sensitive and unique within a store. Maximum 1,024 bytes.
         */
        fun path(path: String) = path(JsonField.of(path))

        /**
         * Sets [Builder.path] to an arbitrary JSON value.
         *
         * You should usually call [Builder.path] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun path(path: JsonField<String>) = apply { this.path = path }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("memory")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * When the memory was last changed, in RFC 3339.
         *
         * To find out who made the change, look at `created_by` on the memory version that
         * `memory_version_id` references.
         */
        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        /**
         * Sets [Builder.updatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

        /**
         * The memory's UTF-8 text content, or `null` when the response uses `view=basic`.
         *
         * The maximum size is 100 kB (102,400 bytes).
         */
        fun content(content: String?) = content(JsonField.ofNullable(content))

        /** Alias for calling [Builder.content] with `content.orElse(null)`. */
        fun content(content: Optional<String>) = content(content.getOrNull())

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun content(content: JsonField<String>) = apply { this.content = content }

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
         * Returns an immutable instance of [BetaManagedAgentsMemory].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .contentSha256()
         * .contentSizeBytes()
         * .createdAt()
         * .memoryStoreId()
         * .memoryVersionId()
         * .path()
         * .updatedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsMemory =
            BetaManagedAgentsMemory(
                checkRequired("id", id),
                checkRequired("contentSha256", contentSha256),
                checkRequired("contentSizeBytes", contentSizeBytes),
                checkRequired("createdAt", createdAt),
                checkRequired("memoryStoreId", memoryStoreId),
                checkRequired("memoryVersionId", memoryVersionId),
                checkRequired("path", path),
                type,
                checkRequired("updatedAt", updatedAt),
                content,
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
    fun validate(): BetaManagedAgentsMemory = apply {
        if (validated) {
            return@apply
        }

        id()
        contentSha256()
        contentSizeBytes()
        createdAt()
        memoryStoreId()
        memoryVersionId()
        path()
        _type().let {
            if (it != JsonValue.from("memory")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        updatedAt()
        content()
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
            (if (contentSha256.asKnown().isPresent) 1 else 0) +
            (if (contentSizeBytes.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (memoryStoreId.asKnown().isPresent) 1 else 0) +
            (if (memoryVersionId.asKnown().isPresent) 1 else 0) +
            (if (path.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("memory")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (if (content.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMemory &&
            id == other.id &&
            contentSha256 == other.contentSha256 &&
            contentSizeBytes == other.contentSizeBytes &&
            createdAt == other.createdAt &&
            memoryStoreId == other.memoryStoreId &&
            memoryVersionId == other.memoryVersionId &&
            path == other.path &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            content == other.content &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            contentSha256,
            contentSizeBytes,
            createdAt,
            memoryStoreId,
            memoryVersionId,
            path,
            type,
            updatedAt,
            content,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMemory{id=$id, contentSha256=$contentSha256, contentSizeBytes=$contentSizeBytes, createdAt=$createdAt, memoryStoreId=$memoryStoreId, memoryVersionId=$memoryVersionId, path=$path, type=$type, updatedAt=$updatedAt, content=$content, additionalProperties=$additionalProperties}"
}
