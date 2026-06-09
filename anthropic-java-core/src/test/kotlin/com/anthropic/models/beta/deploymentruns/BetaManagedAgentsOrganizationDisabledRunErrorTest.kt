// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsOrganizationDisabledRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsOrganizationDisabledRunError =
            BetaManagedAgentsOrganizationDisabledRunError.builder()
                .message("message")
                .type(
                    BetaManagedAgentsOrganizationDisabledRunError.Type.ORGANIZATION_DISABLED_ERROR
                )
                .build()

        assertThat(betaManagedAgentsOrganizationDisabledRunError.message()).isEqualTo("message")
        assertThat(betaManagedAgentsOrganizationDisabledRunError.type())
            .isEqualTo(
                BetaManagedAgentsOrganizationDisabledRunError.Type.ORGANIZATION_DISABLED_ERROR
            )
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsOrganizationDisabledRunError =
            BetaManagedAgentsOrganizationDisabledRunError.builder()
                .message("message")
                .type(
                    BetaManagedAgentsOrganizationDisabledRunError.Type.ORGANIZATION_DISABLED_ERROR
                )
                .build()

        val roundtrippedBetaManagedAgentsOrganizationDisabledRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsOrganizationDisabledRunError),
                jacksonTypeRef<BetaManagedAgentsOrganizationDisabledRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsOrganizationDisabledRunError)
            .isEqualTo(betaManagedAgentsOrganizationDisabledRunError)
    }
}
