// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.rules

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaFederationRuleMatchTest {

    @Test
    fun create() {
        val betaFederationRuleMatch =
            BetaFederationRuleMatch.builder()
                .audience("audience")
                .claims(
                    BetaFederationRuleMatch.Claims.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .condition("condition")
                .subjectPrefix("subject_prefix")
                .build()

        assertThat(betaFederationRuleMatch.audience()).contains("audience")
        assertThat(betaFederationRuleMatch.claims())
            .contains(
                BetaFederationRuleMatch.Claims.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(betaFederationRuleMatch.condition()).contains("condition")
        assertThat(betaFederationRuleMatch.subjectPrefix()).contains("subject_prefix")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaFederationRuleMatch =
            BetaFederationRuleMatch.builder()
                .audience("audience")
                .claims(
                    BetaFederationRuleMatch.Claims.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .condition("condition")
                .subjectPrefix("subject_prefix")
                .build()

        val roundtrippedBetaFederationRuleMatch =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaFederationRuleMatch),
                jacksonTypeRef<BetaFederationRuleMatch>(),
            )

        assertThat(roundtrippedBetaFederationRuleMatch).isEqualTo(betaFederationRuleMatch)
    }
}
