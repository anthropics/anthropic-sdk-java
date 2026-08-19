// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerScrollConfigTest {

    @Test
    fun create() {
        val computerScrollConfig =
            ComputerScrollConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerScrollConfig.deferLoading()).contains(true)
        assertThat(computerScrollConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerScrollConfig =
            ComputerScrollConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerScrollConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerScrollConfig),
                jacksonTypeRef<ComputerScrollConfig>(),
            )

        assertThat(roundtrippedComputerScrollConfig).isEqualTo(computerScrollConfig)
    }
}
