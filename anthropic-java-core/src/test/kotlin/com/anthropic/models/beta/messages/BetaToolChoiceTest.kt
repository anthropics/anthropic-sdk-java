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

internal class BetaToolChoiceTest {

    @Test
    fun ofAuto() {
        val auto = BetaToolChoiceAuto.builder().disableParallelToolUse(true).build()

        val betaToolChoice = BetaToolChoice.ofAuto(auto)

        assertThat(betaToolChoice.auto()).contains(auto)
        assertThat(betaToolChoice.any()).isEmpty
        assertThat(betaToolChoice.tool()).isEmpty
        assertThat(betaToolChoice.none()).isEmpty
    }

    @Test
    fun ofAutoRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolChoice =
            BetaToolChoice.ofAuto(BetaToolChoiceAuto.builder().disableParallelToolUse(true).build())

        val roundtrippedBetaToolChoice =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolChoice),
                jacksonTypeRef<BetaToolChoice>(),
            )

        assertThat(roundtrippedBetaToolChoice).isEqualTo(betaToolChoice)
    }

    @Test
    fun ofAny() {
        val any = BetaToolChoiceAny.builder().disableParallelToolUse(true).build()

        val betaToolChoice = BetaToolChoice.ofAny(any)

        assertThat(betaToolChoice.auto()).isEmpty
        assertThat(betaToolChoice.any()).contains(any)
        assertThat(betaToolChoice.tool()).isEmpty
        assertThat(betaToolChoice.none()).isEmpty
    }

    @Test
    fun ofAnyRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolChoice =
            BetaToolChoice.ofAny(BetaToolChoiceAny.builder().disableParallelToolUse(true).build())

        val roundtrippedBetaToolChoice =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolChoice),
                jacksonTypeRef<BetaToolChoice>(),
            )

        assertThat(roundtrippedBetaToolChoice).isEqualTo(betaToolChoice)
    }

    @Test
    fun ofTool() {
        val tool = BetaToolChoiceTool.builder().name("name").disableParallelToolUse(true).build()

        val betaToolChoice = BetaToolChoice.ofTool(tool)

        assertThat(betaToolChoice.auto()).isEmpty
        assertThat(betaToolChoice.any()).isEmpty
        assertThat(betaToolChoice.tool()).contains(tool)
        assertThat(betaToolChoice.none()).isEmpty
    }

    @Test
    fun ofToolRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolChoice =
            BetaToolChoice.ofTool(
                BetaToolChoiceTool.builder().name("name").disableParallelToolUse(true).build()
            )

        val roundtrippedBetaToolChoice =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolChoice),
                jacksonTypeRef<BetaToolChoice>(),
            )

        assertThat(roundtrippedBetaToolChoice).isEqualTo(betaToolChoice)
    }

    @Test
    fun ofNone() {
        val none = BetaToolChoiceNone.builder().build()

        val betaToolChoice = BetaToolChoice.ofNone(none)

        assertThat(betaToolChoice.auto()).isEmpty
        assertThat(betaToolChoice.any()).isEmpty
        assertThat(betaToolChoice.tool()).isEmpty
        assertThat(betaToolChoice.none()).contains(none)
    }

    @Test
    fun ofNoneRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolChoice = BetaToolChoice.ofNone(BetaToolChoiceNone.builder().build())

        val roundtrippedBetaToolChoice =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolChoice),
                jacksonTypeRef<BetaToolChoice>(),
            )

        assertThat(roundtrippedBetaToolChoice).isEqualTo(betaToolChoice)
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
        val betaToolChoice =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaToolChoice>())

        val e = assertThrows<AnthropicInvalidDataException> { betaToolChoice.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
