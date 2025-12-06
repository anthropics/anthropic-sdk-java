// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaToolBash20241022Test {

    @Test
    fun create() {
        val betaToolBash20241022 =
            BetaToolBash20241022.builder()
                .addAllowedCaller(BetaToolBash20241022.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolBash20241022.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        assertThat(betaToolBash20241022.allowedCallers().getOrNull())
            .containsExactly(BetaToolBash20241022.AllowedCaller.DIRECT)
        assertThat(betaToolBash20241022.cacheControl())
            .contains(
                BetaCacheControlEphemeral.builder()
                    .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                    .build()
            )
        assertThat(betaToolBash20241022.deferLoading()).contains(true)
        assertThat(betaToolBash20241022.inputExamples().getOrNull())
            .containsExactly(
                BetaToolBash20241022.InputExample.builder()
                    .putAdditionalProperty("foo", JsonValue.from("bar"))
                    .build()
            )
        assertThat(betaToolBash20241022.strict()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaToolBash20241022 =
            BetaToolBash20241022.builder()
                .addAllowedCaller(BetaToolBash20241022.AllowedCaller.DIRECT)
                .cacheControl(
                    BetaCacheControlEphemeral.builder()
                        .ttl(BetaCacheControlEphemeral.Ttl.TTL_5M)
                        .build()
                )
                .deferLoading(true)
                .addInputExample(
                    BetaToolBash20241022.InputExample.builder()
                        .putAdditionalProperty("foo", JsonValue.from("bar"))
                        .build()
                )
                .strict(true)
                .build()

        val roundtrippedBetaToolBash20241022 =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaToolBash20241022),
                jacksonTypeRef<BetaToolBash20241022>(),
            )

        assertThat(roundtrippedBetaToolBash20241022).isEqualTo(betaToolBash20241022)
    }
}
