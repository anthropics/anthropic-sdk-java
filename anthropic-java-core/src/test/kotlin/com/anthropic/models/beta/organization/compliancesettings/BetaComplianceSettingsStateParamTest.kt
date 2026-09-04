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

internal class BetaComplianceSettingsStateParamTest {

    @Test
    fun ofEnabled() {
        val enabled = BetaComplianceSettingsStateEnabledParam.builder().build()

        val betaComplianceSettingsStateParam = BetaComplianceSettingsStateParam.ofEnabled(enabled)

        assertThat(betaComplianceSettingsStateParam.enabled()).contains(enabled)
        assertThat(betaComplianceSettingsStateParam.disabled()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceSettingsStateParam =
            BetaComplianceSettingsStateParam.ofEnabled(
                BetaComplianceSettingsStateEnabledParam.builder().build()
            )

        val roundtrippedBetaComplianceSettingsStateParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceSettingsStateParam),
                jacksonTypeRef<BetaComplianceSettingsStateParam>(),
            )

        assertThat(roundtrippedBetaComplianceSettingsStateParam)
            .isEqualTo(betaComplianceSettingsStateParam)
    }

    @Test
    fun ofDisabled() {
        val disabled = BetaComplianceSettingsStateDisabledParam.builder().build()

        val betaComplianceSettingsStateParam = BetaComplianceSettingsStateParam.ofDisabled(disabled)

        assertThat(betaComplianceSettingsStateParam.enabled()).isEmpty
        assertThat(betaComplianceSettingsStateParam.disabled()).contains(disabled)
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaComplianceSettingsStateParam =
            BetaComplianceSettingsStateParam.ofDisabled(
                BetaComplianceSettingsStateDisabledParam.builder().build()
            )

        val roundtrippedBetaComplianceSettingsStateParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaComplianceSettingsStateParam),
                jacksonTypeRef<BetaComplianceSettingsStateParam>(),
            )

        assertThat(roundtrippedBetaComplianceSettingsStateParam)
            .isEqualTo(betaComplianceSettingsStateParam)
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
        val betaComplianceSettingsStateParam =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<BetaComplianceSettingsStateParam>())

        val e =
            assertThrows<AnthropicInvalidDataException> {
                betaComplianceSettingsStateParam.validate()
            }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
