package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaThinkingConfigBetweenToolsTest {

    @Test
    fun create() {
        val betaThinkingConfigBetweenTools = BetaThinkingConfigBetweenTools.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaThinkingConfigBetweenTools = BetaThinkingConfigBetweenTools.builder().build()

        val roundtrippedBetaThinkingConfigBetweenTools =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaThinkingConfigBetweenTools),
                jacksonTypeRef<BetaThinkingConfigBetweenTools>(),
            )

        assertThat(roundtrippedBetaThinkingConfigBetweenTools)
            .isEqualTo(betaThinkingConfigBetweenTools)
    }
}
