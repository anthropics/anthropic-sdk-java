package com.anthropic.models.beta.deployments

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

internal class BetaManagedAgentsDeploymentPausedReasonErrorTest {

    @Test
    fun ofEnvironmentArchived() {
        val environmentArchived =
            BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.builder().build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentArchived(environmentArchived)

        assertThat(presentVariants(betaManagedAgentsDeploymentPausedReasonError))
            .isEqualTo(mapOf("environmentArchived" to Optional.of(environmentArchived)))
    }

    @Test
    fun ofEnvironmentArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentArchived(
                BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError.builder().build()
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
            BetaManagedAgentsAgentArchivedDeploymentPausedReasonError.builder().build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofAgentArchived(agentArchived)

        assertThat(presentVariants(betaManagedAgentsDeploymentPausedReasonError))
            .isEqualTo(mapOf("agentArchived" to Optional.of(agentArchived)))
    }

    @Test
    fun ofAgentArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofAgentArchived(
                BetaManagedAgentsAgentArchivedDeploymentPausedReasonError.builder().build()
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
            BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError.builder().build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentNotFound(environmentNotFound)

        assertThat(presentVariants(betaManagedAgentsDeploymentPausedReasonError))
            .isEqualTo(mapOf("environmentNotFound" to Optional.of(environmentNotFound)))
    }

    @Test
    fun ofEnvironmentNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentNotFound(
                BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError.builder().build()
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
            BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError.builder().build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofVaultNotFound(vaultNotFound)

        assertThat(presentVariants(betaManagedAgentsDeploymentPausedReasonError))
            .isEqualTo(mapOf("vaultNotFound" to Optional.of(vaultNotFound)))
    }

    @Test
    fun ofVaultNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofVaultNotFound(
                BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError.builder().build()
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
            BetaManagedAgentsFileNotFoundDeploymentPausedReasonError.builder().build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofFileNotFound(fileNotFound)

        assertThat(presentVariants(betaManagedAgentsDeploymentPausedReasonError))
            .isEqualTo(mapOf("fileNotFound" to Optional.of(fileNotFound)))
    }

    @Test
    fun ofFileNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofFileNotFound(
                BetaManagedAgentsFileNotFoundDeploymentPausedReasonError.builder().build()
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
            BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError.builder().build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofSessionResourceNotFound(
                sessionResourceNotFound
            )

        assertThat(presentVariants(betaManagedAgentsDeploymentPausedReasonError))
            .isEqualTo(mapOf("sessionResourceNotFound" to Optional.of(sessionResourceNotFound)))
    }

    @Test
    fun ofSessionResourceNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofSessionResourceNotFound(
                BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError.builder()
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
            BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError.builder().build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofWorkspaceArchived(workspaceArchived)

        assertThat(presentVariants(betaManagedAgentsDeploymentPausedReasonError))
            .isEqualTo(mapOf("workspaceArchived" to Optional.of(workspaceArchived)))
    }

    @Test
    fun ofWorkspaceArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofWorkspaceArchived(
                BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError.builder().build()
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
            BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError.builder().build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofOrganizationDisabled(
                organizationDisabled
            )

        assertThat(presentVariants(betaManagedAgentsDeploymentPausedReasonError))
            .isEqualTo(mapOf("organizationDisabled" to Optional.of(organizationDisabled)))
    }

    @Test
    fun ofOrganizationDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofOrganizationDisabled(
                BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError.builder().build()
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
            BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError.builder().build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofMemoryStoreArchived(memoryStoreArchived)

        assertThat(presentVariants(betaManagedAgentsDeploymentPausedReasonError))
            .isEqualTo(mapOf("memoryStoreArchived" to Optional.of(memoryStoreArchived)))
    }

    @Test
    fun ofMemoryStoreArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofMemoryStoreArchived(
                BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError.builder().build()
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
            BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError.builder().build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofSkillNotFound(skillNotFound)

        assertThat(presentVariants(betaManagedAgentsDeploymentPausedReasonError))
            .isEqualTo(mapOf("skillNotFound" to Optional.of(skillNotFound)))
    }

