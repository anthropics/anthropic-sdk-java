package com.anthropic.models.beta.organization.claudecode.managedsettings.configuration

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaClaudeCodeManagedSettingsModeParamTest {

    @Test
    fun ofOrganization() {
        val organization = BetaClaudeCodeManagedSettingsOrganizationModeParam.builder().build()

        val betaClaudeCodeManagedSettingsModeParam =
            BetaClaudeCodeManagedSettingsModeParam.ofOrganization(organization)

        assertThat(betaClaudeCodeManagedSettingsModeParam.organization()).contains(organization)
        assertThat(betaClaudeCodeManagedSettingsModeParam.rbacGroups()).isEmpty
    }

    @Test
    fun ofOrganizationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeManagedSettingsModeParam =
            BetaClaudeCodeManagedSettingsModeParam.ofOrganization(
                BetaClaudeCodeManagedSettingsOrganizationModeParam.builder().build()
            )

        val roundtrippedBetaClaudeCodeManagedSettingsModeParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeManagedSettingsModeParam),
                jacksonTypeRef<BetaClaudeCodeManagedSettingsModeParam>(),
            )

        assertThat(roundtrippedBetaClaudeCodeManagedSettingsModeParam)
            .isEqualTo(betaClaudeCodeManagedSettingsModeParam)
    }

    @Test
    fun ofRbacGroups() {
        val rbacGroups = BetaClaudeCodeManagedSettingsRbacGroupsModeParam.builder().build()

        val betaClaudeCodeManagedSettingsModeParam =
            BetaClaudeCodeManagedSettingsModeParam.ofRbacGroups(rbacGroups)

        assertThat(betaClaudeCodeManagedSettingsModeParam.organization()).isEmpty
        assertThat(betaClaudeCodeManagedSettingsModeParam.rbacGroups()).contains(rbacGroups)
    }

    @Test
    fun ofRbacGroupsRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeManagedSettingsModeParam =
            BetaClaudeCodeManagedSettingsModeParam.ofRbacGroups(
                BetaClaudeCodeManagedSettingsRbacGroupsModeParam.builder().build()
            )

        val roundtrippedBetaClaudeCodeManagedSettingsModeParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeManagedSettingsModeParam),
                jacksonTypeRef<BetaClaudeCodeManagedSettingsModeParam>(),
            )

        assertThat(roundtrippedBetaClaudeCodeManagedSettingsModeParam)
            .isEqualTo(betaClaudeCodeManagedSettingsModeParam)
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
        val betaClaudeCodeManagedSettingsModeParam =
            jsonMapper()
                .convertValue(
                    testCase.value,
                    jacksonTypeRef<BetaClaudeCodeManagedSettingsModeParam>(),
                )

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaClaudeCodeManagedSettingsModeParam.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
