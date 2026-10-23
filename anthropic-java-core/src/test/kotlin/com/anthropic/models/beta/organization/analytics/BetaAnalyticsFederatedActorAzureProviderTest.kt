package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsFederatedActorAzureProviderTest {

    @Test
    fun create() {
        val betaAnalyticsFederatedActorAzureProvider =
            BetaAnalyticsFederatedActorAzureProvider.of("subscription_id")

        assertThat(betaAnalyticsFederatedActorAzureProvider.subscriptionId())
            .isEqualTo("subscription_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsFederatedActorAzureProvider =
            BetaAnalyticsFederatedActorAzureProvider.of("subscription_id")

        val roundtrippedBetaAnalyticsFederatedActorAzureProvider =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsFederatedActorAzureProvider),
                jacksonTypeRef<BetaAnalyticsFederatedActorAzureProvider>(),
            )

        assertThat(roundtrippedBetaAnalyticsFederatedActorAzureProvider)
            .isEqualTo(betaAnalyticsFederatedActorAzureProvider)
    }
}
