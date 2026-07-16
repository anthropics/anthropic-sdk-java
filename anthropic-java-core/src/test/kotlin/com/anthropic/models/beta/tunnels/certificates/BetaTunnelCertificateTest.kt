// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.tunnels.certificates

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaTunnelCertificateTest {

    @Test
    fun create() {
        val betaTunnelCertificate =
            BetaTunnelCertificate.builder()
                .id("id")
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .fingerprint("fingerprint")
                .tunnelId("tunnel_id")
                .build()

        assertThat(betaTunnelCertificate.id()).isEqualTo("id")
        assertThat(betaTunnelCertificate.archivedAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaTunnelCertificate.createdAt())
            .isEqualTo(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaTunnelCertificate.expiresAt())
            .contains(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
        assertThat(betaTunnelCertificate.fingerprint()).isEqualTo("fingerprint")
        assertThat(betaTunnelCertificate.tunnelId()).isEqualTo("tunnel_id")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaTunnelCertificate =
            BetaTunnelCertificate.builder()
                .id("id")
                .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                .fingerprint("fingerprint")
                .tunnelId("tunnel_id")
                .build()

        val roundtrippedBetaTunnelCertificate =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaTunnelCertificate),
                jacksonTypeRef<BetaTunnelCertificate>(),
            )

        assertThat(roundtrippedBetaTunnelCertificate).isEqualTo(betaTunnelCertificate)
    }
}
