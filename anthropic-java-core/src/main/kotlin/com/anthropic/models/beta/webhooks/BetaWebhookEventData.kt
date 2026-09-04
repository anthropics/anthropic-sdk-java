// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@JsonDeserialize(using = BetaWebhookEventData.Deserializer::class)
@JsonSerialize(using = BetaWebhookEventData.Serializer::class)
class BetaWebhookEventData
private constructor(
    private val sessionCreated: BetaWebhookSessionCreatedEventData? = null,
    private val sessionPending: BetaWebhookSessionPendingEventData? = null,
    private val sessionRunning: BetaWebhookSessionRunningEventData? = null,
    private val sessionIdled: BetaWebhookSessionIdledEventData? = null,
    private val sessionRequiresAction: BetaWebhookSessionRequiresActionEventData? = null,
    private val sessionArchived: BetaWebhookSessionArchivedEventData? = null,
    private val sessionDeleted: BetaWebhookSessionDeletedEventData? = null,
    private val sessionStatusRescheduled: BetaWebhookSessionStatusRescheduledEventData? = null,
    private val sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData? = null,
    private val sessionStatusIdled: BetaWebhookSessionStatusIdledEventData? = null,
    private val sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData? = null,
    private val sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData? = null,
    private val sessionThreadIdled: BetaWebhookSessionThreadIdledEventData? = null,
    private val sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData? = null,
    private val sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData? =
        null,
    private val vaultCreated: BetaWebhookVaultCreatedEventData? = null,
    private val vaultArchived: BetaWebhookVaultArchivedEventData? = null,
    private val vaultDeleted: BetaWebhookVaultDeletedEventData? = null,
    private val vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData? = null,
    private val vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData? = null,
    private val vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData? = null,
    private val vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData? =
        null,
    private val sessionUpdated: BetaWebhookSessionUpdatedEventData? = null,
    private val agentCreated: BetaWebhookAgentCreatedEventData? = null,
    private val agentArchived: BetaWebhookAgentArchivedEventData? = null,
    private val agentDeleted: BetaWebhookAgentDeletedEventData? = null,
    private val deploymentPaused: BetaWebhookDeploymentPausedEventData? = null,
    private val deploymentRunFailed: BetaWebhookDeploymentRunFailedEventData? = null,
    private val deploymentCreated: BetaWebhookDeploymentCreatedEventData? = null,
    private val deploymentUpdated: BetaWebhookDeploymentUpdatedEventData? = null,
    private val deploymentUnpaused: BetaWebhookDeploymentUnpausedEventData? = null,
    private val agentUpdated: BetaWebhookAgentUpdatedEventData? = null,
    private val deploymentArchived: BetaWebhookDeploymentArchivedEventData? = null,
    private val deploymentRunStarted: BetaWebhookDeploymentRunStartedEventData? = null,
    private val deploymentDeleted: BetaWebhookDeploymentDeletedEventData? = null,
    private val deploymentRunSucceeded: BetaWebhookDeploymentRunSucceededEventData? = null,
    private val environmentCreated: BetaWebhookEnvironmentCreatedEventData? = null,
    private val environmentUpdated: BetaWebhookEnvironmentUpdatedEventData? = null,
    private val environmentArchived: BetaWebhookEnvironmentArchivedEventData? = null,
    private val environmentDeleted: BetaWebhookEnvironmentDeletedEventData? = null,
    private val memoryStoreCreated: BetaWebhookMemoryStoreCreatedEventData? = null,
    private val memoryStoreArchived: BetaWebhookMemoryStoreArchivedEventData? = null,
    private val memoryStoreDeleted: BetaWebhookMemoryStoreDeletedEventData? = null,
    private val sessionBudgetReached: BetaWebhookSessionBudgetReachedEventData? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitSessionCreated(
                    sessionCreated: BetaWebhookSessionCreatedEventData
                ): Type = Type.SESSION_CREATED

                override fun visitSessionPending(
                    sessionPending: BetaWebhookSessionPendingEventData
                ): Type = Type.SESSION_PENDING

                override fun visitSessionRunning(
                    sessionRunning: BetaWebhookSessionRunningEventData
                ): Type = Type.SESSION_RUNNING

                override fun visitSessionIdled(
                    sessionIdled: BetaWebhookSessionIdledEventData
                ): Type = Type.SESSION_IDLED

                override fun visitSessionRequiresAction(
                    sessionRequiresAction: BetaWebhookSessionRequiresActionEventData
                ): Type = Type.SESSION_REQUIRES_ACTION

                override fun visitSessionArchived(
                    sessionArchived: BetaWebhookSessionArchivedEventData
                ): Type = Type.SESSION_ARCHIVED

                override fun visitSessionDeleted(
                    sessionDeleted: BetaWebhookSessionDeletedEventData
                ): Type = Type.SESSION_DELETED

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaWebhookSessionStatusRescheduledEventData
                ): Type = Type.SESSION_STATUS_RESCHEDULED

                override fun visitSessionStatusRunStarted(
                    sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData
                ): Type = Type.SESSION_STATUS_RUN_STARTED

                override fun visitSessionStatusIdled(
                    sessionStatusIdled: BetaWebhookSessionStatusIdledEventData
                ): Type = Type.SESSION_STATUS_IDLED

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData
                ): Type = Type.SESSION_STATUS_TERMINATED

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData
                ): Type = Type.SESSION_THREAD_CREATED

                override fun visitSessionThreadIdled(
                    sessionThreadIdled: BetaWebhookSessionThreadIdledEventData
                ): Type = Type.SESSION_THREAD_IDLED

                override fun visitSessionThreadTerminated(
                    sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData
                ): Type = Type.SESSION_THREAD_TERMINATED

                override fun visitSessionOutcomeEvaluationEnded(
                    sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData
                ): Type = Type.SESSION_OUTCOME_EVALUATION_ENDED

                override fun visitVaultCreated(
                    vaultCreated: BetaWebhookVaultCreatedEventData
                ): Type = Type.VAULT_CREATED

                override fun visitVaultArchived(
                    vaultArchived: BetaWebhookVaultArchivedEventData
                ): Type = Type.VAULT_ARCHIVED

                override fun visitVaultDeleted(
                    vaultDeleted: BetaWebhookVaultDeletedEventData
                ): Type = Type.VAULT_DELETED

                override fun visitVaultCredentialCreated(
                    vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData
                ): Type = Type.VAULT_CREDENTIAL_CREATED

                override fun visitVaultCredentialArchived(
                    vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData
                ): Type = Type.VAULT_CREDENTIAL_ARCHIVED

                override fun visitVaultCredentialDeleted(
                    vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData
                ): Type = Type.VAULT_CREDENTIAL_DELETED

                override fun visitVaultCredentialRefreshFailed(
                    vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData
                ): Type = Type.VAULT_CREDENTIAL_REFRESH_FAILED

                override fun visitSessionUpdated(
                    sessionUpdated: BetaWebhookSessionUpdatedEventData
                ): Type = Type.SESSION_UPDATED

                override fun visitAgentCreated(
                    agentCreated: BetaWebhookAgentCreatedEventData
                ): Type = Type.AGENT_CREATED

                override fun visitAgentArchived(
                    agentArchived: BetaWebhookAgentArchivedEventData
                ): Type = Type.AGENT_ARCHIVED

                override fun visitAgentDeleted(
                    agentDeleted: BetaWebhookAgentDeletedEventData
                ): Type = Type.AGENT_DELETED

                override fun visitDeploymentPaused(
                    deploymentPaused: BetaWebhookDeploymentPausedEventData
                ): Type = Type.DEPLOYMENT_PAUSED

                override fun visitDeploymentRunFailed(
                    deploymentRunFailed: BetaWebhookDeploymentRunFailedEventData
                ): Type = Type.DEPLOYMENT_RUN_FAILED

                override fun visitDeploymentCreated(
                    deploymentCreated: BetaWebhookDeploymentCreatedEventData
                ): Type = Type.DEPLOYMENT_CREATED

                override fun visitDeploymentUpdated(
                    deploymentUpdated: BetaWebhookDeploymentUpdatedEventData
                ): Type = Type.DEPLOYMENT_UPDATED

                override fun visitDeploymentUnpaused(
                    deploymentUnpaused: BetaWebhookDeploymentUnpausedEventData
                ): Type = Type.DEPLOYMENT_UNPAUSED

                override fun visitAgentUpdated(
                    agentUpdated: BetaWebhookAgentUpdatedEventData
                ): Type = Type.AGENT_UPDATED

                override fun visitDeploymentArchived(
                    deploymentArchived: BetaWebhookDeploymentArchivedEventData
                ): Type = Type.DEPLOYMENT_ARCHIVED

                override fun visitDeploymentRunStarted(
                    deploymentRunStarted: BetaWebhookDeploymentRunStartedEventData
                ): Type = Type.DEPLOYMENT_RUN_STARTED

                override fun visitDeploymentDeleted(
                    deploymentDeleted: BetaWebhookDeploymentDeletedEventData
                ): Type = Type.DEPLOYMENT_DELETED

                override fun visitDeploymentRunSucceeded(
                    deploymentRunSucceeded: BetaWebhookDeploymentRunSucceededEventData
                ): Type = Type.DEPLOYMENT_RUN_SUCCEEDED

                override fun visitEnvironmentCreated(
                    environmentCreated: BetaWebhookEnvironmentCreatedEventData
                ): Type = Type.ENVIRONMENT_CREATED

                override fun visitEnvironmentUpdated(
                    environmentUpdated: BetaWebhookEnvironmentUpdatedEventData
                ): Type = Type.ENVIRONMENT_UPDATED

                override fun visitEnvironmentArchived(
                    environmentArchived: BetaWebhookEnvironmentArchivedEventData
                ): Type = Type.ENVIRONMENT_ARCHIVED

                override fun visitEnvironmentDeleted(
                    environmentDeleted: BetaWebhookEnvironmentDeletedEventData
                ): Type = Type.ENVIRONMENT_DELETED

                override fun visitMemoryStoreCreated(
                    memoryStoreCreated: BetaWebhookMemoryStoreCreatedEventData
                ): Type = Type.MEMORY_STORE_CREATED

                override fun visitMemoryStoreArchived(
                    memoryStoreArchived: BetaWebhookMemoryStoreArchivedEventData
                ): Type = Type.MEMORY_STORE_ARCHIVED

                override fun visitMemoryStoreDeleted(
                    memoryStoreDeleted: BetaWebhookMemoryStoreDeletedEventData
                ): Type = Type.MEMORY_STORE_DELETED

                override fun visitSessionBudgetReached(
                    sessionBudgetReached: BetaWebhookSessionBudgetReachedEventData
                ): Type = Type.SESSION_BUDGET_REACHED

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun id(): String =
        accept(
            object : Visitor<String> {
                override fun visitSessionCreated(
                    sessionCreated: BetaWebhookSessionCreatedEventData
                ): String = sessionCreated.id()

                override fun visitSessionPending(
                    sessionPending: BetaWebhookSessionPendingEventData
                ): String = sessionPending.id()

                override fun visitSessionRunning(
                    sessionRunning: BetaWebhookSessionRunningEventData
                ): String = sessionRunning.id()

                override fun visitSessionIdled(
                    sessionIdled: BetaWebhookSessionIdledEventData
                ): String = sessionIdled.id()

                override fun visitSessionRequiresAction(
                    sessionRequiresAction: BetaWebhookSessionRequiresActionEventData
                ): String = sessionRequiresAction.id()

                override fun visitSessionArchived(
                    sessionArchived: BetaWebhookSessionArchivedEventData
                ): String = sessionArchived.id()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaWebhookSessionDeletedEventData
                ): String = sessionDeleted.id()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaWebhookSessionStatusRescheduledEventData
                ): String = sessionStatusRescheduled.id()

                override fun visitSessionStatusRunStarted(
                    sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData
                ): String = sessionStatusRunStarted.id()

                override fun visitSessionStatusIdled(
                    sessionStatusIdled: BetaWebhookSessionStatusIdledEventData
                ): String = sessionStatusIdled.id()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData
                ): String = sessionStatusTerminated.id()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData
                ): String = sessionThreadCreated.id()

                override fun visitSessionThreadIdled(
                    sessionThreadIdled: BetaWebhookSessionThreadIdledEventData
                ): String = sessionThreadIdled.id()

                override fun visitSessionThreadTerminated(
                    sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData
                ): String = sessionThreadTerminated.id()

                override fun visitSessionOutcomeEvaluationEnded(
                    sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData
                ): String = sessionOutcomeEvaluationEnded.id()

                override fun visitVaultCreated(
                    vaultCreated: BetaWebhookVaultCreatedEventData
                ): String = vaultCreated.id()

                override fun visitVaultArchived(
                    vaultArchived: BetaWebhookVaultArchivedEventData
                ): String = vaultArchived.id()

                override fun visitVaultDeleted(
                    vaultDeleted: BetaWebhookVaultDeletedEventData
                ): String = vaultDeleted.id()

                override fun visitVaultCredentialCreated(
                    vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData
                ): String = vaultCredentialCreated.id()

                override fun visitVaultCredentialArchived(
                    vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData
                ): String = vaultCredentialArchived.id()

                override fun visitVaultCredentialDeleted(
                    vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData
                ): String = vaultCredentialDeleted.id()

                override fun visitVaultCredentialRefreshFailed(
                    vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData
                ): String = vaultCredentialRefreshFailed.id()

                override fun visitSessionUpdated(
                    sessionUpdated: BetaWebhookSessionUpdatedEventData
                ): String = sessionUpdated.id()

                override fun visitAgentCreated(
                    agentCreated: BetaWebhookAgentCreatedEventData
                ): String = agentCreated.id()

                override fun visitAgentArchived(
                    agentArchived: BetaWebhookAgentArchivedEventData
                ): String = agentArchived.id()

                override fun visitAgentDeleted(
                    agentDeleted: BetaWebhookAgentDeletedEventData
                ): String = agentDeleted.id()

                override fun visitDeploymentPaused(
                    deploymentPaused: BetaWebhookDeploymentPausedEventData
                ): String = deploymentPaused.id()

                override fun visitDeploymentRunFailed(
                    deploymentRunFailed: BetaWebhookDeploymentRunFailedEventData
                ): String = deploymentRunFailed.id()

                override fun visitDeploymentCreated(
                    deploymentCreated: BetaWebhookDeploymentCreatedEventData
                ): String = deploymentCreated.id()

                override fun visitDeploymentUpdated(
                    deploymentUpdated: BetaWebhookDeploymentUpdatedEventData
                ): String = deploymentUpdated.id()

                override fun visitDeploymentUnpaused(
                    deploymentUnpaused: BetaWebhookDeploymentUnpausedEventData
                ): String = deploymentUnpaused.id()

                override fun visitAgentUpdated(
                    agentUpdated: BetaWebhookAgentUpdatedEventData
                ): String = agentUpdated.id()

                override fun visitDeploymentArchived(
                    deploymentArchived: BetaWebhookDeploymentArchivedEventData
                ): String = deploymentArchived.id()

                override fun visitDeploymentRunStarted(
                    deploymentRunStarted: BetaWebhookDeploymentRunStartedEventData
                ): String = deploymentRunStarted.id()

                override fun visitDeploymentDeleted(
                    deploymentDeleted: BetaWebhookDeploymentDeletedEventData
                ): String = deploymentDeleted.id()

                override fun visitDeploymentRunSucceeded(
                    deploymentRunSucceeded: BetaWebhookDeploymentRunSucceededEventData
                ): String = deploymentRunSucceeded.id()

                override fun visitEnvironmentCreated(
                    environmentCreated: BetaWebhookEnvironmentCreatedEventData
                ): String = environmentCreated.id()

                override fun visitEnvironmentUpdated(
                    environmentUpdated: BetaWebhookEnvironmentUpdatedEventData
                ): String = environmentUpdated.id()

                override fun visitEnvironmentArchived(
                    environmentArchived: BetaWebhookEnvironmentArchivedEventData
                ): String = environmentArchived.id()

                override fun visitEnvironmentDeleted(
                    environmentDeleted: BetaWebhookEnvironmentDeletedEventData
                ): String = environmentDeleted.id()

                override fun visitMemoryStoreCreated(
                    memoryStoreCreated: BetaWebhookMemoryStoreCreatedEventData
                ): String = memoryStoreCreated.id()

                override fun visitMemoryStoreArchived(
                    memoryStoreArchived: BetaWebhookMemoryStoreArchivedEventData
                ): String = memoryStoreArchived.id()

                override fun visitMemoryStoreDeleted(
                    memoryStoreDeleted: BetaWebhookMemoryStoreDeletedEventData
                ): String = memoryStoreDeleted.id()

                override fun visitSessionBudgetReached(
                    sessionBudgetReached: BetaWebhookSessionBudgetReachedEventData
                ): String = sessionBudgetReached.id()

                override fun unknown(json: JsonValue?): String =
                    json.getProperty<String>("id").getRequired("id")
            }
        )

    fun organizationId(): String =
        accept(
            object : Visitor<String> {
                override fun visitSessionCreated(
                    sessionCreated: BetaWebhookSessionCreatedEventData
                ): String = sessionCreated.organizationId()

                override fun visitSessionPending(
                    sessionPending: BetaWebhookSessionPendingEventData
                ): String = sessionPending.organizationId()

                override fun visitSessionRunning(
                    sessionRunning: BetaWebhookSessionRunningEventData
                ): String = sessionRunning.organizationId()

                override fun visitSessionIdled(
                    sessionIdled: BetaWebhookSessionIdledEventData
                ): String = sessionIdled.organizationId()

                override fun visitSessionRequiresAction(
                    sessionRequiresAction: BetaWebhookSessionRequiresActionEventData
                ): String = sessionRequiresAction.organizationId()

                override fun visitSessionArchived(
                    sessionArchived: BetaWebhookSessionArchivedEventData
                ): String = sessionArchived.organizationId()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaWebhookSessionDeletedEventData
                ): String = sessionDeleted.organizationId()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaWebhookSessionStatusRescheduledEventData
                ): String = sessionStatusRescheduled.organizationId()

                override fun visitSessionStatusRunStarted(
                    sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData
                ): String = sessionStatusRunStarted.organizationId()

                override fun visitSessionStatusIdled(
                    sessionStatusIdled: BetaWebhookSessionStatusIdledEventData
                ): String = sessionStatusIdled.organizationId()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData
                ): String = sessionStatusTerminated.organizationId()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData
                ): String = sessionThreadCreated.organizationId()

                override fun visitSessionThreadIdled(
                    sessionThreadIdled: BetaWebhookSessionThreadIdledEventData
                ): String = sessionThreadIdled.organizationId()

                override fun visitSessionThreadTerminated(
                    sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData
                ): String = sessionThreadTerminated.organizationId()

                override fun visitSessionOutcomeEvaluationEnded(
                    sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData
                ): String = sessionOutcomeEvaluationEnded.organizationId()

                override fun visitVaultCreated(
                    vaultCreated: BetaWebhookVaultCreatedEventData
                ): String = vaultCreated.organizationId()

                override fun visitVaultArchived(
                    vaultArchived: BetaWebhookVaultArchivedEventData
                ): String = vaultArchived.organizationId()

                override fun visitVaultDeleted(
                    vaultDeleted: BetaWebhookVaultDeletedEventData
                ): String = vaultDeleted.organizationId()

                override fun visitVaultCredentialCreated(
                    vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData
                ): String = vaultCredentialCreated.organizationId()

                override fun visitVaultCredentialArchived(
                    vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData
                ): String = vaultCredentialArchived.organizationId()

                override fun visitVaultCredentialDeleted(
                    vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData
                ): String = vaultCredentialDeleted.organizationId()

                override fun visitVaultCredentialRefreshFailed(
                    vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData
                ): String = vaultCredentialRefreshFailed.organizationId()

                override fun visitSessionUpdated(
                    sessionUpdated: BetaWebhookSessionUpdatedEventData
                ): String = sessionUpdated.organizationId()

                override fun visitAgentCreated(
                    agentCreated: BetaWebhookAgentCreatedEventData
                ): String = agentCreated.organizationId()

                override fun visitAgentArchived(
                    agentArchived: BetaWebhookAgentArchivedEventData
                ): String = agentArchived.organizationId()

                override fun visitAgentDeleted(
                    agentDeleted: BetaWebhookAgentDeletedEventData
                ): String = agentDeleted.organizationId()

                override fun visitDeploymentPaused(
                    deploymentPaused: BetaWebhookDeploymentPausedEventData
                ): String = deploymentPaused.organizationId()

                override fun visitDeploymentRunFailed(
                    deploymentRunFailed: BetaWebhookDeploymentRunFailedEventData
                ): String = deploymentRunFailed.organizationId()

                override fun visitDeploymentCreated(
                    deploymentCreated: BetaWebhookDeploymentCreatedEventData
                ): String = deploymentCreated.organizationId()

                override fun visitDeploymentUpdated(
                    deploymentUpdated: BetaWebhookDeploymentUpdatedEventData
                ): String = deploymentUpdated.organizationId()

                override fun visitDeploymentUnpaused(
                    deploymentUnpaused: BetaWebhookDeploymentUnpausedEventData
                ): String = deploymentUnpaused.organizationId()

                override fun visitAgentUpdated(
                    agentUpdated: BetaWebhookAgentUpdatedEventData
                ): String = agentUpdated.organizationId()

                override fun visitDeploymentArchived(
                    deploymentArchived: BetaWebhookDeploymentArchivedEventData
                ): String = deploymentArchived.organizationId()

                override fun visitDeploymentRunStarted(
                    deploymentRunStarted: BetaWebhookDeploymentRunStartedEventData
                ): String = deploymentRunStarted.organizationId()

                override fun visitDeploymentDeleted(
                    deploymentDeleted: BetaWebhookDeploymentDeletedEventData
                ): String = deploymentDeleted.organizationId()

                override fun visitDeploymentRunSucceeded(
                    deploymentRunSucceeded: BetaWebhookDeploymentRunSucceededEventData
                ): String = deploymentRunSucceeded.organizationId()

                override fun visitEnvironmentCreated(
                    environmentCreated: BetaWebhookEnvironmentCreatedEventData
                ): String = environmentCreated.organizationId()

                override fun visitEnvironmentUpdated(
                    environmentUpdated: BetaWebhookEnvironmentUpdatedEventData
                ): String = environmentUpdated.organizationId()

                override fun visitEnvironmentArchived(
                    environmentArchived: BetaWebhookEnvironmentArchivedEventData
                ): String = environmentArchived.organizationId()

                override fun visitEnvironmentDeleted(
                    environmentDeleted: BetaWebhookEnvironmentDeletedEventData
                ): String = environmentDeleted.organizationId()

                override fun visitMemoryStoreCreated(
                    memoryStoreCreated: BetaWebhookMemoryStoreCreatedEventData
                ): String = memoryStoreCreated.organizationId()

                override fun visitMemoryStoreArchived(
                    memoryStoreArchived: BetaWebhookMemoryStoreArchivedEventData
                ): String = memoryStoreArchived.organizationId()

                override fun visitMemoryStoreDeleted(
                    memoryStoreDeleted: BetaWebhookMemoryStoreDeletedEventData
                ): String = memoryStoreDeleted.organizationId()

                override fun visitSessionBudgetReached(
                    sessionBudgetReached: BetaWebhookSessionBudgetReachedEventData
                ): String = sessionBudgetReached.organizationId()

                override fun unknown(json: JsonValue?): String =
                    json.getProperty<String>("organization_id").getRequired("organization_id")
            }
        )

    fun workspaceId(): String =
        accept(
            object : Visitor<String> {
                override fun visitSessionCreated(
                    sessionCreated: BetaWebhookSessionCreatedEventData
                ): String = sessionCreated.workspaceId()

                override fun visitSessionPending(
                    sessionPending: BetaWebhookSessionPendingEventData
                ): String = sessionPending.workspaceId()

                override fun visitSessionRunning(
                    sessionRunning: BetaWebhookSessionRunningEventData
                ): String = sessionRunning.workspaceId()

                override fun visitSessionIdled(
                    sessionIdled: BetaWebhookSessionIdledEventData
                ): String = sessionIdled.workspaceId()

                override fun visitSessionRequiresAction(
                    sessionRequiresAction: BetaWebhookSessionRequiresActionEventData
                ): String = sessionRequiresAction.workspaceId()

                override fun visitSessionArchived(
                    sessionArchived: BetaWebhookSessionArchivedEventData
                ): String = sessionArchived.workspaceId()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaWebhookSessionDeletedEventData
                ): String = sessionDeleted.workspaceId()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaWebhookSessionStatusRescheduledEventData
                ): String = sessionStatusRescheduled.workspaceId()

                override fun visitSessionStatusRunStarted(
                    sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData
                ): String = sessionStatusRunStarted.workspaceId()

                override fun visitSessionStatusIdled(
                    sessionStatusIdled: BetaWebhookSessionStatusIdledEventData
                ): String = sessionStatusIdled.workspaceId()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData
                ): String = sessionStatusTerminated.workspaceId()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData
                ): String = sessionThreadCreated.workspaceId()

                override fun visitSessionThreadIdled(
                    sessionThreadIdled: BetaWebhookSessionThreadIdledEventData
                ): String = sessionThreadIdled.workspaceId()

                override fun visitSessionThreadTerminated(
                    sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData
                ): String = sessionThreadTerminated.workspaceId()

                override fun visitSessionOutcomeEvaluationEnded(
                    sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData
                ): String = sessionOutcomeEvaluationEnded.workspaceId()

                override fun visitVaultCreated(
                    vaultCreated: BetaWebhookVaultCreatedEventData
                ): String = vaultCreated.workspaceId()

                override fun visitVaultArchived(
                    vaultArchived: BetaWebhookVaultArchivedEventData
                ): String = vaultArchived.workspaceId()

                override fun visitVaultDeleted(
                    vaultDeleted: BetaWebhookVaultDeletedEventData
                ): String = vaultDeleted.workspaceId()

                override fun visitVaultCredentialCreated(
                    vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData
                ): String = vaultCredentialCreated.workspaceId()

                override fun visitVaultCredentialArchived(
                    vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData
                ): String = vaultCredentialArchived.workspaceId()

                override fun visitVaultCredentialDeleted(
                    vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData
                ): String = vaultCredentialDeleted.workspaceId()

                override fun visitVaultCredentialRefreshFailed(
                    vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData
                ): String = vaultCredentialRefreshFailed.workspaceId()

                override fun visitSessionUpdated(
                    sessionUpdated: BetaWebhookSessionUpdatedEventData
                ): String = sessionUpdated.workspaceId()

                override fun visitAgentCreated(
                    agentCreated: BetaWebhookAgentCreatedEventData
                ): String = agentCreated.workspaceId()

                override fun visitAgentArchived(
                    agentArchived: BetaWebhookAgentArchivedEventData
                ): String = agentArchived.workspaceId()

                override fun visitAgentDeleted(
                    agentDeleted: BetaWebhookAgentDeletedEventData
                ): String = agentDeleted.workspaceId()

                override fun visitDeploymentPaused(
                    deploymentPaused: BetaWebhookDeploymentPausedEventData
                ): String = deploymentPaused.workspaceId()

                override fun visitDeploymentRunFailed(
                    deploymentRunFailed: BetaWebhookDeploymentRunFailedEventData
                ): String = deploymentRunFailed.workspaceId()

                override fun visitDeploymentCreated(
                    deploymentCreated: BetaWebhookDeploymentCreatedEventData
                ): String = deploymentCreated.workspaceId()

                override fun visitDeploymentUpdated(
                    deploymentUpdated: BetaWebhookDeploymentUpdatedEventData
                ): String = deploymentUpdated.workspaceId()

                override fun visitDeploymentUnpaused(
                    deploymentUnpaused: BetaWebhookDeploymentUnpausedEventData
                ): String = deploymentUnpaused.workspaceId()

                override fun visitAgentUpdated(
                    agentUpdated: BetaWebhookAgentUpdatedEventData
                ): String = agentUpdated.workspaceId()

                override fun visitDeploymentArchived(
                    deploymentArchived: BetaWebhookDeploymentArchivedEventData
                ): String = deploymentArchived.workspaceId()

                override fun visitDeploymentRunStarted(
                    deploymentRunStarted: BetaWebhookDeploymentRunStartedEventData
                ): String = deploymentRunStarted.workspaceId()

                override fun visitDeploymentDeleted(
                    deploymentDeleted: BetaWebhookDeploymentDeletedEventData
                ): String = deploymentDeleted.workspaceId()

                override fun visitDeploymentRunSucceeded(
                    deploymentRunSucceeded: BetaWebhookDeploymentRunSucceededEventData
                ): String = deploymentRunSucceeded.workspaceId()

                override fun visitEnvironmentCreated(
                    environmentCreated: BetaWebhookEnvironmentCreatedEventData
                ): String = environmentCreated.workspaceId()

                override fun visitEnvironmentUpdated(
                    environmentUpdated: BetaWebhookEnvironmentUpdatedEventData
                ): String = environmentUpdated.workspaceId()

                override fun visitEnvironmentArchived(
                    environmentArchived: BetaWebhookEnvironmentArchivedEventData
                ): String = environmentArchived.workspaceId()

                override fun visitEnvironmentDeleted(
                    environmentDeleted: BetaWebhookEnvironmentDeletedEventData
                ): String = environmentDeleted.workspaceId()

                override fun visitMemoryStoreCreated(
                    memoryStoreCreated: BetaWebhookMemoryStoreCreatedEventData
                ): String = memoryStoreCreated.workspaceId()

                override fun visitMemoryStoreArchived(
                    memoryStoreArchived: BetaWebhookMemoryStoreArchivedEventData
                ): String = memoryStoreArchived.workspaceId()

                override fun visitMemoryStoreDeleted(
                    memoryStoreDeleted: BetaWebhookMemoryStoreDeletedEventData
                ): String = memoryStoreDeleted.workspaceId()

                override fun visitSessionBudgetReached(
                    sessionBudgetReached: BetaWebhookSessionBudgetReachedEventData
                ): String = sessionBudgetReached.workspaceId()

                override fun unknown(json: JsonValue?): String =
                    json.getProperty<String>("workspace_id").getRequired("workspace_id")
            }
        )

    fun sessionThreadId(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitSessionCreated(
                    sessionCreated: BetaWebhookSessionCreatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionPending(
                    sessionPending: BetaWebhookSessionPendingEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionRunning(
                    sessionRunning: BetaWebhookSessionRunningEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionIdled(
                    sessionIdled: BetaWebhookSessionIdledEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionRequiresAction(
                    sessionRequiresAction: BetaWebhookSessionRequiresActionEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionArchived(
                    sessionArchived: BetaWebhookSessionArchivedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaWebhookSessionDeletedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaWebhookSessionStatusRescheduledEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusRunStarted(
                    sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusIdled(
                    sessionStatusIdled: BetaWebhookSessionStatusIdledEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData
                ): Optional<String> = Optional.of(sessionThreadCreated.sessionThreadId())

                override fun visitSessionThreadIdled(
                    sessionThreadIdled: BetaWebhookSessionThreadIdledEventData
                ): Optional<String> = Optional.of(sessionThreadIdled.sessionThreadId())

                override fun visitSessionThreadTerminated(
                    sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData
                ): Optional<String> = Optional.of(sessionThreadTerminated.sessionThreadId())

                override fun visitSessionOutcomeEvaluationEnded(
                    sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData
                ): Optional<String> = Optional.empty()

                override fun visitVaultCreated(
                    vaultCreated: BetaWebhookVaultCreatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitVaultArchived(
                    vaultArchived: BetaWebhookVaultArchivedEventData
                ): Optional<String> = Optional.empty()

                override fun visitVaultDeleted(
                    vaultDeleted: BetaWebhookVaultDeletedEventData
                ): Optional<String> = Optional.empty()

                override fun visitVaultCredentialCreated(
                    vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitVaultCredentialArchived(
                    vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData
                ): Optional<String> = Optional.empty()

                override fun visitVaultCredentialDeleted(
                    vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData
                ): Optional<String> = Optional.empty()

                override fun visitVaultCredentialRefreshFailed(
                    vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionUpdated(
                    sessionUpdated: BetaWebhookSessionUpdatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitAgentCreated(
                    agentCreated: BetaWebhookAgentCreatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitAgentArchived(
                    agentArchived: BetaWebhookAgentArchivedEventData
                ): Optional<String> = Optional.empty()

                override fun visitAgentDeleted(
                    agentDeleted: BetaWebhookAgentDeletedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentPaused(
                    deploymentPaused: BetaWebhookDeploymentPausedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentRunFailed(
                    deploymentRunFailed: BetaWebhookDeploymentRunFailedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentCreated(
                    deploymentCreated: BetaWebhookDeploymentCreatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentUpdated(
                    deploymentUpdated: BetaWebhookDeploymentUpdatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentUnpaused(
                    deploymentUnpaused: BetaWebhookDeploymentUnpausedEventData
                ): Optional<String> = Optional.empty()

                override fun visitAgentUpdated(
                    agentUpdated: BetaWebhookAgentUpdatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentArchived(
                    deploymentArchived: BetaWebhookDeploymentArchivedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentRunStarted(
                    deploymentRunStarted: BetaWebhookDeploymentRunStartedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentDeleted(
                    deploymentDeleted: BetaWebhookDeploymentDeletedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentRunSucceeded(
                    deploymentRunSucceeded: BetaWebhookDeploymentRunSucceededEventData
                ): Optional<String> = Optional.empty()

                override fun visitEnvironmentCreated(
                    environmentCreated: BetaWebhookEnvironmentCreatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitEnvironmentUpdated(
                    environmentUpdated: BetaWebhookEnvironmentUpdatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitEnvironmentArchived(
                    environmentArchived: BetaWebhookEnvironmentArchivedEventData
                ): Optional<String> = Optional.empty()

                override fun visitEnvironmentDeleted(
                    environmentDeleted: BetaWebhookEnvironmentDeletedEventData
                ): Optional<String> = Optional.empty()

                override fun visitMemoryStoreCreated(
                    memoryStoreCreated: BetaWebhookMemoryStoreCreatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitMemoryStoreArchived(
                    memoryStoreArchived: BetaWebhookMemoryStoreArchivedEventData
                ): Optional<String> = Optional.empty()

                override fun visitMemoryStoreDeleted(
                    memoryStoreDeleted: BetaWebhookMemoryStoreDeletedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionBudgetReached(
                    sessionBudgetReached: BetaWebhookSessionBudgetReachedEventData
                ): Optional<String> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("session_thread_id").asKnown()
            }
        )

    fun vaultId(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitSessionCreated(
                    sessionCreated: BetaWebhookSessionCreatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionPending(
                    sessionPending: BetaWebhookSessionPendingEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionRunning(
                    sessionRunning: BetaWebhookSessionRunningEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionIdled(
                    sessionIdled: BetaWebhookSessionIdledEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionRequiresAction(
                    sessionRequiresAction: BetaWebhookSessionRequiresActionEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionArchived(
                    sessionArchived: BetaWebhookSessionArchivedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaWebhookSessionDeletedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaWebhookSessionStatusRescheduledEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusRunStarted(
                    sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusIdled(
                    sessionStatusIdled: BetaWebhookSessionStatusIdledEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadIdled(
                    sessionThreadIdled: BetaWebhookSessionThreadIdledEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionThreadTerminated(
                    sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionOutcomeEvaluationEnded(
                    sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData
                ): Optional<String> = Optional.empty()

                override fun visitVaultCreated(
                    vaultCreated: BetaWebhookVaultCreatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitVaultArchived(
                    vaultArchived: BetaWebhookVaultArchivedEventData
                ): Optional<String> = Optional.empty()

                override fun visitVaultDeleted(
                    vaultDeleted: BetaWebhookVaultDeletedEventData
                ): Optional<String> = Optional.empty()

                override fun visitVaultCredentialCreated(
                    vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData
                ): Optional<String> = Optional.of(vaultCredentialCreated.vaultId())

                override fun visitVaultCredentialArchived(
                    vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData
                ): Optional<String> = Optional.of(vaultCredentialArchived.vaultId())

                override fun visitVaultCredentialDeleted(
                    vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData
                ): Optional<String> = Optional.of(vaultCredentialDeleted.vaultId())

                override fun visitVaultCredentialRefreshFailed(
                    vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData
                ): Optional<String> = Optional.of(vaultCredentialRefreshFailed.vaultId())

                override fun visitSessionUpdated(
                    sessionUpdated: BetaWebhookSessionUpdatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitAgentCreated(
                    agentCreated: BetaWebhookAgentCreatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitAgentArchived(
                    agentArchived: BetaWebhookAgentArchivedEventData
                ): Optional<String> = Optional.empty()

                override fun visitAgentDeleted(
                    agentDeleted: BetaWebhookAgentDeletedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentPaused(
                    deploymentPaused: BetaWebhookDeploymentPausedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentRunFailed(
                    deploymentRunFailed: BetaWebhookDeploymentRunFailedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentCreated(
                    deploymentCreated: BetaWebhookDeploymentCreatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentUpdated(
                    deploymentUpdated: BetaWebhookDeploymentUpdatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentUnpaused(
                    deploymentUnpaused: BetaWebhookDeploymentUnpausedEventData
                ): Optional<String> = Optional.empty()

                override fun visitAgentUpdated(
                    agentUpdated: BetaWebhookAgentUpdatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentArchived(
                    deploymentArchived: BetaWebhookDeploymentArchivedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentRunStarted(
                    deploymentRunStarted: BetaWebhookDeploymentRunStartedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentDeleted(
                    deploymentDeleted: BetaWebhookDeploymentDeletedEventData
                ): Optional<String> = Optional.empty()

                override fun visitDeploymentRunSucceeded(
                    deploymentRunSucceeded: BetaWebhookDeploymentRunSucceededEventData
                ): Optional<String> = Optional.empty()

                override fun visitEnvironmentCreated(
                    environmentCreated: BetaWebhookEnvironmentCreatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitEnvironmentUpdated(
                    environmentUpdated: BetaWebhookEnvironmentUpdatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitEnvironmentArchived(
                    environmentArchived: BetaWebhookEnvironmentArchivedEventData
                ): Optional<String> = Optional.empty()

                override fun visitEnvironmentDeleted(
                    environmentDeleted: BetaWebhookEnvironmentDeletedEventData
                ): Optional<String> = Optional.empty()

                override fun visitMemoryStoreCreated(
                    memoryStoreCreated: BetaWebhookMemoryStoreCreatedEventData
                ): Optional<String> = Optional.empty()

                override fun visitMemoryStoreArchived(
                    memoryStoreArchived: BetaWebhookMemoryStoreArchivedEventData
                ): Optional<String> = Optional.empty()

                override fun visitMemoryStoreDeleted(
                    memoryStoreDeleted: BetaWebhookMemoryStoreDeletedEventData
                ): Optional<String> = Optional.empty()

                override fun visitSessionBudgetReached(
                    sessionBudgetReached: BetaWebhookSessionBudgetReachedEventData
                ): Optional<String> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("vault_id").asKnown()
            }
        )

    fun sessionCreated(): Optional<BetaWebhookSessionCreatedEventData> =
        Optional.ofNullable(sessionCreated)

    fun sessionPending(): Optional<BetaWebhookSessionPendingEventData> =
        Optional.ofNullable(sessionPending)

    fun sessionRunning(): Optional<BetaWebhookSessionRunningEventData> =
        Optional.ofNullable(sessionRunning)

    fun sessionIdled(): Optional<BetaWebhookSessionIdledEventData> =
        Optional.ofNullable(sessionIdled)

    fun sessionRequiresAction(): Optional<BetaWebhookSessionRequiresActionEventData> =
        Optional.ofNullable(sessionRequiresAction)

    fun sessionArchived(): Optional<BetaWebhookSessionArchivedEventData> =
        Optional.ofNullable(sessionArchived)

    fun sessionDeleted(): Optional<BetaWebhookSessionDeletedEventData> =
        Optional.ofNullable(sessionDeleted)

    fun sessionStatusRescheduled(): Optional<BetaWebhookSessionStatusRescheduledEventData> =
        Optional.ofNullable(sessionStatusRescheduled)

    fun sessionStatusRunStarted(): Optional<BetaWebhookSessionStatusRunStartedEventData> =
        Optional.ofNullable(sessionStatusRunStarted)

    fun sessionStatusIdled(): Optional<BetaWebhookSessionStatusIdledEventData> =
        Optional.ofNullable(sessionStatusIdled)

    fun sessionStatusTerminated(): Optional<BetaWebhookSessionStatusTerminatedEventData> =
        Optional.ofNullable(sessionStatusTerminated)

    fun sessionThreadCreated(): Optional<BetaWebhookSessionThreadCreatedEventData> =
        Optional.ofNullable(sessionThreadCreated)

    fun sessionThreadIdled(): Optional<BetaWebhookSessionThreadIdledEventData> =
        Optional.ofNullable(sessionThreadIdled)

    fun sessionThreadTerminated(): Optional<BetaWebhookSessionThreadTerminatedEventData> =
        Optional.ofNullable(sessionThreadTerminated)

    fun sessionOutcomeEvaluationEnded():
        Optional<BetaWebhookSessionOutcomeEvaluationEndedEventData> =
        Optional.ofNullable(sessionOutcomeEvaluationEnded)

    fun vaultCreated(): Optional<BetaWebhookVaultCreatedEventData> =
        Optional.ofNullable(vaultCreated)

    fun vaultArchived(): Optional<BetaWebhookVaultArchivedEventData> =
        Optional.ofNullable(vaultArchived)

    fun vaultDeleted(): Optional<BetaWebhookVaultDeletedEventData> =
        Optional.ofNullable(vaultDeleted)

    fun vaultCredentialCreated(): Optional<BetaWebhookVaultCredentialCreatedEventData> =
        Optional.ofNullable(vaultCredentialCreated)

    fun vaultCredentialArchived(): Optional<BetaWebhookVaultCredentialArchivedEventData> =
        Optional.ofNullable(vaultCredentialArchived)

    fun vaultCredentialDeleted(): Optional<BetaWebhookVaultCredentialDeletedEventData> =
        Optional.ofNullable(vaultCredentialDeleted)

    fun vaultCredentialRefreshFailed(): Optional<BetaWebhookVaultCredentialRefreshFailedEventData> =
        Optional.ofNullable(vaultCredentialRefreshFailed)

    fun sessionUpdated(): Optional<BetaWebhookSessionUpdatedEventData> =
        Optional.ofNullable(sessionUpdated)

    fun agentCreated(): Optional<BetaWebhookAgentCreatedEventData> =
        Optional.ofNullable(agentCreated)

    fun agentArchived(): Optional<BetaWebhookAgentArchivedEventData> =
        Optional.ofNullable(agentArchived)

    fun agentDeleted(): Optional<BetaWebhookAgentDeletedEventData> =
        Optional.ofNullable(agentDeleted)

    fun deploymentPaused(): Optional<BetaWebhookDeploymentPausedEventData> =
        Optional.ofNullable(deploymentPaused)

    fun deploymentRunFailed(): Optional<BetaWebhookDeploymentRunFailedEventData> =
        Optional.ofNullable(deploymentRunFailed)

    fun deploymentCreated(): Optional<BetaWebhookDeploymentCreatedEventData> =
        Optional.ofNullable(deploymentCreated)

    fun deploymentUpdated(): Optional<BetaWebhookDeploymentUpdatedEventData> =
        Optional.ofNullable(deploymentUpdated)

    fun deploymentUnpaused(): Optional<BetaWebhookDeploymentUnpausedEventData> =
        Optional.ofNullable(deploymentUnpaused)

    fun agentUpdated(): Optional<BetaWebhookAgentUpdatedEventData> =
        Optional.ofNullable(agentUpdated)

    fun deploymentArchived(): Optional<BetaWebhookDeploymentArchivedEventData> =
        Optional.ofNullable(deploymentArchived)

    fun deploymentRunStarted(): Optional<BetaWebhookDeploymentRunStartedEventData> =
        Optional.ofNullable(deploymentRunStarted)

    fun deploymentDeleted(): Optional<BetaWebhookDeploymentDeletedEventData> =
        Optional.ofNullable(deploymentDeleted)

    fun deploymentRunSucceeded(): Optional<BetaWebhookDeploymentRunSucceededEventData> =
        Optional.ofNullable(deploymentRunSucceeded)

    fun environmentCreated(): Optional<BetaWebhookEnvironmentCreatedEventData> =
        Optional.ofNullable(environmentCreated)

    fun environmentUpdated(): Optional<BetaWebhookEnvironmentUpdatedEventData> =
        Optional.ofNullable(environmentUpdated)

    fun environmentArchived(): Optional<BetaWebhookEnvironmentArchivedEventData> =
        Optional.ofNullable(environmentArchived)

    fun environmentDeleted(): Optional<BetaWebhookEnvironmentDeletedEventData> =
        Optional.ofNullable(environmentDeleted)

    fun memoryStoreCreated(): Optional<BetaWebhookMemoryStoreCreatedEventData> =
        Optional.ofNullable(memoryStoreCreated)

    fun memoryStoreArchived(): Optional<BetaWebhookMemoryStoreArchivedEventData> =
        Optional.ofNullable(memoryStoreArchived)

    fun memoryStoreDeleted(): Optional<BetaWebhookMemoryStoreDeletedEventData> =
        Optional.ofNullable(memoryStoreDeleted)

    fun sessionBudgetReached(): Optional<BetaWebhookSessionBudgetReachedEventData> =
        Optional.ofNullable(sessionBudgetReached)

    fun isSessionCreated(): Boolean = sessionCreated != null

    fun isSessionPending(): Boolean = sessionPending != null

    fun isSessionRunning(): Boolean = sessionRunning != null

    fun isSessionIdled(): Boolean = sessionIdled != null

    fun isSessionRequiresAction(): Boolean = sessionRequiresAction != null

    fun isSessionArchived(): Boolean = sessionArchived != null

    fun isSessionDeleted(): Boolean = sessionDeleted != null

    fun isSessionStatusRescheduled(): Boolean = sessionStatusRescheduled != null

    fun isSessionStatusRunStarted(): Boolean = sessionStatusRunStarted != null

    fun isSessionStatusIdled(): Boolean = sessionStatusIdled != null

    fun isSessionStatusTerminated(): Boolean = sessionStatusTerminated != null

    fun isSessionThreadCreated(): Boolean = sessionThreadCreated != null

    fun isSessionThreadIdled(): Boolean = sessionThreadIdled != null

    fun isSessionThreadTerminated(): Boolean = sessionThreadTerminated != null

    fun isSessionOutcomeEvaluationEnded(): Boolean = sessionOutcomeEvaluationEnded != null

    fun isVaultCreated(): Boolean = vaultCreated != null

    fun isVaultArchived(): Boolean = vaultArchived != null

    fun isVaultDeleted(): Boolean = vaultDeleted != null

    fun isVaultCredentialCreated(): Boolean = vaultCredentialCreated != null

    fun isVaultCredentialArchived(): Boolean = vaultCredentialArchived != null

    fun isVaultCredentialDeleted(): Boolean = vaultCredentialDeleted != null

    fun isVaultCredentialRefreshFailed(): Boolean = vaultCredentialRefreshFailed != null

    fun isSessionUpdated(): Boolean = sessionUpdated != null

    fun isAgentCreated(): Boolean = agentCreated != null

    fun isAgentArchived(): Boolean = agentArchived != null

    fun isAgentDeleted(): Boolean = agentDeleted != null

    fun isDeploymentPaused(): Boolean = deploymentPaused != null

    fun isDeploymentRunFailed(): Boolean = deploymentRunFailed != null

    fun isDeploymentCreated(): Boolean = deploymentCreated != null

    fun isDeploymentUpdated(): Boolean = deploymentUpdated != null

    fun isDeploymentUnpaused(): Boolean = deploymentUnpaused != null

    fun isAgentUpdated(): Boolean = agentUpdated != null

    fun isDeploymentArchived(): Boolean = deploymentArchived != null

    fun isDeploymentRunStarted(): Boolean = deploymentRunStarted != null

    fun isDeploymentDeleted(): Boolean = deploymentDeleted != null

    fun isDeploymentRunSucceeded(): Boolean = deploymentRunSucceeded != null

    fun isEnvironmentCreated(): Boolean = environmentCreated != null

    fun isEnvironmentUpdated(): Boolean = environmentUpdated != null

    fun isEnvironmentArchived(): Boolean = environmentArchived != null

    fun isEnvironmentDeleted(): Boolean = environmentDeleted != null

    fun isMemoryStoreCreated(): Boolean = memoryStoreCreated != null

    fun isMemoryStoreArchived(): Boolean = memoryStoreArchived != null

    fun isMemoryStoreDeleted(): Boolean = memoryStoreDeleted != null

    fun isSessionBudgetReached(): Boolean = sessionBudgetReached != null

    fun asSessionCreated(): BetaWebhookSessionCreatedEventData =
        sessionCreated.getOrThrow("sessionCreated")

    fun asSessionPending(): BetaWebhookSessionPendingEventData =
        sessionPending.getOrThrow("sessionPending")

    fun asSessionRunning(): BetaWebhookSessionRunningEventData =
        sessionRunning.getOrThrow("sessionRunning")

    fun asSessionIdled(): BetaWebhookSessionIdledEventData = sessionIdled.getOrThrow("sessionIdled")

    fun asSessionRequiresAction(): BetaWebhookSessionRequiresActionEventData =
        sessionRequiresAction.getOrThrow("sessionRequiresAction")

    fun asSessionArchived(): BetaWebhookSessionArchivedEventData =
        sessionArchived.getOrThrow("sessionArchived")

    fun asSessionDeleted(): BetaWebhookSessionDeletedEventData =
        sessionDeleted.getOrThrow("sessionDeleted")

    fun asSessionStatusRescheduled(): BetaWebhookSessionStatusRescheduledEventData =
        sessionStatusRescheduled.getOrThrow("sessionStatusRescheduled")

    fun asSessionStatusRunStarted(): BetaWebhookSessionStatusRunStartedEventData =
        sessionStatusRunStarted.getOrThrow("sessionStatusRunStarted")

    fun asSessionStatusIdled(): BetaWebhookSessionStatusIdledEventData =
        sessionStatusIdled.getOrThrow("sessionStatusIdled")

    fun asSessionStatusTerminated(): BetaWebhookSessionStatusTerminatedEventData =
        sessionStatusTerminated.getOrThrow("sessionStatusTerminated")

    fun asSessionThreadCreated(): BetaWebhookSessionThreadCreatedEventData =
        sessionThreadCreated.getOrThrow("sessionThreadCreated")

    fun asSessionThreadIdled(): BetaWebhookSessionThreadIdledEventData =
        sessionThreadIdled.getOrThrow("sessionThreadIdled")

    fun asSessionThreadTerminated(): BetaWebhookSessionThreadTerminatedEventData =
        sessionThreadTerminated.getOrThrow("sessionThreadTerminated")

    fun asSessionOutcomeEvaluationEnded(): BetaWebhookSessionOutcomeEvaluationEndedEventData =
        sessionOutcomeEvaluationEnded.getOrThrow("sessionOutcomeEvaluationEnded")

    fun asVaultCreated(): BetaWebhookVaultCreatedEventData = vaultCreated.getOrThrow("vaultCreated")

    fun asVaultArchived(): BetaWebhookVaultArchivedEventData =
        vaultArchived.getOrThrow("vaultArchived")

    fun asVaultDeleted(): BetaWebhookVaultDeletedEventData = vaultDeleted.getOrThrow("vaultDeleted")

    fun asVaultCredentialCreated(): BetaWebhookVaultCredentialCreatedEventData =
        vaultCredentialCreated.getOrThrow("vaultCredentialCreated")

    fun asVaultCredentialArchived(): BetaWebhookVaultCredentialArchivedEventData =
        vaultCredentialArchived.getOrThrow("vaultCredentialArchived")

    fun asVaultCredentialDeleted(): BetaWebhookVaultCredentialDeletedEventData =
        vaultCredentialDeleted.getOrThrow("vaultCredentialDeleted")

    fun asVaultCredentialRefreshFailed(): BetaWebhookVaultCredentialRefreshFailedEventData =
        vaultCredentialRefreshFailed.getOrThrow("vaultCredentialRefreshFailed")

    fun asSessionUpdated(): BetaWebhookSessionUpdatedEventData =
        sessionUpdated.getOrThrow("sessionUpdated")

    fun asAgentCreated(): BetaWebhookAgentCreatedEventData = agentCreated.getOrThrow("agentCreated")

    fun asAgentArchived(): BetaWebhookAgentArchivedEventData =
        agentArchived.getOrThrow("agentArchived")

    fun asAgentDeleted(): BetaWebhookAgentDeletedEventData = agentDeleted.getOrThrow("agentDeleted")

    fun asDeploymentPaused(): BetaWebhookDeploymentPausedEventData =
        deploymentPaused.getOrThrow("deploymentPaused")

    fun asDeploymentRunFailed(): BetaWebhookDeploymentRunFailedEventData =
        deploymentRunFailed.getOrThrow("deploymentRunFailed")

    fun asDeploymentCreated(): BetaWebhookDeploymentCreatedEventData =
        deploymentCreated.getOrThrow("deploymentCreated")

    fun asDeploymentUpdated(): BetaWebhookDeploymentUpdatedEventData =
        deploymentUpdated.getOrThrow("deploymentUpdated")

    fun asDeploymentUnpaused(): BetaWebhookDeploymentUnpausedEventData =
        deploymentUnpaused.getOrThrow("deploymentUnpaused")

    fun asAgentUpdated(): BetaWebhookAgentUpdatedEventData = agentUpdated.getOrThrow("agentUpdated")

    fun asDeploymentArchived(): BetaWebhookDeploymentArchivedEventData =
        deploymentArchived.getOrThrow("deploymentArchived")

    fun asDeploymentRunStarted(): BetaWebhookDeploymentRunStartedEventData =
        deploymentRunStarted.getOrThrow("deploymentRunStarted")

    fun asDeploymentDeleted(): BetaWebhookDeploymentDeletedEventData =
        deploymentDeleted.getOrThrow("deploymentDeleted")

    fun asDeploymentRunSucceeded(): BetaWebhookDeploymentRunSucceededEventData =
        deploymentRunSucceeded.getOrThrow("deploymentRunSucceeded")

    fun asEnvironmentCreated(): BetaWebhookEnvironmentCreatedEventData =
        environmentCreated.getOrThrow("environmentCreated")

    fun asEnvironmentUpdated(): BetaWebhookEnvironmentUpdatedEventData =
        environmentUpdated.getOrThrow("environmentUpdated")

    fun asEnvironmentArchived(): BetaWebhookEnvironmentArchivedEventData =
        environmentArchived.getOrThrow("environmentArchived")

    fun asEnvironmentDeleted(): BetaWebhookEnvironmentDeletedEventData =
        environmentDeleted.getOrThrow("environmentDeleted")

    fun asMemoryStoreCreated(): BetaWebhookMemoryStoreCreatedEventData =
        memoryStoreCreated.getOrThrow("memoryStoreCreated")

    fun asMemoryStoreArchived(): BetaWebhookMemoryStoreArchivedEventData =
        memoryStoreArchived.getOrThrow("memoryStoreArchived")

    fun asMemoryStoreDeleted(): BetaWebhookMemoryStoreDeletedEventData =
        memoryStoreDeleted.getOrThrow("memoryStoreDeleted")

    fun asSessionBudgetReached(): BetaWebhookSessionBudgetReachedEventData =
        sessionBudgetReached.getOrThrow("sessionBudgetReached")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaWebhookEventData.accept(new BetaWebhookEventData.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitSessionCreated(BetaWebhookSessionCreatedEventData sessionCreated) {
     *         return Optional.of(sessionCreated.toString());
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
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
    fun <T> accept(visitor: Visitor<T>): T =
        when {
            sessionCreated != null -> visitor.visitSessionCreated(sessionCreated)
            sessionPending != null -> visitor.visitSessionPending(sessionPending)
            sessionRunning != null -> visitor.visitSessionRunning(sessionRunning)
            sessionIdled != null -> visitor.visitSessionIdled(sessionIdled)
            sessionRequiresAction != null ->
                visitor.visitSessionRequiresAction(sessionRequiresAction)
            sessionArchived != null -> visitor.visitSessionArchived(sessionArchived)
            sessionDeleted != null -> visitor.visitSessionDeleted(sessionDeleted)
            sessionStatusRescheduled != null ->
                visitor.visitSessionStatusRescheduled(sessionStatusRescheduled)
            sessionStatusRunStarted != null ->
                visitor.visitSessionStatusRunStarted(sessionStatusRunStarted)
            sessionStatusIdled != null -> visitor.visitSessionStatusIdled(sessionStatusIdled)
            sessionStatusTerminated != null ->
                visitor.visitSessionStatusTerminated(sessionStatusTerminated)
            sessionThreadCreated != null -> visitor.visitSessionThreadCreated(sessionThreadCreated)
            sessionThreadIdled != null -> visitor.visitSessionThreadIdled(sessionThreadIdled)
            sessionThreadTerminated != null ->
                visitor.visitSessionThreadTerminated(sessionThreadTerminated)
            sessionOutcomeEvaluationEnded != null ->
                visitor.visitSessionOutcomeEvaluationEnded(sessionOutcomeEvaluationEnded)
            vaultCreated != null -> visitor.visitVaultCreated(vaultCreated)
            vaultArchived != null -> visitor.visitVaultArchived(vaultArchived)
            vaultDeleted != null -> visitor.visitVaultDeleted(vaultDeleted)
            vaultCredentialCreated != null ->
                visitor.visitVaultCredentialCreated(vaultCredentialCreated)
            vaultCredentialArchived != null ->
                visitor.visitVaultCredentialArchived(vaultCredentialArchived)
            vaultCredentialDeleted != null ->
                visitor.visitVaultCredentialDeleted(vaultCredentialDeleted)
            vaultCredentialRefreshFailed != null ->
                visitor.visitVaultCredentialRefreshFailed(vaultCredentialRefreshFailed)
            sessionUpdated != null -> visitor.visitSessionUpdated(sessionUpdated)
            agentCreated != null -> visitor.visitAgentCreated(agentCreated)
            agentArchived != null -> visitor.visitAgentArchived(agentArchived)
            agentDeleted != null -> visitor.visitAgentDeleted(agentDeleted)
            deploymentPaused != null -> visitor.visitDeploymentPaused(deploymentPaused)
            deploymentRunFailed != null -> visitor.visitDeploymentRunFailed(deploymentRunFailed)
            deploymentCreated != null -> visitor.visitDeploymentCreated(deploymentCreated)
            deploymentUpdated != null -> visitor.visitDeploymentUpdated(deploymentUpdated)
            deploymentUnpaused != null -> visitor.visitDeploymentUnpaused(deploymentUnpaused)
            agentUpdated != null -> visitor.visitAgentUpdated(agentUpdated)
            deploymentArchived != null -> visitor.visitDeploymentArchived(deploymentArchived)
            deploymentRunStarted != null -> visitor.visitDeploymentRunStarted(deploymentRunStarted)
            deploymentDeleted != null -> visitor.visitDeploymentDeleted(deploymentDeleted)
            deploymentRunSucceeded != null ->
                visitor.visitDeploymentRunSucceeded(deploymentRunSucceeded)
            environmentCreated != null -> visitor.visitEnvironmentCreated(environmentCreated)
            environmentUpdated != null -> visitor.visitEnvironmentUpdated(environmentUpdated)
            environmentArchived != null -> visitor.visitEnvironmentArchived(environmentArchived)
            environmentDeleted != null -> visitor.visitEnvironmentDeleted(environmentDeleted)
            memoryStoreCreated != null -> visitor.visitMemoryStoreCreated(memoryStoreCreated)
            memoryStoreArchived != null -> visitor.visitMemoryStoreArchived(memoryStoreArchived)
            memoryStoreDeleted != null -> visitor.visitMemoryStoreDeleted(memoryStoreDeleted)
            sessionBudgetReached != null -> visitor.visitSessionBudgetReached(sessionBudgetReached)
            else -> visitor.unknown(_json)
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
    fun validate(): BetaWebhookEventData = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitSessionCreated(
                    sessionCreated: BetaWebhookSessionCreatedEventData
                ) {
                    sessionCreated.validate()
                }

                override fun visitSessionPending(
                    sessionPending: BetaWebhookSessionPendingEventData
                ) {
                    sessionPending.validate()
                }

                override fun visitSessionRunning(
                    sessionRunning: BetaWebhookSessionRunningEventData
                ) {
                    sessionRunning.validate()
                }

                override fun visitSessionIdled(sessionIdled: BetaWebhookSessionIdledEventData) {
                    sessionIdled.validate()
                }

                override fun visitSessionRequiresAction(
                    sessionRequiresAction: BetaWebhookSessionRequiresActionEventData
                ) {
                    sessionRequiresAction.validate()
                }

                override fun visitSessionArchived(
                    sessionArchived: BetaWebhookSessionArchivedEventData
                ) {
                    sessionArchived.validate()
                }

                override fun visitSessionDeleted(
                    sessionDeleted: BetaWebhookSessionDeletedEventData
                ) {
                    sessionDeleted.validate()
                }

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaWebhookSessionStatusRescheduledEventData
                ) {
                    sessionStatusRescheduled.validate()
                }

                override fun visitSessionStatusRunStarted(
                    sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData
                ) {
                    sessionStatusRunStarted.validate()
                }

                override fun visitSessionStatusIdled(
                    sessionStatusIdled: BetaWebhookSessionStatusIdledEventData
                ) {
                    sessionStatusIdled.validate()
                }

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData
                ) {
                    sessionStatusTerminated.validate()
                }

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData
                ) {
                    sessionThreadCreated.validate()
                }

                override fun visitSessionThreadIdled(
                    sessionThreadIdled: BetaWebhookSessionThreadIdledEventData
                ) {
                    sessionThreadIdled.validate()
                }

                override fun visitSessionThreadTerminated(
                    sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData
                ) {
                    sessionThreadTerminated.validate()
                }

                override fun visitSessionOutcomeEvaluationEnded(
                    sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData
                ) {
                    sessionOutcomeEvaluationEnded.validate()
                }

                override fun visitVaultCreated(vaultCreated: BetaWebhookVaultCreatedEventData) {
                    vaultCreated.validate()
                }

                override fun visitVaultArchived(vaultArchived: BetaWebhookVaultArchivedEventData) {
                    vaultArchived.validate()
                }

                override fun visitVaultDeleted(vaultDeleted: BetaWebhookVaultDeletedEventData) {
                    vaultDeleted.validate()
                }

                override fun visitVaultCredentialCreated(
                    vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData
                ) {
                    vaultCredentialCreated.validate()
                }

                override fun visitVaultCredentialArchived(
                    vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData
                ) {
                    vaultCredentialArchived.validate()
                }

                override fun visitVaultCredentialDeleted(
                    vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData
                ) {
                    vaultCredentialDeleted.validate()
                }

                override fun visitVaultCredentialRefreshFailed(
                    vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData
                ) {
                    vaultCredentialRefreshFailed.validate()
                }

                override fun visitSessionUpdated(
                    sessionUpdated: BetaWebhookSessionUpdatedEventData
                ) {
                    sessionUpdated.validate()
                }

                override fun visitAgentCreated(agentCreated: BetaWebhookAgentCreatedEventData) {
                    agentCreated.validate()
                }

                override fun visitAgentArchived(agentArchived: BetaWebhookAgentArchivedEventData) {
                    agentArchived.validate()
                }

                override fun visitAgentDeleted(agentDeleted: BetaWebhookAgentDeletedEventData) {
                    agentDeleted.validate()
                }

                override fun visitDeploymentPaused(
                    deploymentPaused: BetaWebhookDeploymentPausedEventData
                ) {
                    deploymentPaused.validate()
                }

                override fun visitDeploymentRunFailed(
                    deploymentRunFailed: BetaWebhookDeploymentRunFailedEventData
                ) {
                    deploymentRunFailed.validate()
                }

                override fun visitDeploymentCreated(
                    deploymentCreated: BetaWebhookDeploymentCreatedEventData
                ) {
                    deploymentCreated.validate()
                }

                override fun visitDeploymentUpdated(
                    deploymentUpdated: BetaWebhookDeploymentUpdatedEventData
                ) {
                    deploymentUpdated.validate()
                }

                override fun visitDeploymentUnpaused(
                    deploymentUnpaused: BetaWebhookDeploymentUnpausedEventData
                ) {
                    deploymentUnpaused.validate()
                }

                override fun visitAgentUpdated(agentUpdated: BetaWebhookAgentUpdatedEventData) {
                    agentUpdated.validate()
                }

                override fun visitDeploymentArchived(
                    deploymentArchived: BetaWebhookDeploymentArchivedEventData
                ) {
                    deploymentArchived.validate()
                }

                override fun visitDeploymentRunStarted(
                    deploymentRunStarted: BetaWebhookDeploymentRunStartedEventData
                ) {
                    deploymentRunStarted.validate()
                }

                override fun visitDeploymentDeleted(
                    deploymentDeleted: BetaWebhookDeploymentDeletedEventData
                ) {
                    deploymentDeleted.validate()
                }

                override fun visitDeploymentRunSucceeded(
                    deploymentRunSucceeded: BetaWebhookDeploymentRunSucceededEventData
                ) {
                    deploymentRunSucceeded.validate()
                }

                override fun visitEnvironmentCreated(
                    environmentCreated: BetaWebhookEnvironmentCreatedEventData
                ) {
                    environmentCreated.validate()
                }

                override fun visitEnvironmentUpdated(
                    environmentUpdated: BetaWebhookEnvironmentUpdatedEventData
                ) {
                    environmentUpdated.validate()
                }

                override fun visitEnvironmentArchived(
                    environmentArchived: BetaWebhookEnvironmentArchivedEventData
                ) {
                    environmentArchived.validate()
                }

                override fun visitEnvironmentDeleted(
                    environmentDeleted: BetaWebhookEnvironmentDeletedEventData
                ) {
                    environmentDeleted.validate()
                }

                override fun visitMemoryStoreCreated(
                    memoryStoreCreated: BetaWebhookMemoryStoreCreatedEventData
                ) {
                    memoryStoreCreated.validate()
                }

                override fun visitMemoryStoreArchived(
                    memoryStoreArchived: BetaWebhookMemoryStoreArchivedEventData
                ) {
                    memoryStoreArchived.validate()
                }

                override fun visitMemoryStoreDeleted(
                    memoryStoreDeleted: BetaWebhookMemoryStoreDeletedEventData
                ) {
                    memoryStoreDeleted.validate()
                }

                override fun visitSessionBudgetReached(
                    sessionBudgetReached: BetaWebhookSessionBudgetReachedEventData
                ) {
                    sessionBudgetReached.validate()
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitSessionCreated(
                    sessionCreated: BetaWebhookSessionCreatedEventData
                ) = sessionCreated.validity()

                override fun visitSessionPending(
                    sessionPending: BetaWebhookSessionPendingEventData
                ) = sessionPending.validity()

                override fun visitSessionRunning(
                    sessionRunning: BetaWebhookSessionRunningEventData
                ) = sessionRunning.validity()

                override fun visitSessionIdled(sessionIdled: BetaWebhookSessionIdledEventData) =
                    sessionIdled.validity()

                override fun visitSessionRequiresAction(
                    sessionRequiresAction: BetaWebhookSessionRequiresActionEventData
                ) = sessionRequiresAction.validity()

                override fun visitSessionArchived(
                    sessionArchived: BetaWebhookSessionArchivedEventData
                ) = sessionArchived.validity()

                override fun visitSessionDeleted(
                    sessionDeleted: BetaWebhookSessionDeletedEventData
                ) = sessionDeleted.validity()

                override fun visitSessionStatusRescheduled(
                    sessionStatusRescheduled: BetaWebhookSessionStatusRescheduledEventData
                ) = sessionStatusRescheduled.validity()

                override fun visitSessionStatusRunStarted(
                    sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData
                ) = sessionStatusRunStarted.validity()

                override fun visitSessionStatusIdled(
                    sessionStatusIdled: BetaWebhookSessionStatusIdledEventData
                ) = sessionStatusIdled.validity()

                override fun visitSessionStatusTerminated(
                    sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData
                ) = sessionStatusTerminated.validity()

                override fun visitSessionThreadCreated(
                    sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData
                ) = sessionThreadCreated.validity()

                override fun visitSessionThreadIdled(
                    sessionThreadIdled: BetaWebhookSessionThreadIdledEventData
                ) = sessionThreadIdled.validity()

                override fun visitSessionThreadTerminated(
                    sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData
                ) = sessionThreadTerminated.validity()

                override fun visitSessionOutcomeEvaluationEnded(
                    sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData
                ) = sessionOutcomeEvaluationEnded.validity()

                override fun visitVaultCreated(vaultCreated: BetaWebhookVaultCreatedEventData) =
                    vaultCreated.validity()

                override fun visitVaultArchived(vaultArchived: BetaWebhookVaultArchivedEventData) =
                    vaultArchived.validity()

                override fun visitVaultDeleted(vaultDeleted: BetaWebhookVaultDeletedEventData) =
                    vaultDeleted.validity()

                override fun visitVaultCredentialCreated(
                    vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData
                ) = vaultCredentialCreated.validity()

                override fun visitVaultCredentialArchived(
                    vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData
                ) = vaultCredentialArchived.validity()

                override fun visitVaultCredentialDeleted(
                    vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData
                ) = vaultCredentialDeleted.validity()

                override fun visitVaultCredentialRefreshFailed(
                    vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData
                ) = vaultCredentialRefreshFailed.validity()

                override fun visitSessionUpdated(
                    sessionUpdated: BetaWebhookSessionUpdatedEventData
                ) = sessionUpdated.validity()

                override fun visitAgentCreated(agentCreated: BetaWebhookAgentCreatedEventData) =
                    agentCreated.validity()

                override fun visitAgentArchived(agentArchived: BetaWebhookAgentArchivedEventData) =
                    agentArchived.validity()

                override fun visitAgentDeleted(agentDeleted: BetaWebhookAgentDeletedEventData) =
                    agentDeleted.validity()

                override fun visitDeploymentPaused(
                    deploymentPaused: BetaWebhookDeploymentPausedEventData
                ) = deploymentPaused.validity()

                override fun visitDeploymentRunFailed(
                    deploymentRunFailed: BetaWebhookDeploymentRunFailedEventData
                ) = deploymentRunFailed.validity()

                override fun visitDeploymentCreated(
                    deploymentCreated: BetaWebhookDeploymentCreatedEventData
                ) = deploymentCreated.validity()

                override fun visitDeploymentUpdated(
                    deploymentUpdated: BetaWebhookDeploymentUpdatedEventData
                ) = deploymentUpdated.validity()

                override fun visitDeploymentUnpaused(
                    deploymentUnpaused: BetaWebhookDeploymentUnpausedEventData
                ) = deploymentUnpaused.validity()

                override fun visitAgentUpdated(agentUpdated: BetaWebhookAgentUpdatedEventData) =
                    agentUpdated.validity()

                override fun visitDeploymentArchived(
                    deploymentArchived: BetaWebhookDeploymentArchivedEventData
                ) = deploymentArchived.validity()

                override fun visitDeploymentRunStarted(
                    deploymentRunStarted: BetaWebhookDeploymentRunStartedEventData
                ) = deploymentRunStarted.validity()

                override fun visitDeploymentDeleted(
                    deploymentDeleted: BetaWebhookDeploymentDeletedEventData
                ) = deploymentDeleted.validity()

                override fun visitDeploymentRunSucceeded(
                    deploymentRunSucceeded: BetaWebhookDeploymentRunSucceededEventData
                ) = deploymentRunSucceeded.validity()

                override fun visitEnvironmentCreated(
                    environmentCreated: BetaWebhookEnvironmentCreatedEventData
                ) = environmentCreated.validity()

                override fun visitEnvironmentUpdated(
                    environmentUpdated: BetaWebhookEnvironmentUpdatedEventData
                ) = environmentUpdated.validity()

                override fun visitEnvironmentArchived(
                    environmentArchived: BetaWebhookEnvironmentArchivedEventData
                ) = environmentArchived.validity()

                override fun visitEnvironmentDeleted(
                    environmentDeleted: BetaWebhookEnvironmentDeletedEventData
                ) = environmentDeleted.validity()

                override fun visitMemoryStoreCreated(
                    memoryStoreCreated: BetaWebhookMemoryStoreCreatedEventData
                ) = memoryStoreCreated.validity()

                override fun visitMemoryStoreArchived(
                    memoryStoreArchived: BetaWebhookMemoryStoreArchivedEventData
                ) = memoryStoreArchived.validity()

                override fun visitMemoryStoreDeleted(
                    memoryStoreDeleted: BetaWebhookMemoryStoreDeletedEventData
                ) = memoryStoreDeleted.validity()

                override fun visitSessionBudgetReached(
                    sessionBudgetReached: BetaWebhookSessionBudgetReachedEventData
                ) = sessionBudgetReached.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaWebhookEventData &&
            sessionCreated == other.sessionCreated &&
            sessionPending == other.sessionPending &&
            sessionRunning == other.sessionRunning &&
            sessionIdled == other.sessionIdled &&
            sessionRequiresAction == other.sessionRequiresAction &&
            sessionArchived == other.sessionArchived &&
            sessionDeleted == other.sessionDeleted &&
            sessionStatusRescheduled == other.sessionStatusRescheduled &&
            sessionStatusRunStarted == other.sessionStatusRunStarted &&
            sessionStatusIdled == other.sessionStatusIdled &&
            sessionStatusTerminated == other.sessionStatusTerminated &&
            sessionThreadCreated == other.sessionThreadCreated &&
            sessionThreadIdled == other.sessionThreadIdled &&
            sessionThreadTerminated == other.sessionThreadTerminated &&
            sessionOutcomeEvaluationEnded == other.sessionOutcomeEvaluationEnded &&
            vaultCreated == other.vaultCreated &&
            vaultArchived == other.vaultArchived &&
            vaultDeleted == other.vaultDeleted &&
            vaultCredentialCreated == other.vaultCredentialCreated &&
            vaultCredentialArchived == other.vaultCredentialArchived &&
            vaultCredentialDeleted == other.vaultCredentialDeleted &&
            vaultCredentialRefreshFailed == other.vaultCredentialRefreshFailed &&
            sessionUpdated == other.sessionUpdated &&
            agentCreated == other.agentCreated &&
            agentArchived == other.agentArchived &&
            agentDeleted == other.agentDeleted &&
            deploymentPaused == other.deploymentPaused &&
            deploymentRunFailed == other.deploymentRunFailed &&
            deploymentCreated == other.deploymentCreated &&
            deploymentUpdated == other.deploymentUpdated &&
            deploymentUnpaused == other.deploymentUnpaused &&
            agentUpdated == other.agentUpdated &&
            deploymentArchived == other.deploymentArchived &&
            deploymentRunStarted == other.deploymentRunStarted &&
            deploymentDeleted == other.deploymentDeleted &&
            deploymentRunSucceeded == other.deploymentRunSucceeded &&
            environmentCreated == other.environmentCreated &&
            environmentUpdated == other.environmentUpdated &&
            environmentArchived == other.environmentArchived &&
            environmentDeleted == other.environmentDeleted &&
            memoryStoreCreated == other.memoryStoreCreated &&
            memoryStoreArchived == other.memoryStoreArchived &&
            memoryStoreDeleted == other.memoryStoreDeleted &&
            sessionBudgetReached == other.sessionBudgetReached
    }

    override fun hashCode(): Int =
        Objects.hash(
            sessionCreated,
            sessionPending,
            sessionRunning,
            sessionIdled,
            sessionRequiresAction,
            sessionArchived,
            sessionDeleted,
            sessionStatusRescheduled,
            sessionStatusRunStarted,
            sessionStatusIdled,
            sessionStatusTerminated,
            sessionThreadCreated,
            sessionThreadIdled,
            sessionThreadTerminated,
            sessionOutcomeEvaluationEnded,
            vaultCreated,
            vaultArchived,
            vaultDeleted,
            vaultCredentialCreated,
            vaultCredentialArchived,
            vaultCredentialDeleted,
            vaultCredentialRefreshFailed,
            sessionUpdated,
            agentCreated,
            agentArchived,
            agentDeleted,
            deploymentPaused,
            deploymentRunFailed,
            deploymentCreated,
            deploymentUpdated,
            deploymentUnpaused,
            agentUpdated,
            deploymentArchived,
            deploymentRunStarted,
            deploymentDeleted,
            deploymentRunSucceeded,
            environmentCreated,
            environmentUpdated,
            environmentArchived,
            environmentDeleted,
            memoryStoreCreated,
            memoryStoreArchived,
            memoryStoreDeleted,
            sessionBudgetReached,
        )

    override fun toString(): String =
        when {
            sessionCreated != null -> "BetaWebhookEventData{sessionCreated=$sessionCreated}"
            sessionPending != null -> "BetaWebhookEventData{sessionPending=$sessionPending}"
            sessionRunning != null -> "BetaWebhookEventData{sessionRunning=$sessionRunning}"
            sessionIdled != null -> "BetaWebhookEventData{sessionIdled=$sessionIdled}"
            sessionRequiresAction != null ->
                "BetaWebhookEventData{sessionRequiresAction=$sessionRequiresAction}"
            sessionArchived != null -> "BetaWebhookEventData{sessionArchived=$sessionArchived}"
            sessionDeleted != null -> "BetaWebhookEventData{sessionDeleted=$sessionDeleted}"
            sessionStatusRescheduled != null ->
                "BetaWebhookEventData{sessionStatusRescheduled=$sessionStatusRescheduled}"
            sessionStatusRunStarted != null ->
                "BetaWebhookEventData{sessionStatusRunStarted=$sessionStatusRunStarted}"
            sessionStatusIdled != null ->
                "BetaWebhookEventData{sessionStatusIdled=$sessionStatusIdled}"
            sessionStatusTerminated != null ->
                "BetaWebhookEventData{sessionStatusTerminated=$sessionStatusTerminated}"
            sessionThreadCreated != null ->
                "BetaWebhookEventData{sessionThreadCreated=$sessionThreadCreated}"
            sessionThreadIdled != null ->
                "BetaWebhookEventData{sessionThreadIdled=$sessionThreadIdled}"
            sessionThreadTerminated != null ->
                "BetaWebhookEventData{sessionThreadTerminated=$sessionThreadTerminated}"
            sessionOutcomeEvaluationEnded != null ->
                "BetaWebhookEventData{sessionOutcomeEvaluationEnded=$sessionOutcomeEvaluationEnded}"
            vaultCreated != null -> "BetaWebhookEventData{vaultCreated=$vaultCreated}"
            vaultArchived != null -> "BetaWebhookEventData{vaultArchived=$vaultArchived}"
            vaultDeleted != null -> "BetaWebhookEventData{vaultDeleted=$vaultDeleted}"
            vaultCredentialCreated != null ->
                "BetaWebhookEventData{vaultCredentialCreated=$vaultCredentialCreated}"
            vaultCredentialArchived != null ->
                "BetaWebhookEventData{vaultCredentialArchived=$vaultCredentialArchived}"
            vaultCredentialDeleted != null ->
                "BetaWebhookEventData{vaultCredentialDeleted=$vaultCredentialDeleted}"
            vaultCredentialRefreshFailed != null ->
                "BetaWebhookEventData{vaultCredentialRefreshFailed=$vaultCredentialRefreshFailed}"
            sessionUpdated != null -> "BetaWebhookEventData{sessionUpdated=$sessionUpdated}"
            agentCreated != null -> "BetaWebhookEventData{agentCreated=$agentCreated}"
            agentArchived != null -> "BetaWebhookEventData{agentArchived=$agentArchived}"
            agentDeleted != null -> "BetaWebhookEventData{agentDeleted=$agentDeleted}"
            deploymentPaused != null -> "BetaWebhookEventData{deploymentPaused=$deploymentPaused}"
            deploymentRunFailed != null ->
                "BetaWebhookEventData{deploymentRunFailed=$deploymentRunFailed}"
            deploymentCreated != null ->
                "BetaWebhookEventData{deploymentCreated=$deploymentCreated}"
            deploymentUpdated != null ->
                "BetaWebhookEventData{deploymentUpdated=$deploymentUpdated}"
            deploymentUnpaused != null ->
                "BetaWebhookEventData{deploymentUnpaused=$deploymentUnpaused}"
            agentUpdated != null -> "BetaWebhookEventData{agentUpdated=$agentUpdated}"
            deploymentArchived != null ->
                "BetaWebhookEventData{deploymentArchived=$deploymentArchived}"
            deploymentRunStarted != null ->
                "BetaWebhookEventData{deploymentRunStarted=$deploymentRunStarted}"
            deploymentDeleted != null ->
                "BetaWebhookEventData{deploymentDeleted=$deploymentDeleted}"
            deploymentRunSucceeded != null ->
                "BetaWebhookEventData{deploymentRunSucceeded=$deploymentRunSucceeded}"
            environmentCreated != null ->
                "BetaWebhookEventData{environmentCreated=$environmentCreated}"
            environmentUpdated != null ->
                "BetaWebhookEventData{environmentUpdated=$environmentUpdated}"
            environmentArchived != null ->
                "BetaWebhookEventData{environmentArchived=$environmentArchived}"
            environmentDeleted != null ->
                "BetaWebhookEventData{environmentDeleted=$environmentDeleted}"
            memoryStoreCreated != null ->
                "BetaWebhookEventData{memoryStoreCreated=$memoryStoreCreated}"
            memoryStoreArchived != null ->
                "BetaWebhookEventData{memoryStoreArchived=$memoryStoreArchived}"
            memoryStoreDeleted != null ->
                "BetaWebhookEventData{memoryStoreDeleted=$memoryStoreDeleted}"
            sessionBudgetReached != null ->
                "BetaWebhookEventData{sessionBudgetReached=$sessionBudgetReached}"
            _json != null -> "BetaWebhookEventData{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaWebhookEventData")
        }

    companion object {

        @JvmStatic
        fun ofSessionCreated(sessionCreated: BetaWebhookSessionCreatedEventData) =
            BetaWebhookEventData(sessionCreated = sessionCreated)

        @JvmStatic
        fun ofSessionPending(sessionPending: BetaWebhookSessionPendingEventData) =
            BetaWebhookEventData(sessionPending = sessionPending)

        @JvmStatic
        fun ofSessionRunning(sessionRunning: BetaWebhookSessionRunningEventData) =
            BetaWebhookEventData(sessionRunning = sessionRunning)

        @JvmStatic
        fun ofSessionIdled(sessionIdled: BetaWebhookSessionIdledEventData) =
            BetaWebhookEventData(sessionIdled = sessionIdled)

        @JvmStatic
        fun ofSessionRequiresAction(
            sessionRequiresAction: BetaWebhookSessionRequiresActionEventData
        ) = BetaWebhookEventData(sessionRequiresAction = sessionRequiresAction)

        @JvmStatic
        fun ofSessionArchived(sessionArchived: BetaWebhookSessionArchivedEventData) =
            BetaWebhookEventData(sessionArchived = sessionArchived)

        @JvmStatic
        fun ofSessionDeleted(sessionDeleted: BetaWebhookSessionDeletedEventData) =
            BetaWebhookEventData(sessionDeleted = sessionDeleted)

        @JvmStatic
        fun ofSessionStatusRescheduled(
            sessionStatusRescheduled: BetaWebhookSessionStatusRescheduledEventData
        ) = BetaWebhookEventData(sessionStatusRescheduled = sessionStatusRescheduled)

        @JvmStatic
        fun ofSessionStatusRunStarted(
            sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData
        ) = BetaWebhookEventData(sessionStatusRunStarted = sessionStatusRunStarted)

        @JvmStatic
        fun ofSessionStatusIdled(sessionStatusIdled: BetaWebhookSessionStatusIdledEventData) =
            BetaWebhookEventData(sessionStatusIdled = sessionStatusIdled)

        @JvmStatic
        fun ofSessionStatusTerminated(
            sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData
        ) = BetaWebhookEventData(sessionStatusTerminated = sessionStatusTerminated)

        @JvmStatic
        fun ofSessionThreadCreated(sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData) =
            BetaWebhookEventData(sessionThreadCreated = sessionThreadCreated)

        @JvmStatic
        fun ofSessionThreadIdled(sessionThreadIdled: BetaWebhookSessionThreadIdledEventData) =
            BetaWebhookEventData(sessionThreadIdled = sessionThreadIdled)

        @JvmStatic
        fun ofSessionThreadTerminated(
            sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData
        ) = BetaWebhookEventData(sessionThreadTerminated = sessionThreadTerminated)

        @JvmStatic
        fun ofSessionOutcomeEvaluationEnded(
            sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData
        ) = BetaWebhookEventData(sessionOutcomeEvaluationEnded = sessionOutcomeEvaluationEnded)

        @JvmStatic
        fun ofVaultCreated(vaultCreated: BetaWebhookVaultCreatedEventData) =
            BetaWebhookEventData(vaultCreated = vaultCreated)

        @JvmStatic
        fun ofVaultArchived(vaultArchived: BetaWebhookVaultArchivedEventData) =
            BetaWebhookEventData(vaultArchived = vaultArchived)

        @JvmStatic
        fun ofVaultDeleted(vaultDeleted: BetaWebhookVaultDeletedEventData) =
            BetaWebhookEventData(vaultDeleted = vaultDeleted)

        @JvmStatic
        fun ofVaultCredentialCreated(
            vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData
        ) = BetaWebhookEventData(vaultCredentialCreated = vaultCredentialCreated)

        @JvmStatic
        fun ofVaultCredentialArchived(
            vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData
        ) = BetaWebhookEventData(vaultCredentialArchived = vaultCredentialArchived)

        @JvmStatic
        fun ofVaultCredentialDeleted(
            vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData
        ) = BetaWebhookEventData(vaultCredentialDeleted = vaultCredentialDeleted)

        @JvmStatic
        fun ofVaultCredentialRefreshFailed(
            vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData
        ) = BetaWebhookEventData(vaultCredentialRefreshFailed = vaultCredentialRefreshFailed)

        @JvmStatic
        fun ofSessionUpdated(sessionUpdated: BetaWebhookSessionUpdatedEventData) =
            BetaWebhookEventData(sessionUpdated = sessionUpdated)

        @JvmStatic
        fun ofAgentCreated(agentCreated: BetaWebhookAgentCreatedEventData) =
            BetaWebhookEventData(agentCreated = agentCreated)

        @JvmStatic
        fun ofAgentArchived(agentArchived: BetaWebhookAgentArchivedEventData) =
            BetaWebhookEventData(agentArchived = agentArchived)

        @JvmStatic
        fun ofAgentDeleted(agentDeleted: BetaWebhookAgentDeletedEventData) =
            BetaWebhookEventData(agentDeleted = agentDeleted)

        @JvmStatic
        fun ofDeploymentPaused(deploymentPaused: BetaWebhookDeploymentPausedEventData) =
            BetaWebhookEventData(deploymentPaused = deploymentPaused)

        @JvmStatic
        fun ofDeploymentRunFailed(deploymentRunFailed: BetaWebhookDeploymentRunFailedEventData) =
            BetaWebhookEventData(deploymentRunFailed = deploymentRunFailed)

        @JvmStatic
        fun ofDeploymentCreated(deploymentCreated: BetaWebhookDeploymentCreatedEventData) =
            BetaWebhookEventData(deploymentCreated = deploymentCreated)

        @JvmStatic
        fun ofDeploymentUpdated(deploymentUpdated: BetaWebhookDeploymentUpdatedEventData) =
            BetaWebhookEventData(deploymentUpdated = deploymentUpdated)

        @JvmStatic
        fun ofDeploymentUnpaused(deploymentUnpaused: BetaWebhookDeploymentUnpausedEventData) =
            BetaWebhookEventData(deploymentUnpaused = deploymentUnpaused)

        @JvmStatic
        fun ofAgentUpdated(agentUpdated: BetaWebhookAgentUpdatedEventData) =
            BetaWebhookEventData(agentUpdated = agentUpdated)

        @JvmStatic
        fun ofDeploymentArchived(deploymentArchived: BetaWebhookDeploymentArchivedEventData) =
            BetaWebhookEventData(deploymentArchived = deploymentArchived)

        @JvmStatic
        fun ofDeploymentRunStarted(deploymentRunStarted: BetaWebhookDeploymentRunStartedEventData) =
            BetaWebhookEventData(deploymentRunStarted = deploymentRunStarted)

        @JvmStatic
        fun ofDeploymentDeleted(deploymentDeleted: BetaWebhookDeploymentDeletedEventData) =
            BetaWebhookEventData(deploymentDeleted = deploymentDeleted)

        @JvmStatic
        fun ofDeploymentRunSucceeded(
            deploymentRunSucceeded: BetaWebhookDeploymentRunSucceededEventData
        ) = BetaWebhookEventData(deploymentRunSucceeded = deploymentRunSucceeded)

        @JvmStatic
        fun ofEnvironmentCreated(environmentCreated: BetaWebhookEnvironmentCreatedEventData) =
            BetaWebhookEventData(environmentCreated = environmentCreated)

        @JvmStatic
        fun ofEnvironmentUpdated(environmentUpdated: BetaWebhookEnvironmentUpdatedEventData) =
            BetaWebhookEventData(environmentUpdated = environmentUpdated)

        @JvmStatic
        fun ofEnvironmentArchived(environmentArchived: BetaWebhookEnvironmentArchivedEventData) =
            BetaWebhookEventData(environmentArchived = environmentArchived)

        @JvmStatic
        fun ofEnvironmentDeleted(environmentDeleted: BetaWebhookEnvironmentDeletedEventData) =
            BetaWebhookEventData(environmentDeleted = environmentDeleted)

        @JvmStatic
        fun ofMemoryStoreCreated(memoryStoreCreated: BetaWebhookMemoryStoreCreatedEventData) =
            BetaWebhookEventData(memoryStoreCreated = memoryStoreCreated)

        @JvmStatic
        fun ofMemoryStoreArchived(memoryStoreArchived: BetaWebhookMemoryStoreArchivedEventData) =
            BetaWebhookEventData(memoryStoreArchived = memoryStoreArchived)

        @JvmStatic
        fun ofMemoryStoreDeleted(memoryStoreDeleted: BetaWebhookMemoryStoreDeletedEventData) =
            BetaWebhookEventData(memoryStoreDeleted = memoryStoreDeleted)

        @JvmStatic
        fun ofSessionBudgetReached(sessionBudgetReached: BetaWebhookSessionBudgetReachedEventData) =
            BetaWebhookEventData(sessionBudgetReached = sessionBudgetReached)
    }

    /**
     * An interface that defines how to map each variant of [BetaWebhookEventData] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitSessionCreated(sessionCreated: BetaWebhookSessionCreatedEventData): T

        fun visitSessionPending(sessionPending: BetaWebhookSessionPendingEventData): T

        fun visitSessionRunning(sessionRunning: BetaWebhookSessionRunningEventData): T

        fun visitSessionIdled(sessionIdled: BetaWebhookSessionIdledEventData): T

        fun visitSessionRequiresAction(
            sessionRequiresAction: BetaWebhookSessionRequiresActionEventData
        ): T

        fun visitSessionArchived(sessionArchived: BetaWebhookSessionArchivedEventData): T

        fun visitSessionDeleted(sessionDeleted: BetaWebhookSessionDeletedEventData): T

        fun visitSessionStatusRescheduled(
            sessionStatusRescheduled: BetaWebhookSessionStatusRescheduledEventData
        ): T

        fun visitSessionStatusRunStarted(
            sessionStatusRunStarted: BetaWebhookSessionStatusRunStartedEventData
        ): T

        fun visitSessionStatusIdled(sessionStatusIdled: BetaWebhookSessionStatusIdledEventData): T

        fun visitSessionStatusTerminated(
            sessionStatusTerminated: BetaWebhookSessionStatusTerminatedEventData
        ): T

        fun visitSessionThreadCreated(
            sessionThreadCreated: BetaWebhookSessionThreadCreatedEventData
        ): T

        fun visitSessionThreadIdled(sessionThreadIdled: BetaWebhookSessionThreadIdledEventData): T

        fun visitSessionThreadTerminated(
            sessionThreadTerminated: BetaWebhookSessionThreadTerminatedEventData
        ): T

        fun visitSessionOutcomeEvaluationEnded(
            sessionOutcomeEvaluationEnded: BetaWebhookSessionOutcomeEvaluationEndedEventData
        ): T

        fun visitVaultCreated(vaultCreated: BetaWebhookVaultCreatedEventData): T

        fun visitVaultArchived(vaultArchived: BetaWebhookVaultArchivedEventData): T

        fun visitVaultDeleted(vaultDeleted: BetaWebhookVaultDeletedEventData): T

        fun visitVaultCredentialCreated(
            vaultCredentialCreated: BetaWebhookVaultCredentialCreatedEventData
        ): T

        fun visitVaultCredentialArchived(
            vaultCredentialArchived: BetaWebhookVaultCredentialArchivedEventData
        ): T

        fun visitVaultCredentialDeleted(
            vaultCredentialDeleted: BetaWebhookVaultCredentialDeletedEventData
        ): T

        fun visitVaultCredentialRefreshFailed(
            vaultCredentialRefreshFailed: BetaWebhookVaultCredentialRefreshFailedEventData
        ): T

        fun visitSessionUpdated(sessionUpdated: BetaWebhookSessionUpdatedEventData): T

        fun visitAgentCreated(agentCreated: BetaWebhookAgentCreatedEventData): T

        fun visitAgentArchived(agentArchived: BetaWebhookAgentArchivedEventData): T

        fun visitAgentDeleted(agentDeleted: BetaWebhookAgentDeletedEventData): T

        fun visitDeploymentPaused(deploymentPaused: BetaWebhookDeploymentPausedEventData): T

        fun visitDeploymentRunFailed(
            deploymentRunFailed: BetaWebhookDeploymentRunFailedEventData
        ): T

        fun visitDeploymentCreated(deploymentCreated: BetaWebhookDeploymentCreatedEventData): T

        fun visitDeploymentUpdated(deploymentUpdated: BetaWebhookDeploymentUpdatedEventData): T

        fun visitDeploymentUnpaused(deploymentUnpaused: BetaWebhookDeploymentUnpausedEventData): T

        fun visitAgentUpdated(agentUpdated: BetaWebhookAgentUpdatedEventData): T

        fun visitDeploymentArchived(deploymentArchived: BetaWebhookDeploymentArchivedEventData): T

        fun visitDeploymentRunStarted(
            deploymentRunStarted: BetaWebhookDeploymentRunStartedEventData
        ): T

        fun visitDeploymentDeleted(deploymentDeleted: BetaWebhookDeploymentDeletedEventData): T

        fun visitDeploymentRunSucceeded(
            deploymentRunSucceeded: BetaWebhookDeploymentRunSucceededEventData
        ): T

        fun visitEnvironmentCreated(environmentCreated: BetaWebhookEnvironmentCreatedEventData): T

        fun visitEnvironmentUpdated(environmentUpdated: BetaWebhookEnvironmentUpdatedEventData): T

        fun visitEnvironmentArchived(
            environmentArchived: BetaWebhookEnvironmentArchivedEventData
        ): T

        fun visitEnvironmentDeleted(environmentDeleted: BetaWebhookEnvironmentDeletedEventData): T

        fun visitMemoryStoreCreated(memoryStoreCreated: BetaWebhookMemoryStoreCreatedEventData): T

        fun visitMemoryStoreArchived(
            memoryStoreArchived: BetaWebhookMemoryStoreArchivedEventData
        ): T

        fun visitMemoryStoreDeleted(memoryStoreDeleted: BetaWebhookMemoryStoreDeletedEventData): T

        fun visitSessionBudgetReached(
            sessionBudgetReached: BetaWebhookSessionBudgetReachedEventData
        ): T

        /**
         * Maps an unknown variant of [BetaWebhookEventData] to a value of type [T].
         *
         * An instance of [BetaWebhookEventData] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaWebhookEventData: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaWebhookEventData>(BetaWebhookEventData::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaWebhookEventData {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "session.created" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionCreatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionCreated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.pending" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionPendingEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionPending = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.running" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionRunningEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionRunning = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.idled" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebhookSessionIdledEventData>())
                        ?.let { BetaWebhookEventData(sessionIdled = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.requires_action" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionRequiresActionEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionRequiresAction = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.archived" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionArchivedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionArchived = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.deleted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionDeletedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionDeleted = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.status_rescheduled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionStatusRescheduledEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionStatusRescheduled = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.status_run_started" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionStatusRunStartedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionStatusRunStarted = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.status_idled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionStatusIdledEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionStatusIdled = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.status_terminated" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionStatusTerminatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionStatusTerminated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.thread_created" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionThreadCreatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionThreadCreated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.thread_idled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionThreadIdledEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionThreadIdled = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.thread_terminated" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionThreadTerminatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionThreadTerminated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.outcome_evaluation_ended" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionOutcomeEvaluationEndedEventData>(),
                        )
                        ?.let {
                            BetaWebhookEventData(sessionOutcomeEvaluationEnded = it, _json = json)
                        } ?: BetaWebhookEventData(_json = json)
                }
                "vault.created" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebhookVaultCreatedEventData>())
                        ?.let { BetaWebhookEventData(vaultCreated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "vault.archived" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebhookVaultArchivedEventData>())
                        ?.let { BetaWebhookEventData(vaultArchived = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "vault.deleted" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebhookVaultDeletedEventData>())
                        ?.let { BetaWebhookEventData(vaultDeleted = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "vault_credential.created" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookVaultCredentialCreatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(vaultCredentialCreated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "vault_credential.archived" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookVaultCredentialArchivedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(vaultCredentialArchived = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "vault_credential.deleted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookVaultCredentialDeletedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(vaultCredentialDeleted = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "vault_credential.refresh_failed" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookVaultCredentialRefreshFailedEventData>(),
                        )
                        ?.let {
                            BetaWebhookEventData(vaultCredentialRefreshFailed = it, _json = json)
                        } ?: BetaWebhookEventData(_json = json)
                }
                "session.updated" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionUpdatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionUpdated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "agent.created" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebhookAgentCreatedEventData>())
                        ?.let { BetaWebhookEventData(agentCreated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "agent.archived" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebhookAgentArchivedEventData>())
                        ?.let { BetaWebhookEventData(agentArchived = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "agent.deleted" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebhookAgentDeletedEventData>())
                        ?.let { BetaWebhookEventData(agentDeleted = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "deployment.paused" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookDeploymentPausedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(deploymentPaused = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "deployment_run.failed" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookDeploymentRunFailedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(deploymentRunFailed = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "deployment.created" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookDeploymentCreatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(deploymentCreated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "deployment.updated" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookDeploymentUpdatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(deploymentUpdated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "deployment.unpaused" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookDeploymentUnpausedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(deploymentUnpaused = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "agent.updated" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaWebhookAgentUpdatedEventData>())
                        ?.let { BetaWebhookEventData(agentUpdated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "deployment.archived" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookDeploymentArchivedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(deploymentArchived = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "deployment_run.started" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookDeploymentRunStartedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(deploymentRunStarted = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "deployment.deleted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookDeploymentDeletedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(deploymentDeleted = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "deployment_run.succeeded" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookDeploymentRunSucceededEventData>(),
                        )
                        ?.let { BetaWebhookEventData(deploymentRunSucceeded = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "environment.created" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookEnvironmentCreatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(environmentCreated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "environment.updated" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookEnvironmentUpdatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(environmentUpdated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "environment.archived" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookEnvironmentArchivedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(environmentArchived = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "environment.deleted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookEnvironmentDeletedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(environmentDeleted = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "memory_store.created" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookMemoryStoreCreatedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(memoryStoreCreated = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "memory_store.archived" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookMemoryStoreArchivedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(memoryStoreArchived = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "memory_store.deleted" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookMemoryStoreDeletedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(memoryStoreDeleted = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
                "session.budget_reached" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaWebhookSessionBudgetReachedEventData>(),
                        )
                        ?.let { BetaWebhookEventData(sessionBudgetReached = it, _json = json) }
                        ?: BetaWebhookEventData(_json = json)
                }
            }

            return BetaWebhookEventData(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaWebhookEventData>(BetaWebhookEventData::class) {

        override fun serialize(
            value: BetaWebhookEventData,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.sessionCreated != null -> generator.writeObject(value.sessionCreated)
                value.sessionPending != null -> generator.writeObject(value.sessionPending)
                value.sessionRunning != null -> generator.writeObject(value.sessionRunning)
                value.sessionIdled != null -> generator.writeObject(value.sessionIdled)
                value.sessionRequiresAction != null ->
                    generator.writeObject(value.sessionRequiresAction)
                value.sessionArchived != null -> generator.writeObject(value.sessionArchived)
                value.sessionDeleted != null -> generator.writeObject(value.sessionDeleted)
                value.sessionStatusRescheduled != null ->
                    generator.writeObject(value.sessionStatusRescheduled)
                value.sessionStatusRunStarted != null ->
                    generator.writeObject(value.sessionStatusRunStarted)
                value.sessionStatusIdled != null -> generator.writeObject(value.sessionStatusIdled)
                value.sessionStatusTerminated != null ->
                    generator.writeObject(value.sessionStatusTerminated)
                value.sessionThreadCreated != null ->
                    generator.writeObject(value.sessionThreadCreated)
                value.sessionThreadIdled != null -> generator.writeObject(value.sessionThreadIdled)
                value.sessionThreadTerminated != null ->
                    generator.writeObject(value.sessionThreadTerminated)
                value.sessionOutcomeEvaluationEnded != null ->
                    generator.writeObject(value.sessionOutcomeEvaluationEnded)
                value.vaultCreated != null -> generator.writeObject(value.vaultCreated)
                value.vaultArchived != null -> generator.writeObject(value.vaultArchived)
                value.vaultDeleted != null -> generator.writeObject(value.vaultDeleted)
                value.vaultCredentialCreated != null ->
                    generator.writeObject(value.vaultCredentialCreated)
                value.vaultCredentialArchived != null ->
                    generator.writeObject(value.vaultCredentialArchived)
                value.vaultCredentialDeleted != null ->
                    generator.writeObject(value.vaultCredentialDeleted)
                value.vaultCredentialRefreshFailed != null ->
                    generator.writeObject(value.vaultCredentialRefreshFailed)
                value.sessionUpdated != null -> generator.writeObject(value.sessionUpdated)
                value.agentCreated != null -> generator.writeObject(value.agentCreated)
                value.agentArchived != null -> generator.writeObject(value.agentArchived)
                value.agentDeleted != null -> generator.writeObject(value.agentDeleted)
                value.deploymentPaused != null -> generator.writeObject(value.deploymentPaused)
                value.deploymentRunFailed != null ->
                    generator.writeObject(value.deploymentRunFailed)
                value.deploymentCreated != null -> generator.writeObject(value.deploymentCreated)
                value.deploymentUpdated != null -> generator.writeObject(value.deploymentUpdated)
                value.deploymentUnpaused != null -> generator.writeObject(value.deploymentUnpaused)
                value.agentUpdated != null -> generator.writeObject(value.agentUpdated)
                value.deploymentArchived != null -> generator.writeObject(value.deploymentArchived)
                value.deploymentRunStarted != null ->
                    generator.writeObject(value.deploymentRunStarted)
                value.deploymentDeleted != null -> generator.writeObject(value.deploymentDeleted)
                value.deploymentRunSucceeded != null ->
                    generator.writeObject(value.deploymentRunSucceeded)
                value.environmentCreated != null -> generator.writeObject(value.environmentCreated)
                value.environmentUpdated != null -> generator.writeObject(value.environmentUpdated)
                value.environmentArchived != null ->
                    generator.writeObject(value.environmentArchived)
                value.environmentDeleted != null -> generator.writeObject(value.environmentDeleted)
                value.memoryStoreCreated != null -> generator.writeObject(value.memoryStoreCreated)
                value.memoryStoreArchived != null ->
                    generator.writeObject(value.memoryStoreArchived)
                value.memoryStoreDeleted != null -> generator.writeObject(value.memoryStoreDeleted)
                value.sessionBudgetReached != null ->
                    generator.writeObject(value.sessionBudgetReached)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaWebhookEventData")
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

            @JvmField val SESSION_CREATED = of("session.created")

            @JvmField val SESSION_PENDING = of("session.pending")

            @JvmField val SESSION_RUNNING = of("session.running")

            @JvmField val SESSION_IDLED = of("session.idled")

            @JvmField val SESSION_REQUIRES_ACTION = of("session.requires_action")

            @JvmField val SESSION_ARCHIVED = of("session.archived")

            @JvmField val SESSION_DELETED = of("session.deleted")

            @JvmField val SESSION_STATUS_RESCHEDULED = of("session.status_rescheduled")

            @JvmField val SESSION_STATUS_RUN_STARTED = of("session.status_run_started")

            @JvmField val SESSION_STATUS_IDLED = of("session.status_idled")

            @JvmField val SESSION_STATUS_TERMINATED = of("session.status_terminated")

            @JvmField val SESSION_THREAD_CREATED = of("session.thread_created")

            @JvmField val SESSION_THREAD_IDLED = of("session.thread_idled")

            @JvmField val SESSION_THREAD_TERMINATED = of("session.thread_terminated")

            @JvmField val SESSION_OUTCOME_EVALUATION_ENDED = of("session.outcome_evaluation_ended")

            @JvmField val VAULT_CREATED = of("vault.created")

            @JvmField val VAULT_ARCHIVED = of("vault.archived")

            @JvmField val VAULT_DELETED = of("vault.deleted")

            @JvmField val VAULT_CREDENTIAL_CREATED = of("vault_credential.created")

            @JvmField val VAULT_CREDENTIAL_ARCHIVED = of("vault_credential.archived")

            @JvmField val VAULT_CREDENTIAL_DELETED = of("vault_credential.deleted")

            @JvmField val VAULT_CREDENTIAL_REFRESH_FAILED = of("vault_credential.refresh_failed")

            @JvmField val SESSION_UPDATED = of("session.updated")

            @JvmField val AGENT_CREATED = of("agent.created")

            @JvmField val AGENT_ARCHIVED = of("agent.archived")

            @JvmField val AGENT_DELETED = of("agent.deleted")

            @JvmField val DEPLOYMENT_PAUSED = of("deployment.paused")

            @JvmField val DEPLOYMENT_RUN_FAILED = of("deployment_run.failed")

            @JvmField val DEPLOYMENT_CREATED = of("deployment.created")

            @JvmField val DEPLOYMENT_UPDATED = of("deployment.updated")

            @JvmField val DEPLOYMENT_UNPAUSED = of("deployment.unpaused")

            @JvmField val AGENT_UPDATED = of("agent.updated")

            @JvmField val DEPLOYMENT_ARCHIVED = of("deployment.archived")

            @JvmField val DEPLOYMENT_RUN_STARTED = of("deployment_run.started")

            @JvmField val DEPLOYMENT_DELETED = of("deployment.deleted")

            @JvmField val DEPLOYMENT_RUN_SUCCEEDED = of("deployment_run.succeeded")

            @JvmField val ENVIRONMENT_CREATED = of("environment.created")

            @JvmField val ENVIRONMENT_UPDATED = of("environment.updated")

            @JvmField val ENVIRONMENT_ARCHIVED = of("environment.archived")

            @JvmField val ENVIRONMENT_DELETED = of("environment.deleted")

            @JvmField val MEMORY_STORE_CREATED = of("memory_store.created")

            @JvmField val MEMORY_STORE_ARCHIVED = of("memory_store.archived")

            @JvmField val MEMORY_STORE_DELETED = of("memory_store.deleted")

            @JvmField val SESSION_BUDGET_REACHED = of("session.budget_reached")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            SESSION_CREATED,
            SESSION_PENDING,
            SESSION_RUNNING,
            SESSION_IDLED,
            SESSION_REQUIRES_ACTION,
            SESSION_ARCHIVED,
            SESSION_DELETED,
            SESSION_STATUS_RESCHEDULED,
            SESSION_STATUS_RUN_STARTED,
            SESSION_STATUS_IDLED,
            SESSION_STATUS_TERMINATED,
            SESSION_THREAD_CREATED,
            SESSION_THREAD_IDLED,
            SESSION_THREAD_TERMINATED,
            SESSION_OUTCOME_EVALUATION_ENDED,
            VAULT_CREATED,
            VAULT_ARCHIVED,
            VAULT_DELETED,
            VAULT_CREDENTIAL_CREATED,
            VAULT_CREDENTIAL_ARCHIVED,
            VAULT_CREDENTIAL_DELETED,
            VAULT_CREDENTIAL_REFRESH_FAILED,
            SESSION_UPDATED,
            AGENT_CREATED,
            AGENT_ARCHIVED,
            AGENT_DELETED,
            DEPLOYMENT_PAUSED,
            DEPLOYMENT_RUN_FAILED,
            DEPLOYMENT_CREATED,
            DEPLOYMENT_UPDATED,
            DEPLOYMENT_UNPAUSED,
            AGENT_UPDATED,
            DEPLOYMENT_ARCHIVED,
            DEPLOYMENT_RUN_STARTED,
            DEPLOYMENT_DELETED,
            DEPLOYMENT_RUN_SUCCEEDED,
            ENVIRONMENT_CREATED,
            ENVIRONMENT_UPDATED,
            ENVIRONMENT_ARCHIVED,
            ENVIRONMENT_DELETED,
            MEMORY_STORE_CREATED,
            MEMORY_STORE_ARCHIVED,
            MEMORY_STORE_DELETED,
            SESSION_BUDGET_REACHED,
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
            SESSION_CREATED,
            SESSION_PENDING,
            SESSION_RUNNING,
            SESSION_IDLED,
            SESSION_REQUIRES_ACTION,
            SESSION_ARCHIVED,
            SESSION_DELETED,
            SESSION_STATUS_RESCHEDULED,
            SESSION_STATUS_RUN_STARTED,
            SESSION_STATUS_IDLED,
            SESSION_STATUS_TERMINATED,
            SESSION_THREAD_CREATED,
            SESSION_THREAD_IDLED,
            SESSION_THREAD_TERMINATED,
            SESSION_OUTCOME_EVALUATION_ENDED,
            VAULT_CREATED,
            VAULT_ARCHIVED,
            VAULT_DELETED,
            VAULT_CREDENTIAL_CREATED,
            VAULT_CREDENTIAL_ARCHIVED,
            VAULT_CREDENTIAL_DELETED,
            VAULT_CREDENTIAL_REFRESH_FAILED,
            SESSION_UPDATED,
            AGENT_CREATED,
            AGENT_ARCHIVED,
            AGENT_DELETED,
            DEPLOYMENT_PAUSED,
            DEPLOYMENT_RUN_FAILED,
            DEPLOYMENT_CREATED,
            DEPLOYMENT_UPDATED,
            DEPLOYMENT_UNPAUSED,
            AGENT_UPDATED,
            DEPLOYMENT_ARCHIVED,
            DEPLOYMENT_RUN_STARTED,
            DEPLOYMENT_DELETED,
            DEPLOYMENT_RUN_SUCCEEDED,
            ENVIRONMENT_CREATED,
            ENVIRONMENT_UPDATED,
            ENVIRONMENT_ARCHIVED,
            ENVIRONMENT_DELETED,
            MEMORY_STORE_CREATED,
            MEMORY_STORE_ARCHIVED,
            MEMORY_STORE_DELETED,
            SESSION_BUDGET_REACHED,
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
                SESSION_CREATED -> Value.SESSION_CREATED
                SESSION_PENDING -> Value.SESSION_PENDING
                SESSION_RUNNING -> Value.SESSION_RUNNING
                SESSION_IDLED -> Value.SESSION_IDLED
                SESSION_REQUIRES_ACTION -> Value.SESSION_REQUIRES_ACTION
                SESSION_ARCHIVED -> Value.SESSION_ARCHIVED
                SESSION_DELETED -> Value.SESSION_DELETED
                SESSION_STATUS_RESCHEDULED -> Value.SESSION_STATUS_RESCHEDULED
                SESSION_STATUS_RUN_STARTED -> Value.SESSION_STATUS_RUN_STARTED
                SESSION_STATUS_IDLED -> Value.SESSION_STATUS_IDLED
                SESSION_STATUS_TERMINATED -> Value.SESSION_STATUS_TERMINATED
                SESSION_THREAD_CREATED -> Value.SESSION_THREAD_CREATED
                SESSION_THREAD_IDLED -> Value.SESSION_THREAD_IDLED
                SESSION_THREAD_TERMINATED -> Value.SESSION_THREAD_TERMINATED
                SESSION_OUTCOME_EVALUATION_ENDED -> Value.SESSION_OUTCOME_EVALUATION_ENDED
                VAULT_CREATED -> Value.VAULT_CREATED
                VAULT_ARCHIVED -> Value.VAULT_ARCHIVED
                VAULT_DELETED -> Value.VAULT_DELETED
                VAULT_CREDENTIAL_CREATED -> Value.VAULT_CREDENTIAL_CREATED
                VAULT_CREDENTIAL_ARCHIVED -> Value.VAULT_CREDENTIAL_ARCHIVED
                VAULT_CREDENTIAL_DELETED -> Value.VAULT_CREDENTIAL_DELETED
                VAULT_CREDENTIAL_REFRESH_FAILED -> Value.VAULT_CREDENTIAL_REFRESH_FAILED
                SESSION_UPDATED -> Value.SESSION_UPDATED
                AGENT_CREATED -> Value.AGENT_CREATED
                AGENT_ARCHIVED -> Value.AGENT_ARCHIVED
                AGENT_DELETED -> Value.AGENT_DELETED
                DEPLOYMENT_PAUSED -> Value.DEPLOYMENT_PAUSED
                DEPLOYMENT_RUN_FAILED -> Value.DEPLOYMENT_RUN_FAILED
                DEPLOYMENT_CREATED -> Value.DEPLOYMENT_CREATED
                DEPLOYMENT_UPDATED -> Value.DEPLOYMENT_UPDATED
                DEPLOYMENT_UNPAUSED -> Value.DEPLOYMENT_UNPAUSED
                AGENT_UPDATED -> Value.AGENT_UPDATED
                DEPLOYMENT_ARCHIVED -> Value.DEPLOYMENT_ARCHIVED
                DEPLOYMENT_RUN_STARTED -> Value.DEPLOYMENT_RUN_STARTED
                DEPLOYMENT_DELETED -> Value.DEPLOYMENT_DELETED
                DEPLOYMENT_RUN_SUCCEEDED -> Value.DEPLOYMENT_RUN_SUCCEEDED
                ENVIRONMENT_CREATED -> Value.ENVIRONMENT_CREATED
                ENVIRONMENT_UPDATED -> Value.ENVIRONMENT_UPDATED
                ENVIRONMENT_ARCHIVED -> Value.ENVIRONMENT_ARCHIVED
                ENVIRONMENT_DELETED -> Value.ENVIRONMENT_DELETED
                MEMORY_STORE_CREATED -> Value.MEMORY_STORE_CREATED
                MEMORY_STORE_ARCHIVED -> Value.MEMORY_STORE_ARCHIVED
                MEMORY_STORE_DELETED -> Value.MEMORY_STORE_DELETED
                SESSION_BUDGET_REACHED -> Value.SESSION_BUDGET_REACHED
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
                SESSION_CREATED -> Known.SESSION_CREATED
                SESSION_PENDING -> Known.SESSION_PENDING
                SESSION_RUNNING -> Known.SESSION_RUNNING
                SESSION_IDLED -> Known.SESSION_IDLED
                SESSION_REQUIRES_ACTION -> Known.SESSION_REQUIRES_ACTION
                SESSION_ARCHIVED -> Known.SESSION_ARCHIVED
                SESSION_DELETED -> Known.SESSION_DELETED
                SESSION_STATUS_RESCHEDULED -> Known.SESSION_STATUS_RESCHEDULED
                SESSION_STATUS_RUN_STARTED -> Known.SESSION_STATUS_RUN_STARTED
                SESSION_STATUS_IDLED -> Known.SESSION_STATUS_IDLED
                SESSION_STATUS_TERMINATED -> Known.SESSION_STATUS_TERMINATED
                SESSION_THREAD_CREATED -> Known.SESSION_THREAD_CREATED
                SESSION_THREAD_IDLED -> Known.SESSION_THREAD_IDLED
                SESSION_THREAD_TERMINATED -> Known.SESSION_THREAD_TERMINATED
                SESSION_OUTCOME_EVALUATION_ENDED -> Known.SESSION_OUTCOME_EVALUATION_ENDED
                VAULT_CREATED -> Known.VAULT_CREATED
                VAULT_ARCHIVED -> Known.VAULT_ARCHIVED
                VAULT_DELETED -> Known.VAULT_DELETED
                VAULT_CREDENTIAL_CREATED -> Known.VAULT_CREDENTIAL_CREATED
                VAULT_CREDENTIAL_ARCHIVED -> Known.VAULT_CREDENTIAL_ARCHIVED
                VAULT_CREDENTIAL_DELETED -> Known.VAULT_CREDENTIAL_DELETED
                VAULT_CREDENTIAL_REFRESH_FAILED -> Known.VAULT_CREDENTIAL_REFRESH_FAILED
                SESSION_UPDATED -> Known.SESSION_UPDATED
                AGENT_CREATED -> Known.AGENT_CREATED
                AGENT_ARCHIVED -> Known.AGENT_ARCHIVED
                AGENT_DELETED -> Known.AGENT_DELETED
                DEPLOYMENT_PAUSED -> Known.DEPLOYMENT_PAUSED
                DEPLOYMENT_RUN_FAILED -> Known.DEPLOYMENT_RUN_FAILED
                DEPLOYMENT_CREATED -> Known.DEPLOYMENT_CREATED
                DEPLOYMENT_UPDATED -> Known.DEPLOYMENT_UPDATED
                DEPLOYMENT_UNPAUSED -> Known.DEPLOYMENT_UNPAUSED
                AGENT_UPDATED -> Known.AGENT_UPDATED
                DEPLOYMENT_ARCHIVED -> Known.DEPLOYMENT_ARCHIVED
                DEPLOYMENT_RUN_STARTED -> Known.DEPLOYMENT_RUN_STARTED
                DEPLOYMENT_DELETED -> Known.DEPLOYMENT_DELETED
                DEPLOYMENT_RUN_SUCCEEDED -> Known.DEPLOYMENT_RUN_SUCCEEDED
                ENVIRONMENT_CREATED -> Known.ENVIRONMENT_CREATED
                ENVIRONMENT_UPDATED -> Known.ENVIRONMENT_UPDATED
                ENVIRONMENT_ARCHIVED -> Known.ENVIRONMENT_ARCHIVED
                ENVIRONMENT_DELETED -> Known.ENVIRONMENT_DELETED
                MEMORY_STORE_CREATED -> Known.MEMORY_STORE_CREATED
                MEMORY_STORE_ARCHIVED -> Known.MEMORY_STORE_ARCHIVED
                MEMORY_STORE_DELETED -> Known.MEMORY_STORE_DELETED
                SESSION_BUDGET_REACHED -> Known.SESSION_BUDGET_REACHED
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
}
