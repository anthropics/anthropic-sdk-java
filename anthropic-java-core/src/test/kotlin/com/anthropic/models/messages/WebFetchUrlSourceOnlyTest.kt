package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchUrlSourceOnlyTest {

    @Test
    fun create() {
        val webFetchUrlSourceOnly =
            WebFetchUrlSourceOnly.builder()
                .addTool(WebFetchUrlSourceToolReference.of("name"))
                .build()

        assertThat(webFetchUrlSourceOnly.tools())
            .containsExactly(WebFetchUrlSourceToolReference.of("name"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchUrlSourceOnly =
            WebFetchUrlSourceOnly.builder()
                .addTool(WebFetchUrlSourceToolReference.of("name"))
                .build()

        val roundtrippedWebFetchUrlSourceOnly =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchUrlSourceOnly),
                jacksonTypeRef<WebFetchUrlSourceOnly>(),
            )

        assertThat(roundtrippedWebFetchUrlSourceOnly).isEqualTo(webFetchUrlSourceOnly)
    }
}
