// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaOrganizationTest {

    @Test
    fun create() {
        val betaOrganization =
            BetaOrganization.builder()
                .id("12345678-1234-5678-1234-567812345678")
                .name("Organization Name")
                .build()

        assertThat(betaOrganization.id()).isEqualTo("12345678-1234-5678-1234-567812345678")
        assertThat(betaOrganization.name()).isEqualTo("Organization Name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaOrganization =
            BetaOrganization.builder()
                .id("12345678-1234-5678-1234-567812345678")
                .name("Organization Name")
                .build()

        val roundtrippedBetaOrganization =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaOrganization),
                jacksonTypeRef<BetaOrganization>(),
            )

        assertThat(roundtrippedBetaOrganization).isEqualTo(betaOrganization)
    }
}
