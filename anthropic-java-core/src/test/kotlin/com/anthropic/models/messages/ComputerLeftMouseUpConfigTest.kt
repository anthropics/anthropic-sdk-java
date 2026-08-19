// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerLeftMouseUpConfigTest {

    @Test
    fun create() {
        val computerLeftMouseUpConfig =
            ComputerLeftMouseUpConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerLeftMouseUpConfig.deferLoading()).contains(true)
        assertThat(computerLeftMouseUpConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerLeftMouseUpConfig =
            ComputerLeftMouseUpConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerLeftMouseUpConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerLeftMouseUpConfig),
                jacksonTypeRef<ComputerLeftMouseUpConfig>(),
            )

        assertThat(roundtrippedComputerLeftMouseUpConfig).isEqualTo(computerLeftMouseUpConfig)
    }
}
