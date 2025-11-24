// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolBash20250124Test {

    @Test
    fun create() {
        val betaToolBash20250124 =
            BetaToolBash20250124.builder()
                .addAllowedCaller(BetaToolBash20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolBash20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        assertThat(betaToolBash20250124.allowedCallers().getOrNull())
            .containsExactly(BetaToolBash20250124.AllowedCaller.DIRECT)
        assertThat(betaToolBash20250124.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaToolBash20250124.deferLoading()).contains(true)
        assertThat(betaToolBash20250124.inputExamples().getOrNull())
            .containsExactly(
                BetaToolBash20250124.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaToolBash20250124.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolBash20250124 =
            BetaToolBash20250124.builder()
                .addAllowedCaller(BetaToolBash20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolBash20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val roundtrippedBetaToolBash20250124 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolBash20250124),
                jacksonTypeRef<BetaToolBash20250124>(),
            )

        assertThat(roundtrippedBetaToolBash20250124).isEqualTo(betaToolBash20250124)
    }
}
