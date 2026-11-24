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

internal class BetaThinkingConfigParamTest {

    @Test
    fun ofEnabled() {
        val enabled =
            BetaThinkingConfigEnabled.builder()
                .budgetTokens(1024L)
                .blockBinding(
                    BetaThinkingBlockBinding.builder()
                        .prefixMismatchBehavior(BetaThinkingPrefixMismatchBehavior.ERROR)
                        .build()
                )
                .display(BetaThinkingConfigEnabled.Display.SUMMARIZED)
                .build()

        val betaThinkingConfigParam = BetaThinkingConfigParam.ofEnabled(enabled)

        assertThat(presentVariants(betaThinkingConfigParam))
            .isEqualTo(mapOf("enabled" to Optional.of(enabled)))
    }

    @Test
    fun ofEnabledRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingConfigParam =
            BetaThinkingConfigParam.ofEnabled(
                BetaThinkingConfigEnabled.builder()
                    .budgetTokens(1024L)
                    .blockBinding(
                        BetaThinkingBlockBinding.builder()
                            .prefixMismatchBehavior(BetaThinkingPrefixMismatchBehavior.ERROR)
                            .build()
                    )
                    .display(BetaThinkingConfigEnabled.Display.SUMMARIZED)
                    .build()
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

        assertThat(presentVariants(betaThinkingConfigParam))
            .isEqualTo(mapOf("disabled" to Optional.of(disabled)))
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

    @Test
    fun ofBetweenTools() {
        val betweenTools = BetaThinkingConfigBetweenTools.builder().build()

        val betaThinkingConfigParam = BetaThinkingConfigParam.ofBetweenTools(betweenTools)

        assertThat(presentVariants(betaThinkingConfigParam))
            .isEqualTo(mapOf("betweenTools" to Optional.of(betweenTools)))
    }

    @Test
    fun ofBetweenToolsRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingConfigParam =
            BetaThinkingConfigParam.ofBetweenTools(BetaThinkingConfigBetweenTools.builder().build())

        val roundtrippedBetaThinkingConfigParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingConfigParam),
                jacksonTypeRef<BetaThinkingConfigParam>(),
            )

        assertThat(roundtrippedBetaThinkingConfigParam).isEqualTo(betaThinkingConfigParam)
    }

    @Test
    fun ofAdaptive() {
        val adaptive =
            BetaThinkingConfigAdaptive.builder()
                .blockBinding(
                    BetaThinkingBlockBinding.builder()
                        .prefixMismatchBehavior(BetaThinkingPrefixMismatchBehavior.ERROR)
                        .build()
                )
                .display(BetaThinkingConfigAdaptive.Display.SUMMARIZED)
                .build()

        val betaThinkingConfigParam = BetaThinkingConfigParam.ofAdaptive(adaptive)

        assertThat(presentVariants(betaThinkingConfigParam))
            .isEqualTo(mapOf("adaptive" to Optional.of(adaptive)))
    }

    @Test
    fun ofAdaptiveRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingConfigParam =
            BetaThinkingConfigParam.ofAdaptive(
                BetaThinkingConfigAdaptive.builder()
                    .blockBinding(
                        BetaThinkingBlockBinding.builder()
                            .prefixMismatchBehavior(BetaThinkingPrefixMismatchBehavior.ERROR)
                            .build()
                    )
                    .display(BetaThinkingConfigAdaptive.Display.SUMMARIZED)
                    .build()
            )

        val roundtrippedBetaThinkingConfigParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingConfigParam),
                jacksonTypeRef<BetaThinkingConfigParam>(),
            )

        assertThat(roundtrippedBetaThinkingConfigParam).isEqualTo(betaThinkingConfigParam)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaThinkingConfigParam =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf(
                            "type" to "unknown_variant",
                            "block_binding" to mapOf("prefix_mismatch_behavior" to "error"),
                        )
                    ),
                    jacksonTypeRef<BetaThinkingConfigParam>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaThinkingConfigParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaThinkingConfigParam.blockBinding())
            .contains(
                BetaThinkingBlockBinding.builder()
                    .prefixMismatchBehavior(BetaThinkingPrefixMismatchBehavior.ERROR)
                    .build()
            )

        val mismatchedBetaThinkingConfigParam =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "block_binding" to listOf("invalid"))
                    ),
                    jacksonTypeRef<BetaThinkingConfigParam>(),
                )

        assertThat(mismatchedBetaThinkingConfigParam.blockBinding()).isEmpty
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

        assertThat(betaThinkingConfigParam.blockBinding()).isEmpty
    }

    private fun presentVariants(
        betaThinkingConfigParam: BetaThinkingConfigParam
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "enabled" to betaThinkingConfigParam.enabled(),
                "disabled" to betaThinkingConfigParam.disabled(),
                "betweenTools" to betaThinkingConfigParam.betweenTools(),
                "adaptive" to betaThinkingConfigParam.adaptive(),
            )
            .filterValues { it.isPresent }
}
