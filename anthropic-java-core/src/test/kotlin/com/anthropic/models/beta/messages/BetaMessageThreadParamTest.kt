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

internal class BetaMessageThreadParamTest {

    @Test
    fun ofCreate() {
        val create = BetaThreadCreateParams.builder().build()

        val betaMessageThreadParam = BetaMessageThreadParam.ofCreate(create)

        assertThat(presentVariants(betaMessageThreadParam))
            .isEqualTo(mapOf("create" to Optional.of(create)))
    }

    @Test
    fun ofCreateRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageThreadParam =
            BetaMessageThreadParam.ofCreate(BetaThreadCreateParams.builder().build())

        val roundtrippedBetaMessageThreadParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageThreadParam),
                jacksonTypeRef<BetaMessageThreadParam>(),
            )

        assertThat(roundtrippedBetaMessageThreadParam).isEqualTo(betaMessageThreadParam)
    }

    @Test
    fun ofContinue() {
        val continue_ = BetaThreadContinueParams.of("previous_message_id")

        val betaMessageThreadParam = BetaMessageThreadParam.ofContinue(continue_)

        assertThat(presentVariants(betaMessageThreadParam))
            .isEqualTo(mapOf("continue_" to Optional.of(continue_)))
    }

    @Test
    fun ofContinueRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaMessageThreadParam =
            BetaMessageThreadParam.ofContinue(BetaThreadContinueParams.of("previous_message_id"))

        val roundtrippedBetaMessageThreadParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaMessageThreadParam),
                jacksonTypeRef<BetaMessageThreadParam>(),
            )

        assertThat(roundtrippedBetaMessageThreadParam).isEqualTo(betaMessageThreadParam)
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
        val betaMessageThreadParam =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaMessageThreadParam>())

        val e = assertThrows<AnthropicInvalidDataException> { betaMessageThreadParam.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaMessageThreadParam: BetaMessageThreadParam
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "create" to betaMessageThreadParam.create(),
                "continue_" to betaMessageThreadParam.continue_(),
            )
            .filterValues { it.isPresent }
}
