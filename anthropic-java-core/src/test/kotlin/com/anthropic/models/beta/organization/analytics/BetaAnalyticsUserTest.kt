package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsUserTest {

    @Test
    fun create() {
        val betaAnalyticsUser =
            BetaAnalyticsUser.builder().id("id").emailAddress("email_address").build()

        assertThat(betaAnalyticsUser.id()).isEqualTo("id")
        assertThat(betaAnalyticsUser.emailAddress()).isEqualTo("email_address")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsUser =
            BetaAnalyticsUser.builder().id("id").emailAddress("email_address").build()

        val roundtrippedBetaAnalyticsUser =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsUser),
                jacksonTypeRef<BetaAnalyticsUser>(),
            )

        assertThat(roundtrippedBetaAnalyticsUser).isEqualTo(betaAnalyticsUser)
    }
}
