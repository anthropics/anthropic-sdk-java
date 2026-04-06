// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingConfigDisabledTest {

    @Test
    fun create() {
        val betaThinkingConfigDisabled = BetaThinkingConfigDisabled.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingConfigDisabled = BetaThinkingConfigDisabled.builder().build()

        val roundtrippedBetaThinkingConfigDisabled =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingConfigDisabled),
                jacksonTypeRef<BetaThinkingConfigDisabled>(),
            )

        assertThat(roundtrippedBetaThinkingConfigDisabled).isEqualTo(betaThinkingConfigDisabled)
    }
}
