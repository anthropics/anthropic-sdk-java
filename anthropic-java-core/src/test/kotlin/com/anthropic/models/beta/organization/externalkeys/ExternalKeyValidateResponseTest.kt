// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalKeyValidateResponseTest {

    @Test
    fun create() {
        val externalKeyValidateResponse =
            ExternalKeyValidateResponse.builder()
                .error("error")
                .status(ExternalKeyValidateResponse.Status.FAILURE)
                .build()

        assertThat(externalKeyValidateResponse.error()).contains("error")
        assertThat(externalKeyValidateResponse.status())
            .isEqualTo(ExternalKeyValidateResponse.Status.FAILURE)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val externalKeyValidateResponse =
            ExternalKeyValidateResponse.builder()
                .error("error")
                .status(ExternalKeyValidateResponse.Status.FAILURE)
                .build()

        val roundtrippedExternalKeyValidateResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(externalKeyValidateResponse),
                jacksonTypeRef<ExternalKeyValidateResponse>(),
            )

        assertThat(roundtrippedExternalKeyValidateResponse).isEqualTo(externalKeyValidateResponse)
    }
}
