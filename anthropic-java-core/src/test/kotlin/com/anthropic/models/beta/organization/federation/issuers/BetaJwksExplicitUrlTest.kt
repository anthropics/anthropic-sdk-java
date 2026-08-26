// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.issuers

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaJwksExplicitUrlTest {

    @Test
    fun create() {
        val betaJwksExplicitUrl =
            BetaJwksExplicitUrl.builder().url("x").caCertPem("ca_cert_pem").build()

        assertThat(betaJwksExplicitUrl.url()).isEqualTo("x")
        assertThat(betaJwksExplicitUrl.caCertPem()).contains("ca_cert_pem")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaJwksExplicitUrl =
            BetaJwksExplicitUrl.builder().url("x").caCertPem("ca_cert_pem").build()

        val roundtrippedBetaJwksExplicitUrl =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaJwksExplicitUrl),
                jacksonTypeRef<BetaJwksExplicitUrl>(),
            )

        assertThat(roundtrippedBetaJwksExplicitUrl).isEqualTo(betaJwksExplicitUrl)
    }
}
