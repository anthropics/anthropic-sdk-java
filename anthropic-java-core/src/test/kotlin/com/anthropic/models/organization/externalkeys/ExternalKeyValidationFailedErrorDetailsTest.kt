package com.anthropic.models.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalKeyValidationFailedErrorDetailsTest {

    @Test
    fun create() {
        val externalKeyValidationFailedErrorDetails =
            ExternalKeyValidationFailedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val externalKeyValidationFailedErrorDetails =
            ExternalKeyValidationFailedErrorDetails.builder().build()

        val roundtrippedExternalKeyValidationFailedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(externalKeyValidationFailedErrorDetails),
                jacksonTypeRef<ExternalKeyValidationFailedErrorDetails>(),
            )

        assertThat(roundtrippedExternalKeyValidationFailedErrorDetails)
            .isEqualTo(externalKeyValidationFailedErrorDetails)
    }
}
