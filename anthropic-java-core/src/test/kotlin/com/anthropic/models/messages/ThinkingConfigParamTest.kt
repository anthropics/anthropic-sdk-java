// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class ThinkingConfigParamTest {

    @Test
    fun ofEnabled() {
        val enabled = ThinkingConfigEnabled.builder().budgetTokens(1024L).build()

        val thinkingConfigParam = ThinkingConfigParam.ofEnabled(enabled)

        assertThat(thinkingConfigParam.enabled()).contains(enabled)
        assertThat(thinkingConfigParam.disabled()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val thinkingConfigParam =
            ThinkingConfigParam.ofEnabled(
                ThinkingConfigEnabled.builder().budgetTokens(1024L).build()
            )

        val roundtrippedThinkingConfigParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(thinkingConfigParam),
                jacksonTypeRef<ThinkingConfigParam>(),
            )

        assertThat(roundtrippedThinkingConfigParam).isEqualTo(thinkingConfigParam)
    }

    @Test
    fun ofDisabled() {
        val disabled = ThinkingConfigDisabled.builder().build()

        val thinkingConfigParam = ThinkingConfigParam.ofDisabled(disabled)

        assertThat(thinkingConfigParam.enabled()).isEmpty
        assertThat(thinkingConfigParam.disabled()).contains(disabled)
    }

    @Test
    fun ofDisabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val thinkingConfigParam =
            ThinkingConfigParam.ofDisabled(ThinkingConfigDisabled.builder().build())

        val roundtrippedThinkingConfigParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(thinkingConfigParam),
                jacksonTypeRef<ThinkingConfigParam>(),
            )

        assertThat(roundtrippedThinkingConfigParam).isEqualTo(thinkingConfigParam)
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
        val thinkingConfigParam =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<ThinkingConfigParam>())

        val e = assertThrows<AnthropicInvalidDataException> { thinkingConfigParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
