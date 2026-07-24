// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaRequestToolRemovalBlockTest {

    @Test
    fun create() {
        val betaRequestToolRemovalBlock =
            BetaRequestToolRemovalBlock.builder()
                .referenceTool("name")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaRequestToolRemovalBlock.tool())
            .isEqualTo(
                BetaRequestToolRemovalBlock.Tool.ofReference(
                    BetaToolChangeToolReference.builder().name("name").build()
                )
            )
        assertThat(betaRequestToolRemovalBlock.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaRequestToolRemovalBlock =
            BetaRequestToolRemovalBlock.builder()
                .referenceTool("name")
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaRequestToolRemovalBlock =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaRequestToolRemovalBlock),
                jacksonTypeRef<BetaRequestToolRemovalBlock>(),
            )

        assertThat(roundtrippedBetaRequestToolRemovalBlock).isEqualTo(betaRequestToolRemovalBlock)
    }
}
