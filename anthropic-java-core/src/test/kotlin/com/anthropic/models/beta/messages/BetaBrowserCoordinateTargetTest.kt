package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserCoordinateTargetTest {

    @Test
    fun create() {
        val betaBrowserCoordinateTarget = BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build()

        assertThat(betaBrowserCoordinateTarget.x()).isEqualTo(0L)
        assertThat(betaBrowserCoordinateTarget.y()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserCoordinateTarget = BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build()

        val roundtrippedBetaBrowserCoordinateTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserCoordinateTarget),
                jacksonTypeRef<BetaBrowserCoordinateTarget>(),
            )

        assertThat(roundtrippedBetaBrowserCoordinateTarget).isEqualTo(betaBrowserCoordinateTarget)
    }
}
