package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsFederatedActorTest {

    @Test
    fun create() {
        val betaAnalyticsFederatedActor =
            BetaAnalyticsFederatedActor.builder()
                .provider(
                    BetaAnalyticsFederatedActorAwsProvider.builder()
                        .accountId("account_id")
                        .signedPrincipal("signed_principal")
                        .build()
                )
                .subject("subject")
                .build()

        assertThat(betaAnalyticsFederatedActor.provider())
            .isEqualTo(
                BetaAnalyticsFederatedActorProvider.ofAws(
                    BetaAnalyticsFederatedActorAwsProvider.builder()
                        .accountId("account_id")
                        .signedPrincipal("signed_principal")
                        .build()
                )
            )
        assertThat(betaAnalyticsFederatedActor.subject()).contains("subject")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsFederatedActor =
            BetaAnalyticsFederatedActor.builder()
                .provider(
                    BetaAnalyticsFederatedActorAwsProvider.builder()
                        .accountId("account_id")
                        .signedPrincipal("signed_principal")
                        .build()
                )
                .subject("subject")
                .build()

        val roundtrippedBetaAnalyticsFederatedActor =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsFederatedActor),
                jacksonTypeRef<BetaAnalyticsFederatedActor>(),
            )

        assertThat(roundtrippedBetaAnalyticsFederatedActor).isEqualTo(betaAnalyticsFederatedActor)
    }
}
