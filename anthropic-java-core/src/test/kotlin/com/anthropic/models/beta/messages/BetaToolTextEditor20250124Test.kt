// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolTextEditor20250124Test {

    @Test
    fun create() {
        val betaToolTextEditor20250124 =
            BetaToolTextEditor20250124.builder()
                .addAllowedCaller(BetaToolTextEditor20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolTextEditor20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        assertThat(betaToolTextEditor20250124.allowedCallers().getOrNull())
            .containsExactly(BetaToolTextEditor20250124.AllowedCaller.DIRECT)
        assertThat(betaToolTextEditor20250124.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaToolTextEditor20250124.deferLoading()).contains(true)
        assertThat(betaToolTextEditor20250124.inputExamples().getOrNull())
            .containsExactly(
                BetaToolTextEditor20250124.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaToolTextEditor20250124.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolTextEditor20250124 =
            BetaToolTextEditor20250124.builder()
                .addAllowedCaller(BetaToolTextEditor20250124.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolTextEditor20250124.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val roundtrippedBetaToolTextEditor20250124 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolTextEditor20250124),
                jacksonTypeRef<BetaToolTextEditor20250124>(),
            )

        assertThat(roundtrippedBetaToolTextEditor20250124).isEqualTo(betaToolTextEditor20250124)
    }
}
