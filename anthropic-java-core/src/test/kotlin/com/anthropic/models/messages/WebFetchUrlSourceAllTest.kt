package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class WebFetchUrlSourceAllTest {

    @Test
    fun create() {
        val webFetchUrlSourceAll = WebFetchUrlSourceAll.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val webFetchUrlSourceAll = WebFetchUrlSourceAll.builder().build()

        val roundtrippedWebFetchUrlSourceAll =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(webFetchUrlSourceAll),
                jacksonTypeRef<WebFetchUrlSourceAll>(),
            )

        assertThat(roundtrippedWebFetchUrlSourceAll).isEqualTo(webFetchUrlSourceAll)
    }
}
