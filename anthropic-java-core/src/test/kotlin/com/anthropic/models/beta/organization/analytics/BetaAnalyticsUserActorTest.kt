package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsUserActorTest {

    @Test
    fun create() {
        val betaAnalyticsUserActor =
            BetaAnalyticsUserActor.builder()
                .deleted(true)
                .emailAddress("jane@example.com")
                .name("Jane Smith")
                .userId("user_01AbCdEfGhIjKlMnOpQrSt")
                .build()

        assertThat(betaAnalyticsUserActor.deleted()).isEqualTo(true)
        assertThat(betaAnalyticsUserActor.emailAddress()).contains("jane@example.com")
        assertThat(betaAnalyticsUserActor.name()).contains("Jane Smith")
        assertThat(betaAnalyticsUserActor.userId()).isEqualTo("user_01AbCdEfGhIjKlMnOpQrSt")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsUserActor =
            BetaAnalyticsUserActor.builder()
                .deleted(true)
                .emailAddress("jane@example.com")
                .name("Jane Smith")
                .userId("user_01AbCdEfGhIjKlMnOpQrSt")
                .build()

        val roundtrippedBetaAnalyticsUserActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsUserActor),
                jacksonTypeRef<BetaAnalyticsUserActor>(),
            )

        assertThat(roundtrippedBetaAnalyticsUserActor).isEqualTo(betaAnalyticsUserActor)
    }
}
