package com.anthropic.models.beta.compliance.review.items

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

/** One stored item that the organization it belongs to can read for review. */
class BetaComplianceReviewItem
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val actor: JsonField<BetaComplianceReviewActor>,
    private val contentExpiresAt: JsonField<OffsetDateTime>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val details: JsonField<BetaComplianceReviewItemDetails>,
    private val organizationId: JsonField<String>,
    private val readReason: JsonField<BetaComplianceReviewReadReason>,
    private val type: JsonValue,
    private val userProfileId: JsonField<String>,
    private val workspaceId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("actor")
        @ExcludeMissing
        actor: JsonField<BetaComplianceReviewActor> = JsonMissing.of(),
        @JsonProperty("content_expires_at")
        @ExcludeMissing
        contentExpiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("details")
        @ExcludeMissing
        details: JsonField<BetaComplianceReviewItemDetails> = JsonMissing.of(),
        @JsonProperty("organization_id")
        @ExcludeMissing
        organizationId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("read_reason")
        @ExcludeMissing
        readReason: JsonField<BetaComplianceReviewReadReason> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("user_profile_id")
        @ExcludeMissing
        userProfileId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("workspace_id")
        @ExcludeMissing
        workspaceId: JsonField<String> = JsonMissing.of(),
    ) : this(
        id,
        actor,
        contentExpiresAt,
        createdAt,
        details,
        organizationId,
        readReason,
        type,
        userProfileId,
        workspaceId,
        mutableMapOf(),
    )

    /**
     * The ID of the item. For a message, its own ID, such as `msg_...`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * For a message, who sent it. Null when no sender was recorded. New types can appear, so accept
     * a type that you don't know.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun actor(): Optional<BetaComplianceReviewActor> = actor.getOptional("actor")

    /**
     * Read the item, and save any copy you need, by this time. The item can stop being readable
     * sooner.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun contentExpiresAt(): OffsetDateTime = contentExpiresAt.getRequired("content_expires_at")

    /**
     * For a message, when Anthropic received the call.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * What is particular to this kind of item. New types can appear, so accept a type that you
     * don't know.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun details(): BetaComplianceReviewItemDetails = details.getRequired("details")

    /**
     * The UUID of the organization that the item belongs to. For a message, the organization that
     * sent it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun organizationId(): String = organizationId.getRequired("organization_id")

    /**
     * Why the item is readable. New types can appear, so accept a type that you don't know.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun readReason(): BetaComplianceReviewReadReason = readReason.getRequired("read_reason")

    /**
     * The object type, always `compliance_review_item`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("compliance_review_item")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * The ID of the user profile that the item was attributed to (`uprof_...`). Null when none was
     * recorded.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun userProfileId(): Optional<String> = userProfileId.getOptional("user_profile_id")

    /**
     * The ID of the workspace (`wrkspc_...`). Null when the item isn't attributed to a workspace.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun workspaceId(): Optional<String> = workspaceId.getOptional("workspace_id")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [actor].
     *
     * Unlike [actor], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("actor")
    @ExcludeMissing
    fun _actor(): JsonField<BetaComplianceReviewActor> = actor

    /**
     * Returns the raw JSON value of [contentExpiresAt].
     *
     * Unlike [contentExpiresAt], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("content_expires_at")
    @ExcludeMissing
    fun _contentExpiresAt(): JsonField<OffsetDateTime> = contentExpiresAt

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [details].
     *
     * Unlike [details], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("details")
    @ExcludeMissing
    fun _details(): JsonField<BetaComplianceReviewItemDetails> = details

    /**
     * Returns the raw JSON value of [organizationId].
     *
     * Unlike [organizationId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("organization_id")
    @ExcludeMissing
    fun _organizationId(): JsonField<String> = organizationId

    /**
     * Returns the raw JSON value of [readReason].
     *
     * Unlike [readReason], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("read_reason")
    @ExcludeMissing
    fun _readReason(): JsonField<BetaComplianceReviewReadReason> = readReason

    /**
     * Returns the raw JSON value of [userProfileId].
     *
     * Unlike [userProfileId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user_profile_id")
    @ExcludeMissing
    fun _userProfileId(): JsonField<String> = userProfileId

    /**
     * Returns the raw JSON value of [workspaceId].
     *
     * Unlike [workspaceId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_id")
    @ExcludeMissing
    fun _workspaceId(): JsonField<String> = workspaceId

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
         * Returns a mutable builder for constructing an instance of [BetaComplianceReviewItem].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .actor()
         * .contentExpiresAt()
         * .createdAt()
         * .details()
         * .organizationId()
         * .readReason()
         * .userProfileId()
         * .workspaceId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaComplianceReviewItem]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var actor: JsonField<BetaComplianceReviewActor>? = null
        private var contentExpiresAt: JsonField<OffsetDateTime>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var details: JsonField<BetaComplianceReviewItemDetails>? = null
        private var organizationId: JsonField<String>? = null
        private var readReason: JsonField<BetaComplianceReviewReadReason>? = null
        private var type: JsonValue = JsonValue.from("compliance_review_item")
        private var userProfileId: JsonField<String>? = null
        private var workspaceId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaComplianceReviewItem: BetaComplianceReviewItem) = apply {
            id = betaComplianceReviewItem.id
            actor = betaComplianceReviewItem.actor
            contentExpiresAt = betaComplianceReviewItem.contentExpiresAt
            createdAt = betaComplianceReviewItem.createdAt
            details = betaComplianceReviewItem.details
            organizationId = betaComplianceReviewItem.organizationId
            readReason = betaComplianceReviewItem.readReason
            type = betaComplianceReviewItem.type
            userProfileId = betaComplianceReviewItem.userProfileId
            workspaceId = betaComplianceReviewItem.workspaceId
            additionalProperties = betaComplianceReviewItem.additionalProperties.toMutableMap()
        }

        /** The ID of the item. For a message, its own ID, such as `msg_...`. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * For a message, who sent it. Null when no sender was recorded. New types can appear, so
         * accept a type that you don't know.
         */
        fun actor(actor: BetaComplianceReviewActor?) = actor(JsonField.ofNullable(actor))

        /** Alias for calling [Builder.actor] with `actor.orElse(null)`. */
        fun actor(actor: Optional<BetaComplianceReviewActor>) = actor(actor.getOrNull())

        /**
         * Sets [Builder.actor] to an arbitrary JSON value.
         *
         * You should usually call [Builder.actor] with a well-typed [BetaComplianceReviewActor]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun actor(actor: JsonField<BetaComplianceReviewActor>) = apply { this.actor = actor }

        /** Alias for calling [actor] with `BetaComplianceReviewActor.ofUser(user)`. */
        fun actor(user: BetaComplianceReviewUserActor) =
            actor(BetaComplianceReviewActor.ofUser(user))

        /** Alias for calling [actor] with `BetaComplianceReviewActor.ofApi(api)`. */
        fun actor(api: BetaComplianceReviewApiActor) = actor(BetaComplianceReviewActor.ofApi(api))

        /**
         * Alias for calling [actor] with the following:
         * ```java
         * BetaComplianceReviewApiActor.builder()
         *     .apiKeyId(apiKeyId)
         *     .build()
         * ```
         */
        fun apiActor(apiKeyId: String) =
            actor(BetaComplianceReviewApiActor.builder().apiKeyId(apiKeyId).build())

        /**
         * Alias for calling [actor] with
         * `BetaComplianceReviewActor.ofServiceAccount(serviceAccount)`.
         */
        fun actor(serviceAccount: BetaComplianceReviewServiceAccountActor) =
            actor(BetaComplianceReviewActor.ofServiceAccount(serviceAccount))

        /**
         * Alias for calling [actor] with the following:
         * ```java
         * BetaComplianceReviewServiceAccountActor.builder()
         *     .serviceAccountId(serviceAccountId)
         *     .build()
         * ```
         */
        fun serviceAccountActor(serviceAccountId: String) =
            actor(
                BetaComplianceReviewServiceAccountActor.builder()
                    .serviceAccountId(serviceAccountId)
                    .build()
            )

        /**
         * Read the item, and save any copy you need, by this time. The item can stop being readable
         * sooner.
         */
        fun contentExpiresAt(contentExpiresAt: OffsetDateTime) =
            contentExpiresAt(JsonField.of(contentExpiresAt))

        /**
         * Sets [Builder.contentExpiresAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contentExpiresAt] with a well-typed [OffsetDateTime]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun contentExpiresAt(contentExpiresAt: JsonField<OffsetDateTime>) = apply {
            this.contentExpiresAt = contentExpiresAt
        }

        /** For a message, when Anthropic received the call. */
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
         * What is particular to this kind of item. New types can appear, so accept a type that you
         * don't know.
         */
        fun details(details: BetaComplianceReviewItemDetails) = details(JsonField.of(details))

        /**
         * Sets [Builder.details] to an arbitrary JSON value.
         *
         * You should usually call [Builder.details] with a well-typed
         * [BetaComplianceReviewItemDetails] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun details(details: JsonField<BetaComplianceReviewItemDetails>) = apply {
            this.details = details
        }

        /**
         * Alias for calling [details] with `BetaComplianceReviewItemDetails.ofMessage(message)`.
         */
        fun details(message: BetaComplianceReviewMessageDetails) =
            details(BetaComplianceReviewItemDetails.ofMessage(message))

        /**
         * The UUID of the organization that the item belongs to. For a message, the organization
         * that sent it.
         */
        fun organizationId(organizationId: String) = organizationId(JsonField.of(organizationId))

        /**
         * Sets [Builder.organizationId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.organizationId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun organizationId(organizationId: JsonField<String>) = apply {
            this.organizationId = organizationId
        }

        /** Why the item is readable. New types can appear, so accept a type that you don't know. */
        fun readReason(readReason: BetaComplianceReviewReadReason) =
            readReason(JsonField.of(readReason))

        /**
         * Sets [Builder.readReason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.readReason] with a well-typed
         * [BetaComplianceReviewReadReason] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun readReason(readReason: JsonField<BetaComplianceReviewReadReason>) = apply {
            this.readReason = readReason
        }

        /**
         * Alias for calling [readReason] with
         * `BetaComplianceReviewReadReason.ofSafeguardsReview(safeguardsReview)`.
         */
        fun readReason(safeguardsReview: BetaComplianceReviewSafeguardsReason) =
            readReason(BetaComplianceReviewReadReason.ofSafeguardsReview(safeguardsReview))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("compliance_review_item")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * The ID of the user profile that the item was attributed to (`uprof_...`). Null when none
         * was recorded.
         */
        fun userProfileId(userProfileId: String?) =
            userProfileId(JsonField.ofNullable(userProfileId))

        /** Alias for calling [Builder.userProfileId] with `userProfileId.orElse(null)`. */
        fun userProfileId(userProfileId: Optional<String>) =
            userProfileId(userProfileId.getOrNull())

        /**
         * Sets [Builder.userProfileId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.userProfileId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun userProfileId(userProfileId: JsonField<String>) = apply {
            this.userProfileId = userProfileId
        }

        /**
         * The ID of the workspace (`wrkspc_...`). Null when the item isn't attributed to a
         * workspace.
         */
        fun workspaceId(workspaceId: String?) = workspaceId(JsonField.ofNullable(workspaceId))

        /** Alias for calling [Builder.workspaceId] with `workspaceId.orElse(null)`. */
        fun workspaceId(workspaceId: Optional<String>) = workspaceId(workspaceId.getOrNull())

        /**
         * Sets [Builder.workspaceId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun workspaceId(workspaceId: JsonField<String>) = apply { this.workspaceId = workspaceId }

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
         * Returns an immutable instance of [BetaComplianceReviewItem].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .actor()
         * .contentExpiresAt()
         * .createdAt()
         * .details()
         * .organizationId()
         * .readReason()
         * .userProfileId()
         * .workspaceId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaComplianceReviewItem =
            BetaComplianceReviewItem(
                checkRequired("id", id),
                checkRequired("actor", actor),
                checkRequired("contentExpiresAt", contentExpiresAt),
                checkRequired("createdAt", createdAt),
                checkRequired("details", details),
                checkRequired("organizationId", organizationId),
                checkRequired("readReason", readReason),
                type,
                checkRequired("userProfileId", userProfileId),
                checkRequired("workspaceId", workspaceId),
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
    fun validate(): BetaComplianceReviewItem = apply {
        if (validated) {
            return@apply
        }

        id()
        actor().ifPresent { it.validate() }
        contentExpiresAt()
        createdAt()
        details().validate()
        organizationId()
        readReason().validate()
        _type().let {
            if (it != JsonValue.from("compliance_review_item")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        userProfileId()
        workspaceId()
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
            (actor.asKnown().getOrNull()?.validity() ?: 0) +
            (if (contentExpiresAt.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (details.asKnown().getOrNull()?.validity() ?: 0) +
            (if (organizationId.asKnown().isPresent) 1 else 0) +
            (readReason.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("compliance_review_item")) 1 else 0 } +
            (if (userProfileId.asKnown().isPresent) 1 else 0) +
            (if (workspaceId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceReviewItem &&
            id == other.id &&
            actor == other.actor &&
            contentExpiresAt == other.contentExpiresAt &&
            createdAt == other.createdAt &&
            details == other.details &&
            organizationId == other.organizationId &&
            readReason == other.readReason &&
            type == other.type &&
            userProfileId == other.userProfileId &&
            workspaceId == other.workspaceId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            actor,
            contentExpiresAt,
            createdAt,
            details,
            organizationId,
            readReason,
            type,
            userProfileId,
            workspaceId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaComplianceReviewItem{id=$id, actor=$actor, contentExpiresAt=$contentExpiresAt, createdAt=$createdAt, details=$details, organizationId=$organizationId, readReason=$readReason, type=$type, userProfileId=$userProfileId, workspaceId=$workspaceId, additionalProperties=$additionalProperties}"
}
