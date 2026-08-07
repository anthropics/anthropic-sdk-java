// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRequestToolAdditionBlockTest {

    @Test
    fun create() {
        val betaRequestToolAdditionBlock =
            BetaRequestToolAdditionBlock.builder()
                .referenceTool("name")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaRequestToolAdditionBlock.tool())
            .isEqualTo(BetaRequestToolAdditionBlock.Tool.ofReference("name"))
        assertThat(betaRequestToolAdditionBlock.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRequestToolAdditionBlock =
            BetaRequestToolAdditionBlock.builder()
                .referenceTool("name")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaRequestToolAdditionBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRequestToolAdditionBlock),
                jacksonTypeRef<BetaRequestToolAdditionBlock>(),
            )

        assertThat(roundtrippedBetaRequestToolAdditionBlock).isEqualTo(betaRequestToolAdditionBlock)
    }
}
