package com.anthropic.models.beta.compliance.review.cases

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
 * A record of an organization's messages that Anthropic flagged and sent to the organization to
 * review. It lists the messages by ID and doesn't include their content.
 */
class BetaComplianceReviewCase
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val enforcementAction: JsonField<BetaComplianceReviewEnforcementAction>,
    private val organizationId: JsonField<String>,
    private val policyCategories: JsonField<List<BetaComplianceReviewPolicyCategory>>,
    private val status: JsonField<BetaComplianceReviewCaseStatus>,
    private val transcripts: JsonField<List<BetaComplianceReviewTranscript>>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val workspaceId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("enforcement_action")
        @ExcludeMissing
        enforcementAction: JsonField<BetaComplianceReviewEnforcementAction> = JsonMissing.of(),
        @JsonProperty("organization_id")
        @ExcludeMissing
        organizationId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("policy_categories")
        @ExcludeMissing
        policyCategories: JsonField<List<BetaComplianceReviewPolicyCategory>> = JsonMissing.of(),
        @JsonProperty("status")
        @ExcludeMissing
        status: JsonField<BetaComplianceReviewCaseStatus> = JsonMissing.of(),
        @JsonProperty("transcripts")
        @ExcludeMissing
        transcripts: JsonField<List<BetaComplianceReviewTranscript>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("workspace_id")
        @ExcludeMissing
        workspaceId: JsonField<String> = JsonMissing.of(),
    ) : this(
        id,
        createdAt,
        enforcementAction,
        organizationId,
        policyCategories,
        status,
        transcripts,
        type,
        updatedAt,
        workspaceId,
        mutableMapOf(),
    )

    /**
     * The unique ID of the case (`rvc_...`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * When Anthropic sent the case. The time you have to respond starts here.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * The action that Anthropic took before it sent the case. Null when it took none.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun enforcementAction(): Optional<BetaComplianceReviewEnforcementAction> =
        enforcementAction.getOptional("enforcement_action")

    /**
     * The UUID of the organization that sent the flagged messages, which the case belongs to.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun organizationId(): String = organizationId.getRequired("organization_id")

    /**
     * Anthropic's labels for why the messages were flagged, such as `cyber` or `fraud`. There is at
     * least one. New values can appear, so accept a value that you don't know.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun policyCategories(): List<BetaComplianceReviewPolicyCategory> =
        policyCategories.getRequired("policy_categories")

    /**
     * Whether Anthropic is still waiting for a response.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): BetaComplianceReviewCaseStatus = status.getRequired("status")

    /**
     * The flagged messages. There is at least one, and the list isn't paginated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun transcripts(): List<BetaComplianceReviewTranscript> = transcripts.getRequired("transcripts")

    /**
     * The object type, always `compliance_review_case`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("compliance_review_case")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * When the case last changed. It changes when Anthropic sends the case, when the case closes,
     * and when Anthropic sends a notice about the case.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * The ID of the workspace that sent the flagged messages (`wrkspc_...`). Null when the messages
     * have no workspace, and it can be null for the default workspace.
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
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [enforcementAction].
     *
     * Unlike [enforcementAction], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("enforcement_action")
    @ExcludeMissing
    fun _enforcementAction(): JsonField<BetaComplianceReviewEnforcementAction> = enforcementAction

    /**
     * Returns the raw JSON value of [organizationId].
     *
     * Unlike [organizationId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("organization_id")
    @ExcludeMissing
    fun _organizationId(): JsonField<String> = organizationId

    /**
     * Returns the raw JSON value of [policyCategories].
     *
     * Unlike [policyCategories], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("policy_categories")
    @ExcludeMissing
    fun _policyCategories(): JsonField<List<BetaComplianceReviewPolicyCategory>> = policyCategories

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status")
    @ExcludeMissing
    fun _status(): JsonField<BetaComplianceReviewCaseStatus> = status

    /**
     * Returns the raw JSON value of [transcripts].
     *
     * Unlike [transcripts], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("transcripts")
    @ExcludeMissing
    fun _transcripts(): JsonField<List<BetaComplianceReviewTranscript>> = transcripts

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

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
         * Returns a mutable builder for constructing an instance of [BetaComplianceReviewCase].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .enforcementAction()
         * .organizationId()
         * .policyCategories()
         * .status()
         * .transcripts()
         * .updatedAt()
         * .workspaceId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaComplianceReviewCase]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var enforcementAction: JsonField<BetaComplianceReviewEnforcementAction>? = null
        private var organizationId: JsonField<String>? = null
        private var policyCategories: JsonField<MutableList<BetaComplianceReviewPolicyCategory>>? =
            null
        private var status: JsonField<BetaComplianceReviewCaseStatus>? = null
        private var transcripts: JsonField<MutableList<BetaComplianceReviewTranscript>>? = null
        private var type: JsonValue = JsonValue.from("compliance_review_case")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var workspaceId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaComplianceReviewCase: BetaComplianceReviewCase) = apply {
            id = betaComplianceReviewCase.id
            createdAt = betaComplianceReviewCase.createdAt
            enforcementAction = betaComplianceReviewCase.enforcementAction
            organizationId = betaComplianceReviewCase.organizationId
            policyCategories =
                betaComplianceReviewCase.policyCategories
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            status = betaComplianceReviewCase.status
            transcripts =
                betaComplianceReviewCase.transcripts
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            type = betaComplianceReviewCase.type
            updatedAt = betaComplianceReviewCase.updatedAt
            workspaceId = betaComplianceReviewCase.workspaceId
            additionalProperties = betaComplianceReviewCase.additionalProperties.toMutableMap()
        }

        /** The unique ID of the case (`rvc_...`). */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** When Anthropic sent the case. The time you have to respond starts here. */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** The action that Anthropic took before it sent the case. Null when it took none. */
        fun enforcementAction(enforcementAction: BetaComplianceReviewEnforcementAction?) =
            enforcementAction(JsonField.ofNullable(enforcementAction))

        /** Alias for calling [Builder.enforcementAction] with `enforcementAction.orElse(null)`. */
        fun enforcementAction(enforcementAction: Optional<BetaComplianceReviewEnforcementAction>) =
            enforcementAction(enforcementAction.getOrNull())

        /**
         * Sets [Builder.enforcementAction] to an arbitrary JSON value.
         *
         * You should usually call [Builder.enforcementAction] with a well-typed
         * [BetaComplianceReviewEnforcementAction] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun enforcementAction(enforcementAction: JsonField<BetaComplianceReviewEnforcementAction>) =
            apply {
                this.enforcementAction = enforcementAction
            }

        /**
         * The UUID of the organization that sent the flagged messages, which the case belongs to.
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

        /**
         * Anthropic's labels for why the messages were flagged, such as `cyber` or `fraud`. There
         * is at least one. New values can appear, so accept a value that you don't know.
         */
        fun policyCategories(policyCategories: List<BetaComplianceReviewPolicyCategory>) =
            policyCategories(JsonField.of(policyCategories))

        /**
         * Sets [Builder.policyCategories] to an arbitrary JSON value.
         *
         * You should usually call [Builder.policyCategories] with a well-typed
         * `List<BetaComplianceReviewPolicyCategory>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun policyCategories(
            policyCategories: JsonField<List<BetaComplianceReviewPolicyCategory>>
        ) = apply { this.policyCategories = policyCategories.map { it.toMutableList() } }

        /**
         * Adds a single [BetaComplianceReviewPolicyCategory] to [policyCategories].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addPolicyCategory(policyCategory: BetaComplianceReviewPolicyCategory) = apply {
            policyCategories =
                (policyCategories ?: JsonField.of(mutableListOf())).also {
                    checkKnown("policyCategories", it).add(policyCategory)
                }
        }

        /** Whether Anthropic is still waiting for a response. */
        fun status(status: BetaComplianceReviewCaseStatus) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed
         * [BetaComplianceReviewCaseStatus] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun status(status: JsonField<BetaComplianceReviewCaseStatus>) = apply {
            this.status = status
        }

        /** Alias for calling [status] with `BetaComplianceReviewCaseStatus.ofOpen(open)`. */
        fun status(open: BetaComplianceReviewCaseOpen) =
            status(BetaComplianceReviewCaseStatus.ofOpen(open))

        /** Alias for calling [status] with `BetaComplianceReviewCaseStatus.ofClosed(closed)`. */
        fun status(closed: BetaComplianceReviewCaseClosed) =
            status(BetaComplianceReviewCaseStatus.ofClosed(closed))

        /**
         * Alias for calling [status] with the following:
         * ```java
         * BetaComplianceReviewCaseClosed.builder()
         *     .reason(reason)
         *     .build()
         * ```
         */
        fun closedStatus(reason: BetaComplianceReviewCaseCloseReason) =
            status(BetaComplianceReviewCaseClosed.builder().reason(reason).build())

        /** The flagged messages. There is at least one, and the list isn't paginated. */
        fun transcripts(transcripts: List<BetaComplianceReviewTranscript>) =
            transcripts(JsonField.of(transcripts))

        /**
         * Sets [Builder.transcripts] to an arbitrary JSON value.
         *
         * You should usually call [Builder.transcripts] with a well-typed
         * `List<BetaComplianceReviewTranscript>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun transcripts(transcripts: JsonField<List<BetaComplianceReviewTranscript>>) = apply {
            this.transcripts = transcripts.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaComplianceReviewTranscript] to [transcripts].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addTranscript(transcript: BetaComplianceReviewTranscript) = apply {
            transcripts =
                (transcripts ?: JsonField.of(mutableListOf())).also {
                    checkKnown("transcripts", it).add(transcript)
                }
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("compliance_review_case")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * When the case last changed. It changes when Anthropic sends the case, when the case
         * closes, and when Anthropic sends a notice about the case.
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
         * The ID of the workspace that sent the flagged messages (`wrkspc_...`). Null when the
         * messages have no workspace, and it can be null for the default workspace.
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
         * Returns an immutable instance of [BetaComplianceReviewCase].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .enforcementAction()
         * .organizationId()
         * .policyCategories()
         * .status()
         * .transcripts()
         * .updatedAt()
         * .workspaceId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaComplianceReviewCase =
            BetaComplianceReviewCase(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("enforcementAction", enforcementAction),
                checkRequired("organizationId", organizationId),
                checkRequired("policyCategories", policyCategories).map { it.toImmutable() },
                checkRequired("status", status),
                checkRequired("transcripts", transcripts).map { it.toImmutable() },
                type,
                checkRequired("updatedAt", updatedAt),
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
    fun validate(): BetaComplianceReviewCase = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        enforcementAction().ifPresent { it.validate() }
        organizationId()
        policyCategories().forEach { it.validate() }
        status().validate()
        transcripts().forEach { it.validate() }
        _type().let {
            if (it != JsonValue.from("compliance_review_case")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        updatedAt()
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
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (enforcementAction.asKnown().getOrNull()?.validity() ?: 0) +
            (if (organizationId.asKnown().isPresent) 1 else 0) +
            (policyCategories.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
            (transcripts.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            type.let { if (it == JsonValue.from("compliance_review_case")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (if (workspaceId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceReviewCase &&
            id == other.id &&
            createdAt == other.createdAt &&
            enforcementAction == other.enforcementAction &&
            organizationId == other.organizationId &&
            policyCategories == other.policyCategories &&
            status == other.status &&
            transcripts == other.transcripts &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            workspaceId == other.workspaceId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            createdAt,
            enforcementAction,
            organizationId,
            policyCategories,
            status,
            transcripts,
            type,
            updatedAt,
            workspaceId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaComplianceReviewCase{id=$id, createdAt=$createdAt, enforcementAction=$enforcementAction, organizationId=$organizationId, policyCategories=$policyCategories, status=$status, transcripts=$transcripts, type=$type, updatedAt=$updatedAt, workspaceId=$workspaceId, additionalProperties=$additionalProperties}"
}
