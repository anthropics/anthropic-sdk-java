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

internal class BetaBrowserClickTargetTest {

    @Test
    fun ofCoordinate() {
        val coordinate = BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build()

        val betaBrowserClickTarget = BetaBrowserClickTarget.ofCoordinate(coordinate)

        assertThat(presentVariants(betaBrowserClickTarget))
            .isEqualTo(mapOf("coordinate" to Optional.of(coordinate)))
    }

    @Test
    fun ofCoordinateRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserClickTarget =
            BetaBrowserClickTarget.ofCoordinate(
                BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build()
            )

        val roundtrippedBetaBrowserClickTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserClickTarget),
                jacksonTypeRef<BetaBrowserClickTarget>(),
            )

        assertThat(roundtrippedBetaBrowserClickTarget).isEqualTo(betaBrowserClickTarget)
    }

    @Test
    fun ofRef() {
        val ref = BetaBrowserRefTarget.of("ref")

        val betaBrowserClickTarget = BetaBrowserClickTarget.ofRef(ref)

        assertThat(presentVariants(betaBrowserClickTarget))
            .isEqualTo(mapOf("ref" to Optional.of(ref)))
    }

    @Test
    fun ofRefRoundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserClickTarget = BetaBrowserClickTarget.ofRef(BetaBrowserRefTarget.of("ref"))

        val roundtrippedBetaBrowserClickTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserClickTarget),
                jacksonTypeRef<BetaBrowserClickTarget>(),
            )

        assertThat(roundtrippedBetaBrowserClickTarget).isEqualTo(betaBrowserClickTarget)
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
        val betaBrowserClickTarget =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BetaBrowserClickTarget>())

        val e = assertThrows<AnthropicInvalidDataException> { betaBrowserClickTarget.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(
        betaBrowserClickTarget: BetaBrowserClickTarget
    ): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "coordinate" to betaBrowserClickTarget.coordinate(),
                "ref" to betaBrowserClickTarget.ref(),
            )
            .filterValues { it.isPresent }
}
