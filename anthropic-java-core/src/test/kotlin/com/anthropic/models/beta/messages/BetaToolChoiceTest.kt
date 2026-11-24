package com.anthropic.models.beta.messages

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

internal class BetaToolChoiceTest {

    @Test
    fun ofAuto() {
        val auto = BetaToolChoiceAuto.builder().disableParallelToolUse(true).build()

        val betaToolChoice = BetaToolChoice.ofAuto(auto)

        assertThat(presentVariants(betaToolChoice)).isEqualTo(mapOf("auto" to Optional.of(auto)))
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

        assertThat(presentVariants(betaToolChoice)).isEqualTo(mapOf("any" to Optional.of(any)))
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

        assertThat(presentVariants(betaToolChoice)).isEqualTo(mapOf("tool" to Optional.of(tool)))
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

        assertThat(presentVariants(betaToolChoice)).isEqualTo(mapOf("none" to Optional.of(none)))
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

    @Test
    fun unknownVariantCommonProperties() {
        val betaToolChoice =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "disable_parallel_tool_use" to true)
                    ),
                    jacksonTypeRef<BetaToolChoice>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaToolChoice.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaToolChoice.disableParallelToolUse()).contains(true)

        val mismatchedBetaToolChoice =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "disable_parallel_tool_use" to listOf("invalid"),
                        )
                    ),
                    jacksonTypeRef<BetaToolChoice>(),
                )

        assertThat(mismatchedBetaToolChoice.disableParallelToolUse()).isEmpty
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

        assertThat(betaToolChoice.disableParallelToolUse()).isEmpty
    }

    private fun presentVariants(betaToolChoice: BetaToolChoice): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "auto" to betaToolChoice.auto(),
                "any" to betaToolChoice.any(),
                "tool" to betaToolChoice.tool(),
                "none" to betaToolChoice.none(),
            )
            .filterValues { it.isPresent }
}
