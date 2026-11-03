package com.anthropic.models.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalKeyChangedErrorDetailsTest {

    @Test
    fun create() {
        val externalKeyChangedErrorDetails = ExternalKeyChangedErrorDetails.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val externalKeyChangedErrorDetails = ExternalKeyChangedErrorDetails.builder().build()

        val roundtrippedExternalKeyChangedErrorDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(externalKeyChangedErrorDetails),
                jacksonTypeRef<ExternalKeyChangedErrorDetails>(),
            )

        assertThat(roundtrippedExternalKeyChangedErrorDetails)
            .isEqualTo(externalKeyChangedErrorDetails)
    }
}
