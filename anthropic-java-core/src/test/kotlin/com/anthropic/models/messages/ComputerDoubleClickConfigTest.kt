// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerDoubleClickConfigTest {

    @Test
    fun create() {
        val computerDoubleClickConfig =
            ComputerDoubleClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerDoubleClickConfig.deferLoading()).contains(true)
        assertThat(computerDoubleClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerDoubleClickConfig =
            ComputerDoubleClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerDoubleClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerDoubleClickConfig),
                jacksonTypeRef<ComputerDoubleClickConfig>(),
            )

        assertThat(roundtrippedComputerDoubleClickConfig).isEqualTo(computerDoubleClickConfig)
    }
}
