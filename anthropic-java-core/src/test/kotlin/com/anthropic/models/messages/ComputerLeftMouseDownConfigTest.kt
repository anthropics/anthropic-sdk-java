// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerLeftMouseDownConfigTest {

    @Test
    fun create() {
        val computerLeftMouseDownConfig =
            ComputerLeftMouseDownConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerLeftMouseDownConfig.deferLoading()).contains(true)
        assertThat(computerLeftMouseDownConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerLeftMouseDownConfig =
            ComputerLeftMouseDownConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerLeftMouseDownConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerLeftMouseDownConfig),
                jacksonTypeRef<ComputerLeftMouseDownConfig>(),
            )

        assertThat(roundtrippedComputerLeftMouseDownConfig).isEqualTo(computerLeftMouseDownConfig)
    }
}
