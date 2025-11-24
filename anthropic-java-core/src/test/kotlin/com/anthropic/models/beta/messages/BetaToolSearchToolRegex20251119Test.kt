// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolSearchToolRegex20251119Test {

    @Test
    fun create() {
        val betaToolSearchToolRegex20251119 =
            BetaToolSearchToolRegex20251119.builder()
                .type(BetaToolSearchToolRegex20251119.Type.TOOL_SEARCH_TOOL_REGEX_20251119)
                .addAllowedCaller(BetaToolSearchToolRegex20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        assertThat(betaToolSearchToolRegex20251119.type())
            .isEqualTo(BetaToolSearchToolRegex20251119.Type.TOOL_SEARCH_TOOL_REGEX_20251119)
        assertThat(betaToolSearchToolRegex20251119.allowedCallers().getOrNull())
            .containsExactly(BetaToolSearchToolRegex20251119.AllowedCaller.DIRECT)
        assertThat(betaToolSearchToolRegex20251119.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaToolSearchToolRegex20251119.deferLoading()).contains(true)
        assertThat(betaToolSearchToolRegex20251119.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolSearchToolRegex20251119 =
            BetaToolSearchToolRegex20251119.builder()
                .type(BetaToolSearchToolRegex20251119.Type.TOOL_SEARCH_TOOL_REGEX_20251119)
                .addAllowedCaller(BetaToolSearchToolRegex20251119.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .strict(true)
                .build()

        val roundtrippedBetaToolSearchToolRegex20251119 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolSearchToolRegex20251119),
                jacksonTypeRef<BetaToolSearchToolRegex20251119>(),
            )

        assertThat(roundtrippedBetaToolSearchToolRegex20251119)
            .isEqualTo(betaToolSearchToolRegex20251119)
    }
}
