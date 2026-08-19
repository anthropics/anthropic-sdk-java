// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerRightClickConfigTest {

    @Test
    fun create() {
        val computerRightClickConfig =
            ComputerRightClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerRightClickConfig.deferLoading()).contains(true)
        assertThat(computerRightClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerRightClickConfig =
            ComputerRightClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerRightClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerRightClickConfig),
                jacksonTypeRef<ComputerRightClickConfig>(),
            )

        assertThat(roundtrippedComputerRightClickConfig).isEqualTo(computerRightClickConfig)
    }
}
