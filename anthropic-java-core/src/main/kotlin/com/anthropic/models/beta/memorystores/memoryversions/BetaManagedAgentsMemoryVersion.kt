// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memoryversions

import com.anthropic.core.Enum
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
 * A `memory_version` object: one immutable, attributed row in a memory's append-only history. Every
 * non-no-op mutation to a memory produces a new version. Versions belong to the store (not the
 * individual memory) and persist after the memory is deleted. Retrieving a redacted version returns
 * 200 with `content`, `path`, `content_size_bytes`, and `content_sha256` set to `null`; branch on
 * `redacted_at`, not HTTP status.
 */
class BetaManagedAgentsMemoryVersion
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val memoryId: JsonField<String>,
    private val memoryStoreId: JsonField<String>,
    private val operation: JsonField<BetaManagedAgentsMemoryVersionOperation>,
    private val type: JsonField<Type>,
    private val content: JsonField<String>,
    private val contentSha256: JsonField<String>,
    private val contentSizeBytes: JsonField<Int>,
    private val createdBy: JsonField<BetaManagedAgentsActor>,
    private val path: JsonField<String>,
    private val redactedAt: JsonField<OffsetDateTime>,
    private val redactedBy: JsonField<BetaManagedAgentsActor>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("memory_id") @ExcludeMissing memoryId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("memory_store_id")
        @ExcludeMissing
        memoryStoreId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("operation")
        @ExcludeMissing
        operation: JsonField<BetaManagedAgentsMemoryVersionOperation> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("content") @ExcludeMissing content: JsonField<String> = JsonMissing.of(),
        @JsonProperty("content_sha256")
        @ExcludeMissing
        contentSha256: JsonField<String> = JsonMissing.of(),
        @JsonProperty("content_size_bytes")
        @ExcludeMissing
        contentSizeBytes: JsonField<Int> = JsonMissing.of(),
        @JsonProperty("created_by")
        @ExcludeMissing
        createdBy: JsonField<BetaManagedAgentsActor> = JsonMissing.of(),
        @JsonProperty("path") @ExcludeMissing path: JsonField<String> = JsonMissing.of(),
        @JsonProperty("redacted_at")
        @ExcludeMissing
        redactedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("redacted_by")
        @ExcludeMissing
        redactedBy: JsonField<BetaManagedAgentsActor> = JsonMissing.of(),
    ) : this(
        id,
        createdAt,
        memoryId,
        memoryStoreId,
        operation,
        type,
        content,
        contentSha256,
        contentSizeBytes,
        createdBy,
        path,
        redactedAt,
        redactedBy,
        mutableMapOf(),
    )

    /**
     * Unique identifier for this version (a `memver_...` value).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * ID of the memory this version snapshots (a `mem_...` value). Remains valid after the memory
     * is deleted; pass it as `memory_id` to
     * [List memory versions](/en/api/beta/memory_stores/memory_versions/list) to retrieve the full
     * lineage including the `deleted` row.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun memoryId(): String = memoryId.getRequired("memory_id")

    /**
     * ID of the memory store this version belongs to (a `memstore_...` value).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun memoryStoreId(): String = memoryStoreId.getRequired("memory_store_id")

    /**
     * The kind of mutation a `memory_version` records. Every non-no-op mutation to a memory appends
     * exactly one version row with one of these values.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun operation(): BetaManagedAgentsMemoryVersionOperation = operation.getRequired("operation")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * The memory's UTF-8 text content as of this version. `null` when `view=basic`, when
     * `operation` is `deleted`, or when `redacted_at` is set.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun content(): Optional<String> = content.getOptional("content")

    /**
     * Lowercase hex SHA-256 digest of `content` as of this version (64 characters). `null` when
     * `redacted_at` is set or `operation` is `deleted`. Populated regardless of `view` otherwise.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun contentSha256(): Optional<String> = contentSha256.getOptional("content_sha256")

    /**
     * Size of `content` in bytes as of this version. `null` when `redacted_at` is set or
     * `operation` is `deleted`. Populated regardless of `view` otherwise.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun contentSizeBytes(): Optional<Int> = contentSizeBytes.getOptional("content_size_bytes")

    /**
     * Identifies who performed a write or redact operation. Captured at write time on the
     * `memory_version` row. The API key that created a session is not recorded on agent writes;
     * attribution answers who made the write, not who is ultimately responsible. Look up session
     * provenance separately via the [Sessions API](/en/api/sessions-retrieve).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun createdBy(): Optional<BetaManagedAgentsActor> = createdBy.getOptional("created_by")

    /**
     * The memory's path at the time of this write. `null` if and only if `redacted_at` is set.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun path(): Optional<String> = path.getOptional("path")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun redactedAt(): Optional<OffsetDateTime> = redactedAt.getOptional("redacted_at")

    /**
     * Identifies who performed a write or redact operation. Captured at write time on the
     * `memory_version` row. The API key that created a session is not recorded on agent writes;
     * attribution answers who made the write, not who is ultimately responsible. Look up session
     * provenance separately via the [Sessions API](/en/api/sessions-retrieve).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun redactedBy(): Optional<BetaManagedAgentsActor> = redactedBy.getOptional("redacted_by")

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
     * Returns the raw JSON value of [memoryId].
     *
     * Unlike [memoryId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("memory_id") @ExcludeMissing fun _memoryId(): JsonField<String> = memoryId

    /**
     * Returns the raw JSON value of [memoryStoreId].
     *
     * Unlike [memoryStoreId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("memory_store_id")
    @ExcludeMissing
    fun _memoryStoreId(): JsonField<String> = memoryStoreId

    /**
     * Returns the raw JSON value of [operation].
     *
     * Unlike [operation], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("operation")
    @ExcludeMissing
    fun _operation(): JsonField<BetaManagedAgentsMemoryVersionOperation> = operation

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<String> = content

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
     * Returns the raw JSON value of [createdBy].
     *
     * Unlike [createdBy], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_by")
    @ExcludeMissing
    fun _createdBy(): JsonField<BetaManagedAgentsActor> = createdBy

    /**
     * Returns the raw JSON value of [path].
     *
     * Unlike [path], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("path") @ExcludeMissing fun _path(): JsonField<String> = path

    /**
     * Returns the raw JSON value of [redactedAt].
     *
     * Unlike [redactedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("redacted_at")
    @ExcludeMissing
    fun _redactedAt(): JsonField<OffsetDateTime> = redactedAt

    /**
     * Returns the raw JSON value of [redactedBy].
     *
     * Unlike [redactedBy], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("redacted_by")
    @ExcludeMissing
    fun _redactedBy(): JsonField<BetaManagedAgentsActor> = redactedBy

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
         * [BetaManagedAgentsMemoryVersion].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .memoryId()
         * .memoryStoreId()
         * .operation()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsMemoryVersion]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var memoryId: JsonField<String>? = null
        private var memoryStoreId: JsonField<String>? = null
        private var operation: JsonField<BetaManagedAgentsMemoryVersionOperation>? = null
        private var type: JsonField<Type>? = null
        private var content: JsonField<String> = JsonMissing.of()
        private var contentSha256: JsonField<String> = JsonMissing.of()
        private var contentSizeBytes: JsonField<Int> = JsonMissing.of()
        private var createdBy: JsonField<BetaManagedAgentsActor> = JsonMissing.of()
        private var path: JsonField<String> = JsonMissing.of()
        private var redactedAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var redactedBy: JsonField<BetaManagedAgentsActor> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsMemoryVersion: BetaManagedAgentsMemoryVersion) = apply {
            id = betaManagedAgentsMemoryVersion.id
            createdAt = betaManagedAgentsMemoryVersion.createdAt
            memoryId = betaManagedAgentsMemoryVersion.memoryId
            memoryStoreId = betaManagedAgentsMemoryVersion.memoryStoreId
            operation = betaManagedAgentsMemoryVersion.operation
            type = betaManagedAgentsMemoryVersion.type
            content = betaManagedAgentsMemoryVersion.content
            contentSha256 = betaManagedAgentsMemoryVersion.contentSha256
            contentSizeBytes = betaManagedAgentsMemoryVersion.contentSizeBytes
            createdBy = betaManagedAgentsMemoryVersion.createdBy
            path = betaManagedAgentsMemoryVersion.path
            redactedAt = betaManagedAgentsMemoryVersion.redactedAt
            redactedBy = betaManagedAgentsMemoryVersion.redactedBy
            additionalProperties =
                betaManagedAgentsMemoryVersion.additionalProperties.toMutableMap()
        }

        /** Unique identifier for this version (a `memver_...` value). */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** A timestamp in RFC 3339 format */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /**
         * ID of the memory this version snapshots (a `mem_...` value). Remains valid after the
         * memory is deleted; pass it as `memory_id` to
         * [List memory versions](/en/api/beta/memory_stores/memory_versions/list) to retrieve the
         * full lineage including the `deleted` row.
         */
        fun memoryId(memoryId: String) = memoryId(JsonField.of(memoryId))

        /**
         * Sets [Builder.memoryId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.memoryId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun memoryId(memoryId: JsonField<String>) = apply { this.memoryId = memoryId }

        /** ID of the memory store this version belongs to (a `memstore_...` value). */
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
         * The kind of mutation a `memory_version` records. Every non-no-op mutation to a memory
         * appends exactly one version row with one of these values.
         */
        fun operation(operation: BetaManagedAgentsMemoryVersionOperation) =
            operation(JsonField.of(operation))

        /**
         * Sets [Builder.operation] to an arbitrary JSON value.
         *
         * You should usually call [Builder.operation] with a well-typed
         * [BetaManagedAgentsMemoryVersionOperation] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun operation(operation: JsonField<BetaManagedAgentsMemoryVersionOperation>) = apply {
            this.operation = operation
        }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /**
         * The memory's UTF-8 text content as of this version. `null` when `view=basic`, when
         * `operation` is `deleted`, or when `redacted_at` is set.
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

        /**
         * Lowercase hex SHA-256 digest of `content` as of this version (64 characters). `null` when
         * `redacted_at` is set or `operation` is `deleted`. Populated regardless of `view`
         * otherwise.
         */
        fun contentSha256(contentSha256: String?) =
            contentSha256(JsonField.ofNullable(contentSha256))

        /** Alias for calling [Builder.contentSha256] with `contentSha256.orElse(null)`. */
        fun contentSha256(contentSha256: Optional<String>) =
            contentSha256(contentSha256.getOrNull())

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

        /**
         * Size of `content` in bytes as of this version. `null` when `redacted_at` is set or
         * `operation` is `deleted`. Populated regardless of `view` otherwise.
         */
        fun contentSizeBytes(contentSizeBytes: Int?) =
            contentSizeBytes(JsonField.ofNullable(contentSizeBytes))

        /**
         * Alias for [Builder.contentSizeBytes].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun contentSizeBytes(contentSizeBytes: Int) = contentSizeBytes(contentSizeBytes as Int?)

        /** Alias for calling [Builder.contentSizeBytes] with `contentSizeBytes.orElse(null)`. */
        fun contentSizeBytes(contentSizeBytes: Optional<Int>) =
            contentSizeBytes(contentSizeBytes.getOrNull())

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

        /**
         * Identifies who performed a write or redact operation. Captured at write time on the
         * `memory_version` row. The API key that created a session is not recorded on agent writes;
         * attribution answers who made the write, not who is ultimately responsible. Look up
         * session provenance separately via the [Sessions API](/en/api/sessions-retrieve).
         */
        fun createdBy(createdBy: BetaManagedAgentsActor) = createdBy(JsonField.of(createdBy))

        /**
         * Sets [Builder.createdBy] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdBy] with a well-typed [BetaManagedAgentsActor]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun createdBy(createdBy: JsonField<BetaManagedAgentsActor>) = apply {
            this.createdBy = createdBy
        }

        /** Alias for calling [createdBy] with `BetaManagedAgentsActor.ofSession(session)`. */
        fun createdBy(session: BetaManagedAgentsSessionActor) =
            createdBy(BetaManagedAgentsActor.ofSession(session))

        /**
         * Alias for calling [createdBy] with the following:
         * ```java
         * BetaManagedAgentsSessionActor.builder()
         *     .type(BetaManagedAgentsSessionActor.Type.SESSION_ACTOR)
         *     .sessionId(sessionId)
         *     .build()
         * ```
         */
        fun sessionCreatedBy(sessionId: String) =
            createdBy(
                BetaManagedAgentsSessionActor.builder()
                    .type(BetaManagedAgentsSessionActor.Type.SESSION_ACTOR)
                    .sessionId(sessionId)
                    .build()
            )

        /** Alias for calling [createdBy] with `BetaManagedAgentsActor.ofApi(api)`. */
        fun createdBy(api: BetaManagedAgentsApiActor) = createdBy(BetaManagedAgentsActor.ofApi(api))

        /**
         * Alias for calling [createdBy] with the following:
         * ```java
         * BetaManagedAgentsApiActor.builder()
         *     .type(BetaManagedAgentsApiActor.Type.API_ACTOR)
         *     .apiKeyId(apiKeyId)
         *     .build()
         * ```
         */
        fun apiCreatedBy(apiKeyId: String) =
            createdBy(
                BetaManagedAgentsApiActor.builder()
                    .type(BetaManagedAgentsApiActor.Type.API_ACTOR)
                    .apiKeyId(apiKeyId)
                    .build()
            )

        /** Alias for calling [createdBy] with `BetaManagedAgentsActor.ofUser(user)`. */
        fun createdBy(user: BetaManagedAgentsUserActor) =
            createdBy(BetaManagedAgentsActor.ofUser(user))

        /**
         * Alias for calling [createdBy] with the following:
         * ```java
         * BetaManagedAgentsUserActor.builder()
         *     .type(BetaManagedAgentsUserActor.Type.USER_ACTOR)
         *     .userId(userId)
         *     .build()
         * ```
         */
        fun userCreatedBy(userId: String) =
            createdBy(
                BetaManagedAgentsUserActor.builder()
                    .type(BetaManagedAgentsUserActor.Type.USER_ACTOR)
                    .userId(userId)
                    .build()
            )

        /**
         * The memory's path at the time of this write. `null` if and only if `redacted_at` is set.
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

        /** A timestamp in RFC 3339 format */
        fun redactedAt(redactedAt: OffsetDateTime?) = redactedAt(JsonField.ofNullable(redactedAt))

        /** Alias for calling [Builder.redactedAt] with `redactedAt.orElse(null)`. */
        fun redactedAt(redactedAt: Optional<OffsetDateTime>) = redactedAt(redactedAt.getOrNull())

        /**
         * Sets [Builder.redactedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.redactedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun redactedAt(redactedAt: JsonField<OffsetDateTime>) = apply {
            this.redactedAt = redactedAt
        }

        /**
         * Identifies who performed a write or redact operation. Captured at write time on the
         * `memory_version` row. The API key that created a session is not recorded on agent writes;
         * attribution answers who made the write, not who is ultimately responsible. Look up
         * session provenance separately via the [Sessions API](/en/api/sessions-retrieve).
         */
        fun redactedBy(redactedBy: BetaManagedAgentsActor) = redactedBy(JsonField.of(redactedBy))

        /**
         * Sets [Builder.redactedBy] to an arbitrary JSON value.
         *
         * You should usually call [Builder.redactedBy] with a well-typed [BetaManagedAgentsActor]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun redactedBy(redactedBy: JsonField<BetaManagedAgentsActor>) = apply {
            this.redactedBy = redactedBy
        }

        /** Alias for calling [redactedBy] with `BetaManagedAgentsActor.ofSession(session)`. */
        fun redactedBy(session: BetaManagedAgentsSessionActor) =
            redactedBy(BetaManagedAgentsActor.ofSession(session))

        /**
         * Alias for calling [redactedBy] with the following:
         * ```java
         * BetaManagedAgentsSessionActor.builder()
         *     .type(BetaManagedAgentsSessionActor.Type.SESSION_ACTOR)
         *     .sessionId(sessionId)
         *     .build()
         * ```
         */
        fun sessionRedactedBy(sessionId: String) =
            redactedBy(
                BetaManagedAgentsSessionActor.builder()
                    .type(BetaManagedAgentsSessionActor.Type.SESSION_ACTOR)
                    .sessionId(sessionId)
                    .build()
            )

        /** Alias for calling [redactedBy] with `BetaManagedAgentsActor.ofApi(api)`. */
        fun redactedBy(api: BetaManagedAgentsApiActor) =
            redactedBy(BetaManagedAgentsActor.ofApi(api))

        /**
         * Alias for calling [redactedBy] with the following:
         * ```java
         * BetaManagedAgentsApiActor.builder()
         *     .type(BetaManagedAgentsApiActor.Type.API_ACTOR)
         *     .apiKeyId(apiKeyId)
         *     .build()
         * ```
         */
        fun apiRedactedBy(apiKeyId: String) =
            redactedBy(
                BetaManagedAgentsApiActor.builder()
                    .type(BetaManagedAgentsApiActor.Type.API_ACTOR)
                    .apiKeyId(apiKeyId)
                    .build()
            )

        /** Alias for calling [redactedBy] with `BetaManagedAgentsActor.ofUser(user)`. */
        fun redactedBy(user: BetaManagedAgentsUserActor) =
            redactedBy(BetaManagedAgentsActor.ofUser(user))

        /**
         * Alias for calling [redactedBy] with the following:
         * ```java
         * BetaManagedAgentsUserActor.builder()
         *     .type(BetaManagedAgentsUserActor.Type.USER_ACTOR)
         *     .userId(userId)
         *     .build()
         * ```
         */
        fun userRedactedBy(userId: String) =
            redactedBy(
                BetaManagedAgentsUserActor.builder()
                    .type(BetaManagedAgentsUserActor.Type.USER_ACTOR)
                    .userId(userId)
                    .build()
            )

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
         * Returns an immutable instance of [BetaManagedAgentsMemoryVersion].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .memoryId()
         * .memoryStoreId()
         * .operation()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsMemoryVersion =
            BetaManagedAgentsMemoryVersion(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("memoryId", memoryId),
                checkRequired("memoryStoreId", memoryStoreId),
                checkRequired("operation", operation),
                checkRequired("type", type),
                content,
                contentSha256,
                contentSizeBytes,
                createdBy,
                path,
                redactedAt,
                redactedBy,
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
    fun validate(): BetaManagedAgentsMemoryVersion = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        memoryId()
        memoryStoreId()
        operation().validate()
        type().validate()
        content()
        contentSha256()
        contentSizeBytes()
        createdBy().ifPresent { it.validate() }
        path()
        redactedAt()
        redactedBy().ifPresent { it.validate() }
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
            (if (memoryId.asKnown().isPresent) 1 else 0) +
            (if (memoryStoreId.asKnown().isPresent) 1 else 0) +
            (operation.asKnown().getOrNull()?.validity() ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (if (content.asKnown().isPresent) 1 else 0) +
            (if (contentSha256.asKnown().isPresent) 1 else 0) +
            (if (contentSizeBytes.asKnown().isPresent) 1 else 0) +
            (createdBy.asKnown().getOrNull()?.validity() ?: 0) +
            (if (path.asKnown().isPresent) 1 else 0) +
            (if (redactedAt.asKnown().isPresent) 1 else 0) +
            (redactedBy.asKnown().getOrNull()?.validity() ?: 0)

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val MEMORY_VERSION = of("memory_version")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            MEMORY_VERSION
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            MEMORY_VERSION,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                MEMORY_VERSION -> Value.MEMORY_VERSION
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                MEMORY_VERSION -> Known.MEMORY_VERSION
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Type = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMemoryVersion &&
            id == other.id &&
            createdAt == other.createdAt &&
            memoryId == other.memoryId &&
            memoryStoreId == other.memoryStoreId &&
            operation == other.operation &&
            type == other.type &&
            content == other.content &&
            contentSha256 == other.contentSha256 &&
            contentSizeBytes == other.contentSizeBytes &&
            createdBy == other.createdBy &&
            path == other.path &&
            redactedAt == other.redactedAt &&
            redactedBy == other.redactedBy &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            createdAt,
            memoryId,
            memoryStoreId,
            operation,
            type,
            content,
            contentSha256,
            contentSizeBytes,
            createdBy,
            path,
            redactedAt,
            redactedBy,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMemoryVersion{id=$id, createdAt=$createdAt, memoryId=$memoryId, memoryStoreId=$memoryStoreId, operation=$operation, type=$type, content=$content, contentSha256=$contentSha256, contentSizeBytes=$contentSizeBytes, createdBy=$createdBy, path=$path, redactedAt=$redactedAt, redactedBy=$redactedBy, additionalProperties=$additionalProperties}"
}
