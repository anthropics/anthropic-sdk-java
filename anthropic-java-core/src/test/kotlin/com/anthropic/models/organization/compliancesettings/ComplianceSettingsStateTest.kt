package com.anthropic.models.organization.compliancesettings

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class ComplianceSettingsStateTest {

    @Test
    fun ofEnabled() {
        val enabled = ComplianceSettingsStateEnabled.builder().build()

        val complianceSettingsState = ComplianceSettingsState.ofEnabled(enabled)

        assertThat(complianceSettingsState.enabled()).contains(enabled)
        assertThat(complianceSettingsState.disabled()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val complianceSettingsState =
            ComplianceSettingsState.ofEnabled(ComplianceSettingsStateEnabled.builder().build())

        val roundtrippedComplianceSettingsState =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(complianceSettingsState),
                jacksonTypeRef<ComplianceSettingsState>(),
            )

        assertThat(roundtrippedComplianceSettingsState).isEqualTo(complianceSettingsState)
    }

    @Test
    fun ofDisabled() {
        val disabled = ComplianceSettingsStateDisabled.builder().build()

        val complianceSettingsState = ComplianceSettingsState.ofDisabled(disabled)

        assertThat(complianceSettingsState.enabled()).isEmpty
        assertThat(complianceSettingsState.disabled()).contains(disabled)
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val complianceSettingsState =
            ComplianceSettingsState.ofDisabled(ComplianceSettingsStateDisabled.builder().build())

        val roundtrippedComplianceSettingsState =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(complianceSettingsState),
                jacksonTypeRef<ComplianceSettingsState>(),
            )

        assertThat(roundtrippedComplianceSettingsState).isEqualTo(complianceSettingsState)
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
        val complianceSettingsState =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<ComplianceSettingsState>())

        val e = assertThrows<AnthropicInvalidDataException> { complianceSettingsState.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
