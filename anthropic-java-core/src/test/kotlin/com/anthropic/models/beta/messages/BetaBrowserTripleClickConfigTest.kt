// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserTripleClickConfigTest {

    @Test
    fun create() {
        val betaBrowserTripleClickConfig =
            BetaBrowserTripleClickConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserTripleClickConfig.deferLoading()).contains(true)
        assertThat(betaBrowserTripleClickConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserTripleClickConfig =
            BetaBrowserTripleClickConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserTripleClickConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserTripleClickConfig),
                jacksonTypeRef<BetaBrowserTripleClickConfig>(),
            )

        assertThat(roundtrippedBetaBrowserTripleClickConfig).isEqualTo(betaBrowserTripleClickConfig)
    }
}
