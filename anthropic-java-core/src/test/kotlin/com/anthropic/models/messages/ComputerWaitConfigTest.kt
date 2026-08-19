// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerWaitConfigTest {

    @Test
    fun create() {
        val computerWaitConfig =
            ComputerWaitConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerWaitConfig.deferLoading()).contains(true)
        assertThat(computerWaitConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerWaitConfig =
            ComputerWaitConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerWaitConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerWaitConfig),
                jacksonTypeRef<ComputerWaitConfig>(),
            )

        assertThat(roundtrippedComputerWaitConfig).isEqualTo(computerWaitConfig)
    }
}
