// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaManagedAgentsDeploymentPausedReasonErrorTest {

    @Test
    fun ofEnvironmentArchived() {
        val environmentArchived =
            BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.Type
                        .ENVIRONMENT_ARCHIVED_ERROR
                )
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentArchived(environmentArchived)

        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentArchived())
            .contains(environmentArchived)
        assertThat(betaManagedAgentsDeploymentPausedReasonError.agentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.fileNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.workspaceArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.organizationDisabled()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.skillNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.unknown()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported())
            .isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked()).isEmpty
    }

    @Test
    fun ofEnvironmentArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentArchived(
                BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.builder()
                    .type(
                        BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.Type
                            .ENVIRONMENT_ARCHIVED_ERROR
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsDeploymentPausedReasonError)
    }

    @Test
    fun ofAgentArchived() {
        val agentArchived =
            BetaManagedAgentsAgentArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsAgentArchivedDeploymentPausedReasonError.Type
                        .AGENT_ARCHIVED_ERROR
                )
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofAgentArchived(agentArchived)

        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.agentArchived())
            .contains(agentArchived)
        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.fileNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.workspaceArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.organizationDisabled()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.skillNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.unknown()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported())
            .isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked()).isEmpty
    }

    @Test
    fun ofAgentArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofAgentArchived(
                BetaManagedAgentsAgentArchivedDeploymentPausedReasonError.builder()
                    .type(
                        BetaManagedAgentsAgentArchivedDeploymentPausedReasonError.Type
                            .AGENT_ARCHIVED_ERROR
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsDeploymentPausedReasonError)
    }

    @Test
    fun ofEnvironmentNotFound() {
        val environmentNotFound =
            BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError.Type
                        .ENVIRONMENT_NOT_FOUND_ERROR
                )
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentNotFound(environmentNotFound)

        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.agentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentNotFound())
            .contains(environmentNotFound)
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.fileNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.workspaceArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.organizationDisabled()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.skillNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.unknown()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported())
            .isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked()).isEmpty
    }

    @Test
    fun ofEnvironmentNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentNotFound(
                BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError.builder()
                    .type(
                        BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError.Type
                            .ENVIRONMENT_NOT_FOUND_ERROR
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsDeploymentPausedReasonError)
    }

    @Test
    fun ofVaultNotFound() {
        val vaultNotFound =
            BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError.Type
                        .VAULT_NOT_FOUND_ERROR
                )
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofVaultNotFound(vaultNotFound)

        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.agentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultNotFound())
            .contains(vaultNotFound)
        assertThat(betaManagedAgentsDeploymentPausedReasonError.fileNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.workspaceArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.organizationDisabled()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.skillNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.unknown()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported())
            .isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked()).isEmpty
    }

    @Test
    fun ofVaultNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofVaultNotFound(
                BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError.builder()
                    .type(
                        BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError.Type
                            .VAULT_NOT_FOUND_ERROR
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsDeploymentPausedReasonError)
    }

    @Test
    fun ofFileNotFound() {
        val fileNotFound =
            BetaManagedAgentsFileNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsFileNotFoundDeploymentPausedReasonError.Type
                        .FILE_NOT_FOUND_ERROR
                )
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofFileNotFound(fileNotFound)

        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.agentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.fileNotFound())
            .contains(fileNotFound)
        assertThat(betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.workspaceArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.organizationDisabled()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.skillNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.unknown()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported())
            .isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked()).isEmpty
    }

    @Test
    fun ofFileNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofFileNotFound(
                BetaManagedAgentsFileNotFoundDeploymentPausedReasonError.builder()
                    .type(
                        BetaManagedAgentsFileNotFoundDeploymentPausedReasonError.Type
                            .FILE_NOT_FOUND_ERROR
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsDeploymentPausedReasonError)
    }

    @Test
    fun ofSessionResourceNotFound() {
        val sessionResourceNotFound =
            BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError.Type
                        .SESSION_RESOURCE_NOT_FOUND_ERROR
                )
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofSessionResourceNotFound(
                sessionResourceNotFound
            )

        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.agentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.fileNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound())
            .contains(sessionResourceNotFound)
        assertThat(betaManagedAgentsDeploymentPausedReasonError.workspaceArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.organizationDisabled()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.skillNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.unknown()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported())
            .isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked()).isEmpty
    }

    @Test
    fun ofSessionResourceNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofSessionResourceNotFound(
                BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError.builder()
                    .type(
                        BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError.Type
                            .SESSION_RESOURCE_NOT_FOUND_ERROR
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsDeploymentPausedReasonError)
    }

    @Test
    fun ofWorkspaceArchived() {
        val workspaceArchived =
            BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError.Type
                        .WORKSPACE_ARCHIVED_ERROR
                )
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofWorkspaceArchived(workspaceArchived)

        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.agentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.fileNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.workspaceArchived())
            .contains(workspaceArchived)
        assertThat(betaManagedAgentsDeploymentPausedReasonError.organizationDisabled()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.skillNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.unknown()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported())
            .isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked()).isEmpty
    }

    @Test
    fun ofWorkspaceArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofWorkspaceArchived(
                BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError.builder()
                    .type(
                        BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError.Type
                            .WORKSPACE_ARCHIVED_ERROR
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsDeploymentPausedReasonError)
    }

    @Test
    fun ofOrganizationDisabled() {
        val organizationDisabled =
            BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError.Type
                        .ORGANIZATION_DISABLED_ERROR
                )
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofOrganizationDisabled(
                organizationDisabled
            )

        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.agentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.fileNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.workspaceArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.organizationDisabled())
            .contains(organizationDisabled)
        assertThat(betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.skillNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.unknown()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported())
            .isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked()).isEmpty
    }

    @Test
    fun ofOrganizationDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofOrganizationDisabled(
                BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError.builder()
                    .type(
                        BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError.Type
                            .ORGANIZATION_DISABLED_ERROR
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsDeploymentPausedReasonError)
    }

    @Test
    fun ofMemoryStoreArchived() {
        val memoryStoreArchived =
            BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError.Type
                        .MEMORY_STORE_ARCHIVED_ERROR
                )
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofMemoryStoreArchived(memoryStoreArchived)

        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.agentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.fileNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.workspaceArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.organizationDisabled()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived())
            .contains(memoryStoreArchived)
        assertThat(betaManagedAgentsDeploymentPausedReasonError.skillNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.unknown()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported())
            .isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked()).isEmpty
    }

    @Test
    fun ofMemoryStoreArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofMemoryStoreArchived(
                BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError.builder()
                    .type(
                        BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError.Type
                            .MEMORY_STORE_ARCHIVED_ERROR
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsDeploymentPausedReasonError)
    }

    @Test
    fun ofSkillNotFound() {
        val skillNotFound =
            BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError.Type
                        .SKILL_NOT_FOUND_ERROR
                )
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofSkillNotFound(skillNotFound)

        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.agentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.fileNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.workspaceArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.organizationDisabled()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.skillNotFound())
            .contains(skillNotFound)
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.unknown()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported())
            .isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked()).isEmpty
    }

    @Test
    fun ofSkillNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofSkillNotFound(
                BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError.builder()
                    .type(
                        BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError.Type
                            .SKILL_NOT_FOUND_ERROR
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsDeploymentPausedReasonError)
    }

    @Test
    fun ofVaultArchived() {
        val vaultArchived =
            BetaManagedAgentsVaultArchivedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsVaultArchivedDeploymentPausedReasonError.Type
                        .VAULT_ARCHIVED_ERROR
                )
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofVaultArchived(vaultArchived)

        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.agentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.fileNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.workspaceArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.organizationDisabled()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.skillNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultArchived())
            .contains(vaultArchived)
        assertThat(betaManagedAgentsDeploymentPausedReasonError.unknown()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported())
            .isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked()).isEmpty
    }

    @Test
    fun ofVaultArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofVaultArchived(
                BetaManagedAgentsVaultArchivedDeploymentPausedReasonError.builder()
                    .type(
                        BetaManagedAgentsVaultArchivedDeploymentPausedReasonError.Type
                            .VAULT_ARCHIVED_ERROR
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsDeploymentPausedReasonError)
    }

    @Test
    fun ofUnknown() {
        val unknown =
            BetaManagedAgentsUnknownDeploymentPausedReasonError.builder()
                .type(BetaManagedAgentsUnknownDeploymentPausedReasonError.Type.UNKNOWN_ERROR)
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofUnknown(unknown)

        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.agentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.fileNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.workspaceArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.organizationDisabled()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.skillNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.unknown()).contains(unknown)
        assertThat(betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported())
            .isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked()).isEmpty
    }

    @Test
    fun ofUnknownRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofUnknown(
                BetaManagedAgentsUnknownDeploymentPausedReasonError.builder()
                    .type(BetaManagedAgentsUnknownDeploymentPausedReasonError.Type.UNKNOWN_ERROR)
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsDeploymentPausedReasonError)
    }

    @Test
    fun ofSelfHostedResourcesUnsupported() {
        val selfHostedResourcesUnsupported =
            BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError.Type
                        .SELF_HOSTED_RESOURCES_UNSUPPORTED_ERROR
                )
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofSelfHostedResourcesUnsupported(
                selfHostedResourcesUnsupported
            )

        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.agentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.fileNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.workspaceArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.organizationDisabled()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.skillNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.unknown()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported())
            .contains(selfHostedResourcesUnsupported)
        assertThat(betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked()).isEmpty
    }

    @Test
    fun ofSelfHostedResourcesUnsupportedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofSelfHostedResourcesUnsupported(
                BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError.builder()
                    .type(
                        BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError
                            .Type
                            .SELF_HOSTED_RESOURCES_UNSUPPORTED_ERROR
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsDeploymentPausedReasonError)
    }

    @Test
    fun ofMcpEgressBlocked() {
        val mcpEgressBlocked =
            BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError.builder()
                .type(
                    BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError.Type
                        .MCP_EGRESS_BLOCKED_ERROR
                )
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofMcpEgressBlocked(mcpEgressBlocked)

        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.agentArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.environmentNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.fileNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.workspaceArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.organizationDisabled()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.skillNotFound()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.vaultArchived()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.unknown()).isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported())
            .isEmpty
        assertThat(betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked())
            .contains(mcpEgressBlocked)
    }

    @Test
    fun ofMcpEgressBlockedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofMcpEgressBlocked(
                BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError.builder()
                    .type(
                        BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError.Type
                            .MCP_EGRESS_BLOCKED_ERROR
                    )
                    .build()
            )

        val roundtrippedBetaManagedAgentsDeploymentPausedReasonError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsDeploymentPausedReasonError),
                jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsDeploymentPausedReasonError)
            .isEqualTo(betaManagedAgentsDeploymentPausedReasonError)
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
        val betaManagedAgentsDeploymentPausedReasonError =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaManagedAgentsDeploymentPausedReasonError>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaManagedAgentsDeploymentPausedReasonError.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
