// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SignatureDeltaTest {

    @Test
    fun create() {
        val signatureDelta = SignatureDelta.builder().signature("signature").build()

        assertThat(signatureDelta.signature()).isEqualTo("signature")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val signatureDelta = SignatureDelta.builder().signature("signature").build()

        val roundtrippedSignatureDelta =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(signatureDelta),
                jacksonTypeRef<SignatureDelta>(),
            )

        assertThat(roundtrippedSignatureDelta).isEqualTo(signatureDelta)
    }
}
