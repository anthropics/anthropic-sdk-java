// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCitationConfigTest {

    @Test
    fun create() {
        val betaCitationConfig = BetaCitationConfig.builder().enabled(true).build()

        assertThat(betaCitationConfig.enabled()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCitationConfig = BetaCitationConfig.builder().enabled(true).build()

        val roundtrippedBetaCitationConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCitationConfig),
                jacksonTypeRef<BetaCitationConfig>(),
            )

        assertThat(roundtrippedBetaCitationConfig).isEqualTo(betaCitationConfig)
    }
}
