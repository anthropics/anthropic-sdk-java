package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsFederatedActorOidcProviderTest {

    @Test
    fun create() {
        val betaAnalyticsFederatedActorOidcProvider =
            BetaAnalyticsFederatedActorOidcProvider.of("issuer")

        assertThat(betaAnalyticsFederatedActorOidcProvider.issuer()).contains("issuer")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsFederatedActorOidcProvider =
            BetaAnalyticsFederatedActorOidcProvider.of("issuer")

        val roundtrippedBetaAnalyticsFederatedActorOidcProvider =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsFederatedActorOidcProvider),
                jacksonTypeRef<BetaAnalyticsFederatedActorOidcProvider>(),
            )

        assertThat(roundtrippedBetaAnalyticsFederatedActorOidcProvider)
            .isEqualTo(betaAnalyticsFederatedActorOidcProvider)
    }
}
