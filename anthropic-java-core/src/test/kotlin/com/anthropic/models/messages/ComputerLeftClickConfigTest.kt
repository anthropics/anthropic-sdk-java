// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerLeftClickConfigTest {

    @Test
    fun create() {
        val computerLeftClickConfig =
            ComputerLeftClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerLeftClickConfig.deferLoading()).contains(true)
        assertThat(computerLeftClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerLeftClickConfig =
            ComputerLeftClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerLeftClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerLeftClickConfig),
                jacksonTypeRef<ComputerLeftClickConfig>(),
            )

        assertThat(roundtrippedComputerLeftClickConfig).isEqualTo(computerLeftClickConfig)
    }
}
