// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.compliancesettings

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaComplianceSettingsStateTest {

    @Test
    fun ofEnabled() {
        val enabled = BetaComplianceSettingsStateEnabled.builder().build()

        val betaComplianceSettingsState = BetaComplianceSettingsState.ofEnabled(enabled)

        assertThat(betaComplianceSettingsState.enabled()).contains(enabled)
        assertThat(betaComplianceSettingsState.disabled()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceSettingsState =
            BetaComplianceSettingsState.ofEnabled(
                BetaComplianceSettingsStateEnabled.builder().build()
            )

        val roundtrippedBetaComplianceSettingsState =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceSettingsState),
                jacksonTypeRef<BetaComplianceSettingsState>(),
            )

        assertThat(roundtrippedBetaComplianceSettingsState).isEqualTo(betaComplianceSettingsState)
    }

    @Test
    fun ofDisabled() {
        val disabled = BetaComplianceSettingsStateDisabled.builder().build()

        val betaComplianceSettingsState = BetaComplianceSettingsState.ofDisabled(disabled)

        assertThat(betaComplianceSettingsState.enabled()).isEmpty
        assertThat(betaComplianceSettingsState.disabled()).contains(disabled)
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceSettingsState =
            BetaComplianceSettingsState.ofDisabled(
                BetaComplianceSettingsStateDisabled.builder().build()
            )

        val roundtrippedBetaComplianceSettingsState =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceSettingsState),
                jacksonTypeRef<BetaComplianceSettingsState>(),
            )

        assertThat(roundtrippedBetaComplianceSettingsState).isEqualTo(betaComplianceSettingsState)
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
        val betaComplianceSettingsState =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaComplianceSettingsState>())

        val e =
            assertThrows<AnthropicInvalidDataException> { betaComplianceSettingsState.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
