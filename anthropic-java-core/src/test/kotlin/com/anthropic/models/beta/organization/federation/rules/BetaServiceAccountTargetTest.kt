// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.rules

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaServiceAccountTargetTest {

    @Test
    fun create() {
        val betaServiceAccountTarget =
            BetaServiceAccountTarget.builder()
                .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                .serviceAccountName("service_account_name")
                .build()

        assertThat(betaServiceAccountTarget.serviceAccountId())
            .isEqualTo("svac_01SDCCSbTxrXDpWc1phhtcfK")
        assertThat(betaServiceAccountTarget.serviceAccountName()).contains("service_account_name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaServiceAccountTarget =
            BetaServiceAccountTarget.builder()
                .serviceAccountId("svac_01SDCCSbTxrXDpWc1phhtcfK")
                .serviceAccountName("service_account_name")
                .build()

        val roundtrippedBetaServiceAccountTarget =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaServiceAccountTarget),
                jacksonTypeRef<BetaServiceAccountTarget>(),
            )

        assertThat(roundtrippedBetaServiceAccountTarget).isEqualTo(betaServiceAccountTarget)
    }
}
