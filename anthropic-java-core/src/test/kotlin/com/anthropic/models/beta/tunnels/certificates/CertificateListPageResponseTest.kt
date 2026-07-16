// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.tunnels.certificates

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CertificateListPageResponseTest {

    @Test
    fun create() {
        val certificateListPageResponse =
            CertificateListPageResponse.builder()
                .addData(
                    BetaTunnelCertificate.builder()
                        .id("id")
                        .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .fingerprint("fingerprint")
                        .tunnelId("tunnel_id")
                        .build()
                )
                .nextPage("next_page")
                .build()

        assertThat(certificateListPageResponse.data())
            .containsExactly(
                BetaTunnelCertificate.builder()
                    .id("id")
                    .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                    .fingerprint("fingerprint")
                    .tunnelId("tunnel_id")
                    .build()
            )
        assertThat(certificateListPageResponse.nextPage()).contains("next_page")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val certificateListPageResponse =
            CertificateListPageResponse.builder()
                .addData(
                    BetaTunnelCertificate.builder()
                        .id("id")
                        .archivedAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .createdAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .expiresAt(OffsetDateTime.parse("2019-12-27T18:11:19.117Z"))
                        .fingerprint("fingerprint")
                        .tunnelId("tunnel_id")
                        .build()
                )
                .nextPage("next_page")
                .build()

        val roundtrippedCertificateListPageResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(certificateListPageResponse),
                jacksonTypeRef<CertificateListPageResponse>(),
            )

        assertThat(roundtrippedCertificateListPageResponse).isEqualTo(certificateListPageResponse)
    }
}
