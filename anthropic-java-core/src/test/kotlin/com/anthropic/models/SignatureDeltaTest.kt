// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SignatureDeltaTest {

    @Test
    fun createSignatureDelta() {
        val signatureDelta = SignatureDelta.builder().signature("signature").build()
        assertThat(signatureDelta).isNotNull
        assertThat(signatureDelta.signature()).isEqualTo("signature")
    }
}
