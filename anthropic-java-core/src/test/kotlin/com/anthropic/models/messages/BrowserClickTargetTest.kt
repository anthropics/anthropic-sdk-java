package com.anthropic.models.messages

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

internal class BrowserClickTargetTest {

    @Test
    fun ofCoordinate() {
        val coordinate = BrowserCoordinateTarget.builder().x(0L).y(0L).build()

        val browserClickTarget = BrowserClickTarget.ofCoordinate(coordinate)

        assertThat(presentVariants(browserClickTarget))
            .isEqualTo(mapOf("coordinate" to Optional.of(coordinate)))
    }

    @Test
    fun ofCoordinateRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserClickTarget =
            BrowserClickTarget.ofCoordinate(BrowserCoordinateTarget.builder().x(0L).y(0L).build())

        val roundtrippedBrowserClickTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserClickTarget),
                jacksonTypeRef<BrowserClickTarget>(),
            )

        assertThat(roundtrippedBrowserClickTarget).isEqualTo(browserClickTarget)
    }

    @Test
    fun ofRef() {
        val ref = BrowserRefTarget.of("ref")

        val browserClickTarget = BrowserClickTarget.ofRef(ref)

        assertThat(presentVariants(browserClickTarget)).isEqualTo(mapOf("ref" to Optional.of(ref)))
    }

    @Test
    fun ofRefRoundtrip() {
        val jsonMapper = jsonMapper()
        val browserClickTarget = BrowserClickTarget.ofRef(BrowserRefTarget.of("ref"))

        val roundtrippedBrowserClickTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserClickTarget),
                jacksonTypeRef<BrowserClickTarget>(),
            )

        assertThat(roundtrippedBrowserClickTarget).isEqualTo(browserClickTarget)
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
        val browserClickTarget =
            jsonMapper().convertValue(testCase.value, jacksonTypeRef<BrowserClickTarget>())

        val e = assertThrows<AnthropicInvalidDataException> { browserClickTarget.validate() }
        assertThat(e).hasMessageStartingWith("Unknown ")
    }

    private fun presentVariants(browserClickTarget: BrowserClickTarget): Map<String, Optional<*>> =
        mapOf<String, Optional<*>>(
                "coordinate" to browserClickTarget.coordinate(),
                "ref" to browserClickTarget.ref(),
            )
            .filterValues { it.isPresent }
}
