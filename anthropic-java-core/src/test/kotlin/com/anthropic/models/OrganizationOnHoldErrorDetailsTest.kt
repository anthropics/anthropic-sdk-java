package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrganizationOnHoldErrorDetailsTest {

    @Test
    fun create() {
        val organizationOnHoldErrorDetails =
            OrganizationOnHoldErrorDetails.builder().appealUrl("appeal_url").build()

        assertThat(organizationOnHoldErrorDetails.appealUrl()).contains("appeal_url")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val organizationOnHoldErrorDetails =
            OrganizationOnHoldErrorDetails.builder().appealUrl("appeal_url").build()

        val roundtrippedOrganizationOnHoldErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(organizationOnHoldErrorDetails),
                jacksonTypeRef<OrganizationOnHoldErrorDetails>(),
            )

        assertThat(roundtrippedOrganizationOnHoldErrorDetails)
            .isEqualTo(organizationOnHoldErrorDetails)
    }
}
