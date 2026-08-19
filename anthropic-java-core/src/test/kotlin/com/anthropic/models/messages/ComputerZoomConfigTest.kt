// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerZoomConfigTest {

    @Test
    fun create() {
        val computerZoomConfig =
            ComputerZoomConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerZoomConfig.deferLoading()).contains(true)
        assertThat(computerZoomConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerZoomConfig =
            ComputerZoomConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerZoomConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerZoomConfig),
                jacksonTypeRef<ComputerZoomConfig>(),
            )

        assertThat(roundtrippedComputerZoomConfig).isEqualTo(computerZoomConfig)
    }
}
