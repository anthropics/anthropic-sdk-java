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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
    fun ofSessionStatusScheduled() {
        val sessionStatusScheduled =
            BetaWebhookSessionStatusScheduledEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionStatusScheduled(sessionStatusScheduled)

        assertThat(betaWebhookEventData.sessionCreated()).isEmpty
        assertThat(betaWebhookEventData.sessionPending()).isEmpty
        assertThat(betaWebhookEventData.sessionRunning()).isEmpty
        assertThat(betaWebhookEventData.sessionIdled()).isEmpty
        assertThat(betaWebhookEventData.sessionRequiresAction()).isEmpty
        assertThat(betaWebhookEventData.sessionArchived()).isEmpty
        assertThat(betaWebhookEventData.sessionDeleted()).isEmpty
        assertThat(betaWebhookEventData.sessionStatusScheduled()).contains(sessionStatusScheduled)
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
    }

    @Test
    fun ofSessionStatusScheduledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionStatusScheduled(
                BetaWebhookSessionStatusScheduledEventData.builder()
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
    }

    @Test
    fun ofSessionThreadCreatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionThreadCreated(
                BetaWebhookSessionThreadCreatedEventData.builder()
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
    fun ofSessionThreadIdled() {
        val sessionThreadIdled =
            BetaWebhookSessionThreadIdledEventData.builder()
                .id("id")
                .organizationId("organization_id")
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
    }

    @Test
    fun ofSessionThreadIdledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionThreadIdled(
                BetaWebhookSessionThreadIdledEventData.builder()
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
    fun ofSessionThreadTerminated() {
        val sessionThreadTerminated =
            BetaWebhookSessionThreadTerminatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
    }

    @Test
    fun ofSessionThreadTerminatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionThreadTerminated(
                BetaWebhookSessionThreadTerminatedEventData.builder()
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
        assertThat(betaWebhookEventData.sessionStatusScheduled()).isEmpty
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
