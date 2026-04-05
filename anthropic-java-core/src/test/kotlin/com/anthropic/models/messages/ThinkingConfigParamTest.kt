// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import kotlinx.kmp.util.core.JsonValue
import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class ThinkingConfigParamTest {

    @Test
    fun ofEnabled() {
        val enabled =
            ThinkingConfigEnabled.builder()
                .budgetTokens(1024L)
                .display(ThinkingConfigEnabled.Display.SUMMARIZED)
                .build()

        val thinkingConfigParam = ThinkingConfigParam.ofEnabled(enabled)

        assertThat(thinkingConfigParam.enabled()).contains(enabled)
        assertThat(thinkingConfigParam.disabled()).isEmpty
        assertThat(thinkingConfigParam.adaptive()).isEmpty
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val thinkingConfigParam =
            ThinkingConfigParam.ofEnabled(
                ThinkingConfigEnabled.builder()
                    .budgetTokens(1024L)
                    .display(ThinkingConfigEnabled.Display.SUMMARIZED)
                    .build()
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
        assertThat(thinkingConfigParam.adaptive()).isEmpty
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

    @Test
    fun ofAdaptive() {
        val adaptive =
            ThinkingConfigAdaptive.builder()
                .display(ThinkingConfigAdaptive.Display.SUMMARIZED)
                .build()

        val thinkingConfigParam = ThinkingConfigParam.ofAdaptive(adaptive)

        assertThat(thinkingConfigParam.enabled()).isEmpty
        assertThat(thinkingConfigParam.disabled()).isEmpty
        assertThat(thinkingConfigParam.adaptive()).contains(adaptive)
    }

    @Test
    fun ofAdaptiveRoundtrip() {
        val jsonMapper = jsonMapper()
        val thinkingConfigParam =
            ThinkingConfigParam.ofAdaptive(
                ThinkingConfigAdaptive.builder()
                    .display(ThinkingConfigAdaptive.Display.SUMMARIZED)
                    .build()
            )

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
