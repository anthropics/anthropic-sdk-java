package com.anthropic.models.organization.federation.issuers

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class JwksExplicitUrlTest {

    @Test
    fun create() {
        val jwksExplicitUrl = JwksExplicitUrl.builder().url("x").caCertPem("ca_cert_pem").build()

        assertThat(jwksExplicitUrl.url()).isEqualTo("x")
        assertThat(jwksExplicitUrl.caCertPem()).contains("ca_cert_pem")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val jwksExplicitUrl = JwksExplicitUrl.builder().url("x").caCertPem("ca_cert_pem").build()

        val roundtrippedJwksExplicitUrl =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(jwksExplicitUrl),
                jacksonTypeRef<JwksExplicitUrl>(),
            )

        assertThat(roundtrippedJwksExplicitUrl).isEqualTo(jwksExplicitUrl)
    }
}
