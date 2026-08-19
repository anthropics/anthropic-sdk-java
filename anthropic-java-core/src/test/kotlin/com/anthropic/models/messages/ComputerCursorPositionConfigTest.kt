// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerCursorPositionConfigTest {

    @Test
    fun create() {
        val computerCursorPositionConfig =
            ComputerCursorPositionConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerCursorPositionConfig.deferLoading()).contains(true)
        assertThat(computerCursorPositionConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerCursorPositionConfig =
            ComputerCursorPositionConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerCursorPositionConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerCursorPositionConfig),
                jacksonTypeRef<ComputerCursorPositionConfig>(),
            )

        assertThat(roundtrippedComputerCursorPositionConfig).isEqualTo(computerCursorPositionConfig)
    }
}