    @Test
    fun ofSkillNotFoundRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofSkillNotFound(
                BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError.builder().build()
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
            BetaManagedAgentsVaultArchivedDeploymentPausedReasonError.builder().build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofVaultArchived(vaultArchived)

        assertThat(presentVariants(betaManagedAgentsDeploymentPausedReasonError))
            .isEqualTo(mapOf("vaultArchived" to Optional.of(vaultArchived)))
    }

    @Test
    fun ofVaultArchivedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofVaultArchived(
                BetaManagedAgentsVaultArchivedDeploymentPausedReasonError.builder().build()
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
        val unknown = BetaManagedAgentsUnknownDeploymentPausedReasonError.builder().build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofUnknown(unknown)

        assertThat(presentVariants(betaManagedAgentsDeploymentPausedReasonError))
            .isEqualTo(mapOf("unknown" to Optional.of(unknown)))
    }

    @Test
    fun ofUnknownRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofUnknown(
                BetaManagedAgentsUnknownDeploymentPausedReasonError.builder().build()
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
                .build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofSelfHostedResourcesUnsupported(
                selfHostedResourcesUnsupported
            )

        assertThat(presentVariants(betaManagedAgentsDeploymentPausedReasonError))
            .isEqualTo(
                mapOf(
                    "selfHostedResourcesUnsupported" to Optional.of(selfHostedResourcesUnsupported)
                )
            )
    }

    @Test
    fun ofSelfHostedResourcesUnsupportedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofSelfHostedResourcesUnsupported(
                BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError.builder()
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
            BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError.builder().build()

        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofMcpEgressBlocked(mcpEgressBlocked)

        assertThat(presentVariants(betaManagedAgentsDeploymentPausedReasonError))
            .isEqualTo(mapOf("mcpEgressBlocked" to Optional.of(mcpEgressBlocked)))
    }

    @Test
    fun ofMcpEgressBlockedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsDeploymentPausedReasonError =
            BetaManagedAgentsDeploymentPausedReasonError.ofMcpEgressBlocked(
                BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError.builder().build()
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

    private fun presentVariants(
        betaManagedAgentsDeploymentPausedReasonError: BetaManagedAgentsDeploymentPausedReasonError
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "environmentArchived" to
                    betaManagedAgentsDeploymentPausedReasonError.environmentArchived(),
                "agentArchived" to betaManagedAgentsDeploymentPausedReasonError.agentArchived(),
                "environmentNotFound" to
                    betaManagedAgentsDeploymentPausedReasonError.environmentNotFound(),
                "vaultNotFound" to betaManagedAgentsDeploymentPausedReasonError.vaultNotFound(),
                "fileNotFound" to betaManagedAgentsDeploymentPausedReasonError.fileNotFound(),
                "sessionResourceNotFound" to
                    betaManagedAgentsDeploymentPausedReasonError.sessionResourceNotFound(),
                "workspaceArchived" to
                    betaManagedAgentsDeploymentPausedReasonError.workspaceArchived(),
                "organizationDisabled" to
                    betaManagedAgentsDeploymentPausedReasonError.organizationDisabled(),
                "memoryStoreArchived" to
                    betaManagedAgentsDeploymentPausedReasonError.memoryStoreArchived(),
                "skillNotFound" to betaManagedAgentsDeploymentPausedReasonError.skillNotFound(),
                "vaultArchived" to betaManagedAgentsDeploymentPausedReasonError.vaultArchived(),
                "unknown" to betaManagedAgentsDeploymentPausedReasonError.unknown(),
                "selfHostedResourcesUnsupported" to
                    betaManagedAgentsDeploymentPausedReasonError.selfHostedResourcesUnsupported(),
                "mcpEgressBlocked" to
                    betaManagedAgentsDeploymentPausedReasonError.mcpEgressBlocked(),
            )
            .filterValues { it.isPresent }
}
