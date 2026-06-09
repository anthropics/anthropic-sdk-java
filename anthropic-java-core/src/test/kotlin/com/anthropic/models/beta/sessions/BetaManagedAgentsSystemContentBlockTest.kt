// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsSystemContentBlockTest {

    @Test
    fun create() {
        val betaManagedAgentsSystemContentBlock =
            BetaManagedAgentsSystemContentBlock.builder()
                .text("Where is my order #1234?")
                .type(BetaManagedAgentsSystemContentBlock.Type.TEXT)
                .build()

        assertThat(betaManagedAgentsSystemContentBlock.text()).isEqualTo("Where is my order #1234?")
        assertThat(betaManagedAgentsSystemContentBlock.type())
            .isEqualTo(BetaManagedAgentsSystemContentBlock.Type.TEXT)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsSystemContentBlock =
            BetaManagedAgentsSystemContentBlock.builder()
                .text("Where is my order #1234?")
                .type(BetaManagedAgentsSystemContentBlock.Type.TEXT)
                .build()

        val roundtrippedBetaManagedAgentsSystemContentBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsSystemContentBlock),
                jacksonTypeRef<BetaManagedAgentsSystemContentBlock>(),
            )

        assertThat(roundtrippedBetaManagedAgentsSystemContentBlock)
            .isEqualTo(betaManagedAgentsSystemContentBlock)
    }
}
