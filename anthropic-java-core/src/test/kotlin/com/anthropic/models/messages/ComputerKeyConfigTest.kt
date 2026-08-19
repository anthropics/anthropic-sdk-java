// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComputerKeyConfigTest {

    @Test
    fun create() {
        val computerKeyConfig = ComputerKeyConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(computerKeyConfig.deferLoading()).contains(true)
        assertThat(computerKeyConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val computerKeyConfig = ComputerKeyConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedComputerKeyConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(computerKeyConfig),
                jacksonTypeRef<ComputerKeyConfig>(),
            )

        assertThat(roundtrippedComputerKeyConfig).isEqualTo(computerKeyConfig)
    }
}
