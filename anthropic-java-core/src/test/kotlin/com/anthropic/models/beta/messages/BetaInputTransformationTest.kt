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

internal class BetaInputTransformationTest {

    @Test
    fun ofThinkingDropped() {
        val thinkingDropped =
            BetaThinkingDroppedInputTransformation.builder()
                .path("path")
                .reason(BetaThinkingDroppedInputTransformation.Reason.MODEL_BINDING_MISMATCH)
                .build()

        val betaInputTransformation = BetaInputTransformation.ofThinkingDropped(thinkingDropped)

        assertThat(presentVariants(betaInputTransformation))
            .isEqualTo(mapOf("thinkingDropped" to Optional.of(thinkingDropped)))
    }

    @Test
    fun ofThinkingDroppedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaInputTransformation =
            BetaInputTransformation.ofThinkingDropped(
                BetaThinkingDroppedInputTransformation.builder()
                    .path("path")
                    .reason(BetaThinkingDroppedInputTransformation.Reason.MODEL_BINDING_MISMATCH)
                    .build()
            )

        val roundtrippedBetaInputTransformation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaInputTransformation),
                jacksonTypeRef<BetaInputTransformation>(),
            )

        assertThat(roundtrippedBetaInputTransformation).isEqualTo(betaInputTransformation)
    }

    @Test
    fun ofThinkingMismatchAllowed() {
        val thinkingMismatchAllowed =
            BetaThinkingMismatchAllowedInputTransformation.builder()
                .path("path")
                .reason(
                    BetaThinkingMismatchAllowedInputTransformation.Reason.MODEL_BINDING_MISMATCH
                )
                .build()

        val betaInputTransformation =
            BetaInputTransformation.ofThinkingMismatchAllowed(thinkingMismatchAllowed)

        assertThat(presentVariants(betaInputTransformation))
            .isEqualTo(mapOf("thinkingMismatchAllowed" to Optional.of(thinkingMismatchAllowed)))
    }

    @Test
    fun ofThinkingMismatchAllowedRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaInputTransformation =
            BetaInputTransformation.ofThinkingMismatchAllowed(
                BetaThinkingMismatchAllowedInputTransformation.builder()
                    .path("path")
                    .reason(
                        BetaThinkingMismatchAllowedInputTransformation.Reason.MODEL_BINDING_MISMATCH
                    )
                    .build()
            )

        val roundtrippedBetaInputTransformation =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaInputTransformation),
                jacksonTypeRef<BetaInputTransformation>(),
            )

        assertThat(roundtrippedBetaInputTransformation).isEqualTo(betaInputTransformation)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaInputTransformation =
            jsonMapper()
                .convertValue(
                    JsonValue.from(mapOf("type" to "unknown_variant", "path" to "path")),
                    jacksonTypeRef<BetaInputTransformation>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaInputTransformation.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaInputTransformation.path()).isEqualTo("path")

        val mismatchedBetaInputTransformation =
            jsonMapper()
                .convertValue(
                    JsonValue.from(mapOf("type" to "unknown_variant", "path" to listOf("invalid"))),
                    jacksonTypeRef<BetaInputTransformation>(),
                )

        assertThrows<AnthropicInvalidDataException> { mismatchedBetaInputTransformation.path() }
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
        val betaInputTransformation =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaInputTransformation>())

        val e = assertThrows<AnthropicInvalidDataException> { betaInputTransformation.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThrows<AnthropicInvalidDataException> { betaInputTransformation.path() }
    }

    private fun presentVariants(
        betaInputTransformation: BetaInputTransformation
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "thinkingDropped" to betaInputTransformation.thinkingDropped(),
                "thinkingMismatchAllowed" to betaInputTransformation.thinkingMismatchAllowed(),
            )
            .filterValues { it.isPresent }
}
