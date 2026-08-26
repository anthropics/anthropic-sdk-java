// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExternalKeyDeleteResponseTest {

    @Test
    fun create() {
        val externalKeyDeleteResponse =
            ExternalKeyDeleteResponse.of("ekey_01AbCdEfGhIjKlMnOpQrStUv")

        assertThat(externalKeyDeleteResponse.id()).isEqualTo("ekey_01AbCdEfGhIjKlMnOpQrStUv")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val externalKeyDeleteResponse =
            ExternalKeyDeleteResponse.of("ekey_01AbCdEfGhIjKlMnOpQrStUv")

        val roundtrippedExternalKeyDeleteResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(externalKeyDeleteResponse),
                jacksonTypeRef<ExternalKeyDeleteResponse>(),
            )

        assertThat(roundtrippedExternalKeyDeleteResponse).isEqualTo(externalKeyDeleteResponse)
    }
}
