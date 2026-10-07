package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserFormInputInputTest {

    @Test
    fun create() {
        val betaBrowserFormInputInput =
            BetaBrowserFormInputInput.builder()
                .target(BetaBrowserRefTarget.of("ref"))
                .value("string")
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserFormInputInput.target()).isEqualTo(BetaBrowserRefTarget.of("ref"))
        assertThat(betaBrowserFormInputInput.value())
            .isEqualTo(BetaBrowserFormInputValue.ofString("string"))
        assertThat(betaBrowserFormInputInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserFormInputInput =
            BetaBrowserFormInputInput.builder()
                .target(BetaBrowserRefTarget.of("ref"))
                .value("string")
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserFormInputInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserFormInputInput),
                jacksonTypeRef<BetaBrowserFormInputInput>(),
            )

        assertThat(roundtrippedBetaBrowserFormInputInput).isEqualTo(betaBrowserFormInputInput)
    }
}
