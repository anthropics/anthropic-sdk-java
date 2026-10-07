package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserRefTargetTest {

    @Test
    fun create() {
        val betaBrowserRefTarget = BetaBrowserRefTarget.of("ref")

        assertThat(betaBrowserRefTarget.ref()).isEqualTo("ref")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserRefTarget = BetaBrowserRefTarget.of("ref")

        val roundtrippedBetaBrowserRefTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserRefTarget),
                jacksonTypeRef<BetaBrowserRefTarget>(),
            )

        assertThat(roundtrippedBetaBrowserRefTarget).isEqualTo(betaBrowserRefTarget)
    }
}
