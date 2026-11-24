package com.anthropic.models.organization.compliancesettings

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

internal class ComplianceSettingsStateParamTest {

    @Test
    fun ofEnabled() {
        val enabled = ComplianceSettingsStateEnabledParam.builder().build()

        val complianceSettingsStateParam = ComplianceSettingsStateParam.ofEnabled(enabled)

        assertThat(presentVariants(complianceSettingsStateParam))
            .isEqualTo(mapOf("enabled" to Optional.of(enabled)))
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val complianceSettingsStateParam =
            ComplianceSettingsStateParam.ofEnabled(
                ComplianceSettingsStateEnabledParam.builder().build()
            )

        val roundtrippedComplianceSettingsStateParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(complianceSettingsStateParam),
                jacksonTypeRef<ComplianceSettingsStateParam>(),
            )

        assertThat(roundtrippedComplianceSettingsStateParam).isEqualTo(complianceSettingsStateParam)
    }

    @Test
    fun ofDisabled() {
        val disabled = ComplianceSettingsStateDisabledParam.builder().build()

        val complianceSettingsStateParam = ComplianceSettingsStateParam.ofDisabled(disabled)

        assertThat(presentVariants(complianceSettingsStateParam))
            .isEqualTo(mapOf("disabled" to Optional.of(disabled)))
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val complianceSettingsStateParam =
            ComplianceSettingsStateParam.ofDisabled(
                ComplianceSettingsStateDisabledParam.builder().build()
            )

        val roundtrippedComplianceSettingsStateParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(complianceSettingsStateParam),
                jacksonTypeRef<ComplianceSettingsStateParam>(),
            )

        assertThat(roundtrippedComplianceSettingsStateParam).isEqualTo(complianceSettingsStateParam)
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
        val complianceSettingsStateParam =
            jsonMapper()
                .convertValue(testCase.value, jacksonTypeRef<ComplianceSettingsStateParam>())

        val e =
            assertThrows<AnthropicInvalidDataException> { complianceSettingsStateParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        complianceSettingsStateParam: ComplianceSettingsStateParam
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "enabled" to complianceSettingsStateParam.enabled(),
                "disabled" to complianceSettingsStateParam.disabled(),
            )
            .filterValues { it.isPresent }
}
