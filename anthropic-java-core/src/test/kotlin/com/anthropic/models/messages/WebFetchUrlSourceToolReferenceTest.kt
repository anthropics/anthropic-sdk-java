package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchUrlSourceToolReferenceTest {

    @Test
    fun create() {
        val webFetchUrlSourceToolReference = WebFetchUrlSourceToolReference.of("name")

        assertThat(webFetchUrlSourceToolReference.name()).isEqualTo("name")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchUrlSourceToolReference = WebFetchUrlSourceToolReference.of("name")

        val roundtrippedWebFetchUrlSourceToolReference =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchUrlSourceToolReference),
                jacksonTypeRef<WebFetchUrlSourceToolReference>(),
            )

        assertThat(roundtrippedWebFetchUrlSourceToolReference)
            .isEqualTo(webFetchUrlSourceToolReference)
    }
}
