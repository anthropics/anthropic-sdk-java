package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrganizationTypeNotSupportedErrorDetailsTest {

    @Test
    fun create() {
        val organizationTypeNotSupportedErrorDetails =
            OrganizationTypeNotSupportedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val organizationTypeNotSupportedErrorDetails =
            OrganizationTypeNotSupportedErrorDetails.builder().build()

        val roundtrippedOrganizationTypeNotSupportedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(organizationTypeNotSupportedErrorDetails),
                jacksonTypeRef<OrganizationTypeNotSupportedErrorDetails>(),
            )

        assertThat(roundtrippedOrganizationTypeNotSupportedErrorDetails)
            .isEqualTo(organizationTypeNotSupportedErrorDetails)
    }
}
