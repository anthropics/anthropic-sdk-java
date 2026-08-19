// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerLeftClickDragConfigTest {

    @Test
    fun create() {
        val computerLeftClickDragConfig =
            ComputerLeftClickDragConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerLeftClickDragConfig.deferLoading()).contains(true)
        assertThat(computerLeftClickDragConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerLeftClickDragConfig =
            ComputerLeftClickDragConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerLeftClickDragConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerLeftClickDragConfig),
                jacksonTypeRef<ComputerLeftClickDragConfig>(),
            )

        assertThat(roundtrippedComputerLeftClickDragConfig).isEqualTo(computerLeftClickDragConfig)
    }
}
