package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaAnalyticsFederatedActorGcpProviderTest {

    @Test
    fun create() {
        val betaAnalyticsFederatedActorGcpProvider =
            BetaAnalyticsFederatedActorGcpProvider.of("project_number")

        assertThat(betaAnalyticsFederatedActorGcpProvider.projectNumber())
            .isEqualTo("project_number")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaAnalyticsFederatedActorGcpProvider =
            BetaAnalyticsFederatedActorGcpProvider.of("project_number")

        val roundtrippedBetaAnalyticsFederatedActorGcpProvider =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaAnalyticsFederatedActorGcpProvider),
                jacksonTypeRef<BetaAnalyticsFederatedActorGcpProvider>(),
            )

        assertThat(roundtrippedBetaAnalyticsFederatedActorGcpProvider)
            .isEqualTo(betaAnalyticsFederatedActorGcpProvider)
    }
}
