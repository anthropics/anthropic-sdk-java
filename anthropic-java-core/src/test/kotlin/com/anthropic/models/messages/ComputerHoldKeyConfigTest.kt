// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerHoldKeyConfigTest {

    @Test
    fun create() {
        val computerHoldKeyConfig =
            ComputerHoldKeyConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerHoldKeyConfig.deferLoading()).contains(true)
        assertThat(computerHoldKeyConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerHoldKeyConfig =
            ComputerHoldKeyConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerHoldKeyConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerHoldKeyConfig),
                jacksonTypeRef<ComputerHoldKeyConfig>(),
            )

        assertThat(roundtrippedComputerHoldKeyConfig).isEqualTo(computerHoldKeyConfig)
    }
}
