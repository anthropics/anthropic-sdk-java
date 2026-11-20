package com.anthropic.models.organization

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ChangeOutcomeUnknownErrorDetailsTest {

    @Test
    fun create() {
        val changeOutcomeUnknownErrorDetails = ChangeOutcomeUnknownErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val changeOutcomeUnknownErrorDetails = ChangeOutcomeUnknownErrorDetails.builder().build()

        val roundtrippedChangeOutcomeUnknownErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(changeOutcomeUnknownErrorDetails),
                jacksonTypeRef<ChangeOutcomeUnknownErrorDetails>(),
            )

        assertThat(roundtrippedChangeOutcomeUnknownErrorDetails)
            .isEqualTo(changeOutcomeUnknownErrorDetails)
    }
}
