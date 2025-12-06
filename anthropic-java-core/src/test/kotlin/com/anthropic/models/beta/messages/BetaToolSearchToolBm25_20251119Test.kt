// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolSearchToolBm25_20251119Test {

    @Test
    fun create() {
        val betaToolSearchToolBm25_20251119 =
            BetaToolSearchToolBm25_20251119.builder()
                .type(BetaToolSearchToolBm25_20251119.Type.TOOL_SEARCH_TOOL_BM25_20251119)
                .addAllowedCaller(BetaToolSearchToolBm25_20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        assertThat(betaToolSearchToolBm25_20251119.type())
            .isEqualTo(BetaToolSearchToolBm25_20251119.Type.TOOL_SEARCH_TOOL_BM25_20251119)
        assertThat(betaToolSearchToolBm25_20251119.allowedCallers().getOrNull())
            .containsExactly(BetaToolSearchToolBm25_20251119.AllowedCaller.DIRECT)
        assertThat(betaToolSearchToolBm25_20251119.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaToolSearchToolBm25_20251119.deferLoading()).contains(true)
        assertThat(betaToolSearchToolBm25_20251119.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolSearchToolBm25_20251119 =
            BetaToolSearchToolBm25_20251119.builder()
                .type(BetaToolSearchToolBm25_20251119.Type.TOOL_SEARCH_TOOL_BM25_20251119)
                .addAllowedCaller(BetaToolSearchToolBm25_20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val roundtrippedBetaToolSearchToolBm25_20251119 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolSearchToolBm25_20251119),
                jacksonTypeRef<BetaToolSearchToolBm25_20251119>(),
            )

        assertThat(roundtrippedBetaToolSearchToolBm25_20251119)
            .isEqualTo(betaToolSearchToolBm25_20251119)
    }
}
