package com.anthropic.models.beta.compliance.review.notices

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * A message from Anthropic to an organization about its review cases or about the organization as a
 * whole, such as a request to respond by a date, a warning, or a question. A notice doesn't change
 * after Anthropic sends it.
 */
class BetaComplianceReviewNotice
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val caseIds: JsonField<List<String>>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val kind: JsonField<BetaComplianceReviewNoticeKind>,
    private val message: JsonField<String>,
    private val organizationId: JsonField<String>,
    private val respondBy: JsonField<OffsetDateTime>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("case_ids")
        @ExcludeMissing
        caseIds: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("kind")
        @ExcludeMissing
        kind: JsonField<BetaComplianceReviewNoticeKind> = JsonMissing.of(),
        @JsonProperty("message") @ExcludeMissing message: JsonField<String> = JsonMissing.of(),
        @JsonProperty("organization_id")
        @ExcludeMissing
        organizationId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("respond_by")
        @ExcludeMissing
        respondBy: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(id, caseIds, createdAt, kind, message, organizationId, respondBy, type, mutableMapOf())

    /**
     * The unique ID of the notice (`rvn_...`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * The IDs of the cases that the notice is about (`rvc_...`). Empty when the notice is about the
     * whole organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun caseIds(): List<String> = caseIds.getRequired("case_ids")

    /**
     * When Anthropic sent the notice.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * What kind of notice this is.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun kind(): BetaComplianceReviewNoticeKind = kind.getRequired("kind")

    /**
     * The text of the notice.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun message(): String = message.getRequired("message")

    /**
     * The UUID of the organization that the notice is for.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun organizationId(): String = organizationId.getRequired("organization_id")

    /**
     * The time by which Anthropic asks for an answer. Null when there is no deadline. Missing it
     * changes nothing on a case, and the time to respond to a case can end before it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun respondBy(): Optional<OffsetDateTime> = respondBy.getOptional("respond_by")

    /**
     * The object type, always `compliance_review_notice`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("compliance_review_notice")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [caseIds].
     *
     * Unlike [caseIds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("case_ids") @ExcludeMissing fun _caseIds(): JsonField<List<String>> = caseIds

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [kind].
     *
     * Unlike [kind], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("kind")
    @ExcludeMissing
    fun _kind(): JsonField<BetaComplianceReviewNoticeKind> = kind

    /**
     * Returns the raw JSON value of [message].
     *
     * Unlike [message], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("message") @ExcludeMissing fun _message(): JsonField<String> = message

    /**
     * Returns the raw JSON value of [organizationId].
     *
     * Unlike [organizationId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("organization_id")
    @ExcludeMissing
    fun _organizationId(): JsonField<String> = organizationId

    /**
     * Returns the raw JSON value of [respondBy].
     *
     * Unlike [respondBy], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("respond_by")
    @ExcludeMissing
    fun _respondBy(): JsonField<OffsetDateTime> = respondBy

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
         * Returns a mutable builder for constructing an instance of [BetaComplianceReviewNotice].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .caseIds()
         * .createdAt()
         * .kind()
         * .message()
         * .organizationId()
         * .respondBy()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaComplianceReviewNotice]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var caseIds: JsonField<MutableList<String>>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var kind: JsonField<BetaComplianceReviewNoticeKind>? = null
        private var message: JsonField<String>? = null
        private var organizationId: JsonField<String>? = null
        private var respondBy: JsonField<OffsetDateTime>? = null
        private var type: JsonValue = JsonValue.from("compliance_review_notice")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaComplianceReviewNotice: BetaComplianceReviewNotice) = apply {
            id = betaComplianceReviewNotice.id
            caseIds =
                betaComplianceReviewNotice.caseIds
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            createdAt = betaComplianceReviewNotice.createdAt
            kind = betaComplianceReviewNotice.kind
            message = betaComplianceReviewNotice.message
            organizationId = betaComplianceReviewNotice.organizationId
            respondBy = betaComplianceReviewNotice.respondBy
            type = betaComplianceReviewNotice.type
            additionalProperties = betaComplianceReviewNotice.additionalProperties.toMutableMap()
        }

        /** The unique ID of the notice (`rvn_...`). */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * The IDs of the cases that the notice is about (`rvc_...`). Empty when the notice is about
         * the whole organization.
         */
        fun caseIds(caseIds: List<String>) = caseIds(JsonField.of(caseIds))

        /**
         * Sets [Builder.caseIds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.caseIds] with a well-typed `List<String>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun caseIds(caseIds: JsonField<List<String>>) = apply {
            this.caseIds = caseIds.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [caseIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addCaseId(caseId: String) = apply {
            caseIds =
                (caseIds ?: JsonField.of(mutableListOf())).also {
                    checkKnown("caseIds", it).add(caseId)
                }
        }

        /** When Anthropic sent the notice. */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** What kind of notice this is. */
        fun kind(kind: BetaComplianceReviewNoticeKind) = kind(JsonField.of(kind))

        /**
         * Sets [Builder.kind] to an arbitrary JSON value.
         *
         * You should usually call [Builder.kind] with a well-typed [BetaComplianceReviewNoticeKind]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun kind(kind: JsonField<BetaComplianceReviewNoticeKind>) = apply { this.kind = kind }

        /** The text of the notice. */
        fun message(message: String) = message(JsonField.of(message))

        /**
         * Sets [Builder.message] to an arbitrary JSON value.
         *
         * You should usually call [Builder.message] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun message(message: JsonField<String>) = apply { this.message = message }

        /** The UUID of the organization that the notice is for. */
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

        /**
         * The time by which Anthropic asks for an answer. Null when there is no deadline. Missing
         * it changes nothing on a case, and the time to respond to a case can end before it.
         */
        fun respondBy(respondBy: OffsetDateTime?) = respondBy(JsonField.ofNullable(respondBy))

        /** Alias for calling [Builder.respondBy] with `respondBy.orElse(null)`. */
        fun respondBy(respondBy: Optional<OffsetDateTime>) = respondBy(respondBy.getOrNull())

        /**
         * Sets [Builder.respondBy] to an arbitrary JSON value.
         *
         * You should usually call [Builder.respondBy] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun respondBy(respondBy: JsonField<OffsetDateTime>) = apply { this.respondBy = respondBy }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("compliance_review_notice")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaComplianceReviewNotice].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .caseIds()
         * .createdAt()
         * .kind()
         * .message()
         * .organizationId()
         * .respondBy()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaComplianceReviewNotice =
            BetaComplianceReviewNotice(
                checkRequired("id", id),
                checkRequired("caseIds", caseIds).map { it.toImmutable() },
                checkRequired("createdAt", createdAt),
                checkRequired("kind", kind),
                checkRequired("message", message),
                checkRequired("organizationId", organizationId),
                checkRequired("respondBy", respondBy),
                type,
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
    fun validate(): BetaComplianceReviewNotice = apply {
        if (validated) {
            return@apply
        }

        id()
        caseIds()
        createdAt()
        kind().validate()
        message()
        organizationId()
        respondBy()
        _type().let {
            if (it != JsonValue.from("compliance_review_notice")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
            (caseIds.asKnown().getOrNull()?.size ?: 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (kind.asKnown().getOrNull()?.validity() ?: 0) +
            (if (message.asKnown().isPresent) 1 else 0) +
            (if (organizationId.asKnown().isPresent) 1 else 0) +
            (if (respondBy.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("compliance_review_notice")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceReviewNotice &&
            id == other.id &&
            caseIds == other.caseIds &&
            createdAt == other.createdAt &&
            kind == other.kind &&
            message == other.message &&
            organizationId == other.organizationId &&
            respondBy == other.respondBy &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            caseIds,
            createdAt,
            kind,
            message,
            organizationId,
            respondBy,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaComplianceReviewNotice{id=$id, caseIds=$caseIds, createdAt=$createdAt, kind=$kind, message=$message, organizationId=$organizationId, respondBy=$respondBy, type=$type, additionalProperties=$additionalProperties}"
}
