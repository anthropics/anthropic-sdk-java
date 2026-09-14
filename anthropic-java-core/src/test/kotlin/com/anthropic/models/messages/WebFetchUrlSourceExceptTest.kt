package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchUrlSourceExceptTest {

    @Test
    fun create() {
        val webFetchUrlSourceExcept =
            WebFetchUrlSourceExcept.builder()
                .addTool(WebFetchUrlSourceToolReference.of("name"))
                .build()

        assertThat(webFetchUrlSourceExcept.tools())
            .containsExactly(WebFetchUrlSourceToolReference.of("name"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchUrlSourceExcept =
            WebFetchUrlSourceExcept.builder()
                .addTool(WebFetchUrlSourceToolReference.of("name"))
                .build()

        val roundtrippedWebFetchUrlSourceExcept =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchUrlSourceExcept),
                jacksonTypeRef<WebFetchUrlSourceExcept>(),
            )

        assertThat(roundtrippedWebFetchUrlSourceExcept).isEqualTo(webFetchUrlSourceExcept)
    }
}
