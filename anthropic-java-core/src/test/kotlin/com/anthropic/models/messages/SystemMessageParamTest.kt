package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SystemMessageParamTest {

    @Test
    fun create() {
        val systemMessageParam = SystemMessageParam.builder().content("string").build()

        assertThat(systemMessageParam.content())
            .isEqualTo(SystemMessageParam.Content.ofString("string"))
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val systemMessageParam = SystemMessageParam.builder().content("string").build()

        val roundtrippedSystemMessageParam =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(systemMessageParam),
                jacksonTypeRef<SystemMessageParam>(),
            )

        assertThat(roundtrippedSystemMessageParam).isEqualTo(systemMessageParam)
    }
}
