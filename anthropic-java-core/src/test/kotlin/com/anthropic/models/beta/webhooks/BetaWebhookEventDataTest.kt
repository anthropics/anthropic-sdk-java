package com.anthropic.models.beta.webhooks

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Optional
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionCreated" to Optional.of(sessionCreated)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionPending" to Optional.of(sessionPending)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionRunning" to Optional.of(sessionRunning)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionIdled" to Optional.of(sessionIdled)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionRequiresAction" to Optional.of(sessionRequiresAction)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionArchived" to Optional.of(sessionArchived)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionDeleted" to Optional.of(sessionDeleted)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionStatusRescheduled" to Optional.of(sessionStatusRescheduled)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionStatusRunStarted" to Optional.of(sessionStatusRunStarted)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionStatusIdled" to Optional.of(sessionStatusIdled)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionStatusTerminated" to Optional.of(sessionStatusTerminated)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionThreadCreated" to Optional.of(sessionThreadCreated)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionThreadIdled" to Optional.of(sessionThreadIdled)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionThreadTerminated" to Optional.of(sessionThreadTerminated)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(
                mapOf("sessionOutcomeEvaluationEnded" to Optional.of(sessionOutcomeEvaluationEnded))
            )
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("vaultCreated" to Optional.of(vaultCreated)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("vaultArchived" to Optional.of(vaultArchived)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("vaultDeleted" to Optional.of(vaultDeleted)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("vaultCredentialCreated" to Optional.of(vaultCredentialCreated)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("vaultCredentialArchived" to Optional.of(vaultCredentialArchived)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("vaultCredentialDeleted" to Optional.of(vaultCredentialDeleted)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(
                mapOf("vaultCredentialRefreshFailed" to Optional.of(vaultCredentialRefreshFailed))
            )
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionUpdated" to Optional.of(sessionUpdated)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("agentCreated" to Optional.of(agentCreated)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("agentArchived" to Optional.of(agentArchived)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("agentDeleted" to Optional.of(agentDeleted)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("deploymentPaused" to Optional.of(deploymentPaused)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("deploymentRunFailed" to Optional.of(deploymentRunFailed)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("deploymentCreated" to Optional.of(deploymentCreated)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("deploymentUpdated" to Optional.of(deploymentUpdated)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("deploymentUnpaused" to Optional.of(deploymentUnpaused)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("agentUpdated" to Optional.of(agentUpdated)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("deploymentArchived" to Optional.of(deploymentArchived)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("deploymentRunStarted" to Optional.of(deploymentRunStarted)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("deploymentDeleted" to Optional.of(deploymentDeleted)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("deploymentRunSucceeded" to Optional.of(deploymentRunSucceeded)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("environmentCreated" to Optional.of(environmentCreated)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("environmentUpdated" to Optional.of(environmentUpdated)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("environmentArchived" to Optional.of(environmentArchived)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("environmentDeleted" to Optional.of(environmentDeleted)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("memoryStoreCreated" to Optional.of(memoryStoreCreated)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("memoryStoreArchived" to Optional.of(memoryStoreArchived)))
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

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("memoryStoreDeleted" to Optional.of(memoryStoreDeleted)))
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

    @Test
    fun ofSessionBudgetReached() {
        val sessionBudgetReached =
            BetaWebhookSessionBudgetReachedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .workspaceId("workspace_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofSessionBudgetReached(sessionBudgetReached)

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("sessionBudgetReached" to Optional.of(sessionBudgetReached)))
    }

    @Test
    fun ofSessionBudgetReachedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofSessionBudgetReached(
                BetaWebhookSessionBudgetReachedEventData.builder()
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
    fun ofUserProfileEnforcementActionOpened() {
        val userProfileEnforcementActionOpened =
            BetaWebhookUserProfileEnforcementActionOpenedEventData.builder()
                .id("id")
                .enforcementActionId("enforcement_action_id")
                .organizationId("organization_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofUserProfileEnforcementActionOpened(
                userProfileEnforcementActionOpened
            )

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(
                mapOf(
                    "userProfileEnforcementActionOpened" to
                        Optional.of(userProfileEnforcementActionOpened)
                )
            )
    }

    @Test
    fun ofUserProfileEnforcementActionOpenedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofUserProfileEnforcementActionOpened(
                BetaWebhookUserProfileEnforcementActionOpenedEventData.builder()
                    .id("id")
                    .enforcementActionId("enforcement_action_id")
                    .organizationId("organization_id")
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
    fun ofUserProfileEnforcementActionResolved() {
        val userProfileEnforcementActionResolved =
            BetaWebhookUserProfileEnforcementActionResolvedEventData.builder()
                .id("id")
                .enforcementActionId("enforcement_action_id")
                .organizationId("organization_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofUserProfileEnforcementActionResolved(
                userProfileEnforcementActionResolved
            )

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(
                mapOf(
                    "userProfileEnforcementActionResolved" to
                        Optional.of(userProfileEnforcementActionResolved)
                )
            )
    }

    @Test
    fun ofUserProfileEnforcementActionResolvedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofUserProfileEnforcementActionResolved(
                BetaWebhookUserProfileEnforcementActionResolvedEventData.builder()
                    .id("id")
                    .enforcementActionId("enforcement_action_id")
                    .organizationId("organization_id")
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
    fun ofUserProfileProgramStatusUpdated() {
        val userProfileProgramStatusUpdated =
            BetaWebhookUserProfileProgramStatusUpdatedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofUserProfileProgramStatusUpdated(userProfileProgramStatusUpdated)

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(
                mapOf(
                    "userProfileProgramStatusUpdated" to
                        Optional.of(userProfileProgramStatusUpdated)
                )
            )
    }

    @Test
    fun ofUserProfileProgramStatusUpdatedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofUserProfileProgramStatusUpdated(
                BetaWebhookUserProfileProgramStatusUpdatedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
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
    fun ofExternalKeyFailed() {
        val externalKeyFailed =
            BetaWebhookExternalKeyFailedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofExternalKeyFailed(externalKeyFailed)

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("externalKeyFailed" to Optional.of(externalKeyFailed)))
    }

    @Test
    fun ofExternalKeyFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofExternalKeyFailed(
                BetaWebhookExternalKeyFailedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
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
    fun ofExternalKeyRecovered() {
        val externalKeyRecovered =
            BetaWebhookExternalKeyRecoveredEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        val betaWebhookEventData = BetaWebhookEventData.ofExternalKeyRecovered(externalKeyRecovered)

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("externalKeyRecovered" to Optional.of(externalKeyRecovered)))
    }

    @Test
    fun ofExternalKeyRecoveredRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofExternalKeyRecovered(
                BetaWebhookExternalKeyRecoveredEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
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
    fun ofExternalStorageFailed() {
        val externalStorageFailed =
            BetaWebhookExternalStorageFailedEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofExternalStorageFailed(externalStorageFailed)

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("externalStorageFailed" to Optional.of(externalStorageFailed)))
    }

    @Test
    fun ofExternalStorageFailedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofExternalStorageFailed(
                BetaWebhookExternalStorageFailedEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
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
    fun ofExternalStorageRecovered() {
        val externalStorageRecovered =
            BetaWebhookExternalStorageRecoveredEventData.builder()
                .id("id")
                .organizationId("organization_id")
                .build()

        val betaWebhookEventData =
            BetaWebhookEventData.ofExternalStorageRecovered(externalStorageRecovered)

        assertThat(presentVariants(betaWebhookEventData))
            .isEqualTo(mapOf("externalStorageRecovered" to Optional.of(externalStorageRecovered)))
    }

    @Test
    fun ofExternalStorageRecoveredRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaWebhookEventData =
            BetaWebhookEventData.ofExternalStorageRecovered(
                BetaWebhookExternalStorageRecoveredEventData.builder()
                    .id("id")
                    .organizationId("organization_id")
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
    fun unknownVariantCommonProperties() {
        val betaWebhookEventData =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "id" to "id",
                            "organization_id" to "organization_id",
                            "workspace_id" to "workspace_id",
                            "session_thread_id" to "session_thread_id",
                            "vault_id" to "vault_id",
                            "enforcement_action_id" to "enforcement_action_id",
                        )
                    ),
                    jacksonTypeRef<BetaWebhookEventData>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaWebhookEventData.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaWebhookEventData.id()).isEqualTo("id")
        assertThat(betaWebhookEventData.organizationId()).isEqualTo("organization_id")
        assertThat(betaWebhookEventData.workspaceId()).contains("workspace_id")
        assertThat(betaWebhookEventData.sessionThreadId()).contains("session_thread_id")
        assertThat(betaWebhookEventData.vaultId()).contains("vault_id")
        assertThat(betaWebhookEventData.enforcementActionId()).contains("enforcement_action_id")

        val mismatchedBetaWebhookEventData =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "id" to listOf("invalid"),
                            "organization_id" to listOf("invalid"),
                            "workspace_id" to listOf("invalid"),
                            "session_thread_id" to listOf("invalid"),
                            "vault_id" to listOf("invalid"),
                            "enforcement_action_id" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<BetaWebhookEventData>(),
                )

        assertThrows<AnthropicInvalidDataException> { mismatchedBetaWebhookEventData.id() }
        assertThrows<AnthropicInvalidDataException> {
            mismatchedBetaWebhookEventData.organizationId()
        }
        assertThat(mismatchedBetaWebhookEventData.workspaceId()).isEmpty
        assertThat(mismatchedBetaWebhookEventData.sessionThreadId()).isEmpty
        assertThat(mismatchedBetaWebhookEventData.vaultId()).isEmpty
        assertThat(mismatchedBetaWebhookEventData.enforcementActionId()).isEmpty
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

        assertThrows<AnthropicInvalidDataException> { betaWebhookEventData.id() }
        assertThrows<AnthropicInvalidDataException> { betaWebhookEventData.organizationId() }
        assertThat(betaWebhookEventData.workspaceId()).isEmpty
        assertThat(betaWebhookEventData.sessionThreadId()).isEmpty
        assertThat(betaWebhookEventData.vaultId()).isEmpty
        assertThat(betaWebhookEventData.enforcementActionId()).isEmpty
    }

    private fun presentVariants(
        betaWebhookEventData: BetaWebhookEventData
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "sessionCreated" to betaWebhookEventData.sessionCreated(),
                "sessionPending" to betaWebhookEventData.sessionPending(),
                "sessionRunning" to betaWebhookEventData.sessionRunning(),
                "sessionIdled" to betaWebhookEventData.sessionIdled(),
                "sessionRequiresAction" to betaWebhookEventData.sessionRequiresAction(),
                "sessionArchived" to betaWebhookEventData.sessionArchived(),
                "sessionDeleted" to betaWebhookEventData.sessionDeleted(),
                "sessionStatusRescheduled" to betaWebhookEventData.sessionStatusRescheduled(),
                "sessionStatusRunStarted" to betaWebhookEventData.sessionStatusRunStarted(),
                "sessionStatusIdled" to betaWebhookEventData.sessionStatusIdled(),
                "sessionStatusTerminated" to betaWebhookEventData.sessionStatusTerminated(),
                "sessionThreadCreated" to betaWebhookEventData.sessionThreadCreated(),
                "sessionThreadIdled" to betaWebhookEventData.sessionThreadIdled(),
                "sessionThreadTerminated" to betaWebhookEventData.sessionThreadTerminated(),
                "sessionOutcomeEvaluationEnded" to
                    betaWebhookEventData.sessionOutcomeEvaluationEnded(),
                "vaultCreated" to betaWebhookEventData.vaultCreated(),
                "vaultArchived" to betaWebhookEventData.vaultArchived(),
                "vaultDeleted" to betaWebhookEventData.vaultDeleted(),
                "vaultCredentialCreated" to betaWebhookEventData.vaultCredentialCreated(),
                "vaultCredentialArchived" to betaWebhookEventData.vaultCredentialArchived(),
                "vaultCredentialDeleted" to betaWebhookEventData.vaultCredentialDeleted(),
                "vaultCredentialRefreshFailed" to
                    betaWebhookEventData.vaultCredentialRefreshFailed(),
                "sessionUpdated" to betaWebhookEventData.sessionUpdated(),
                "agentCreated" to betaWebhookEventData.agentCreated(),
                "agentArchived" to betaWebhookEventData.agentArchived(),
                "agentDeleted" to betaWebhookEventData.agentDeleted(),
                "deploymentPaused" to betaWebhookEventData.deploymentPaused(),
                "deploymentRunFailed" to betaWebhookEventData.deploymentRunFailed(),
                "deploymentCreated" to betaWebhookEventData.deploymentCreated(),
                "deploymentUpdated" to betaWebhookEventData.deploymentUpdated(),
                "deploymentUnpaused" to betaWebhookEventData.deploymentUnpaused(),
                "agentUpdated" to betaWebhookEventData.agentUpdated(),
                "deploymentArchived" to betaWebhookEventData.deploymentArchived(),
                "deploymentRunStarted" to betaWebhookEventData.deploymentRunStarted(),
                "deploymentDeleted" to betaWebhookEventData.deploymentDeleted(),
                "deploymentRunSucceeded" to betaWebhookEventData.deploymentRunSucceeded(),
                "environmentCreated" to betaWebhookEventData.environmentCreated(),
                "environmentUpdated" to betaWebhookEventData.environmentUpdated(),
                "environmentArchived" to betaWebhookEventData.environmentArchived(),
                "environmentDeleted" to betaWebhookEventData.environmentDeleted(),
                "memoryStoreCreated" to betaWebhookEventData.memoryStoreCreated(),
                "memoryStoreArchived" to betaWebhookEventData.memoryStoreArchived(),
                "memoryStoreDeleted" to betaWebhookEventData.memoryStoreDeleted(),
                "sessionBudgetReached" to betaWebhookEventData.sessionBudgetReached(),
                "userProfileEnforcementActionOpened" to
                    betaWebhookEventData.userProfileEnforcementActionOpened(),
                "userProfileEnforcementActionResolved" to
                    betaWebhookEventData.userProfileEnforcementActionResolved(),
                "userProfileProgramStatusUpdated" to
                    betaWebhookEventData.userProfileProgramStatusUpdated(),
                "externalKeyFailed" to betaWebhookEventData.externalKeyFailed(),
                "externalKeyRecovered" to betaWebhookEventData.externalKeyRecovered(),
                "externalStorageFailed" to betaWebhookEventData.externalStorageFailed(),
                "externalStorageRecovered" to betaWebhookEventData.externalStorageRecovered(),
            )
            .filterValues { it.isPresent }
}
