// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerMouseMoveConfigTest {

    @Test
    fun create() {
        val computerMouseMoveConfig =
            ComputerMouseMoveConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerMouseMoveConfig.deferLoading()).contains(true)
        assertThat(computerMouseMoveConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerMouseMoveConfig =
            ComputerMouseMoveConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerMouseMoveConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerMouseMoveConfig),
                jacksonTypeRef<ComputerMouseMoveConfig>(),
            )

        assertThat(roundtrippedComputerMouseMoveConfig).isEqualTo(computerMouseMoveConfig)
    }
}
