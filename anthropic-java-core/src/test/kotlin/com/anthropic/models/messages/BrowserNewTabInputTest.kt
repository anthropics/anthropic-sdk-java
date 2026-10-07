package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserNewTabInputTest {

    @Test
    fun create() {
        val browserNewTabInput = BrowserNewTabInput.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserNewTabInput = BrowserNewTabInput.builder().build()

        val roundtrippedBrowserNewTabInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserNewTabInput),
                jacksonTypeRef<BrowserNewTabInput>(),
            )

        assertThat(roundtrippedBrowserNewTabInput).isEqualTo(browserNewTabInput)
    }
}
