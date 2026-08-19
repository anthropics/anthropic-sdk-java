// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerMiddleClickConfigTest {

    @Test
    fun create() {
        val computerMiddleClickConfig =
            ComputerMiddleClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerMiddleClickConfig.deferLoading()).contains(true)
        assertThat(computerMiddleClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerMiddleClickConfig =
            ComputerMiddleClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerMiddleClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerMiddleClickConfig),
                jacksonTypeRef<ComputerMiddleClickConfig>(),
            )

        assertThat(roundtrippedComputerMiddleClickConfig).isEqualTo(computerMiddleClickConfig)
    }
}
