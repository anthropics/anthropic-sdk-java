// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CitationsConfigTest {

    @Test
    fun create() {
        val citationsConfig = CitationsConfig.builder().enabled(true).build()

        assertThat(citationsConfig.enabled()).isEqualTo(true)
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val citationsConfig = CitationsConfig.builder().enabled(true).build()

        val roundtrippedCitationsConfig =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(citationsConfig),
                jacksonTypeRef<CitationsConfig>(),
            )

        assertThat(roundtrippedCitationsConfig).isEqualTo(citationsConfig)
    }
}
