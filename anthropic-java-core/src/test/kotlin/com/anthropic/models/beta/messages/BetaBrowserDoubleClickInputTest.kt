package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserDoubleClickInputTest {

    @Test
    fun create() {
        val betaBrowserDoubleClickInput =
            BetaBrowserDoubleClickInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserDoubleClickInput.target())
            .isEqualTo(
                BetaBrowserClickTarget.ofCoordinate(
                    BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build()
                )
            )
        assertThat(betaBrowserDoubleClickInput.modifiers()).contains("modifiers")
        assertThat(betaBrowserDoubleClickInput.tabId()).contains("tab_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserDoubleClickInput =
            BetaBrowserDoubleClickInput.builder()
                .target(BetaBrowserCoordinateTarget.builder().x(0L).y(0L).build())
                .modifiers("modifiers")
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserDoubleClickInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserDoubleClickInput),
                jacksonTypeRef<BetaBrowserDoubleClickInput>(),
            )

        assertThat(roundtrippedBetaBrowserDoubleClickInput).isEqualTo(betaBrowserDoubleClickInput)
    }
}
