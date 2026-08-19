// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerTypeConfigTest {

    @Test
    fun create() {
        val computerTypeConfig =
            ComputerTypeConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerTypeConfig.deferLoading()).contains(true)
        assertThat(computerTypeConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerTypeConfig =
            ComputerTypeConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerTypeConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerTypeConfig),
                jacksonTypeRef<ComputerTypeConfig>(),
            )

        assertThat(roundtrippedComputerTypeConfig).isEqualTo(computerTypeConfig)
    }
}
