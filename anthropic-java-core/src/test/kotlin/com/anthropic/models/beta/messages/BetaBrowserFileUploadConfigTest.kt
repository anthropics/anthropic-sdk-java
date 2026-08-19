// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserFileUploadConfigTest {

    @Test
    fun create() {
        val betaBrowserFileUploadConfig =
            BetaBrowserFileUploadConfig.builder().deferLoading(true).enabled(true).build()

        assertThat(betaBrowserFileUploadConfig.deferLoading()).contains(true)
        assertThat(betaBrowserFileUploadConfig.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserFileUploadConfig =
            BetaBrowserFileUploadConfig.builder().deferLoading(true).enabled(true).build()

        val roundtrippedBetaBrowserFileUploadConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserFileUploadConfig),
                jacksonTypeRef<BetaBrowserFileUploadConfig>(),
            )

        assertThat(roundtrippedBetaBrowserFileUploadConfig).isEqualTo(betaBrowserFileUploadConfig)
    }
}
