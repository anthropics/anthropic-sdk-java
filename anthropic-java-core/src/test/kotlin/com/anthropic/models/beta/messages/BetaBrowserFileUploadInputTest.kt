package com.anthropic.models.beta.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BetaBrowserFileUploadInputTest {

    @Test
    fun create() {
        val betaBrowserFileUploadInput =
            BetaBrowserFileUploadInput.builder()
                .target(BetaBrowserRefTarget.of("ref"))
                .addDocumentId("string")
                .addPath("string")
                .tabId("tab_id")
                .build()

        assertThat(betaBrowserFileUploadInput.target()).isEqualTo(BetaBrowserRefTarget.of("ref"))
        assertThat(betaBrowserFileUploadInput.documentIds().getOrNull()).containsExactly("string")
        assertThat(betaBrowserFileUploadInput.paths().getOrNull()).containsExactly("string")
        assertThat(betaBrowserFileUploadInput.tabId()).contains("tab_id")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBetaBrowserFileUploadInput =
            BetaBrowserFileUploadInput.of(BetaBrowserRefTarget.of("ref"))

        val betaBrowserFileUploadInput =
            baseBetaBrowserFileUploadInput
                .toBuilder()
                .addDocumentId("string")
                .addPath("string")
                .build()

        assertThat(betaBrowserFileUploadInput.documentIds().getOrNull()).containsExactly("string")
        assertThat(betaBrowserFileUploadInput.paths().getOrNull()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val betaBrowserFileUploadInput =
            BetaBrowserFileUploadInput.builder()
                .target(BetaBrowserRefTarget.of("ref"))
                .addDocumentId("string")
                .addPath("string")
                .tabId("tab_id")
                .build()

        val roundtrippedBetaBrowserFileUploadInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(betaBrowserFileUploadInput),
                jacksonTypeRef<BetaBrowserFileUploadInput>(),
            )

        assertThat(roundtrippedBetaBrowserFileUploadInput).isEqualTo(betaBrowserFileUploadInput)
    }
}
