// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaCodeExecutionTool20250522Test {

    @Test
    fun create() {
        val betaCodeExecutionTool20250522 =
            BetaCodeExecutionTool20250522.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        assertThat(betaCodeExecutionTool20250522.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaCodeExecutionTool20250522 =
            BetaCodeExecutionTool20250522.builder()
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .build()

        val roundtrippedBetaCodeExecutionTool20250522 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaCodeExecutionTool20250522),
                jacksonTypeRef<BetaCodeExecutionTool20250522>(),
            )

        assertThat(roundtrippedBetaCodeExecutionTool20250522)
            .isEqualTo(betaCodeExecutionTool20250522)
    }
}
