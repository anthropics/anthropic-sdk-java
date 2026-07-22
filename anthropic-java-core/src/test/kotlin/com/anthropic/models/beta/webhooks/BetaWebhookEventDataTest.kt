// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.webhooks

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaWebhookEventDataTest {

    @Test
    fun ofSessionCreated() {
        val sessionCreated =
            BetaWebhookSessionCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofSessionCreated(sessionCreated)

        assertThat(betaWebhookEventData.sessionCreated()).contains(sessionCreated)
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionCreatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionCreated(
                BetaWebhookSessionCreatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionPending() {
        val sessionPending =
            BetaWebhookSessionPendingEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofSessionPending(sessionPending)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).contains(sessionPending)
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionPendingRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionPending(
                BetaWebhookSessionPendingEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionRunning() {
        val sessionRunning =
            BetaWebhookSessionRunningEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofSessionRunning(sessionRunning)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).contains(sessionRunning)
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionRunningRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionRunning(
                BetaWebhookSessionRunningEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionIdled() {
        val sessionIdled =
            BetaWebhookSessionIdledEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofSessionIdled(sessionIdled)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).contains(sessionIdled)
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionIdledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionIdled(
                BetaWebhookSessionIdledEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionRequiresAction() {
        val sessionRequiresAction =
            BetaWebhookSessionRequiresActionEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionRequiresAction(sessionRequiresAction)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).contains(sessionRequiresAction)
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionRequiresActionRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionRequiresAction(
                BetaWebhookSessionRequiresActionEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionArchived() {
        val sessionArchived =
            BetaWebhookSessionArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofSessionArchived(sessionArchived)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).contains(sessionArchived)
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionArchived(
                BetaWebhookSessionArchivedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionDeleted() {
        val sessionDeleted =
            BetaWebhookSessionDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofSessionDeleted(sessionDeleted)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).contains(sessionDeleted)
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionDeletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionDeleted(
                BetaWebhookSessionDeletedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionStatusRescheduled() {
        val sessionStatusRescheduled =
            BetaWebhookSessionStatusRescheduledEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionStatusRescheduled(sessionStatusRescheduled)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled())
            .contains(sessionStatusRescheduled)
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionStatusRescheduledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionStatusRescheduled(
                BetaWebhookSessionStatusRescheduledEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionStatusRunStarted() {
        val sessionStatusRunStarted =
            BetaWebhookSessionStatusRunStartedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionStatusRunStarted(sessionStatusRunStarted)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).contains(sessionStatusRunStarted)
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionStatusRunStartedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionStatusRunStarted(
                BetaWebhookSessionStatusRunStartedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionStatusIdled() {
        val sessionStatusIdled =
            BetaWebhookSessionStatusIdledEventData.builder()
                .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .organizationId("org_011CZkZZAe0sMna4vkBdtrfx")
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofSessionStatusIdled(sessionStatusIdled)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).contains(sessionStatusIdled)
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionStatusIdledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionStatusIdled(
                BetaWebhookSessionStatusIdledEventData.builder()
                    .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .organizationId("org_011CZkZZAe0sMna4vkBdtrfx")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionStatusTerminated() {
        val sessionStatusTerminated =
            BetaWebhookSessionStatusTerminatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionStatusTerminated(sessionStatusTerminated)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).contains(sessionStatusTerminated)
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionStatusTerminatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionStatusTerminated(
                BetaWebhookSessionStatusTerminatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionThreadCreated() {
        val sessionThreadCreated =
            BetaWebhookSessionThreadCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .sessionThreadId("session_thread_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofSessionThreadCreated(sessionThreadCreated)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).contains(sessionThreadCreated)
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionThreadCreatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionThreadCreated(
                BetaWebhookSessionThreadCreatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .sessionThreadId("session_thread_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionThreadIdled() {
        val sessionThreadIdled =
            BetaWebhookSessionThreadIdledEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .sessionThreadId("session_thread_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofSessionThreadIdled(sessionThreadIdled)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).contains(sessionThreadIdled)
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionThreadIdledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionThreadIdled(
                BetaWebhookSessionThreadIdledEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .sessionThreadId("session_thread_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionThreadTerminated() {
        val sessionThreadTerminated =
            BetaWebhookSessionThreadTerminatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .sessionThreadId("session_thread_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionThreadTerminated(sessionThreadTerminated)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).contains(sessionThreadTerminated)
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionThreadTerminatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionThreadTerminated(
                BetaWebhookSessionThreadTerminatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .sessionThreadId("session_thread_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionOutcomeEvaluationEnded() {
        val sessionOutcomeEvaluationEnded =
            BetaWebhookSessionOutcomeEvaluationEndedEventData.builder()
                .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                .organizationId("org_011CZkZZAe0sMna4vkBdtrfx")
                .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionOutcomeEvaluationEnded(sessionOutcomeEvaluationEnded)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded())
            .contains(sessionOutcomeEvaluationEnded)
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionOutcomeEvaluationEndedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionOutcomeEvaluationEnded(
                BetaWebhookSessionOutcomeEvaluationEndedEventData.builder()
                    .id("sesn_011CZkZAtmR3yMPDzynEDxu7")
                    .organizationId("org_011CZkZZAe0sMna4vkBdtrfx")
                    .workspaceId("wrkspc_011CZkZaBF1tNoB5wlCeusgy")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofVaultCreated() {
        val vaultCreated =
            BetaWebhookVaultCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofVaultCreated(vaultCreated)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).contains(vaultCreated)
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofVaultCreatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofVaultCreated(
                BetaWebhookVaultCreatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofVaultArchived() {
        val vaultArchived =
            BetaWebhookVaultArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofVaultArchived(vaultArchived)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).contains(vaultArchived)
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofVaultArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofVaultArchived(
                BetaWebhookVaultArchivedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofVaultDeleted() {
        val vaultDeleted =
            BetaWebhookVaultDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofVaultDeleted(vaultDeleted)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).contains(vaultDeleted)
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofVaultDeletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofVaultDeleted(
                BetaWebhookVaultDeletedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofVaultCredentialCreated() {
        val vaultCredentialCreated =
            BetaWebhookVaultCredentialCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .vaultId("vault_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofVaultCredentialCreated(vaultCredentialCreated)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).contains(vaultCredentialCreated)
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofVaultCredentialCreatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofVaultCredentialCreated(
                BetaWebhookVaultCredentialCreatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .vaultId("vault_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofVaultCredentialArchived() {
        val vaultCredentialArchived =
            BetaWebhookVaultCredentialArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .vaultId("vault_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofVaultCredentialArchived(vaultCredentialArchived)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).contains(vaultCredentialArchived)
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofVaultCredentialArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofVaultCredentialArchived(
                BetaWebhookVaultCredentialArchivedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .vaultId("vault_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofVaultCredentialDeleted() {
        val vaultCredentialDeleted =
            BetaWebhookVaultCredentialDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .vaultId("vault_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofVaultCredentialDeleted(vaultCredentialDeleted)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).contains(vaultCredentialDeleted)
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofVaultCredentialDeletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofVaultCredentialDeleted(
                BetaWebhookVaultCredentialDeletedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .vaultId("vault_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofVaultCredentialRefreshFailed() {
        val vaultCredentialRefreshFailed =
            BetaWebhookVaultCredentialRefreshFailedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .vaultId("vault_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofVaultCredentialRefreshFailed(vaultCredentialRefreshFailed)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed())
            .contains(vaultCredentialRefreshFailed)
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofVaultCredentialRefreshFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofVaultCredentialRefreshFailed(
                BetaWebhookVaultCredentialRefreshFailedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .vaultId("vault_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofSessionUpdated() {
        val sessionUpdated =
            BetaWebhookSessionUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofSessionUpdated(sessionUpdated)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).contains(sessionUpdated)
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofSessionUpdatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionUpdated(
                BetaWebhookSessionUpdatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofAgentCreated() {
        val agentCreated =
            BetaWebhookAgentCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofAgentCreated(agentCreated)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).contains(agentCreated)
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofAgentCreatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofAgentCreated(
                BetaWebhookAgentCreatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofAgentArchived() {
        val agentArchived =
            BetaWebhookAgentArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofAgentArchived(agentArchived)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).contains(agentArchived)
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofAgentArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofAgentArchived(
                BetaWebhookAgentArchivedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofAgentDeleted() {
        val agentDeleted =
            BetaWebhookAgentDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofAgentDeleted(agentDeleted)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).contains(agentDeleted)
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofAgentDeletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofAgentDeleted(
                BetaWebhookAgentDeletedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofDeploymentPaused() {
        val deploymentPaused =
            BetaWebhookDeploymentPausedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofDeploymentPaused(deploymentPaused)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).contains(deploymentPaused)
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofDeploymentPausedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofDeploymentPaused(
                BetaWebhookDeploymentPausedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofDeploymentRunFailed() {
        val deploymentRunFailed =
            BetaWebhookDeploymentRunFailedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofDeploymentRunFailed(deploymentRunFailed)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).contains(deploymentRunFailed)
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofDeploymentRunFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofDeploymentRunFailed(
                BetaWebhookDeploymentRunFailedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofDeploymentCreated() {
        val deploymentCreated =
            BetaWebhookDeploymentCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofDeploymentCreated(deploymentCreated)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).contains(deploymentCreated)
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofDeploymentCreatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofDeploymentCreated(
                BetaWebhookDeploymentCreatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofDeploymentUpdated() {
        val deploymentUpdated =
            BetaWebhookDeploymentUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofDeploymentUpdated(deploymentUpdated)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).contains(deploymentUpdated)
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofDeploymentUpdatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofDeploymentUpdated(
                BetaWebhookDeploymentUpdatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofDeploymentUnpaused() {
        val deploymentUnpaused =
            BetaWebhookDeploymentUnpausedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofDeploymentUnpaused(deploymentUnpaused)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).contains(deploymentUnpaused)
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofDeploymentUnpausedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofDeploymentUnpaused(
                BetaWebhookDeploymentUnpausedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofAgentUpdated() {
        val agentUpdated =
            BetaWebhookAgentUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofAgentUpdated(agentUpdated)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).contains(agentUpdated)
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofAgentUpdatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofAgentUpdated(
                BetaWebhookAgentUpdatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofDeploymentArchived() {
        val deploymentArchived =
            BetaWebhookDeploymentArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofDeploymentArchived(deploymentArchived)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).contains(deploymentArchived)
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofDeploymentArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofDeploymentArchived(
                BetaWebhookDeploymentArchivedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofDeploymentRunStarted() {
        val deploymentRunStarted =
            BetaWebhookDeploymentRunStartedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofDeploymentRunStarted(deploymentRunStarted)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).contains(deploymentRunStarted)
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofDeploymentRunStartedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofDeploymentRunStarted(
                BetaWebhookDeploymentRunStartedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofDeploymentDeleted() {
        val deploymentDeleted =
            BetaWebhookDeploymentDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofDeploymentDeleted(deploymentDeleted)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).contains(deploymentDeleted)
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofDeploymentDeletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofDeploymentDeleted(
                BetaWebhookDeploymentDeletedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofDeploymentRunSucceeded() {
        val deploymentRunSucceeded =
            BetaWebhookDeploymentRunSucceededEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofDeploymentRunSucceeded(deploymentRunSucceeded)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).contains(deploymentRunSucceeded)
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofDeploymentRunSucceededRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofDeploymentRunSucceeded(
                BetaWebhookDeploymentRunSucceededEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofEnvironmentCreated() {
        val environmentCreated =
            BetaWebhookEnvironmentCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofEnvironmentCreated(environmentCreated)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).contains(environmentCreated)
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofEnvironmentCreatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofEnvironmentCreated(
                BetaWebhookEnvironmentCreatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofEnvironmentUpdated() {
        val environmentUpdated =
            BetaWebhookEnvironmentUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofEnvironmentUpdated(environmentUpdated)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).contains(environmentUpdated)
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofEnvironmentUpdatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofEnvironmentUpdated(
                BetaWebhookEnvironmentUpdatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofEnvironmentArchived() {
        val environmentArchived =
            BetaWebhookEnvironmentArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofEnvironmentArchived(environmentArchived)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).contains(environmentArchived)
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofEnvironmentArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofEnvironmentArchived(
                BetaWebhookEnvironmentArchivedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofEnvironmentDeleted() {
        val environmentDeleted =
            BetaWebhookEnvironmentDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofEnvironmentDeleted(environmentDeleted)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).contains(environmentDeleted)
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofEnvironmentDeletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofEnvironmentDeleted(
                BetaWebhookEnvironmentDeletedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofMemoryStoreCreated() {
        val memoryStoreCreated =
            BetaWebhookMemoryStoreCreatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofMemoryStoreCreated(memoryStoreCreated)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).contains(memoryStoreCreated)
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofMemoryStoreCreatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofMemoryStoreCreated(
                BetaWebhookMemoryStoreCreatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofMemoryStoreArchived() {
        val memoryStoreArchived =
            BetaWebhookMemoryStoreArchivedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofMemoryStoreArchived(memoryStoreArchived)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).contains(memoryStoreArchived)
        assertThat(betaWebhookEventData.memoryStoreDeleted()).isEmpty
    }

    @Test
    fun ofMemoryStoreArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofMemoryStoreArchived(
                BetaWebhookMemoryStoreArchivedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    @Test
    fun ofMemoryStoreDeleted() {
        val memoryStoreDeleted =
            BetaWebhookMemoryStoreDeletedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofMemoryStoreDeleted(memoryStoreDeleted)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRescheduled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusRunStarted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadTerminated()).isEmpty
        assertThat(betaWebhookEventData.sessionOutcomeEvaluationEnded()).isEmpty
        assertThat(betaWebhookEventData.vaultCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialCreated()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialArchived()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialDeleted()).isEmpty
        assertThat(betaWebhookEventData.vaultCredentialRefreshFailed()).isEmpty
        assertThat(betaWebhookEventData.sessionUpdated()).isEmpty
        assertThat(betaWebhookEventData.agentCreated()).isEmpty
        assertThat(betaWebhookEventData.agentArchived()).isEmpty
        assertThat(betaWebhookEventData.agentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentPaused()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunFailed()).isEmpty
        assertThat(betaWebhookEventData.deploymentCreated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentUnpaused()).isEmpty
        assertThat(betaWebhookEventData.agentUpdated()).isEmpty
        assertThat(betaWebhookEventData.deploymentArchived()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunStarted()).isEmpty
        assertThat(betaWebhookEventData.deploymentDeleted()).isEmpty
        assertThat(betaWebhookEventData.deploymentRunSucceeded()).isEmpty
        assertThat(betaWebhookEventData.environmentCreated()).isEmpty
        assertThat(betaWebhookEventData.environmentUpdated()).isEmpty
        assertThat(betaWebhookEventData.environmentArchived()).isEmpty
        assertThat(betaWebhookEventData.environmentDeleted()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreCreated()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreArchived()).isEmpty
        assertThat(betaWebhookEventData.memoryStoreDeleted()).contains(memoryStoreDeleted)
    }

    @Test
    fun ofMemoryStoreDeletedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofMemoryStoreDeleted(
                BetaWebhookMemoryStoreDeletedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
                    .workspaceId("workspace_id")
                    .build()
            )

        val roundtrippedBetaWebhookEventData =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaWebhookEventData),
                jacksonTypeRef<BetaWebhookEventData>(),
            )

        assertThat(roundtrippedBetaWebhookEventData).isEqualTo(betaWebhookEventData)
    }

    enum class IncompatibleJsonShapeTestCase(val value: JsonValue) {
        BOOLEAN(JsonValue.from(false)),
        STRING(JsonValue.from("invalid")),
        INTEGER(JsonValue.from(-1)),
        FLOAT(JsonValue.from(3.14)),
        ARRAY(JsonValue.from(listOf("invalid", "array"))),
    }

    @ParameterizedTest
    @EnumSource
    fun incompatibleJsonShapeDeserializesToUnknown(testCase: IncompatibleJsonShapeTestCase) {
        val betaWebhookEventData =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaWebhookEventData>())

        val e = assertThrows<AnthropicInvalidDataException> { betaWebhookEventData.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
