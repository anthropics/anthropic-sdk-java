package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ThinkingConfigBetweenToolsTest {

    @Test
    fun create() {
        val thinkingConfigBetweenTools = ThinkingConfigBetweenTools.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val thinkingConfigBetweenTools = ThinkingConfigBetweenTools.builder().build()

        val roundtrippedThinkingConfigBetweenTools =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(thinkingConfigBetweenTools),
                jacksonTypeRef<ThinkingConfigBetweenTools>(),
            )

        assertThat(roundtrippedThinkingConfigBetweenTools).isEqualTo(thinkingConfigBetweenTools)
    }
}
