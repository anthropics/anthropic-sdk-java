// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import kotlinx.kmp.util.core.jsonMapper
import kotlinx.kmp.util.core.json.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RefusalStopDetailsTest {

    @Test
    fun create() {
        val refusalStopDetails =
            RefusalStopDetails.builder()
                .category(RefusalStopDetails.Category.CYBER)
                .explanation("explanation")
                .build()

        assertThat(refusalStopDetails.category()).contains(RefusalStopDetails.Category.CYBER)
        assertThat(refusalStopDetails.explanation()).contains("explanation")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val refusalStopDetails =
            RefusalStopDetails.builder()
                .category(RefusalStopDetails.Category.CYBER)
                .explanation("explanation")
                .build()

        val roundtrippedRefusalStopDetails =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(refusalStopDetails),
                jacksonTypeRef<RefusalStopDetails>(),
            )

        assertThat(roundtrippedRefusalStopDetails).isEqualTo(refusalStopDetails)
    }
}
