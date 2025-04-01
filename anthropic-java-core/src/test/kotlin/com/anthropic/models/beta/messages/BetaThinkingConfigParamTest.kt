// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaThinkingConfigParamTest {

    @Test
    fun ofEnabled() {
        val enabled = BetaThinkingConfigEnabled.builder().budgetTokens(1024L).build()

        val betaThinkingConfigParam = BetaThinkingConfigParam.ofEnabled(enabled)

        assertThat(betaThinkingConfigParam.enabled()).contains(enabled)
        assertThat(betaThinkingConfigParam.disabled()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingConfigParam =
            BetaThinkingConfigParam.ofEnabled(
                BetaThinkingConfigEnabled.builder().budgetTokens(1024L).build()
            )

        val roundtrippedBetaThinkingConfigParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingConfigParam),
                jacksonTypeRef<BetaThinkingConfigParam>(),
            )

        assertThat(roundtrippedBetaThinkingConfigParam).isEqualTo(betaThinkingConfigParam)
    }

    @Test
    fun ofDisabled() {
        val disabled = BetaThinkingConfigDisabled.builder().build()

        val betaThinkingConfigParam = BetaThinkingConfigParam.ofDisabled(disabled)

        assertThat(betaThinkingConfigParam.enabled()).isEmpty
        assertThat(betaThinkingConfigParam.disabled()).contains(disabled)
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingConfigParam =
            BetaThinkingConfigParam.ofDisabled(BetaThinkingConfigDisabled.builder().build())

        val roundtrippedBetaThinkingConfigParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingConfigParam),
                jacksonTypeRef<BetaThinkingConfigParam>(),
            )

        assertThat(roundtrippedBetaThinkingConfigParam).isEqualTo(betaThinkingConfigParam)
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
        val betaThinkingConfigParam =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaThinkingConfigParam>())

        val e = assertThrows<AnthropicInvalidDataException> { betaThinkingConfigParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
