package com.anthropic.models.organization.federation.rules

import com.anthropic.core.JsonValue
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FederationRuleMatchTest {

    @Test
    fun create() {
        val federationRuleMatch =
            FederationRuleMatch.builder()
                .audience("audience")
                .claims(
                    FederationRuleMatch.Claims.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .condition("condition")
                .subjectPrefix("subject_prefix")
                .build()

        assertThat(federationRuleMatch.audience()).contains("audience")
        assertThat(federationRuleMatch.claims())
            .contains(
                FederationRuleMatch.Claims.builder()
                    .putAdditionalProperty("foo", JsonValue.from("string"))
                    .build()
            )
        assertThat(federationRuleMatch.condition()).contains("condition")
        assertThat(federationRuleMatch.subjectPrefix()).contains("subject_prefix")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val federationRuleMatch =
            FederationRuleMatch.builder()
                .audience("audience")
                .claims(
                    FederationRuleMatch.Claims.builder()
                        .putAdditionalProperty("foo", JsonValue.from("string"))
                        .build()
                )
                .condition("condition")
                .subjectPrefix("subject_prefix")
                .build()

        val roundtrippedFederationRuleMatch =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(federationRuleMatch),
                jacksonTypeRef<FederationRuleMatch>(),
            )

        assertThat(roundtrippedFederationRuleMatch).isEqualTo(federationRuleMatch)
    }
}
