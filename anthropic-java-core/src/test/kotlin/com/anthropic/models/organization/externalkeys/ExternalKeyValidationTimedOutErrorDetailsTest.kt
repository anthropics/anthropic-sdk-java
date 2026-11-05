package com.anthropic.models.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalKeyValidationTimedOutErrorDetailsTest {

    @Test
    fun create() {
        val externalKeyValidationTimedOutErrorDetails =
            ExternalKeyValidationTimedOutErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val externalKeyValidationTimedOutErrorDetails =
            ExternalKeyValidationTimedOutErrorDetails.builder().build()

        val roundtrippedExternalKeyValidationTimedOutErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(externalKeyValidationTimedOutErrorDetails),
                jacksonTypeRef<ExternalKeyValidationTimedOutErrorDetails>(),
            )

        assertThat(roundtrippedExternalKeyValidationTimedOutErrorDetails)
            .isEqualTo(externalKeyValidationTimedOutErrorDetails)
    }
}
