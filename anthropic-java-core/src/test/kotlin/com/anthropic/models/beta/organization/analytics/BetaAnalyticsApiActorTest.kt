package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsApiActorTest {

    @Test
    fun create() {
        val betaAnalyticsApiActor = BetaAnalyticsApiActor.of("apikey_01BcDeFgHjKmNpQrStUvWxYz")

        assertThat(betaAnalyticsApiActor.apiKeyId()).isEqualTo("apikey_01BcDeFgHjKmNpQrStUvWxYz")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsApiActor = BetaAnalyticsApiActor.of("apikey_01BcDeFgHjKmNpQrStUvWxYz")

        val roundtrippedBetaAnalyticsApiActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsApiActor),
                jacksonTypeRef<BetaAnalyticsApiActor>(),
            )

        assertThat(roundtrippedBetaAnalyticsApiActor).isEqualTo(betaAnalyticsApiActor)
    }
}
