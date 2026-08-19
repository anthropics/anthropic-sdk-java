// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerTripleClickConfigTest {

    @Test
    fun create() {
        val computerTripleClickConfig =
            ComputerTripleClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerTripleClickConfig.deferLoading()).contains(true)
        assertThat(computerTripleClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerTripleClickConfig =
            ComputerTripleClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerTripleClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerTripleClickConfig),
                jacksonTypeRef<ComputerTripleClickConfig>(),
            )

        assertThat(roundtrippedComputerTripleClickConfig).isEqualTo(computerTripleClickConfig)
    }
}
