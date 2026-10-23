package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsFederatedActorAwsProviderTest {

    @Test
    fun create() {
        val betaAnalyticsFederatedActorAwsProvider =
            BetaAnalyticsFederatedActorAwsProvider.builder()
                .accountId("account_id")
                .signedPrincipal("signed_principal")
                .build()

        assertThat(betaAnalyticsFederatedActorAwsProvider.accountId()).isEqualTo("account_id")
        assertThat(betaAnalyticsFederatedActorAwsProvider.signedPrincipal())
            .isEqualTo("signed_principal")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsFederatedActorAwsProvider =
            BetaAnalyticsFederatedActorAwsProvider.builder()
                .accountId("account_id")
                .signedPrincipal("signed_principal")
                .build()

        val roundtrippedBetaAnalyticsFederatedActorAwsProvider =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsFederatedActorAwsProvider),
                jacksonTypeRef<BetaAnalyticsFederatedActorAwsProvider>(),
            )

        assertThat(roundtrippedBetaAnalyticsFederatedActorAwsProvider)
            .isEqualTo(betaAnalyticsFederatedActorAwsProvider)
    }
}
