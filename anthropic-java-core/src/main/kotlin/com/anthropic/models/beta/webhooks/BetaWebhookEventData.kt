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
        when {
            sessionCreated != null -> Type.SESSION_CREATED
            sessionPending != null -> Type.SESSION_PENDING
            sessionRunning != null -> Type.SESSION_RUNNING
            sessionIdled != null -> Type.SESSION_IDLED
            sessionRequiresAction != null -> Type.SESSION_REQUIRES_ACTION
            sessionArchived != null -> Type.SESSION_ARCHIVED
            sessionDeleted != null -> Type.SESSION_DELETED
            sessionStatusRescheduled != null -> Type.SESSION_STATUS_RESCHEDULED
            sessionStatusRunStarted != null -> Type.SESSION_STATUS_RUN_STARTED
            sessionStatusIdled != null -> Type.SESSION_STATUS_IDLED
            sessionStatusTerminated != null -> Type.SESSION_STATUS_TERMINATED
            sessionThreadCreated != null -> Type.SESSION_THREAD_CREATED
            sessionThreadIdled != null -> Type.SESSION_THREAD_IDLED
            sessionThreadTerminated != null -> Type.SESSION_THREAD_TERMINATED
            sessionOutcomeEvaluationEnded != null -> Type.SESSION_OUTCOME_EVALUATION_ENDED
            vaultCreated != null -> Type.VAULT_CREATED
            vaultArchived != null -> Type.VAULT_ARCHIVED
            vaultDeleted != null -> Type.VAULT_DELETED
            vaultCredentialCreated != null -> Type.VAULT_CREDENTIAL_CREATED
            vaultCredentialArchived != null -> Type.VAULT_CREDENTIAL_ARCHIVED
            vaultCredentialDeleted != null -> Type.VAULT_CREDENTIAL_DELETED
            vaultCredentialRefreshFailed != null -> Type.VAULT_CREDENTIAL_REFRESH_FAILED
            sessionUpdated != null -> Type.SESSION_UPDATED
            agentCreated != null -> Type.AGENT_CREATED
            agentArchived != null -> Type.AGENT_ARCHIVED
            agentDeleted != null -> Type.AGENT_DELETED
            deploymentPaused != null -> Type.DEPLOYMENT_PAUSED
            deploymentRunFailed != null -> Type.DEPLOYMENT_RUN_FAILED
            deploymentCreated != null -> Type.DEPLOYMENT_CREATED
            deploymentUpdated != null -> Type.DEPLOYMENT_UPDATED
            deploymentUnpaused != null -> Type.DEPLOYMENT_UNPAUSED
            agentUpdated != null -> Type.AGENT_UPDATED
            deploymentArchived != null -> Type.DEPLOYMENT_ARCHIVED
            deploymentRunStarted != null -> Type.DEPLOYMENT_RUN_STARTED
            deploymentDeleted != null -> Type.DEPLOYMENT_DELETED
            deploymentRunSucceeded != null -> Type.DEPLOYMENT_RUN_SUCCEEDED
            environmentCreated != null -> Type.ENVIRONMENT_CREATED
            environmentUpdated != null -> Type.ENVIRONMENT_UPDATED
            environmentArchived != null -> Type.ENVIRONMENT_ARCHIVED
            environmentDeleted != null -> Type.ENVIRONMENT_DELETED
            memoryStoreCreated != null -> Type.MEMORY_STORE_CREATED
            memoryStoreArchived != null -> Type.MEMORY_STORE_ARCHIVED
            memoryStoreDeleted != null -> Type.MEMORY_STORE_DELETED
            sessionBudgetReached != null -> Type.SESSION_BUDGET_REACHED
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun id(): String =
        when {
            sessionCreated != null -> sessionCreated.id()
            sessionPending != null -> sessionPending.id()
            sessionRunning != null -> sessionRunning.id()
            sessionIdled != null -> sessionIdled.id()
            sessionRequiresAction != null -> sessionRequiresAction.id()
            sessionArchived != null -> sessionArchived.id()
            sessionDeleted != null -> sessionDeleted.id()
            sessionStatusRescheduled != null -> sessionStatusRescheduled.id()
            sessionStatusRunStarted != null -> sessionStatusRunStarted.id()
            sessionStatusIdled != null -> sessionStatusIdled.id()
            sessionStatusTerminated != null -> sessionStatusTerminated.id()
            sessionThreadCreated != null -> sessionThreadCreated.id()
            sessionThreadIdled != null -> sessionThreadIdled.id()
            sessionThreadTerminated != null -> sessionThreadTerminated.id()
            sessionOutcomeEvaluationEnded != null -> sessionOutcomeEvaluationEnded.id()
            vaultCreated != null -> vaultCreated.id()
            vaultArchived != null -> vaultArchived.id()
            vaultDeleted != null -> vaultDeleted.id()
            vaultCredentialCreated != null -> vaultCredentialCreated.id()
            vaultCredentialArchived != null -> vaultCredentialArchived.id()
            vaultCredentialDeleted != null -> vaultCredentialDeleted.id()
            vaultCredentialRefreshFailed != null -> vaultCredentialRefreshFailed.id()
            sessionUpdated != null -> sessionUpdated.id()
            agentCreated != null -> agentCreated.id()
            agentArchived != null -> agentArchived.id()
            agentDeleted != null -> agentDeleted.id()
            deploymentPaused != null -> deploymentPaused.id()
            deploymentRunFailed != null -> deploymentRunFailed.id()
            deploymentCreated != null -> deploymentCreated.id()
            deploymentUpdated != null -> deploymentUpdated.id()
            deploymentUnpaused != null -> deploymentUnpaused.id()
            agentUpdated != null -> agentUpdated.id()
            deploymentArchived != null -> deploymentArchived.id()
            deploymentRunStarted != null -> deploymentRunStarted.id()
            deploymentDeleted != null -> deploymentDeleted.id()
            deploymentRunSucceeded != null -> deploymentRunSucceeded.id()
            environmentCreated != null -> environmentCreated.id()
            environmentUpdated != null -> environmentUpdated.id()
            environmentArchived != null -> environmentArchived.id()
            environmentDeleted != null -> environmentDeleted.id()
            memoryStoreCreated != null -> memoryStoreCreated.id()
            memoryStoreArchived != null -> memoryStoreArchived.id()
            memoryStoreDeleted != null -> memoryStoreDeleted.id()
            sessionBudgetReached != null -> sessionBudgetReached.id()
            else -> _json.getProperty<String>("id").getRequired("id")
        }

    fun organizationId(): String =
        when {
            sessionCreated != null -> sessionCreated.organizationId()
            sessionPending != null -> sessionPending.organizationId()
            sessionRunning != null -> sessionRunning.organizationId()
            sessionIdled != null -> sessionIdled.organizationId()
            sessionRequiresAction != null -> sessionRequiresAction.organizationId()
            sessionArchived != null -> sessionArchived.organizationId()
            sessionDeleted != null -> sessionDeleted.organizationId()
            sessionStatusRescheduled != null -> sessionStatusRescheduled.organizationId()
            sessionStatusRunStarted != null -> sessionStatusRunStarted.organizationId()
            sessionStatusIdled != null -> sessionStatusIdled.organizationId()
            sessionStatusTerminated != null -> sessionStatusTerminated.organizationId()
            sessionThreadCreated != null -> sessionThreadCreated.organizationId()
            sessionThreadIdled != null -> sessionThreadIdled.organizationId()
            sessionThreadTerminated != null -> sessionThreadTerminated.organizationId()
            sessionOutcomeEvaluationEnded != null -> sessionOutcomeEvaluationEnded.organizationId()
            vaultCreated != null -> vaultCreated.organizationId()
            vaultArchived != null -> vaultArchived.organizationId()
            vaultDeleted != null -> vaultDeleted.organizationId()
            vaultCredentialCreated != null -> vaultCredentialCreated.organizationId()
            vaultCredentialArchived != null -> vaultCredentialArchived.organizationId()
            vaultCredentialDeleted != null -> vaultCredentialDeleted.organizationId()
            vaultCredentialRefreshFailed != null -> vaultCredentialRefreshFailed.organizationId()
            sessionUpdated != null -> sessionUpdated.organizationId()
            agentCreated != null -> agentCreated.organizationId()
            agentArchived != null -> agentArchived.organizationId()
            agentDeleted != null -> agentDeleted.organizationId()
            deploymentPaused != null -> deploymentPaused.organizationId()
            deploymentRunFailed != null -> deploymentRunFailed.organizationId()
            deploymentCreated != null -> deploymentCreated.organizationId()
            deploymentUpdated != null -> deploymentUpdated.organizationId()
            deploymentUnpaused != null -> deploymentUnpaused.organizationId()
            agentUpdated != null -> agentUpdated.organizationId()
            deploymentArchived != null -> deploymentArchived.organizationId()
            deploymentRunStarted != null -> deploymentRunStarted.organizationId()
            deploymentDeleted != null -> deploymentDeleted.organizationId()
            deploymentRunSucceeded != null -> deploymentRunSucceeded.organizationId()
            environmentCreated != null -> environmentCreated.organizationId()
            environmentUpdated != null -> environmentUpdated.organizationId()
            environmentArchived != null -> environmentArchived.organizationId()
            environmentDeleted != null -> environmentDeleted.organizationId()
            memoryStoreCreated != null -> memoryStoreCreated.organizationId()
            memoryStoreArchived != null -> memoryStoreArchived.organizationId()
            memoryStoreDeleted != null -> memoryStoreDeleted.organizationId()
            sessionBudgetReached != null -> sessionBudgetReached.organizationId()
            else -> _json.getProperty<String>("organization_id").getRequired("organization_id")
        }

    fun workspaceId(): String =
        when {
            sessionCreated != null -> sessionCreated.workspaceId()
            sessionPending != null -> sessionPending.workspaceId()
            sessionRunning != null -> sessionRunning.workspaceId()
            sessionIdled != null -> sessionIdled.workspaceId()
            sessionRequiresAction != null -> sessionRequiresAction.workspaceId()
            sessionArchived != null -> sessionArchived.workspaceId()
            sessionDeleted != null -> sessionDeleted.workspaceId()
            sessionStatusRescheduled != null -> sessionStatusRescheduled.workspaceId()
            sessionStatusRunStarted != null -> sessionStatusRunStarted.workspaceId()
            sessionStatusIdled != null -> sessionStatusIdled.workspaceId()
            sessionStatusTerminated != null -> sessionStatusTerminated.workspaceId()
            sessionThreadCreated != null -> sessionThreadCreated.workspaceId()
            sessionThreadIdled != null -> sessionThreadIdled.workspaceId()
            sessionThreadTerminated != null -> sessionThreadTerminated.workspaceId()
            sessionOutcomeEvaluationEnded != null -> sessionOutcomeEvaluationEnded.workspaceId()
            vaultCreated != null -> vaultCreated.workspaceId()
            vaultArchived != null -> vaultArchived.workspaceId()
            vaultDeleted != null -> vaultDeleted.workspaceId()
            vaultCredentialCreated != null -> vaultCredentialCreated.workspaceId()
            vaultCredentialArchived != null -> vaultCredentialArchived.workspaceId()
            vaultCredentialDeleted != null -> vaultCredentialDeleted.workspaceId()
            vaultCredentialRefreshFailed != null -> vaultCredentialRefreshFailed.workspaceId()
            sessionUpdated != null -> sessionUpdated.workspaceId()
            agentCreated != null -> agentCreated.workspaceId()
            agentArchived != null -> agentArchived.workspaceId()
            agentDeleted != null -> agentDeleted.workspaceId()
            deploymentPaused != null -> deploymentPaused.workspaceId()
            deploymentRunFailed != null -> deploymentRunFailed.workspaceId()
            deploymentCreated != null -> deploymentCreated.workspaceId()
            deploymentUpdated != null -> deploymentUpdated.workspaceId()
            deploymentUnpaused != null -> deploymentUnpaused.workspaceId()
            agentUpdated != null -> agentUpdated.workspaceId()
            deploymentArchived != null -> deploymentArchived.workspaceId()
            deploymentRunStarted != null -> deploymentRunStarted.workspaceId()
            deploymentDeleted != null -> deploymentDeleted.workspaceId()
            deploymentRunSucceeded != null -> deploymentRunSucceeded.workspaceId()
            environmentCreated != null -> environmentCreated.workspaceId()
            environmentUpdated != null -> environmentUpdated.workspaceId()
            environmentArchived != null -> environmentArchived.workspaceId()
            environmentDeleted != null -> environmentDeleted.workspaceId()
            memoryStoreCreated != null -> memoryStoreCreated.workspaceId()
            memoryStoreArchived != null -> memoryStoreArchived.workspaceId()
            memoryStoreDeleted != null -> memoryStoreDeleted.workspaceId()
            sessionBudgetReached != null -> sessionBudgetReached.workspaceId()
            else -> _json.getProperty<String>("workspace_id").getRequired("workspace_id")
        }

    fun sessionThreadId(): Optional<String> =
        when {
            sessionCreated != null -> Optional.empty()
            sessionPending != null -> Optional.empty()
            sessionRunning != null -> Optional.empty()
            sessionIdled != null -> Optional.empty()
            sessionRequiresAction != null -> Optional.empty()
            sessionArchived != null -> Optional.empty()
            sessionDeleted != null -> Optional.empty()
            sessionStatusRescheduled != null -> Optional.empty()
            sessionStatusRunStarted != null -> Optional.empty()
            sessionStatusIdled != null -> Optional.empty()
            sessionStatusTerminated != null -> Optional.empty()
            sessionThreadCreated != null -> Optional.of(sessionThreadCreated.sessionThreadId())
            sessionThreadIdled != null -> Optional.of(sessionThreadIdled.sessionThreadId())
            sessionThreadTerminated != null ->
                Optional.of(sessionThreadTerminated.sessionThreadId())
            sessionOutcomeEvaluationEnded != null -> Optional.empty()
            vaultCreated != null -> Optional.empty()
            vaultArchived != null -> Optional.empty()
            vaultDeleted != null -> Optional.empty()
            vaultCredentialCreated != null -> Optional.empty()
            vaultCredentialArchived != null -> Optional.empty()
            vaultCredentialDeleted != null -> Optional.empty()
            vaultCredentialRefreshFailed != null -> Optional.empty()
            sessionUpdated != null -> Optional.empty()
            agentCreated != null -> Optional.empty()
            agentArchived != null -> Optional.empty()
            agentDeleted != null -> Optional.empty()
            deploymentPaused != null -> Optional.empty()
            deploymentRunFailed != null -> Optional.empty()
            deploymentCreated != null -> Optional.empty()
            deploymentUpdated != null -> Optional.empty()
            deploymentUnpaused != null -> Optional.empty()
            agentUpdated != null -> Optional.empty()
            deploymentArchived != null -> Optional.empty()
            deploymentRunStarted != null -> Optional.empty()
            deploymentDeleted != null -> Optional.empty()
            deploymentRunSucceeded != null -> Optional.empty()
            environmentCreated != null -> Optional.empty()
            environmentUpdated != null -> Optional.empty()
            environmentArchived != null -> Optional.empty()
            environmentDeleted != null -> Optional.empty()
            memoryStoreCreated != null -> Optional.empty()
            memoryStoreArchived != null -> Optional.empty()
            memoryStoreDeleted != null -> Optional.empty()
            sessionBudgetReached != null -> Optional.empty()
            else -> _json.getProperty<String>("session_thread_id").asKnown()
        }

    fun vaultId(): Optional<String> =
        when {
            sessionCreated != null -> Optional.empty()
            sessionPending != null -> Optional.empty()
            sessionRunning != null -> Optional.empty()
            sessionIdled != null -> Optional.empty()
            sessionRequiresAction != null -> Optional.empty()
            sessionArchived != null -> Optional.empty()
            sessionDeleted != null -> Optional.empty()
            sessionStatusRescheduled != null -> Optional.empty()
            sessionStatusRunStarted != null -> Optional.empty()
            sessionStatusIdled != null -> Optional.empty()
            sessionStatusTerminated != null -> Optional.empty()
            sessionThreadCreated != null -> Optional.empty()
            sessionThreadIdled != null -> Optional.empty()
            sessionThreadTerminated != null -> Optional.empty()
            sessionOutcomeEvaluationEnded != null -> Optional.empty()
            vaultCreated != null -> Optional.empty()
            vaultArchived != null -> Optional.empty()
            vaultDeleted != null -> Optional.empty()
            vaultCredentialCreated != null -> Optional.of(vaultCredentialCreated.vaultId())
            vaultCredentialArchived != null -> Optional.of(vaultCredentialArchived.vaultId())
            vaultCredentialDeleted != null -> Optional.of(vaultCredentialDeleted.vaultId())
            vaultCredentialRefreshFailed != null ->
                Optional.of(vaultCredentialRefreshFailed.vaultId())
            sessionUpdated != null -> Optional.empty()
            agentCreated != null -> Optional.empty()
            agentArchived != null -> Optional.empty()
            agentDeleted != null -> Optional.empty()
            deploymentPaused != null -> Optional.empty()
            deploymentRunFailed != null -> Optional.empty()
            deploymentCreated != null -> Optional.empty()
            deploymentUpdated != null -> Optional.empty()
            deploymentUnpaused != null -> Optional.empty()
            agentUpdated != null -> Optional.empty()
            deploymentArchived != null -> Optional.empty()
            deploymentRunStarted != null -> Optional.empty()
            deploymentDeleted != null -> Optional.empty()
            deploymentRunSucceeded != null -> Optional.empty()
            environmentCreated != null -> Optional.empty()
            environmentUpdated != null -> Optional.empty()
            environmentArchived != null -> Optional.empty()
            environmentDeleted != null -> Optional.empty()
            memoryStoreCreated != null -> Optional.empty()
            memoryStoreArchived != null -> Optional.empty()
            memoryStoreDeleted != null -> Optional.empty()
            sessionBudgetReached != null -> Optional.empty()
            else -> _json.getProperty<String>("vault_id").asKnown()
        }

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

        when {
            sessionCreated != null -> sessionCreated.validate()
            sessionPending != null -> sessionPending.validate()
            sessionRunning != null -> sessionRunning.validate()
            sessionIdled != null -> sessionIdled.validate()
            sessionRequiresAction != null -> sessionRequiresAction.validate()
            sessionArchived != null -> sessionArchived.validate()
            sessionDeleted != null -> sessionDeleted.validate()
            sessionStatusRescheduled != null -> sessionStatusRescheduled.validate()
            sessionStatusRunStarted != null -> sessionStatusRunStarted.validate()
            sessionStatusIdled != null -> sessionStatusIdled.validate()
            sessionStatusTerminated != null -> sessionStatusTerminated.validate()
            sessionThreadCreated != null -> sessionThreadCreated.validate()
            sessionThreadIdled != null -> sessionThreadIdled.validate()
            sessionThreadTerminated != null -> sessionThreadTerminated.validate()
            sessionOutcomeEvaluationEnded != null -> sessionOutcomeEvaluationEnded.validate()
            vaultCreated != null -> vaultCreated.validate()
            vaultArchived != null -> vaultArchived.validate()
            vaultDeleted != null -> vaultDeleted.validate()
            vaultCredentialCreated != null -> vaultCredentialCreated.validate()
            vaultCredentialArchived != null -> vaultCredentialArchived.validate()
            vaultCredentialDeleted != null -> vaultCredentialDeleted.validate()
            vaultCredentialRefreshFailed != null -> vaultCredentialRefreshFailed.validate()
            sessionUpdated != null -> sessionUpdated.validate()
            agentCreated != null -> agentCreated.validate()
            agentArchived != null -> agentArchived.validate()
            agentDeleted != null -> agentDeleted.validate()
            deploymentPaused != null -> deploymentPaused.validate()
            deploymentRunFailed != null -> deploymentRunFailed.validate()
            deploymentCreated != null -> deploymentCreated.validate()
            deploymentUpdated != null -> deploymentUpdated.validate()
            deploymentUnpaused != null -> deploymentUnpaused.validate()
            agentUpdated != null -> agentUpdated.validate()
            deploymentArchived != null -> deploymentArchived.validate()
            deploymentRunStarted != null -> deploymentRunStarted.validate()
            deploymentDeleted != null -> deploymentDeleted.validate()
            deploymentRunSucceeded != null -> deploymentRunSucceeded.validate()
            environmentCreated != null -> environmentCreated.validate()
            environmentUpdated != null -> environmentUpdated.validate()
            environmentArchived != null -> environmentArchived.validate()
            environmentDeleted != null -> environmentDeleted.validate()
            memoryStoreCreated != null -> memoryStoreCreated.validate()
            memoryStoreArchived != null -> memoryStoreArchived.validate()
            memoryStoreDeleted != null -> memoryStoreDeleted.validate()
            sessionBudgetReached != null -> sessionBudgetReached.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaWebhookEventData: $_json")
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
        when {
            sessionCreated != null -> sessionCreated.validity()
            sessionPending != null -> sessionPending.validity()
            sessionRunning != null -> sessionRunning.validity()
            sessionIdled != null -> sessionIdled.validity()
            sessionRequiresAction != null -> sessionRequiresAction.validity()
            sessionArchived != null -> sessionArchived.validity()
            sessionDeleted != null -> sessionDeleted.validity()
            sessionStatusRescheduled != null -> sessionStatusRescheduled.validity()
            sessionStatusRunStarted != null -> sessionStatusRunStarted.validity()
            sessionStatusIdled != null -> sessionStatusIdled.validity()
            sessionStatusTerminated != null -> sessionStatusTerminated.validity()
            sessionThreadCreated != null -> sessionThreadCreated.validity()
            sessionThreadIdled != null -> sessionThreadIdled.validity()
            sessionThreadTerminated != null -> sessionThreadTerminated.validity()
            sessionOutcomeEvaluationEnded != null -> sessionOutcomeEvaluationEnded.validity()
            vaultCreated != null -> vaultCreated.validity()
            vaultArchived != null -> vaultArchived.validity()
            vaultDeleted != null -> vaultDeleted.validity()
            vaultCredentialCreated != null -> vaultCredentialCreated.validity()
            vaultCredentialArchived != null -> vaultCredentialArchived.validity()
            vaultCredentialDeleted != null -> vaultCredentialDeleted.validity()
            vaultCredentialRefreshFailed != null -> vaultCredentialRefreshFailed.validity()
            sessionUpdated != null -> sessionUpdated.validity()
            agentCreated != null -> agentCreated.validity()
            agentArchived != null -> agentArchived.validity()
            agentDeleted != null -> agentDeleted.validity()
            deploymentPaused != null -> deploymentPaused.validity()
            deploymentRunFailed != null -> deploymentRunFailed.validity()
            deploymentCreated != null -> deploymentCreated.validity()
            deploymentUpdated != null -> deploymentUpdated.validity()
            deploymentUnpaused != null -> deploymentUnpaused.validity()
            agentUpdated != null -> agentUpdated.validity()
            deploymentArchived != null -> deploymentArchived.validity()
            deploymentRunStarted != null -> deploymentRunStarted.validity()
            deploymentDeleted != null -> deploymentDeleted.validity()
            deploymentRunSucceeded != null -> deploymentRunSucceeded.validity()
            environmentCreated != null -> environmentCreated.validity()
            environmentUpdated != null -> environmentUpdated.validity()
            environmentArchived != null -> environmentArchived.validity()
            environmentDeleted != null -> environmentDeleted.validity()
            memoryStoreCreated != null -> memoryStoreCreated.validity()
            memoryStoreArchived != null -> memoryStoreArchived.validity()
            memoryStoreDeleted != null -> memoryStoreDeleted.validity()
            sessionBudgetReached != null -> sessionBudgetReached.validity()
            else -> 0
        }

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

    class Type private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val SESSION_CREATED = Type(JsonField.of("session.created"))

            @JvmField val SESSION_PENDING = Type(JsonField.of("session.pending"))

            @JvmField val SESSION_RUNNING = Type(JsonField.of("session.running"))

            @JvmField val SESSION_IDLED = Type(JsonField.of("session.idled"))

            @JvmField val SESSION_REQUIRES_ACTION = Type(JsonField.of("session.requires_action"))

            @JvmField val SESSION_ARCHIVED = Type(JsonField.of("session.archived"))

            @JvmField val SESSION_DELETED = Type(JsonField.of("session.deleted"))

            @JvmField
            val SESSION_STATUS_RESCHEDULED = Type(JsonField.of("session.status_rescheduled"))

            @JvmField
            val SESSION_STATUS_RUN_STARTED = Type(JsonField.of("session.status_run_started"))

            @JvmField val SESSION_STATUS_IDLED = Type(JsonField.of("session.status_idled"))

            @JvmField
            val SESSION_STATUS_TERMINATED = Type(JsonField.of("session.status_terminated"))

            @JvmField val SESSION_THREAD_CREATED = Type(JsonField.of("session.thread_created"))

            @JvmField val SESSION_THREAD_IDLED = Type(JsonField.of("session.thread_idled"))

            @JvmField
            val SESSION_THREAD_TERMINATED = Type(JsonField.of("session.thread_terminated"))

            @JvmField
            val SESSION_OUTCOME_EVALUATION_ENDED =
                Type(JsonField.of("session.outcome_evaluation_ended"))

            @JvmField val VAULT_CREATED = Type(JsonField.of("vault.created"))

            @JvmField val VAULT_ARCHIVED = Type(JsonField.of("vault.archived"))

            @JvmField val VAULT_DELETED = Type(JsonField.of("vault.deleted"))

            @JvmField val VAULT_CREDENTIAL_CREATED = Type(JsonField.of("vault_credential.created"))

            @JvmField
            val VAULT_CREDENTIAL_ARCHIVED = Type(JsonField.of("vault_credential.archived"))

            @JvmField val VAULT_CREDENTIAL_DELETED = Type(JsonField.of("vault_credential.deleted"))

            @JvmField
            val VAULT_CREDENTIAL_REFRESH_FAILED =
                Type(JsonField.of("vault_credential.refresh_failed"))

            @JvmField val SESSION_UPDATED = Type(JsonField.of("session.updated"))

            @JvmField val AGENT_CREATED = Type(JsonField.of("agent.created"))

            @JvmField val AGENT_ARCHIVED = Type(JsonField.of("agent.archived"))

            @JvmField val AGENT_DELETED = Type(JsonField.of("agent.deleted"))

            @JvmField val DEPLOYMENT_PAUSED = Type(JsonField.of("deployment.paused"))

            @JvmField val DEPLOYMENT_RUN_FAILED = Type(JsonField.of("deployment_run.failed"))

            @JvmField val DEPLOYMENT_CREATED = Type(JsonField.of("deployment.created"))

            @JvmField val DEPLOYMENT_UPDATED = Type(JsonField.of("deployment.updated"))

            @JvmField val DEPLOYMENT_UNPAUSED = Type(JsonField.of("deployment.unpaused"))

            @JvmField val AGENT_UPDATED = Type(JsonField.of("agent.updated"))

            @JvmField val DEPLOYMENT_ARCHIVED = Type(JsonField.of("deployment.archived"))

            @JvmField val DEPLOYMENT_RUN_STARTED = Type(JsonField.of("deployment_run.started"))

            @JvmField val DEPLOYMENT_DELETED = Type(JsonField.of("deployment.deleted"))

            @JvmField val DEPLOYMENT_RUN_SUCCEEDED = Type(JsonField.of("deployment_run.succeeded"))

            @JvmField val ENVIRONMENT_CREATED = Type(JsonField.of("environment.created"))

            @JvmField val ENVIRONMENT_UPDATED = Type(JsonField.of("environment.updated"))

            @JvmField val ENVIRONMENT_ARCHIVED = Type(JsonField.of("environment.archived"))

            @JvmField val ENVIRONMENT_DELETED = Type(JsonField.of("environment.deleted"))

            @JvmField val MEMORY_STORE_CREATED = Type(JsonField.of("memory_store.created"))

            @JvmField val MEMORY_STORE_ARCHIVED = Type(JsonField.of("memory_store.archived"))

            @JvmField val MEMORY_STORE_DELETED = Type(JsonField.of("memory_store.deleted"))

            @JvmField val SESSION_BUDGET_REACHED = Type(JsonField.of("session.budget_reached"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "session.created" -> SESSION_CREATED
                    "session.pending" -> SESSION_PENDING
                    "session.running" -> SESSION_RUNNING
                    "session.idled" -> SESSION_IDLED
                    "session.requires_action" -> SESSION_REQUIRES_ACTION
                    "session.archived" -> SESSION_ARCHIVED
                    "session.deleted" -> SESSION_DELETED
                    "session.status_rescheduled" -> SESSION_STATUS_RESCHEDULED
                    "session.status_run_started" -> SESSION_STATUS_RUN_STARTED
                    "session.status_idled" -> SESSION_STATUS_IDLED
                    "session.status_terminated" -> SESSION_STATUS_TERMINATED
                    "session.thread_created" -> SESSION_THREAD_CREATED
                    "session.thread_idled" -> SESSION_THREAD_IDLED
                    "session.thread_terminated" -> SESSION_THREAD_TERMINATED
                    "session.outcome_evaluation_ended" -> SESSION_OUTCOME_EVALUATION_ENDED
                    "vault.created" -> VAULT_CREATED
                    "vault.archived" -> VAULT_ARCHIVED
                    "vault.deleted" -> VAULT_DELETED
                    "vault_credential.created" -> VAULT_CREDENTIAL_CREATED
                    "vault_credential.archived" -> VAULT_CREDENTIAL_ARCHIVED
                    "vault_credential.deleted" -> VAULT_CREDENTIAL_DELETED
                    "vault_credential.refresh_failed" -> VAULT_CREDENTIAL_REFRESH_FAILED
                    "session.updated" -> SESSION_UPDATED
                    "agent.created" -> AGENT_CREATED
                    "agent.archived" -> AGENT_ARCHIVED
                    "agent.deleted" -> AGENT_DELETED
                    "deployment.paused" -> DEPLOYMENT_PAUSED
                    "deployment_run.failed" -> DEPLOYMENT_RUN_FAILED
                    "deployment.created" -> DEPLOYMENT_CREATED
                    "deployment.updated" -> DEPLOYMENT_UPDATED
                    "deployment.unpaused" -> DEPLOYMENT_UNPAUSED
                    "agent.updated" -> AGENT_UPDATED
                    "deployment.archived" -> DEPLOYMENT_ARCHIVED
                    "deployment_run.started" -> DEPLOYMENT_RUN_STARTED
                    "deployment.deleted" -> DEPLOYMENT_DELETED
                    "deployment_run.succeeded" -> DEPLOYMENT_RUN_SUCCEEDED
                    "environment.created" -> ENVIRONMENT_CREATED
                    "environment.updated" -> ENVIRONMENT_UPDATED
                    "environment.archived" -> ENVIRONMENT_ARCHIVED
                    "environment.deleted" -> ENVIRONMENT_DELETED
                    "memory_store.created" -> MEMORY_STORE_CREATED
                    "memory_store.archived" -> MEMORY_STORE_ARCHIVED
                    "memory_store.deleted" -> MEMORY_STORE_DELETED
                    "session.budget_reached" -> SESSION_BUDGET_REACHED
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
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
