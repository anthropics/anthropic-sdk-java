package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ParentOrganizationRequiredErrorDetailsTest {

    @Test
    fun create() {
        val parentOrganizationRequiredErrorDetails =
            ParentOrganizationRequiredErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val parentOrganizationRequiredErrorDetails =
            ParentOrganizationRequiredErrorDetails.builder().build()

        val roundtrippedParentOrganizationRequiredErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(parentOrganizationRequiredErrorDetails),
                jacksonTypeRef<ParentOrganizationRequiredErrorDetails>(),
            )

        assertThat(roundtrippedParentOrganizationRequiredErrorDetails)
            .isEqualTo(parentOrganizationRequiredErrorDetails)
    }
}
