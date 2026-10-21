package com.anthropic.models.beta.deploymentruns

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaManagedAgentsOrganizationDisabledRunErrorTest {

    @Test
    fun create() {
        val betaManagedAgentsOrganizationDisabledRunError =
            BetaManagedAgentsOrganizationDisabledRunError.of("message")

        assertThat(betaManagedAgentsOrganizationDisabledRunError.message()).isEqualTo("message")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaManagedAgentsOrganizationDisabledRunError =
            BetaManagedAgentsOrganizationDisabledRunError.of("message")

        val roundtrippedBetaManagedAgentsOrganizationDisabledRunError =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaManagedAgentsOrganizationDisabledRunError),
                jacksonTypeRef<BetaManagedAgentsOrganizationDisabledRunError>(),
            )

        assertThat(roundtrippedBetaManagedAgentsOrganizationDisabledRunError)
            .isEqualTo(betaManagedAgentsOrganizationDisabledRunError)
    }
}
