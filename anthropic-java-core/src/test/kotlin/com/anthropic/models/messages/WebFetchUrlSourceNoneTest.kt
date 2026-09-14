package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchUrlSourceNoneTest {

    @Test
    fun create() {
        val webFetchUrlSourceNone = WebFetchUrlSourceNone.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchUrlSourceNone = WebFetchUrlSourceNone.builder().build()

        val roundtrippedWebFetchUrlSourceNone =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchUrlSourceNone),
                jacksonTypeRef<WebFetchUrlSourceNone>(),
            )

        assertThat(roundtrippedWebFetchUrlSourceNone).isEqualTo(webFetchUrlSourceNone)
    }
}
