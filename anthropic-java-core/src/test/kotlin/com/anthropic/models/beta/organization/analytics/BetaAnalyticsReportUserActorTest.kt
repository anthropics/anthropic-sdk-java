package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsReportUserActorTest {

    @Test
    fun create() {
        val betaAnalyticsReportUserActor =
            BetaAnalyticsReportUserActor.builder()
                .deleted(false)
                .emailAddress("user@emaildomain.com")
                .name("Jane Doe")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        assertThat(betaAnalyticsReportUserActor.deleted()).isEqualTo(false)
        assertThat(betaAnalyticsReportUserActor.emailAddress()).contains("user@emaildomain.com")
        assertThat(betaAnalyticsReportUserActor.name()).contains("Jane Doe")
        assertThat(betaAnalyticsReportUserActor.userId()).isEqualTo("user_01WxYzAbCdEfGhJkMnPqRsTu")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsReportUserActor =
            BetaAnalyticsReportUserActor.builder()
                .deleted(false)
                .emailAddress("user@emaildomain.com")
                .name("Jane Doe")
                .userId("user_01WxYzAbCdEfGhJkMnPqRsTu")
                .build()

        val roundtrippedBetaAnalyticsReportUserActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsReportUserActor),
                jacksonTypeRef<BetaAnalyticsReportUserActor>(),
            )

        assertThat(roundtrippedBetaAnalyticsReportUserActor).isEqualTo(betaAnalyticsReportUserActor)
    }
}
