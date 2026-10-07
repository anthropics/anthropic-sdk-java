package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserRefTargetTest {

    @Test
    fun create() {
        val browserRefTarget = BrowserRefTarget.of("ref")

        assertThat(browserRefTarget.ref()).isEqualTo("ref")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserRefTarget = BrowserRefTarget.of("ref")

        val roundtrippedBrowserRefTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserRefTarget),
                jacksonTypeRef<BrowserRefTarget>(),
            )

        assertThat(roundtrippedBrowserRefTarget).isEqualTo(browserRefTarget)
    }
}
