package com.anthropic.models

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LinkedOrganizationSpendLimitReachedErrorDetailsTest {

    @Test
    fun create() {
        val linkedOrganizationSpendLimitReachedErrorDetails =
            LinkedOrganizationSpendLimitReachedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val linkedOrganizationSpendLimitReachedErrorDetails =
            LinkedOrganizationSpendLimitReachedErrorDetails.builder().build()

        val roundtrippedLinkedOrganizationSpendLimitReachedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(linkedOrganizationSpendLimitReachedErrorDetails),
                jacksonTypeRef<LinkedOrganizationSpendLimitReachedErrorDetails>(),
            )

        assertThat(roundtrippedLinkedOrganizationSpendLimitReachedErrorDetails)
            .isEqualTo(linkedOrganizationSpendLimitReachedErrorDetails)
    }
}
