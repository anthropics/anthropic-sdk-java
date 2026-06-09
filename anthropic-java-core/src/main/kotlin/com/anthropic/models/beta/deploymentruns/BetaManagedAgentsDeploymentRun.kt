// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.agents.BetaManagedAgentsAgentReference
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * A persistent, append-only record of a single deployment execution. Records session creation
 * success or failure — no session lifecycle tracking.
 */
class BetaManagedAgentsDeploymentRun
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val agent: JsonField<BetaManagedAgentsAgentReference>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val deploymentId: JsonField<String>,
    private val error: JsonField<Error>,
    private val sessionId: JsonField<String>,
    private val triggerContext: JsonField<BetaManagedAgentsTriggerContext>,
    private val type: JsonField<Type>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("agent")
        @ExcludeMissing
        agent: JsonField<BetaManagedAgentsAgentReference> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("deployment_id")
        @ExcludeMissing
        deploymentId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("error") @ExcludeMissing error: JsonField<Error> = JsonMissing.of(),
        @JsonProperty("session_id") @ExcludeMissing sessionId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("trigger_context")
        @ExcludeMissing
        triggerContext: JsonField<BetaManagedAgentsTriggerContext> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
    ) : this(
        id,
        agent,
        createdAt,
        deploymentId,
        error,
        sessionId,
        triggerContext,
        type,
        mutableMapOf(),
    )

    /**
     * Unique identifier for this run (`drun_...`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * A resolved agent reference with a concrete version.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun agent(): BetaManagedAgentsAgentReference = agent.getRequired("agent")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * ID of the deployment that produced this run.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun deploymentId(): String = deploymentId.getRequired("deployment_id")

    /**
     * Why the run failed to create a session. The type identifies the failure; message is
     * human-readable detail.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun error(): Optional<Error> = error.getOptional("error")

    /**
     * Populated on success. Null on creation failure. Exactly one of session_id or error is
     * non-null.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun sessionId(): Optional<String> = sessionId.getOptional("session_id")

    /**
     * Describes what triggered a deployment run, with trigger-specific metadata.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun triggerContext(): BetaManagedAgentsTriggerContext =
        triggerContext.getRequired("trigger_context")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [agent].
     *
     * Unlike [agent], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("agent")
    @ExcludeMissing
    fun _agent(): JsonField<BetaManagedAgentsAgentReference> = agent

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [deploymentId].
     *
     * Unlike [deploymentId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("deployment_id")
    @ExcludeMissing
    fun _deploymentId(): JsonField<String> = deploymentId

    /**
     * Returns the raw JSON value of [error].
     *
     * Unlike [error], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("error") @ExcludeMissing fun _error(): JsonField<Error> = error

    /**
     * Returns the raw JSON value of [sessionId].
     *
     * Unlike [sessionId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("session_id") @ExcludeMissing fun _sessionId(): JsonField<String> = sessionId

    /**
     * Returns the raw JSON value of [triggerContext].
     *
     * Unlike [triggerContext], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("trigger_context")
    @ExcludeMissing
    fun _triggerContext(): JsonField<BetaManagedAgentsTriggerContext> = triggerContext

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

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
         * [BetaManagedAgentsDeploymentRun].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .agent()
         * .createdAt()
         * .deploymentId()
         * .error()
         * .sessionId()
         * .triggerContext()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsDeploymentRun]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var agent: JsonField<BetaManagedAgentsAgentReference>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var deploymentId: JsonField<String>? = null
        private var error: JsonField<Error>? = null
        private var sessionId: JsonField<String>? = null
        private var triggerContext: JsonField<BetaManagedAgentsTriggerContext>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsDeploymentRun: BetaManagedAgentsDeploymentRun) = apply {
            id = betaManagedAgentsDeploymentRun.id
            agent = betaManagedAgentsDeploymentRun.agent
            createdAt = betaManagedAgentsDeploymentRun.createdAt
            deploymentId = betaManagedAgentsDeploymentRun.deploymentId
            error = betaManagedAgentsDeploymentRun.error
            sessionId = betaManagedAgentsDeploymentRun.sessionId
            triggerContext = betaManagedAgentsDeploymentRun.triggerContext
            type = betaManagedAgentsDeploymentRun.type
            additionalProperties =
                betaManagedAgentsDeploymentRun.additionalProperties.toMutableMap()
        }

        /** Unique identifier for this run (`drun_...`). */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** A resolved agent reference with a concrete version. */
        fun agent(agent: BetaManagedAgentsAgentReference) = agent(JsonField.of(agent))

        /**
         * Sets [Builder.agent] to an arbitrary JSON value.
         *
         * You should usually call [Builder.agent] with a well-typed
         * [BetaManagedAgentsAgentReference] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun agent(agent: JsonField<BetaManagedAgentsAgentReference>) = apply { this.agent = agent }

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

        /** ID of the deployment that produced this run. */
        fun deploymentId(deploymentId: String) = deploymentId(JsonField.of(deploymentId))

        /**
         * Sets [Builder.deploymentId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.deploymentId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun deploymentId(deploymentId: JsonField<String>) = apply {
            this.deploymentId = deploymentId
        }

        /**
         * Why the run failed to create a session. The type identifies the failure; message is
         * human-readable detail.
         */
        fun error(error: Error?) = error(JsonField.ofNullable(error))

        /** Alias for calling [Builder.error] with `error.orElse(null)`. */
        fun error(error: Optional<Error>) = error(error.getOrNull())

        /**
         * Sets [Builder.error] to an arbitrary JSON value.
         *
         * You should usually call [Builder.error] with a well-typed [Error] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun error(error: JsonField<Error>) = apply { this.error = error }

        /** Alias for calling [error] with `Error.ofEnvironmentArchived(environmentArchived)`. */
        fun error(environmentArchived: BetaManagedAgentsEnvironmentArchivedRunError) =
            error(Error.ofEnvironmentArchived(environmentArchived))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsEnvironmentArchivedRunError.builder()
         *     .type(BetaManagedAgentsEnvironmentArchivedRunError.Type.ENVIRONMENT_ARCHIVED_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun environmentArchivedError(message: String) =
            error(
                BetaManagedAgentsEnvironmentArchivedRunError.builder()
                    .type(
                        BetaManagedAgentsEnvironmentArchivedRunError.Type.ENVIRONMENT_ARCHIVED_ERROR
                    )
                    .message(message)
                    .build()
            )

        /** Alias for calling [error] with `Error.ofAgentArchived(agentArchived)`. */
        fun error(agentArchived: BetaManagedAgentsAgentArchivedRunError) =
            error(Error.ofAgentArchived(agentArchived))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsAgentArchivedRunError.builder()
         *     .type(BetaManagedAgentsAgentArchivedRunError.Type.AGENT_ARCHIVED_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun agentArchivedError(message: String) =
            error(
                BetaManagedAgentsAgentArchivedRunError.builder()
                    .type(BetaManagedAgentsAgentArchivedRunError.Type.AGENT_ARCHIVED_ERROR)
                    .message(message)
                    .build()
            )

        /** Alias for calling [error] with `Error.ofEnvironmentNotFound(environmentNotFound)`. */
        fun error(environmentNotFound: BetaManagedAgentsEnvironmentNotFoundRunError) =
            error(Error.ofEnvironmentNotFound(environmentNotFound))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsEnvironmentNotFoundRunError.builder()
         *     .type(BetaManagedAgentsEnvironmentNotFoundRunError.Type.ENVIRONMENT_NOT_FOUND_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun environmentNotFoundError(message: String) =
            error(
                BetaManagedAgentsEnvironmentNotFoundRunError.builder()
                    .type(
                        BetaManagedAgentsEnvironmentNotFoundRunError.Type
                            .ENVIRONMENT_NOT_FOUND_ERROR
                    )
                    .message(message)
                    .build()
            )

        /** Alias for calling [error] with `Error.ofVaultNotFound(vaultNotFound)`. */
        fun error(vaultNotFound: BetaManagedAgentsVaultNotFoundRunError) =
            error(Error.ofVaultNotFound(vaultNotFound))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsVaultNotFoundRunError.builder()
         *     .type(BetaManagedAgentsVaultNotFoundRunError.Type.VAULT_NOT_FOUND_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun vaultNotFoundError(message: String) =
            error(
                BetaManagedAgentsVaultNotFoundRunError.builder()
                    .type(BetaManagedAgentsVaultNotFoundRunError.Type.VAULT_NOT_FOUND_ERROR)
                    .message(message)
                    .build()
            )

        /** Alias for calling [error] with `Error.ofVaultArchived(vaultArchived)`. */
        fun error(vaultArchived: BetaManagedAgentsVaultArchivedRunError) =
            error(Error.ofVaultArchived(vaultArchived))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsVaultArchivedRunError.builder()
         *     .type(BetaManagedAgentsVaultArchivedRunError.Type.VAULT_ARCHIVED_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun vaultArchivedError(message: String) =
            error(
                BetaManagedAgentsVaultArchivedRunError.builder()
                    .type(BetaManagedAgentsVaultArchivedRunError.Type.VAULT_ARCHIVED_ERROR)
                    .message(message)
                    .build()
            )

        /** Alias for calling [error] with `Error.ofFileNotFound(fileNotFound)`. */
        fun error(fileNotFound: BetaManagedAgentsFileNotFoundRunError) =
            error(Error.ofFileNotFound(fileNotFound))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsFileNotFoundRunError.builder()
         *     .type(BetaManagedAgentsFileNotFoundRunError.Type.FILE_NOT_FOUND_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun fileNotFoundError(message: String) =
            error(
                BetaManagedAgentsFileNotFoundRunError.builder()
                    .type(BetaManagedAgentsFileNotFoundRunError.Type.FILE_NOT_FOUND_ERROR)
                    .message(message)
                    .build()
            )

        /** Alias for calling [error] with `Error.ofMemoryStoreArchived(memoryStoreArchived)`. */
        fun error(memoryStoreArchived: BetaManagedAgentsMemoryStoreArchivedRunError) =
            error(Error.ofMemoryStoreArchived(memoryStoreArchived))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsMemoryStoreArchivedRunError.builder()
         *     .type(BetaManagedAgentsMemoryStoreArchivedRunError.Type.MEMORY_STORE_ARCHIVED_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun memoryStoreArchivedError(message: String) =
            error(
                BetaManagedAgentsMemoryStoreArchivedRunError.builder()
                    .type(
                        BetaManagedAgentsMemoryStoreArchivedRunError.Type
                            .MEMORY_STORE_ARCHIVED_ERROR
                    )
                    .message(message)
                    .build()
            )

        /** Alias for calling [error] with `Error.ofSkillNotFound(skillNotFound)`. */
        fun error(skillNotFound: BetaManagedAgentsSkillNotFoundRunError) =
            error(Error.ofSkillNotFound(skillNotFound))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsSkillNotFoundRunError.builder()
         *     .type(BetaManagedAgentsSkillNotFoundRunError.Type.SKILL_NOT_FOUND_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun skillNotFoundError(message: String) =
            error(
                BetaManagedAgentsSkillNotFoundRunError.builder()
                    .type(BetaManagedAgentsSkillNotFoundRunError.Type.SKILL_NOT_FOUND_ERROR)
                    .message(message)
                    .build()
            )

        /**
         * Alias for calling [error] with
         * `Error.ofSessionResourceNotFound(sessionResourceNotFound)`.
         */
        fun error(sessionResourceNotFound: BetaManagedAgentsSessionResourceNotFoundRunError) =
            error(Error.ofSessionResourceNotFound(sessionResourceNotFound))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsSessionResourceNotFoundRunError.builder()
         *     .type(BetaManagedAgentsSessionResourceNotFoundRunError.Type.SESSION_RESOURCE_NOT_FOUND_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun sessionResourceNotFoundError(message: String) =
            error(
                BetaManagedAgentsSessionResourceNotFoundRunError.builder()
                    .type(
                        BetaManagedAgentsSessionResourceNotFoundRunError.Type
                            .SESSION_RESOURCE_NOT_FOUND_ERROR
                    )
                    .message(message)
                    .build()
            )

        /** Alias for calling [error] with `Error.ofWorkspaceArchived(workspaceArchived)`. */
        fun error(workspaceArchived: BetaManagedAgentsWorkspaceArchivedRunError) =
            error(Error.ofWorkspaceArchived(workspaceArchived))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsWorkspaceArchivedRunError.builder()
         *     .type(BetaManagedAgentsWorkspaceArchivedRunError.Type.WORKSPACE_ARCHIVED_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun workspaceArchivedError(message: String) =
            error(
                BetaManagedAgentsWorkspaceArchivedRunError.builder()
                    .type(BetaManagedAgentsWorkspaceArchivedRunError.Type.WORKSPACE_ARCHIVED_ERROR)
                    .message(message)
                    .build()
            )

        /** Alias for calling [error] with `Error.ofOrganizationDisabled(organizationDisabled)`. */
        fun error(organizationDisabled: BetaManagedAgentsOrganizationDisabledRunError) =
            error(Error.ofOrganizationDisabled(organizationDisabled))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsOrganizationDisabledRunError.builder()
         *     .type(BetaManagedAgentsOrganizationDisabledRunError.Type.ORGANIZATION_DISABLED_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun organizationDisabledError(message: String) =
            error(
                BetaManagedAgentsOrganizationDisabledRunError.builder()
                    .type(
                        BetaManagedAgentsOrganizationDisabledRunError.Type
                            .ORGANIZATION_DISABLED_ERROR
                    )
                    .message(message)
                    .build()
            )

        /** Alias for calling [error] with `Error.ofSessionRateLimited(sessionRateLimited)`. */
        fun error(sessionRateLimited: BetaManagedAgentsSessionRateLimitedRunError) =
            error(Error.ofSessionRateLimited(sessionRateLimited))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsSessionRateLimitedRunError.builder()
         *     .type(BetaManagedAgentsSessionRateLimitedRunError.Type.SESSION_RATE_LIMITED_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun sessionRateLimitedError(message: String) =
            error(
                BetaManagedAgentsSessionRateLimitedRunError.builder()
                    .type(
                        BetaManagedAgentsSessionRateLimitedRunError.Type.SESSION_RATE_LIMITED_ERROR
                    )
                    .message(message)
                    .build()
            )

        /**
         * Alias for calling [error] with
         * `Error.ofSessionCreationRejected(sessionCreationRejected)`.
         */
        fun error(sessionCreationRejected: BetaManagedAgentsSessionCreationRejectedRunError) =
            error(Error.ofSessionCreationRejected(sessionCreationRejected))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsSessionCreationRejectedRunError.builder()
         *     .type(BetaManagedAgentsSessionCreationRejectedRunError.Type.SESSION_CREATION_REJECTED_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun sessionCreationRejectedError(message: String) =
            error(
                BetaManagedAgentsSessionCreationRejectedRunError.builder()
                    .type(
                        BetaManagedAgentsSessionCreationRejectedRunError.Type
                            .SESSION_CREATION_REJECTED_ERROR
                    )
                    .message(message)
                    .build()
            )

        /** Alias for calling [error] with `Error.ofUnknown(unknown)`. */
        fun error(unknown: BetaManagedAgentsUnknownRunError) = error(Error.ofUnknown(unknown))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsUnknownRunError.builder()
         *     .type(BetaManagedAgentsUnknownRunError.Type.UNKNOWN_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun unknownError(message: String) =
            error(
                BetaManagedAgentsUnknownRunError.builder()
                    .type(BetaManagedAgentsUnknownRunError.Type.UNKNOWN_ERROR)
                    .message(message)
                    .build()
            )

        /**
         * Alias for calling [error] with
         * `Error.ofSelfHostedResourcesUnsupported(selfHostedResourcesUnsupported)`.
         */
        fun error(
            selfHostedResourcesUnsupported: BetaManagedAgentsSelfHostedResourcesUnsupportedRunError
        ) = error(Error.ofSelfHostedResourcesUnsupported(selfHostedResourcesUnsupported))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsSelfHostedResourcesUnsupportedRunError.builder()
         *     .type(BetaManagedAgentsSelfHostedResourcesUnsupportedRunError.Type.SELF_HOSTED_RESOURCES_UNSUPPORTED_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun selfHostedResourcesUnsupportedError(message: String) =
            error(
                BetaManagedAgentsSelfHostedResourcesUnsupportedRunError.builder()
                    .type(
                        BetaManagedAgentsSelfHostedResourcesUnsupportedRunError.Type
                            .SELF_HOSTED_RESOURCES_UNSUPPORTED_ERROR
                    )
                    .message(message)
                    .build()
            )

        /** Alias for calling [error] with `Error.ofMcpEgressBlocked(mcpEgressBlocked)`. */
        fun error(mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedRunError) =
            error(Error.ofMcpEgressBlocked(mcpEgressBlocked))

        /**
         * Alias for calling [error] with the following:
         * ```java
         * BetaManagedAgentsMcpEgressBlockedRunError.builder()
         *     .type(BetaManagedAgentsMcpEgressBlockedRunError.Type.MCP_EGRESS_BLOCKED_ERROR)
         *     .message(message)
         *     .build()
         * ```
         */
        fun mcpEgressBlockedError(message: String) =
            error(
                BetaManagedAgentsMcpEgressBlockedRunError.builder()
                    .type(BetaManagedAgentsMcpEgressBlockedRunError.Type.MCP_EGRESS_BLOCKED_ERROR)
                    .message(message)
                    .build()
            )

        /**
         * Populated on success. Null on creation failure. Exactly one of session_id or error is
         * non-null.
         */
        fun sessionId(sessionId: String?) = sessionId(JsonField.ofNullable(sessionId))

        /** Alias for calling [Builder.sessionId] with `sessionId.orElse(null)`. */
        fun sessionId(sessionId: Optional<String>) = sessionId(sessionId.getOrNull())

        /**
         * Sets [Builder.sessionId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.sessionId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun sessionId(sessionId: JsonField<String>) = apply { this.sessionId = sessionId }

        /** Describes what triggered a deployment run, with trigger-specific metadata. */
        fun triggerContext(triggerContext: BetaManagedAgentsTriggerContext) =
            triggerContext(JsonField.of(triggerContext))

        /**
         * Sets [Builder.triggerContext] to an arbitrary JSON value.
         *
         * You should usually call [Builder.triggerContext] with a well-typed
         * [BetaManagedAgentsTriggerContext] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun triggerContext(triggerContext: JsonField<BetaManagedAgentsTriggerContext>) = apply {
            this.triggerContext = triggerContext
        }

        /**
         * Alias for calling [triggerContext] with
         * `BetaManagedAgentsTriggerContext.ofSchedule(schedule)`.
         */
        fun triggerContext(schedule: BetaManagedAgentsScheduleTriggerContext) =
            triggerContext(BetaManagedAgentsTriggerContext.ofSchedule(schedule))

        /**
         * Alias for calling [triggerContext] with the following:
         * ```java
         * BetaManagedAgentsScheduleTriggerContext.builder()
         *     .type(BetaManagedAgentsScheduleTriggerContext.Type.SCHEDULE)
         *     .scheduledAt(scheduledAt)
         *     .build()
         * ```
         */
        fun scheduleTriggerContext(scheduledAt: OffsetDateTime) =
            triggerContext(
                BetaManagedAgentsScheduleTriggerContext.builder()
                    .type(BetaManagedAgentsScheduleTriggerContext.Type.SCHEDULE)
                    .scheduledAt(scheduledAt)
                    .build()
            )

        /**
         * Alias for calling [triggerContext] with
         * `BetaManagedAgentsTriggerContext.ofManual(manual)`.
         */
        fun triggerContext(manual: BetaManagedAgentsManualTriggerContext) =
            triggerContext(BetaManagedAgentsTriggerContext.ofManual(manual))

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaManagedAgentsDeploymentRun].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .agent()
         * .createdAt()
         * .deploymentId()
         * .error()
         * .sessionId()
         * .triggerContext()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsDeploymentRun =
            BetaManagedAgentsDeploymentRun(
                checkRequired("id", id),
                checkRequired("agent", agent),
                checkRequired("createdAt", createdAt),
                checkRequired("deploymentId", deploymentId),
                checkRequired("error", error),
                checkRequired("sessionId", sessionId),
                checkRequired("triggerContext", triggerContext),
                checkRequired("type", type),
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
    fun validate(): BetaManagedAgentsDeploymentRun = apply {
        if (validated) {
            return@apply
        }

        id()
        agent().validate()
        createdAt()
        deploymentId()
        error().ifPresent { it.validate() }
        sessionId()
        triggerContext().validate()
        type().validate()
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
            (agent.asKnown().getOrNull()?.validity() ?: 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (deploymentId.asKnown().isPresent) 1 else 0) +
            (error.asKnown().getOrNull()?.validity() ?: 0) +
            (if (sessionId.asKnown().isPresent) 1 else 0) +
            (triggerContext.asKnown().getOrNull()?.validity() ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * Why the run failed to create a session. The type identifies the failure; message is
     * human-readable detail.
     */
    @JsonDeserialize(using = Error.Deserializer::class)
    @JsonSerialize(using = Error.Serializer::class)
    class Error
    private constructor(
        private val environmentArchived: BetaManagedAgentsEnvironmentArchivedRunError? = null,
        private val agentArchived: BetaManagedAgentsAgentArchivedRunError? = null,
        private val environmentNotFound: BetaManagedAgentsEnvironmentNotFoundRunError? = null,
        private val vaultNotFound: BetaManagedAgentsVaultNotFoundRunError? = null,
        private val vaultArchived: BetaManagedAgentsVaultArchivedRunError? = null,
        private val fileNotFound: BetaManagedAgentsFileNotFoundRunError? = null,
        private val memoryStoreArchived: BetaManagedAgentsMemoryStoreArchivedRunError? = null,
        private val skillNotFound: BetaManagedAgentsSkillNotFoundRunError? = null,
        private val sessionResourceNotFound: BetaManagedAgentsSessionResourceNotFoundRunError? =
            null,
        private val workspaceArchived: BetaManagedAgentsWorkspaceArchivedRunError? = null,
        private val organizationDisabled: BetaManagedAgentsOrganizationDisabledRunError? = null,
        private val sessionRateLimited: BetaManagedAgentsSessionRateLimitedRunError? = null,
        private val sessionCreationRejected: BetaManagedAgentsSessionCreationRejectedRunError? =
            null,
        private val unknown: BetaManagedAgentsUnknownRunError? = null,
        private val selfHostedResourcesUnsupported:
            BetaManagedAgentsSelfHostedResourcesUnsupportedRunError? =
            null,
        private val mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedRunError? = null,
        private val _json: JsonValue? = null,
    ) {

        /** The deployment's environment was archived. */
        fun environmentArchived(): Optional<BetaManagedAgentsEnvironmentArchivedRunError> =
            Optional.ofNullable(environmentArchived)

        /** The deployment's agent was archived. */
        fun agentArchived(): Optional<BetaManagedAgentsAgentArchivedRunError> =
            Optional.ofNullable(agentArchived)

        /** The deployment's environment no longer exists. */
        fun environmentNotFound(): Optional<BetaManagedAgentsEnvironmentNotFoundRunError> =
            Optional.ofNullable(environmentNotFound)

        /** A vault referenced by the deployment no longer exists. */
        fun vaultNotFound(): Optional<BetaManagedAgentsVaultNotFoundRunError> =
            Optional.ofNullable(vaultNotFound)

        /** A vault referenced by the deployment is archived. */
        fun vaultArchived(): Optional<BetaManagedAgentsVaultArchivedRunError> =
            Optional.ofNullable(vaultArchived)

        /** A file resource referenced by the deployment no longer exists. */
        fun fileNotFound(): Optional<BetaManagedAgentsFileNotFoundRunError> =
            Optional.ofNullable(fileNotFound)

        /** A memory store referenced by the deployment is archived. */
        fun memoryStoreArchived(): Optional<BetaManagedAgentsMemoryStoreArchivedRunError> =
            Optional.ofNullable(memoryStoreArchived)

        /** A skill referenced by the deployment's agent no longer exists. */
        fun skillNotFound(): Optional<BetaManagedAgentsSkillNotFoundRunError> =
            Optional.ofNullable(skillNotFound)

        /** A referenced resource no longer exists and its kind was not reported. */
        fun sessionResourceNotFound(): Optional<BetaManagedAgentsSessionResourceNotFoundRunError> =
            Optional.ofNullable(sessionResourceNotFound)

        /** The deployment's workspace was archived. */
        fun workspaceArchived(): Optional<BetaManagedAgentsWorkspaceArchivedRunError> =
            Optional.ofNullable(workspaceArchived)

        /** The deployment's organization is disabled. */
        fun organizationDisabled(): Optional<BetaManagedAgentsOrganizationDisabledRunError> =
            Optional.ofNullable(organizationDisabled)

        /**
         * Session creation was rejected due to rate limiting. The schedule keeps firing; subsequent
         * runs may succeed.
         */
        fun sessionRateLimited(): Optional<BetaManagedAgentsSessionRateLimitedRunError> =
            Optional.ofNullable(sessionRateLimited)

        /** The session create request was rejected with a non-retryable validation error. */
        fun sessionCreationRejected(): Optional<BetaManagedAgentsSessionCreationRejectedRunError> =
            Optional.ofNullable(sessionCreationRejected)

        /**
         * An unknown or unexpected error caused the run to fail. A fallback variant; clients that
         * do not recognize a new error type can match on message alone.
         */
        fun unknown(): Optional<BetaManagedAgentsUnknownRunError> = Optional.ofNullable(unknown)

        /**
         * The deployment configures resources, but its environment is self-hosted and cannot mount
         * them.
         */
        fun selfHostedResourcesUnsupported():
            Optional<BetaManagedAgentsSelfHostedResourcesUnsupportedRunError> =
            Optional.ofNullable(selfHostedResourcesUnsupported)

        /**
         * An MCP server host used by the deployment's agent is blocked by the environment's network
         * policy.
         */
        fun mcpEgressBlocked(): Optional<BetaManagedAgentsMcpEgressBlockedRunError> =
            Optional.ofNullable(mcpEgressBlocked)

        fun isEnvironmentArchived(): Boolean = environmentArchived != null

        fun isAgentArchived(): Boolean = agentArchived != null

        fun isEnvironmentNotFound(): Boolean = environmentNotFound != null

        fun isVaultNotFound(): Boolean = vaultNotFound != null

        fun isVaultArchived(): Boolean = vaultArchived != null

        fun isFileNotFound(): Boolean = fileNotFound != null

        fun isMemoryStoreArchived(): Boolean = memoryStoreArchived != null

        fun isSkillNotFound(): Boolean = skillNotFound != null

        fun isSessionResourceNotFound(): Boolean = sessionResourceNotFound != null

        fun isWorkspaceArchived(): Boolean = workspaceArchived != null

        fun isOrganizationDisabled(): Boolean = organizationDisabled != null

        fun isSessionRateLimited(): Boolean = sessionRateLimited != null

        fun isSessionCreationRejected(): Boolean = sessionCreationRejected != null

        fun isUnknown(): Boolean = unknown != null

        fun isSelfHostedResourcesUnsupported(): Boolean = selfHostedResourcesUnsupported != null

        fun isMcpEgressBlocked(): Boolean = mcpEgressBlocked != null

        /** The deployment's environment was archived. */
        fun asEnvironmentArchived(): BetaManagedAgentsEnvironmentArchivedRunError =
            environmentArchived.getOrThrow("environmentArchived")

        /** The deployment's agent was archived. */
        fun asAgentArchived(): BetaManagedAgentsAgentArchivedRunError =
            agentArchived.getOrThrow("agentArchived")

        /** The deployment's environment no longer exists. */
        fun asEnvironmentNotFound(): BetaManagedAgentsEnvironmentNotFoundRunError =
            environmentNotFound.getOrThrow("environmentNotFound")

        /** A vault referenced by the deployment no longer exists. */
        fun asVaultNotFound(): BetaManagedAgentsVaultNotFoundRunError =
            vaultNotFound.getOrThrow("vaultNotFound")

        /** A vault referenced by the deployment is archived. */
        fun asVaultArchived(): BetaManagedAgentsVaultArchivedRunError =
            vaultArchived.getOrThrow("vaultArchived")

        /** A file resource referenced by the deployment no longer exists. */
        fun asFileNotFound(): BetaManagedAgentsFileNotFoundRunError =
            fileNotFound.getOrThrow("fileNotFound")

        /** A memory store referenced by the deployment is archived. */
        fun asMemoryStoreArchived(): BetaManagedAgentsMemoryStoreArchivedRunError =
            memoryStoreArchived.getOrThrow("memoryStoreArchived")

        /** A skill referenced by the deployment's agent no longer exists. */
        fun asSkillNotFound(): BetaManagedAgentsSkillNotFoundRunError =
            skillNotFound.getOrThrow("skillNotFound")

        /** A referenced resource no longer exists and its kind was not reported. */
        fun asSessionResourceNotFound(): BetaManagedAgentsSessionResourceNotFoundRunError =
            sessionResourceNotFound.getOrThrow("sessionResourceNotFound")

        /** The deployment's workspace was archived. */
        fun asWorkspaceArchived(): BetaManagedAgentsWorkspaceArchivedRunError =
            workspaceArchived.getOrThrow("workspaceArchived")

        /** The deployment's organization is disabled. */
        fun asOrganizationDisabled(): BetaManagedAgentsOrganizationDisabledRunError =
            organizationDisabled.getOrThrow("organizationDisabled")

        /**
         * Session creation was rejected due to rate limiting. The schedule keeps firing; subsequent
         * runs may succeed.
         */
        fun asSessionRateLimited(): BetaManagedAgentsSessionRateLimitedRunError =
            sessionRateLimited.getOrThrow("sessionRateLimited")

        /** The session create request was rejected with a non-retryable validation error. */
        fun asSessionCreationRejected(): BetaManagedAgentsSessionCreationRejectedRunError =
            sessionCreationRejected.getOrThrow("sessionCreationRejected")

        /**
         * An unknown or unexpected error caused the run to fail. A fallback variant; clients that
         * do not recognize a new error type can match on message alone.
         */
        fun asUnknown(): BetaManagedAgentsUnknownRunError = unknown.getOrThrow("unknown")

        /**
         * The deployment configures resources, but its environment is self-hosted and cannot mount
         * them.
         */
        fun asSelfHostedResourcesUnsupported():
            BetaManagedAgentsSelfHostedResourcesUnsupportedRunError =
            selfHostedResourcesUnsupported.getOrThrow("selfHostedResourcesUnsupported")

        /**
         * An MCP server host used by the deployment's agent is blocked by the environment's network
         * policy.
         */
        fun asMcpEgressBlocked(): BetaManagedAgentsMcpEgressBlockedRunError =
            mcpEgressBlocked.getOrThrow("mcpEgressBlocked")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = error.accept(new Error.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitEnvironmentArchived(BetaManagedAgentsEnvironmentArchivedRunError environmentArchived) {
         *         return Optional.of(environmentArchived.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                environmentArchived != null -> visitor.visitEnvironmentArchived(environmentArchived)
                agentArchived != null -> visitor.visitAgentArchived(agentArchived)
                environmentNotFound != null -> visitor.visitEnvironmentNotFound(environmentNotFound)
                vaultNotFound != null -> visitor.visitVaultNotFound(vaultNotFound)
                vaultArchived != null -> visitor.visitVaultArchived(vaultArchived)
                fileNotFound != null -> visitor.visitFileNotFound(fileNotFound)
                memoryStoreArchived != null -> visitor.visitMemoryStoreArchived(memoryStoreArchived)
                skillNotFound != null -> visitor.visitSkillNotFound(skillNotFound)
                sessionResourceNotFound != null ->
                    visitor.visitSessionResourceNotFound(sessionResourceNotFound)
                workspaceArchived != null -> visitor.visitWorkspaceArchived(workspaceArchived)
                organizationDisabled != null ->
                    visitor.visitOrganizationDisabled(organizationDisabled)
                sessionRateLimited != null -> visitor.visitSessionRateLimited(sessionRateLimited)
                sessionCreationRejected != null ->
                    visitor.visitSessionCreationRejected(sessionCreationRejected)
                unknown != null -> visitor.visitUnknown(unknown)
                selfHostedResourcesUnsupported != null ->
                    visitor.visitSelfHostedResourcesUnsupported(selfHostedResourcesUnsupported)
                mcpEgressBlocked != null -> visitor.visitMcpEgressBlocked(mcpEgressBlocked)
                else -> visitor.unknown(_json)
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
        fun validate(): Error = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitEnvironmentArchived(
                        environmentArchived: BetaManagedAgentsEnvironmentArchivedRunError
                    ) {
                        environmentArchived.validate()
                    }

                    override fun visitAgentArchived(
                        agentArchived: BetaManagedAgentsAgentArchivedRunError
                    ) {
                        agentArchived.validate()
                    }

                    override fun visitEnvironmentNotFound(
                        environmentNotFound: BetaManagedAgentsEnvironmentNotFoundRunError
                    ) {
                        environmentNotFound.validate()
                    }

                    override fun visitVaultNotFound(
                        vaultNotFound: BetaManagedAgentsVaultNotFoundRunError
                    ) {
                        vaultNotFound.validate()
                    }

                    override fun visitVaultArchived(
                        vaultArchived: BetaManagedAgentsVaultArchivedRunError
                    ) {
                        vaultArchived.validate()
                    }

                    override fun visitFileNotFound(
                        fileNotFound: BetaManagedAgentsFileNotFoundRunError
                    ) {
                        fileNotFound.validate()
                    }

                    override fun visitMemoryStoreArchived(
                        memoryStoreArchived: BetaManagedAgentsMemoryStoreArchivedRunError
                    ) {
                        memoryStoreArchived.validate()
                    }

                    override fun visitSkillNotFound(
                        skillNotFound: BetaManagedAgentsSkillNotFoundRunError
                    ) {
                        skillNotFound.validate()
                    }

                    override fun visitSessionResourceNotFound(
                        sessionResourceNotFound: BetaManagedAgentsSessionResourceNotFoundRunError
                    ) {
                        sessionResourceNotFound.validate()
                    }

                    override fun visitWorkspaceArchived(
                        workspaceArchived: BetaManagedAgentsWorkspaceArchivedRunError
                    ) {
                        workspaceArchived.validate()
                    }

                    override fun visitOrganizationDisabled(
                        organizationDisabled: BetaManagedAgentsOrganizationDisabledRunError
                    ) {
                        organizationDisabled.validate()
                    }

                    override fun visitSessionRateLimited(
                        sessionRateLimited: BetaManagedAgentsSessionRateLimitedRunError
                    ) {
                        sessionRateLimited.validate()
                    }

                    override fun visitSessionCreationRejected(
                        sessionCreationRejected: BetaManagedAgentsSessionCreationRejectedRunError
                    ) {
                        sessionCreationRejected.validate()
                    }

                    override fun visitUnknown(unknown: BetaManagedAgentsUnknownRunError) {
                        unknown.validate()
                    }

                    override fun visitSelfHostedResourcesUnsupported(
                        selfHostedResourcesUnsupported:
                            BetaManagedAgentsSelfHostedResourcesUnsupportedRunError
                    ) {
                        selfHostedResourcesUnsupported.validate()
                    }

                    override fun visitMcpEgressBlocked(
                        mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedRunError
                    ) {
                        mcpEgressBlocked.validate()
                    }
                }
            )
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
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitEnvironmentArchived(
                        environmentArchived: BetaManagedAgentsEnvironmentArchivedRunError
                    ) = environmentArchived.validity()

                    override fun visitAgentArchived(
                        agentArchived: BetaManagedAgentsAgentArchivedRunError
                    ) = agentArchived.validity()

                    override fun visitEnvironmentNotFound(
                        environmentNotFound: BetaManagedAgentsEnvironmentNotFoundRunError
                    ) = environmentNotFound.validity()

                    override fun visitVaultNotFound(
                        vaultNotFound: BetaManagedAgentsVaultNotFoundRunError
                    ) = vaultNotFound.validity()

                    override fun visitVaultArchived(
                        vaultArchived: BetaManagedAgentsVaultArchivedRunError
                    ) = vaultArchived.validity()

                    override fun visitFileNotFound(
                        fileNotFound: BetaManagedAgentsFileNotFoundRunError
                    ) = fileNotFound.validity()

                    override fun visitMemoryStoreArchived(
                        memoryStoreArchived: BetaManagedAgentsMemoryStoreArchivedRunError
                    ) = memoryStoreArchived.validity()

                    override fun visitSkillNotFound(
                        skillNotFound: BetaManagedAgentsSkillNotFoundRunError
                    ) = skillNotFound.validity()

                    override fun visitSessionResourceNotFound(
                        sessionResourceNotFound: BetaManagedAgentsSessionResourceNotFoundRunError
                    ) = sessionResourceNotFound.validity()

                    override fun visitWorkspaceArchived(
                        workspaceArchived: BetaManagedAgentsWorkspaceArchivedRunError
                    ) = workspaceArchived.validity()

                    override fun visitOrganizationDisabled(
                        organizationDisabled: BetaManagedAgentsOrganizationDisabledRunError
                    ) = organizationDisabled.validity()

                    override fun visitSessionRateLimited(
                        sessionRateLimited: BetaManagedAgentsSessionRateLimitedRunError
                    ) = sessionRateLimited.validity()

                    override fun visitSessionCreationRejected(
                        sessionCreationRejected: BetaManagedAgentsSessionCreationRejectedRunError
                    ) = sessionCreationRejected.validity()

                    override fun visitUnknown(unknown: BetaManagedAgentsUnknownRunError) =
                        unknown.validity()

                    override fun visitSelfHostedResourcesUnsupported(
                        selfHostedResourcesUnsupported:
                            BetaManagedAgentsSelfHostedResourcesUnsupportedRunError
                    ) = selfHostedResourcesUnsupported.validity()

                    override fun visitMcpEgressBlocked(
                        mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedRunError
                    ) = mcpEgressBlocked.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Error &&
                environmentArchived == other.environmentArchived &&
                agentArchived == other.agentArchived &&
                environmentNotFound == other.environmentNotFound &&
                vaultNotFound == other.vaultNotFound &&
                vaultArchived == other.vaultArchived &&
                fileNotFound == other.fileNotFound &&
                memoryStoreArchived == other.memoryStoreArchived &&
                skillNotFound == other.skillNotFound &&
                sessionResourceNotFound == other.sessionResourceNotFound &&
                workspaceArchived == other.workspaceArchived &&
                organizationDisabled == other.organizationDisabled &&
                sessionRateLimited == other.sessionRateLimited &&
                sessionCreationRejected == other.sessionCreationRejected &&
                unknown == other.unknown &&
                selfHostedResourcesUnsupported == other.selfHostedResourcesUnsupported &&
                mcpEgressBlocked == other.mcpEgressBlocked
        }

        override fun hashCode(): Int =
            Objects.hash(
                environmentArchived,
                agentArchived,
                environmentNotFound,
                vaultNotFound,
                vaultArchived,
                fileNotFound,
                memoryStoreArchived,
                skillNotFound,
                sessionResourceNotFound,
                workspaceArchived,
                organizationDisabled,
                sessionRateLimited,
                sessionCreationRejected,
                unknown,
                selfHostedResourcesUnsupported,
                mcpEgressBlocked,
            )

        override fun toString(): String =
            when {
                environmentArchived != null -> "Error{environmentArchived=$environmentArchived}"
                agentArchived != null -> "Error{agentArchived=$agentArchived}"
                environmentNotFound != null -> "Error{environmentNotFound=$environmentNotFound}"
                vaultNotFound != null -> "Error{vaultNotFound=$vaultNotFound}"
                vaultArchived != null -> "Error{vaultArchived=$vaultArchived}"
                fileNotFound != null -> "Error{fileNotFound=$fileNotFound}"
                memoryStoreArchived != null -> "Error{memoryStoreArchived=$memoryStoreArchived}"
                skillNotFound != null -> "Error{skillNotFound=$skillNotFound}"
                sessionResourceNotFound != null ->
                    "Error{sessionResourceNotFound=$sessionResourceNotFound}"
                workspaceArchived != null -> "Error{workspaceArchived=$workspaceArchived}"
                organizationDisabled != null -> "Error{organizationDisabled=$organizationDisabled}"
                sessionRateLimited != null -> "Error{sessionRateLimited=$sessionRateLimited}"
                sessionCreationRejected != null ->
                    "Error{sessionCreationRejected=$sessionCreationRejected}"
                unknown != null -> "Error{unknown=$unknown}"
                selfHostedResourcesUnsupported != null ->
                    "Error{selfHostedResourcesUnsupported=$selfHostedResourcesUnsupported}"
                mcpEgressBlocked != null -> "Error{mcpEgressBlocked=$mcpEgressBlocked}"
                _json != null -> "Error{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Error")
            }

        companion object {

            /** The deployment's environment was archived. */
            @JvmStatic
            fun ofEnvironmentArchived(
                environmentArchived: BetaManagedAgentsEnvironmentArchivedRunError
            ) = Error(environmentArchived = environmentArchived)

            /** The deployment's agent was archived. */
            @JvmStatic
            fun ofAgentArchived(agentArchived: BetaManagedAgentsAgentArchivedRunError) =
                Error(agentArchived = agentArchived)

            /** The deployment's environment no longer exists. */
            @JvmStatic
            fun ofEnvironmentNotFound(
                environmentNotFound: BetaManagedAgentsEnvironmentNotFoundRunError
            ) = Error(environmentNotFound = environmentNotFound)

            /** A vault referenced by the deployment no longer exists. */
            @JvmStatic
            fun ofVaultNotFound(vaultNotFound: BetaManagedAgentsVaultNotFoundRunError) =
                Error(vaultNotFound = vaultNotFound)

            /** A vault referenced by the deployment is archived. */
            @JvmStatic
            fun ofVaultArchived(vaultArchived: BetaManagedAgentsVaultArchivedRunError) =
                Error(vaultArchived = vaultArchived)

            /** A file resource referenced by the deployment no longer exists. */
            @JvmStatic
            fun ofFileNotFound(fileNotFound: BetaManagedAgentsFileNotFoundRunError) =
                Error(fileNotFound = fileNotFound)

            /** A memory store referenced by the deployment is archived. */
            @JvmStatic
            fun ofMemoryStoreArchived(
                memoryStoreArchived: BetaManagedAgentsMemoryStoreArchivedRunError
            ) = Error(memoryStoreArchived = memoryStoreArchived)

            /** A skill referenced by the deployment's agent no longer exists. */
            @JvmStatic
            fun ofSkillNotFound(skillNotFound: BetaManagedAgentsSkillNotFoundRunError) =
                Error(skillNotFound = skillNotFound)

            /** A referenced resource no longer exists and its kind was not reported. */
            @JvmStatic
            fun ofSessionResourceNotFound(
                sessionResourceNotFound: BetaManagedAgentsSessionResourceNotFoundRunError
            ) = Error(sessionResourceNotFound = sessionResourceNotFound)

            /** The deployment's workspace was archived. */
            @JvmStatic
            fun ofWorkspaceArchived(workspaceArchived: BetaManagedAgentsWorkspaceArchivedRunError) =
                Error(workspaceArchived = workspaceArchived)

            /** The deployment's organization is disabled. */
            @JvmStatic
            fun ofOrganizationDisabled(
                organizationDisabled: BetaManagedAgentsOrganizationDisabledRunError
            ) = Error(organizationDisabled = organizationDisabled)

            /**
             * Session creation was rejected due to rate limiting. The schedule keeps firing;
             * subsequent runs may succeed.
             */
            @JvmStatic
            fun ofSessionRateLimited(
                sessionRateLimited: BetaManagedAgentsSessionRateLimitedRunError
            ) = Error(sessionRateLimited = sessionRateLimited)

            /** The session create request was rejected with a non-retryable validation error. */
            @JvmStatic
            fun ofSessionCreationRejected(
                sessionCreationRejected: BetaManagedAgentsSessionCreationRejectedRunError
            ) = Error(sessionCreationRejected = sessionCreationRejected)

            /**
             * An unknown or unexpected error caused the run to fail. A fallback variant; clients
             * that do not recognize a new error type can match on message alone.
             */
            @JvmStatic
            fun ofUnknown(unknown: BetaManagedAgentsUnknownRunError) = Error(unknown = unknown)

            /**
             * The deployment configures resources, but its environment is self-hosted and cannot
             * mount them.
             */
            @JvmStatic
            fun ofSelfHostedResourcesUnsupported(
                selfHostedResourcesUnsupported:
                    BetaManagedAgentsSelfHostedResourcesUnsupportedRunError
            ) = Error(selfHostedResourcesUnsupported = selfHostedResourcesUnsupported)

            /**
             * An MCP server host used by the deployment's agent is blocked by the environment's
             * network policy.
             */
            @JvmStatic
            fun ofMcpEgressBlocked(mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedRunError) =
                Error(mcpEgressBlocked = mcpEgressBlocked)
        }

        /** An interface that defines how to map each variant of [Error] to a value of type [T]. */
        interface Visitor<out T> {

            /** The deployment's environment was archived. */
            fun visitEnvironmentArchived(
                environmentArchived: BetaManagedAgentsEnvironmentArchivedRunError
            ): T

            /** The deployment's agent was archived. */
            fun visitAgentArchived(agentArchived: BetaManagedAgentsAgentArchivedRunError): T

            /** The deployment's environment no longer exists. */
            fun visitEnvironmentNotFound(
                environmentNotFound: BetaManagedAgentsEnvironmentNotFoundRunError
            ): T

            /** A vault referenced by the deployment no longer exists. */
            fun visitVaultNotFound(vaultNotFound: BetaManagedAgentsVaultNotFoundRunError): T

            /** A vault referenced by the deployment is archived. */
            fun visitVaultArchived(vaultArchived: BetaManagedAgentsVaultArchivedRunError): T

            /** A file resource referenced by the deployment no longer exists. */
            fun visitFileNotFound(fileNotFound: BetaManagedAgentsFileNotFoundRunError): T

            /** A memory store referenced by the deployment is archived. */
            fun visitMemoryStoreArchived(
                memoryStoreArchived: BetaManagedAgentsMemoryStoreArchivedRunError
            ): T

            /** A skill referenced by the deployment's agent no longer exists. */
            fun visitSkillNotFound(skillNotFound: BetaManagedAgentsSkillNotFoundRunError): T

            /** A referenced resource no longer exists and its kind was not reported. */
            fun visitSessionResourceNotFound(
                sessionResourceNotFound: BetaManagedAgentsSessionResourceNotFoundRunError
            ): T

            /** The deployment's workspace was archived. */
            fun visitWorkspaceArchived(
                workspaceArchived: BetaManagedAgentsWorkspaceArchivedRunError
            ): T

            /** The deployment's organization is disabled. */
            fun visitOrganizationDisabled(
                organizationDisabled: BetaManagedAgentsOrganizationDisabledRunError
            ): T

            /**
             * Session creation was rejected due to rate limiting. The schedule keeps firing;
             * subsequent runs may succeed.
             */
            fun visitSessionRateLimited(
                sessionRateLimited: BetaManagedAgentsSessionRateLimitedRunError
            ): T

            /** The session create request was rejected with a non-retryable validation error. */
            fun visitSessionCreationRejected(
                sessionCreationRejected: BetaManagedAgentsSessionCreationRejectedRunError
            ): T

            /**
             * An unknown or unexpected error caused the run to fail. A fallback variant; clients
             * that do not recognize a new error type can match on message alone.
             */
            fun visitUnknown(unknown: BetaManagedAgentsUnknownRunError): T

            /**
             * The deployment configures resources, but its environment is self-hosted and cannot
             * mount them.
             */
            fun visitSelfHostedResourcesUnsupported(
                selfHostedResourcesUnsupported:
                    BetaManagedAgentsSelfHostedResourcesUnsupportedRunError
            ): T

            /**
             * An MCP server host used by the deployment's agent is blocked by the environment's
             * network policy.
             */
            fun visitMcpEgressBlocked(
                mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedRunError
            ): T

            /**
             * Maps an unknown variant of [Error] to a value of type [T].
             *
             * An instance of [Error] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Error: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Error>(Error::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Error {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "environment_archived_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsEnvironmentArchivedRunError>(),
                            )
                            ?.let { Error(environmentArchived = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "agent_archived_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsAgentArchivedRunError>(),
                            )
                            ?.let { Error(agentArchived = it, _json = json) } ?: Error(_json = json)
                    }
                    "environment_not_found_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsEnvironmentNotFoundRunError>(),
                            )
                            ?.let { Error(environmentNotFound = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "vault_not_found_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsVaultNotFoundRunError>(),
                            )
                            ?.let { Error(vaultNotFound = it, _json = json) } ?: Error(_json = json)
                    }
                    "vault_archived_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsVaultArchivedRunError>(),
                            )
                            ?.let { Error(vaultArchived = it, _json = json) } ?: Error(_json = json)
                    }
                    "file_not_found_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsFileNotFoundRunError>(),
                            )
                            ?.let { Error(fileNotFound = it, _json = json) } ?: Error(_json = json)
                    }
                    "memory_store_archived_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsMemoryStoreArchivedRunError>(),
                            )
                            ?.let { Error(memoryStoreArchived = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "skill_not_found_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsSkillNotFoundRunError>(),
                            )
                            ?.let { Error(skillNotFound = it, _json = json) } ?: Error(_json = json)
                    }
                    "session_resource_not_found_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsSessionResourceNotFoundRunError>(),
                            )
                            ?.let { Error(sessionResourceNotFound = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "workspace_archived_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsWorkspaceArchivedRunError>(),
                            )
                            ?.let { Error(workspaceArchived = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "organization_disabled_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsOrganizationDisabledRunError>(),
                            )
                            ?.let { Error(organizationDisabled = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "session_rate_limited_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsSessionRateLimitedRunError>(),
                            )
                            ?.let { Error(sessionRateLimited = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "session_creation_rejected_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsSessionCreationRejectedRunError>(),
                            )
                            ?.let { Error(sessionCreationRejected = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "unknown_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsUnknownRunError>(),
                            )
                            ?.let { Error(unknown = it, _json = json) } ?: Error(_json = json)
                    }
                    "self_hosted_resources_unsupported_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<
                                    BetaManagedAgentsSelfHostedResourcesUnsupportedRunError
                                >(),
                            )
                            ?.let { Error(selfHostedResourcesUnsupported = it, _json = json) }
                            ?: Error(_json = json)
                    }
                    "mcp_egress_blocked_error" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsMcpEgressBlockedRunError>(),
                            )
                            ?.let { Error(mcpEgressBlocked = it, _json = json) }
                            ?: Error(_json = json)
                    }
                }

                return Error(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Error>(Error::class) {

            override fun serialize(
                value: Error,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.environmentArchived != null ->
                        generator.writeObject(value.environmentArchived)
                    value.agentArchived != null -> generator.writeObject(value.agentArchived)
                    value.environmentNotFound != null ->
                        generator.writeObject(value.environmentNotFound)
                    value.vaultNotFound != null -> generator.writeObject(value.vaultNotFound)
                    value.vaultArchived != null -> generator.writeObject(value.vaultArchived)
                    value.fileNotFound != null -> generator.writeObject(value.fileNotFound)
                    value.memoryStoreArchived != null ->
                        generator.writeObject(value.memoryStoreArchived)
                    value.skillNotFound != null -> generator.writeObject(value.skillNotFound)
                    value.sessionResourceNotFound != null ->
                        generator.writeObject(value.sessionResourceNotFound)
                    value.workspaceArchived != null ->
                        generator.writeObject(value.workspaceArchived)
                    value.organizationDisabled != null ->
                        generator.writeObject(value.organizationDisabled)
                    value.sessionRateLimited != null ->
                        generator.writeObject(value.sessionRateLimited)
                    value.sessionCreationRejected != null ->
                        generator.writeObject(value.sessionCreationRejected)
                    value.unknown != null -> generator.writeObject(value.unknown)
                    value.selfHostedResourcesUnsupported != null ->
                        generator.writeObject(value.selfHostedResourcesUnsupported)
                    value.mcpEgressBlocked != null -> generator.writeObject(value.mcpEgressBlocked)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Error")
                }
            }
        }
    }

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

            @JvmField val DEPLOYMENT_RUN = of("deployment_run")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            DEPLOYMENT_RUN
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
            DEPLOYMENT_RUN,
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
                DEPLOYMENT_RUN -> Value.DEPLOYMENT_RUN
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
                DEPLOYMENT_RUN -> Known.DEPLOYMENT_RUN
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

        return other is BetaManagedAgentsDeploymentRun &&
            id == other.id &&
            agent == other.agent &&
            createdAt == other.createdAt &&
            deploymentId == other.deploymentId &&
            error == other.error &&
            sessionId == other.sessionId &&
            triggerContext == other.triggerContext &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            agent,
            createdAt,
            deploymentId,
            error,
            sessionId,
            triggerContext,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsDeploymentRun{id=$id, agent=$agent, createdAt=$createdAt, deploymentId=$deploymentId, error=$error, sessionId=$sessionId, triggerContext=$triggerContext, type=$type, additionalProperties=$additionalProperties}"
}
