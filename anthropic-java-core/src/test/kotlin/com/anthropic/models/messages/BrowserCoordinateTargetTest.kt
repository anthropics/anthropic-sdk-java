package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserCoordinateTargetTest {

    @Test
    fun create() {
        val browserCoordinateTarget = BrowserCoordinateTarget.builder().x(0L).y(0L).build()

        assertThat(browserCoordinateTarget.x()).isEqualTo(0L)
        assertThat(browserCoordinateTarget.y()).isEqualTo(0L)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserCoordinateTarget = BrowserCoordinateTarget.builder().x(0L).y(0L).build()

        val roundtrippedBrowserCoordinateTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserCoordinateTarget),
                jacksonTypeRef<BrowserCoordinateTarget>(),
            )

        assertThat(roundtrippedBrowserCoordinateTarget).isEqualTo(browserCoordinateTarget)
    }
}
