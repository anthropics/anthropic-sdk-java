// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsImageBlockTest {

    @Test
    fun create() {
        val betaManagedAgentsImageBlock =
            BetaManagedAgentsImageBlock.builder()
                .source(
                    BetaManagedAgentsBase64ImageSource.builder()
                        .data("data")
                        .mediaType("media_type")
                        .type(BetaManagedAgentsBase64ImageSource.Type.BASE64)
                        .build()
                )
                .type(BetaManagedAgentsImageBlock.Type.IMAGE)
                .build()

        assertThat(betaManagedAgentsImageBlock.source())
            .isEqualTo(
                BetaManagedAgentsImageBlock.Source.ofBase64(
                    BetaManagedAgentsBase64ImageSource.builder()
                        .data("data")
                        .mediaType("media_type")
                        .type(BetaManagedAgentsBase64ImageSource.Type.BASE64)
                        .build()
                )
            )
        assertThat(betaManagedAgentsImageBlock.type())
            .isEqualTo(BetaManagedAgentsImageBlock.Type.IMAGE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsImageBlock =
            BetaManagedAgentsImageBlock.builder()
                .source(
                    BetaManagedAgentsBase64ImageSource.builder()
                        .data("data")
                        .mediaType("media_type")
                        .type(BetaManagedAgentsBase64ImageSource.Type.BASE64)
                        .build()
                )
                .type(BetaManagedAgentsImageBlock.Type.IMAGE)
                .build()

        val roundtrippedBetaManagedAgentsImageBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsImageBlock),
                jacksonTypeRef<BetaManagedAgentsImageBlock>(),
            )

        assertThat(roundtrippedBetaManagedAgentsImageBlock).isEqualTo(betaManagedAgentsImageBlock)
    }
}
