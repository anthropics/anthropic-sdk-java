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

internal class BetaToolUseCallerTest {

    @Test
    fun ofDirect() {
        val direct = BetaDirectCaller.builder().build()

        val betaToolUseCaller = BetaToolUseCaller.ofDirect(direct)

        assertThat(betaToolUseCaller.direct()).contains(direct)
        assertThat(betaToolUseCaller.codeExecution20250825()).isEmpty
        assertThat(betaToolUseCaller.codeExecution20260120()).isEmpty
    }

    @Test
    fun ofDirectRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUseCaller = BetaToolUseCaller.ofDirect(BetaDirectCaller.builder().build())

        val roundtrippedBetaToolUseCaller =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUseCaller),
                jacksonTypeRef<BetaToolUseCaller>(),
            )

        assertThat(roundtrippedBetaToolUseCaller).isEqualTo(betaToolUseCaller)
    }

    @Test
    fun ofCodeExecution20250825() {
        val codeExecution20250825 = BetaServerToolCaller.of("srvtoolu_SQfNkl1n_JR_")

        val betaToolUseCaller = BetaToolUseCaller.ofCodeExecution20250825(codeExecution20250825)

        assertThat(betaToolUseCaller.direct()).isEmpty
        assertThat(betaToolUseCaller.codeExecution20250825()).contains(codeExecution20250825)
        assertThat(betaToolUseCaller.codeExecution20260120()).isEmpty
    }

    @Test
    fun ofCodeExecution20250825Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUseCaller =
            BetaToolUseCaller.ofCodeExecution20250825(
                BetaServerToolCaller.of("srvtoolu_SQfNkl1n_JR_")
            )

        val roundtrippedBetaToolUseCaller =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUseCaller),
                jacksonTypeRef<BetaToolUseCaller>(),
            )

        assertThat(roundtrippedBetaToolUseCaller).isEqualTo(betaToolUseCaller)
    }

    @Test
    fun ofCodeExecution20260120() {
        val codeExecution20260120 = BetaServerToolCaller20260120.of("srvtoolu_SQfNkl1n_JR_")

        val betaToolUseCaller = BetaToolUseCaller.ofCodeExecution20260120(codeExecution20260120)

        assertThat(betaToolUseCaller.direct()).isEmpty
        assertThat(betaToolUseCaller.codeExecution20250825()).isEmpty
        assertThat(betaToolUseCaller.codeExecution20260120()).contains(codeExecution20260120)
    }

    @Test
    fun ofCodeExecution20260120Roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolUseCaller =
            BetaToolUseCaller.ofCodeExecution20260120(
                BetaServerToolCaller20260120.of("srvtoolu_SQfNkl1n_JR_")
            )

        val roundtrippedBetaToolUseCaller =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolUseCaller),
                jacksonTypeRef<BetaToolUseCaller>(),
            )

        assertThat(roundtrippedBetaToolUseCaller).isEqualTo(betaToolUseCaller)
    }

    @Test
    fun unknownVariantCommonProperties() {
        val betaToolUseCaller =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "tool_id" to "srvtoolu_SQfNkl1n_JR_")
                    ),
                    jacksonTypeRef<BetaToolUseCaller>(),
                )

        val e = assertThrows<AnthropicInvalidDataException> { betaToolUseCaller.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaToolUseCaller.toolId()).contains("srvtoolu_SQfNkl1n_JR_")

        val mismatchedBetaToolUseCaller =
            jsonMapper()
                .convertValue(
                    JsonValue.from(
                        mapOf("type" to "unknown_variant", "tool_id" to listOf("invalid"))
                    ),
                    jacksonTypeRef<BetaToolUseCaller>(),
                )

        assertThat(mismatchedBetaToolUseCaller.toolId()).isEmpty
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
        val betaToolUseCaller =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaToolUseCaller>())

        val e = assertThrows<AnthropicInvalidDataException> { betaToolUseCaller.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")

        assertThat(betaToolUseCaller.toolId()).isEmpty
    }
}
