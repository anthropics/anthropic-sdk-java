package com.anthropic.models.beta.organization.rbacroles

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class BetaRbacRoleSourceTest {

    @Test
    fun ofBuiltIn() {
        val builtIn = BetaRbacRoleSourceBuiltIn.builder().build()

        val betaRbacRoleSource = BetaRbacRoleSource.ofBuiltIn(builtIn)

        assertThat(betaRbacRoleSource.builtIn()).contains(builtIn)
        assertThat(betaRbacRoleSource.custom()).isEmpty
    }

    @Test
    fun ofBuiltInRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacRoleSource =
            BetaRbacRoleSource.ofBuiltIn(BetaRbacRoleSourceBuiltIn.builder().build())

        val roundtrippedBetaRbacRoleSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacRoleSource),
                jacksonTypeRef<BetaRbacRoleSource>(),
            )

        assertThat(roundtrippedBetaRbacRoleSource).isEqualTo(betaRbacRoleSource)
    }

    @Test
    fun ofCustom() {
        val custom = BetaRbacRoleSourceCustom.builder().build()

        val betaRbacRoleSource = BetaRbacRoleSource.ofCustom(custom)

        assertThat(betaRbacRoleSource.builtIn()).isEmpty
        assertThat(betaRbacRoleSource.custom()).contains(custom)
    }

    @Test
    fun ofCustomRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaRbacRoleSource =
            BetaRbacRoleSource.ofCustom(BetaRbacRoleSourceCustom.builder().build())

        val roundtrippedBetaRbacRoleSource =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRbacRoleSource),
                jacksonTypeRef<BetaRbacRoleSource>(),
            )

        assertThat(roundtrippedBetaRbacRoleSource).isEqualTo(betaRbacRoleSource)
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
        val betaRbacRoleSource =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaRbacRoleSource>())

        val e = assertThrows<AnthropicInvalidDataException> { betaRbacRoleSource.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }
}
