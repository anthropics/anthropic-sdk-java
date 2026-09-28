package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CacheMissPreviousMessageNotFoundTest {

    @Test
    fun create() {
        val cacheMissPreviousMessageNotFound = CacheMissPreviousMessageNotFound.builder().build()
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val cacheMissPreviousMessageNotFound = CacheMissPreviousMessageNotFound.builder().build()

        val roundtrippedCacheMissPreviousMessageNotFound =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(cacheMissPreviousMessageNotFound),
                jacksonTypeRef<CacheMissPreviousMessageNotFound>(),
            )

        assertThat(roundtrippedCacheMissPreviousMessageNotFound)
            .isEqualTo(cacheMissPreviousMessageNotFound)
    }
}
