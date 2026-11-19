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

internal class BetaClaudeCodeManagedSettingsModeTest {

    @Test
    fun ofOrganization() {
        val organization = BetaClaudeCodeManagedSettingsOrganizationMode.builder().build()

        val betaClaudeCodeManagedSettingsMode =
            BetaClaudeCodeManagedSettingsMode.ofOrganization(organization)

        assertThat(betaClaudeCodeManagedSettingsMode.organization()).contains(organization)
        assertThat(betaClaudeCodeManagedSettingsMode.rbacGroups()).isEmpty
    }

    @Test
    fun ofOrganizationRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeManagedSettingsMode =
            BetaClaudeCodeManagedSettingsMode.ofOrganization(
                BetaClaudeCodeManagedSettingsOrganizationMode.builder().build()
            )

        val roundtrippedBetaClaudeCodeManagedSettingsMode =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeManagedSettingsMode),
                jacksonTypeRef<BetaClaudeCodeManagedSettingsMode>(),
            )

        assertThat(roundtrippedBetaClaudeCodeManagedSettingsMode)
            .isEqualTo(betaClaudeCodeManagedSettingsMode)
    }

    @Test
    fun ofRbacGroups() {
        val rbacGroups = BetaClaudeCodeManagedSettingsRbacGroupsMode.builder().build()

        val betaClaudeCodeManagedSettingsMode =
            BetaClaudeCodeManagedSettingsMode.ofRbacGroups(rbacGroups)

        assertThat(betaClaudeCodeManagedSettingsMode.organization()).isEmpty
        assertThat(betaClaudeCodeManagedSettingsMode.rbacGroups()).contains(rbacGroups)
    }

    @Test
    fun ofRbacGroupsRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaClaudeCodeManagedSettingsMode =
            BetaClaudeCodeManagedSettingsMode.ofRbacGroups(
                BetaClaudeCodeManagedSettingsRbacGroupsMode.builder().build()
            )

        val roundtrippedBetaClaudeCodeManagedSettingsMode =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaClaudeCodeManagedSettingsMode),
                jacksonTypeRef<BetaClaudeCodeManagedSettingsMode>(),
            )

        assertThat(roundtrippedBetaClaudeCodeManagedSettingsMode)
            .isEqualTo(betaClaudeCodeManagedSettingsMode)
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
        val betaClaudeCodeManagedSettingsMode =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaClaudeCodeManagedSettingsMode>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaClaudeCodeManagedSettingsMode.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
