// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationsConfigParamTest {

    @Test
    fun create() {
        val citationsConfigParam = CitationsConfigParam.builder().enabled(true).build()

        assertThat(citationsConfigParam.enabled()).contains(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationsConfigParam = CitationsConfigParam.builder().enabled(true).build()

        val roundtrippedCitationsConfigParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(citationsConfigParam),
                jacksonTypeRef<CitationsConfigParam>(),
            )

        assertThat(roundtrippedCitationsConfigParam).isEqualTo(citationsConfigParam)
    }
}
