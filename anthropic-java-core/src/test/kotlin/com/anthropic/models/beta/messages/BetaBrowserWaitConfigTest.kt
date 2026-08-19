// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserWaitConfigTest {

    @Test
    fun create() {
        val betaBrowserWaitConfig =
            BetaBrowserWaitConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserWaitConfig.deferLoading()).contains(true)
        assertThat(betaBrowserWaitConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserWaitConfig =
            BetaBrowserWaitConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserWaitConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserWaitConfig),
                jacksonTypeRef<BetaBrowserWaitConfig>(),
            )

        assertThat(roundtrippedBetaBrowserWaitConfig).isEqualTo(betaBrowserWaitConfig)
    }
}
